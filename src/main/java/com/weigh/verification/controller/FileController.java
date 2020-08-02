package com.weigh.verification.controller;

import com.weigh.verification.entity.FileEntity;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TableEntity;
import com.weigh.verification.entity.TokenUserEntity;
import com.weigh.verification.model.FileModel;
import com.weigh.verification.service.FileService;
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
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @PostMapping("upload")
    Result upload(TokenUserEntity tokenUserEntity, FileEntity file) {
        fileService.upload(tokenUserEntity.getUserId(), file);

        Result result = new Result();
        result.setCode(200);
        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("check")
    Result check(FileModel fileModel) {
        FileModel fileInfo = fileService.check(fileModel.getHash());

        Result result = new Result();

        result.setCode(200);
        if(fileInfo == null){
            result.setData(1);
        }else{
            result.setData(0);
        }
        result.setMsg("success");

        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @GetMapping("getList")
    Result getList(TableEntity tableEntity) {
        List<FileModel> fileList= fileService.getList(tableEntity.getPage(), tableEntity.getPageSize());
        Result result = new Result();

        result.setCode(200);
        result.setData(fileList);
        result.setMsg("success");
        return result;
    }

    @Operation(security = { @SecurityRequirement(name = "JWT") })
    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Integer id) {
        Integer res = fileService.delete(id);

        Result result = new Result();
        if(res != 1){
            result.setCode(400);
            result.setMsg("删除文件失败");
        }else{
            result.setCode(200);
            result.setMsg("删除文件成功");
        }
        return result;
    }

    @GetMapping("downloads/{id}")
    Result downloads(@PathVariable Integer id) {
        return new Result();
    }
}
