package com.weigh.verification.service;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacUserModel;

/**
 * @author xuyang
 */
public interface RbacUserService {
    /**
     * 获取分页数据
     *
     * @param page      页数
     * @param pageSize  每页多少条数据
     * @param rbacUserModel 条件
     * @return 分页列表
     */
    PageInfo<RbacUserModel> getList(Integer page, Integer pageSize, RbacUserModel rbacUserModel);

    /**
     * 添加
     *
     * @param rbacUserModel 信息
     * @return 结果
     */
    Result add(RbacUserModel rbacUserModel);

    /**
     * 编辑
     *
     * @param rbacUserModel 信息
     * @return 结果
     */
    Result edit(RbacUserModel rbacUserModel);

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
