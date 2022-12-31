package com.wagba.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wagba.R;
import com.wagba.data.models.User;
import com.wagba.databinding.FragmentPersonalInformationBinding;
import com.wagba.ui.viewmodels.AuthenticationViewModel;
import com.wagba.ui.viewmodels.UserViewModel;


public class PersonalInformation extends Fragment {
    UserViewModel userViewModel;
    FragmentPersonalInformationBinding binding;
    AuthenticationViewModel authenticationViewModel;
    User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(this)
                .get(UserViewModel.class);
        authenticationViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         user = userViewModel.getUser().getValue();

        binding = FragmentPersonalInformationBinding.inflate(inflater, container, false);
        if (user != null)
            bind(user);

        return binding.getRoot();
    }

    private void bind(User user) {
        binding.userNameET.setText(user.getUsername());
        binding.userEmailTv.setText(user.getEmail());
        binding.userPhoneTv.setText(user.getPhoneNumber());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void editFields(){
        binding.userNameET.setEnabled(true);
        binding.userEmailTv.setEnabled(true);
        binding.userPhoneTv.setEnabled(true);
    }
    private void emailFocusListener() {
        binding.userEmailTv.setOnFocusChangeListener((view, b) -> {
            if (!b)
                binding.emailContainer.setHelperText(authenticationViewModel.validEmail(binding.userEmailTv.getText().toString()));
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}