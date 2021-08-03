package com.pruebaversant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pruebaversant.R;

public class achievements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
    }

    public void Dashboard (View view){
        Intent i = new Intent(this, Dashboard.class);
        startActivity(i);

    }
}