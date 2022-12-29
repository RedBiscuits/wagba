package com.wagba.data.repositories;

import com.wagba.data.db.UserDao;
import com.wagba.data.models.User;


public class UserRepository {
    private UserDao userDao;
    private static User Instance = null;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User getUser() {
        Instance = userDao.getUser();
        return Instance;
    }

    public String getUserName() {
        if (Instance != null) {
            return Instance.getUsername();
        }
        return "";
    }

    public String getUserEmail() {
        if (Instance != null) {
            return Instance.getEmail();
        }
        return "";
    }

    public String getUserToken() {
        if (Instance != null) {
            return Instance.getUserToken();
        }
        return "";
    }
}
