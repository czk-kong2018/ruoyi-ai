package org.ruoyi.admit.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.admit.domain.AdmissionTask;
import org.ruoyi.core.domain.BaseEntity;

import java.util.Date;

/**
 * 招生录取待办事项业务对象 admission_task
 *
 * @author ageerle
 * @date 2026-01-09
 */
@Data
@AutoMapper(target = AdmissionTask.class, reverseConvertGenerate = false)
public class AdmissionTaskBo {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 招生年份，如2025
     */
    private Integer admissionYear;

    /**
     * 招生月份
     */
    private Integer admissionMonth;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务详情
     */
    private String description;

    /**
     * 状态: 0-待办, 1-进行中, 2-已完成
     */
    private Integer status;

    /**
     * 优先级: 1-低, 2-中, 3-高
     */
    private Integer priority;

    /**
     * 截止时间
     */
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deadline;

    /**
     * 待办事项类别: 数据统计/宣传/招生录取等
     */
    private String classification;

    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;
}
