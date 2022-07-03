package com.fc.service.impl;

import com.fc.dao.CarouselMapper;
import com.fc.entity.Carousel;
import com.fc.service.CarouselService;
import com.fc.vo.DataVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Integer id) {
        List<Carousel> carousels;

        ResultVo resultVO;

        try {
            if (id == null) {
                PageHelper.startPage(pageNum, pageSize);

                carousels = carouselMapper.selectByExample(null);
            } else {
                Carousel carousel = carouselMapper.selectByPrimaryKey(id);
                carousels = new ArrayList<>();
                carousels.add(carousel);
            }

            PageInfo<Carousel> pageInfo = new PageInfo<>(carousels);

            DataVo<Carousel> dataVO = new DataVo<>(pageInfo.getTotal(), carousels, pageNum, pageSize);

            resultVO = new ResultVo(200, "轮播图获取成功", true, dataVO);
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVo(-1000, "轮播图获取失败", false, null);
        }

        return resultVO;
    }

    @Override
    public ResultVo add(Carousel carousel) {
        // 如果没有指明是否可用，默认不可用
        if (carousel.getAvailable() == null) {
            carousel.setAvailable(false);
        }

        int affectedRows = carouselMapper.insertSelective(carousel);

        ResultVo resultVO;

        if (affectedRows > 0) {
            resultVO = new ResultVo(200, "轮播图添加成功", true, carousel);
        } else {
            resultVO = new ResultVo(-1000, "轮播图添加失败", false, null);
        }

        return resultVO;
    }

    @Override
    public ResultVo update(Carousel carousel) {
        int affectedRows = carouselMapper.updateByPrimaryKeySelective(carousel);

        ResultVo resultVO;

        if (affectedRows > 0) {
            Carousel result = carouselMapper.selectByPrimaryKey(carousel.getId());

            resultVO = new ResultVo(200, "轮播图修改成功", true, result);
        } else {
            resultVO = new ResultVo(-1000, "轮播图修改失败", false, null);
        }

        return resultVO;
    }

    @Override
    public ResultVo delete(Integer id) {
        int affectedRows = carouselMapper.deleteByPrimaryKey(id);

        ResultVo resultVO;

        if (affectedRows > 0) {
            resultVO = new ResultVo(200, "轮播图删除成功", true, null);
        } else {
            resultVO = new ResultVo(-1000, "轮播图删除失败", false, null);
        }

        return resultVO;
    }

    @Override
    public ResultVo changeStatus(Integer id) {
        int affectedRows = carouselMapper.changeStatus(id);

        ResultVo resultVO;

        if (affectedRows > 0) {
            resultVO = new ResultVo(200, "轮播图状态更换成功", true, null);
        } else {
            resultVO = new ResultVo(-1000, "轮播图状态更换失败", false, null);
        }

        return resultVO;
    }
}
