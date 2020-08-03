package com.weigh.verification.service.impl;

import com.power.common.util.DateTimeUtil;
import com.power.common.util.UUIDUtil;
import com.weigh.verification.dao.FileDao;
import com.weigh.verification.entity.FileEntity;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.FileModel;
import com.weigh.verification.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author xuyang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;

    @Override
    public Result upload(Integer userId, FileEntity fileEntity) {
        Result result = new Result();

        MultipartFile file = fileEntity.getFile();
        try {
            if (file.isEmpty()) {
                return null;
            }

            String fileName = file.getOriginalFilename();
            Long size = file.getSize();
            assert fileName != null;
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            log.info("上传的文件名为：" + fileName + " 后缀名为：" + suffixName);

            // 设置文件存储路径
            String filePath = "/upload/";
            String path = System.getProperty("user.dir") + filePath + UUIDUtil.getUuid32() + "." + suffixName;

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

            file.transferTo(dest);

            FileInputStream fileInputStream = new FileInputStream(dest);
            String hex = DigestUtils.sha512Hex(fileInputStream);

            // 判断前端的哈希和文件真实哈希是否匹配
            if (!hex.equals(fileEntity.getHash())) {
                log.info("前端的哈希和文件真实哈希不匹配");
                result.setCode(400);
                result.setMsg("前端的哈希和文件真实哈希不匹配");
                return result;
            }

            // 根据用户哈希获取文件信息
            FileModel fileInfo = fileDao.getInfoByHash(fileEntity.getHash());

            // 如果文件信息存在就不需要插入数据，直接返回文件信息
            if (fileInfo != null) {
                log.info("文件已存在，无需重复写入数据");
                result.setCode(200);
                result.setMsg("文件已存在，无需重复写入数据");
                result.setData(fileInfo);
                return result;
            }

            // 写入文件成功，开始插入数据库
            FileModel fileModel = new FileModel();
            Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);


            fileModel.setUserId(userId);
            fileModel.setName(fileName);
            fileModel.setType(suffixName);
            fileModel.setPath(path);
            fileModel.setSize(size);
            fileModel.setHash(hex);
            fileModel.setCreateTime(time);
            fileModel.setUpdateTime(time);

            Integer res = fileDao.add(fileModel);

            if (res != 1) {
                log.info("写入文件信息失败");
                result.setCode(400);
                result.setMsg("写入文件信息失败");
                return result;
            }

            result.setCode(200);
            result.setMsg("上传成功");
            result.setData(fileModel);
            return result;
        } catch (IllegalStateException | IOException e) {
            log.error(e.getMessage());
            result.setCode(400);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @Override
    public FileModel check(String hash) {
        return fileDao.getInfoByHash(hash);
    }

    @Override
    public List<FileModel> getList(Integer page, Integer pageSize) {
        return fileDao.getList(page, pageSize);
    }

    @Override
    public Integer delete(Integer id) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        return fileDao.delete(id, time);
    }

    @Override
    public FileModel getInfo(Integer id) {
        return null;
    }
}
