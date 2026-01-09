package org.ruoyi.admit.service;

import org.ruoyi.admit.domain.vo.AdmissionDashboardStatsVo;

public interface AdmissionDashboardService {

    AdmissionDashboardStatsVo queryStats(Integer admissionYear);
}
