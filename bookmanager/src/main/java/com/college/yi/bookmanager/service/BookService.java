package com.college.yi.bookmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.college.yi.bookmanager.entity.BookEntity;
import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.repository.BookMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    // 書籍一覧取得
    public List<Book> getAllBooks() {
        List<BookEntity> entities = bookMapper.findAll();

        if (entities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "書籍が存在しません。");
        }

        return entities.stream()
                .map(e -> new Book(
                        e.getId(),
                        e.getTitle(),
                        e.getAuthor(),
                        e.getPublisher(),
                        e.getPublishDate(),
                        e.getStock()))
                .collect(Collectors.toList());
    }

    // 書籍登録（POST）
    public Book registerBook(Book book) {
        BookEntity entity = new BookEntity();
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPublisher(book.getPublisher());
        entity.setPublishDate(book.getPublishDate());
        entity.setStock(book.getStock());

        bookMapper.insert(entity);

        book.setId(entity.getId());
        return book;
    }

    // 書籍更新（PUT）
    public Book updateBook(int id, Book book) {
        BookEntity existing = bookMapper.findById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "指定された書籍が見つかりません。");
        }

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setPublisher(book.getPublisher());
        existing.setPublishDate(book.getPublishDate());
        existing.setStock(book.getStock());

        bookMapper.update(existing);

        return new Book(
                existing.getId(),
                existing.getTitle(),
                existing.getAuthor(),
                existing.getPublisher(),
                existing.getPublishDate(),
                existing.getStock()
        );
    }

    // 書籍削除（DELETE）
    public void deleteBook(int id) {
        BookEntity existing = bookMapper.findById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "指定された書籍が見つかりません。");
        }

        bookMapper.delete(id);
    }
}
