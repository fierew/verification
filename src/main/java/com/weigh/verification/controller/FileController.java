package com.weigh.verification.controller;

import com.weigh.verification.entity.FileEntity;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TokenUserEntity;
import com.weigh.verification.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    Result upload(TokenUserEntity tokenUserEntity, FileEntity file) {
        fileService.upload(tokenUserEntity.getUserId(), file);

        Result result = new Result();
        result.setCode(200);
        return result;
    }

    @GetMapping("check")
    Result check() {
        return new Result();
    }

    @GetMapping("getList")
    Result getList() {
        return new Result();
    }

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Integer id) {
        return new Result();
    }

    @GetMapping("downloads/{id}")
    Result downloads(@PathVariable Integer id) {
        return new Result();
    }
}
