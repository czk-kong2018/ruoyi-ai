package org.ruoyi.admit.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.ruoyi.admit.domain.AdmissionFullRecord;
import org.ruoyi.common.core.utils.IdCardMaskUtil;
import org.ruoyi.common.core.utils.PhoneMaskUtil;
import org.ruoyi.common.excel.annotation.ExcelDictFormat;
import org.ruoyi.common.excel.convert.ExcelDictConvert;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * 录取数据源视图对象 admission_full_record
 *
 * @author ageerle
 * @date Mon Jan 05 11:13:51 HKT 2026
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AdmissionFullRecord.class)
public class AdmissionFullRecordVo implements Serializable {

    private Integer id;
    /**
     * 录取年份
     */
    @ExcelProperty(value = "录取年份")
    private Integer admissionYear;
    /**
     * 录取通知书流水号
     */
    @ExcelProperty(value = "录取通知书流水号")
    private String noticeSerialNo;
    /**
     * 考生号
     */
    @ExcelProperty(value = "考生号")
    private String candidateNo;
    /**
     * 准考证号
     */
    @ExcelProperty(value = "准考证号")
    private String ticketNo;
    /**
     * 考生姓名
     */
    @ExcelProperty(value = "考生姓名")
    private String name;
    /**
     * 性别
     */
    @ExcelProperty(value = "性别", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String gender;
    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号")
    private String idCard;
    /**
     * 生源地级市代码
     */
    @ExcelProperty(value = "生源地级市代码")
    private String cityCode;
    /**
     * 生源地级市
     */
    @ExcelProperty(value = "生源地级市")
    private String cityName;
    /**
     * 专业组
     */
    @ExcelProperty(value = "专业组")
    private String majorGroup;
    /**
     * 分数
     */
    @ExcelProperty(value = "分数")
    private BigDecimal score;
    /**
     * 投档总分
     */
    @ExcelProperty(value = "投档总分")
    private String totalScore;
    /**
     * 本科排位
     */
    @ExcelProperty(value = "本科排位")
    private String scoreRank;
    /**
     * 专业代码
     */
    @ExcelProperty(value = "专业代码")
    private String majorCode;
    /**
     * 录取专业名称
     */
    @ExcelProperty(value = "录取专业名称")
    private String admissionMajor;
    /**
     * 录取志愿专业名称
     */
    @ExcelProperty(value = "录取志愿专业名称")
    private String appliedMajor;
    /**
     * 是否调剂
     */
    @ExcelProperty(value = "是否调剂")
    private String isAdjusted;
    /**
     * 二级学院名称
     */
    @ExcelProperty(value = "二级学院名称")
    private String collegeName;
    /**
     * 报到地点
     */
    @ExcelProperty(value = "报到地点")
    private String reportLocation;
    /**
     * 户口所在地
     */
    @ExcelProperty(value = "户口所在地")
    private String hukouAddress;
    /**
     * 科类名称
     */
    @ExcelProperty(value = "科类名称")
    private String subjectCategory;
    /**
     * 出生日期
     */
    @ExcelProperty(value = "出生日期")
    private LocalDate birthday;
    /**
     * 毕业学校
     */
    @ExcelProperty(value = "毕业学校")
    private String highSchool;
    /**
     * 联系手机
     */
    @ExcelProperty(value = "联系手机")
    private String mobile;
    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String telephone;
    /**
     * 邮政编码
     */
    @ExcelProperty(value = "邮政编码")
    private String zipCode;
    /**
     * 收件人
     */
    @ExcelProperty(value = "收件人")
    private String receiverName;
    /**
     * 邮寄地址
     */
    @ExcelProperty(value = "邮寄地址")
    private String mailAddress;
    /**
     * 考生类别
     */
    @ExcelProperty(value = "考生类别")
    private String studentCategory;
    /**
     * 民族
     */
    @ExcelProperty(value = "民族")
    private String ethnicity;
    /**
     * 政治面貌
     */
    @ExcelProperty(value = "政治面貌")
    private String politicalStatus;
    /**
     * 数学
     */
    @ExcelProperty(value = "数学")
    private BigDecimal mathScore;
    /**
     * 外语语种
     */
    @ExcelProperty(value = "外语语种")
    private String foreignLangType;
    /**
     * 外语
     */
    @ExcelProperty(value = "外语")
    private BigDecimal foreignLangScore;
    /**
     * 学制
     */
    @ExcelProperty(value = "学制")
    private String schoolingLength;
    /**
     * 生源省份
     */
    @ExcelProperty(value = "生源省份")
    private String province;
    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


    public String getIdCard() {
        return IdCardMaskUtil.mask(idCard);
    }

    public String getTelephone() {
        return PhoneMaskUtil.mask(telephone);
    }

    public String getMobile() {
        return PhoneMaskUtil.mask(mobile);
    }

}
