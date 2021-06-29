package com.pruebaversant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {




    private EditText email;
    private EditText pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();



    }

    public void registro (View view){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);

    }

    public void login (View view) throws IOException {


/*
        //-----------------------inserta datos en la base de datos de sentencebuild a partir del sentencebuild.csv no hace parte del programa  -------------------------


        String SEPARATOR = ",";
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        InputStream is = this.getResources().openRawResource(R.raw.sentencebuild);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        int cont = 1;
        while (null != line) {

            String[] fields = line.split(SEPARATOR);

            Map<String, Object> a = new HashMap<>();
            a.put("CEFR", fields[0]);
            a.put("gse", fields[1]);
            a.put("numero", fields[2]);
            a.put("answer", fields[3]);

            db.collection("SentenceBuilds").document(String.valueOf(cont))
                    .set(a)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("actualiza", "actualizado!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("error actrualizando", "Error writing document", e);
                        }
                    });

            line = br.readLine();
            cont++;
        }



         //-----------------------inserta datos en la base de datos de dictation a partir del dictation.csv no hace parte del programa  -------------------------


        String SEPARATOR = ",";
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        InputStream is = this.getResources().openRawResource(R.raw.dictation);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        int cont = 1;
        while (null != line) {

            String[] fields = line.split(SEPARATOR);

            Map<String, Object> a = new HashMap<>();
            a.put("CEFR", fields[0]);
            a.put("gse", fields[1]);
            a.put("numero", fields[2]);
            a.put("answer", fields[3]);

            db.collection("Dictation").document(String.valueOf(cont))
                    .set(a)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("actualiza", "actualizado!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("error actrualizando", "Error writing document", e);
                        }
                    });

            line = br.readLine();
            cont++;
        }




                 //-----------------------inserta datos en la base de datos de sentence_completition a partir del sentencecompletition.csv no hace parte del programa  -------------------------


        String SEPARATOR = ";";
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        InputStream is = this.getResources().openRawResource(R.raw.sentencecompletition);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        int cont = 1;
        while (null != line) {

            String[] fields = line.split(SEPARATOR);

            Map<String, Object> a = new HashMap<>();
            a.put("CEFR", fields[0]);
            a.put("gse", fields[1]);
            a.put("question", fields[2]);
            a.put("answer", fields[3]);

            db.collection("SentenceCompletition").document(String.valueOf(cont))
                    .set(a)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("actualiza", "actualizado!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("error actrualizando", "Error writing document", e);
                        }
                    });

            line = br.readLine();
            cont++;
        }
*/

        //-------------------------------------- -----Fin de la inserción -------------------------------

            String e = email.getText().toString();
            String p = pass.getText().toString();

            if (!e.isEmpty() && !p.isEmpty()) {
                mAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                           Intent in = new Intent(MainActivity.this, Home.class);
                            in.putExtra("email", e);
                            startActivity(in);

                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "No se pudo iniciar sesion por favor compruebe los datos", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            } else {
                Toast.makeText(MainActivity.this, "Campos incompletos", Toast.LENGTH_SHORT).show();

            }





    }





    }