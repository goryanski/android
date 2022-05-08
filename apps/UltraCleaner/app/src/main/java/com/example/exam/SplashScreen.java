package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView checkboxTick;
    Button startBtn;
    TextView textWarning;
    boolean isCheckboxClicked = false;
    int disabledTextColor;
    int enabledTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        checkboxTick = findViewById(R.id.checkbox_tick);
        switchCheckBox(isCheckboxClicked);

        startBtn = findViewById(R.id.start_btn);
        disabledTextColor = getResources().getColor(R.color.btn_start_disable);
        enabledTextColor = getResources().getColor(R.color.btn_start_enable);
        setStartBtnAvailability(isCheckboxClicked);

        textWarning = findViewById(R.id.text_warning);
        setTextWarningVisible(false);
    }

    public void onCheckboxClicked(View view) {
        isCheckboxClicked = !isCheckboxClicked;
        switchCheckBox(isCheckboxClicked);
        setStartBtnAvailability(isCheckboxClicked);
    }

    private void switchCheckBox(boolean checked) {
        if(checked) {
            checkboxTick.setVisibility(View.VISIBLE);
            setTextWarningVisible(false);
        } else {
            checkboxTick.setVisibility(View.GONE);
        }
    }

    private void setStartBtnAvailability(boolean enabled) {
        if(enabled) {
            startBtn.setTextColor(enabledTextColor);
        } else {
            startBtn.setTextColor(disabledTextColor);
        }
    }

    public void onStartBtnClicked(View view) {
        if(isCheckboxClicked) {
            // go to next activity
            startActivity(new Intent(SplashScreen.this, LoadingScreen.class));
            finish();
        } else {
            setTextWarningVisible(true);
        }
    }

    private void setTextWarningVisible(boolean isVisible) {
        if(isVisible) {
            textWarning.setVisibility(View.VISIBLE);
        } else {
            textWarning.setVisibility(View.GONE);
        }
    }
}

