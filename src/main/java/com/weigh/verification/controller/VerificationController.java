package com.weigh.verification.controller;

import com.weigh.verification.entity.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("verification")
public class VerificationController {
    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @PostMapping("add")
    Result add() {
        return new Result();
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @PutMapping("edit/{id}")
    Result edit(@PathVariable Integer id) {
        return new Result();
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Integer id) {
        return new Result();
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("getList")
    Result getList() {
        return new Result();
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("getInfo/{id}")
    Result getInfo(@PathVariable Integer id) {
        return new Result();
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("downloads/{id}")
    Result downloads(@PathVariable Integer id) {
        return new Result();
    }
}
