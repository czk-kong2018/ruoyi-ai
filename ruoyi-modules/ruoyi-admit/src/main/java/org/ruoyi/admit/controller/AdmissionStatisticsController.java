package org.ruoyi.admit.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.admit.domain.bo.AdmissionStatisticsBo;
import org.ruoyi.admit.domain.vo.AdmissionStatisticsVo;
import org.ruoyi.admit.service.AdmissionStatisticsService;
import org.ruoyi.common.core.domain.R;
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
 * 高校历年录取情况统计
 *
 * @author ageerle
 * @date 2026-01-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admit/admissionStatistics")
public class AdmissionStatisticsController extends BaseController {

    private final AdmissionStatisticsService admissionStatisticsService;

    /**
     * 查询高校历年录取情况统计列表
     */
    @SaCheckPermission("admit:admissionStatistics:list")
    @GetMapping("/list")
    public TableDataInfo<AdmissionStatisticsVo> list(AdmissionStatisticsBo bo, PageQuery pageQuery) {
        return admissionStatisticsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出高校历年录取情况统计列表
     */
    @SaCheckPermission("admit:admissionStatistics:export")
    @Log(title = "高校历年录取情况统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AdmissionStatisticsBo bo, HttpServletResponse response) {
        List<AdmissionStatisticsVo> list = admissionStatisticsService.queryList(bo);
        ExcelUtil.exportExcel(list, "高校历年录取情况统计", AdmissionStatisticsVo.class, response);
    }

    /**
     * 获取高校历年录取情况统计详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("admit:admissionStatistics:query")
    @GetMapping("/{id}")
    public R<AdmissionStatisticsVo> getInfo(@NotNull(message = "主键不能为空")
                                            @PathVariable Long id) {
        return R.ok(admissionStatisticsService.queryById(id));
    }

    /**
     * 同步录取数据源统计数据
     *
     * @param admissionYear 录取年份
     */
    @SaCheckPermission("admit:admissionStatistics:edit")
    @Log(title = "同步录取统计数据", businessType = BusinessType.UPDATE)
    @PostMapping("/sync")
    public R<String> syncFromFullRecord(@RequestParam Integer admissionYear) {
        int count = admissionStatisticsService.syncFromFullRecord(admissionYear);
        return R.ok("count:"+count, "同步成功，共生成 " + count + " 条统计记录");
    }
}
