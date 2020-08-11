package com.weigh.verification.controller;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.annotation.PassToken;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TableEntity;
import com.weigh.verification.model.UserModel;
import com.weigh.verification.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (res == null) {
            result.setCode(400);
            result.setMsg("用户名或密码错误");
        } else {
            result.setCode(200);
            result.setData(res);
            result.setMsg("登录成功");
        }

        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("getInfo/{id}")
    Result getInfo(@PathVariable Integer id) {
        Result result = new Result();

        result.setCode(200);
        result.setData(userService.getInfo(id));
        result.setMsg("success");

        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("getList")
    Result getList(TableEntity tableEntity) {
        PageInfo<UserModel> userList = userService.getList(tableEntity.getPage(), tableEntity.getPageSize());

        Result result = new Result();

        result.setCode(200);
        result.setData(userList);
        result.setMsg("success");
        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @PostMapping("add")
    Result add(UserModel userModel) {
        Integer res = userService.add(userModel);

        Result result = new Result();
        if (res != 1) {
            result.setCode(400);
            result.setMsg("新增用户失败");
        } else {
            result.setCode(200);
            result.setMsg("新增用户成功");
        }

        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @PutMapping("edit/{id}")
    Result edit(@PathVariable Integer id, UserModel userModel) {
        Integer res = userService.edit(id, userModel);
        Result result = new Result();
        if (res != 1) {
            result.setCode(400);
            result.setMsg("编辑用户失败");
        } else {
            result.setCode(200);
            result.setMsg("编辑用户成功");
        }

        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Integer id) {
        Integer res = userService.delete(id);
        Result result = new Result();
        if (res != 1) {
            result.setCode(400);
            result.setMsg("删除失败");
        } else {
            result.setCode(200);
            result.setMsg("删除成功");
        }

        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @PutMapping("modifyState/{id}")
    Result modifyState(@PathVariable Integer id) {
        Integer res = userService.modifyState(id);
        Result result = new Result();
        if (res != 1) {
            result.setCode(400);
            result.setMsg("修改状态失败");
        } else {
            result.setCode(200);
            result.setMsg("修改状态成功");
        }

        return result;
    }
}
