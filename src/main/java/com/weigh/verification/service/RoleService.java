package com.weigh.verification.service;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RoleModel;

/**
 * @author xuyang
 */
public interface RoleService {
    /**
     * 获取分页数据
     *
     * @param page      页数
     * @param pageSize  每页多少条数据
     * @param roleModel 条件
     * @return 分页列表
     */
    PageInfo<RoleModel> getList(Integer page, Integer pageSize, RoleModel roleModel);

    /**
     * 添加
     *
     * @param roleModel 信息
     * @return 结果
     */
    Result add(RoleModel roleModel);

    /**
     * 编辑
     *
     * @param id id
     * @param roleModel 信息
     * @return 结果
     */
    Result edit(Integer id, RoleModel roleModel);

    /**
     * 删除
     *
     * @param id id
     * @return 结果
     */
    Result delete(Integer id);
}
