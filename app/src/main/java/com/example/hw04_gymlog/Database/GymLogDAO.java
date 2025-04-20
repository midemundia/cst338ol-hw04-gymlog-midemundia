package com.example.hw04_gymlog.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.hw04_gymlog.Database.entities.GymLog;

import java.util.ArrayList;

//Gymlog Data Access Object
@Dao
public interface GymLogDAO {
    //Add records to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GymLog gymLog);

    //Get all columns from the table
    @Query("Select * from " + GymLogDatabase.GYM_LOG_TABLE)
    ArrayList<GymLog> getAllRecords();
}
