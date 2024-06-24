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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    Button calculateNumbersBtn, saveBtn, loadBtn;
    EditText insertTextIntoEditText;
    LinearLayout circlesLayout;
    StringBuilder currentDrawingParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        calculateNumbersBtn = findViewById(R.id.calculate_btn);
        insertTextIntoEditText = findViewById(R.id.textField);
        circlesLayout = findViewById(R.id.circles_layout);
        saveBtn = findViewById(R.id.save_btn);
        loadBtn = findViewById(R.id.load_btn);

        calculateNumbersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = insertTextIntoEditText.getText().toString().trim();
                int lettersCount = 0;

                for (int i = 0; i < text.length(); i++) {
                    if (Character.isLetter(text.charAt(i))) {
                        lettersCount++;
                    }
                }

                Toast.makeText(TestActivity.this, "Number of letters: " + lettersCount, Toast.LENGTH_SHORT).show();
                drawCircles(lettersCount);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDrawingParameters();
            }
        });

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDrawingParameters();
            }
        });

        // Uncomment this line if you want to load the saved drawing parameters on activity creation
        // loadDrawingParameters();
    }

    private void drawCircles(int count) {
        circlesLayout.removeAllViews();
        Random random = new Random();
        currentDrawingParams = new StringBuilder();

        for (int i = 0; i < count; i++) {
            int size = random.nextInt(100) + 50;
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

            View circle = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
            int maxWidth = circlesLayout.getWidth() - size;
            int maxHeight = circlesLayout.getHeight() - size;
            int leftMargin = random.nextInt(maxWidth);
            int topMargin = random.nextInt(maxHeight);
            layoutParams.setMargins(leftMargin, topMargin, 0, 0);
            circle.setLayoutParams(layoutParams);
            circle.setBackgroundColor(color);

            circlesLayout.addView(circle);

            currentDrawingParams.append(size).append(",").append(color).append(",").append(leftMargin).append(",").append(topMargin).append("\n");
        }
    }

    private void saveDrawingParameters() {
        if (currentDrawingParams == null || currentDrawingParams.length() == 0) {
            Toast.makeText(this, "No drawing parameters to save.", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(getExternalFilesDir(null), "drawing_parameters.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(currentDrawingParams.toString());
            writer.close();
            Toast.makeText(this, "Drawing parameters saved.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save drawing parameters.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDrawingParameters() {
        File file = new File(getExternalFilesDir(null), "drawing_parameters.txt");
        if (!file.exists()) {
            Toast.makeText(this, "No saved drawing parameters found.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            circlesLayout.removeAllViews();
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(",");
                int size = Integer.parseInt(params[0]);
                int color = Integer.parseInt(params[1]);
                int leftMargin = Integer.parseInt(params[2]);
                int topMargin = Integer.parseInt(params[3]);

                View circle = new View(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
                layoutParams.setMargins(leftMargin, topMargin, 0, 0);
                circle.setLayoutParams(layoutParams);
                circle.setBackgroundColor(color);

                circlesLayout.addView(circle);
            }
            reader.close();
            Toast.makeText(this, "Drawing parameters loaded.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load drawing parameters.", Toast.LENGTH_SHORT).show();
        }
    }
}