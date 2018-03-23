package com.sc.noney.ui.expense;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.sc.noney.data.Expense;
import com.sc.noney.source.ExpenseRepository;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/3/20.
 */

public class ExpensesViewModel extends AndroidViewModel {

    private Context context;

    private ExpenseRepository expenseRepo;
    private LiveData<List<Expense>> expenses;

    public ExpensesViewModel(Application application, ExpenseRepository expenseRepo) {
        super(application);
        this.expenseRepo = expenseRepo;
        context = application.getApplicationContext();

        Flowable<List<Expense>> flowable = expenseRepo.getExpenses();
        expenses = LiveDataReactiveStreams.fromPublisher(flowable);
    }

    public LiveData<List<Expense>> getExpenses() {
        return expenses;
    }
}
