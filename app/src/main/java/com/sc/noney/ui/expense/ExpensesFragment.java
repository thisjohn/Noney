package com.sc.noney.ui.expense;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sc.noney.ViewModelFactory;
import com.sc.noney.data.Expense;
import com.sc.noney.databinding.FragmentExpensesBinding;

/**
 * A fragment representing a list of Items.
 * <p />
 * Activities containing this fragment MUST implement the {@link OnInteractionListener}
 * interface.
 */
public class ExpensesFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "ExpensesFragment.COLUMN_COUNT";

    public static ExpensesFragment newInstance(int columnCount) {
        ExpensesFragment fragment = new ExpensesFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);

        fragment.setArguments(args);
        return fragment;
    }

    private FragmentExpensesBinding binding;
    private ExpenseRecyclerViewAdapter adapter;

    private int columnCount = 1;
    private OnInteractionListener onInteractionListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ExpensesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExpensesBinding.inflate(inflater, container, false);

        setupViewModel();
        setupUI();

        subscribe();

        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInteractionListener) {
            onInteractionListener = (OnInteractionListener) context;
        }
        else {
            //throw new RuntimeException(context.toString() + " must implement OnInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onInteractionListener = null;
    }

    private void setupViewModel() {
        Application application = getActivity().getApplication();
        ViewModelFactory factory = ViewModelFactory.getInstance(application);

        ExpensesViewModel viewModel = ViewModelProviders.of(this, factory).get(ExpensesViewModel.class);

        binding.setViewModel(viewModel);
    }

    private void setupUI() {
        // List
        RecyclerView recyclerView = binding.list;

        Context context = recyclerView.getContext();
        if (columnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        adapter = new ExpenseRecyclerViewAdapter(context, onInteractionListener);
        recyclerView.setAdapter(adapter);
    }

    private void subscribe() {
        ExpensesViewModel viewModel = binding.getViewModel();
        viewModel.getExpenses().observe(this, it -> {
            if (adapter != null) {
                adapter.setExpenses(it);
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnInteractionListener {
        void onInteraction(Expense item);
    }
}
