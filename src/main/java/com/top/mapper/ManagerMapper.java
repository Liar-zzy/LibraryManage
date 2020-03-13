package com.top.mapper;

import com.top.pojo.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerMapper {

    /**
     * 插入管理员信息
     * */
    int addAManager(@Param("addManager")Manager manager);

    /**
     * 删除管理员信息
     * */
    int deleteAManager(@Param("deleteManager")String id);

    /**
     * 修改管理员信息
     * */
    int modifyAManager(@Param("modifyManager")Manager manager);

    /**
     * 查询管理员信息
     * */
    Manager getAManager(@Param("getManager") String mid);

}
