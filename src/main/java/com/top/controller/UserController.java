package com.top.controller;

import com.top.pojo.User;
import com.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * User登录
     * */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, String> login(@RequestBody User user, HttpSession session) {
        Map<String, String> map = new HashMap<>();
        System.out.println("传入的 User Id :   " + user.getId() + "  password: " + user.getPassword());

        user = userService.GetUser(user.getId());

        if (user != null) {
            System.out.println("login success");

            session.setAttribute("SESSION_USER", user);

            map.put("logincheck", "success");

        } else {
            System.out.println("login failure");
            map.put("logincheck", "failure");
        }
        return map;
    }

    /**
     * 添加User
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public Map<String,String> addUser(@RequestBody User user){

        boolean success;
        Map<String, String > map = new HashMap<>();
        success = userService.AddUser(user);

        if(success == true) {
            map.put("add","success");
            System.out.println("add User success");
        }
        else {
            map.put("add","fail");
            System.out.println("add User fail");
        }
        return map;
    }

    /**
     * 删除User
     * */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String,String> deleteUser(@RequestBody User user){
        System.out.println("delete A User ");
        System.out.println(user.getName());

        boolean success;
        success = userService.DeleteUser(user.getId());

        Map<String, String > map = new HashMap<>();
        if(success){
            map.put("delete","success");
            System.out.println("delete User success");
        }
        else{
            map.put("delete","success");
            System.out.println("delete User fail");
        }
        return map;
    }

    /**
     * 修改User
     * */
    @RequestMapping("/updateUser")
    @ResponseBody
    public Map<String,String> updateUser(@RequestBody User user){
        System.out.println("update A User ");
        System.out.println(user.getId() + user.getName());

        boolean success;
        success = userService.UpdateUser(user);
        Map<String, String > map = new HashMap<>();

        if(success == true) {
            map.put("update","success");
            System.out.println("update user success");
        } else {
            map.put("update","fail");
            System.out.println("update user fail");
        }
        return map;
    }

}
