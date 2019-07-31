package com.example.notesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.notesapp.R;
import com.shawnlin.numberpicker.NumberPicker;

public class UpdateNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        init();
    }

    private void init() {
        AppCompatEditText titleText = findViewById(R.id.et_update_title);
        AppCompatEditText descText = findViewById(R.id.et_update_desc);
        NumberPicker numberPicker = findViewById(R.id.np_update_priority);

        Bundle intent= getIntent().getExtras();
        titleText.setText(intent.get(MainActivity.EXTRA_TITLE).toString());
        descText.setText(intent.get(MainActivity.EXTRA_DESC).toString());
        numberPicker.setValue(Integer.parseInt(intent.get(MainActivity.EXTRA_ORDER).toString()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setResult(RESULT_OK);
            }
        },5000);
    }
}
