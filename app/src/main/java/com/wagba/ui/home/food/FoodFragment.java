package com.wagba.ui.home.food;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wagba.R;
import com.wagba.databinding.FragmentFoodBinding;
import com.wagba.databinding.FragmentHomeBinding;
import com.wagba.ui.adapters.FoodAdapter;
import com.wagba.ui.adapters.RestaurantsAdapter;
import com.wagba.ui.helpers.ProgressHelper;
import com.wagba.ui.viewmodels.DataViewModel;

public class FoodFragment extends Fragment {


    private FragmentFoodBinding binding;
    private DataViewModel viewModel;
    private FoodAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodBinding.inflate(inflater , container , false);
        viewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        adapter = new FoodAdapter(requireContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgressHelper.show(requireContext());
        binding.textView2.setText(FoodFragmentArgs.fromBundle(getArguments()).getRestaurant().getName());
        viewModel.getFoodByName(FoodFragmentArgs.fromBundle(getArguments()).getRestaurant().getCategory());
        binding.foodRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.foodRV.setAdapter(adapter);

        viewModel.getFoodList().observe(requireActivity(), foodList -> {
            adapter.setFoodList(foodList);
            adapter.notifyDataSetChanged();
            ProgressHelper.dismiss();
        });

        viewModel.getError().observe(requireActivity(), error -> {
            if (error){
                Toast.makeText(requireContext(),"Error occurred" , Toast.LENGTH_SHORT).show();
            }
        });

        binding.foodReturn.setOnClickListener(view1 -> requireActivity().onBackPressed());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}