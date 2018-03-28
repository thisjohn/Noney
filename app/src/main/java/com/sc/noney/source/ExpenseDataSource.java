package com.sc.noney.source;

import com.sc.noney.data.Expense;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/1/19.
 */

public interface ExpenseDataSource {

    void insertOrUpdateExpense(Expense expense);

    Flowable<List<Expense>> getExpenses();
}
