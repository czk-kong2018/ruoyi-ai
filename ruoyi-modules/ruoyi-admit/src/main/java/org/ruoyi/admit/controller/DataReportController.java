package org.ruoyi.admit.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ruoyi.admit.service.strategy.ReportContext;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.log.annotation.Log;
import org.ruoyi.common.log.enums.BusinessType;
import org.ruoyi.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据报表导出控制器
 *
 * @author ageerle
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admit/dataReport")
public class DataReportController extends BaseController {

    private final ReportContext reportContext;

    /**
     * 导出数据报表
     *
     * @param year       统计年份
     * @param reportType 报表类型（对应字典 data_report 的 dictValue）
     * @param response   HTTP响应
     */
    @SaCheckPermission("admit:dataReport:export")
    @Log(title = "数据报表导出", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(
            @RequestParam("year") Integer year,
            @RequestParam("reportType") String reportType,
            HttpServletResponse response) {

        log.info("收到报表导出请求，年份: {}, 类型: {}", year, reportType);

        // 检查报表类型是否支持
        if (!reportContext.supports(reportType)) {
            log.warn("不支持的报表类型: {}", reportType);
            throw new IllegalArgumentException("不支持的报表类型: " + reportType);
        }

        // 使用策略模式执行导出
        reportContext.export(reportType, year, response);
    }

    /**
     * 检查报表类型是否支持
     *
     * @param reportType 报表类型
     * @return 是否支持
     */
    @GetMapping("/supports")
    public R<Boolean> supports(@RequestParam("reportType") String reportType) {
        return R.ok(reportContext.supports(reportType));
    }
}
