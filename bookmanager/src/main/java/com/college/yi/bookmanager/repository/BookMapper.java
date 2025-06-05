package com.college.yi.bookmanager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.college.yi.bookmanager.entity.BookEntity;

@Mapper
public interface BookMapper {
    // 書籍一覧取得
    List<BookEntity> findAll();

    // 書籍登録
    void insert(BookEntity book);

    // 書籍IDで検索（更新・削除前に対象書籍の存在を確認）
    BookEntity findById(int id);

    // 書籍更新
    void update(BookEntity book);

    // 書籍削除（ID指定）
    void delete(int id); // ←★この行を追加
}
