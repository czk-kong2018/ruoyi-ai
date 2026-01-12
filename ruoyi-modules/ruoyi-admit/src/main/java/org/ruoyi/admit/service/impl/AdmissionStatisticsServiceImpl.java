package org.ruoyi.admit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ruoyi.admit.domain.AdmissionStatistics;
import org.ruoyi.admit.domain.bo.AdmissionStatisticsBo;
import org.ruoyi.admit.domain.vo.AdmissionStatisticsVo;
import org.ruoyi.admit.mapper.AdmissionStatisticsMapper;
import org.ruoyi.admit.service.AdmissionStatisticsService;
import org.ruoyi.common.core.utils.StringUtils;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 高校历年录取情况统计Service业务层处理
 *
 * @author ageerle
 * @date 2026-01-09
 */
@RequiredArgsConstructor
@Service
public class AdmissionStatisticsServiceImpl implements AdmissionStatisticsService {

    private final AdmissionStatisticsMapper baseMapper;

    @Override
    public AdmissionStatisticsVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public TableDataInfo<AdmissionStatisticsVo> queryPageList(AdmissionStatisticsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AdmissionStatistics> lqw = buildQueryWrapper(bo);
        Page<AdmissionStatisticsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public List<AdmissionStatisticsVo> queryList(AdmissionStatisticsBo bo) {
        LambdaQueryWrapper<AdmissionStatistics> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AdmissionStatistics> buildQueryWrapper(AdmissionStatisticsBo bo) {
        LambdaQueryWrapper<AdmissionStatistics> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getUniversityName()), AdmissionStatistics::getUniversityName, bo.getUniversityName());
        lqw.eq(bo.getAdmissionYear() != null, AdmissionStatistics::getAdmissionYear, bo.getAdmissionYear());
        lqw.likeRight(StringUtils.isNotBlank(bo.getProvince()), AdmissionStatistics::getProvince, bo.getProvince());
        lqw.likeRight(StringUtils.isNotBlank(bo.getSubjectCategory()), AdmissionStatistics::getSubjectCategory, bo.getSubjectCategory());
        lqw.likeRight(StringUtils.isNotBlank(bo.getAdmissionMajor()), AdmissionStatistics::getAdmissionMajor, bo.getAdmissionMajor());
        lqw.orderByDesc(AdmissionStatistics::getAdmissionYear);
        return lqw;
    }

    @Override
    public int syncFromFullRecord(Integer admissionYear) {
        // 1. 先删除该年份的本校数据 (dataSource = 0)
        LambdaQueryWrapper<AdmissionStatistics> deleteWrapper = Wrappers.lambdaQuery();
        deleteWrapper.eq(AdmissionStatistics::getAdmissionYear, admissionYear);
        deleteWrapper.eq(AdmissionStatistics::getDataSource, 0);
        baseMapper.delete(deleteWrapper);

        // 2. 通过 Mapper XML 查询聚合数据
        List<java.util.Map<String, Object>> rows = baseMapper.selectAggregatedStatsByYear(admissionYear);
        
        // 3. 转换并插入
        List<AdmissionStatistics> statsList = new java.util.ArrayList<>();
        
        for (java.util.Map<String, Object> row : rows) {
            AdmissionStatistics stat = new AdmissionStatistics();
            stat.setUniversityName("广东金融学院");
            stat.setAdmissionYear(admissionYear);
            stat.setProvince((String) row.get("province"));
            stat.setSubjectCategory((String) row.get("subject_category"));
            stat.setMajorGroup((String) row.get("major_group"));
            stat.setMajorCode((String) row.get("major_code"));
            stat.setAdmissionMajor((String) row.get("admission_major"));
            stat.setCollegeName((String) row.get("college_name"));
            stat.setAdmitCount(row.get("admit_count") != null ? ((Number) row.get("admit_count")).intValue() : 0);
            stat.setMinScore(row.get("min_score") != null ? new java.math.BigDecimal(row.get("min_score").toString()) : null);
            stat.setMaxScore(row.get("max_score") != null ? new java.math.BigDecimal(row.get("max_score").toString()) : null);
            stat.setAvgScore(row.get("avg_score") != null ? new java.math.BigDecimal(row.get("avg_score").toString()) : null);
            stat.setHighestRank(row.get("highest_rank") != null ? ((Number) row.get("highest_rank")).intValue() : null);
            stat.setLowestRank(row.get("lowest_rank") != null ? ((Number) row.get("lowest_rank")).intValue() : null);
            stat.setDataSource(0); // 本校数据
            stat.setCreateTime(new java.util.Date());
            statsList.add(stat);
        }
        
        // 4. 批量插入
        if (!statsList.isEmpty()) {
            for (AdmissionStatistics stat : statsList) {
                baseMapper.insert(stat);
            }
        }
        
        return statsList.size();
    }
}

