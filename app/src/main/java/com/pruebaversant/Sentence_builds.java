 package com.pruebaversant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Sentence_builds extends AppCompatActivity {

    int numero, auxnum;
    private TextView textView13,textView10,textView11;
    String text, respuesta, cefr, gse1, score,doc, email;
    private EditText resp;
    MediaPlayer  mp;
    long tiempoInicial,tiempo;

    DTO dto =  new DTO();
    UpdateItems up =  new UpdateItems();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_builds);
        ImageButton boton =  findViewById(R.id.Play);

        Date fechaInicio=new Date();
        tiempoInicial =fechaInicio.getTime();


        boton.setEnabled(true);  //Asigna valor true.
        resp=findViewById(R.id.answer);
        textView13 = findViewById(R.id.textView13);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        email = getIntent().getStringExtra("email");
        Log.d("correo", email);

        FirebaseFirestore db1 = FirebaseFirestore.getInstance();

        DocumentReference docRef1 = db1.collection("ScoreSentenceBuild").document(String.valueOf(email));
        docRef1.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document1 = task.getResult();
                        assert document1 != null;
                        if (document1.exists()) {
                            cefr = (String) document1.get("CEFR");
                            gse1= (String) document1.get("gse");
                            score= (String) document1.get("score");
                            doc=(String) document1.get("doc");
                            tiempo= (long) document1.get("time");
                            textView13.setText(score);
                            textView10.setText(gse1);
                            textView11.setText(cefr);
                        }
                        else {
                            Log.d("SentenceBuilds", "No such document");

                        } }else {
                        Log.w("SentenceBuilds", "Error getting documents.", task.getException());
                    }
                });

    }



    public void Play (View view)  {
        ImageButton boton =  findViewById(R.id.Play);

        boton.setEnabled(false);

        if(numero == 0){
            numero =(int) (Math.random() * 5) + 1;
            if (auxnum !=0){
                while (auxnum == numero){
                    numero =(int) (Math.random() * 5) ;
                }
            }
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d("SentenceBuilds", doc);
        int doc1=Integer.parseInt(doc);
        int document = numero+doc1;
        DocumentReference docRef = db.collection("SentenceBuilds").document(String.valueOf(document));
        docRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document1 = task.getResult();
                        assert document1 != null;
                        if (document1.exists()) {
                          respuesta = (String) document1.get("answer");
                            cefr = (String) document1.get("CEFR");
                            gse1= (String) document1.get("gse");
                            textView10.setText(gse1);
                            textView11.setText(cefr);
                            Log.d("SentenceBuilds", respuesta);
                        }
                        else {
                            Log.d("SentenceBuilds", "No such document");

                        } }else {
                        Log.w("SentenceBuilds", "Error getting documents.", task.getException());
                    }
                });

        cefr =up.Cefr(gse1);





        String pista;
        cefr= cefr.toLowerCase();
        cefr= cefr.replace("+", "");
        Log.d("Audio", 's'+cefr+gse1);
        switch (numero)
        {
            case 1:
                pista="1";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('s'+cefr+gse1+pista,"raw",getPackageName()));
                break;

            case 2:
                pista="2";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('s'+cefr+gse1+pista,"raw",getPackageName()));
                break;

            case 3:
                pista="3";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('s'+cefr+gse1+pista,"raw",getPackageName()));
                break;

            case 4:
                pista="4";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('s'+cefr+gse1+pista,"raw",getPackageName()));
                break;

            case 5:
                pista="5";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('s'+cefr+gse1+pista,"raw",getPackageName()));
                break;

        }

        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
        mp.start();

    }

    public void Answer  (View view){
        text = resp.getText().toString();
        if (respuesta.equals(text) ){

            int auxscore = Integer.parseInt(score);
            auxscore = auxscore+ 100;
            int auxgse = Integer.parseInt(gse1);
            auxgse++;
            score = String.valueOf(auxscore);
            gse1= String.valueOf(auxgse);
            int auxdoc= Integer.parseInt(doc);
            auxdoc=auxdoc+5;
            doc = String.valueOf(auxdoc);

            Map<String, Object> a = new HashMap<>();
            a.put("CEFR", cefr);
            a.put("gse", gse1);
            a.put("score", score);
            a.put("doc", doc);
            a.put("time", tiempo);


            dto.InsertScore(email,a, "ScoreSentenceBuild");

            Toast.makeText(Sentence_builds.this, "Right Answer", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Sentence_builds.class);
            i.putExtra("email", email);
            startActivity(i);
        }else {
            Toast.makeText(Sentence_builds.this, "Wrong Answer... Try Again", Toast.LENGTH_SHORT).show();

        }

    }

    public void Replay (View view)  {
        int auxscore = Integer.parseInt(score);
        auxscore = auxscore-1;
        score = String.valueOf(auxscore);

        Map<String, Object> a = new HashMap<>();
        a.put("CEFR", cefr);
        a.put("gse", gse1);
        a.put("score", score);
        a.put("doc", doc);
        a.put("time", tiempo);


        dto.InsertScore(email,a,"ScoreSentenceBuild");

        textView13.setText(score);
        textView10.setText(gse1);
        textView11.setText(cefr);
        Play(view);

    }

    public void ChangeQuestion (View view)  {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Sentence_builds.this);
        alerta.setMessage("If you change the question, you are going to lose 10 points and two levels. Are you sure you want to change the question?")
                .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                auxnum = numero;
                numero=0;

                int auxgse = Integer.parseInt(gse1);
                if (auxgse==31)
                    auxgse=auxgse-1;
                if (auxgse>=32)
                    auxgse=auxgse-2;

                gse1= String.valueOf(auxgse);
                UpdateItems up =  new UpdateItems();
                cefr =up.Cefr(gse1);

                int auxdoc= Integer.parseInt(doc);
                if (auxdoc==5)
                    auxdoc=0;
                if (auxdoc==10)
                    auxdoc=0;
                if (auxdoc>10)
                    auxdoc=auxdoc-10;

                doc = String.valueOf(auxdoc);

                int auxscore = Integer.parseInt(score);
                auxscore = auxscore-10;
                score = String.valueOf(auxscore);

                Map<String, Object> a = new HashMap<>();
                a.put("CEFR", cefr);
                a.put("gse", gse1);
                a.put("score", score);
                a.put("doc", doc);
                a.put("time", tiempo);

                dto.InsertScore(email,a, "ScoreSentenceBuild");

                textView13.setText(score);
                textView10.setText(gse1);
                textView11.setText(cefr);
                Play(view);

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Alert");
        titulo.show();
    }



    public void Practice (View view)  {
        Date fechaFin=new Date();
        long tiempoFinal=fechaFin.getTime();
        long resta=(tiempoFinal/1000) - (tiempoInicial/1000);

        resta= resta+tiempo;


        Map<String, Object> a = new HashMap<>();
        a.put("CEFR", cefr);
        a.put("gse", gse1);
        a.put("score", score);
        a.put("doc", doc);
        a.put("time", resta);

        dto.InsertScore(email,a, "ScoreSentenceBuild");
        Log.d("TiempodeSentenceBuilds", String.valueOf(resta));
        Intent i = new Intent(this, Practice.class);
        i.putExtra("email", email);
        startActivity(i);

    }



}