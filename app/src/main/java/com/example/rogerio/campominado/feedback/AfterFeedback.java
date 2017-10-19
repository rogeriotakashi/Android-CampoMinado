package com.example.rogerio.campominado.feedback;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.rogerio.campominado.R;

public class AfterFeedback extends AppCompatActivity {

    TextView teste;

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


    }

}
