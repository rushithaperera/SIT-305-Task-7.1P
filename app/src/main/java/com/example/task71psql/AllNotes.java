package com.example.task71psql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllNotes extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    List<Model> noteList;
    DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);

        recyclerView = findViewById(R.id.recycler_view);

        noteList = new ArrayList<>();

        databaseClass = new DatabaseClass(this);
        fetchAllNotesFromDatabase();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, AllNotes.this, noteList);
        recyclerView.setAdapter(adapter);
    }

    void fetchAllNotesFromDatabase(){
        Cursor cursor = databaseClass.readAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data to show", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                noteList.add(new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }
        }
    }
}