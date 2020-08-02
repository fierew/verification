package com.weigh.verification.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weigh.verification.annotation.PassToken;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TableEntity;
import com.weigh.verification.entity.TemplateParamEntity;
import com.weigh.verification.entity.TokenUserEntity;
import com.weigh.verification.model.TemplateModel;
import com.weigh.verification.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @PostMapping("add")
    Result add(TokenUserEntity tokenUserEntity, TemplateModel templateModel) {
        Integer res = templateService.add(tokenUserEntity.getUserId(), templateModel);

        Result result = new Result();
        if (res == null) {
            result.setCode(400);
            result.setMsg("新增模板失败");
        } else {
            result.setCode(200);
            result.setData(res);
            result.setMsg("新增模板成功");
        }
        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @PutMapping("edit/{id}")
    Result edit(TokenUserEntity tokenUserEntity, @PathVariable Integer id, TemplateModel templateModel) {
        Integer res = templateService.edit(id, tokenUserEntity.getUserId(), templateModel);

        Result result = new Result();
        if (res == null) {
            result.setCode(400);
            result.setMsg("编辑模板失败");
        } else {
            result.setCode(200);
            result.setData(res);
            result.setMsg("编辑模板成功");
        }
        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Integer id) {
        Integer res = templateService.delete(id);
        Result result = new Result();
        if (res != 1) {
            result.setCode(400);
            result.setMsg("删除失败");
        } else {
            result.setCode(200);
            result.setMsg("删除成功");
        }

        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("getList")
    Result getList(TableEntity tableEntity) {
        List<TemplateModel> templateList = templateService.getList(tableEntity.getPage(), tableEntity.getPageSize());
        Result result = new Result();
        result.setData(templateList);
        result.setMsg("success");
        result.setCode(200);
        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("getAll")
    Result getAll() {
        List<TemplateModel> template = templateService.getAll();
        Result result = new Result();
        result.setData(template);
        result.setMsg("success");
        result.setCode(200);
        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("analysis")
    Result analysis(@RequestParam(value = "filePath", required = true, defaultValue = "") String filePath) {
        List<String> res = templateService.analysis(filePath);
        Result result = new Result();
        result.setCode(200);
        result.setData(res);
        result.setMsg("success");


        return result;
    }
}
