package com.sc.noney.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by sclee on 2018/1/19.
 */

@Entity(tableName = "expenses")
public class Expense {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "detail")
    public String detail;

    public Expense(@NonNull String id, String content, String detail) {
        this.id = id;
        this.content = content;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return content;
    }
}
