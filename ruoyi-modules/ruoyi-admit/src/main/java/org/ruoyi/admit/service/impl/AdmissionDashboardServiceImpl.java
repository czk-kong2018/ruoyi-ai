package org.ruoyi.admit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.ruoyi.admit.domain.AdmissionFullRecord;
import org.ruoyi.admit.domain.vo.AdmissionDashboardStatsVo;
import org.ruoyi.admit.mapper.AdmissionFullRecordMapper;
import org.ruoyi.admit.service.AdmissionDashboardService;
import org.ruoyi.common.core.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AdmissionDashboardServiceImpl implements AdmissionDashboardService {

    private static final String GENDER_MALE_CN = "男";
    private static final String GENDER_FEMALE_CN = "女";

    private final AdmissionFullRecordMapper baseMapper;

    @Override
    public AdmissionDashboardStatsVo queryStats(Integer admissionYear) {
        AdmissionDashboardStatsVo stats = new AdmissionDashboardStatsVo();
        List<AdmissionDashboardStatsVo.StatItem> genderStats = listGroupStats("gender", admissionYear);

        stats.setGenderStats(genderStats);
        stats.setProvinceStats(listGroupStats("province", admissionYear));
        stats.setSubjectCategoryStats(listGroupStats("subject_category", admissionYear));
        stats.setReportLocationStats(listGroupStats("report_location", admissionYear));
        stats.setCollegeStats(listGroupStats("college_name", admissionYear));

        AdmissionDashboardStatsVo.Overview overview = new AdmissionDashboardStatsVo.Overview();
        overview.setTotalCount(countTotal(admissionYear));
        overview.setMaleCount(sumGender(genderStats, true));
        overview.setFemaleCount(sumGender(genderStats, false));
        overview.setProvinceCount(countDistinct("province", admissionYear));
        overview.setCollegeCount(countDistinct("college_name", admissionYear));

        stats.setOverview(overview);
        return stats;
    }

    private long countTotal(Integer admissionYear) {
        QueryWrapper<AdmissionFullRecord> wrapper = buildBaseWrapper(admissionYear);
        return baseMapper.selectCount(wrapper);
    }

    private long countDistinct(String column, Integer admissionYear) {
        QueryWrapper<AdmissionFullRecord> wrapper = buildBaseWrapper(admissionYear);
        wrapper.isNotNull(column).ne(column, "");
        wrapper.select("count(distinct " + column + ")");
        List<Object> values = baseMapper.selectObjs(wrapper);
        if (values.isEmpty() || values.get(0) == null) {
            return 0L;
        }
        Object value = values.get(0);
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return 0L;
    }

    private List<AdmissionDashboardStatsVo.StatItem> listGroupStats(String column, Integer admissionYear) {
        QueryWrapper<AdmissionFullRecord> wrapper = buildBaseWrapper(admissionYear);
        wrapper.select(column + " as name", "count(*) as value");
        wrapper.isNotNull(column).ne(column, "");
        wrapper.groupBy(column);
        wrapper.orderByDesc("value");
        List<Map<String, Object>> rows = baseMapper.selectMaps(wrapper);
        List<AdmissionDashboardStatsVo.StatItem> items = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            AdmissionDashboardStatsVo.StatItem item = toStatItem(row);
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }

    private AdmissionDashboardStatsVo.StatItem toStatItem(Map<String, Object> row) {
        if (row == null) {
            return null;
        }
        Object nameObj = row.get("name");
        if (nameObj == null) {
            return null;
        }
        String name = String.valueOf(nameObj).trim();
        if (StringUtils.isBlank(name)) {
            return null;
        }
        AdmissionDashboardStatsVo.StatItem item = new AdmissionDashboardStatsVo.StatItem();
        item.setName(name);
        Object valueObj = row.get("value");
        if (valueObj instanceof Number) {
            item.setValue(((Number) valueObj).longValue());
        } else {
            item.setValue(0L);
        }
        return item;
    }

    private long sumGender(List<AdmissionDashboardStatsVo.StatItem> items, boolean male) {
        long total = 0L;
        for (AdmissionDashboardStatsVo.StatItem item : items) {
            if (item == null) {
                continue;
            }
            String name = item.getName();
            boolean matches = male ? isMale(name) : isFemale(name);
            if (matches) {
                Long value = item.getValue();
                total += value == null ? 0L : value;
            }
        }
        return total;
    }

    private boolean isMale(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        String normalized = value.trim().toLowerCase();
        return GENDER_MALE_CN.equals(value.trim())
            || "0".equals(normalized)
            || "m".equals(normalized)
            || "male".equals(normalized);
    }

    private boolean isFemale(String value) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        String normalized = value.trim().toLowerCase();
        return GENDER_FEMALE_CN.equals(value.trim())
            || "1".equals(normalized)
            || "f".equals(normalized)
            || "female".equals(normalized);
    }

    private QueryWrapper<AdmissionFullRecord> buildBaseWrapper(Integer admissionYear) {
        QueryWrapper<AdmissionFullRecord> wrapper = new QueryWrapper<>();
        if (admissionYear != null) {
            wrapper.eq("admission_year", admissionYear);
        }
        return wrapper;
    }
}
