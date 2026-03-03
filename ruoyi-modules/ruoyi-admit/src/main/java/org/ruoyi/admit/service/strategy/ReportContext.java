package org.ruoyi.admit.service.strategy;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表策略上下文
 * 负责根据报表类型动态选择并执行相应的导出策略
 *
 * @author ageerle
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ReportContext {

    /**
     * Spring会自动注入所有实现了ReportStrategy接口的Bean
     */
    private final List<ReportStrategy> strategies;

    /**
     * 策略映射表：reportType -> Strategy
     */
    private final Map<String, ReportStrategy> strategyMap = new HashMap<>();

    /**
     * 初始化策略映射表
     */
    @PostConstruct
    public void init() {
        for (ReportStrategy strategy : strategies) {
            strategyMap.put(strategy.getReportType(), strategy);
            log.info("注册报表策略: {} -> {}", strategy.getReportType(), strategy.getClass().getSimpleName());
        }
    }

    /**
     * 执行报表导出
     *
     * @param reportType 报表类型（对应字典值）
     * @param year       统计年份
     * @param response   HTTP响应对象
     * @throws IllegalArgumentException 如果报表类型不存在
     */
    public void export(String reportType, Integer year, HttpServletResponse response) {
        ReportStrategy strategy = strategyMap.get(reportType);
        if (strategy == null) {
            log.error("未找到报表类型对应的策略: {}", reportType);
            throw new IllegalArgumentException("不支持的报表类型: " + reportType);
        }
        log.info("使用策略 {} 导出报表，类型: {}, 年份: {}", strategy.getClass().getSimpleName(), reportType, year);
        strategy.export(year, response);
    }

    /**
     * 检查是否支持指定的报表类型
     *
     * @param reportType 报表类型
     * @return 是否支持
     */
    public boolean supports(String reportType) {
        return strategyMap.containsKey(reportType);
    }
}
