package com.weigh.verification.controller;

import com.github.pagehelper.PageInfo;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PostMapping("/upload")
    Result upload(TokenUserEntity tokenUserEntity, FileEntity file) {
        return fileService.upload(tokenUserEntity.getUserId(), file);
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("/check")
    Result check(FileModel fileModel) {
        FileModel fileInfo = fileService.check(fileModel.getHash());

        Result result = new Result();

        result.setCode(200);
        if (fileInfo == null) {
            result.setData(1);
        } else {
            result.setData(0);
        }
        result.setMsg("success");

        return result;
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("/getList")
    Result getList(TableEntity tableEntity) {
        PageInfo<FileModel> fileList = fileService.getList(tableEntity.getPage(), tableEntity.getPageSize());
        Result result = new Result();

        result.setCode(200);
        result.setData(fileList);
        result.setMsg("success");
        return result;
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Integer id) {
        Integer res = fileService.delete(id);

        Result result = new Result();
        if (res != 1) {
            result.setCode(400);
            result.setMsg("删除文件失败");
        } else {
            result.setCode(200);
            result.setMsg("删除文件成功");
        }
        return result;
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("/downloads/{id}")
    void downloads(@PathVariable Integer id, HttpServletResponse response) {
        // 根据文件ID查文件路径
        FileModel fileInfo = fileService.getInfo(id);

        if (fileInfo == null) {
            log.error("文件信息不存在");
            return;
        }

        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            String fileName = fileInfo.getName();
            String fileType = fileInfo.getType();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + fileType + "\"");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            // 网络流
            outputStream = response.getOutputStream();


            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(new File("upload/" + fileInfo.getPath())));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch (IOException e) {
            log.error("下载异常！");
            log.error(e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
