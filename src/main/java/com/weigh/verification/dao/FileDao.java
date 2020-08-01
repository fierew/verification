package com.weigh.verification.dao;

import com.weigh.verification.model.FileModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xuyang
 */
@Mapper
public interface FileDao {
    /**
     * 新增文件
     * @param fileModel 文件信息
     * @return 结果
     */
    Integer add(FileModel fileModel);

    /**
     * 根据哈希获取文件信息
     * @param hash 哈希
     * @return 文件信息
     */
    FileModel getInfoByHash(String hash);
}
