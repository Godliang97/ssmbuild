package com.kuang.controller;

import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    //controller 调用service层
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    //查询全部的书籍，并且返回到一个书籍展示页面
    @RequestMapping(value = "/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);

        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping(value = "/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    //添加书籍的请求
    @RequestMapping(value = "/addBook")
    public String addBook(Books books) {
        System.out.println("addBook:" + books);
        bookService.addBook(books);
        //重定向到我们的@RequestMapping(value = "/addBook")
        return "redirect:/book/allBook";
    }

    //修改书籍
    @RequestMapping(value = "/toUpdateBook")
    public String toUpdatePaper(Model model, Integer id) {
        Books books = bookService.queryBookById(id);
        model.addAttribute("book", books);
        return "updateBook";
    }

    @RequestMapping(value = "/updateBook")
    public String updateBook(Model model, Books book) {
        bookService.updateBook(book);
        Books books = bookService.queryBookById(book.getBookID());
        model.addAttribute("books", books);
        return "redirect:/book/allBook";

    }

    //删除书籍
    @RequestMapping(value = "/del/{bookId}")
    public String deleteBook(@PathVariable("bookId") Integer id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //查询书籍
    @RequestMapping(value = "/queryBook")
    public String queryBook(String queryBookName, Model model) {
        Books books = bookService.queryBookByName(queryBookName);
        List<Books> list = new ArrayList<Books>();
        list.add(books);

        if (books == null) {
            list = bookService.queryAllBook();
            model.addAttribute("error", "未查到");
        }

        model.addAttribute("list", list);
        return "allBook";
    }
}
