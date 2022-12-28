package com.wagba.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;
import com.wagba.R;
import com.wagba.databinding.FragmentProfileBinding;
import com.wagba.ui.viewmodels.AuthenticationViewModel;


public class Profile extends Fragment {

    FragmentProfileBinding binding;
    AuthenticationViewModel authenticationViewModel;
    public Profile() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authenticationViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.imageButton.setOnClickListener(view -> Navigation.findNavController(requireActivity(),R.id.action_profile_to_personalInformation));
        binding.profielTv.setOnClickListener(view -> Navigation.findNavController(requireActivity(),R.id.action_profile_to_personalInformation));
        return binding.getRoot();
    }

}