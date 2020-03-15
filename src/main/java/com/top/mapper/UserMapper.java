package com.top.mapper;

import com.top.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 插入User信息
     * */
    int addAUser(@Param("addUser") User user);

    /**g
     * 删除User信息
     * */
    int deleteAUser(@Param("deleteUser")String rid);

    /**
     * 修改User信息
     * */
    int modifyAUser(@Param("modifyUser") User user);

    /**
     * 查询User信息
     * */
    User getAUser(@Param("getUser")String rid);

    /**

     * 查询所有读者
     * */
    List<User> getAllUser();
}

