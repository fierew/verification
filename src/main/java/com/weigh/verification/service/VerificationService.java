package com.weigh.verification.service;

import com.weigh.verification.model.VerificationModel;

import java.util.List;

/**
 * @author xuyang
 */
public interface VerificationService {
    /**
     * 新增鉴定信息
     *
     * @param verificationModel 鉴定信息
     * @return 结果
     */
    Integer add(VerificationModel verificationModel);

    /**
     * 编辑鉴定信息
     *
     * @param id                鉴定id
     * @param verificationModel 鉴定信息
     * @return 结果
     */
    Integer edit(Integer id, VerificationModel verificationModel);

    /**
     * 删除鉴定信息
     *
     * @param id 鉴定id
     * @return 结果
     */
    Integer delete(Integer id);

    /**
     * 获取鉴定列表
     *
     * @param page     页数
     * @param pageSize 每页条数
     * @return 鉴定列表
     */
    List<VerificationModel> getList(Integer page, Integer pageSize);

    /**
     * 获取鉴定信息
     *
     * @param id 鉴定id
     * @return 鉴定信息
     */
    VerificationModel getInfo(Integer id);
}
