package com.example.educationalpractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DbManager {
    private final NoteDB noteDB;
    private SQLiteDatabase database;

    public DbManager(Context context){
        noteDB = new NoteDB(context);
    }

    public void OpenDb(){
        database = noteDB.getWritableDatabase();
    }

    public void CloseDb(){
        if(database.isOpen()){
            database.close();
        }
    }

    public void insertNote(String noteName, String noteDescription, String noteDate){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDB.NOTE_NAME, noteName);
        contentValues.put(NoteDB.NOTE_DATE, noteDate);
        contentValues.put(NoteDB.NOTE_DESCRIPTION, noteDescription);
        database.insert(NoteDB.NOTE_TABLE_NAME, null, contentValues);
    }

    public void updateNote(long noteId, String noteName, String noteDescription){
        String noteIdStr =  Long.toString(noteId);
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDB.NOTE_ID, noteId);
        contentValues.put(NoteDB.NOTE_NAME, noteName);
        contentValues.put(NoteDB.NOTE_DATE, noteDescription);
        database.update(NoteDB.NOTE_TABLE_NAME, contentValues,NoteDB.NOTE_ID + "= ?", new String[] {noteIdStr});
    }

/*    public Note getOneNote(long noteId){
        String noteIdStr =  Long.toString(noteId);
        Note note = new Note();
        Cursor cursor = database.query(
                NoteDB.NOTE_TABLE_NAME,
                new String[]{NoteDB.NOTE_NAME, NoteDB.NOTE_DESCRIPTION},
                NoteDB.NOTE_ID + " = ?",
                new String[]{noteIdStr},
                null,
                null,
                null
        );
        if(cursor.moveToFirst()){
            note.setNoteDescription(cursor.getString(cursor.getColumnIndexOrThrow(NoteDB.NOTE_DESCRIPTION)));
            note.setNoteName(cursor.getString(cursor.getColumnIndexOrThrow(NoteDB.NOTE_NAME)));
        }
        cursor.close();
        return note;
    }*/

    public List<Note> getAllNotes(){
        List<Note> tempList = new ArrayList<>();
        Cursor cursor = database.query(
                NoteDB.NOTE_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            Note note = new Note();
            note.setNoteId(cursor.getLong(cursor.getColumnIndexOrThrow(NoteDB.NOTE_ID)));
            note.setNoteDate(cursor.getString(cursor.getColumnIndexOrThrow(NoteDB.NOTE_DATE)));
            note.setNoteDescription(cursor.getString(cursor.getColumnIndexOrThrow(NoteDB.NOTE_DESCRIPTION)));
            note.setNoteName(cursor.getString(cursor.getColumnIndexOrThrow(NoteDB.NOTE_NAME)));

            tempList.add(note);
        }
        cursor.close();
        return tempList;
    }
}
