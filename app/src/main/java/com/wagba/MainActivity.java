package com.wagba;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.databinding.ActivityMainBinding;
import com.wagba.ui.helpers.NavigationHelper;

import org.checkerframework.common.subtyping.qual.Bottom;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
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