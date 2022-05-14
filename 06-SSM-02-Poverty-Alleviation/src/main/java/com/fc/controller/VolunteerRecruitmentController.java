package com.fc.controller;

import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerRecruitmentService;
import com.fc.vo.ResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("volunteer")
public class VolunteerRecruitmentController {
    @Autowired
    private VolunteerRecruitmentService volunteerRecruitmentService;

    @RequestMapping("getlist")
    public ResultVO getlist(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                            Long id) {
        return volunteerRecruitmentService.getlist(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVO add(@RequestBody VolunteerRecruitment volunteerRecruitment) {
        return volunteerRecruitmentService.add(volunteerRecruitment);
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody VolunteerRecruitment volunteerRecruitment) {
        return  volunteerRecruitmentService.update(volunteerRecruitment);
    }

    @GetMapping("delete")
    public ResultVO delete(Long id) {
        return volunteerRecruitmentService.delete(id);
    }
}
