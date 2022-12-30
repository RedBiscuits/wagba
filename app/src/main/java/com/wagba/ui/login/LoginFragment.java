package com.wagba.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.wagba.R;
import com.wagba.databinding.FragmentLoginBinding;
import com.wagba.ui.viewmodels.AuthenticationViewModel;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private AuthenticationViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        viewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);


        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailFocusListener();
        passwordFocusListener();
        binding.loginBtn.setOnClickListener(view12 -> {
            validateInput();
            submitLogin();
        });
        binding.registerText.setOnClickListener(view1 ->
                Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main)
                        .navigate(R.id.action_FirstFragment_to_registerFragment));

        viewModel.getlogin().observe(requireActivity(), result -> loginResponse(result));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void loginResponse(Boolean it) {
        if (it) {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_SecondFragment);
        } else{
            Toast.makeText(
                    requireContext(),
                    "Wrong credentials, try registering if your are not a registered user :)",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void submitLogin() {
        Boolean validEmail = binding.emailContainer.getHelperText() == null;
        Boolean validPassword = binding.passwordContainer.getHelperText() == null;
        if (validEmail && validPassword) {
            viewModel.loginUser(
                    Objects.requireNonNull(binding.emailEdittext.getText()).toString(),
                    Objects.requireNonNull(binding.passwordEdittext.getText()).toString()
            );
        } else {
            validateInput();
        }

    }

    private void validateInput(){
        binding.emailContainer.setHelperText(viewModel.validEmail(binding.emailEdittext.getText().toString())) ;
        binding.passwordContainer.setHelperText(viewModel.validPassword(binding.passwordEdittext.getText().toString()));
    }
    private void emailFocusListener() {
        binding.emailEdittext.setOnFocusChangeListener((view, b) -> {
            if (!b)
                binding.emailContainer.setHelperText(viewModel.validEmail(binding.emailEdittext.getText().toString()));
        });
    }

    private void passwordFocusListener() {
        binding.passwordEdittext.setOnFocusChangeListener((view, b) -> {
            if (!b)
                binding.passwordContainer.setHelperText( viewModel.validPassword(binding.passwordEdittext.getText().toString()));

        });
    }


}