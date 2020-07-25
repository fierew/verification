package com.weigh.verification.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuyang
 */
@Configuration
public class DocConfig {
    @Bean
    public OpenAPI customOpenApi(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("JWT",
                        new SecurityScheme().type(SecurityScheme.Type.APIKEY).name("authorization").in(SecurityScheme.In.HEADER)))
                .info(new Info().title("博客后台 API").version(appVersion).description(
                        "这是一个博客管理后台API文档")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
