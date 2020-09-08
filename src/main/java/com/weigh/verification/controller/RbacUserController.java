package com.weigh.verification.controller;

import com.weigh.verification.annotation.PassToken;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TableEntity;
import com.weigh.verification.entity.TokenUserEntity;
import com.weigh.verification.model.RbacUserModel;
import com.weigh.verification.service.RbacUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("rbac/user")
public class RbacUserController {
    @Autowired
    RbacUserService rbacUserService;

    @PassToken
    @PostMapping("/login")
    Result login(@RequestBody RbacUserModel rbacUserModel) {
        return rbacUserService.login(rbacUserModel);
    }

    @GetMapping("/getList")
    Result getList(TableEntity tableEntity, RbacUserModel rbacUserModel) {
        return rbacUserService.getList(tableEntity.getPage(), tableEntity.getPageSize(), rbacUserModel);
    }

    @GetMapping("/getInfo")
    Result getInfo(TokenUserEntity tokenUserEntity) {
        return rbacUserService.getInfo(tokenUserEntity.getUserId());
    }

    @PassToken
    @PostMapping("/add")
    Result add(@RequestBody RbacUserModel rbacUserModel) {
        return rbacUserService.add(rbacUserModel);
    }

    @PassToken
    @PutMapping("/edit/{id}")
    Result edit(@PathVariable Integer id, @RequestBody RbacUserModel rbacUserModel) {
        return rbacUserService.edit(id, rbacUserModel);
    }

    @PassToken
    @PutMapping("/modifyState/{id}")
    Result modifyState(@PathVariable Integer id, @RequestBody RbacUserModel rbacUserModel) {
        return rbacUserService.modifyState(id, rbacUserModel.getState());
    }

    @PassToken
    @PutMapping("/modifyPassWord/{id}")
    Result modifyPassWord(@PathVariable Integer id, @RequestBody RbacUserModel rbacUserModel) {
        return rbacUserService.modifyPassWord(id, rbacUserModel.getPassword());
    }
}
