package com.top.controller;

import com.top.pojo.Book;
import com.top.pojo.User;
import com.top.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("resources/book")
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
        book.setLendState("0");

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
     * 查询所有图书
     * */
    @RequestMapping("/inquireAllBook")
    public ModelAndView inquireBook( HttpServletRequest request) {


        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("SESSION_USER");

        ModelAndView modelAndView = new ModelAndView();

        List<Book> bookList;

        bookList = bookService.GetAllBook();

        if (bookList != null) {

            System.out.println("inquire success");
            modelAndView.addObject("SESSION_BOOK", bookList);

        } else {
            System.out.println("inquire failure");

        }

        for(int i=0;i<bookList.size();i++){
            System.out.println(bookList.get(i).toString());
        }

        System.out.println("用户角色  :  " + user.getRole());
        if(user.getRole().equals("1"))
        {
            modelAndView.setViewName("page/m-getBook");
            System.out.println("Manager getBook");
        }
        else
        {
            modelAndView.setViewName("page/r-getBook");
            System.out.println("Reader getBook");
        }

        return modelAndView;
    }

}
