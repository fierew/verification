package com.weigh.verification.service;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacResourceModel;

/**
 * @author xuyang
 */
public interface RbacResourceService {
    /**
     * 获取树
     *
     * @return 树
     */
    Result getAll();

    /**
     * 添加
     *
     * @param rbacResourceModel 信息
     * @return 结果
     */
    Result add(RbacResourceModel rbacResourceModel);

    /**
     * 编辑
     *
     * @param id            ID
     * @param rbacResourceModel 信息
     * @return 结果
     */
    Result edit(Integer id, RbacResourceModel rbacResourceModel);

    /**
     * 删除
     *
     * @param id id
     * @return 结果
     */
    Result delete(Integer id);

    /**
     * 修改状态
     *
     * @param id    id
     * @param state 状态
     * @return 结果
     */
    Result modifyState(Integer id, Byte state);
}
