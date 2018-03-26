package com.sc.noney.source;

import com.sc.noney.data.Expense;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/1/20.
 */

public class ExpenseDummyDataSource implements ExpenseDataSource {

    private static ExpenseDummyDataSource instance;

    synchronized public static ExpenseDummyDataSource getInstance() {
        if (instance == null) {
            instance = new ExpenseDummyDataSource();
        }
        return instance;
    }

    private ExpenseDummyDataSource() {}

    @Override
    public Flowable<List<Expense>> getExpenses() {
        List<Expense> expenses = Arrays.asList(
                new Expense("A", "a1", "apple"),
                new Expense("B", "b3", "banana"),
                new Expense("C", "c2", "carambola")
        );
        return Flowable.fromArray(expenses);
    }
}
