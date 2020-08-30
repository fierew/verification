package com.weigh.verification.service.rbac;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.ResourceModel;
import com.weigh.verification.model.rbac.UserModel;

import java.util.List;

/**
 * @author xuyang
 */
public interface ResourceService {
    /**
     * 获取树
     *
     * @return 树
     */
    List<ResourceModel> getTree();

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
     * @param resourceModel 信息
     * @return 结果
     */
    Result edit(ResourceModel resourceModel);

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
     * @param id id
     * @return 结果
     */
    Result modifyState(Integer id);
}
