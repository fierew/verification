package com.weigh.verification.service;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacDeptModel;

/**
 * @author xuyang
 */
public interface RbacDeptService {
    /**
     * 新增机构
     *
     * @param rbacDeptModel 机构信息
     * @return 结果
     */
    Result add(RbacDeptModel rbacDeptModel);

    /**
     * 编辑机构
     *
     * @param id        id
     * @param rbacDeptModel 机构信息
     * @return 结果
     */
    Result edit(Integer id, RbacDeptModel rbacDeptModel);

    /**
     * 删除机构
     *
     * @param id id
     * @return 结果
     */
    Result delete(Integer id);

    /**
     * 获取机构
     *
     * @return 结果
     */
    Result getAll();
}
