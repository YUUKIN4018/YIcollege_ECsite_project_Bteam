package com.college.yi.ecsite.exception;

public class StockUpdateException extends RuntimeException{
    //エラー発生時にメッセージを受け取るコンストラクタ
    public StockUpdateException(String message) {
        super(message);
    }
}
