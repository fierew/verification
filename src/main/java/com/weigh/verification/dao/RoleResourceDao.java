package com.weigh.verification.dao;

import com.weigh.verification.model.RoleResourceModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface RoleResourceDao {
    /**
     * 添加角色资源
     *
     * @param roleResourceModels 角色资源信息
     * @return 结果
     */
    Integer addAll(List<RoleResourceModel> roleResourceModels);

    /**
     * 物理删除
     *
     * @param roleId 角色id
     * @return 结果
     */
    Integer delete(Integer roleId);
}
