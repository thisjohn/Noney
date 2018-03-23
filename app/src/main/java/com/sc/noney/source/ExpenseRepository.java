package com.sc.noney.source;

import com.sc.noney.data.Expense;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/1/19.
 */

public class ExpenseRepository {

    private static ExpenseRepository instance;

    synchronized public static ExpenseRepository getInstance(ExpenseDataSource dataSource) {
        if (instance == null) {
            instance = new ExpenseRepository(dataSource);
        }
        return instance;
    }

    private ExpenseDataSource dataSource;

    private ExpenseRepository(ExpenseDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Flowable<List<Expense>> getExpenses() {
        return dataSource.getExpenses();
    }
}
