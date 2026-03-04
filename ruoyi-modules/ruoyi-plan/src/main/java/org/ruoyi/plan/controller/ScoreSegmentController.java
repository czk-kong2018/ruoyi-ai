package org.ruoyi.plan.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.ruoyi.plan.domain.bo.ScoreSegmentBo;
import org.ruoyi.plan.domain.vo.ScoreSegmentVo;
import org.ruoyi.plan.service.ScoreSegmentService;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 一分一段表
 *
 * @author ruoyi
 * @date 2026-03-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/plan/scoreSegment")
public class ScoreSegmentController extends BaseController {

    private final ScoreSegmentService scoreSegmentService;

    /**
     * 查询一分一段表列表
     */
    @SaCheckPermission("plan:scoreSegment:list")
    @GetMapping("/list")
    public TableDataInfo<ScoreSegmentVo> list(ScoreSegmentBo bo, PageQuery pageQuery) {
        return scoreSegmentService.queryPageList(bo, pageQuery);
    }
}
