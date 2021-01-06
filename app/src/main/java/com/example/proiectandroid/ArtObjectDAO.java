package com.example.proiectandroid;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArtObjectDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertArtObject(ArtObject obj);

    @Query("DELETE FROM artObjects")
    public void deleteAll();

    @Query("SELECT * FROM artObjects")
    List<ArtObject> getall();

    @Query("SELECT * FROM artObjects WHERE user_id =:currentUser")
    List<ArtObject> getCollection(String currentUser);

    @Query("DELETE FROM artObjects WHERE object_id = :obj_id")
    void deleteByID(int obj_id);
}