package com.weigh.verification.dao;

import com.weigh.verification.model.RbacDeptModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
public interface RbacDeptDao {
    /**
     * 新增机构
     *
     * @param deptModel 机构信息
     * @return 结果
     */
    Integer add(RbacDeptModel deptModel);

    /**
     * 编辑机构
     *
     * @param deptModel 机构信息
     * @return 结果
     */
    Integer edit(RbacDeptModel deptModel);

    /**
     * 删除机构
     *
     * @param id         机构id
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer delete(@Param("id") Integer id, @Param("updateTime") Integer updateTime);

    /**
     * 批量删除机构
     *
     * @param ids        机构id
     * @param updateTime 更新时间
     * @return 结果
     */
    Integer deletes(@Param("ids") List<Integer> ids, @Param("updateTime") Integer updateTime);

    /**
     * 获取机构所有信息
     *
     * @return 机构信息
     */
    List<RbacDeptModel> getAll();
}
