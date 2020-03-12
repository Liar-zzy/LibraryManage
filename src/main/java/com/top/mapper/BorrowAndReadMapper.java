package com.top.mapper;

import com.top.pojo.BorrowAndRead;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowAndReadMapper {

    /**
     * 插入借阅记录
     * */
    int insertARecord(@Param("addRecord")BorrowAndRead borrowAndRead);

    /**
     * 删除借阅记录
     * */
    int deleteARecord(@Param("Bid")String Bid, @Param("Pid")String Pid);

}
