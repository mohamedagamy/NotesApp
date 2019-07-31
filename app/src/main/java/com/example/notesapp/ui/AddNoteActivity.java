package com.example.notesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;

import com.example.notesapp.R;
import com.shawnlin.numberpicker.NumberPicker;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        init();
    }

    private void init() {
        AppCompatEditText titleText = findViewById(R.id.et_add_title);
        AppCompatEditText descText = findViewById(R.id.et_add_desc);
        NumberPicker numberPicker = findViewById(R.id.np_add_priority);

        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_TITLE,titleText.getText().toString());
        intent.putExtra(MainActivity.EXTRA_DESC,descText.getText().toString());
        intent.putExtra(MainActivity.EXTRA_ORDER,numberPicker.getValue());
        setResult(RESULT_OK,intent);
    }
}
