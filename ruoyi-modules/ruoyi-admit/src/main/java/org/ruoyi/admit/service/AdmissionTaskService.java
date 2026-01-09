package org.ruoyi.admit.service;

import org.ruoyi.admit.domain.bo.AdmissionTaskBo;
import org.ruoyi.admit.domain.vo.AdmissionTaskVo;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 招生录取待办事项Service接口
 *
 * @author ageerle
 * @date 2026-01-09
 */
public interface AdmissionTaskService {

    /**
     * 查询招生录取待办事项
     */
    AdmissionTaskVo queryById(Long id);

    /**
     * 查询招生录取待办事项列表
     */
    TableDataInfo<AdmissionTaskVo> queryPageList(AdmissionTaskBo bo, PageQuery pageQuery);

    /**
     * 查询招生录取待办事项列表
     */
    List<AdmissionTaskVo> queryList(AdmissionTaskBo bo);

    /**
     * 新增招生录取待办事项
     */
    Boolean insertByBo(AdmissionTaskBo bo);

    /**
     * 修改招生录取待办事项
     */
    Boolean updateByBo(AdmissionTaskBo bo);

    /**
     * 校验并批量删除招生录取待办事项信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
