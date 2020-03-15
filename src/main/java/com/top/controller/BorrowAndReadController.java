package com.top.controller;

import com.top.pojo.Book;
import com.top.pojo.BorrowAndRead;
import com.top.pojo.User;
import com.top.service.BookService;
import com.top.service.BorrowAndReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("resources/borrowAndRead")
public class BorrowAndReadController {

    @Autowired
    private BorrowAndReadService borrowAndReadService;

    @Autowired
    private BookService bookService;

    /**
     * 添加借阅记录(借阅图书)
     * */
    @RequestMapping("/addBorrowAndRead")
    @ResponseBody
    public Map<String,String> addBorrowAndRead(@RequestBody BorrowAndRead borrowAndRead){

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        borrowAndRead.setLendTime((sdf.format(date)));

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        date = cal.getTime();
        borrowAndRead.setReturnTime(sdf.format(date));

        boolean success;
        Map<String, String > map = new HashMap<>();
        success = borrowAndReadService.AddRecord(borrowAndRead);

        if(success == true) {
            map.put("add","success");
            /**
             * 借阅成功将该图书借阅状态置1
             * */
            System.out.println("add BorrowAndRead success");
            Book book = bookService.GetBook(borrowAndRead.getBorrowBookId());
            book.setLendState(true);
            bookService.UpdateBook(book);
        }
        else {
            map.put("add","fail");
            System.out.println("add BorrowAndRead fail");
        }
        return map;
    }

    /**
     * 删除借阅记录(归还图书)
     * */
    @RequestMapping("/deleteBorrowAndRead")
    @ResponseBody
    public Map<String,String> deleteBorrowAndRead(@RequestBody BorrowAndRead borrowAndRead, HttpServletRequest request){
        System.out.println("delete A borrowAndRead ");
        System.out.println(borrowAndRead.getBorrowBookId());

        HttpSession session = request.getSession();
        User userold = (User)session.getAttribute("SESSION_USER");
        boolean success;
        borrowAndRead.setBorrowPersonId(userold.getId());
        success = borrowAndReadService.DeleteRecord(borrowAndRead.getBorrowBookId(),borrowAndRead.getBorrowPersonId());


        Map<String, String > map = new HashMap<>();
        if(success){
            map.put("delete","success");
            /**
             * 归还成功将借阅状态置0
             * */
            Book book = bookService.GetBook(borrowAndRead.getBorrowBookId());
            book.setLendState(false);
            bookService.UpdateBook(book);
            System.out.println("delete borrowAndRead success");
        }
        else{
            map.put("delete","success");
            System.out.println("delete borrowAndRead fail");
        }
        return map;
    }

    /**
     * 查询个人借阅信息
     * */
    @RequestMapping("/inquireBorrowAndRead")
    public ModelAndView inquireBorrowAndRead(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();


        User user = (User) session.getAttribute("SESSION_USER");

        List<BorrowAndRead> borrowAndReadList = borrowAndReadService.InquireBorrowAndRead(user.getId());

        if (borrowAndReadList != null) {
            System.out.println("inquire borrowAndReadList success");

            session.setAttribute("borrowBookList", borrowAndReadList);


        } else {
            System.out.println("inquire borrowAndReadList failure");

        }

        modelAndView.setViewName("page/r-manageBook");
        return modelAndView;
    }


    /**
     * 查询所有借阅信息
     * */
    @RequestMapping("/inquireAllBorrowAndRead")
    @ResponseBody
    public Map<String, String> inquireAllBorrowAndRead(@RequestBody BorrowAndRead borrowAndRead, HttpSession session) {
        Map<String, String> map = new HashMap<>();

        User user = (User) session.getAttribute("SESSION_USER");

        List<BorrowAndRead> AllBorrowAndReadList = borrowAndReadService.InquireAllBorrowAndRead();

        if (AllBorrowAndReadList != null) {
            System.out.println("inquire AllBorrowAndReadList success");

            session.setAttribute("AllBorrowAndReadList", AllBorrowAndReadList);

            map.put("inquireAllBorrowAndReadList", "success");
        } else {
            System.out.println("inquire All!!BorrowAndReadList failure");
            map.put("inquireAllBorrowAndReadList", "failure");
        }
        return map;
    }
}

