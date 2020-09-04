package com.weigh.verification.dao;

import com.weigh.verification.model.RoleDeptModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuyang
 */
@Mapper
public interface RoleDeptDao {
    /**
     * 批量插入
     *
     * @param roleDeptModels 角色机构信息
     * @return 结果
     */
    Integer addAll(List<RoleDeptModel> roleDeptModels);

    /**
     * 物理删除
     *
     * @param roleId 角色id
     * @return 结果
     */
    Integer delete(Integer roleId);
}
