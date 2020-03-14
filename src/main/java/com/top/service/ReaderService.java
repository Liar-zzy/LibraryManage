package com.top.service;

import com.top.pojo.Reader;

public interface ReaderService {

    /**
     * 插入读者信息
     * */
    boolean AddReader(Reader reader);

    /**
     * 删除读者信息
     * */
    boolean DeleteReader(String Rid);

    /**
     * 修改读者信息
     * */
    boolean UpdateReader(Reader reader);

    /**
     * 查询读者信息
     * */
    Reader GetReader(String Rid);
    
}
