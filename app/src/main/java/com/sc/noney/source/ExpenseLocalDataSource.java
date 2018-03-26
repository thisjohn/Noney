package com.sc.noney.source;

import com.sc.noney.data.Expense;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/3/20.
 */

public class ExpenseLocalDataSource implements ExpenseDataSource {

    @Override
    public Flowable<List<Expense>> getExpenses() {
        return null;
    }
}
