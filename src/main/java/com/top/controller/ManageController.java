package com.top.controller;

import com.top.pojo.Book;
import com.top.pojo.User;
import com.top.service.BookService;
import com.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.List;

import java.util.Map;

@Controller
@RequestMapping("resources/man")
public class ManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    /**
     * 管理员 管理所有图书
     * */
    @RequestMapping("/inquireAllBook")
    public ModelAndView inquireBook() {

        ModelAndView modelAndView = new ModelAndView();

        List<Book> bookList;

        bookList = bookService.GetAllBook();

        if (bookList != null) {

            System.out.println("inquire success");
            modelAndView.addObject("SESSION_BOOK", bookList);

        } else {
            System.out.println("inquire failure");
        }

            modelAndView.setViewName("page/m-manageBook");
            System.out.println("Man manageBook");

        return modelAndView;
    }

    /**
     * 管理员管理所有用户
     * */
    @RequestMapping("/manAllReader")
    public ModelAndView manAllBorrowAndRead() {

        ModelAndView modelAndView = new ModelAndView();


        List<User> AllUserList = userService.getAllUser();

        if (AllUserList != null) {
            System.out.println("inquire AllUserList success");

            modelAndView.addObject("AllUserList", AllUserList);

        } else {
            System.out.println("inquire AllUserList failure");

        }
        modelAndView.setViewName("page/m-manageReader");
        return modelAndView;
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


    /**
     * 查询所有读者
     * */
    @RequestMapping("/inquireAllReader")
    public ModelAndView inquireAllBorrowAndRead() {

        ModelAndView modelAndView = new ModelAndView();


        List<User> AllUserList = userService.getAllUser();

        if (AllUserList != null) {
            System.out.println("inquire AllUserList success");

            modelAndView.addObject("AllUserList", AllUserList);

        } else {
            System.out.println("inquire AllUserList failure");

        }
        modelAndView.setViewName("page/m-getReader");
        return modelAndView;
    }

}

