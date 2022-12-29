package com.wagba.ui.home.food;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wagba.R;
import com.wagba.databinding.FragmentFoodBinding;
import com.wagba.ui.viewmodels.DataViewModel;

public class FoodFragment extends Fragment {


    private FragmentFoodBinding binding;
    private DataViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodBinding.inflate(inflater , container , false);
        viewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
//        viewModel.getFoodByName(name);
        return binding.getRoot();
    }


}