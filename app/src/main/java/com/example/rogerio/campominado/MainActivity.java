package com.example.rogerio.campominado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rogerio.campominado.adapters.MenuAdapter;
import com.example.rogerio.campominado.feedback.FeedbackActivity;
import com.example.rogerio.campominado.game.GameActivity;
import com.example.rogerio.campominado.leaderboard.LeaderboardActivity;
import com.example.rogerio.campominado.personal.PersonalRecordActivity;
import com.example.rogerio.campominado.menu.Menu_Item;
import com.example.rogerio.campominado.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Menu_Item> listMenuItem;
    MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ListView
        listView = (ListView) findViewById(R.id.listaAlbum);
        listMenuItem = new ArrayList<>();

        //Menu
        listMenuItem.add(new Menu_Item("icon1", "Play"));
        listMenuItem.add(new Menu_Item("icon5", "LeaderBoard"));
        listMenuItem.add(new Menu_Item("icon2", "Personal Records"));
        listMenuItem.add(new Menu_Item("icon3", "Settings"));
        listMenuItem.add(new Menu_Item("icon4", "Feedback"));

        //Adapter
        adapter = new MenuAdapter(this, listMenuItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, GameActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, LeaderboardActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, PersonalRecordActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, FeedbackActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
