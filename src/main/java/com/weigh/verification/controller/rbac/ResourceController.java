package com.weigh.verification.controller.rbac;

import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TokenUserEntity;
import com.weigh.verification.model.rbac.ResourceModel;
import com.weigh.verification.service.rbac.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    Result add(ResourceModel resourceModel){
        return resourceService.add(resourceModel);
    }
}
