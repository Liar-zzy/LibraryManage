package com.top.controller;

import com.top.pojo.Reader;
import com.top.service.ReaderService;
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
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    /**
     * 读者登录
     * */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, String> login(@RequestBody Reader reader, HttpSession session) {
        Map<String, String> map = new HashMap<>();
        System.out.println("传入的 Reader Id :   " + reader.getId() + "  password: " + reader.getPassword());

        reader = readerService.GetReader(reader.getId());

        if (reader != null) {
            System.out.println("login success");

            session.setAttribute("SESSION_USER", reader);

            map.put("logincheck", "success");

        } else {
            System.out.println("login failure");
            map.put("logincheck", "failure");
        }
        return map;
    }

    /**
     * 添加读者
     */
    @RequestMapping("/addReader")
    @ResponseBody
    public Map<String,String> addReader(@RequestBody Reader reader){

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        reader.setRegisterDate((sdf.format(date)));

        boolean success;
        Map<String, String > map = new HashMap<>();
        success = readerService.AddReader(reader);

        if(success == true) {
            map.put("add","success");
            System.out.println("add Reader success");
        }
        else {
            map.put("add","fail");
            System.out.println("add Reader fail");
        }
        return map;
    }

    /**
     * 删除读者
     * */
    @RequestMapping("/deleteReader")
    @ResponseBody
    public Map<String,String> deleteReader(@RequestBody Reader reader){
        System.out.println("delete A Reader ");
        System.out.println(reader.getName());

        boolean success;
        success = readerService.DeleteReader(reader.getId());

        Map<String, String > map = new HashMap<>();
        if(success){
            map.put("delete","success");
            System.out.println("delete Reader success");
        }
        else{
            map.put("delete","success");
            System.out.println("delete Reader fail");
        }
        return map;
    }

    /**
     * 修改读者信息
     * */
    @RequestMapping("/updateReader")
    @ResponseBody
    public Map<String,String> updateReader(@RequestBody Reader reader){
        System.out.println("update A Reader ");
        System.out.println(reader.getId() + reader.getName());

        boolean success;
        success = readerService.UpdateReader(reader);
        Map<String, String > map = new HashMap<>();

        if(success == true) {
            map.put("update","success");
            System.out.println("update reader success");
        } else {
            map.put("update","fail");
            System.out.println("update reader fail");
        }
        return map;
    }

}
