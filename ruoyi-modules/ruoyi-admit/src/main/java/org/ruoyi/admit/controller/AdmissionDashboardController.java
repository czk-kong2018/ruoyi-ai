package org.ruoyi.admit.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.ruoyi.admit.domain.vo.AdmissionDashboardStatsVo;
import org.ruoyi.admit.service.AdmissionDashboardService;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admit/admissionDashboard")
public class AdmissionDashboardController extends BaseController {

    private final AdmissionDashboardService admissionDashboardService;

    @SaCheckPermission("admit:admissionDashboard:query")
    @GetMapping("/stats")
    public R<AdmissionDashboardStatsVo> stats(
            @RequestParam(required = false) Integer admissionYear,
            @RequestParam(required = false) String province,
            @RequestParam(required = false) String subjectCategory) {
        return R.ok(admissionDashboardService.queryStats(admissionYear, province, subjectCategory));
    }
    
    /**
     * 根据省份获取科类列表（用于级联下拉）
     */
    @SaCheckPermission("admit:admissionDashboard:query")
    @GetMapping("/subjectCategoriesByProvince")
    public R<java.util.List<AdmissionDashboardStatsVo.StatItem>> subjectCategoriesByProvince(
            @RequestParam(required = false) Integer admissionYear,
            @RequestParam(required = false) String province) {
        return R.ok(admissionDashboardService.querySubjectCategoriesByProvince(admissionYear, province));
    }
}
