package com.fc.service.impl;

import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVo getList(Integer pageNum, Integer pageSize, Long id) {
        ResultVo resultVo;
        List<User> users;

        try {
            if (id == null) {
                PageHelper.startPage(pageNum, pageSize);
                users = userMapper.selectByExample(null);

            } else {
                User user = userMapper.selectByPrimaryKey(id);
                users = new ArrayList<>();
                users.add(user);
            }
            PageInfo<User> pageInfo = new PageInfo<>(users);

            DataVo<User> dataVo = new DataVo<>(pageInfo.getTotal(), users, pageNum, pageSize);

            resultVo = new ResultVo(200, "查询用户信息成功", true, dataVo);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(-1000, "查询用户信息失败", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo add(User user) {
        ResultVo resultVo;

        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }

        int affectedRows = userMapper.insertSelective(user);

        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "添加新用户成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "添加用户失败", false, null);
        }

        return resultVo;
    }

    @Override
    public ResultVo update(User user) {
        ResultVo resultVo;

        int affectedRows = userMapper.updateByPrimaryKeySelective(user);

        if (affectedRows > 0) {
            User result = userMapper.selectByPrimaryKey(user.getId());
            resultVo = new ResultVo(200, "修改用户信息成功", true, result);

        } else {
            resultVo = new ResultVo(-1000, "修改用户信息失败", false, null);

        }
        return resultVo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo resultVo;

        int affectedRows = userMapper.deleteByPrimaryKey(id);

        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "删除用户成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "删除用户失败", false, null);
        }
        return resultVo;
    }
}
