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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.FileOutputStream;

public class Download extends AppCompatActivity {
    String email;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        email = getIntent().getStringExtra("email");
    }

    public void Arrange_Sentences(View view){

        StringBuilder dataArr= new StringBuilder();
        dataArr.append("Email,Score,CEFR,GSE,Time");

        db.collection("ScoreSentenceBuild")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                dataArr.append("\n"+document.getId()+","+ document.get("score")+","+ document.get("CEFR")+","+ document.get("gse")+","+document.get("time"));

                            }
                            try{

                                FileOutputStream out = openFileOutput("Arrange the Sentences.csv", Context.MODE_PRIVATE);

                                out.write((dataArr.toString()).getBytes());

                                Context context = getApplicationContext();
                                File filelocation = new File(getFilesDir(), "Arrange the Sentences.csv");
                                Uri path = FileProvider.getUriForFile(context,"com.pruebaversant.fileprovider", filelocation);
                                Intent fileIntent = new Intent(Intent.ACTION_SEND);
                                fileIntent.setType("text/csv");
                                fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Arrange the Sentences");
                                fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                fileIntent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
                                fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                                startActivity(Intent.createChooser(fileIntent,"Send Mail"));


                            }catch(Exception e){
                                e.printStackTrace();
                            }


                        } else {
                            Log.d("ScoreSentenceBuild", String.valueOf(task.getException()));
                        }


                    }

                });





    }

    public void Complete_Sentences(View view){

        StringBuilder dataArr= new StringBuilder();
        dataArr.append("Email,Score,CEFR,GSE,Time");

        db.collection("ScoreSentenceCompletition")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                dataArr.append("\n"+document.getId()+","+ document.get("score")+","+ document.get("CEFR")+","+ document.get("gse")+","+document.get("time"));

                            }
                            try{

                                FileOutputStream out = openFileOutput("Complete the sentences.csv", Context.MODE_PRIVATE);

                                out.write((dataArr.toString()).getBytes());

                                Context context = getApplicationContext();
                                File filelocation = new File(getFilesDir(), "Complete the sentences.csv");
                                Uri path = FileProvider.getUriForFile(context,"com.pruebaversant.fileprovider", filelocation);
                                Intent fileIntent = new Intent(Intent.ACTION_SEND);
                                fileIntent.setType("text/csv");
                                fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Complete the sentences");
                                fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                fileIntent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
                                fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                                startActivity(Intent.createChooser(fileIntent,"Send Mail"));


                            }catch(Exception e){
                                e.printStackTrace();
                            }


                        } else {
                            Log.d("ScoreDictation", String.valueOf(task.getException()));
                        }


                    }

                });


    }

    public void Transcription(View view){

        StringBuilder dataArr= new StringBuilder();
        dataArr.append("Email,Score,CEFR,GSE,Time");

        db.collection("ScoreDictation")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                dataArr.append("\n"+document.getId()+","+ document.get("score")+","+ document.get("CEFR")+","+ document.get("gse")+","+document.get("time"));

                            }
                            try{

                                FileOutputStream out = openFileOutput("Transcription.csv", Context.MODE_PRIVATE);

                                out.write((dataArr.toString()).getBytes());

                                Context context = getApplicationContext();
                                File filelocation = new File(getFilesDir(), "Transcription.csv");
                                Uri path = FileProvider.getUriForFile(context,"com.pruebaversant.fileprovider", filelocation);
                                Intent fileIntent = new Intent(Intent.ACTION_SEND);
                                fileIntent.setType("text/csv");
                                fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Transcription");
                                fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                fileIntent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
                                fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                                startActivity(Intent.createChooser(fileIntent,"Send Mail"));


                            }catch(Exception e){
                                e.printStackTrace();
                            }


                        } else {
                            Log.d("ScoreDictation", String.valueOf(task.getException()));
                        }


                    }

                });





    }

}