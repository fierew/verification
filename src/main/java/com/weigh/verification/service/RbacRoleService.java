package com.weigh.verification.service;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacRoleModel;

/**
 * @author xuyang
 */
public interface RbacRoleService {
    /**
     * 获取分页数据
     *
     * @param page      页数
     * @param pageSize  每页多少条数据
     * @param rbacRoleModel 条件
     * @return 分页列表
     */
    Result getList(Integer page, Integer pageSize, RbacRoleModel rbacRoleModel);

    /**
     * 获取所有数据
     *
     * @return 分页列表
     */
    Result getAll();

    /**
     * 添加
     *
     * @param rbacRoleModel 信息
     * @return 结果
     */
    Result add(RbacRoleModel rbacRoleModel);

    /**
     * 编辑
     *
     * @param id id
     * @param rbacRoleModel 信息
     * @return 结果
     */
    Result edit(Integer id, RbacRoleModel rbacRoleModel);

    /**
     * 删除
     *
     * @param id id
     * @return 结果
     */
    Result delete(Integer id);
}
