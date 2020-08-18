package com.weigh.verification.utils;

import com.power.common.util.DateTimeUtil;
import com.power.common.util.UUIDUtil;
import com.weigh.verification.entity.FileEntity;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.FileModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xuyang
 */
@Slf4j
@Component
public class FileUtil {
    public Result upload(FileEntity fileEntity) {
        Result result = new Result();

        MultipartFile file = fileEntity.getFile();

        if (file.isEmpty()) {
            log.info("文件对象不存在");
            result.setCode(400);
            result.setMsg("文件对象不存在");
            return result;
        }

        String fileName = file.getOriginalFilename();
        Long size = file.getSize();
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        log.info("上传的文件名为：" + fileName + " 后缀名为：" + suffixName);

        // 设置文件存储路径
        Date date = new Date();

        SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthSdf = new SimpleDateFormat("MM");
        SimpleDateFormat daySdf = new SimpleDateFormat("dd");


        String year = yearSdf.format(date);
        String month = monthSdf.format(date);
        String day = daySdf.format(date);

        String filePath = "/upload/";
        String fileUuidName = year + "/" + month + "/" + day + "/" + UUIDUtil.getUuid32() + suffixName;
        String path = System.getProperty("user.dir") + filePath + fileUuidName;

        File dest = new File(path);

        // 检查是否存在路径目录
        if (!dest.getParentFile().exists()) {
            log.info("文件目录：" + path + " 不存在，准备创建目录");
            // 新建目录
            boolean mkdirRes = dest.getParentFile().mkdirs();
            if (!mkdirRes) {
                log.info("文件目录：" + path + " 创建失败");
                result.setCode(400);
                result.setMsg("文件目录创建失败");
                return result;
            }
        }

        try {
            file.transferTo(dest);

            FileInputStream fileInputStream = new FileInputStream(dest);
            String hex = DigestUtils.sha256Hex(fileInputStream);
            // System.out.println(hex);
            // 判断前端的哈希和文件真实哈希是否匹配
            if (!hex.equals(fileEntity.getHash())) {
                if (!dest.delete()) {
                    log.error("文件删除失败：" + path);
                }

                log.info("前端的哈希和文件真实哈希不匹配");
                result.setCode(400);
                result.setMsg("前端的哈希和文件真实哈希不匹配");
                return result;
            }

            // 写入文件成功，开始插入数据库
            FileModel fileModel = new FileModel();
            Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);


            fileModel.setName(fileName);
            fileModel.setType(suffixName);
            fileModel.setPath(fileUuidName);
            fileModel.setSize(size);
            fileModel.setHash(hex);
            fileModel.setCreateTime(time);
            fileModel.setUpdateTime(time);

            result.setCode(200);
            result.setMsg("success");
            result.setData(fileModel);
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            result.setCode(400);
            result.setMsg(e.getMessage());
            return result;
        }
    }
}
