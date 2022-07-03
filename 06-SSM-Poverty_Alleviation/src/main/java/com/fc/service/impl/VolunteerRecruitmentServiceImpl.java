package com.fc.service.impl;

import com.fc.dao.VolunteerRecruitmentMapper;
import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerRecruitmentService;
import com.fc.vo.DataVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VolunteerRecruitmentServiceImpl implements VolunteerRecruitmentService {
    @Autowired
    private VolunteerRecruitmentMapper volunteerRecruitmentMapper;
    @Override
    public ResultVo getlist(Integer pageNum, Integer pageSize, Long id) {
        List<VolunteerRecruitment> volunteerRecruitments;

        ResultVo resultVo;

       try {


           if (id == null) {
               PageHelper.startPage(pageNum, pageSize);
               volunteerRecruitments = volunteerRecruitmentMapper.selectByExample(null);
           } else {
               VolunteerRecruitment result = volunteerRecruitmentMapper.selectByPrimaryKey(id);

               volunteerRecruitments = new ArrayList<>();

               volunteerRecruitments.add(result);
           }
           PageInfo<VolunteerRecruitment> pageInfo = new PageInfo<>(volunteerRecruitments);

           DataVo dataVo = new DataVo<>(pageInfo.getTotal(), volunteerRecruitments, pageNum, pageSize);

           resultVo = new ResultVo(200, "志愿者查询成功", true, dataVo);
       } catch (Exception e) {
           e.printStackTrace();
           resultVo = new ResultVo(-1000, "志愿者查询失败", false, null);
       }
        return resultVo;
    }

    @Override
    public ResultVo add(VolunteerRecruitment volunteerRecruitment) {
        ResultVo resultVo;
        if (volunteerRecruitment.getCreateTime() == null) {
            volunteerRecruitment.setCreateTime(new Date());
        }

        int affectedRows = volunteerRecruitmentMapper.insertSelective(volunteerRecruitment);

        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "志愿者添加成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "志愿者添加失败", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo update(VolunteerRecruitment volunteerRecruitment) {
        ResultVo resultVo;
        int affectedRows = volunteerRecruitmentMapper.updateByPrimaryKeySelective(volunteerRecruitment);

        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "志愿者修改成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "志愿者修改信息失败", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo resultVo;
        int affectedRows = volunteerRecruitmentMapper.deleteByPrimaryKey(id);
        if (affectedRows > 0 ) {
            resultVo = new ResultVo(200, "删除志愿者成功", true, null);

        } else {
            resultVo = new ResultVo(-1000, "删除失败", false, null);
        }
        return resultVo;
    }
}
