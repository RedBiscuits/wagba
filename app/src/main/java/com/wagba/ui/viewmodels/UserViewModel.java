package com.wagba.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.data.models.User;
import com.wagba.data.repositories.UserRepository;

public class UserViewModel extends ViewModel {
    UserRepository repository;
    private MutableLiveData<FirebaseUser> firebaseMutableLiveD = new MutableLiveData<>();

    LiveData<FirebaseUser> firebaseUserLiveD() {
        firebaseMutableLiveD.setValue(FirebaseHelper.getCurrentUser());
        return firebaseMutableLiveD;
    }

    public UserViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void addUser() {
        FirebaseUser user = firebaseMutableLiveD.getValue();
        if (user != null) {
            repository.addUser(new User(user.getEmail(),
                    user.getDisplayName(),
                    user.getUid(),
                    user.getIdToken(true).toString(),
                    user.getPhoneNumber()));
        } else
            Log.d("firebase user", "firebase user doesn't exist");
    }

}
