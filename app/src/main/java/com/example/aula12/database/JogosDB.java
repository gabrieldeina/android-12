package com.example.aula12.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.aula12.database.dao.JogoDAO;
import com.example.aula12.entity.Jogo;

@Database(entities = {Jogo.class}, version = 1, exportSchema = false)
public abstract class JogosDB extends RoomDatabase {

    private static final String DB_NAME = "jogos.db";

    private static JogosDB instance;

    public abstract JogoDAO getJogoDAO();

    public static JogosDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, JogosDB.class, DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

}
