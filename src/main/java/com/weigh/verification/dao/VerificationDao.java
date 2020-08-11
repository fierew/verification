package com.weigh.verification.dao;

import com.weigh.verification.model.VerificationModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface VerificationDao {
    /**
     * 根据鉴定id获取鉴定信息
     *
     * @param id 鉴定id
     * @return 鉴定信息
     */
    VerificationModel getInfo(Integer id);

    /**
     * 获取鉴定列表
     *
     * @param limit  偏移量
     * @param offset 条数
     * @return 用户列表
     */
    List<VerificationModel> getList(@Param("limit") Integer limit, @Param("offset") Integer offset);

    /**
     * 修改鉴定信息
     *
     * @param verificationModel 鉴定信息
     * @return 结果
     */
    Integer edit(VerificationModel verificationModel);

    /**
     * 删除鉴定
     *
     * @param id         鉴定id
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer delete(@Param("id") Integer id, @Param("updateTime") Integer updateTime);

    /**
     * 新增鉴定
     *
     * @param verificationModel 鉴定信息
     * @return 结果
     */
    Integer add(VerificationModel verificationModel);
}
