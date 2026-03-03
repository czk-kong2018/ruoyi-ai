package org.ruoyi.admit.service.strategy;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 快报表报表导出策略
 * 对应字典 data_report 中的 kuaibao_report 类型
 *
 * @author ageerle
 */
@Slf4j
@Component
public class KuaibaoReportStrategy implements ReportStrategy {

    @Override
    public String getReportType() {
        return "kuaibao_report";
    }

    @Override
    public void export(Integer year, HttpServletResponse response) {
        log.info("开始导出快报表报表，年份: {}", year);
        // TODO: 实现快报表报表导出逻辑
        // 1. 查询数据
        // 2. 组装Excel
        // 3. 输出到response
        log.info("快报表报表导出完成");
    }
}
