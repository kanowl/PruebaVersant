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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;





public class Dictation extends AppCompatActivity {

    int numero, auxnum;
    String text, respuesta, cefr, gse1, score,doc,email;

    private TextView textView13,textView10,textView11;
    private EditText resp;
    MediaPlayer mp;
    long tiempoInicial,tiempo;
    DTO dto = new DTO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictation);

        UpdateItems up =  new UpdateItems();
        int bandera = getIntent().getIntExtra("band", 1);
        up.HoraInicio(bandera);


        ImageButton boton =  findViewById(R.id.Play);
        boton.setEnabled(true);

        resp=findViewById(R.id.AnswerDictation);
        textView13 = findViewById(R.id.textView13);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);

        FirebaseFirestore db1 = FirebaseFirestore.getInstance();
        email = getIntent().getStringExtra("email");


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
                                tiempo= (long)document1.get("time");
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
        ImageButton boton = findViewById(R.id.Play);

        boton.setEnabled(false);

        UpdateItems up =  new UpdateItems();
        numero = up.Randompista(numero,auxnum);

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
                                cefr = (String) document.get("CEFR");
                                gse1= (String) document.get("gse");
                                textView10.setText(gse1);
                                textView11.setText(cefr);
                                Log.d("Dictation", respuesta);
                            }
                            else {
                                Log.d("Dictation", "No such document");

                            } }else {
                            Log.w("Dictation", "Error getting documents.", task.getException());
                        }
                    }
                });



        cefr =up.Cefr(gse1);


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

            dto.InsertScore(email, "ScoreDictation", cefr, gse1,score, doc, tiempo );
            Toast.makeText(Dictation.this, "Right Answer", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Dictation.class);
            i.putExtra("band", 1);
            i.putExtra("email", email);
            startActivity(i);
        }else {
            Toast.makeText(Dictation.this, "Wrong Answer... Try Again", Toast.LENGTH_SHORT).show();

        }

    }

    public void Replay (View view)  {
        int auxscore = Integer.parseInt(score);
        auxscore = auxscore-1;
        score = String.valueOf(auxscore);

        dto.InsertScore(email, "ScoreDictation", cefr, gse1,score, doc, tiempo );
        textView13.setText(score);
        textView10.setText(gse1);
        textView11.setText(cefr);
        Play(view);

    }

    public void ChangeQuestion (View view)  {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Dictation.this);
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
                /*gse1= String.valueOf(auxgse);

                if (auxgse>42)
                    cefr="B1";
                if (auxgse>57)
                    cefr="B2";
                if (auxgse>75)
                    cefr="C1";*/


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

                dto.InsertScore(email, "ScoreDictation", cefr, gse1,score, doc, tiempo );
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



    public void Practice (View view){

        UpdateItems up =  new UpdateItems();
        long ttotal = up.HoraFinal(tiempo);

         dto.InsertScore(email, "ScoreDictation", cefr, gse1,score, doc, ttotal );

        Intent i = new Intent(this, Practice.class);
        i.putExtra("email", email);
        startActivity(i);

    }
}