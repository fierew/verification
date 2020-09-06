package com.weigh.verification.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.RbacUserDao;
import com.weigh.verification.entity.Result;
import com.weigh.verification.model.RbacUserModel;
import com.weigh.verification.service.RbacUserService;
import com.weigh.verification.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author xuyang
 */
@Slf4j
@Service
public class RbacUserServiceImpl implements RbacUserService {
    @Autowired
    private RbacUserDao rbacUserDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result getList(Integer page, Integer pageSize, RbacUserModel rbacUserModel) {
        PageHelper.startPage(page, pageSize);
        List<RbacUserModel> list = rbacUserDao.getList(rbacUserModel);

        Result result = new Result();
        result.setCode(200);
        result.setData(new PageInfo<>(list));
        result.setMsg("success");

        return result;
    }

    @Override
    public Result add(RbacUserModel rbacUserModel) {
        Result result = new Result();

        // 判断邮箱是否存在
        RbacUserModel rbacUserInfo = rbacUserDao.getInfoByEmail(rbacUserModel.getEmail());
        if (rbacUserInfo != null) {
            result.setCode(400);
            result.setMsg("该邮箱已存在");
            return result;
        }

        // 判断手机号是否存在
        RbacUserModel rbacUserInfo1 = rbacUserDao.getInfoByMobile(rbacUserModel.getMobile());
        if (rbacUserInfo1 != null) {
            result.setCode(400);
            result.setMsg("该手机号已存在");
            return result;
        }

        rbacUserModel.setPassword(bCryptPasswordEncoder.encode(rbacUserModel.getPassword()));

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        rbacUserModel.setCreateTime(time);
        rbacUserModel.setUpdateTime(time);

        rbacUserDao.add(rbacUserModel);

        result.setCode(200);
        result.setMsg("新增用户成功");
        return result;
    }

    @Override
    public Result edit(Integer id, RbacUserModel rbacUserModel) {
        Result result = new Result();

        RbacUserModel rbacUserInfo = rbacUserDao.getInfoById(id);
        if (rbacUserInfo == null) {
            result.setCode(400);
            result.setMsg("要编辑的数据不存在");
            return result;
        }

        // 判断邮箱是否存在
        RbacUserModel rbacUserInfo1 = rbacUserDao.getInfoByEmail(rbacUserModel.getEmail());
        if (rbacUserInfo1 != null && !rbacUserInfo1.getId().equals(id)) {
            result.setCode(400);
            result.setMsg("该邮箱已存在");
            return result;
        }

        // 判断手机号是否存在
        RbacUserModel rbacUserInfo2 = rbacUserDao.getInfoByMobile(rbacUserModel.getMobile());
        if (rbacUserInfo2 != null && !rbacUserInfo2.getId().equals(id)) {
            result.setCode(400);
            result.setMsg("该手机号已存在");
            return result;
        }

        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        rbacUserModel.setUpdateTime(time);

        rbacUserDao.edit(rbacUserModel);

        result.setCode(200);
        result.setMsg("编辑用户成功");
        return result;
    }

    @Override
    public Result delete(Integer id) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        rbacUserDao.delete(id, time);

        Result result = new Result();

        result.setCode(200);
        result.setMsg("删除用户成功");
        return result;
    }

    @Override
    public Result modifyState(Integer id, Byte state) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);
        rbacUserDao.modifyState(id, state, time);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("修改用户状态成功");
        return result;
    }

    @Override
    public Result modifyPassWord(Integer id, String password) {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        rbacUserDao.modifyPassword(id, bCryptPasswordEncoder.encode(password), time);

        Result result = new Result();
        result.setCode(200);
        result.setMsg("修改用户密码成功");
        return result;
    }

    @Override
    public Result login(RbacUserModel rbacUserModel) {
        RbacUserModel rbacUserInfo = rbacUserDao.getInfoByEmail(rbacUserModel.getEmail());

        Result result = new Result();

        if (rbacUserInfo == null || !bCryptPasswordEncoder.matches(rbacUserModel.getPassword(), rbacUserInfo.getPassword())) {
            result.setCode(400);
            result.setMsg("用户名或密码错误");
            return result;
        }

        if (rbacUserInfo.getState() == 0) {
            result.setCode(400);
            result.setMsg("用户被禁用，请联系管理员！");
            return result;
        }

        String token = jwtUtil.sign(rbacUserInfo.getId());

        Map<String, Object> info = new HashMap<>(5);

        info.put("token", token);
        info.put("userId", rbacUserInfo.getId());
        info.put("email", rbacUserInfo.getEmail());
        info.put("mobile", rbacUserInfo.getMobile());
        info.put("realName", rbacUserInfo.getRealName());

        result.setCode(200);
        result.setMsg("登录成功！");
        result.setData(info);
        return result;
    }

    @Override
    public Result getInfo(Integer id) {
        RbacUserModel rbacUserModel = rbacUserDao.getInfoById(id);

        Result result = new Result();
        if (rbacUserModel == null) {
            result.setCode(400);
            result.setMsg("用户不存在！");
            return result;
        }

        if (rbacUserModel.getState() == null) {
            result.setCode(400);
            result.setMsg("用户被禁用，请联系管理员！");
            return result;
        }

        Map<String, Object> info = new HashMap<>(5);

        info.put("userId", rbacUserModel.getId());
        info.put("email", rbacUserModel.getEmail());
        info.put("mobile", rbacUserModel.getMobile());
        info.put("realName", rbacUserModel.getRealName());

        result.setCode(200);
        result.setMsg("success");
        result.setData(info);
        return result;
    }
}
