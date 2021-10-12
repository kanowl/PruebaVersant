package com.pruebaversant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pruebaversant.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        email = getIntent().getStringExtra("email");

        Button DownloadButton =  findViewById(R.id.DownloadButton);

        DownloadButton.setVisibility(View.INVISIBLE); //Asigna valor true.

        if (email.equals("viktor182@gmail.com")|| email.equals("hortuacesar@gmail.com"))
            DownloadButton.setVisibility(View.VISIBLE); //Asigna valor true.



    }

    public void Home (View view){
        Intent i = new Intent(this, Home.class);
        i.putExtra("email", email);

        startActivity(i);

    }

    public void Achievements (View view){
        Intent i = new Intent(this, achievements.class);
        i.putExtra("email", email);

        startActivity(i);

    }





    public void Download (View view){

        Intent i = new Intent(this, Download.class);
        i.putExtra("email", email);

        startActivity(i);

        StringBuilder data= new StringBuilder();
        data.append("Email,Score,CEFR,GSE,Time");












    }



}