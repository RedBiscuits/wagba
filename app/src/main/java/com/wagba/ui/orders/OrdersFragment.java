package com.wagba.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wagba.databinding.FragmentOrdersBinding;
import com.wagba.ui.adapters.OrdersAdapter;
import com.wagba.ui.helpers.ProgressHelper;
import com.wagba.ui.viewmodels.DataViewModel;

import java.util.ArrayList;


public class OrdersFragment extends Fragment {

    private FragmentOrdersBinding binding;
    private OrdersAdapter adapter;
    private DataViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        adapter = new OrdersAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        viewModel.getPreviousOrders();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgressHelper.show(requireContext());
        binding.porderRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.setOrders(new ArrayList<>());
        binding.porderRV.setAdapter(adapter);

        viewModel.getOrderList().observe(requireActivity(), orders -> {
            adapter.setOrders(orders);
            adapter.notifyDataSetChanged();
            binding.noPrevOrders.setVisibility(View.GONE);
            ProgressHelper.dismiss();
        });

    }
}