package com.hupofx.controller;

import com.hupofx.anno.MyAnnotation4Swagger;
import com.hupofx.entity.MyEntity;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Api - 描述当前类型生成帮助文档的信息
 * 属性 -
 * - tags：给当前类型定义别名，可以有多个。定义几个别名，在ui视图中就显示几个控制器访问菜单。
 * - description：给当前类型生成的帮助文档定义一个描述信息
 */
@RestController
@RequestMapping("/swagger")
@Api(tags = {"MyController", "Swagger学习控制器"}, description = "测试API类型描述信息")
public class MyController {

    @RequestMapping("/testEntity")
    public MyEntity testEntity(){
        return new MyEntity();
    }

    @GetMapping("/test")
    //@ApiImplicitParam(name = "m" , value = "m参数描述", required = false,paramType = "字符串类型",dataType = "名值对")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "m", value = "m参数描述", required = false, paramType = "字符串类型", dataType = "名值对"),
            @ApiImplicitParam(name = "n", value = "n参数描述", required = true, paramType = "字符串(String)", dataType = "名值对")
    })
    public String test(String m, String n) {
        return "test";
    }

    @ApiOperation(value = "post方法，执行数据新增操作", notes = "Swagger学习使用-处理post请求的方法")
    @PostMapping("/post")
    public String post(
            @ApiParam(name = "用户名（a）", value = "新增用户时提供的用户名", required = true) String a,
            @ApiParam(name = "密码（b）", value = "新增用户提供的密码", required = true) String b) {
        return "post";
    }

    //ApiIgnore 忽略，当前注解描述的方法或类型，不生成api帮助文档。
    @ApiIgnore
    @GetMapping("/get")
    public String get(String a, String b) {
        return "get";
    }

    @MyAnnotation4Swagger
    @RequestMapping("/req")
    public String req(String m) {
        return "req";
    }
}
