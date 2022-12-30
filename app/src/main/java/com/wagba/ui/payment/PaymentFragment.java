package com.wagba.ui.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.wagba.R;
import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.data.models.Order;
import com.wagba.databinding.FragmentPaymentBinding;
import com.wagba.ui.helpers.CartContentHelper;
import com.wagba.ui.helpers.NavigationHelper;

import java.util.Date;

public class PaymentFragment extends Fragment {
    FragmentPaymentBinding binding;
    Order order;
    Date date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        order = new Order();
        date = new Date();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //total cost
        binding.totalTV.setText(CartContentHelper.getTotal().toString());
        order.setAmount(CartContentHelper.getTotal().toString());

        //email
        binding.orderEmailTV.setText(FirebaseHelper.getCurrentUser().getEmail());
        order.setEmail(FirebaseHelper.getCurrentUser().getEmail());

        //date
        binding.orderDateTV.setText(Long.toString(date.getTime()));
        order.setDate(Long.toString(date.getTime()));

        binding.placeOrderBTN.setOnClickListener(view1 -> {
            int now = date.getHours();

            int locationId = binding.locationGroup.getCheckedRadioButtonId();
            int timeId = binding.timeGroup.getCheckedRadioButtonId();

            if (locationId == R.id.gate1_radio) {
                order.setLocation(binding.gate1Radio.getText().toString());
            } else if (locationId == R.id.gate2_radio) {
                order.setLocation(binding.gate2Radio.getText().toString());
            } else {
                Toast.makeText(requireContext(), "Please select a valid location.", Toast.LENGTH_LONG).show();
                return;
            }


            if (timeId == R.id.pm3_radio) {
                order.setTime(binding.pm3Radio.getText().toString());
                if (now > 13) {
                    System.out.println("3pm tomorrow ");
                    Toast.makeText(requireContext(), "Your order is placed for tomorrow.", Toast.LENGTH_LONG).show();
                }
            } else if (timeId == R.id.pm12_radio) {
                order.setTime(binding.pm12Radio.getText().toString());
                if (now > 10) {
                    System.out.println("3pm tomorrow ");
                    Toast.makeText(requireContext(), "Your order is placed for tomorrow.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(requireContext(), "Please select a valid time.", Toast.LENGTH_LONG).show();
                return;
            }


            FirebaseHelper.postOrder(order).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    NavigationHelper.navigate(R.id.SecondFragment);
                }else {
                    Toast.makeText(requireContext(), "Unknown error occurred.", Toast.LENGTH_LONG).show();
                }
            });
        });

    }
}