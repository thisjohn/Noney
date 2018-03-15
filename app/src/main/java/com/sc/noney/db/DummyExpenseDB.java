package com.sc.noney.db;

import com.sc.noney.data.Expense;
import com.sc.noney.model.ExpenseDataSource;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/1/20.
 */

public class DummyExpenseDB implements ExpenseDataSource {

    private static DummyExpenseDB instance;

    synchronized public static DummyExpenseDB getInstance() {
        if (instance == null) {
            instance = new DummyExpenseDB();
        }
        return instance;
    }

    private DummyExpenseDB() {}

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
