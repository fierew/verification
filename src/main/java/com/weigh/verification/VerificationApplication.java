package com.weigh.verification;

import com.weigh.verification.config.UniqueNameGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuyang
 */
@MapperScan("com.weigh.verification.dao")
@SpringBootApplication
@Controller
@ComponentScan(nameGenerator = UniqueNameGenerator.class)
public class VerificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(VerificationApplication.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }
}
