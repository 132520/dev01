package com.fc.service.impl;

import com.fc.dao.AlleviationMapper;
import com.fc.entity.Alleviation;
import com.fc.entity.User;
import com.fc.service.AlleviationService;
import com.fc.vo.DataVO;
import com.fc.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AlleviationServiceImpl implements AlleviationService{
    @Autowired
    private AlleviationMapper alleviationMapper;

    @Override
    public ResultVO getList(Integer pageNum, Integer pageSize, Long id) {
        List<Alleviation> alleviations;
        ResultVO resultVO;

        try{
            if (id == null) {
                PageHelper.startPage(pageNum, pageSize);

                alleviations = alleviationMapper.selectByExample(null);
            } else {
                Alleviation alleviation = alleviationMapper.selectByPrimaryKey(id);
                alleviations = new ArrayList<>();
                alleviations.add(alleviation);
            }
            PageInfo<Alleviation> pageInfo = new PageInfo<>(alleviations);

            DataVO dataVO = new DataVO<>(pageInfo.getTotal(), alleviations, pageNum, pageSize);

            resultVO = new ResultVO(200,"扶贫获取成功", true, dataVO);

        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(-1000, "扶贫政策获取失败", false, null);

        }
        return resultVO;

    }

    @Override
    public ResultVO add(Alleviation alleviation) {
        ResultVO resultVO;
        if (alleviation.getCreateTime() ==  null) {
            alleviation.setCreateTime(new Date());
            alleviation.setLastClickTime(null);
        }

        int affectedRows = alleviationMapper.insertSelective(alleviation);

        if (affectedRows > 0) {
            click(alleviation.getId(), null);
            resultVO = new ResultVO(200, "扶贫添加成功", true, null);
        } else {
            resultVO = new ResultVO(-1000, "扶贫添加失败", false, null);
        }
        return resultVO;
    }

    @Override
    public ResultVO update(Alleviation alleviation) {
        ResultVO resultVO;

        int affectedRows = alleviationMapper.updateByPrimaryKeySelective(alleviation);

        if (affectedRows > 0) {
            Alleviation result = alleviationMapper.selectByPrimaryKey(alleviation.getId());

            resultVO = new ResultVO(200, "修改政策成功", true, result);
        } else {
            resultVO = new ResultVO(-1000, "修改失败", false, null);
        }
        return resultVO;
    }

    @Override
    public ResultVO delete(Long id) {
        ResultVO resultVO;
        int affectedRows = alleviationMapper.deleteByPrimaryKey(id);

        if (affectedRows > 0) {
            resultVO = new ResultVO(200, "删除成功", true, null);
        } else {
            resultVO = new ResultVO(-1000, "删除政策失败", false, null);
        }
        return resultVO;
    }

    @Override
    public ResultVO click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }

        Integer affectedRows = alleviationMapper.click(id, lastClickTime);

        ResultVO resultVO;

        if (affectedRows > 0) {
            resultVO = new ResultVO(200, "点击量+1", true, null);
        } else {
            resultVO = new ResultVO(-1000, "点击失败", false, null);
        }

        return resultVO;
    }
}
