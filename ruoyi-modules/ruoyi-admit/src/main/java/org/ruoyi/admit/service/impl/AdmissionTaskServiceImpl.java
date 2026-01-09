package org.ruoyi.admit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ruoyi.admit.domain.AdmissionTask;
import org.ruoyi.admit.domain.bo.AdmissionTaskBo;
import org.ruoyi.admit.domain.vo.AdmissionTaskVo;
import org.ruoyi.admit.mapper.AdmissionTaskMapper;
import org.ruoyi.admit.service.AdmissionTaskService;
import org.ruoyi.common.core.utils.MapstructUtils;
import org.ruoyi.common.core.utils.StringUtils;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 招生录取待办事项Service业务层处理
 *
 * @author ageerle
 * @date 2026-01-09
 */
@RequiredArgsConstructor
@Service
public class AdmissionTaskServiceImpl implements AdmissionTaskService {

    private final AdmissionTaskMapper baseMapper;

    /**
     * 查询招生录取待办事项
     */
    @Override
    public AdmissionTaskVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询招生录取待办事项列表
     */
    @Override
    public TableDataInfo<AdmissionTaskVo> queryPageList(AdmissionTaskBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AdmissionTask> lqw = buildQueryWrapper(bo);
        Page<AdmissionTaskVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询招生录取待办事项列表
     */
    @Override
    public List<AdmissionTaskVo> queryList(AdmissionTaskBo bo) {
        LambdaQueryWrapper<AdmissionTask> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AdmissionTask> buildQueryWrapper(AdmissionTaskBo bo) {
        LambdaQueryWrapper<AdmissionTask> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAdmissionYear() != null, AdmissionTask::getAdmissionYear, bo.getAdmissionYear());
        lqw.eq(bo.getAdmissionMonth() != null, AdmissionTask::getAdmissionMonth, bo.getAdmissionMonth());
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), AdmissionTask::getTitle, bo.getTitle());
        lqw.eq(bo.getStatus() != null, AdmissionTask::getStatus, bo.getStatus());
        lqw.eq(bo.getPriority() != null, AdmissionTask::getPriority, bo.getPriority());
        lqw.eq(StringUtils.isNotBlank(bo.getClassification()), AdmissionTask::getClassification, bo.getClassification());
        return lqw;
    }

    /**
     * 新增招生录取待办事项
     */
    @Override
    public Boolean insertByBo(AdmissionTaskBo bo) {
        AdmissionTask add = MapstructUtils.convert(bo, AdmissionTask.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改招生录取待办事项
     */
    @Override
    public Boolean updateByBo(AdmissionTaskBo bo) {
        AdmissionTask update = MapstructUtils.convert(bo, AdmissionTask.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除招生录取待办事项
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
