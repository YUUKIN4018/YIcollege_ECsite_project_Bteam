package com.college.yi.bookmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 書籍一覧取得（GET）
    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    // 書籍登録（POST）
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.registerBook(book));
    }

    // 書籍更新（PUT）
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    // 書籍削除（DELETE）
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
