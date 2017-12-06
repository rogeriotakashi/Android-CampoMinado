package com.example.rogerio.campominado.settings;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;


import com.example.rogerio.campominado.R;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_easy:
                if (checked)
                    GameSettings.GAME_DIFFICULT = 1;
                    break;
            case R.id.radio_normal:
                if (checked)
                    GameSettings.GAME_DIFFICULT = 2;
                    break;
            case R.id.radio_hard:
                if (checked)
                    GameSettings.GAME_DIFFICULT = 3;
                    break;
            case R.id.radio_insane:
                if (checked)
                    GameSettings.GAME_DIFFICULT = 4;
                    break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
