package com.pruebaversant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.pruebaversant.R;

public class Dictation_instruction extends AppCompatActivity {
    String email;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictation_instruction);
        email = getIntent().getStringExtra("email");
        tv1 = (TextView)findViewById(R.id.ContentDictationIns);
        tv1.setMovementMethod(new ScrollingMovementMethod());

    }


    public void dictation (View view){
        Intent i = new Intent(this, Dictation.class);
        i.putExtra("email", email);

        startActivity(i);

    }
}