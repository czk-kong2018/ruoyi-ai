package org.ruoyi.admit.service.strategy;

import jakarta.servlet.http.HttpServletResponse;

/**
 * 报表导出策略接口
 * 使用策略模式处理不同类型的报表导出逻辑
 *
 * @author ageerle
 */
public interface ReportStrategy {

    /**
     * 获取策略支持的报表类型标识
     * 该值应与字典 data_report 中的 dictValue 对应
     *
     * @return 报表类型标识
     */
    String getReportType();

    /**
     * 执行报表导出
     *
     * @param year     统计年份
     * @param response HTTP响应对象，用于输出Excel文件
     */
    void export(Integer year, HttpServletResponse response);
}
