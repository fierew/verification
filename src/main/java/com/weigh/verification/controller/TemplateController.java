package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("template")
public class TemplateController {
    @PostMapping("add")
    Result add() {
        return new Result();
    }

    @PutMapping("edit/{id}")
    Result edit(@PathVariable Long id) {
        return new Result();
    }

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id) {
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
}
