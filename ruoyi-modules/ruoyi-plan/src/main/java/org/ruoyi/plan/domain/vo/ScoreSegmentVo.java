package org.ruoyi.plan.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.ruoyi.plan.domain.ScoreSegment;

import java.io.Serializable;

/**
 * 一分一段表视图对象 score_segment
 *
 * @author ruoyi
 * @date 2026-03-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ScoreSegment.class)
public class ScoreSegmentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 年份
     */
    @ExcelProperty(value = "年份")
    private Integer year;

    /**
     * 科类名称
     */
    @ExcelProperty(value = "科类名称")
    private String subjectCategory;

    /**
     * 分数
     */
    @ExcelProperty(value = "分数")
    private java.math.BigDecimal score;

    /**
     * 人数
     */
    @ExcelProperty(value = "人数")
    private Integer num;

    /**
     * 累计人数
     */
    @ExcelProperty(value = "累计人数")
    private Integer totalNum;
}
