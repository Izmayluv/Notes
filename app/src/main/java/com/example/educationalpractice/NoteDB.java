package com.example.educationalpractice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDB extends SQLiteOpenHelper {
    /**
     * database name, version
     */
    public static final String DATABASE_NAME = "note-store.db";
    public static final int DATABASE_VERSION = 1;
    /**
     * table,
     * column names
     */
    public static final String NOTE_TABLE_NAME = "notes";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_NAME = "name";
    public static final String NOTE_DATE = "date";
    public static final String NOTE_DESCRIPTION = "description";
    /**
     * creation string
     */
    private static final String DB_CREATION_STRING = "CREATE TABLE IF NOT EXISTS "+
            NOTE_TABLE_NAME + " (" + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ NOTE_NAME + " TEXT,"+
            NOTE_DATE + " TEXT," + NOTE_DESCRIPTION + " TEXT)";

    public NoteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATION_STRING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
