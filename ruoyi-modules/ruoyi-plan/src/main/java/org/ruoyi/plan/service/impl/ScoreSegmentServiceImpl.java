package org.ruoyi.plan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ruoyi.plan.domain.ScoreSegment;
import org.ruoyi.plan.domain.bo.ScoreSegmentBo;
import org.ruoyi.plan.domain.vo.ScoreSegmentVo;
import org.ruoyi.plan.mapper.ScoreSegmentMapper;
import org.ruoyi.plan.service.ScoreSegmentService;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 一分一段表Service业务层处理
 *
 * @author ruoyi
 * @date 2026-03-03
 */
@RequiredArgsConstructor
@Service
public class ScoreSegmentServiceImpl implements ScoreSegmentService {

    private final ScoreSegmentMapper baseMapper;

    @Override
    public TableDataInfo<ScoreSegmentVo> queryPageList(ScoreSegmentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ScoreSegment> lqw = buildQueryWrapper(bo);
        Page<ScoreSegmentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public List<ScoreSegmentVo> queryList(ScoreSegmentBo bo) {
        LambdaQueryWrapper<ScoreSegment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ScoreSegment> buildQueryWrapper(ScoreSegmentBo bo) {
        LambdaQueryWrapper<ScoreSegment> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getYear() != null, ScoreSegment::getYear, bo.getYear());
        lqw.eq(cn.hutool.core.util.StrUtil.isNotBlank(bo.getSubjectCategory()), ScoreSegment::getSubjectCategory,
                bo.getSubjectCategory());
        lqw.orderByDesc(ScoreSegment::getScore);
        return lqw;
    }
}
