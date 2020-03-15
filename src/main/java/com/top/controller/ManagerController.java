package com.top.controller;

import com.top.pojo.Manager;
import com.top.service.ManagerService;
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
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 管理员登录
     * */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, String> login(@RequestBody Manager manager, HttpSession session) {
        Map<String, String> map = new HashMap<>();
        System.out.println("传入的 Reader Id :   " + manager.getId() + "  password: " + manager.getPassword());

        manager = managerService.GetManager(manager.getId());

        if (manager != null) {
            System.out.println("login success");

            session.setAttribute("SESSION_USER", manager);

            map.put("logincheck", "success");

            //成功后 传回角色信息
            map.put("logincheck", "success");
        } else {
            System.out.println("login failure");
            map.put("logincheck", "failure");
        }
        return map;
    }

    /**
     * 添加管理员
     * */
    @RequestMapping("/addManager")
    @ResponseBody
    public Map<String,String> addManager(@RequestBody Manager manager){

        System.out.println(manager.getName() + "Id: "+manager.getId()+"pass: "+manager.getPassword());
        boolean success;
        Map<String, String > map = new HashMap<>();
        success = managerService.AddManager(manager);

        if(success == true) {
            map.put("add","success");
            System.out.println("add manager success");
        }
        else {
            map.put("add","fail");
            System.out.println("add manager fail");
        }
        return map;
    }

    /**
     * 删除管理员
     * */
    @RequestMapping("/deleteManager")
    @ResponseBody
    public Map<String,String> deleteManager(@RequestBody Manager manager){
        System.out.println("delete A manager ");
        System.out.println(manager.getName());

        boolean success;
        success = managerService.DeleteManager(manager.getId());

        Map<String, String > map = new HashMap<>();
        if(success){
            map.put("delete","success");
            System.out.println("delete manager success");
        }
        else{
            map.put("delete","success");
            System.out.println("delete manager fail");
        }
        return map;
    }


    /**
     * 修改管理员信息
     * */
    @RequestMapping("/updateManager")
    @ResponseBody
    public Map<String,String> updateManager(@RequestBody Manager manager){
        System.out.println("update A manager ");
        System.out.println(manager.getId() + manager.getName());

        boolean success;
        success = managerService.UpdateManager(manager);
        Map<String, String > map = new HashMap<>();

        if(success == true) {
            map.put("update","success");
            System.out.println("update manager success");
        } else {
            map.put("update","fail");
            System.out.println("update manager fail");
        }
        return map;
    }

}
