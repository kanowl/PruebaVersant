package com.pruebaversant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Dictation extends AppCompatActivity {

    int numero, auxnum, flag=0;
    private TextView textView13,textView10,textView11;
    String text, respuesta, cefr, gse1, score,doc,email;
    private EditText resp;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictation);

        ImageButton boton = (ImageButton) findViewById(R.id.Play);
        boton.setEnabled(true);

        resp=findViewById(R.id.AnswerDictation);
        textView13 = (TextView)findViewById(R.id.textView13);
        textView10 = (TextView)findViewById(R.id.textView10);
        textView11 = (TextView)findViewById(R.id.textView11);

        FirebaseFirestore db1 = FirebaseFirestore.getInstance();
        email = getIntent().getStringExtra("email");


      /*  if (flag==0){
            cefr = "A2";
            gse1= "30";
            score= "100";
            doc="0";
            textView13.setText(score);
            textView10.setText(gse1);
            textView11.setText(cefr);
        }else{*/
            DocumentReference docRef1 = db1.collection("ScoreDictation").document(String.valueOf(email));
            docRef1.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document1 = task.getResult();
                            if (document1.exists()) {
                                cefr = (String) document1.get("CEFR");
                                gse1= (String) document1.get("gse");
                                score= (String) document1.get("score");
                                doc=(String) document1.get("doc");
                                textView13.setText(score);
                                textView10.setText(gse1);
                                textView11.setText(cefr);
                            }
                            else {
                                Log.d("Dictation", "No such document");

                            } }else {
                            Log.w("Dictation", "Error getting documents.", task.getException());
                        }
                    }
                });
        }

    //}

    public void Play (View view)  {
        ImageButton boton = (ImageButton) findViewById(R.id.Play);

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

        int doc1=Integer.parseInt(doc);
        int document = numero+doc1;
        DocumentReference docRef = db.collection("Dictation").document(String.valueOf(document));
        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                respuesta = (String) document.get("answer");
                                Log.d("Dictation", respuesta);
                            }
                            else {
                                Log.d("Dictation", "No such document");

                            } }else {
                            Log.w("Dictation", "Error getting documents.", task.getException());
                        }
                    }
                });

        int auxgse = Integer.parseInt(gse1);
        if (auxgse>42)
            cefr="b1";
        if (auxgse>57)
            cefr="b2";
        if (auxgse>75)
            cefr="c1";

        String pista;
        cefr= cefr.toLowerCase();
        cefr= cefr.replace("+", "");
        Log.d("Audio", 'd'+cefr+gse1);
        switch (numero)
        {
            case 1:
                pista="1";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('d'+cefr+gse1+pista,"raw",getPackageName()));


                break;

            case 2:
                pista="2";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('d'+cefr+gse1+pista,"raw",getPackageName()));

                break;
            case 3:
                pista="3";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('d'+cefr+gse1+pista,"raw",getPackageName()));

                break;
            case 4:
                pista="4";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('d'+cefr+gse1+pista,"raw",getPackageName()));

                break;
            case 5:
                pista="5";
                mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier('d'+cefr+gse1+pista,"raw",getPackageName()));


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

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Map<String, Object> a = new HashMap<>();
            a.put("CEFR", cefr);
            a.put("gse", gse1);
            a.put("score", score);
            a.put("doc", doc);

            db.collection("ScoreDictation").document(email)
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
            Toast.makeText(Dictation.this, "Right Answer", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Dictation.class);
            i.putExtra("email", email);
            startActivity(i);
        }else {
            Toast.makeText(Dictation.this, "Wrong Answer... Try Again", Toast.LENGTH_SHORT).show();

        }

    }

    public void Replay (View view) throws IOException {
        int auxscore = Integer.parseInt(score);
        auxscore = auxscore-1;
        score = String.valueOf(auxscore);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> a = new HashMap<>();
        a.put("CEFR", cefr);
        a.put("gse", gse1);
        a.put("score", score);
        a.put("doc", doc);

        db.collection("ScoreDictation").document(email)
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

        textView13.setText(score);
        textView10.setText(gse1);
        textView11.setText(cefr);
        Play(view);

    }

    public void ChangeQuestion (View view) throws IOException {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Dictation.this);
        alerta.setMessage("If you change the question, you are going to lose 10 points and two levels. Are you sure you want to change the question?")
                .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                auxnum = numero;
                numero=0;

                int auxscore = Integer.parseInt(score);
                auxscore = auxscore-10;
                score = String.valueOf(auxscore);

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> a = new HashMap<>();
                a.put("CEFR", cefr);
                a.put("gse", gse1);
                a.put("score", score);
                a.put("doc", doc);

                db.collection("ScoreDictation").document(email)
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

                textView13.setText(score);
                textView10.setText(gse1);
                textView11.setText(cefr);
                Play(view);

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();;
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Alert");
        titulo.show();
    }



    public void Practice (View view){
        Intent i = new Intent(this, Practice.class);
        i.putExtra("email", email);
        startActivity(i);

    }
}