package com.weigh.verification.service;

import com.weigh.verification.entity.Result;
import com.weigh.verification.model.DeptModel;

/**
 * @author xuyang
 */
public interface DeptService {
    /**
     * 新增机构
     *
     * @param deptModel 机构信息
     * @return 结果
     */
    Result add(DeptModel deptModel);

    /**
     * 编辑机构
     *
     * @param id        id
     * @param deptModel 机构信息
     * @return 结果
     */
    Result edit(Integer id, DeptModel deptModel);

    /**
     * 删除机构
     *
     * @param id id
     * @return 结果
     */
    Result delete(Integer id);

    /**
     * 获取机构
     *
     * @return 结果
     */
    Result getAll();
}
