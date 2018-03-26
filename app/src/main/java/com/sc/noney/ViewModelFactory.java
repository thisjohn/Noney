package com.sc.noney;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.sc.noney.source.ExpenseRepository;
import com.sc.noney.ui.expense.ExpensesViewModel;

/**
 * Created by sclee on 2018/3/23.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static ViewModelFactory instance;

    synchronized public static ViewModelFactory getInstance(Application application) {
        if (instance == null) {
            ExpenseRepository expenseRepo = Injector.provideExpenseRepository(application.getApplicationContext());
            instance = new ViewModelFactory(application, expenseRepo);
        }
        return instance;
    }

    private Application application;
    private ExpenseRepository expenseRepo;

    private ViewModelFactory(Application application, ExpenseRepository expenseRepo) {
        this.application = application;
        this.expenseRepo = expenseRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ExpensesViewModel.class)) {
            return (T) new ExpensesViewModel(application, expenseRepo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
