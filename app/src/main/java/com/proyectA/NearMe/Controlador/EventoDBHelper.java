package com.proyectA.NearMe.Controlador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.proyectA.NearMe.Modelo.Evento;
import com.proyectA.NearMe.Modelo.EventoLocal;

public class EventoDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NearME.db";

    public EventoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + EventoLocal.EventosLocales.TABLE_NAME + " ("
                + EventoLocal.EventosLocales._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EventoLocal.EventosLocales.ID + " TEXT NOT NULL,"
                + EventoLocal.EventosLocales.TITULO + " TEXT NOT NULL,"
                + EventoLocal.EventosLocales.FECHA + " TEXT NOT NULL,"
                + EventoLocal.EventosLocales.HORA + " TEXT NOT NULL,"
                + EventoLocal.EventosLocales.UBICACION + " TEXT NOT NULL,"
                + EventoLocal.EventosLocales.DESCRIPCION + " TEXT,"
                + "UNIQUE (" + EventoLocal.EventosLocales.ID + "))");
    }

    // Llamada que se realiza cuando la base de datos necesita ser actualizada
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EventoLocal.EventosLocales.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
}
