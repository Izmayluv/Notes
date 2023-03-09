package com.example.educationalpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    FloatingActionButton floatingActionButton;
    DbManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        dbManager = new DbManager(getApplicationContext());
        dbManager.OpenDb();
        NoteAdapter.OnNoteClickListener onNoteClickListener = new NoteAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note, long itemId, int adapterPos) {
                Intent intent = new Intent(getApplicationContext(), NoteEditActivity.class);
                intent.putExtra("note", note);
                intent.putExtra("edit", false);
                startActivity(intent);
            }
        };

        noteAdapter = new NoteAdapter(getApplicationContext(),dbManager.getAllNotes(), onNoteClickListener);
        recyclerView.setAdapter(noteAdapter);
        dbManager.CloseDb();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoteEditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager.OpenDb();
        noteAdapter.UpdateAdapter(dbManager.getAllNotes());
        dbManager.CloseDb();
    }

    public static String CurrentDate(){
        SimpleDateFormat currentDate = new SimpleDateFormat("dd MMMM", new Locale("ru", "ru"));
        return currentDate.format(new Date());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.CloseDb();
    }
}