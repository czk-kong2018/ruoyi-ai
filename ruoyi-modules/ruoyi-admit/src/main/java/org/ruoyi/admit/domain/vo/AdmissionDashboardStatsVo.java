package org.ruoyi.admit.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdmissionDashboardStatsVo implements Serializable {

    private Overview overview;
    private List<StatItem> genderStats;
    private List<StatItem> provinceStats;
    private List<StatItem> subjectCategoryStats;
    private List<StatItem> reportLocationStats;
    private List<StatItem> collegeStats;

    @Data
    public static class Overview implements Serializable {

        private Long totalCount;
        private Long maleCount;
        private Long femaleCount;
        private Long provinceCount;
        private Long collegeCount;
    }

    @Data
    public static class StatItem implements Serializable {

        private String name;
        private Long value;
    }
}
