package com.wagba.ui.profile;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.GetTokenResult;
import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.data.repositories.UserRepository;
import com.wagba.databinding.FragmentPersonalInformationBinding;
import com.wagba.ui.viewmodels.AuthenticationViewModel;
import com.wagba.ui.viewmodels.UserViewModel;


public class PersonalInformation extends Fragment {
    AuthenticationViewModel authenticationViewModel;
    UserViewModel userViewModel;
    FragmentPersonalInformationBinding binding ;
    FirebaseUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      authenticationViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
    //  userViewModel= new ViewModelProvider(this,ViewModelProvider.Factory.from(UserViewModelFactory)).get(UserViewModel(UserRepository).class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         user =  FirebaseHelper.getCurrentUser();

        binding = FragmentPersonalInformationBinding.inflate(inflater,container,false);
        if (user !=null)
            bind();
        return binding.getRoot();
    }
    private void bind(){
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