package com.wagba.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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

        binding = FragmentPersonalInformationBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel.getUserFromDb();
        userViewModel.getUser().observe(requireActivity(), user -> {
            if (user != null)
                bind(user);
        });
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