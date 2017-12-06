package com.example.rogerio.campominado.personal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rogerio.campominado.R;
import com.example.rogerio.campominado.adapters.RecycleViewAdapter;
import com.example.rogerio.campominado.model.PersonalRecord;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PersonalRecordActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<PersonalRecord> records;
    DatabaseHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record);

        records = new ArrayList<>();
        rv = (RecyclerView) findViewById(R.id.recycleView);

        //Defining a Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        //Sqlite
        dbHelper = new DatabaseHelper(this);
        sqLiteDatabase = dbHelper.getReadableDatabase();

        selectPersonalRecords();
    }


    public void selectPersonalRecords() {
        String sql = "Select * from PersonalRecords";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            String str = "";
            do {
                String nickname = cursor.getString(1);
                long timeSpent = Long.parseLong(cursor.getString(2));
                String difficulty = cursor.getString(3);
                records.add(new PersonalRecord(nickname, formatTimeSpent(timeSpent), difficulty));
            } while (cursor.moveToNext());
        }

        RecycleViewAdapter adapter = new RecycleViewAdapter(records);
        rv.setAdapter(adapter);

    }


    public String formatTimeSpent(long timeSpent) {
        String time = String.format(Locale.getDefault(),
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(timeSpent),
                TimeUnit.MILLISECONDS.toSeconds(timeSpent) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeSpent))
        );

        return time;
    }


}
