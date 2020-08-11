package com.weigh.verification.service.impl;

import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.FileDao;
import com.weigh.verification.entity.FileEntity;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.FileModel;
import com.weigh.verification.service.FileService;
import com.weigh.verification.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * @author xuyang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private FileDao fileDao;

    @Override
    public Result upload(Integer userId, FileEntity fileEntity) {
        Result result = new Result();

        Result fileResult = fileUtil.upload(fileEntity);

        if (fileResult.getCode() != 200) {
            return fileResult;
        }

        // 根据用户哈希获取文件信息
        FileModel fileInfo = fileDao.getInfoByHash(fileEntity.getHash());

        // 如果文件信息存在就不需要插入数据，直接返回文件信息
        if (fileInfo != null) {
            // 判断文件是否存在
            String modelPath = System.getProperty("user.dir") + "/upload/" + fileInfo.getPath();
            File modelFile = new File(modelPath);

            FileModel fileData = (FileModel) fileResult.getData();

            if (!modelFile.isFile() || !modelFile.exists()) {
                fileDao.editPath(fileInfo.getId(), fileData.getPath());
                fileInfo.setPath(fileData.getPath());
            } else {
                String path = System.getProperty("user.dir") + "/upload/" + fileData.getPath();
                File file = new File(path);
                if (file.isFile() && file.exists()) {
                    if (!file.delete()) {
                        log.error("文件删除失败：" + path);
                    } else {
                        log.info("文件删除成功：" + path);
                    }
                }
            }

            log.info("文件已存在，无需重复上传数据");
            result.setCode(200);
            result.setMsg("文件已存在，无需重复上传数据");
            result.setData(fileInfo);
            return result;
        }

        FileModel fileModel = (FileModel) fileResult.getData();

        fileModel.setUserId(userId);

        Integer res = fileDao.add(fileModel);

        if (res != 1) {
            log.info("写入文件信息失败");
            result.setCode(400);
            result.setMsg("写入文件信息失败");
            return result;
        }

        FileModel getFileInfo = fileDao.getInfoByHash(fileModel.getHash());
        fileModel.setId(getFileInfo.getId());

        result.setCode(200);
        result.setMsg("上传成功");
        result.setData(fileModel);
        return result;
    }

    @Override
    public FileModel check(String hash) {
        return fileDao.getInfoByHash(hash);
    }

    @Override
    public List<FileModel> getList(Integer page, Integer pageSize) {
        Integer limit = (page - 1) * pageSize;
        return fileDao.getList(limit, pageSize);
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
