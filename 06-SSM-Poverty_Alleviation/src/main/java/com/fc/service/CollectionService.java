package com.fc.service;

import com.fc.entity.Collection;
import com.fc.vo.ResultVo;

public interface CollectionService {
    ResultVo getList(Integer pageNum, Integer pageSize, Long id);

    ResultVo add(Collection collection);

    ResultVo update(Collection collection);

    ResultVo delete(Long id);
}
