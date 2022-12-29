package com.wagba.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wagba.data.db.UserDb;
import com.wagba.data.models.User;
import com.wagba.data.repositories.UserRepository;
import com.wagba.databinding.FragmentPersonalInformationBinding;
import com.wagba.ui.viewmodels.AuthenticationViewModel;
import com.wagba.ui.viewmodels.UserViewModel;
import com.wagba.ui.viewmodels.UserViewModelFactory;


public class PersonalInformation extends Fragment {
    UserViewModel userViewModel;
    FragmentPersonalInformationBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserViewModelFactory viewModelFactory = new UserViewModelFactory(requireContext());
        userViewModel = new ViewModelProvider(this,
                viewModelFactory)
                .get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        User user = userViewModel.getUser().getValue();

        binding = FragmentPersonalInformationBinding.inflate(inflater, container, false);
        if (user != null)
            bind(user);
        return binding.getRoot();
    }

    private void bind(User user) {
        binding.usernameTv.setText(user.getUsername());
        binding.userEmailTv.setText(user.getEmail());
        binding.mobileNumTv.setText(user.getPhoneNumber());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}