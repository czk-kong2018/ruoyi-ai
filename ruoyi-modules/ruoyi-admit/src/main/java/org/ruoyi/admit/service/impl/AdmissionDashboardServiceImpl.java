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
    public AdmissionDashboardStatsVo queryStats(Integer admissionYear, String province, String subjectCategory) {
        AdmissionDashboardStatsVo stats = new AdmissionDashboardStatsVo();
        List<AdmissionDashboardStatsVo.StatItem> genderStats = listGroupStats("gender", admissionYear);

        stats.setGenderStats(genderStats);
        // Use aggregated province stats (merge by keyword)
        stats.setProvinceStats(listAggregatedProvinceStats(admissionYear));
        stats.setSubjectCategoryStats(listGroupStats("subject_category", admissionYear));
        stats.setReportLocationStats(listGroupStats("report_location", admissionYear));
        stats.setCollegeStats(listGroupStats("college_name", admissionYear));
        
        // Add score and rank distribution stats (filtered by province and subjectCategory)
        stats.setScoreDistributionStats(listScoreDistribution(admissionYear, province, subjectCategory));
        stats.setRankDistributionStats(listRankDistribution(admissionYear, province, subjectCategory));

        AdmissionDashboardStatsVo.Overview overview = new AdmissionDashboardStatsVo.Overview();
        long totalCount = countTotal(admissionYear);
        overview.setTotalCount(totalCount);
        overview.setMaleCount(sumGender(genderStats, true));
        overview.setFemaleCount(sumGender(genderStats, false));
        overview.setProvinceCount(countDistinct("province", admissionYear));
        overview.setCollegeCount(countDistinct("college_name", admissionYear));
        
        // 汉族人数统计
        long hanCount = countByEthnicity(admissionYear, "汉族");
        overview.setHanCount(hanCount);
        // 汉族占比
        double hanRatio = totalCount > 0 ? (double) hanCount / totalCount * 100 : 0.0;
        overview.setHanRatio(Math.round(hanRatio * 10) / 10.0);

        stats.setOverview(overview);
        return stats;
    }
    
    /**
     * 分数分布统计 - 按分数段分组（按省份和科类筛选）
     */
    private List<AdmissionDashboardStatsVo.StatItem> listScoreDistribution(Integer admissionYear, String province, String subjectCategory) {
        QueryWrapper<AdmissionFullRecord> wrapper = buildBaseWrapper(admissionYear);
        if (StringUtils.isNotBlank(province)) {
            wrapper.like("province", province);
        }
        if (StringUtils.isNotBlank(subjectCategory)) {
            wrapper.eq("subject_category", subjectCategory);
        }
        wrapper.isNotNull("score").gt("score", 0);
        wrapper.select("score as name", "count(*) as value");
        wrapper.groupBy("score");
        wrapper.orderByAsc("score");
        List<Map<String, Object>> rows = baseMapper.selectMaps(wrapper);
        List<AdmissionDashboardStatsVo.StatItem> items = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            AdmissionDashboardStatsVo.StatItem item = toStatItemNumeric(row);
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }
    
    /**
     * 排位分布统计 - 按排位段分组（每5000名一个区间，按省份和科类筛选）
     */
    private List<AdmissionDashboardStatsVo.StatItem> listRankDistribution(Integer admissionYear, String province, String subjectCategory) {
        QueryWrapper<AdmissionFullRecord> wrapper = buildBaseWrapper(admissionYear);
        if (StringUtils.isNotBlank(province)) {
            wrapper.like("province", province);
        }
        if (StringUtils.isNotBlank(subjectCategory)) {
            wrapper.eq("subject_category", subjectCategory);
        }
        wrapper.isNotNull("score_rank").gt("score_rank", 0);
        // 按5000名一个区间分组 - 使用相同表达式避免 only_full_group_by 错误
        wrapper.select("FLOOR(score_rank / 5000) * 5000 as name", "count(*) as value");
        wrapper.groupBy("FLOOR(score_rank / 5000) * 5000");
        wrapper.orderByAsc("FLOOR(score_rank / 5000) * 5000");
        List<Map<String, Object>> rows = baseMapper.selectMaps(wrapper);
        List<AdmissionDashboardStatsVo.StatItem> items = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            AdmissionDashboardStatsVo.StatItem item = toStatItemNumeric(row);
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }
    
    private AdmissionDashboardStatsVo.StatItem toStatItemNumeric(Map<String, Object> row) {
        if (row == null) {
            return null;
        }
        Object nameObj = row.get("name");
        if (nameObj == null) {
            return null;
        }
        AdmissionDashboardStatsVo.StatItem item = new AdmissionDashboardStatsVo.StatItem();
        // Convert numeric name to string
        item.setName(String.valueOf(nameObj));
        Object valueObj = row.get("value");
        if (valueObj instanceof Number) {
            item.setValue(((Number) valueObj).longValue());
        } else {
            item.setValue(0L);
        }
        return item;
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
    
    private long countByEthnicity(Integer admissionYear, String ethnicity) {
        QueryWrapper<AdmissionFullRecord> wrapper = buildBaseWrapper(admissionYear);
        wrapper.eq("ethnicity", ethnicity);
        return baseMapper.selectCount(wrapper);
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
    
    /**
     * 获取聚合后的省份统计（按关键字合并）
     * 例如: 新疆喀什 -> 新疆, 香港中学文凭考试 -> 香港
     */
    private List<AdmissionDashboardStatsVo.StatItem> listAggregatedProvinceStats(Integer admissionYear) {
        // 省份关键字映射
        String[] provinceKeywords = {
            "广东", "北京", "上海", "天津", "重庆",
            "河北", "山西", "辽宁", "吉林", "黑龙江",
            "江苏", "浙江", "安徽", "福建", "江西",
            "山东", "河南", "湖北", "湖南", "海南",
            "四川", "贵州", "云南", "陕西", "甘肃",
            "青海", "台湾", "内蒙古", "广西", "西藏",
            "宁夏", "新疆", "香港", "澳门"
        };
        
        List<AdmissionDashboardStatsVo.StatItem> rawStats = listGroupStats("province", admissionYear);
        Map<String, Long> aggregated = new java.util.LinkedHashMap<>();
        
        for (AdmissionDashboardStatsVo.StatItem item : rawStats) {
            String provinceName = item.getName();
            Long value = item.getValue() != null ? item.getValue() : 0L;
            
            // 查找匹配的省份关键字
            String matchedProvince = provinceName;
            for (String keyword : provinceKeywords) {
                if (provinceName.contains(keyword)) {
                    matchedProvince = keyword;
                    break;
                }
            }
            
            aggregated.merge(matchedProvince, value, Long::sum);
        }
        
        // 转换为StatItem列表并按值排序
        List<AdmissionDashboardStatsVo.StatItem> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : aggregated.entrySet()) {
            AdmissionDashboardStatsVo.StatItem item = new AdmissionDashboardStatsVo.StatItem();
            item.setName(entry.getKey());
            item.setValue(entry.getValue());
            result.add(item);
        }
        result.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));
        
        return result;
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
    
    @Override
    public List<AdmissionDashboardStatsVo.StatItem> querySubjectCategoriesByProvince(Integer admissionYear, String province) {
        QueryWrapper<AdmissionFullRecord> wrapper = buildBaseWrapper(admissionYear);
        if (StringUtils.isNotBlank(province)) {
            wrapper.like("province", province);
        }
        wrapper.select("subject_category as name", "count(*) as value");
        wrapper.isNotNull("subject_category").ne("subject_category", "");
        wrapper.groupBy("subject_category");
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
}
