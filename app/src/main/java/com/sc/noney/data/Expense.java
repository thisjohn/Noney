package com.sc.noney.data;

import android.databinding.ObservableField;

/**
 * Created by sclee on 2018/1/19.
 */

public class Expense {

    public ObservableField<String> id = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> detail = new ObservableField<>();

    public Expense(String id, String content, String detail) {
        this.id.set(id);
        this.content.set(content);
        this.detail.set(detail);
    }

    @Override
    public String toString() {
        return content.get();
    }
}
