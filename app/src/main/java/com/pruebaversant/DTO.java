package com.pruebaversant;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;


public class DTO {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void InsertScore (String e, Map a, String table){

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





    }
