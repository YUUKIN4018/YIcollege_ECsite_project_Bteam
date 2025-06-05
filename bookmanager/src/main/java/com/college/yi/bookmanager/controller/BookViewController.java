package com.college.yi.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookViewController {

    @GetMapping("/books")
    public String showBookListPage() {
        return "index";  // → templates/index.html を表示
    }
}
