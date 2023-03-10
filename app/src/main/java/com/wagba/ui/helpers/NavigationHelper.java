package com.wagba.ui.helpers;

import android.app.Activity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wagba.R;
import com.wagba.ui.home.HomeFragmentDirections;

public class NavigationHelper {
    private static NavController navController;

    public static BottomNavigationView getNavigationView() {
        return navigationView;
    }

    private static BottomNavigationView navigationView;

    public static void initNavController(Activity activity ){
        navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);

        navigationView= activity.findViewById(R.id.bottomNavView);
        navigationView.setBackground(null);
    }

    public static void navigate( int id) {
        try {
            navController.getBackStackEntry(id);
            navController.popBackStack(id , false);
        }catch (Exception e){
            navController.navigate(id);
        }
    }

    public static void navigate(HomeFragmentDirections.ActionSecondFragmentToFoodFragment action) {
        try {
            navController.getBackStackEntry(action.getActionId());
            navController.popBackStack(action.getActionId(), false);
        }catch (Exception e){
            navController.navigate(action);
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
            if (navDestination.getId() == R.id.FirstFragment
                    ||navDestination.getId() == R.id.registerFragment
                    ||navDestination.getId() == R.id.foodFragment
                    ||navDestination.getId() == R.id.cartFragment
                    ||navDestination.getId() == R.id.paymentFragment
            ){
                hideBottomNav();
            }else {
                showBottomNav();
            }
        });
        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.HomeItem:
                    navigate(R.id.SecondFragment);
                    return true;

                case R.id.ordersItem:
                    navigate(R.id.ordersFragment);
                    return true;

                case R.id.profileItem:
                    navigate(R.id.profile);
                    return true;
            }
            return false;
        });

    }

    public static void setActiveIcon(int id){
        navigationView.setSelectedItemId(id);
    }

    private static void hideBottomNav(){
        navigationView.setVisibility(View.GONE);
    }

    private static void showBottomNav(){
        navigationView.setVisibility(View.VISIBLE);
    }
}
