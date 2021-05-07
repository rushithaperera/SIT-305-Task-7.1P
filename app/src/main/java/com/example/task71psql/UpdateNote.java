package com.example.task71psql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNote extends AppCompatActivity {

    EditText myNoteTitle, myNoteContent;
    Button btnupdate, btnDelete;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        myNoteTitle = findViewById(R.id.addNoteTitle);
        myNoteContent = findViewById(R.id.addNoteContent);
        btnupdate = findViewById(R.id.update);
        btnDelete = findViewById(R.id.delete);

        btnDelete.setBackgroundColor(Color.RED);

        Intent i = getIntent();
        myNoteTitle.setText(i.getStringExtra("title"));
        myNoteContent.setText(i.getStringExtra("description"));
        id = i.getStringExtra("id");

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(myNoteTitle.getText().toString()) && !TextUtils.isEmpty(myNoteContent.getText().toString())){

                    DatabaseClass db = new DatabaseClass(UpdateNote.this);
                    db.updateNotes(myNoteTitle.getText().toString(), myNoteContent.getText().toString(), id);

                    Intent i = new Intent(UpdateNote.this, AllNotes.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                }
                else{
                    Toast.makeText(UpdateNote.this,"Both Fields Required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(myNoteTitle.getText().toString()) && !TextUtils.isEmpty(myNoteContent.getText().toString())){
                    DatabaseClass db = new DatabaseClass(UpdateNote.this);
                    db.deleteNote(id);

                    Intent i = new Intent(UpdateNote.this, AllNotes.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}