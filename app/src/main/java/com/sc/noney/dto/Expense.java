package com.sc.noney.dto;

/**
 * Created by sclee on 2018/1/19.
 */

public class Expense {

    public String id;
    public String content;
    public String detail;

    public Expense(String id, String content, String detail) {
        this.id = id;
        this.content = content;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return content;
    }
}
