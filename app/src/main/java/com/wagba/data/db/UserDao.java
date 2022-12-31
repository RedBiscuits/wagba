package com.wagba.data.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.wagba.data.models.User;

@Dao
public interface UserDao {
     @Insert (onConflict = OnConflictStrategy.REPLACE)
     void addUser(User user);
     @Query("select * from User order by userID desc Limit 1")
     User getUser();
     @Update
     void updateUser(User user);
     @Query("delete from User")
     void deleteUser();
     @Query("select remainingWalletAmount from User where userID = :userId")
     String getUserCurrentAmount(int userId);
}
