package com.wagba.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wagba.R;
import com.wagba.databinding.FragmentHomeBinding;
import com.wagba.ui.adapters.RestaurantsAdapter;
import com.wagba.ui.helpers.NavigationHelper;
import com.wagba.ui.helpers.ProgressHelper;
import com.wagba.ui.viewmodels.DataViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DataViewModel viewModel;
    private RestaurantsAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        NavigationHelper.setActiveIcon(R.id.HomeItem);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        adapter = new RestaurantsAdapter(requireContext());
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgressHelper.show(requireContext());
        viewModel.fetchRestaurantsData();
        binding.restaurantRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.restaurantRV.setAdapter(adapter);

        viewModel.getRestaurants().observe(requireActivity(), restaurants -> {
            adapter.setRestaurants(restaurants);
            adapter.notifyDataSetChanged();
            ProgressHelper.dismiss();
        });

        viewModel.getError().observe(requireActivity(), error -> {
            if (error){
                Toast.makeText(requireContext(),"Error occurred" , Toast.LENGTH_SHORT).show();
            }
        });

        binding.toCartFAB.setOnClickListener(view3 -> NavigationHelper.navigate(R.id.cartFragment));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}