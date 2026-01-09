package org.ruoyi.admit.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.admit.domain.bo.AdmissionTaskBo;
import org.ruoyi.admit.domain.vo.AdmissionTaskVo;
import org.ruoyi.admit.service.AdmissionTaskService;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.common.excel.utils.ExcelUtil;
import org.ruoyi.common.log.annotation.Log;
import org.ruoyi.common.log.enums.BusinessType;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 招生录取待办事项
 *
 * @author ageerle
 * @date 2026-01-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admit/admissionTask")
public class AdmissionTaskController extends BaseController {

    private final AdmissionTaskService admissionTaskService;

    /**
     * 查询招生录取待办事项列表
     */
    @SaCheckPermission("admit:admissionTask:list")
    @GetMapping("/list")
    public TableDataInfo<AdmissionTaskVo> list(AdmissionTaskBo bo, PageQuery pageQuery) {
        return admissionTaskService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出招生录取待办事项列表
     */
    @SaCheckPermission("admit:admissionTask:export")
    @Log(title = "招生录取待办事项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AdmissionTaskBo bo, HttpServletResponse response) {
        List<AdmissionTaskVo> list = admissionTaskService.queryList(bo);
        ExcelUtil.exportExcel(list, "招生录取待办事项", AdmissionTaskVo.class, response);
    }

    /**
     * 获取招生录取待办事项详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("admit:admissionTask:query")
    @GetMapping("/{id}")
    public R<AdmissionTaskVo> getInfo(@NotNull(message = "主键不能为空")
                                      @PathVariable Long id) {
        return R.ok(admissionTaskService.queryById(id));
    }

    /**
     * 新增招生录取待办事项
     */
    @SaCheckPermission("admit:admissionTask:add")
    @Log(title = "招生录取待办事项", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AdmissionTaskBo bo) {
        return toAjax(admissionTaskService.insertByBo(bo));
    }

    /**
     * 修改招生录取待办事项
     */
    @SaCheckPermission("admit:admissionTask:edit")
    @Log(title = "招生录取待办事项", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AdmissionTaskBo bo) {
        return toAjax(admissionTaskService.updateByBo(bo));
    }

    /**
     * 删除招生录取待办事项
     *
     * @param ids 主键串
     */
    @SaCheckPermission("admit:admissionTask:remove")
    @Log(title = "招生录取待办事项", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(admissionTaskService.deleteWithValidByIds(List.of(ids), true));
    }
}
