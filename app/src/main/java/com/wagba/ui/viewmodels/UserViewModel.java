package com.wagba.ui.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.wagba.data.db.UserDao;
import com.wagba.data.db.UserDb;
import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.data.models.User;
import com.wagba.data.repositories.UserRepository;

public class UserViewModel extends ViewModel {


    private MutableLiveData<FirebaseUser> firebaseMutableLiveD = new MutableLiveData<>();


    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public LiveData<User> getUser() {
        return userMutableLiveData;
    }
    public void updateUser(User user) {
        UserRepository.updateUser(user);
        userMutableLiveData.setValue(user);
    }


    public void getUserFromDb() {
        userMutableLiveData.postValue(UserRepository.getUser());
    }

}
