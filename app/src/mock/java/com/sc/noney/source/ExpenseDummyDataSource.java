package com.sc.noney.source;

import com.sc.noney.data.Expense;

import java.util.Arrays;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

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

    private List<Expense> expenses;
    private Subject<List<Expense>> expensesSubject;

    private ExpenseDummyDataSource() {
        expenses = Arrays.asList(
                new Expense("A", "a1", "apple"),
                new Expense("B", "b3", "banana"),
                new Expense("C", "c2", "carambola")
        );
        expensesSubject = BehaviorSubject.createDefault(expenses);
    }

    @Override
    public void insertOrUpdateExpense(Expense expense) {
        expenses.add(expense);
        expensesSubject.onNext(expenses);
    }

    @Override
    public Flowable<List<Expense>> getExpenses() {
        return expensesSubject.toFlowable(BackpressureStrategy.BUFFER);
    }
}
