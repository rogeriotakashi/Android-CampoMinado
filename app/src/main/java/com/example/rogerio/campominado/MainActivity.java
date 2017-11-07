package com.example.rogerio.campominado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rogerio.campominado.adapters.MenuAdapter;
import com.example.rogerio.campominado.feedback.FeedbackActivity;
import com.example.rogerio.campominado.leaderboard.Leaderboard;
import com.example.rogerio.campominado.menu.Menu_Item;
import com.example.rogerio.campominado.settings.Settings;

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
        listMenuItem = new ArrayList<Menu_Item>();



        //Adicionar Albuns
        listMenuItem.add(new Menu_Item("icon1","Play"));
        listMenuItem.add(new Menu_Item("icon2","LeaderBoard"));
        listMenuItem.add(new Menu_Item("icon3","Settings"));
        listMenuItem.add(new Menu_Item("icon4","Feedback"));


        //Adapter
        adapter = new MenuAdapter(this, listMenuItem);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, Game.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, Leaderboard.class);
                    startActivity(intent);

                }

                if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, Settings.class);
                    startActivity(intent);

                }

                if (position == 3) {
                    Intent intent = new Intent(MainActivity.this, FeedbackActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
