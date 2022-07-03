package com.fc.service;

import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.vo.ResultVo;

public interface MessageBoardService {
    ResultVo getlist(Integer pageNum, Integer pageSize, Long id);

    ResultVo add(MessageBoardWithBLOBs messageBoardWithBLOBs);

    ResultVo update(MessageBoardWithBLOBs messageBoardWithBLOBs);

    ResultVo delete(Long id);

}
