package com.fc.service;

import com.fc.entity.VolunteerRecruitment;
import com.fc.vo.ResultVO;

public interface VolunteerRecruitmentService {
    ResultVO getlist(Integer pageNum, Integer pageSize, Long id);

    ResultVO add(VolunteerRecruitment volunteerRecruitment);

    ResultVO update(VolunteerRecruitment volunteerRecruitment);

    ResultVO delete(Long id);
}
