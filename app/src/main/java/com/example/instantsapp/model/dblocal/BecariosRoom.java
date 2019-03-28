package com.example.instantsapp.model.dblocal;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.instantsapp.model.modelodados.InfoUsuarios;

@Database(entities = {InfoUsuarios.class}, version = 1, exportSchema = false)
public abstract class BecariosRoom extends RoomDatabase {
    public abstract BecariosDao becariosDao();
}