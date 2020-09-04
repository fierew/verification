package com.weigh.verification.dao.rbac;

import com.weigh.verification.model.rbac.RoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface RoleDao {
    /**
     * 新增角色
     *
     * @param roleModel 角色信息
     * @return 结果
     */
    Integer add(RoleModel roleModel);

    /**
     * 编辑角色
     *
     * @param roleModel 角色信息
     * @return 结果
     */
    Integer edit(RoleModel roleModel);

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
    List<RoleModel> getList(RoleModel roleModel);
}
