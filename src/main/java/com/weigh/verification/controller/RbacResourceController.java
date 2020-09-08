package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacResourceModel;
import com.weigh.verification.service.RbacResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("rbac/resource")
public class RbacResourceController {
    @Autowired
    private RbacResourceService rbacResourceService;

    @PostMapping("/add")
    Result add(@RequestBody RbacResourceModel rbacResourceModel) {
        return rbacResourceService.add(rbacResourceModel);
    }

    @PutMapping("/edit/{id}")
    Result edit(@PathVariable Integer id, @RequestBody RbacResourceModel rbacResourceModel) {
        return rbacResourceService.edit(id, rbacResourceModel);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Integer id) {
        return rbacResourceService.delete(id);
    }

    @PutMapping("/modifyState/{id}")
    Result modifyState(@PathVariable Integer id, @RequestBody RbacResourceModel rbacResourceModel) {
        return rbacResourceService.modifyState(id, rbacResourceModel.getState());
    }

    @GetMapping("/getList")
    Result getList() {
        return rbacResourceService.getAll();
    }
}
