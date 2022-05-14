package com.fc.service.impl;

import com.fc.dao.PoorMapper;
import com.fc.entity.Poor;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
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
public class PoorServiceImp implements PoorService {
    @Autowired
    private PoorMapper poorMapper;
    @Override
    public ResultVO getlist(Integer pageNum, Integer pageSize, Long id) {
        ResultVO resultVO;
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
            DataVO<PoorWithBLOBs> dataVO = new DataVO<>(pageInfo.getTotal(), poors, pageNum, pageSize);

            resultVO = new ResultVO(200, "查询贫困户信息成功", true, dataVO);
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(-1000, "查询失败", false, null);
        }
        return resultVO;
    }

    @Override
    public ResultVO add(PoorWithBLOBs poor) {
        ResultVO resultVO;
        if (poor.getCreateTime() == null) {
            poor.setCreateTime(new Date());
            poor.setLastClickTime(null);
        }
        int affectedRows = poorMapper.insertSelective(poor);

        if (affectedRows > 0) {
            PoorWithBLOBs result = poorMapper.selectByPrimaryKey(poor.getId());
            resultVO = new ResultVO(200, "添加了一个新的贫困户", true, result);
        } else {
            resultVO = new ResultVO(-1000, "没有添加成功", false, null);
        }
        return resultVO;
    }

    @Override
    public ResultVO update(PoorWithBLOBs poor) {
        ResultVO resultVO;
        int affectedRows = poorMapper.updateByPrimaryKeySelective(poor);
        if (affectedRows > 0) {
            PoorWithBLOBs result = poorMapper.selectByPrimaryKey(poor.getId());
            resultVO = new ResultVO(200, "修改成功", true, result);
        } else {
            resultVO = new ResultVO(-1000, "修改了个寂寞", false, null);
        }
        return resultVO;
    }

    @Override
    public ResultVO delete(Long id) {
        ResultVO resultVO;
        int affectedRows = poorMapper.deleteByPrimaryKey(id);
        if (affectedRows > 0) {
            resultVO = new ResultVO(200, "删除成功", true, null);
        } else {
            resultVO = new ResultVO(-1000, "删了什么？", false, null);
        }
        return resultVO;
    }

    @Override
    public ResultVO click(Long id, Date lastClickTime) {
        ResultVO resultVO;
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }
        Integer affectedRows = poorMapper.click(id, lastClickTime);
        if (affectedRows > 0) {
            resultVO = new ResultVO(200, "点击量+1", true, null);
        } else {
            resultVO = new ResultVO(-1000, "点击失败", false, null);
        }
        return resultVO;
    }


}
