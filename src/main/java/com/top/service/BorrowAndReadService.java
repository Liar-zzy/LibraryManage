package com.top.service;

import com.top.pojo.BorrowAndRead;

import java.util.List;

public interface BorrowAndReadService {

    /**
     * 插入借阅记录
     * */
    boolean AddRecord(BorrowAndRead borrowAndRead);

    /**
     * 删除借阅记录
     * */
    boolean DeleteRecord(String Bid, String Pid);

    /**
     * 查询单个读者借阅信息
     * */
    List<BorrowAndRead> InquireBorrowAndRead(String Pid);

    /**
     * 查询所有借阅记录
     * */
    List<BorrowAndRead> InquireAllBorrowAndRead();
}
