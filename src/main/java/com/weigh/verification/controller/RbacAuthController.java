package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TokenUserEntity;
import com.weigh.verification.service.RbacAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("rbac/auth")
public class RbacAuthController {
    @Autowired
    private RbacAuthService rbacAuthService;

    @GetMapping("/getAll")
    Result getAuth(TokenUserEntity tokenUserEntity) {
        return rbacAuthService.getAuth(tokenUserEntity.getUserId());
    }

    @GetMapping("/getMenu")
    Result getMenu(TokenUserEntity tokenUserEntity) {
        return rbacAuthService.getMenu(tokenUserEntity.getUserId());
    }
}
