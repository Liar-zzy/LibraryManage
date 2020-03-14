package com.top.service.impl;

import com.top.mapper.ReaderMapper;
import com.top.pojo.Reader;
import com.top.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ReaderService")
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderMapper readerMapper;
    
    @Override
    public boolean AddReader(Reader reader) {
        int row = readerMapper.addAReader(reader);
        return row == 1 ? true : false;
    }

    @Override
    public boolean DeleteReader(String Rid) {
        int row = readerMapper.deleteAReader(Rid);
        return row == 1 ? true : false;
    }

    @Override
    public boolean UpdateReader(Reader reader) {
        int row = readerMapper.modifyAReader(reader);
        return row == 1 ? true : false;
    }

    @Override
    public Reader GetReader(String Rid) {
        Reader reader = readerMapper.getAReader(Rid);
        return reader;
    }
    
}
