package com.wagba.data.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wagba.data.models.User;

@Dao
public interface UserDao {

     @Insert (onConflict = OnConflictStrategy.REPLACE)
     void addUser(User user);


     @Query("select * from User")
     User getUser();
}
