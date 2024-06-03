package com.example.egzaminui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class OpenGLAnimActivity extends AppCompatActivity {

    private OpenGLView openGLView;
    private CheckBox animationCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the action bar and set window flags for fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_openglanim);

        // Initialize the OpenGLView
        openGLView = findViewById(R.id.opengl_view);

        // Initialize the animation CheckBox
        animationCheckBox = findViewById(R.id.animation_CB);
        animationCheckBox.setOnCheckedChangeListener(onAnimationCheckBoxChange);
    }

    CompoundButton.OnCheckedChangeListener onAnimationCheckBoxChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            openGLView.setAnimationFlag(b);
        }
    };
}
