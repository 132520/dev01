package com.fc.service.impl;

import com.fc.dao.CollectionMapper;
import com.fc.entity.Collection;
import com.fc.service.CollectionService;

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
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        List<Collection> collections;
        ResultVo resultVo;

        try{
            if (id == null) {
                PageHelper.startPage(pageNum, pageSize);

                collections = collectionMapper.selectByExample(null);
            } else {
                Collection collection = collectionMapper.selectByPrimaryKey(id);
                collections = new ArrayList<>();
                collections.add(collection);
            }
            PageInfo<Collection> pageInfo = new PageInfo<>(collections);

            DataVo dataVo = new DataVo<>(pageInfo.getTotal(), collections, pageNum, pageSize);

            resultVo = new ResultVo(200,"收藏表获取成功", true, dataVo);

        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(-1000, "收藏表获取失败", false, null);

        }
        return resultVo;
    }

    @Override
    public ResultVo add(Collection collection) {
        ResultVo resultVo;
        if (collection.getCreateTime() ==  null) {
            collection.setCreateTime(new Date());
        }

        int affectedRows = collectionMapper.insertSelective(collection);

        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "收藏表添加成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "收藏表添加失败", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo update(Collection collection) {
        ResultVo resultVo;
        int affectedRows = collectionMapper.updateByPrimaryKeySelective(collection);

        if (affectedRows > 0) {
            Collection result = collectionMapper.selectByPrimaryKey(collection.getId());

            resultVo = new ResultVo(200, "收藏表修改成功", true, result);
        } else {
            resultVo = new ResultVo(-1000, "收藏表修改失败", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo resultVo;
        int affectedRows = collectionMapper.deleteByPrimaryKey(id);

        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "收藏表删除成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "收藏表删除失败", false, null);
        }
        return resultVo;
    }
}
