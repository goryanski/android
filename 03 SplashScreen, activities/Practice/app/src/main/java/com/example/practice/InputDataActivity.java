package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class InputDataActivity extends AppCompatActivity {
    EditText cardNumberField;
    EditText cardMonthField;
    EditText cardYearField;
    EditText cardCvvField;
    EditText cardHolderField;

    TextView errorLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        errorLabel = findViewById(R.id.error_label);

        // changes fields background on focus lost
        cardNumberField = findViewById(R.id.card_number_input);
        cardNumberField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isCardNumberValid(cardNumberField)) {
                    cardNumberField.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                } else {
                    cardNumberField.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        cardMonthField = findViewById(R.id.month_input);
        cardMonthField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isCardMonthValid(cardMonthField)) {
                    cardMonthField.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                } else {
                    cardMonthField.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        cardYearField = findViewById(R.id.year_input);
        cardYearField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isCardYearValid(cardYearField)) {
                    cardYearField.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                } else {
                    cardYearField.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        cardCvvField = findViewById(R.id.cvv_input);
        cardCvvField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isCardCvvValid(cardCvvField)) {
                    cardCvvField.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                } else {
                    cardCvvField.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        cardHolderField = findViewById(R.id.card_holder_input);
        cardHolderField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isCardHolderValid(cardHolderField)) {
                    cardHolderField.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                } else {
                    cardHolderField.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });
    }

    public void onBtnSaveClicked(View view) {
        String errors = findDataErrors();

        if(!errors.equals("")) {
            // show errors
            errorLabel.setText(errors);
        } else  {
            // send params to main activity
            Intent intent = new Intent(InputDataActivity.this, MainActivity.class);
            intent.putExtra("cardNumber", cardNumberField.getText().toString());
            intent.putExtra("cardMonth", cardMonthField.getText().toString());
            intent.putExtra("cardYear", cardYearField.getText().toString());
            intent.putExtra("cardCvv", cardCvvField.getText().toString());
            intent.putExtra("cardHolder", cardHolderField.getText().toString());
            startActivity(intent);
            finish();
        }
    }

    private String findDataErrors() {
        String errors = "";
        if(!isCardNumberValid(cardNumberField)) {
            errors += "Card number is invalid\n";
        }
        if(!isCardMonthValid(cardMonthField)) {
            errors += "Month is invalid\n";
        }
        if(!isCardYearValid(cardYearField)) {
            errors += "Year is invalid\n";
        }
        if(!isCardCvvValid(cardCvvField)) {
            errors += "Cvv is invalid\n";
        }
        if(!isCardHolderValid(cardHolderField)) {
            errors += "Card holder name is invalid\n";
        }
        return errors;
    }




    // fields validation
    private boolean isCardNumberValid(EditText field) {
        return field.length() == 12;
    }

    private boolean isCardMonthValid(EditText field) {
        boolean valid = false;
        if(field.length() == 2) {
            int firstNumber = Character.getNumericValue(field.getText().toString().charAt(0));
            int secondNumber = Character.getNumericValue(field.getText().toString().charAt(1));

            if((firstNumber == 0 && (secondNumber >= 1 && secondNumber <= 9)) ||
                    firstNumber == 1 && (secondNumber >= 0 && secondNumber <= 2)) {
                valid = true;
            }
        }
        return valid;
    }

    private boolean isCardYearValid(EditText field) {
        boolean valid = false;
        if(field.length() != 0) {
            int year = Integer.parseInt(field.getText().toString());
            Calendar date = Calendar.getInstance();
            int currentYear = date.get(Calendar.YEAR);

            if(year > currentYear && year < currentYear + 4) {
                valid = true;
            }
        }
        return valid;
    }

    private boolean isCardCvvValid(EditText field) {
        return field.length() == 3;
    }

    private boolean isCardHolderValid(EditText field) {
        String text = field.getText().toString();

        // string must contains only letters and one space
        boolean stringContainSpace = false;
        if(text.length() > 6) {
            for (int i = 0; i < text.length(); i++) {
                if(!Character.isLetter(text.charAt(i))) {
                    if(text.charAt(i) == ' ') {
                        if(stringContainSpace) {
                            return false;
                        } else {
                            stringContainSpace = true;
                        }
                    }
                    else {
                        // neither letter nor space
                        return false;
                    }
                }
            }
            // cycle ends without a return
            return true;
        }
        return false;
    }
}