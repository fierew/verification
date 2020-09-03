package com.weigh.verification.controller.rbac;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.DeptModel;
import com.weigh.verification.service.rbac.DeptService;
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
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/add")
    Result add(@RequestBody DeptModel deptModel) {
        return deptService.add(deptModel);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/edit/{id}")
    Result edit(@PathVariable Integer id, @RequestBody DeptModel deptModel) {
        return deptService.edit(id, deptModel);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/delete/{id}")
    Result delete(@PathVariable Integer id) {
        return deptService.delete(id);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/getAll")
    Result getAll() {
        return deptService.getAll();
    }
}
