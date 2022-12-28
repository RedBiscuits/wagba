package com.wagba.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wagba.data.models.Restaurant;
import com.wagba.databinding.FragmentHomeBinding;
import com.wagba.ui.adapters.RestaurantsAdapter;
import com.wagba.ui.viewmodels.DataViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DataViewModel viewModel;
    private RestaurantsAdapter adapter;
    ProgressDialog progress;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        adapter = new RestaurantsAdapter(requireContext());
        progress = new ProgressDialog(requireContext());
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showProgress();
        viewModel.fetchRestaurantsData();
        binding.restaurantRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.restaurantRV.setAdapter(adapter);

        viewModel.getRestaurants().observe(requireActivity(), restaurants -> {
            adapter.setRestaurants(restaurants);
            adapter.notifyDataSetChanged();
            progress.dismiss();
        });

        viewModel.getError().observe(requireActivity(), error -> {
            if (error){
                Toast.makeText(requireContext(),"Error occurred" , Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showProgress(){
        progress.setMessage("Loading");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }
}