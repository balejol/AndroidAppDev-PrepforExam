package com.example.egzaminui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// * Sample of exam task: make an SQLITE based Doodle style app; draw a weather history curve using this API (xxx);
// add a button which will take 4 pictures and display as 2x2 collage; draw some shapes in a predefined manner.
//
// The task will have numerous subtasks to complete, e.g.: Add an input field where you could enter some text
// and a button (1). Once clicked, your app must calculate the number of letters of a word entered (1) and
// draw an appropriate number of circles in random (1) locations (e.g. RYTIS = 5 circles) (1). A circle size (1),
// border (1) and fill colour (1) should be random. Add code so the app retains the drawing parameters (draws the
// same image on next run/activity refresh (same size and colour values) (1). Add buttons to save these generated
// parameters to external (1) (out of app), browsable file (format of your choice) and a functionality to read this file (1).
//
// You will be free to choose which parts you want to implement (completing everything is 10).

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}