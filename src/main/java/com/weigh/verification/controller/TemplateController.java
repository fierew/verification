package com.weigh.verification.controller;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.entity.Result;
import com.weigh.verification.entity.TableEntity;
import com.weigh.verification.entity.TokenUserEntity;
import com.weigh.verification.model.FileModel;
import com.weigh.verification.model.TemplateModel;
import com.weigh.verification.service.FileService;
import com.weigh.verification.service.TemplateService;
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
@RequestMapping("template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @Autowired
    private FileService fileService;

    @Operation(security = {@SecurityRequirement(name = "JWT")})
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

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PutMapping("edit/{id}")
    Result edit(TokenUserEntity tokenUserEntity, @PathVariable Integer id, TemplateModel templateModel) {
        // System.out.println(templateModel);
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

    @Operation(security = {@SecurityRequirement(name = "JWT")})
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

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("getInfoById/{id}")
    Result getInfoById(@PathVariable Integer id) {
        TemplateModel templateInfo = templateService.getInfoById(id);
        Result result = new Result();
        result.setData(templateInfo);
        result.setMsg("success");
        result.setCode(200);
        return result;
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("getList")
    Result getList(TableEntity tableEntity, TemplateModel templateModel) {
        PageInfo<TemplateModel> templateList = templateService.getList(tableEntity.getPage(), tableEntity.getPageSize(), templateModel);
        Result result = new Result();
        result.setData(templateList);
        result.setMsg("success");
        result.setCode(200);
        return result;
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("getAll")
    Result getAll() {
        List<TemplateModel> template = templateService.getAll();
        Result result = new Result();
        result.setData(template);
        result.setMsg("success");
        result.setCode(200);
        return result;
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("analysis")
    Result analysis(@RequestParam(value = "filePath", defaultValue = "") String filePath) {
        List<String> res = templateService.analysis(System.getProperty("user.dir") + "/upload/" + filePath);
        Result result = new Result();
        result.setCode(200);
        result.setData(res);
        result.setMsg("success");


        return result;
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @PutMapping("modifyState/{id}")
    Result modifyState(@PathVariable Integer id, TemplateModel templateModel) {
        Integer res = templateService.modifyState(id, templateModel.getState());

        Result result = new Result();
        if (res == null) {
            result.setCode(400);
            result.setMsg("修改模板状态失败");
        } else {
            result.setCode(200);
            result.setData(res);
            result.setMsg("修改模板状态成功");
        }
        return result;
    }

    @Operation(security = {@SecurityRequirement(name = "JWT")})
    @GetMapping("downloads/{id}")
    void downloads(@PathVariable Integer id, HttpServletResponse response) {
        // 根据模板ID查模板信息
        TemplateModel templateInfo = templateService.getInfoById(id);

        if (templateInfo == null) {
            log.error("模板信息不存在");
            return;
        }

        // 根据模板信息查文件路径
        FileModel fileInfo = fileService.getInfo(templateInfo.getFileId());

        if (fileInfo == null) {
            log.error("文件信息不存在");
            return;
        }

        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            String fileName = templateInfo.getName();
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
