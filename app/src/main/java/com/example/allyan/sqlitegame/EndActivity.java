package com.example.allyan.sqlitegame;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EndActivity extends AppCompatActivity implements View.OnClickListener {

    private int counter;
    private EditText gamerNameET;
    private TextView numOfClickes;
    private SharedPreferences settings;
    private long timerTime;
    private String timerLength = "TimerLength";
    private TextView timerTV;
    private SQLiteDatabase db;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent intent = getIntent();
        counter = intent.getIntExtra("counter", -1);


        numOfClickes = (TextView) findViewById(R.id.activity_end_textView_numOfClickes);
        numOfClickes.setText(counter + "");

        gamerNameET = (EditText) findViewById(R.id.activity_end_editText_name);

        findViewById(R.id.activity_end_button_save).setOnClickListener(this);

        settings = getSharedPreferences(getString(R.string.shared_prefs_file_name), MODE_PRIVATE);
        timerTime = (Integer.parseInt(settings.getString(timerLength, null))) * 1000;
        timerTV = (TextView) findViewById(R.id.activity_end_time_textViev);
        timerTV.setText(new SimpleDateFormat("mm:ss:SS").format(new Date(timerTime)));

        time = new SimpleDateFormat("mm:ss:SS").format(new Date(timerTime));

        DBOpenHelper helper = new DBOpenHelper(this);
        db = helper.getWritableDatabase();
    }

    private void insertData(String name, String time, int clickes) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COL_NAME, name);
        values.put(DBOpenHelper.COL_TIME, time);
        values.put(DBOpenHelper.COL_CLICKS, clickes);
        db.insert(
                DBOpenHelper.TABLE_NAME,
                null,
                values
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EndActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(gamerNameET.getText().toString().isEmpty()){
            gamerNameET.setError("Can`t be empty");
        }
        String numberClicks = numOfClickes.getText().toString();
        int clickes = Integer.parseInt(numberClicks);
        String gamerName = gamerNameET.getText().toString().trim();
        if(!gamerName.isEmpty()){
            insertData(gamerName, time, clickes);
            Intent intent = new Intent(EndActivity.this, HallOfFameActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
