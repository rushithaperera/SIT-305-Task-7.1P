package com.example.task71psql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNote extends AppCompatActivity {

    EditText myNoteTitle, myNoteContent;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        myNoteTitle = findViewById(R.id.addNoteContent);
        myNoteContent = findViewById(R.id.addNoteTitle);
        btnsave = findViewById(R.id.save);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(myNoteTitle.getText().toString()) && !TextUtils.isEmpty(myNoteContent.getText().toString())){

                    DatabaseClass db = new DatabaseClass(CreateNote.this);
                    db.AddNotes(myNoteTitle.getText().toString(), myNoteContent.getText().toString());

                    Intent intent = new Intent(CreateNote.this, AllNotes.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(CreateNote.this,"Both Fields Required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}