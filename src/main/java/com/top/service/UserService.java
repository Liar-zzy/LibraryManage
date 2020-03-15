package com.top.service;

import com.top.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 插入User信息
     * */
    boolean AddUser(User user);

    /**
     * 删除User信息
     * */
    boolean DeleteUser(String Rid);

    /**
     * 修改User信息
     * */
    boolean UpdateUser(User user);

    /**
     * 查询User信息
     * */
    User GetUser(String Rid);

    /**
     * 查询所有用户
     * */
    List<User> getAllUser();
    
}
