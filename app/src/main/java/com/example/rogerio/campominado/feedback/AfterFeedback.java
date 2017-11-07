package com.example.rogerio.campominado.feedback;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.rogerio.campominado.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AfterFeedback extends AppCompatActivity {

    TextView teste;
    FirebaseDatabase database;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        teste = (TextView) findViewById(R.id.teste);
        Bundle bundle = getIntent().getExtras();

        teste.setText(bundle.getString("nome")+"\n"+bundle.getString("email")+"\n"+bundle.getInt("nota")+"\n"+bundle.getString("comentario"));

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getInstance().getReference();

        writeNewFeedback(   bundle.getString("nome"),
                        bundle.getString("email"),
                        bundle.getInt("nota")+"",
                        bundle.getString("comentario")
        );


    }

    private void writeNewFeedback(String name, String email,String rate,String comment) {
        Feedback feedback = new Feedback(name, email,rate,comment);


        DatabaseReference feedbackRef = mDatabase.child("Feedbacks");

        // Generate a reference to a new location and add some data using push()
        DatabaseReference pushedFeedbackRef = feedbackRef.push();

        // Get the unique ID generated by a push()
        String feedbackId = pushedFeedbackRef.getKey();


        mDatabase.child("Feedbacks").child(feedbackId).setValue(feedback);
    }

}
