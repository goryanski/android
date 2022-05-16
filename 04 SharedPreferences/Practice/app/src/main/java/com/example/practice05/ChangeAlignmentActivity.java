package com.example.practice05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class ChangeAlignmentActivity extends AppCompatActivity {

    String alignment = "center";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_alignment);
    }

    public void onRadioButtonClicked(View view) {
        // if RadioButton checked
        boolean checked = ((RadioButton) view).isChecked();
        // get him
        switch(view.getId()) {
            case R.id.left:
                if (checked){
                    alignment = "left";
                }
                break;
            case R.id.center:
                if (checked){
                    alignment = "center";
                }
                break;
            case R.id.right:
                if (checked){
                    alignment = "right";
                }
                break;
        }
    }

    public void onApplyClicked(View view) {
        // send data to main activity
        Intent intent = new Intent(ChangeAlignmentActivity.this, MainActivity.class);
        intent.putExtra("alignment", alignment);
        startActivity(intent);
        finish();
    }
}