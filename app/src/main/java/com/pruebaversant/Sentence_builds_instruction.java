package com.pruebaversant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pruebaversant.R;
import com.pruebaversant.Sentence_builds;

public class Sentence_builds_instruction extends AppCompatActivity {
    String email;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_builds_instruction);
        email = getIntent().getStringExtra("email");
        tv1 = (TextView)findViewById(R.id.ContentSentenceCompletion);
        tv1.setMovementMethod(new ScrollingMovementMethod());

    }

    public void sentence_builds (View view){
        Intent i = new Intent(this, Sentence_builds.class);
        i.putExtra("band", 0);
        i.putExtra("email", email);

        startActivity(i);

    }
}