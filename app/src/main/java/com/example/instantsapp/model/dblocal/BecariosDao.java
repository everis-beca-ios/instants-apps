package com.example.instantsapp.model.dblocal;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.WorkerThread;

import com.example.instantsapp.model.modelodados.InfoUsuarios;

import java.util.List;

@Dao
public interface BecariosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @WorkerThread
    void inserirUsuarios(List<InfoUsuarios> usuariosList);

    @Query("SELECT * from becarios ORDER BY id DESC")
    LiveData<List<InfoUsuarios>> pegarListaUsuarios();

    @WorkerThread
    @Query("DELETE FROM becarios")
    void deletarTodosOsUsuarios();
}
