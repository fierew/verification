package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TableEntity;
import com.weigh.verification.entity.TokenUserEntity;
import com.weigh.verification.model.VerificationModel;
import com.weigh.verification.service.VerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("verification")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("add")
    Result add(TokenUserEntity tokenUserEntity, VerificationModel verificationModel) {
        return verificationService.add(tokenUserEntity.getUserId(), verificationModel);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PutMapping("edit/{id}")
    Result edit(@PathVariable Integer id, VerificationModel verificationModel) {
        return verificationService.edit(id, verificationModel);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Integer id) {
        return verificationService.delete(id);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("getList")
    Result getList(TableEntity tableEntity) {
        return verificationService.getList(tableEntity.getPage(), tableEntity.getPageSize());
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("getInfo/{id}")
    Result getInfo(@PathVariable Integer id) {
        return verificationService.getInfo(id);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("downloads/{id}")
    Result downloads(@PathVariable Integer id) {
        return new Result();
    }
}
