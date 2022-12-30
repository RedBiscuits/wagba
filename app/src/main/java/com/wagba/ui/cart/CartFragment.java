package com.wagba.ui.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wagba.R;
import com.wagba.data.models.CartItem;
import com.wagba.databinding.FragmentCartBinding;
import com.wagba.ui.adapters.CartAdapter;
import com.wagba.ui.adapters.FoodAdapter;
import com.wagba.ui.helpers.CartContentHelper;
import com.wagba.ui.helpers.ProgressHelper;
import com.wagba.ui.viewmodels.DataViewModel;

public class CartFragment extends Fragment {

    FragmentCartBinding binding;
    private CartAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater , container , false);
        adapter = new CartAdapter(requireContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgressHelper.show(requireContext());
        binding.cartRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.setCartItems(CartContentHelper.getContent());
        binding.cartRV.setAdapter(adapter);
        if (adapter.getItemCount() == 0){
            binding.textNoCartItems.setVisibility(View.VISIBLE);
        }else{
            binding.textNoCartItems.setVisibility(View.GONE);
        }
        ProgressHelper.dismiss();
        binding.cartReturn.setOnClickListener(view1 -> requireActivity().onBackPressed());
        binding.cartProceedBtn.setOnClickListener(view3 -> {
            if(binding.textNoCartItems.getVisibility() == View.VISIBLE){
                Toast.makeText(requireContext() , "Please add items to cart first." , Toast.LENGTH_LONG).show();
            }else {
                Long total =0L;
                for (CartItem item : CartContentHelper.getContent()){
                    total += item.getTotalPrice();
                }
                if(total <= 0L){
                    Toast.makeText(requireContext() , "Please specify quantity." , Toast.LENGTH_LONG).show();
                }else {

                }
            }

        });

    }
}