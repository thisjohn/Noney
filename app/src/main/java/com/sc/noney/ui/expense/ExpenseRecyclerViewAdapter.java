package com.sc.noney.ui.expense;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sc.noney.R;
import com.sc.noney.dto.Expense;
import com.sc.noney.model.ExpenseRepository;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Expense} and makes a call to the
 * specified {@link ExpensesFragment.OnInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ExpenseRecyclerViewAdapter extends RecyclerView.Adapter<ExpenseRecyclerViewAdapter.ViewHolder> {

    private final ExpensesFragment.OnInteractionListener onInteractionListener;
    private List<Expense> items = Collections.emptyList();

    public ExpenseRecyclerViewAdapter(Context context, ExpensesFragment.OnInteractionListener listener) {
        onInteractionListener = listener;

        ExpenseRepository.getInstance(context).getExpenses().subscribe(it -> {
            items = it;
            notifyDataSetChanged();
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_expense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = items.get(position);
        holder.idView.setText(items.get(position).id);
        holder.contentView.setText(items.get(position).content);

        holder.view.setOnClickListener(view -> {
            if (null != onInteractionListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                onInteractionListener.onInteraction(holder.item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final View view;
        @BindView(R.id.id) TextView idView;
        @BindView(R.id.content) TextView contentView;

        Expense item;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            this.view = view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}
