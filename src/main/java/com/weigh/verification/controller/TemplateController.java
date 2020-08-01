package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import com.weigh.verification.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @PostMapping("add")
    Result add() {
        return new Result();
    }

    @PutMapping("edit/{id}")
    Result edit(@PathVariable Integer id) {
        return new Result();
    }

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Integer id) {
        return new Result();
    }

    @GetMapping("getList")
    Result getList() {
        return new Result();
    }

    @GetMapping("getAll")
    Result getAll() {
        return new Result();
    }

    @GetMapping("analysis")
    Result analysis(@RequestParam(value = "filePath",required = true,defaultValue = "") String filePath){
        templateService.analysis(filePath);

        return new Result();
    }
}
