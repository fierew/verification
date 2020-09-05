package com.weigh.verification;

import com.power.common.util.DateTimeUtil;
import com.weigh.verification.dao.RbacUserDao;
import com.weigh.verification.model.RbacUserModel;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * @author xuyang
 */
@MapperScan("com.weigh.verification.dao")
@SpringBootApplication
@Slf4j
@Controller
public class VerificationApplication {
    @Autowired
    private RbacUserDao rbacUserDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(VerificationApplication.class, args);
    }

    @PostConstruct
    public void init() {
        log.info("初始化！！！");
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

        log.info("初始化成功！！！");
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }
}
