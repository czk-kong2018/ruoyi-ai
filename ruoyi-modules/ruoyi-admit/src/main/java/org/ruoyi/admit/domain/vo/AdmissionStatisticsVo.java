package org.ruoyi.admit.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.ruoyi.admit.domain.AdmissionStatistics;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 高校历年录取情况统计视图对象 admission_statistics
 *
 * @author ageerle
 * @date 2026-01-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AdmissionStatistics.class)
public class AdmissionStatisticsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 高校名称
     */
    @ExcelProperty(value = "高校名称")
    private String universityName;

    /**
     * 录取年份
     */
    @ExcelProperty(value = "录取年份")
    private Integer admissionYear;

    /**
     * 生源省份
     */
    @ExcelProperty(value = "生源省份")
    private String province;

    /**
     * 科类名称
     */
    @ExcelProperty(value = "科类名称")
    private String subjectCategory;

    /**
     * 专业组
     */
    @ExcelProperty(value = "专业组")
    private String majorGroup;

    /**
     * 专业代码
     */
    @ExcelProperty(value = "专业代码")
    private String majorCode;

    /**
     * 专业名称
     */
    @ExcelProperty(value = "专业名称")
    private String admissionMajor;

    /**
     * 二级学院名称
     */
    @ExcelProperty(value = "二级学院")
    private String collegeName;

    /**
     * 录取人数
     */
    @ExcelProperty(value = "录取人数")
    private Integer admitCount;

    /**
     * 录取最低分
     */
    @ExcelProperty(value = "最低分")
    private BigDecimal minScore;

    /**
     * 录取最高分
     */
    @ExcelProperty(value = "最高分")
    private BigDecimal maxScore;

    /**
     * 录取平均分
     */
    @ExcelProperty(value = "平均分")
    private BigDecimal avgScore;

    /**
     * 录取最高排位
     */
    @ExcelProperty(value = "最高排位")
    private Integer highestRank;

    /**
     * 录取最低排位
     */
    @ExcelProperty(value = "最低排位")
    private Integer lowestRank;

    /**
     * 数据来源: 0-本校, 1-外校
     */
    @ExcelProperty(value = "数据来源")
    private Integer dataSource;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
