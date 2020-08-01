package com.weigh.verification.service.impl;

import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.UserDao;
import com.weigh.verification.model.UserModel;
import com.weigh.verification.service.UserService;
import com.weigh.verification.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuyang
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public Integer add(UserModel userModel) {
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        userModel.setCreateTime(time);
        userModel.setUpdateTime(time);

        return userDao.add(userModel);
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
    public Object login(UserModel userModel) {
        UserModel userInfo = userDao.getInfo(userModel.getEmail());

        if (userInfo == null || !bCryptPasswordEncoder.matches(userModel.getPassword(), userInfo.getPassword())) {
            return null;
        }

        String token = JwtUtil.sign(userInfo.getId());

        //
        Map<String, Object> info = new HashMap<>(5);

        info.put("token", token);
        info.put("userId", userInfo.getId());
        info.put("email", userInfo.getEmail());

        return info;
    }

    @Override
    public Integer modifyState(Integer id) {
        return null;
    }
}
