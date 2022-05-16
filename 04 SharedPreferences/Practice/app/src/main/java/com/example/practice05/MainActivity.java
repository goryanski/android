package com.example.practice05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int savedTextGravity = -1;
    int savedTextColor = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        // restore saved values from SharedPreferences
        SharedPreferences sp = getApplicationContext().getSharedPreferences("savedData", Context.MODE_PRIVATE);
        int restoredTextGravity = sp.getInt("savedTextGravity", 0);
        int restoredTextColor = sp.getInt("savedTextColor", 0);


        // set color
        try {
            String selectedColor = getIntent().getExtras().getString("selectedColor");
            setSelectedColor(selectedColor);

        } catch (Exception e) {
            e.fillInStackTrace();
            if(restoredTextColor == -1) {
                // set default color
                textView.setTextColor(getResources().getColor(R.color.LightGreen));
            } else {
                textView.setTextColor(restoredTextColor);
            }
        }


        // set alignment
        try {
            String alignment = getIntent().getExtras().getString("alignment");
            setSelectedAlignment(alignment);

        } catch (Exception e) {
            e.fillInStackTrace();
            if(restoredTextGravity == -1) {
                // set default alignment
                textView.setGravity(Gravity.CENTER);
            } else {
                textView.setGravity(restoredTextGravity);
            }
        }
    }

    public void onChangeColorClicked(View view) {
        startActivity(new Intent(MainActivity.this, ChangeColorActivity.class));
    }

    public void onChangeAlignmentClicked(View view) {
        startActivity(new Intent(MainActivity.this, ChangeAlignmentActivity.class));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // every time when we go out from this activity - save data
        SharedPreferences sharedPreferences = getSharedPreferences("savedData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("savedTextColor", savedTextColor);
        editor.putInt("savedTextGravity", savedTextGravity);
        editor.commit();
    }


    private void setSelectedColor(String selectedColor) {
        switch (selectedColor) {
            case "Red":
                textView.setTextColor(getResources().getColor(R.color.Red));
                break;
            case "Blue":
                textView.setTextColor(getResources().getColor(R.color.Blue));
                break;
            case "Purple":
                textView.setTextColor(getResources().getColor(R.color.Purple));
                break;
            case "White":
                textView.setTextColor(getResources().getColor(R.color.White));
                break;
            case "LightGreen":
                textView.setTextColor(getResources().getColor(R.color.LightGreen));
                break;
        }
        // save every selected color to save it to SharedPreferences on onSaveInstanceState()
        savedTextColor = textView.getCurrentTextColor();
    }

    private void setSelectedAlignment(String alignment) {
        switch (alignment) {
            case "left":
                textView.setGravity(Gravity.START);
                break;
            case "center":
                textView.setGravity(Gravity.CENTER);
                break;
            case "right":
                textView.setGravity(Gravity.END);
                break;
        }
        // save every selected gravity to save it to SharedPreferences on onSaveInstanceState()
        savedTextGravity = textView.getGravity();
    }
}