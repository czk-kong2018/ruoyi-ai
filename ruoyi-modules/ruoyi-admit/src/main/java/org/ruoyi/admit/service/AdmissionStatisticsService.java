package org.ruoyi.admit.service;

import org.ruoyi.admit.domain.bo.AdmissionStatisticsBo;
import org.ruoyi.admit.domain.vo.AdmissionStatisticsVo;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;

import java.util.List;

/**
 * 高校历年录取情况统计Service接口
 *
 * @author ageerle
 * @date 2026-01-09
 */
public interface AdmissionStatisticsService {

    /**
     * 查询记录详情
     */
    AdmissionStatisticsVo queryById(Long id);

    /**
     * 查询分页列表
     */
    TableDataInfo<AdmissionStatisticsVo> queryPageList(AdmissionStatisticsBo bo, PageQuery pageQuery);

    /**
     * 查询列表
     */
    List<AdmissionStatisticsVo> queryList(AdmissionStatisticsBo bo);

    /**
     * 从录取数据源同步统计数据
     * @param admissionYear 录取年份
     * @return 同步记录数
     */
    int syncFromFullRecord(Integer admissionYear);
}
