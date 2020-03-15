package com.top.service.impl;

import com.top.mapper.UserMapper;
import com.top.pojo.User;
import com.top.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean AddUser(User user) {
        int row = userMapper.addAUser(user);
        return row == 1 ? true : false;
    }

    @Override
    public boolean DeleteUser(String Rid) {
        int row = userMapper.deleteAUser(Rid);
        return row == 1 ? true : false;
    }

    @Override
    public boolean UpdateUser(User user) {
        int row = userMapper.modifyAUser(user);
        return row == 1 ? true : false;
    }

    @Override
    public User GetUser(String Rid) {
        User user = userMapper.getAUser(Rid);
        return user;
    }

    @Override
    public List<User> GetAllUser() {
        List<User> list = userMapper.getAllUser();
        return list;
    }

}