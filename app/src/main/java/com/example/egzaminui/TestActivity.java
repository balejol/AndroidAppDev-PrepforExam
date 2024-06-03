package com.example.egzaminui;

// > Create Virtual Device
// > Pixel 2 | 5,0" | 1080x1920 | 420dpi
// > Q | 29 | x86 | Android 10.0

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

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

    Button calculateNumbersBtn;
    EditText insertTextIntoEditText;
    LinearLayout circlesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        calculateNumbersBtn = findViewById(R.id.calculate_btn);
        insertTextIntoEditText = findViewById(R.id.textField);
        circlesLayout = findViewById(R.id.circles_layout);

        calculateNumbersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = insertTextIntoEditText.getText().toString().trim();
                int lettersCount = 0;

                // Loop through each character in the text and count letters
                for (int i = 0; i < text.length(); i++) {
                    if (Character.isLetter(text.charAt(i))) {
                        lettersCount++;
                    }
                }

                // Display the count of letters in a toast message
                Toast.makeText(TestActivity.this, "Number of letters: " + lettersCount, Toast.LENGTH_SHORT).show();

                // Draw circles
                drawCircles(lettersCount);
            }
        });
    }

    private void drawCircles(int count) {
        circlesLayout.removeAllViews(); // Clear previous circles

        Random random = new Random();

        for (int i = 1; i <= count; i++) {
            // Generate random circle parameters
            int size = random.nextInt(150) + 50; // Random size between 50 and 200
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)); // Random color

            // Create circle ImageView
            ImageView circle = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
            circle.setLayoutParams(layoutParams);
            circle.setImageResource(R.drawable.ic_rec);
            circle.setColorFilter(color);

            // Set random position within bounds of LinearLayout
            int maxWidth = circlesLayout.getWidth() - size; // Maximum width to ensure circle stays within bounds
            int maxHeight = circlesLayout.getHeight() - size; // Maximum height to ensure circle stays within bounds
            int leftMargin = random.nextInt(maxWidth);
            int topMargin = random.nextInt(maxHeight);
            layoutParams.setMargins(leftMargin, topMargin, 0, 0);
            circle.setLayoutParams(layoutParams);

            circlesLayout.addView(circle);
        }
    }

}