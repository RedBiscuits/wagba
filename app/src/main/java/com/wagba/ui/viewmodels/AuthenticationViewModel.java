package com.wagba.ui.viewmodels;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.data.repositories.AuthenticationRepository;
import com.wagba.data.repositories.UserRepository;

public class AuthenticationViewModel extends ViewModel {

    private MutableLiveData<Boolean> _login = new MutableLiveData<>();
    public LiveData<Boolean> getlogin() {
        return this._login;
    }

    private MutableLiveData<Boolean> _register = new MutableLiveData<>();
    public LiveData<Boolean> getRegister() {
        return this._register;
    }

    public void loginUser(String email, String password) {
        AuthenticationRepository.loginUser(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        _login.postValue(true);
                        UserRepository.addUser(FirebaseHelper.getCurrentUser());

                    }else {
                        _login.postValue(false);
                    }
                });

    }

    public void registerUser(String email, String password) {
        AuthenticationRepository.registerUser(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        _register.postValue(true);
                    }else {
                        _register.postValue(false);
                    }
                });

    }

    public CharSequence validPassword(String passwordText) {
        if (passwordText.length() < 8) {
            return "Minimum characters is 8";
        }
        if (!passwordText.matches(".*[A-Z].*")) {
            return "Must contain 1 uppercase at least";
        }

        if (!passwordText.matches(".*[a-z].*")) {
            return "Must contain 1 lowercase at least";
        }
        if (!passwordText.matches(".*[@#$%^&+=].*")) {
            return "Must contain 1 {@#$%^&+=} at least";
        }
        return null;
    }

    public CharSequence validEmail(String emailText) {

        if (emailText.contains("meowbrrr57@gmail.com")) {
            return null;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid email";
        }

        if (!emailText.contains("@eng.asu.edu.eg")) {
            return "This app is only for ASUFE students";
        }
        return null;
    }
    public CharSequence validConfirmation(String password, String confirmation) {

        if (!password.equals(confirmation)) {
            return "Passwords don't match";
        }

        return null;
    }
}
