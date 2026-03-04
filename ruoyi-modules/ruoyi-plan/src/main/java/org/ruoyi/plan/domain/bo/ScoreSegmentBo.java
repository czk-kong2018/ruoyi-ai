package org.ruoyi.plan.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.ruoyi.plan.domain.ScoreSegment;

/**
 * 一分一段表业务对象 score_segment
 *
 * @author ruoyi
 * @date 2026-03-03
 */
@Data
@AutoMapper(target = ScoreSegment.class, reverseConvertGenerate = false)
public class ScoreSegmentBo {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 科类名称
     */
    private String subjectCategory;
}
