package com.weigh.verification.service.rbac;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.ResourceModel;

/**
 * @author xuyang
 */
public interface ResourceService {
    /**
     * 获取树
     *
     * @return 树
     */
    Result getList() throws IllegalAccessException;

    /**
     * 添加
     *
     * @param resourceModel 信息
     * @return 结果
     */
    Result add(ResourceModel resourceModel);

    /**
     * 编辑
     *
     * @param id            ID
     * @param resourceModel 信息
     * @return 结果
     */
    Result edit(Integer id, ResourceModel resourceModel);

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
