package com.weigh.verification.dao;

import com.weigh.verification.model.User1Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface User1Dao {
    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    User1Model getInfoById(Integer id);

    /**
     * 根据邮箱获取用户
     *
     * @param email 邮箱
     * @return 用户信息
     */
    User1Model getInfoByEmail(String email);

    /**
     * 根据手机获取用户信息
     *
     * @param mobile 手机
     * @return 用户信息
     */
    User1Model getInfoByMobile(String mobile);

    /**
     * 获取用户列表
     *
     * @param userModel 条件
     * @return 用户列表
     */
    List<User1Model> getList(User1Model userModel);

    /**
     * 新增用户
     *
     * @param userModel 用户信息
     * @return 结果
     */
    Integer add(User1Model userModel);

    /**
     * 编辑用户
     *
     * @param userModel 用户信息
     * @return 结果
     */
    Integer edit(User1Model userModel);

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
     * @param state      状态
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer modifyState(@Param("id") Integer id, @Param("state") Integer state, @Param("updateTime") Integer updateTime);

    /**
     * 修改密码
     *
     * @param id         用户id
     * @param password   密码
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer modifyPassword(@Param("id") Integer id, @Param("password") String password, @Param("updateTime") Integer updateTime);

    /**
     * 登录次数加1
     *
     * @param id         用户id
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer incLoginNum(@Param("id") Integer id, @Param("updateTime") Integer updateTime);
}
