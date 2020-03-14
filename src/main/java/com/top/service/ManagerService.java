package com.top.service;

import com.top.pojo.Manager;

public interface ManagerService {

    /**
     * 插入管理员
     * */
    boolean AddManager(Manager manager);

    /**
     * 删除管理员
     * */
    boolean DeleteManager(String Mid);

    /**
     * 修改管理员信息
     * */
    boolean UpdateManager(Manager manager);

    /**
     * 查询管理员信息
     * */
    Manager GetManager(String Mid);

}
