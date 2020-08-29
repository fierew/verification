package com.weigh.verification.dao;

import com.weigh.verification.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface UserDao {
    /**
     * 根据用户名密码获取用户信息
     *
     * @param email 邮箱
     * @return 用户信息
     */
    UserModel getInfo(String email);

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    UserModel getInfoById(Integer id);

    /**
     * 获取用户列表
     *
     * @param userModel 条件
     * @return 用户列表
     */
    List<UserModel> getList(UserModel userModel);

    /**
     * 新增用户信息
     *
     * @param userModel 用户信息
     * @return 结果
     */
    Integer add(UserModel userModel);

    /**
     * 修改用户信息
     *
     * @param userModel 用户信息
     * @return 结果
     */
    Integer edit(UserModel userModel);

    /**
     * 删除用户
     *
     * @param id         用户id
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer delete(@Param("id") Integer id, @Param("updateTime") Integer updateTime);

    /**
     * 修改状态
     *
     * @param id         用户id
     * @param state      用户状态
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer modifyState(@Param("id") Integer id, @Param("state") Integer state, @Param("updateTime") Integer updateTime);
}
