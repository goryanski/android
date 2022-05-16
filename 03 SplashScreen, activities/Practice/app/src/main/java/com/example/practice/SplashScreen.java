package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    int disabledTextColor;
    int enabledTextColor;
    Button startBtn;
    TextView textWarning;
    boolean isStartBtnEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        disabledTextColor = getResources().getColor(R.color.white);
        enabledTextColor = getResources().getColor(R.color.black);

        startBtn = findViewById(R.id.start_btn);
        setStartBtnEnabled(false);

        textWarning = findViewById(R.id.text_warning);
        setTextWarningVisible(false);
    }

    public void onCheckboxClicked(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked()) {
            setStartBtnEnabled(true);
            setTextWarningVisible(false);
        } else {
            setStartBtnEnabled(false);
        }
    }

    public void onBtnStartClicked(View view) {
        if(isStartBtnEnabled) {
            // go to next activity
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        } else {
            setTextWarningVisible(true);
        }
    }

    private void setStartBtnEnabled(boolean isEnabled) {
        if(isEnabled) {
            startBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.enabled_button));
            startBtn.setTextColor(enabledTextColor);
            isStartBtnEnabled = true;
        } else {
            startBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.disabled_button));
            startBtn.setTextColor(disabledTextColor);
            isStartBtnEnabled = false;
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