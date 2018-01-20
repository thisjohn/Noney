package com.sc.noney.model;

import com.sc.noney.dto.Expense;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/1/19.
 */

public interface ExpenseDataSource {

    Flowable<List<Expense>> getExpenses();
}
