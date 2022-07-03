package com.fc.service.impl;

import com.fc.dao.MessageBoardMapper;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.service.MessageBoardService;
import com.fc.vo.DataVo;
import com.fc.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageBoardServiceImpl implements MessageBoardService {
    @Autowired
    private MessageBoardMapper messageBoardMapper;
    @Override
    public ResultVo getlist(Integer pageNum, Integer pageSize, Long id) {
        ResultVo resultVo;
        List<MessageBoardWithBLOBs> messageBoards;
        try {
            if (id == null) {
                PageHelper.startPage(pageNum, pageSize);
                 messageBoards = messageBoardMapper.selectByExampleWithBLOBs(null);
            } else {
                MessageBoardWithBLOBs messageBoard = messageBoardMapper.selectByPrimaryKey(id);
                messageBoards = new ArrayList<>();
                messageBoards.add(messageBoard);
            }
            PageInfo<MessageBoardWithBLOBs> pageInfo = new PageInfo<>(messageBoards);
            DataVo<MessageBoardWithBLOBs> dataVo = new DataVo<>(pageInfo.getTotal(), messageBoards, pageNum, pageSize);

            resultVo = new ResultVo(200, "查询留言板信息成功", true, dataVo);
        } catch (Exception e) {
            e.printStackTrace();
            resultVo = new ResultVo(-1000, "查询留言板信息失败", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo add(MessageBoardWithBLOBs messageBoardWithBLOBs) {
        ResultVo resultVo;

        int affectedRows = messageBoardMapper.insertSelective(messageBoardWithBLOBs);

        if (affectedRows > 0) {
            MessageBoardWithBLOBs result = messageBoardMapper.selectByPrimaryKey(messageBoardWithBLOBs.getId());
            resultVo = new ResultVo(200, "添加了新的留言", true, result);
        } else {
            resultVo = new ResultVo(-1000, "留言没有添加成功", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo update(MessageBoardWithBLOBs messageBoardWithBLOBs) {
        ResultVo resultVo;
        int affectedRows = messageBoardMapper.updateByPrimaryKeySelective(messageBoardWithBLOBs);
        if (affectedRows > 0) {
            MessageBoardWithBLOBs result = messageBoardMapper.selectByPrimaryKey(messageBoardWithBLOBs.getId());
            resultVo = new ResultVo(200, "修改留言成功", true, result);
        } else {
            resultVo = new ResultVo(-1000, "修改了个寂寞", false, null);
        }
        return resultVo;
    }

    @Override
    public ResultVo delete(Long id) {
        ResultVo resultVo;
        int affectedRows = messageBoardMapper.deleteByPrimaryKey(id);
        if (affectedRows > 0) {
            resultVo = new ResultVo(200, "删除成功", true, null);
        } else {
            resultVo = new ResultVo(-1000, "删了什么？", false, null);
        }
        return resultVo;
    }



}
