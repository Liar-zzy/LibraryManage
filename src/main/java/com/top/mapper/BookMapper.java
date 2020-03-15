package com.top.mapper;

import com.top.pojo.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {

    /**
    * 图书插入
    * */
    int addABook(@Param("addBook")Book book);

    /**
     * 图书删除
     * */
    int deleteABook(@Param("deleteBook")String Bid);

    /**
     * 图书信息修改
     * */
    int modifyABook(@Param("modifyBook")Book book);

    /**
     * 查询图书信息
     * */
    Book getABook(@Param("getBook")String Bid);

    /**
     * 查询所有图书信息
     * */
    List<Book> getAllBook();

}
