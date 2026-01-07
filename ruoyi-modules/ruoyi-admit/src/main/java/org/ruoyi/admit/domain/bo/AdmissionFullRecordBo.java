package org.ruoyi.admit.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.ruoyi.admit.domain.AdmissionFullRecord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 录取数据源业务对象 admission_full_record
 *
 * @author ageerle
 * @date Mon Jan 05 11:13:51 HKT 2026
 */
@Data

@AutoMapper(target = AdmissionFullRecord.class, reverseConvertGenerate = false)
public class AdmissionFullRecordBo implements Serializable {

    private Integer id;

    private Integer admissionYear;

    /**
     * 录取通知书流水号
     */
    private String noticeSerialNo;

    /**
     * 考生号
     */
    private String candidateNo;

    /**
     * 准考证号
     */
    private String ticketNo;

    /**
     * 考生姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 生源地级市代码
     */
    private String cityCode;

    /**
     * 生源地级市
     */
    private String cityName;

    /**
     * 专业组
     */
    private String majorGroup;

    /**
     * 分数
     */
    private BigDecimal score;

    /**
     * 投档总分
     */
    private String totalScore;

    /**
     * 本科排位
     */
    private String scoreRank;

    /**
     * 专业代码
     */
    private String majorCode;

    /**
     * 录取专业名称
     */
    private String admissionMajor;

    /**
     * 录取志愿专业名称
     */
    private String appliedMajor;

    /**
     * 是否调剂
     */
    private String isAdjusted;

    /**
     * 二级学院名称
     */
    private String collegeName;

    /**
     * 报到地点
     */
    private String reportLocation;

    /**
     * 户口所在地
     */
    private String hukouAddress;

    /**
     * 科类名称
     */
    private String subjectCategory;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 毕业学校
     */
    private String highSchool;

    /**
     * 联系手机
     */
    private String mobile;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 收件人
     */
    private String receiverName;

    /**
     * 邮寄地址
     */
    private String mailAddress;

    /**
     * 考生类别
     */
    private String studentCategory;

    /**
     * 民族
     */
    private String ethnicity;

    /**
     * 政治面貌
     */
    private String politicalStatus;

    /**
     * 数学
     */
    private BigDecimal mathScore;

    /**
     * 外语语种
     */
    private String foreignLangType;

    /**
     * 外语
     */
    private BigDecimal foreignLangScore;

    /**
     * 学制
     */
    private String schoolingLength;

    /**
     * 照片文件名称
     */
    private String photoFilename;

    /**
     * 生源省份
     */
    private String province;

    /**
     * 备注
     */
    private String remark;
}
