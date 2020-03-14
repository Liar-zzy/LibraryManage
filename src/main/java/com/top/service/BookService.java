package com.top.service;

import com.top.pojo.Book;

public interface BookService {

    /**
     * 图书插入
     * */
    boolean AddBook(Book book);

    /**
     * 图书删除
     * */
    boolean DeleteBook(String Bid);

    /**
     * 更新图书
     * */
    boolean UpdateBook(Book book);

    /**
     * 查询图书
     * */
    Book GetBook(String Bid);
}
