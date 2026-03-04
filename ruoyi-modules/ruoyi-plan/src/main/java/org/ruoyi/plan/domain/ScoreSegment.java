package org.ruoyi.plan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 一分一段表 score_segment
 *
 * @author ruoyi
 * @date 2026-03-03
 */
@Data
@TableName("score_segment")
public class ScoreSegment {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 科类名称
     */
    private String subjectCategory;

    /**
     * 分数
     */
    private java.math.BigDecimal score;

    /**
     * 人数
     */
    private Integer num;

    /**
     * 累计人数
     */
    private Integer totalNum;
}
