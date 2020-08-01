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
     * 获取文件列表
     *
     * @param page     页数
     * @param pageSize 每页条数
     * @return 文件列表
     */
    List<FileModel> getList(@Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * 删除文件
     *
     * @param id         文件ID
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer delete(@Param("id") Integer id, @Param("updateTime") Integer updateTime);
}
