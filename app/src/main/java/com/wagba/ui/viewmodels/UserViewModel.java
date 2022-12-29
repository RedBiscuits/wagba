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
    UserDao userDao;
    UserRepository userRepository;

    public UserViewModel(Context context) {
        userDao = UserDb.getDatabase(context).userDao();
        userRepository = new UserRepository(userDao);

        //Fetch user from firebase helper and save to database
        addUser();
        firebaseMutableLiveD.postValue(FirebaseHelper.getCurrentUser());
    }

    private MutableLiveData<FirebaseUser> firebaseMutableLiveD = new MutableLiveData<>();


    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public LiveData<User> getUser() {
        userMutableLiveData.setValue(getUserfromDb());
        return userMutableLiveData;
    }


    private void addUser() {
        FirebaseUser user = firebaseMutableLiveD.getValue();
        if (user != null) {
            userRepository.addUser(new User(user.getEmail(),
                    user.getDisplayName(),
                    user.getUid(),
                    user.getIdToken(true).toString(),
                    user.getPhoneNumber()));
        } else
            Log.d("firebase user", "firebase user doesn't exist");
    }

    private User getUserfromDb() {
        return userRepository.getUser();
    }

}
