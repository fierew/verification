package com.weigh.verification.service.rbac;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.UserModel;

/**
 * @author xuyang
 */
public interface UserService {
    /**
     * 获取分页数据
     *
     * @param page      页数
     * @param pageSize  每页多少条数据
     * @param userModel 条件
     * @return 分页列表
     */
    PageInfo<UserModel> getList(Integer page, Integer pageSize, UserModel userModel);

    /**
     * 添加
     *
     * @param userModel 信息
     * @return 结果
     */
    Result add(UserModel userModel);

    /**
     * 编辑
     *
     * @param userModel 信息
     * @return 结果
     */
    Result edit(UserModel userModel);

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
