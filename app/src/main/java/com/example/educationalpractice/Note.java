package com.example.educationalpractice;

import java.io.Serializable;

public class Note implements Serializable {

    private long noteId;
    private String noteName;
    private String noteDate;
    private String noteDescription;


    public long getNoteId() {
        return this.noteId;
    }
    public String getNoteName() {
        return this.noteName;
    }
    public String getNoteDate() {
        return this.noteDate;
    }
    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }
    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }
}
