package org.ruoyi.admit.mapper;

import org.ruoyi.admit.domain.AdmissionFullRecord;
import org.ruoyi.admit.domain.vo.AdmissionFullRecordVo;
import org.ruoyi.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 录取数据源Mapper接口
 *
 * @author ageerle
 * @date Mon Jan 05 11:13:51 HKT 2026
 */
@Mapper
public interface AdmissionFullRecordMapper extends BaseMapperPlus<AdmissionFullRecord, AdmissionFullRecordVo> {

}
