package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {
    @PostMapping("upload")
    Result upload() {
        Result result = new Result();
        result.setCode(200);
        return result;
    }

    @GetMapping("check")
    Result check() {
        return new Result();
    }

    @GetMapping("getList")
    Result getList() {
        return new Result();
    }

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Integer id) {
        return new Result();
    }

    @GetMapping("downloads/{id}")
    Result downloads(@PathVariable Integer id) {
        return new Result();
    }
}
