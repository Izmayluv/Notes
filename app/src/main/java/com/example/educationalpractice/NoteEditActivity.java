package com.example.educationalpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NoteEditActivity extends AppCompatActivity {

    ImageButton image;
    EditText noteName, noteDescription;
    DbManager dbManager;
    Note note;
    boolean isEdit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        image = findViewById(R.id.imageButton);
        noteName = findViewById(R.id.noteName);
        noteDescription = findViewById(R.id.noteDesc);

        dbManager = new DbManager(getApplicationContext());

        GetIntents();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnSave();
            }
        });
    }

    private void  GetIntents(){
        Intent i = getIntent();
        if (i != null){
            note = (Note)i.getSerializableExtra("note");
            isEdit = i.getBooleanExtra("edit", true);

            if (!isEdit){
                noteName.setText(note.getNoteName());
                noteDescription.setText(note.getNoteDescription());
            }
        }
    }

    private void OnSave(){
        if (noteName.getText().toString().equals("") || noteDescription.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            return;
        }else {
            if (!isEdit){
                dbManager.OpenDb();
                dbManager.updateNote(note.getNoteId(),
                        noteName.getText().toString(),
                        noteDescription.getText().toString());
                dbManager.CloseDb();
            }
            else {
                dbManager.OpenDb();
                dbManager.insertNote(noteName.getText().toString(),
                        noteDescription.getText().toString(),
                        MainActivity.CurrentDate());
                dbManager.CloseDb();
            }
        }


        finish();
    }
}