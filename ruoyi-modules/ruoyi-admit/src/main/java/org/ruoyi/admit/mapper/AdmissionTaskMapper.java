package org.ruoyi.admit.mapper;

import org.ruoyi.admit.domain.AdmissionTask;
import org.ruoyi.admit.domain.vo.AdmissionTaskVo;
import org.ruoyi.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 招生录取待办事项Mapper接口
 *
 * @author ageerle
 * @date 2026-01-09
 */
@Mapper
public interface AdmissionTaskMapper extends BaseMapperPlus<AdmissionTask, AdmissionTaskVo> {

}
