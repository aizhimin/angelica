/*
 * Copyright (C) 2016 iZaojiao. All rights reserved.
 */
package org.angelica.admin.config;

import javax.servlet.http.HttpServletRequest;

import org.angelica.admin.common.shiro.ShiroUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 接口文档配置
 * @author aizhimin
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.angelica.qingdanxia.controller"))
                .paths(PathSelectors.any())
                .build().ignoredParameterTypes(ShiroUser.class,HttpServletRequest.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("qingdanxia-admin接口文档")
                .description("qingdanxia-admin接口文档")
                .version("1.0")
                .build();
    }
}
