package com.pruebaversant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.pruebaversant.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pruebaversant.MainActivity;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    FirebaseAuth miAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        email=findViewById(R.id.correo);
        pass=findViewById(R.id.password);
        miAuth = FirebaseAuth.getInstance();

    }

    public void registrar (View view){

        String e = email.getText().toString();
        String p =  pass.getText().toString();

        if (!e.isEmpty() && !p.isEmpty()){
            if(p.length()>=6){
                miAuth.createUserWithEmailAndPassword(e,p);
                startActivity(new Intent(Registro.this, MainActivity.class));
                Toast.makeText(Registro.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> a = new HashMap<>();
                a.put("CEFR", "A2");
                a.put("gse", "30");
                a.put("score", "100");
                a.put("doc", "0");

                db.collection("ScoreDictation").document(e)
                        .set(a)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("crea usuario", "actualizado!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("error", "Error writing document", e);
                            }
                        });

                db.collection("ScoreSentenceBuild").document(e)
                        .set(a)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("crea usuario", "actualizado!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("error", "Error writing document", e);
                            }
                        });

                db.collection("ScoreSentenceCompletition").document(e)
                        .set(a)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("crea usuario", "actualizado!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("error", "Error writing document", e);
                            }
                        });

                finish();

            }else{
                Toast.makeText(Registro.this, "El password debe tener mínimo 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(Registro.this, "Campos incompletos", Toast.LENGTH_SHORT).show();
        }
    }
}