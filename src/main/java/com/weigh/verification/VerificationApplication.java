package com.weigh.verification;

import com.weigh.verification.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * @author xuyang
 */
@MapperScan("com.weigh.verification.dao")
@SpringBootApplication
@Controller
public class VerificationApplication {
    @Autowired
    private UserDao userDao;

    public static void main(String[] args) {
        SpringApplication.run(VerificationApplication.class, args);
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化！！！");
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }
}
