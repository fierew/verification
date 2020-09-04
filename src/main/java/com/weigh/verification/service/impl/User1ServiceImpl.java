package com.weigh.verification.service.impl;

import com.github.pagehelper.PageInfo;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.User1Model;
import com.weigh.verification.service.User1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户管理
 *
 * @author xuyang
 */
@Slf4j
@Service
public class User1ServiceImpl implements User1Service {
    @Override
    public PageInfo<User1Model> getList(Integer page, Integer pageSize, User1Model userModel) {
        return null;
    }

    @Override
    public Result add(User1Model userModel) {
        // 判断邮箱是否存在


        // 判断手机号是否存在
        return null;
    }

    @Override
    public Result edit(User1Model userModel) {
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
