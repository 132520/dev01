package com.fc.controller;

import com.fc.entity.Alleviation;
import com.fc.entity.Carousel;
import com.fc.service.AlleviationService;
import com.fc.service.CarouselService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("alleviation")
public class AlleviationController {
    @Autowired
    private AlleviationService alleviationService;

    @GetMapping("getlist")
    public ResultVO getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                            Long id) {
        return alleviationService.getList(pageNum, pageSize, id);
    }

    @GetMapping("add")
    public ResultVO add(@RequestBody Alleviation alleviation) {
        return alleviationService.add(alleviation);
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody Alleviation alleviation) {
        return alleviationService.update(alleviation);
    }

    @GetMapping("delete")
    public ResultVO delete(Long id) {
        return alleviationService.delete(id);
    }

    @GetMapping("click")
    public  ResultVO click(Long id, Date lastClickTime){
        return alleviationService.click(id, lastClickTime);
    }

}
