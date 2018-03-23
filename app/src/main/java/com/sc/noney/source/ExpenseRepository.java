package com.sc.noney.source;

import android.content.Context;

import com.sc.noney.data.Expense;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/1/19.
 */

public class ExpenseRepository {

    private static ExpenseRepository instance;

    synchronized public static ExpenseRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ExpenseRepository(context);
        }
        return instance;
    }

    private ExpenseDataSource dataSource;

    private ExpenseRepository(Context context) {
        //dataSource = ExpenseBriteDataSource.getInstance(context);
        dataSource = ExpenseDummyDataSource.getInstance();
    }

    public Flowable<List<Expense>> getExpenses() {
        return dataSource.getExpenses();
    }
}
