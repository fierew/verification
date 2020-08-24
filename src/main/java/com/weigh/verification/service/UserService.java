package com.weigh.verification.service;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.model.UserModel;

/**
 * @author xuyang
 */
public interface UserService {
    /**
     * 登录
     *
     * @param userModel 用户对象
     * @return UserModel
     */
    Object login(UserModel userModel);

    /**
     * 获取用户信息
     * @param id 用户id
     * @return UserModel
     */
    UserModel getInfo(Integer id);

    /**
     * 获取用户列表
     *
     * @param page     页数
     * @param pageSize 每页条数
     * @return 用户列表
     */
    PageInfo<UserModel> getList(Integer page, Integer pageSize);

    /**
     * 新增用户
     *
     * @param userModel 用户对象
     * @return Integer
     */
    Integer add(UserModel userModel);

    /**
     * 编辑用户
     *
     * @param id        用户id
     * @param userModel 用户对象
     * @return 结果
     */
    Integer edit(Integer id, UserModel userModel);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    Integer delete(Integer id);

    /**
     * 修改用户状态
     *
     * @param id 用户id
     * @return 结果
     */
    Integer modifyState(Integer id);
}
