package com.example.egzaminui;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

// Nupieškite vėliavą naudodami canvas (2), pridėkite animaciją (pvz. sukimąsi (1)), pridėkite mygtuką, kad būtų
// galima keisti kiekvieno šios konstrukcijos elemento storį (1), pridėkite mygtukus, kad būtų galima išsaugoti
// (išsaugoti programoje) (1) ir įkelti (1) jūsų vėliavos atvaizdus (su pakoreguotomis spalvomis / storiu),
// pridėkite mygtuką, kad būtų galima išsaugoti jūsų vėliavos atvaizdą (su pakoreguotomis spalvomis / storiu) į
// išorinį (1) (ne programoje), naršomą xml failą (1) ir funkciją, kad būtų galima peržiūrėti ir įkelti šį failą (1).

public class FlagsActivity extends AppCompatActivity {

    // Declare UI components and other variables
    private DrawView drawView;
    private Button btnColor, btnSize, btnRotate, btnSave, btnImport;
    private ObjectAnimator rotation;
    float currentRotationAngle = 0;
    private int color1, color2, color3, size1, size2, size3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags);

        // Initialize UI components
        drawView = findViewById(R.id.drawView);
        btnColor = findViewById(R.id.colour_btn);
        btnSize = findViewById(R.id.width_btn);
        btnRotate = findViewById(R.id.rotate_btn);
        btnSave = findViewById(R.id.save_btn);
        btnImport = findViewById(R.id.import_btn);

        // Load previously saved drawing parameters
        loadDrawingParameters();

        // Initialize the ObjectAnimator for rotation
        rotation = new ObjectAnimator();
        btnRotate.setOnTouchListener(onTouchView);

        // Set click listener for color button
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate random colors and set them to the DrawView
                int c1 = getRandColor();
                int c2 = getRandColor();
                int c3 = getRandColor();
                drawView.setCircleParams(c1, c2, c3, drawView.getSize1(), drawView.getSize2(), drawView.getSize3());
            }
        });

        // Set click listener for size button
        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate random sizes and set them to the DrawView
                int s1 = getRandSize();
                int s2 = getRandSize();
                int s3 = getRandSize();
                drawView.setCircleParams(drawView.getColor1(), drawView.getColor2(), drawView.getColor3(), s1, s2, s3);
            }
        });

        // Set click listener for save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the current drawing parameters
                saveDrawingParameters(drawView.getColor1(), drawView.getColor2(), drawView.getColor3(),
                        drawView.getSize1(), drawView.getSize2(), drawView.getSize3());
            }
        });

        // Set click listener for import button
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load and apply the previously saved drawing parameters
                loadDrawingParameters();
            }
        });
    }

    // Generate a random color
    private int getRandColor() {
        Random rand = new Random();
        return Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    // Generate a random size
    private int getRandSize() {
        Random rand = new Random();
        return rand.nextInt(200) + 50;
    }

    // Touch listener for the rotate button
    private final View.OnTouchListener onTouchView = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (!rotation.isRunning()) {
                    // Toggle rotation angle between 0 and 45 degrees
                    if (currentRotationAngle == 0)
                        currentRotationAngle += 45.0f;
                    else
                        currentRotationAngle = 0;

                    // Configure and start the rotation animation
                    rotation = ObjectAnimator.ofFloat(drawView, "rotation", currentRotationAngle);
                    rotation.setDuration(800);
                    rotation.setInterpolator(new AccelerateDecelerateInterpolator());
                    rotation.start();
                } else {
                    // Show a toast message if animation is in progress
                    Toast.makeText(getApplicationContext(), "Animation in progress!", Toast.LENGTH_SHORT).show();
                }
            }

            return true; // Indicate that the touch event has been handled
        }
    };

    // Save drawing parameters to a file
    private void saveDrawingParameters(int c1, int c2, int c3, int s1, int s2, int s3) {
        File file = new File(getExternalFilesDir(null), "drawing_parameters.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(c1 + "," + c2 + "," + c3 + "," + s1 + "," + s2 + "," + s3);
            writer.newLine();
            writer.close();
            Toast.makeText(this, "Drawing parameters saved.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save drawing parameters.", Toast.LENGTH_SHORT).show();
        }
    }

    // Load drawing parameters from a file
    private void loadDrawingParameters() {
        File file = new File(getExternalFilesDir(null), "drawing_parameters.txt");
        if (!file.exists()) {
            // Set default parameters if the file doesn't exist
            drawView.setCircleParams(Color.YELLOW, Color.GREEN, Color.RED, 100, 100, 100);
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the parameters from the file
                String[] params = line.split(",");
                color1 = Integer.parseInt(params[0]);
                color2 = Integer.parseInt(params[1]);
                color3 = Integer.parseInt(params[2]);
                size1 = Integer.parseInt(params[3]);
                size2 = Integer.parseInt(params[4]);
                size3 = Integer.parseInt(params[5]);
            }
            reader.close();

            // Set the parameters to the DrawView
            drawView.setCircleParams(color1, color2, color3, size1, size2, size3);
            Toast.makeText(this, "Drawing parameters loaded.", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load drawing parameters.", Toast.LENGTH_SHORT).show();
        }
    }
}
