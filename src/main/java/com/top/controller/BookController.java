package com.top.controller;

import com.top.pojo.Book;
import com.top.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 添加图书
     * */
    @RequestMapping("/addBook")
    @ResponseBody
    public Map<String,String> addBook(@RequestBody Book book){

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        book.setPublishTime((sdf.format(date)));
        book.setRegisterTime((sdf.format(date)));
        book.setLendState(false);

        boolean success;
        Map<String, String > map = new HashMap<>();
        success = bookService.AddBook(book);

        if(success == true) {
            map.put("add","success");
            System.out.println("add book success");
        }
        else {
            map.put("add","fail");
            System.out.println("add book fail");
        }
        return map;
    }

    /**
     * 删除图书
     * */
    @RequestMapping("/deleteBook")
    @ResponseBody
    public Map<String,String> deleteBook(@RequestBody Book book){
        System.out.println("delete A book ");
        System.out.println(book.getId()+"name: "+book.getName());

        boolean success;
        success = bookService.DeleteBook(book.getId());

        Map<String, String > map = new HashMap<>();
        if(success){
            map.put("delete","success");
            System.out.println("delete book success");
        }
        else{
            map.put("delete","success");
            System.out.println("delete book fail");
        }
        return map;
    }

    /**
     * 修改图书信息
     * */
    @RequestMapping("/updateBook")
    @ResponseBody
    public Map<String,String> updateBook(@RequestBody Book book){
        System.out.println("update A book ");
        System.out.println(book.getId() + book.getName());

        boolean success;
        success = bookService.UpdateBook(book);
        Map<String, String > map = new HashMap<>();

        if(success == true) {
            map.put("update","success");
            System.out.println("update book success");
        } else {
            map.put("update","fail");
            System.out.println("update book fail");
        }
        return map;
    }


    /**
     * 查询图书
     * */
    @RequestMapping("/inquireBook")
    @ResponseBody
    public Map<String, String> inquireBook(@RequestBody Book book, HttpSession session) {
        Map<String, String> map = new HashMap<>();

        System.out.println("传入的 Book Id :   " + book.getId());

        book = bookService.GetBook(book.getId());

        if (book != null) {
            System.out.println("inquire success");

            session.setAttribute("SESSION_BOOK", book);

            map.put("inquireBook", "success");
        } else {
            System.out.println("inquire failure");
            map.put("inquireBook", "failure");
        }
        return map;
    }

}
