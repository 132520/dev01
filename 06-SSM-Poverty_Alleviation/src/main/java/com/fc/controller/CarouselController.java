package com.fc.controller;

import com.fc.entity.Carousel;
import com.fc.service.CarouselService;
import com.fc.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @GetMapping("state")
    public ResultVo changeStatus(Integer id) {
        return carouselService.changeStatus(id);
    }

    @GetMapping("getlist")
    public ResultVo getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                            Integer id) {
        return carouselService.getList(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVo add(@RequestBody Carousel carousel) {
        return carouselService.add(carousel);
    }

    @PostMapping("update")
    public ResultVo update(@RequestBody Carousel carousel) {
        return carouselService.update(carousel);
    }

    @GetMapping("delete")
    public ResultVo delete(Integer id) {
        return carouselService.delete(id);
    }

}
