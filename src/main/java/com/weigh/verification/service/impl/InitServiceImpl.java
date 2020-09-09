package com.weigh.verification.service.impl;

import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.RbacUserDao;
import com.weigh.verification.model.RbacUserModel;
import com.weigh.verification.service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author xuyang
 */
@Slf4j
@Service
public class InitServiceImpl implements InitService {
    @Autowired
    private RbacUserDao rbacUserDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void initUser() {
        Integer time = (int) Math.floor(DateTimeUtil.getNowTime() / 1000);

        RbacUserModel rbacUserInfo = rbacUserDao.getInfoByEmail("administrator");

        if (rbacUserInfo == null) {
            log.info("没有发现管理员账号，准备创建！！！");

            RbacUserModel rbacUserModel = new RbacUserModel();
            rbacUserModel.setDeptId(0);
            rbacUserModel.setRoleId(0);
            rbacUserModel.setRealName("管理员");
            rbacUserModel.setMobile("");
            rbacUserModel.setSex((byte) 0);
            rbacUserModel.setAge(0);
            rbacUserModel.setCreateTime(time);
            rbacUserModel.setUpdateTime(time);

            rbacUserModel.setEmail("administrator");
            rbacUserModel.setPassword(bCryptPasswordEncoder.encode("123456"));
            rbacUserDao.add(rbacUserModel);

            log.info("创建管理员账号成功！！！");
        }
    }
}
