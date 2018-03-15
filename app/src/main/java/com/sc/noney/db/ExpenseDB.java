package com.sc.noney.db;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.sc.noney.data.Expense;
import com.sc.noney.model.ExpenseDataSource;
import com.squareup.sqlbrite2.BriteDatabase;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/1/20.
 */

public class ExpenseDB implements ExpenseDataSource {

    private static ExpenseDB instance;

    synchronized public static ExpenseDB getInstance(Context context) {
        if (instance == null) {
            instance = new ExpenseDB(context);
        }
        return instance;
    }

    private BriteDatabase briteDB;

    private ExpenseDB(Context context) {
        briteDB = LocalDB.getInstance(context).getDB();
    }

    @NonNull
    private Expense mapper(Cursor c) {
        return new Expense(
                c.getString(c.getColumnIndexOrThrow("id")),
                c.getString(c.getColumnIndexOrThrow("content")),
                c.getString(c.getColumnIndexOrThrow("details"))
        );
    }

    public Flowable<List<Expense>> getExpenses() {
        String sql = "SELECT id, content, details FROM expenses";

        return briteDB.createQuery("expenses", sql)
                .mapToList(this::mapper)
                .toFlowable(BackpressureStrategy.BUFFER);
    }
}
