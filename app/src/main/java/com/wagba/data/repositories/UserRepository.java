package com.wagba.data.repositories;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.wagba.data.db.UserDao;
import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.data.models.User;


public class UserRepository {
    private static UserDao userDao;
    private static User Instance = null;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;

    }

    public static void addUser(FirebaseUser user) {
        if (user != null) {
            User user1 = new User(user.getEmail(),
                    user.getDisplayName(),
                    user.getUid(),
                    user.getIdToken(false).toString(),
                    user.getPhoneNumber());
            user1.setTotalWalletAmount(0);
            user1.setConsumedWalletAmount(0);
            user1.setRemainingWalletAmount(user1.getTotalWalletAmount() - user1.getRemainingWalletAmount());

            if (user.getPhoneNumber() == null) {
                user1.setPhoneNumber("000");
            }
            if (user.getDisplayName() == null) {
                user1.setUsername("000");

                Log.d("current user", user1.getEmail() + "+" + user1.getUserID() + "+" + user1.getPhoneNumber() + "+" + user1.getUserToken() + "+" + user1.getUsername());
                userDao.addUser(user1);
            }
        } else
            Log.d("firebase user", "firebase user doesn't exist");
    }

    public static User getUser() {
        Instance = userDao.getUser();
        return Instance;
    }

    public static String getUserName() {
        if (Instance != null) {
            return Instance.getUsername();
        }
        return "";
    }

    public static String getUserEmail() {
        if (Instance != null) {
            return Instance.getEmail();
        }
        return "";
    }

    public static String getUserToken() {
        if (Instance != null) {
            return Instance.getUserToken();
        }
        return "";
    }

    public static void updateUser(User user) {
        userDao.updateUser(user);
    }

    public static void userLogout() {
        FirebaseHelper.logoutUser();
        userDao.deleteUser();
        Instance = null;
        Log.d("user", "user logout");
    }
}
