package com.weigh.verification.service.impl.rbac;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.rbac.UserModel;
import com.weigh.verification.service.rbac.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理
 *
 * @author xuyang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Override
    public PageInfo<UserModel> getList(Integer page, Integer pageSize, UserModel userModel) {
        return null;
    }

    @Override
    public Result add(UserModel userModel) {
        return null;
    }

    @Override
    public Result edit(UserModel userModel) {
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
