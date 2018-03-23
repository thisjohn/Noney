package com.sc.noney;

import android.content.Context;
import android.support.annotation.NonNull;

import com.sc.noney.source.ExpenseRepository;

/**
 * Created by sclee on 2018/3/23.
 */

public class Injector {

    public static ExpenseRepository provideExpenseRepository(@NonNull Context context) {
        return ExpenseRepository.getInstance(context);
    }
}
