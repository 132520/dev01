package com.fc.service.impl;

import com.fc.dao.PoorMapper;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
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
public class PoorServiceImpl implements PoorService {
    @Autowired
    private PoorMapper poorMapper;
    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        ResultVo resultVo;
        List<PoorWithBLOBs> poors;

        try {
            if (id == null) {
                PageHelper.startPage(pageNum, pageSize);
                poors = poorMapper.selectByExampleWithBLOBs(null);
            } else {
                PoorWithBLOBs poor = poorMapper.selectByPrimaryKey(id);
                poors = new ArrayList<>();
                poors.add(poor);
            }

            PageInfo<PoorWithBLOBs> pageInfo = new PageInfo<>(poors);

            DataVo<PoorWithBLOBs> dataVo = new DataVo<>(pageInfo.getTotal(), poors, pageNum, pageSize);

            resultVo = new ResultVo(200, "查询贫困户成功", true, dataVo);
            } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(-1000, "查询贫困户失败", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo add(PoorWithBLOBs poor) {
        ResultVo resultVo;

        if (poor.getCreateTime() == null) {
            poor.setCreateTime(new Date());
        }

        int affectedRows = poorMapper.insertSelective(poor);

        if (affectedRows > 0) {

            resultVo = new ResultVo(200, "添加贫困户成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "添加贫困户失败", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo update(PoorWithBLOBs poor) {
        ResultVo resultVo;

        int affectedRows = poorMapper.updateByPrimaryKeyWithBLOBs(poor);

        if (affectedRows > 0) {
            PoorWithBLOBs result = poorMapper.selectByPrimaryKey(poor.getId());

            resultVo = new ResultVo(200, "修改贫困户信息成功", true, result);
        } else {
            resultVo = new ResultVo(-1000, "修改贫困户信息失败", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo resultVo;

        int affectedRows = poorMapper.deleteByPrimaryKey(id);

        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "删除贫困户成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "删除贫困户失败", false, null);
        }
        return resultVo;
    }
}
