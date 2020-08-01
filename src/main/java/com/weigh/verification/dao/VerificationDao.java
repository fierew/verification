package com.weigh.verification.dao;

import com.weigh.verification.model.VerificationModel;
import org.apache.ibatis.annotations.Mapper;

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
     * @param page     页数
     * @param pageSize 每页条数
     * @return 用户列表
     */
    List<VerificationModel> getList(Integer page, Integer pageSize);

    /**
     * 修改鉴定信息
     *
     * @param id                鉴定id
     * @param verificationModel 鉴定信息
     * @return 结果
     */
    Integer edit(Integer id, VerificationModel verificationModel);

    /**
     * 删除鉴定
     *
     * @param id 鉴定id
     * @return 结果
     */
    Integer delete(Integer id);
}
