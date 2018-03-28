package com.sc.noney.db.expense;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sc.noney.data.Expense;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/3/16.
 */

@Dao
public interface ExpenseDao {

    @Insert
    void bulkInsert(Expense... expense);

    @Query("SELECT * FROM expenses")
    Flowable<List<Expense>> getAll();

    @Query("SELECT * FROM expenses WHERE id = :id")
    Flowable<Expense> get(String id);

    @Delete
    void delete(Expense expense);
}
