package com.wagba.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.wagba.R;
import com.wagba.data.db.UserDb;
import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.data.models.User;
import com.wagba.databinding.FragmentProfileBinding;
import com.wagba.ui.helpers.CartContentHelper;
import com.wagba.ui.helpers.NavigationHelper;
import com.wagba.ui.viewmodels.AuthenticationViewModel;


public class Profile extends Fragment {

    private FragmentProfileBinding binding;
    private User user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.logoutBtn.setOnClickListener(view -> {
            FirebaseHelper.logoutUser();
            UserDb.getDatabase(requireContext()).userDao().logoutUser();
            NavigationHelper.navigate(R.id.action_profile_to_FirstFragment);
        });
        binding.walletAmountTv.setText(Long.toString(CartContentHelper.getWallet()));

         user = UserDb.getDatabase(requireContext()).userDao().getUser();

        if (user.getEmail() != null)
            binding.porderEmailTV.setText(user.getEmail());

        if (user.getUsername() != null)
            binding.porderUsernameTV.setText(user.getUsername());

        return binding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        binding.walletAmountTv.setText(Long.toString(CartContentHelper.getWallet()));
    }
}