package com.weigh.verification.controller;

import com.weigh.verification.annotation.PassToken;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @PassToken
    @PostMapping("login")
    Result login() {

        return new Result();
    }

    @GetMapping("getInfo/{id}")
    Result getInfo(@PathVariable Long id) {
        return new Result();
    }

    @GetMapping("getList")
    Result getList() {
        return new Result();
    }

    @PostMapping("add")
    Result add(@Validated UserEntity userEntity) {
        return new Result();
    }

    @PutMapping("edit/{id}")
    Result edit(@PathVariable Long id, @Validated UserEntity userEntity) {
        return new Result();
    }

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id) {
        return new Result();
    }

    @PutMapping("modifyState/{id}")
    Result modifyState(@PathVariable Long id) {
        return new Result();
    }
}
