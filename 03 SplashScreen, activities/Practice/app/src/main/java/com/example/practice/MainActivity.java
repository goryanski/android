package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView slash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slash = findViewById(R.id.slash);
        slash.setVisibility(View.GONE);

        try {
            String cardNumber = getIntent().getExtras().getString("cardNumber");
            String cardMonth = getIntent().getExtras().getString("cardMonth");
            String cardYear = getIntent().getExtras().getString("cardYear");
            String cardCvv = getIntent().getExtras().getString("cardCvv");
            String cardHolder = getIntent().getExtras().getString("cardHolder");

            TextView cardNumberLabel = findViewById(R.id.card_number_label);
            TextView cardMonthLabel = findViewById(R.id.month_label);
            TextView cardYearLabel = findViewById(R.id.year_label);
            TextView cardCvvLabel = findViewById(R.id.cvv_label);
            TextView cardHolderLabel = findViewById(R.id.card_holder_label);
            cardNumberLabel.setText(cardNumber);
            cardMonthLabel.setText(cardMonth);
            cardYearLabel.setText(cardYear);
            cardCvvLabel.setText(cardCvv);
            cardHolderLabel.setText(cardHolder);

            slash.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void onBtnFillClicked(View view) {
        // go to next activity
        startActivity(new Intent(MainActivity.this, InputDataActivity.class));
        finish();
    }
}