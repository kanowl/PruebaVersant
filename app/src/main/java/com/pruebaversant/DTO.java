package com.pruebaversant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DTO extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void InsertScore (String e, String table, String cefr, String gse1, String score, String doc, long tiempo){

        Map<String, Object> a = new HashMap<>();
        a.put("CEFR", cefr);
        a.put("gse", gse1);
        a.put("score", score);
        a.put("doc", doc);
        a.put("time", tiempo);

        db.collection(table).document(e)
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
    }

    String cefr, gse1, score,doc;



    public String[] ConsultaScore (String e,  String table){
        FirebaseFirestore db1 = FirebaseFirestore.getInstance();

        DocumentReference docRef1 = db1.collection("ScoreSentenceBuild").document(String.valueOf(e));
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

                        }
                        else {
                            Log.d("SentenceBuilds", "No such document");

                        } }else {
                        Log.w("SentenceBuilds", "Error getting documents.", task.getException());
                    }
                });

        String  sco[] = new String[4];
        sco[0]=cefr;
        sco[1]=gse1;
        sco[2]=score;
        sco[3]=doc;

    return sco;
    }

    String respuesta;
    public  String[] ConsultaText (int numero , String doc2 ){

        FirebaseFirestore db3 = FirebaseFirestore.getInstance();
        Log.d("SentenceBuilds", doc2);
        int doc1=Integer.parseInt(doc2);
        int document = numero+doc1;
        DocumentReference docRef3 = db3.collection("SentenceBuilds").document(String.valueOf(document));
        docRef3.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document3 = task.getResult();
                        assert document3 != null;
                        if (document3.exists()) {
                           respuesta = (String) document3.get("answer");
                            cefr = (String) document3.get("CEFR");
                            gse1= (String) document3.get("gse");

                            Log.d("SentenceBuilds", respuesta);
                        }
                        else {
                            Log.d("SentenceBuilds", "No such document");

                        } }else {
                        Log.w("SentenceBuilds", "Error getting documents.", task.getException());
                    }
                });

        String  sco[] = new String[2];
        sco[0]=respuesta;
        sco[1]=cefr;
        sco[2]=gse1;
        return sco;


    }
    




}
