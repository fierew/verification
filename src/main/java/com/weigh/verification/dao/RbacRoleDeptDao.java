package com.weigh.verification.dao;

import com.weigh.verification.model.RbacRoleDeptModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface RbacRoleDeptDao {
    /**
     * 批量插入
     *
     * @param roleDeptModels 角色机构信息
     * @return 结果
     */
    Integer addAll(List<RbacRoleDeptModel> roleDeptModels);

    /**
     * 物理删除
     *
     * @param roleId 角色id
     * @return 结果
     */
    Integer delete(Integer roleId);

    /**
     * 根据角色ID查所有机构信息
     *
     * @param id 机构id
     * @return 角色机构信息
     */
    List<RbacRoleDeptModel> getInfoByRoleId(Integer id);

    /**
     * 根据角色ID查所有机构信息
     *
     * @param ids 机构id
     * @return 角色机构信息
     */
    List<RbacRoleDeptModel> getInfoByRoleIds(@Param("ids") List<Integer> ids);
}
