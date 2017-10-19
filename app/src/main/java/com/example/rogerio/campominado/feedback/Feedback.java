package com.example.rogerio.campominado.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.rogerio.campominado.R;


public class Feedback extends AppCompatActivity {

    EditText edNome;
    EditText edEmail;
    RatingBar nota;
    EditText edComentario;
    Button btnAvaliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edNome = (EditText) findViewById(R.id.editNome);
        edEmail = (EditText) findViewById(R.id.editEmail);
        nota = (RatingBar) findViewById(R.id.ratingBar);
        edComentario = (EditText) findViewById(R.id.edComentario);
        btnAvaliar = (Button) findViewById(R.id.btnAvaliar);


        btnAvaliar = (Button) findViewById(R.id.btnAvaliar);

        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feedback.this, AfterFeedback.class);

                intent.putExtra("nome",edNome.getText().toString());
                intent.putExtra("email",edEmail.getText().toString());
                intent.putExtra("nota",nota.getNumStars());
                intent.putExtra("comentario",edComentario.getText().toString());

                startActivity(intent);
                finish();

            }
        });
    }



}
