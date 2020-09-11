package com.weigh.verification.dao;

import com.weigh.verification.model.RbacRoleResourceModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface RbacRoleResourceDao {
    /**
     * 添加角色资源
     *
     * @param roleResourceModels 角色资源信息
     * @return 结果
     */
    Integer addAll(List<RbacRoleResourceModel> roleResourceModels);

    /**
     * 物理删除
     *
     * @param roleId 角色id
     * @return 结果
     */
    Integer delete(Integer roleId);

    /**
     * 根据角色ID查所有资源信息
     *
     * @param id 机构id
     * @return 角色资源信息
     */
    List<RbacRoleResourceModel> getInfoByRoleId(Integer id);

    /**
     * 根据角色ID查所有资源信息
     *
     * @param ids 机构id
     * @return 角色资源信息
     */
    List<RbacRoleResourceModel> getInfoByRoleIds(@Param("ids") List<Integer> ids);
}
