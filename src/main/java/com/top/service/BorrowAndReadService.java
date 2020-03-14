package com.top.service;

import com.top.pojo.BorrowAndRead;

public interface BorrowAndReadService {

    /**
     * 插入借阅记录
     * */
    boolean AddRecord(BorrowAndRead borrowAndRead);

    /**
     * 删除借阅记录
     * */
    boolean DeleteRecord(String Bid, String Pid);
}
