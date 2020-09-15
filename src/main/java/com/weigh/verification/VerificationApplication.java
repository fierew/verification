package com.weigh.verification;

import com.weigh.verification.service.InitService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
public class VerificationApplication {
    @Autowired
    private InitService initService;

    public static void main(String[] args) {
        SpringApplication.run(VerificationApplication.class, args);
    }

    @PostConstruct
    public void init() {
        log.info("初始化！！！");

        initService.initUser();

        log.info("初始化完成！！！");
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }
}
