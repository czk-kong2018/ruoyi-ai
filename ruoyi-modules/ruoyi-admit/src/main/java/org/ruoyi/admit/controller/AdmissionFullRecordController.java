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
import org.ruoyi.common.excel.utils.ExcelUtil;
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
        return R.ok(vo);
    }

}
