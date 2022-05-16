package com.example.practice05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ChangeColorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] colors;
    String selectedColor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);

        colors = new String[]{"Red", "Blue", "Purple", "White", "LightGreen"};

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.selected_item_spinner_style, colors);
        adapter.setDropDownViewResource(R.layout.dropdown_item_spinner_style);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        selectedColor = (String) adapterView.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onApplyClicked(View view) {
        // send data to main activity
        Intent intent = new Intent(ChangeColorActivity.this, MainActivity.class);
        intent.putExtra("selectedColor", selectedColor);
        startActivity(intent);
        finish();
    }
}