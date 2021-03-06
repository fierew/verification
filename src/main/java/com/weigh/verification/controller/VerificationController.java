package com.weigh.verification.controller;

import com.deepoove.poi.XWPFTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weigh.verification.entity.*;
import com.weigh.verification.model.FileModel;
import com.weigh.verification.model.TemplateModel;
import com.weigh.verification.model.VerificationModel;
import com.weigh.verification.service.FileService;
import com.weigh.verification.service.TemplateService;
import com.weigh.verification.service.VerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuyang
 */
@Slf4j
@RestController
@RequestMapping("/verification")
public class VerificationController {
    @Autowired
    private VerificationService verificationService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private FileService fileService;

    @PostMapping("/add")
    Result add(TokenUserEntity tokenUserEntity, @RequestBody VerificationModel verificationModel) {
        return verificationService.add(tokenUserEntity.getUserId(), verificationModel);
    }

    @PutMapping("/edit/{id}")
    Result edit(@PathVariable Integer id, @RequestBody VerificationModel verificationModel) {
        return verificationService.edit(id, verificationModel);
    }

    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable Integer id) {
        return verificationService.delete(id);
    }

    @GetMapping("/getList")
    Result getList(TableEntity tableEntity, VerificationModel verificationModel) {
        return verificationService.getList(tableEntity.getPage(), tableEntity.getPageSize(), verificationModel);
    }

    @GetMapping("/getInfo/{id}")
    Result getInfo(@PathVariable Integer id) {
        return verificationService.getInfo(id);
    }

    @PostMapping("/addLog")
    Result addLog(TokenUserEntity tokenUserEntity, @RequestBody VerificationLogDataEntity verificationLogDataEntity) {
        return verificationService.addLog(tokenUserEntity.getUserId(), verificationLogDataEntity);
    }

    @GetMapping("/getLogList/{id}")
    Result getLogList(TableEntity tableEntity, @PathVariable Integer id) {
        return verificationService.getLogList(tableEntity.getPage(), tableEntity.getPageSize(), id);
    }

    @GetMapping("/downloads/{id}")
    void downloads(@PathVariable Integer id, HttpServletResponse response) {
        Result result = verificationService.getInfo(id);
        if (result.getCode() != 200) {
            log.error(result.getMsg());
            return;
        }

        VerificationModel verificationInfo = (VerificationModel) result.getData();

        try {
            TypeReference<List<VerificationParamEntity>> verificationParamEntity = new TypeReference<List<VerificationParamEntity>>() {
            };

            ObjectMapper mapper = new ObjectMapper();
            List<VerificationParamEntity> verificationParamResults = mapper.readValue(verificationInfo.getParams(), verificationParamEntity);

            Map<String, String> data = new HashMap<>();
            for (VerificationParamEntity verificationParam : verificationParamResults) {
                data.put(verificationParam.getKey(), verificationParam.getValue());
            }

            // 根据模板ID查模板信息
            TemplateModel templateInfo = templateService.getInfoById(verificationInfo.getTemplateId());

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

            XWPFTemplate template = XWPFTemplate.compile("upload/" + fileInfo.getPath()).render(data);


            String verificationName = verificationInfo.getName();
            String fileType = fileInfo.getType();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=\"" + URLEncoder.encode(verificationName, "UTF-8") + fileType + "\"");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            // 网络流
            ServletOutputStream out = response.getOutputStream();

            // 文件流
            // FileOutputStream out = new FileOutputStream("out_test.docx");
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
