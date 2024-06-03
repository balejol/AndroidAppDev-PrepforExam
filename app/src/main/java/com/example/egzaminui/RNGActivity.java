package com.example.egzaminui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button; // new for button
import android.widget.EditText; // new for editText

import android.os.Bundle;
import android.widget.Toast;

public class RNGActivity extends AppCompatActivity {

    Button generateRandomNumberBtn;
    EditText insertRandomNumberEditText;
    int randomNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rng);

        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        generateRandomNumberBtn = (Button) findViewById(R.id.generateRandomNumberBtn);

        generateRandomNumberBtn.setOnClickListener(generateRandomNumberOnClick);
        insertRandomNumberEditText = (EditText) findViewById(R.id.editText);
        insertRandomNumberEditText.addTextChangedListener(insertRandomNumberEditTextWatcher);
    }

    View.OnClickListener generateRandomNumberOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            randomNumber = (int) (Math.random()*1000);
            Log.i("randomNumber", String.valueOf(randomNumber)); // To view this log: open Logcat (kitty icon on the botton left corner)
            // On the screen is showed what random number is generated :
            Toast toast = Toast.makeText(getApplicationContext(),
                    String.valueOf(randomNumber), Toast.LENGTH_SHORT);
            toast.show();
        }
    };
    TextWatcher insertRandomNumberEditTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().length()>0) {
                if (Integer.valueOf(s.toString()) == randomNumber)
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                else
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
            }
            else {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void afterTextChanged(Editable s) { }
    };

}