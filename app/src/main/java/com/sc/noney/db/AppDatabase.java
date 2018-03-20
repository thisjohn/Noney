package com.sc.noney.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.sc.noney.data.Expense;
import com.sc.noney.db.expense.ExpenseDao;

/**
 * Created by sclee on 2018/3/20.
 */

@Database(entities = {Expense.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "noney.db";

    private static AppDatabase instance = null;

    public abstract ExpenseDao expenseDao();

    synchronized public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME).build();
        }
        return instance;
    }
}
