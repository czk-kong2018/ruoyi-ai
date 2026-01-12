package org.ruoyi.admit.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.ruoyi.admit.domain.AdmissionStatistics;

import java.math.BigDecimal;

/**
 * 高校历年录取情况统计业务对象 admission_statistics
 *
 * @author ageerle
 * @date 2026-01-09
 */
@Data
@AutoMapper(target = AdmissionStatistics.class, reverseConvertGenerate = false)
public class AdmissionStatisticsBo {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 高校名称
     */
    private String universityName;

    /**
     * 录取年份
     */
    private Integer admissionYear;

    /**
     * 生源省份
     */
    private String province;

    /**
     * 科类名称
     */
    private String subjectCategory;

    /**
     * 专业组
     */
    private String majorGroup;

    /**
     * 专业代码
     */
    private String majorCode;

    /**
     * 专业名称
     */
    private String admissionMajor;

    /**
     * 二级学院名称
     */
    private String collegeName;

    /**
     * 录取人数
     */
    private Integer admitCount;

    /**
     * 录取最低分
     */
    private BigDecimal minScore;

    /**
     * 录取最高分
     */
    private BigDecimal maxScore;

    /**
     * 录取平均分
     */
    private BigDecimal avgScore;

    /**
     * 录取最高排位
     */
    private Integer highestRank;

    /**
     * 录取最低排位
     */
    private Integer lowestRank;

    /**
     * 数据来源: 0-本校, 1-外校
     */
    private Integer dataSource;

    /**
     * 备注
     */
    private String remark;
}
