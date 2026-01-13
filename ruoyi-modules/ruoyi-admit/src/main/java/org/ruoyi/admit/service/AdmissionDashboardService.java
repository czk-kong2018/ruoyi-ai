package org.ruoyi.admit.service;

import org.ruoyi.admit.domain.vo.AdmissionDashboardStatsVo;

import java.util.List;

public interface AdmissionDashboardService {

    AdmissionDashboardStatsVo queryStats(Integer admissionYear, String province, String subjectCategory);
    
    List<AdmissionDashboardStatsVo.StatItem> querySubjectCategoriesByProvince(Integer admissionYear, String province);
}
