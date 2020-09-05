package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacDeptModel;
import com.weigh.verification.service.RbacDeptService;
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
@RequestMapping("rbac/dept")
public class RbacDeptController {
    @Autowired
    private RbacDeptService rbacDeptService;

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/add")
    Result add(@RequestBody RbacDeptModel rbacDeptModel) {
        return rbacDeptService.add(rbacDeptModel);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/edit/{id}")
    Result edit(@PathVariable Integer id, @RequestBody RbacDeptModel rbacDeptModel) {
        return rbacDeptService.edit(id, rbacDeptModel);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/delete/{id}")
    Result delete(@PathVariable Integer id) {
        return rbacDeptService.delete(id);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/getAll")
    Result getAll() {
        return rbacDeptService.getAll();
    }
}
