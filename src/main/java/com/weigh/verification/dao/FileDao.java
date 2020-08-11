package com.weigh.verification.dao;

import com.weigh.verification.model.FileModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface FileDao {
    /**
     * 新增文件
     *
     * @param fileModel 文件信息
     * @return 结果
     */
    Integer add(FileModel fileModel);

    /**
     * 根据哈希获取文件信息
     *
     * @param hash 哈希
     * @return 文件信息
     */
    FileModel getInfoByHash(String hash);

    /**
     * 根据哈id取文件信息
     *
     * @param id 文件id
     * @return 文件信心
     */
    FileModel getInfo(Integer id);

    /**
     * 获取文件列表
     *
     * @return 文件列表
     */
    List<FileModel> getList();

    /**
     * 删除文件
     *
     * @param id         文件ID
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer delete(@Param("id") Integer id, @Param("updateTime") Integer updateTime);

    /**
     * 修改路径
     *
     * @param id   文件ID
     * @param path 路径
     * @return 结果
     */
    Integer editPath(@Param("id") Integer id, @Param("path") String path);
}
