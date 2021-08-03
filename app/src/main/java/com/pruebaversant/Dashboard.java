package com.pruebaversant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.pruebaversant.R;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void Home (View view){
        Intent i = new Intent(this, Home.class);
        startActivity(i);

    }

    public void Achievements (View view){
        Intent i = new Intent(this, achievements.class);
        startActivity(i);

    }
}