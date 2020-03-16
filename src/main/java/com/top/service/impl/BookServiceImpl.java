package com.top.service.impl;

import com.top.mapper.BookMapper;
import com.top.pojo.Book;
import com.top.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean AddBook(Book book) {
        int row = bookMapper.addABook(book);
        return row == 1 ? true : false;
    }

    @Override
    public boolean DeleteBook(String Bid) {
        int row = bookMapper.deleteABook(Bid);
        return row == 1 ? true : false;
    }

    @Override
    public boolean UpdateBook(Book book) {
        int row = bookMapper.modifyABook(book);
        return row == 1 ? true : false;
    }

    @Override
    public Book GetBook(String Bid) {
        Book book = bookMapper.getABook(Bid);
        return book;
    }

    @Override
    public List<Book> GetAllBook() {
        List<Book> bookList = bookMapper.getAllBook();
        return bookList;
    }

    @Override
    public List<Book> getBookByName(String name) {
        List<Book> books = bookMapper.getBookByName(name);
        return books;
    }


}
