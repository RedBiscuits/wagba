package com.wagba;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.databinding.ActivityMainBinding;
import com.wagba.ui.helpers.NavigationHelper;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setTheme(R.style.Theme_Wagba);
        setContentView(binding.getRoot());

        NavigationHelper.initNavController(this);


        appBarConfiguration = new AppBarConfiguration.Builder(NavigationHelper.getNavController().getGraph()).build();


        if (FirebaseHelper.getCurrentUser() != null) {
            NavigationHelper.navigate(R.id.action_FirstFragment_to_SecondFragment);
        }

        NavigationHelper.navigationListener();
    }


    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(NavigationHelper.getNavController(), appBarConfiguration)
                || super.onSupportNavigateUp();
    }




}