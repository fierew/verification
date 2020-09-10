package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacDeptModel;
import com.weigh.verification.service.RbacDeptService;
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

    @PostMapping("/add")
    Result add(@RequestBody RbacDeptModel rbacDeptModel) {
        return rbacDeptService.add(rbacDeptModel);
    }

    @PutMapping("/edit/{id}")
    Result edit(@PathVariable Integer id, @RequestBody RbacDeptModel rbacDeptModel) {
        return rbacDeptService.edit(id, rbacDeptModel);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Integer id) {
        return rbacDeptService.delete(id);
    }

    @GetMapping("/getAll")
    Result getAll() {
        return rbacDeptService.getAll();
    }
}
