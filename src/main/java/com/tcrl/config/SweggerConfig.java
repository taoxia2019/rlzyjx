package com.tcrl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SweggerConfig
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/10/11 15:41
 * @Version 1.0
 */
@Component
@EnableSwagger2
public class SweggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcrl.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("构建API文档")
                .version("1.0.0")
                .build();
    }
}
