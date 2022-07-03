package com.fc.service.impl;

import com.fc.dao.AlleviationMapper;
import com.fc.entity.Alleviation;
import com.fc.entity.User;
import com.fc.service.AlleviationService;
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
public class AlleviationServiceImpl implements AlleviationService {
    @Autowired
    private AlleviationMapper alleviationMapper;
    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        ResultVo resultVo;
        List<Alleviation> alleviations;

        try {
            if (id == null) {
                PageHelper.startPage(pageNum, pageSize);
                alleviations = alleviationMapper.selectByExample(null);

            } else {
                Alleviation alleviation = alleviationMapper.selectByPrimaryKey(id);
                alleviations = new ArrayList<>();
                alleviations.add(alleviation);
            }
            PageInfo<Alleviation> pageInfo = new PageInfo<>(alleviations);

            DataVo<Alleviation> dataVo = new DataVo<>(pageInfo.getTotal(), alleviations, pageNum, pageSize);

            resultVo = new ResultVo(200, "查询扶贫政策成功", true, dataVo);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(-1000, "查询扶贫政策失败", false, null);
        }
        return resultVo;    }

    @Override
    public ResultVo add(Alleviation alleviation) {
        ResultVo resultVo;

        if (alleviation.getCreateTime() == null) {
            alleviation.setCreateTime(new Date());
        }

        int affectedRows = alleviationMapper.insertSelective(alleviation);

        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "添加新扶贫政策成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "添加扶贫政策失败", false, null);
        }

        return resultVo;
    }

    @Override
    public ResultVo update(Alleviation alleviation) {
        ResultVo resultVo;

        int affectedRows = alleviationMapper.updateByPrimaryKeySelective(alleviation);

        if (affectedRows > 0) {
            Alleviation result = alleviationMapper.selectByPrimaryKey(alleviation.getId());
            resultVo = new ResultVo(200, "修改扶贫政策成功", true, result);

        } else {
            resultVo = new ResultVo(-1000, "修改扶贫政策失败", false, null);

        }
        return resultVo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo resultVo;

        int affectedRows = alleviationMapper.deleteByPrimaryKey(id);

        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "删除扶贫政策成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "删除扶贫政策失败", false, null);
        }
        return resultVo;
    }
}
