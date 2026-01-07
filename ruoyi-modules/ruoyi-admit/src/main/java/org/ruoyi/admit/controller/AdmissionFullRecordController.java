package org.ruoyi.admit.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.admit.domain.bo.AdmissionFullRecordBo;
import org.ruoyi.admit.domain.vo.AdmissionFullRecordVo;
import org.ruoyi.admit.service.AdmissionFullRecordService;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.common.excel.utils.ExcelUtil;
import org.ruoyi.common.idempotent.annotation.RepeatSubmit;
import org.ruoyi.common.log.annotation.Log;
import org.ruoyi.common.log.enums.BusinessType;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.common.core.utils.StringUtils;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 录取数据源
 *
 * @author ageerle
 * @date Mon Jan 05 11:13:51 HKT 2026
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admit/admissionFullRecord")
public class AdmissionFullRecordController extends BaseController {

    private final AdmissionFullRecordService admissionFullRecordService;

    /**
     * 查询录取数据源列表
     */
    @SaCheckPermission("admit:admissionFullRecord:list")
    @GetMapping("/list")
    public TableDataInfo<AdmissionFullRecordVo> list(AdmissionFullRecordBo bo, PageQuery pageQuery) {
        TableDataInfo<AdmissionFullRecordVo> tableDataInfo = admissionFullRecordService.queryPageList(bo, pageQuery);
        if (tableDataInfo.getRows() != null) {
            tableDataInfo.getRows().forEach(this::desensitizeIdCard);
        }
        return tableDataInfo;
    }

    /**
     * 导出录取数据源列表
     */
    @SaCheckPermission("admit:admissionFullRecord:export")
    @Log(title = "录取数据源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AdmissionFullRecordBo bo, HttpServletResponse response) {
        List<AdmissionFullRecordVo> list = admissionFullRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "录取数据源", AdmissionFullRecordVo.class, response);
    }

    /**
     * 获取录取数据源详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("admit:admissionFullRecord:query")
    @GetMapping("/{id}")
    public R<AdmissionFullRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                            @PathVariable Integer id) {
        AdmissionFullRecordVo vo = admissionFullRecordService.queryById(id);
        desensitizeIdCard(vo);
        return R.ok(vo);
    }

    /**
     * 新增录取数据源
     */
    @SaCheckPermission("admit:admissionFullRecord:add")
    @Log(title = "录取数据源", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AdmissionFullRecordBo bo) {
        return toAjax(admissionFullRecordService.insertByBo(bo));
    }

    /**
     * 修改录取数据源
     */
    @SaCheckPermission("admit:admissionFullRecord:edit")
    @Log(title = "录取数据源", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AdmissionFullRecordBo bo) {
        return toAjax(admissionFullRecordService.updateByBo(bo));
    }

    /**
     * 删除录取数据源
     *
     * @param ids 主键串
     */
    @SaCheckPermission("admit:admissionFullRecord:remove")
    @Log(title = "录取数据源", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Integer[] ids) {
        return toAjax(admissionFullRecordService.deleteWithValidByIds(List.of(ids), true));
    }

    /**
     * 身份证号脱敏处理
     *
     * 仅在对外返回的视图对象上进行处理，避免影响数据库中原始数据。
     */
    private void desensitizeIdCard(AdmissionFullRecordVo vo) {
        if (vo == null) {
            return;
        }
        String idCard = vo.getIdCard();
        if (StringUtils.isBlank(idCard)) {
            return;
        }
        // 简单脱敏规则：保留前4位和后4位，中间使用星号替换
        if (idCard.length() <= 8) {
            vo.setIdCard("****");
            return;
        }
        String prefix = idCard.substring(0, 4);
        String suffix = idCard.substring(idCard.length() - 4);
        vo.setIdCard(prefix + "****" + suffix);
    }
}
