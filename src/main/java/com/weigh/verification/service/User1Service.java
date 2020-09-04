package com.weigh.verification.service;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.User1Model;

/**
 * @author xuyang
 */
public interface User1Service {
    /**
     * 获取分页数据
     *
     * @param page      页数
     * @param pageSize  每页多少条数据
     * @param userModel 条件
     * @return 分页列表
     */
    PageInfo<User1Model> getList(Integer page, Integer pageSize, User1Model userModel);

    /**
     * 添加
     *
     * @param userModel 信息
     * @return 结果
     */
    Result add(User1Model userModel);

    /**
     * 编辑
     *
     * @param userModel 信息
     * @return 结果
     */
    Result edit(User1Model userModel);

    /**
     * 删除
     *
     * @param id id
     * @return 结果
     */
    Result delete(Integer id);

    /**
     * 修改状态
     *
     * @param id id
     * @return 结果
     */
    Result modifyState(Integer id);
}
