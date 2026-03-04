package org.ruoyi.plan.service;

import org.ruoyi.plan.domain.bo.ScoreSegmentBo;
import org.ruoyi.plan.domain.vo.ScoreSegmentVo;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;

import java.util.List;

/**
 * 一分一段表Service接口
 *
 * @author ruoyi
 * @date 2026-03-03
 */
public interface ScoreSegmentService {

    /**
     * 查询分页列表
     */
    TableDataInfo<ScoreSegmentVo> queryPageList(ScoreSegmentBo bo, PageQuery pageQuery);

    /**
     * 查询列表
     */
    List<ScoreSegmentVo> queryList(ScoreSegmentBo bo);
}
