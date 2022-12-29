package com.wagba.ui.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wagba.data.repositories.UserRepository;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    Context context;

    public UserViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(context);
        }
        throw new IllegalArgumentException("View Model not found");
    }

}