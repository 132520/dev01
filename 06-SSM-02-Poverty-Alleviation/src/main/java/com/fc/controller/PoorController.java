package com.fc.controller;

import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("poor")
public class PoorController {
    @Autowired
    private PoorService poorService;

    @GetMapping("getlist")
    public ResultVO getlist(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                            Long id) {
        return poorService.getlist(pageNum, pageSize, id);
    }

    @PostMapping("add")
    public ResultVO add(@RequestBody PoorWithBLOBs poor) {
        return poorService.add(poor);
    }

    @PostMapping("update")
    public ResultVO update(@RequestBody PoorWithBLOBs poor) {
        return poorService.update(poor);
    }

    @GetMapping("delete")
    public ResultVO delete(Long id) {
        return poorService.delete(id);
    }
}
