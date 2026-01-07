package org.ruoyi.admit.service.impl;

import org.ruoyi.common.core.utils.MapstructUtils;
    import org.ruoyi.core.page.TableDataInfo;
    import org.ruoyi.core.page.PageQuery;
    import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ruoyi.admit.domain.bo.AdmissionFullRecordBo;
import org.ruoyi.admit.domain.vo.AdmissionFullRecordVo;
import org.ruoyi.admit.domain.AdmissionFullRecord;
import org.ruoyi.admit.mapper.AdmissionFullRecordMapper;
import org.ruoyi.admit.service.AdmissionFullRecordService;
import org.ruoyi.common.core.utils.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 录取数据源Service业务层处理
 *
 * @author ageerle
 * @date Mon Jan 05 11:13:51 HKT 2026
 */
@RequiredArgsConstructor
@Service
public class AdmissionFullRecordServiceImpl implements AdmissionFullRecordService {

    private final AdmissionFullRecordMapper baseMapper;

    /**
     * 查询录取数据源
     */
    @Override
    public AdmissionFullRecordVo queryById(Integer id) {
        return baseMapper.selectVoById(id);
    }

        /**
         * 查询录取数据源列表
         */
        @Override
        public TableDataInfo<AdmissionFullRecordVo> queryPageList(AdmissionFullRecordBo bo, PageQuery pageQuery) {
            LambdaQueryWrapper<AdmissionFullRecord> lqw = buildQueryWrapper(bo);
            Page<AdmissionFullRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
            return TableDataInfo.build(result);
        }

    /**
     * 查询录取数据源列表
     */
    @Override
    public List<AdmissionFullRecordVo> queryList(AdmissionFullRecordBo bo) {
        LambdaQueryWrapper<AdmissionFullRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AdmissionFullRecord> buildQueryWrapper(AdmissionFullRecordBo bo) {
        LambdaQueryWrapper<AdmissionFullRecord> lqw = Wrappers.lambdaQuery();
                    lqw.eq(bo.getId() != null, AdmissionFullRecord::getId, bo.getId());
                    lqw.eq(bo.getAdmissionYear() != null, AdmissionFullRecord::getAdmissionYear, bo.getAdmissionYear());
                    lqw.likeRight(StringUtils.isNotBlank(bo.getNoticeSerialNo()), AdmissionFullRecord::getNoticeSerialNo, bo.getNoticeSerialNo());
                    lqw.likeRight(StringUtils.isNotBlank(bo.getCandidateNo()), AdmissionFullRecord::getCandidateNo, bo.getCandidateNo());
                    lqw.likeRight(StringUtils.isNotBlank(bo.getName()), AdmissionFullRecord::getName, bo.getName());
                    lqw.eq(StringUtils.isNotBlank(bo.getGender()), AdmissionFullRecord::getGender, bo.getGender());
                    lqw.eq(StringUtils.isNotBlank(bo.getIdCard()), AdmissionFullRecord::getIdCard, bo.getIdCard());
                    lqw.likeRight(StringUtils.isNotBlank(bo.getCollegeName()), AdmissionFullRecord::getCollegeName, bo.getCollegeName());
                    lqw.eq(StringUtils.isNotBlank(bo.getReportLocation()), AdmissionFullRecord::getReportLocation, bo.getReportLocation());
                    lqw.eq(StringUtils.isNotBlank(bo.getSubjectCategory()), AdmissionFullRecord::getSubjectCategory, bo.getSubjectCategory());
                    lqw.likeRight(StringUtils.isNotBlank(bo.getEthnicity()), AdmissionFullRecord::getEthnicity, bo.getEthnicity());
                    lqw.likeRight(StringUtils.isNotBlank(bo.getPoliticalStatus()), AdmissionFullRecord::getPoliticalStatus, bo.getPoliticalStatus());
                    lqw.likeRight(StringUtils.isNotBlank(bo.getProvince()), AdmissionFullRecord::getProvince, bo.getProvince());
        return lqw;
    }

    /**
     * 新增录取数据源
     */
    @Override
    public Boolean insertByBo(AdmissionFullRecordBo bo) {
        AdmissionFullRecord add = MapstructUtils.convert(bo, AdmissionFullRecord. class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改录取数据源
     */
    @Override
    public Boolean updateByBo(AdmissionFullRecordBo bo) {
        AdmissionFullRecord update = MapstructUtils.convert(bo, AdmissionFullRecord. class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AdmissionFullRecord entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除录取数据源
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
