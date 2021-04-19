package com.zhangys.controller;

import com.zhangys.entity.DepartmentEntity;
import com.zhangys.entity.UserEntity;
import com.zhangys.service.DepartmentService;
import com.zhangys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 123 on 2017/2/21.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    //访问地址：http://localhost:8080/Test/returnSuccess
    @RequestMapping(value = "/returnSuccess")    //实际访问的url地址
    public String returnSuccess() {
        return "/views/success";    //返回Views文件夹下的success.jsp页面
    }

    //访问地址：http://localhost:8080/Test/returnString,produces用于解决返回中文乱码问题，application/json;为json解决中文乱码
    @RequestMapping(value = "/returnString", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody       //用于返回字符串,不写即返回视图
    public String returnString() {
        return "这是一个controller!";
    }

    @RequestMapping(value = "/returnUser", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String returnUser(){
        UserEntity userEntity ;
        try{

//            List<UserEntity> userEntities = userService.getAllUser();
//            userEntities.size();

//            增
//            userEntity = new UserEntity("岳不群", "yuebuqun", "123456", 1, "a@b.c", "201393008", "11122223333");
//            userService.createUser(userEntity);

//            删
//            userEntity = userService.getUserByName("yuebuqun");
//            userService.deleteUser(userEntity);

//            改
//            userEntity = userService.getUserByName("yuebuqun");
//            userEntity.setCn("201393008");
//            userService.updateUser(userEntity);

//            查
            userEntity = userService.getUserByName("zhangyingsong");
            userEntity.getFullName();
            DepartmentEntity departmentEntity = departmentService.getDptByName("少林");
            departmentEntity.getDepName();
        }catch (Exception e){
            return "error";
        }
        return "success";
    }

}
