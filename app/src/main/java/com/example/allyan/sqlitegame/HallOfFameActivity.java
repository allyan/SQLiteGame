package com.example.allyan.sqlitegame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class HallOfFameActivity extends AppCompatActivity {

    private ArrayList<GamerInfo> gamers;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

        DBOpenHelper helper = new DBOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.hallOfFame_list_view);

        Cursor cursor = db.query(
                DBOpenHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            gamers = new ArrayList<>();

            do {
                String name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COL_NAME));
                String time = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COL_TIME));
                int clicks = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.COL_CLICKS));
                gamers.add(new GamerInfo(name, time, clicks));
//                Log.d("Alisher********", name + ", " + time + ", " + clicks);
            } while (cursor.moveToNext());

        }
        for (int i = 0; i < gamers.size(); i++) {
            int[] clicks = new int[gamers.size()];
            clicks[i] =  gamers.get(i).getClickes();
        }

        GamersAdapter adapter = new GamersAdapter(this, gamers);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(HallOfFameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
