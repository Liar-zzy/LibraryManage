package com.top.service.impl;

import com.top.mapper.BorrowAndReadMapper;
import com.top.pojo.BorrowAndRead;
import com.top.service.BorrowAndReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BorrowAndReadService")
public class BorrowAndReadServiceImpl implements BorrowAndReadService {

    @Autowired
    private BorrowAndReadMapper borrowAndReadMapper;

    @Override
    public boolean AddRecord(BorrowAndRead borrowAndRead) {
        int row = borrowAndReadMapper.insertARecord(borrowAndRead);
        return row == 1 ? true : false;
    }

    @Override
    public boolean DeleteRecord(String Bid, String Pid) {
        int row = borrowAndReadMapper.deleteARecord(Bid, Pid);
        return row == 1 ? true : false;
    }

    @Override
    public List<BorrowAndRead> InquireBorrowAndRead(String Pid) {
        List<BorrowAndRead> list = borrowAndReadMapper.inquireBorrowAndRead(Pid);
        return list;
    }

    @Override
    public List<BorrowAndRead> InquireAllBorrowAndRead() {
        List<BorrowAndRead> list = borrowAndReadMapper.inquireAllBorrowAndRead();
        return list;
    }
}
