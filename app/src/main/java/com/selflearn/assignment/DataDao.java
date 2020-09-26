package com.selflearn.assignment;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    @Query(value = "SELECT * FROM OfflineData")
    List<OfflineData> getAll();

    @Insert
    void insertAll(OfflineData... users);

    @Delete
    void delete(OfflineData data);

    @Query("DELETE FROM OfflineData")
    void deleteAll();
}
