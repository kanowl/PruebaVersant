package com.pruebaversant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class Sentence_completion extends AppCompatActivity {

    int numero, auxnum;
    private TextView textView13,textView10,textView11, question;
    String text, respuesta, cefr, gse1, score,que,email;
    private EditText resp;
    static String doc="0";
    long tiempo;
    int doc1, document;

    DTO dto =  new DTO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpdateItems up =  new UpdateItems();

        setContentView(R.layout.activity_sentence_completion);
        resp=findViewById(R.id.AnswerSentenceCompletion);
        textView13 = findViewById(R.id.textView13);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        question = findViewById(R.id.question);
        email = getIntent().getStringExtra("email");
        int bandera = getIntent().getIntExtra("band", 1);

        up.HoraInicio(bandera);

        numero = up.Randompista(numero,auxnum);

        FirebaseFirestore db1 = FirebaseFirestore.getInstance();

        DocumentReference docRef1 = db1.collection("ScoreSentenceCompletition").document(String.valueOf(email));
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
                                doc= (String) document1.get("doc");
                                tiempo= (long) document1.get("time");
                                Log.d("Resultado consulta BD", doc);

                                doc1= Integer.parseInt(doc);
                                document = numero+doc1;
                                GetAnswer();


                                textView13.setText(score);
                                textView10.setText(gse1);
                                textView11.setText(cefr);
                            }
                            else {
                                Log.d("Sentence_completion", "No such document");

                            } }else {
                            Log.w("Sentence_completion", "Error getting documents.", task.getException());
                        }
                    }
                });


    }


public void GetAnswer (){

        UpdateItems up =  new UpdateItems();
        numero = up.Randompista(numero,auxnum);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        int doc1=Integer.parseInt(doc);
        int document = numero+doc1;
        Log.d("Documento______", String.valueOf(document));
        DocumentReference docRef = db.collection("SentenceCompletition").document(String.valueOf(document));
        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                que = (String) document.get("question");
                                respuesta = (String) document.get("answer");
                                cefr = (String) document.get("CEFR");
                                gse1= (String) document.get("gse");
                                textView10.setText(gse1);
                                textView11.setText(cefr);
                                Log.d("respuesta", respuesta);
                                question.setText(que);
                            }
                            else {
                                Log.d("SentenceCompletition", "No such document");
                            }
                        }else
                            {
                                Log.w("SentenceCompletition", "Error getting documents.", task.getException());
                            }
                    }
                });
        cefr =up.Cefr(gse1);
        textView11.setText(cefr);

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

            dto.InsertScore(email, "ScoreSentenceCompletition", cefr, gse1,score, doc, tiempo);

            Toast.makeText(Sentence_completion.this, "Right Answer", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Sentence_completion.class);
            i.putExtra("email", email);
            i.putExtra("band", 1);
            startActivity(i);
            }else {
            Toast.makeText(Sentence_completion.this, "Wrong Answer... Try Again", Toast.LENGTH_SHORT).show();

        }

    }

    public void ChangeQuestion (View view) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Sentence_completion.this);
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

                dto.InsertScore(email,"ScoreSentenceCompletition", cefr, gse1,score, doc, tiempo);

                textView13.setText(score);
                textView10.setText(gse1);
                textView11.setText(cefr);

                GetAnswer();

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

         dto.InsertScore(email, "ScoreSentenceCompletition", cefr, gse1,score, doc, ttotal);
        Log.d("TiempodeSentenceBuilds", String.valueOf(ttotal));
        Intent i = new Intent(this, Practice.class);
        i.putExtra("email", email);
        startActivity(i);

    }
}