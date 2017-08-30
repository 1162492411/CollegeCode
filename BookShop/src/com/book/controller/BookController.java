package com.book.controller;

import com.book.pojo.Book;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    //前往查看图书页面
    @RequestMapping(method = RequestMethod.GET)
    public String toBooks(Model model) {
        model.addAttribute("books",this.bookService.selectAll());
        return "book/books";
    }

    //前往添加图书页面
    @RequestMapping(value = "add")
    public String toAddBook(){
        return "book/add";
    }

    //添加图书
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String addBook(@RequestBody Book book){
        if(this.bookService.add(book)) return "suc";
        else return "err";
    }

    //前往修改图书页面
    @RequestMapping("{id}/update")
    public String toUpdate(@PathVariable int id, Model model){
        model.addAttribute("book",bookService.selectById(id));
        return "book/update";
    }

    //修改图书
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody String updateBook(@RequestBody Book book){
        if(this.bookService.update(book)) return "suc";
        else return "err";
    }

    //获取所有图书信息
    @RequestMapping(value = "all")
    public @ResponseBody List getAllBooks(){
        return this.bookService.selectAll();
    }

    //查看图书详细信息
    @RequestMapping("{id}")
    public String toBookDetail(@PathVariable int id, Model model){
        model.addAttribute("book",this.bookService.selectById(id));
        return "book/detail";
    }

}
