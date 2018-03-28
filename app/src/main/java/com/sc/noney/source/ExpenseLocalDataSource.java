package com.sc.noney.source;

import android.content.Context;

import com.sc.noney.data.Expense;
import com.sc.noney.db.AppDatabase;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/3/20.
 */

public class ExpenseLocalDataSource implements ExpenseDataSource {

    private static ExpenseLocalDataSource instance;

    synchronized public static ExpenseLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new ExpenseLocalDataSource(context);
        }
        return instance;
    }

    private AppDatabase appDatabase;

    private ExpenseLocalDataSource(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    @Override
    public Flowable<List<Expense>> getExpenses() {
        return appDatabase.expenseDao().getAll();
    }
}
