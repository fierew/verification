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
     * @param page          页数
     * @param pageSize      每页多少条数据
     * @param rbacUserModel 条件
     * @return 分页列表
     */
    Result getList(Integer page, Integer pageSize, RbacUserModel rbacUserModel);

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
     * @param id            id
     * @param rbacUserModel 信息
     * @return 结果
     */
    Result edit(Integer id, RbacUserModel rbacUserModel);

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

    /**
     * 修改密码
     *
     * @param id       id
     * @param password 密码
     * @return 结果
     */
    Result modifyPassWord(Integer id, String password);

    /**
     * 登录
     *
     * @param rbacUserModel 用户信息
     * @return 用户信息
     */
    Result login(RbacUserModel rbacUserModel);

    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    Result getInfo(Integer id);
}
