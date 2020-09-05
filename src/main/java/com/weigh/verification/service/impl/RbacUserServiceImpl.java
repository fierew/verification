package com.weigh.verification.service.impl;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacUserModel;
import com.weigh.verification.service.RbacUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户管理
 *
 * @author xuyang
 */
@Slf4j
@Service
public class RbacUserServiceImpl implements RbacUserService {
    @Override
    public PageInfo<RbacUserModel> getList(Integer page, Integer pageSize, RbacUserModel rbacUserModel) {
        return null;
    }

    @Override
    public Result add(RbacUserModel rbacUserModel) {
        // 判断邮箱是否存在


        // 判断手机号是否存在
        return null;
    }

    @Override
    public Result edit(RbacUserModel rbacUserModel) {
        return null;
    }

    @Override
    public Result delete(Integer id) {
        return null;
    }

    @Override
    public Result modifyState(Integer id) {
        return null;
    }
}
