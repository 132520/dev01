package com.fc.service.impl;

import com.fc.dao.VolunteerRecruitmentMapper;
import com.fc.entity.User;
import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerRecruitmentService;
import com.fc.vo.DataVO;
import com.fc.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.DataBufferShort;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VolunteerRecruitmentServiceImpl implements VolunteerRecruitmentService {
    @Autowired
    private VolunteerRecruitmentMapper volunteerRecruitmentMapper;
    @Override
    public ResultVO getlist(Integer pageNum, Integer pageSize, Long id) {
        List<VolunteerRecruitment> volunteerRecruitments;

        ResultVO resultVO;

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

           DataVO dataVO = new DataVO<>(pageInfo.getTotal(), volunteerRecruitments, pageNum, pageSize);

            resultVO = new ResultVO(200, "志愿者查询成功", true, dataVO);
       } catch (Exception e) {
           e.printStackTrace();
           resultVO = new ResultVO(-1000, "志愿者查询失败", false, null);
       }
        return resultVO;
    }

    @Override
    public ResultVO add(VolunteerRecruitment volunteerRecruitment) {
        ResultVO resultVO;
        if (volunteerRecruitment.getCreateTime() == null) {
            volunteerRecruitment.setCreateTime(new Date());
        }

        int affectedRows = volunteerRecruitmentMapper.insertSelective(volunteerRecruitment);

        if (affectedRows > 0) {
            resultVO = new ResultVO(200, "志愿者添加成功", true, null);
        } else {
            resultVO = new ResultVO(-1000, "志愿者添加失败", false, null);
        }
        return resultVO;
    }

    @Override
    public ResultVO update(VolunteerRecruitment volunteerRecruitment) {
        ResultVO resultVO;
        int affectedRows = volunteerRecruitmentMapper.updateByPrimaryKeySelective(volunteerRecruitment);

        if (affectedRows > 0) {
            resultVO = new ResultVO(200, "志愿者修改成功", true, null);
        } else {
            resultVO = new ResultVO(-1000, "志愿者修改信息失败", false, null);
        }
        return resultVO;
    }

    @Override
    public ResultVO delete(Long id) {
        ResultVO resultVO;
        int affectedRows = volunteerRecruitmentMapper.deleteByPrimaryKey(id);
        if (affectedRows > 0 ) {
            resultVO = new ResultVO(200, "删除志愿者成功", true, null);

        } else {
            resultVO = new ResultVO(-1000, "删除失败", false, null);
        }
        return resultVO;
    }
}
