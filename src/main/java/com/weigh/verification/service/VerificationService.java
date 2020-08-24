package com.weigh.verification.service;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.VerificationLogModel;
import com.weigh.verification.model.VerificationModel;

import java.util.List;

/**
 * @author xuyang
 */
public interface VerificationService {
    /**
     * 新增鉴定信息
     *
     * @param userId            用户id
     * @param verificationModel 鉴定信息
     * @return 结果
     */
    Result add(Integer userId, VerificationModel verificationModel);

    /**
     * 编辑鉴定信息
     *
     * @param id                鉴定id
     * @param verificationModel 鉴定信息
     * @return 结果
     */
    Result edit(Integer id, VerificationModel verificationModel);

    /**
     * 删除鉴定信息
     *
     * @param id 鉴定id
     * @return 结果
     */
    Result delete(Integer id);

    /**
     * 获取鉴定列表
     *
     * @param page              页数
     * @param pageSize          每页条数
     * @param verificationModel 鉴定信息
     * @return 鉴定列表
     */
    Result getList(Integer page, Integer pageSize, VerificationModel verificationModel);

    /**
     * 获取鉴定信息
     *
     * @param id 鉴定id
     * @return 鉴定信息
     */
    Result getInfo(Integer id);

    /**
     * 获取鉴定日志信息
     *
     * @param page     页数
     * @param pageSize 每页条数
     * @param id       鉴定信息id
     * @return 鉴定日志列表
     */
    Result getLogList(Integer page, Integer pageSize, Integer id);

    /**
     * 新增鉴定日志信息
     *
     * @param userId               用户id
     * @param verificationLogModel 鉴定日志信息
     * @return 结果
     */
    Result addLog(Integer userId, VerificationLogModel verificationLogModel);
}
