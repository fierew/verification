package com.weigh.verification.dao;

import com.weigh.verification.entity.VerificationLogEntity;
import com.weigh.verification.model.VerificationLogModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface VerificationLogDao {
    /**
     * 获取鉴定日志信息列表
     *
     * @param id 鉴定日志ID
     * @return 鉴定日志信息列表
     */
    List<VerificationLogEntity> getLogList(Integer id);

    /**
     * 新增鉴定日志信息
     *
     * @param verificationLogModels 鉴定日志信息
     * @return 结果
     */
    Integer addLog(@Param("verificationLogModels") List<VerificationLogModel> verificationLogModels);
}
