package com.weigh.verification.service;

import com.weigh.verification.entity.FileEntity;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.FileModel;

import java.util.List;

/**
 * @author xuyang
 */
public interface FileService {
    /**
     * 上传文件
     * @param userId 用户id
     * @param fileEntity 文件对象
     * @return 文件信息
     */
    Result upload(Integer userId, FileEntity fileEntity);

    /**
     * 判断文件是否存在
     *
     * @param hash has256
     * @return 文件信息
     */
    FileModel check(String hash);

    /**
     * 获取文件列表
     *
     * @param page     页数
     * @param pageSize 每页的条数
     * @return 文件信息数组
     */
    List<FileModel> getList(Integer page, Integer pageSize);

    /**
     * 获取文件信息
     *
     * @param id 文件id
     * @return 文件信息
     */
    FileModel getInfo(Integer id);

    /**
     * 删除文件
     *
     * @param id 文件id
     * @return 结果
     */
    Integer delete(Integer id);
}
