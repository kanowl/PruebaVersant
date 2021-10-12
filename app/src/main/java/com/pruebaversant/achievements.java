package com.pruebaversant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pruebaversant.R;



public class achievements extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         email = getIntent().getStringExtra("email");
        setContentView(R.layout.activity_achievements);
    }

    public void Dashboard (View view){
        Intent i = new Intent(this, Dashboard.class);
        i.putExtra("email", email);
        startActivity(i);

    }
}