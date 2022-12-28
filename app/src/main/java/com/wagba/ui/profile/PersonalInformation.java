package com.wagba.ui.profile;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;

import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.databinding.FragmentPersonalInformationBinding;
import com.wagba.ui.viewmodels.AuthenticationViewModel;



public class PersonalInformation extends Fragment {
    AuthenticationViewModel authenticationViewModel;
    FragmentPersonalInformationBinding binding ;
    public PersonalInformation() {
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
        FirebaseUser user =  FirebaseHelper.getCurrentUser();

        binding = FragmentPersonalInformationBinding.inflate(inflater,container,false);
        if (user !=null)
            bind(user);
        return binding.getRoot();
    }
    private void bind(FirebaseUser user){
        binding.usernameTv.setText(user.getDisplayName());
        binding.userEmailTv.setText(user.getEmail());
        if (!user.isEmailVerified()) {
            binding.emailVerfTv.setVisibility(View.VISIBLE);
        }

       binding.mobileNumTv.setText(user.getPhoneNumber());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}