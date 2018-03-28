package com.sc.noney.ui.expense;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;

import com.sc.noney.data.Expense;
import com.sc.noney.source.ExpenseRepository;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sclee on 2018/3/20.
 */

public class ExpensesViewModel extends AndroidViewModel {

    private ExpenseRepository expenseRepo;

    public ExpensesViewModel(Application application, ExpenseRepository expenseRepo) {
        super(application);
        this.expenseRepo = expenseRepo;
    }

    public LiveData<List<Expense>> getExpenses() {
        Flowable<List<Expense>> flowable = this.expenseRepo.getExpenses();
        return LiveDataReactiveStreams.fromPublisher(flowable);
    }
}
