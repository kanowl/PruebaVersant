package com.pruebaversant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pruebaversant.R;
import com.pruebaversant.MainActivity;

import com.pruebaversant.Settings;
import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         email = getIntent().getStringExtra("email");
        Log.d("SentenceBuilds", email);
    }

    public void Practice (View view){
        Intent i = new Intent(this, Practice.class);
        i.putExtra("email", email);

        startActivity(i);

    }

    public void Dashboard (View view){
        Intent i = new Intent(this, Dashboard.class);
        i.putExtra("email", email);
        startActivity(i);

    }

    public void Logout (View view){
        mAuth=FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void Settings (View view){

        Intent i = new Intent(this, Settings.class);
        startActivity(i);
        finish();
    }
}