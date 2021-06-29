package com.pruebaversant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Practice extends AppCompatActivity {

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        email = getIntent().getStringExtra("email");

    }

    public void sentence_builds (View view){
        Intent i = new Intent(this, Sentence_builds.class);
        i.putExtra("email", email);

        startActivity(i);

    }

    public void sentence_completion (View view){
        Intent i = new Intent(this, Sentence_completion.class);
        i.putExtra("email", email);

        startActivity(i);

    }

    public void dictation (View view){
        Intent i = new Intent(this, Dictation.class);
        i.putExtra("email", email);

        startActivity(i);

    }
    public void Home (View view){
        Intent i = new Intent(this, Home.class);
        i.putExtra("email", email);
        startActivity(i);

    }
}