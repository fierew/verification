package com.weigh.verification.service.impl;

import com.weigh.verification.model.UserModel;
import com.weigh.verification.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuyang
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Integer add(UserModel userModel) {
        return null;
    }

    @Override
    public List<UserModel> getList(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public UserModel getInfo() {
        return null;
    }

    @Override
    public Integer edit(Integer id, UserModel userModel) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public UserModel login() {
        return null;
    }

    @Override
    public Integer modifyState(Integer id) {
        return null;
    }
}
