package com.weigh.verification.dao.rbac;

import com.weigh.verification.model.rbac.RoleResourceModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface RoleResourceDao {
    /**
     * 添加角色资源
     * @param roleResourceModels 角色资源信息
     * @return 结果
     */
    Integer addAll(List<RoleResourceModel> roleResourceModels);
}
