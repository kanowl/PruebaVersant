package com.pruebaversant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pruebaversant.Dictation_instruction;
import com.pruebaversant.Home;
import com.pruebaversant.R;
import com.pruebaversant.Sentence_completition_instruction;

public class Practice extends AppCompatActivity {

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        email = getIntent().getStringExtra("email");

    }

    public void sentence_builds_instruction (View view){
        Intent i = new Intent(this, Sentence_builds_instruction.class);
        i.putExtra("email", email);

        startActivity(i);

    }

    public void sentence_completion_instruction (View view){
        Intent i = new Intent(this, Sentence_completition_instruction.class);
        i.putExtra("email", email);

        startActivity(i);

    }

    public void dictation_instruction (View view){
        Intent i = new Intent(this, Dictation_instruction.class);
        i.putExtra("email", email);

        startActivity(i);

    }
    public void Home (View view){
        Intent i = new Intent(this, Home.class);
        i.putExtra("email", email);
        startActivity(i);

    }
}