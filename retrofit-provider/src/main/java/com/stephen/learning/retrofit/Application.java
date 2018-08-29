package com.stephen.learning.retrofit;

import com.stephen.learning.retrofit.constant.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

/**
 * @Auther: jack
 * @Date: 2018/8/29 17:15
 * @Description: 启动类
 */
@Slf4j
@SpringBootApplication
@EnableSwagger2
public class Application {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stephen.learning.retrofit.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title(AppConstant.title).
                description(DateFormatUtils.format(new Date(),AppConstant.pattern)).
                license(AppConstant.license).
                contact(ApiInfo.DEFAULT_CONTACT).
                version(AppConstant.version).
                build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
