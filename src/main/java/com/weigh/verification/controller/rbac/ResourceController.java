package com.weigh.verification.controller.rbac;

import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TableEntity;
import com.weigh.verification.model.rbac.ResourceModel;
import com.weigh.verification.service.rbac.ResourceService;
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
@RequestMapping("rbac/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/add")
    Result add(@RequestBody ResourceModel resourceModel) {
        return resourceService.add(resourceModel);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PutMapping("/edit/{id}")
    Result edit(@PathVariable Integer id, @RequestBody ResourceModel resourceModel) {
        return resourceService.edit(id, resourceModel);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Integer id) {
        return resourceService.delete(id);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PutMapping("/modifyState/{id}")
    Result modifyState(@PathVariable Integer id, @RequestBody ResourceModel resourceModel) {
        return resourceService.modifyState(id, resourceModel.getState());
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("/getList")
    Result getList() {
        return resourceService.getAll();
    }
}
