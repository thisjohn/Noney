package com.sc.noney.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by sclee on 2018/1/20.
 */

public class LocalDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "noney.db";
    private static final int DB_VERSION = 1;

    private static LocalDB instance = null;

    synchronized public static LocalDB getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDB(context);
        }
        return instance;
    }

    private BriteDatabase briteDB;

    private LocalDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        briteDB = sqlBrite.wrapDatabaseHelper(this, Schedulers.io());
    }

    // -- SQLiteOpenHelper

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE expenses" +
                "(" +
                    "id TEXT PRIMARY KEY," +
                    "content TEXT," +
                    "details TEXT" +
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // --

    public BriteDatabase getDB() {
        return briteDB;
    }
}
