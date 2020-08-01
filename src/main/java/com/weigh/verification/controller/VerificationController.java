package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("verification")
public class VerificationController {
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

    @GetMapping("getInfo/{id}")
    Result getInfo(@PathVariable Integer id) {
        return new Result();
    }

    @GetMapping("downloads/{id}")
    Result downloads(@PathVariable Integer id) {
        return new Result();
    }
}
