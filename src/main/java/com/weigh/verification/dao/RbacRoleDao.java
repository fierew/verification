package com.weigh.verification.dao;

import com.weigh.verification.model.RbacRoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface RbacRoleDao {
    /**
     * 新增角色
     *
     * @param roleModel 角色信息
     * @return 结果
     */
    Integer add(RbacRoleModel roleModel);

    /**
     * 编辑角色
     *
     * @param roleModel 角色信息
     * @return 结果
     */
    Integer edit(RbacRoleModel roleModel);

    /**
     * 删除角色
     *
     * @param id         角色id
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer delete(@Param("id") Integer id, @Param("updateTime") Integer updateTime);

    /**
     * 查询角色列表
     *
     * @param roleModel 条件
     * @return 角色列表
     */
    List<RbacRoleModel> getList(RbacRoleModel roleModel);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<RbacRoleModel> getAll();

    /**
     * 根据id查角色信息
     *
     * @param id 角色id
     * @return 角色信息
     */
    RbacRoleModel getInfoById(@Param("id") Integer id);
}
