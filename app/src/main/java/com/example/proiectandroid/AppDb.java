package com.example.proiectandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.proiectandroid.ArtObject;
import com.example.proiectandroid.ArtObjectDAO;

@Database(entities = {ArtObject.class}, version = 2, exportSchema = false)
public abstract class AppDb extends RoomDatabase {
    public abstract ArtObjectDAO ArtObjectDAO();
}