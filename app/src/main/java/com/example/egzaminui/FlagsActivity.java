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

public class FlagsActivity extends AppCompatActivity {

    private DrawView drawView;
    private Button btnColor, btnSize, btnRotate, btnSave;

    private ObjectAnimator rotation;
    float  currentRotationAngle = 0;
    private int color1, color2, color3, size1, size2, size3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags);

        drawView = (DrawView) findViewById(R.id.drawView);
        btnColor = (Button) findViewById(R.id.colour_btn);
        btnSize = (Button) findViewById(R.id.width_btn);
        btnRotate = (Button) findViewById(R.id.rotate_btn);
        btnSave = (Button) findViewById(R.id.save_btn);

        loadDrawingParameters();

        rotation = new ObjectAnimator();
        btnRotate.setOnTouchListener(onTouchView);
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c1 = getRandColor();
                int c2 = getRandColor();
                int c3 = getRandColor();
                drawView.setCircleParams(c1, c2, c3, drawView.getSize1(), drawView.getSize2(), drawView.getSize3());
            }
        });

        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s1 = getRandSize();
                int s2 = getRandSize();
                int s3 = getRandSize();

                drawView.setCircleParams(drawView.getColor1(), drawView.getColor2(), drawView.getColor3(), s1, s2, s3);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDrawingParameters(drawView.getColor1(), drawView.getColor2(), drawView.getColor3(),
                        drawView.getSize1(), drawView.getSize2(), drawView.getSize3());
            }
        });
    }

    private int getRandColor(){
        Random rand = new Random();
        int c = (Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        return c;
    }

    private int getRandSize(){
        Random rand = new Random();
        int size = rand.nextInt(200) + 50;
        return size;
    }

    private final View.OnTouchListener onTouchView = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                if (!rotation.isRunning()) {

                    if (currentRotationAngle == 0)
                        currentRotationAngle += 45.0f;
                    else
                        currentRotationAngle = 0;

                    rotation = ObjectAnimator.ofFloat(drawView, "rotation",
                            currentRotationAngle);
                    rotation.setDuration(800);
                    rotation.setInterpolator(new AccelerateDecelerateInterpolator());
                    rotation.start();

                }  else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Animation in progress!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            return true;  // Indicate that the touch event has been handled
        }
    };

    private void saveDrawingParameters(int c1, int c2, int c3,
                                       int s1, int s2, int s3) {
        File file = new File(getExternalFilesDir(null), "drawing_parameters.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(c1 + "," + c2 + "," + c3 + "," +
                    s1 + "," + s2 + "," + s3);
            writer.newLine();
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
            drawView.setCircleParams(Color.YELLOW, Color.GREEN, Color.RED, 100, 100, 100);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(",");
                color1 = (Integer.parseInt(params[0]));
                color2 = (Integer.parseInt(params[1]));
                color3 = (Integer.parseInt(params[2]));
                size1 = (Integer.parseInt(params[3]));
                size2 = (Integer.parseInt(params[4]));
                size3 = (Integer.parseInt(params[5]));
            }
            reader.close();

            drawView.setCircleParams(color1, color2, color3, size1, size2, size3);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load drawing parameters.", Toast.LENGTH_SHORT).show();
        }
    }

}