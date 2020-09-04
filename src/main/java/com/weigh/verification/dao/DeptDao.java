package com.weigh.verification.dao;

import com.weigh.verification.model.DeptModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuyang
 */
public interface DeptDao {
    /**
     * 新增机构
     *
     * @param deptModel 机构信息
     * @return 结果
     */
    Integer add(DeptModel deptModel);

    /**
     * 编辑机构
     *
     * @param deptModel 机构信息
     * @return 结果
     */
    Integer edit(DeptModel deptModel);

    /**
     * 删除机构
     *
     * @param id 机构id
     * @return 结果
     */
    Integer delete(@Param("id") Integer id, @Param("updateTime") Integer updateTime);

    /**
     * 获取机构所有信息
     *
     * @return 机构信息
     */
    List<DeptModel> getAll();
}
