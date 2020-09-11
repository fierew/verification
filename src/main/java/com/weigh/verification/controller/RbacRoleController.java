package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TableEntity;
import com.weigh.verification.model.RbacRoleModel;
import com.weigh.verification.service.RbacRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("rbac/role")
public class RbacRoleController {
    @Autowired
    RbacRoleService rbacRoleService;

    @PostMapping("/add")
    Result add(@RequestBody RbacRoleModel rbacRoleModel) {
        return rbacRoleService.add(rbacRoleModel);
    }

    @PutMapping("/edit/{id}")
    Result edit(@PathVariable Integer id, @RequestBody RbacRoleModel rbacRoleModel) {
        return rbacRoleService.edit(id, rbacRoleModel);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Integer id) {
        return rbacRoleService.delete(id);
    }

    @GetMapping("/getList")
    Result getList(TableEntity tableEntity, RbacRoleModel rbacRoleModel) {
        return rbacRoleService.getList(tableEntity.getPage(), tableEntity.getPageSize(), rbacRoleModel);
    }

    @GetMapping("/getAll")
    Result getAll() {
        return rbacRoleService.getAll();
    }
}
