package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Danh sách sách
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    // Hiện form thêm
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    // Xử lý thêm
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    // Hiện form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        bookService.getBookById(id)
                .ifPresent(book -> model.addAttribute("book", book));
        return "edit-book";
    }

    // Xử lý cập nhật
    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
