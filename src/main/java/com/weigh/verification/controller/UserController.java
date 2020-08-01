package com.weigh.verification.controller;

import com.weigh.verification.annotation.PassToken;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.UserEntity;
import com.weigh.verification.model.UserModel;
import com.weigh.verification.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PassToken
    @PostMapping("login")
    Result login(UserModel userModel) {
        Object res = userService.login(userModel);

        Result result = new Result();
        if(res == null){
            result.setCode(400);
            result.setMsg("用户名或密码错误");
        }else{
            result.setCode(200);
            result.setData(res);
            result.setMsg("登录成功");
        }

        return result;
    }

    @GetMapping("getInfo/{id}")
    Result getInfo(@PathVariable Integer id) {
        return new Result();
    }

    @GetMapping("getList")
    Result getList() {
        return new Result();
    }

    @PostMapping("add")
    Result add(UserModel userModel) {
        Integer res = userService.add(userModel);

        Result result = new Result();
        if(res != 1){
            result.setCode(400);
            result.setMsg("新增用户失败");
        }else{
            result.setCode(200);
            result.setMsg("新增用户成功");
        }

        return result;
    }

    @PutMapping("edit/{id}")
    Result edit(@PathVariable Integer id, @Validated UserEntity userEntity) {
        return new Result();
    }

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Integer id) {
        return new Result();
    }

    @PutMapping("modifyState/{id}")
    Result modifyState(@PathVariable Integer id) {
        return new Result();
    }
}
