package com.pruebaversant;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
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

                Map<String, Object> a = new HashMap<>();
                a.put("CEFR", "A2");
                a.put("gse", "30");
                a.put("score", "100");
                a.put("doc", "0");
                a.put("time", 0);



                // LLamar a clase DTO para insertar los datos en la DB
                DTO dto =  new DTO();
                dto.InsertScore(e, a,"ScoreDictation");
                dto.InsertScore(e,a,"ScoreSentenceBuild");
                dto.InsertScore(e,a,"ScoreSentenceCompletition");

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