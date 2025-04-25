package com.example.hw04_gymlog.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.hw04_gymlog.database.entities.GymLog;

import java.util.List;

//GymLog Data Access Object
@Dao
public interface GymLogDAO {
    //Add records to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GymLog gymLog);

    //Get all columns from the table
    @Query("SELECT * FROM " + GymLogDatabase.GYM_LOG_TABLE + " ORDER BY date DESC")
    List<GymLog> getAllRecords();

    @Query("SELECT * FROM " + GymLogDatabase.GYM_LOG_TABLE + " WHERE userId = :userId ORDER BY date DESC")
    LiveData<List<GymLog>> getAllLogsByUserId(int userId);

    @Query("SELECT * FROM " + GymLogDatabase.GYM_LOG_TABLE + " WHERE userId = :loggedInUserId ORDER BY date DESC")
    List<GymLog> getRecordsByUserId(int loggedInUserId);
}
