package com.wagba.ui.helpers;

import android.app.Activity;
import android.view.View;

import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wagba.R;

public class NavigationHelper {
    private static NavController navController;

    public static BottomNavigationView getNavigationView() {
        return navigationView;
    }

    private static BottomNavigationView navigationView;
    private static BottomAppBar bottomAppBar;

    public static void initNavController(Activity activity ){
        navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
        bottomAppBar = activity.findViewById(R.id.bottomAppBar);

        navigationView= activity.findViewById(R.id.bottomNavView);
        navigationView.setBackground(null);
    }

    public static void navigate( int id) {
        try {
            NavBackStackEntry backStackEntry = navController.getBackStackEntry(id);
            navController.popBackStack(backStackEntry.getId() , false);
        }catch (Exception e){
            navController.navigate(id);
        }
    }

    public static void popUp() {
        navController.popBackStack();
    }

    public static NavController getNavController(){
        return navController;
    }

    public static void navigationListener(){
        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if (navDestination.getId() == R.id.FirstFragment ||navDestination.getId() == R.id.registerFragment){
                hideBottomNav();
            }else {
                showBottomNav();
            }
        });

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.HomeItem:
                    item.setChecked(true);
                    navigate(R.id.SecondFragment);
                    break;
                case R.id.profileItem:
                    item.setChecked(true);
                    navigate(R.id.profile);
                    break;
            }
            return false;
        });
    }

    private static void hideBottomNav(){
        navigationView.setVisibility(View.GONE);
        bottomAppBar.setVisibility(View.GONE);
    }

    private static void showBottomNav(){
        navigationView.setVisibility(View.VISIBLE);
        bottomAppBar.setVisibility(View.VISIBLE);
    }
}
