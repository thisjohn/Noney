package com.sc.noney.db.expense;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.sc.noney.data.Expense;
import com.sc.noney.db.BriteDB;
import com.sc.noney.source.ExpenseDataSource;
import com.squareup.sqlbrite2.BriteDatabase;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/1/20.
 */

public class BriteExpenseDB implements ExpenseDataSource {

    private static BriteExpenseDB instance;

    synchronized public static BriteExpenseDB getInstance(Context context) {
        if (instance == null) {
            instance = new BriteExpenseDB(context);
        }
        return instance;
    }

    private BriteDatabase briteDB;

    private BriteExpenseDB(Context context) {
        briteDB = BriteDB.getInstance(context).getDB();
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
