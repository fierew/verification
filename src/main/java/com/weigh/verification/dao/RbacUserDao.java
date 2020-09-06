package com.weigh.verification.dao;

import com.weigh.verification.model.RbacUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface RbacUserDao {
    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    RbacUserModel getInfoById(Integer id);

    /**
     * 根据邮箱获取用户
     *
     * @param email 邮箱
     * @return 用户信息
     */
    RbacUserModel getInfoByEmail(String email);

    /**
     * 根据手机获取用户信息
     *
     * @param mobile 手机
     * @return 用户信息
     */
    RbacUserModel getInfoByMobile(String mobile);

    /**
     * 获取用户列表
     *
     * @param rbacUserModel 条件
     * @return 用户列表
     */
    List<RbacUserModel> getList(RbacUserModel rbacUserModel);

    /**
     * 新增用户
     *
     * @param rbacUserModel 用户信息
     * @return 结果
     */
    Integer add(RbacUserModel rbacUserModel);

    /**
     * 编辑用户
     *
     * @param rbacUserModel 用户信息
     * @return 结果
     */
    Integer edit(RbacUserModel rbacUserModel);

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
    Integer modifyState(@Param("id") Integer id, @Param("state") Byte state, @Param("updateTime") Integer updateTime);

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
