package org.ruoyi.admit.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ruoyi.admit.domain.AdmissionStatistics;
import org.ruoyi.admit.domain.vo.AdmissionStatisticsVo;
import org.ruoyi.core.mapper.BaseMapperPlus;

import java.util.List;
import java.util.Map;

/**
 * 高校历年录取情况统计Mapper接口
 *
 * @author ageerle
 * @date 2026-01-09
 */
@Mapper
public interface AdmissionStatisticsMapper extends BaseMapperPlus<AdmissionStatistics, AdmissionStatisticsVo> {

    /**
     * 从录取数据源聚合统计数据
     */
    List<Map<String, Object>> selectAggregatedStatsByYear(@Param("admissionYear") Integer admissionYear);
}
