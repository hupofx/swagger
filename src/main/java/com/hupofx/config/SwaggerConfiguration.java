package com.hupofx.config;

import com.google.common.base.Predicates;
import com.hupofx.anno.MyAnnotation4Swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    /**
     * 创建Docket类型的对象，并使用spring容器管理
     * Docket是Swagger中的全局配置对象。
     *
     * @return
     */
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        //API帮助文档的描述信息。information
        ApiInfo apiInfo =
                new ApiInfoBuilder()
                        .contact(   //配置swagger文档主体内容。
                                new Contact(
                                        "啦啦啦德玛西亚",  //是文档发布者名称
                                        "https://www.hupofx.com", //是文档发布者的网站地址。企业网站
                                        "admin@123.com")    //文档发布者的电子邮箱
                        )
                        .title("Swagger开发文档")
                        .description("Swagger框架学习帮助文档详细描述-当前Swagger框架是用于开发RestAPI帮助文档的框架")
                        .version("1.1")
                        .build();

        //给docket上下文配置api描述信息
        docket.apiInfo(apiInfo);

        docket = docket
                .select() //获取Docket中的选择器。返回ApiSelectorBuilder。构建选择器的，如：扫描什么包的注解
                .apis(
                        Predicates.not( //取反
                                RequestHandlerSelectors.withMethodAnnotation( //当方法上有注解时候返回true
                                        MyAnnotation4Swagger.class  //方法上有什么注解的时候返回true
                                )
                        )
                )
                .apis(RequestHandlerSelectors.basePackage("com.hupofx.controller"))//设定扫描哪个包(包含子包)
                .paths(
                        Predicates.or( //多个规则符合任意一个即可通过。
                            PathSelectors.regex("/swagger/.*"),//使用正则表达式，约束生成API文档的路径地址。
                            PathSelectors.regex("/swagger2/.*"),
                            PathSelectors.regex("/.*")
                        )
                )
                .build(); //重新构建Docket对象
        return docket;
    }
}
