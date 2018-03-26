package com.sc.noney.ui.expense;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sc.noney.data.Expense;
import com.sc.noney.databinding.ItemExpenseBinding;

import java.util.Collections;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Expense} and makes a call to the
 * specified {@link ExpensesFragment.OnInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ExpenseRecyclerViewAdapter extends RecyclerView.Adapter<ExpenseRecyclerViewAdapter.ViewHolder> {

    private List<Expense> items = Collections.emptyList();
    private final ExpensesFragment.OnInteractionListener onInteractionListener;

    public ExpenseRecyclerViewAdapter(Context context, ExpensesFragment.OnInteractionListener listener) {
        onInteractionListener = listener;
    }

    public void setExpenses(List<Expense> items) {
        this.items = items;
        notifyDataSetChanged();;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemExpenseBinding binding = ItemExpenseBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Expense item = items.get(position);
        View.OnClickListener onClickListener = v -> {
            if (null != onInteractionListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                onInteractionListener.onInteraction(item);
            }
        };

        holder.bind(item, onClickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemExpenseBinding binding;

        ViewHolder(ItemExpenseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Expense item, View.OnClickListener onClickListener) {
            binding.setExpense(item);
            binding.setOnClickListener(onClickListener);
            binding.executePendingBindings();
        }

        @Override
        public String toString() {
            return super.toString() + " '" + binding.getExpense().content + "'";
        }
    }
}
