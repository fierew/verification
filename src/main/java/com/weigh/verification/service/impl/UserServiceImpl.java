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
    private JwtUtil jwtUtil;

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
        return userDao.getList(page, pageSize);
    }

    @Override
    public UserModel getInfo(Integer id) {
        return userDao.getInfoById(id);
    }

    @Override
    public Integer edit(Integer id, UserModel userModel) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        userModel.setId(id);
        userModel.setUpdateTime(time);

        return userDao.edit(userModel);
    }

    @Override
    public Integer delete(Integer id) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        return userDao.delete(id, time);
    }

    @Override
    public Object login(UserModel userModel) {
        UserModel userInfo = userDao.getInfo(userModel.getEmail());

        if (userInfo == null || !bCryptPasswordEncoder.matches(userModel.getPassword(), userInfo.getPassword())) {
            return null;
        }

        String token = jwtUtil.sign(userInfo.getId());

        Map<String, Object> info = new HashMap<>(5);

        info.put("token", token);
        info.put("userId", userInfo.getId());
        info.put("email", userInfo.getEmail());

        return info;
    }

    @Override
    public Integer modifyState(Integer id) {


        UserModel userInfo = userDao.getInfoById(id);
        if (userInfo == null) {
            return null;
        }

        int state = 0;
        if (userInfo.getState() == 0) {
            state = 1;
        }

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        return userDao.modifyState(id, state, time);
    }
}
