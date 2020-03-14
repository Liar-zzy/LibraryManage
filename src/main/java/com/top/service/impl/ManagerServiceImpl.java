package com.top.service.impl;

import com.top.mapper.ManagerMapper;
import com.top.pojo.Manager;
import com.top.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public boolean AddManager(Manager manager) {
        int row = managerMapper.addAManager(manager);
        return row == 1 ? true : false;
    }

    @Override
    public boolean DeleteManager(String Mid) {
        int row = managerMapper.deleteAManager(Mid);
        return row == 1 ? true : false;
    }

    @Override
    public boolean UpdateManager(Manager manager) {
        int row = managerMapper.modifyAManager(manager);
        return row == 1 ? true : false;
    }

    @Override
    public Manager GetManager(String Mid) {
        Manager manager = managerMapper.getAManager(Mid);
        return manager;
    }

}
