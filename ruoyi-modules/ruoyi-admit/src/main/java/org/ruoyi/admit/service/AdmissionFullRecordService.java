package org.ruoyi.admit.service;

import org.ruoyi.admit.domain.vo.AdmissionFullRecordVo;
import org.ruoyi.admit.domain.bo.AdmissionFullRecordBo;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 录取数据源Service接口
 *
 * @author ageerle
 * @date Mon Jan 05 11:13:51 HKT 2026
 */
public interface AdmissionFullRecordService {

    /**
     * 查询录取数据源
     */
    AdmissionFullRecordVo queryById(Integer id);

    /**
     * 查询录取数据源列表
     */
    TableDataInfo<AdmissionFullRecordVo> queryPageList(AdmissionFullRecordBo bo, PageQuery pageQuery);

    /**
     * 查询录取数据源列表
     */
    List<AdmissionFullRecordVo> queryList(AdmissionFullRecordBo bo);


}
