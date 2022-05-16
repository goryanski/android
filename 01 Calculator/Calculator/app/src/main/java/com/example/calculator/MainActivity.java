package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView fieldOperand1;
    TextView fieldOperator;
    TextView fieldOperand2;
    TextView fieldResult;

    Button btnDivide;
    Button btnMultiply;
    Button btnMinus;
    Button btnPlus;

    boolean firstOperandEntered;
    int maxOperandValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fields
        fieldOperand1 = findViewById(R.id.operand1);
        fieldOperator = findViewById(R.id.operator);
        fieldOperand2 = findViewById(R.id.operand2);
        fieldResult = findViewById(R.id.result);


        // buttons 0-9
        Button[] numberButtons = new Button[10];
        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = getButtonByNumber(i);
            numberButtons[i].setOnClickListener(this);
        }


        // rest buttons
        btnDivide = findViewById(R.id.division);
        btnMultiply = findViewById(R.id.multiplying);
        btnMinus = findViewById(R.id.subtraction);
        btnPlus = findViewById(R.id.adding);
        btnDivide.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);

        Button cleanLast = findViewById(R.id.cleanLast);
        Button cleanAll = findViewById(R.id.cleanAll);
        Button btnDot = findViewById(R.id.dot);
        Button result = findViewById(R.id.showResult);
        cleanLast.setOnClickListener(this);
        cleanAll.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        result.setOnClickListener(this);


        firstOperandEntered = false;
        maxOperandValue = 12;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cleanLast:
                // for first TextView
                if(!firstOperandEntered && firstFieldIsNotEmpty()) {
                    removeLastSymbol(fieldOperand1);
                }
                // for third TextView
                else if(firstOperandEntered && thirdFieldIsNotEmpty()) {
                    removeLastSymbol(fieldOperand2);
                }
                break;
            case R.id.cleanAll:
                cleanAllFields();
                break;
            case R.id.division:
                setOperator(btnDivide);
                break;
            case R.id.multiplying:
                setOperator(btnMultiply);
                break;
            case R.id.subtraction:
                setOperator(btnMinus);
                break;
            case R.id.adding:
                setOperator(btnPlus);
                break;
            case R.id.showResult:
                if(firstFieldIsNotEmpty() && thirdFieldIsNotEmpty()) {
                    double value1 = Double.parseDouble(fieldOperand1.getText().toString());
                    double value2 = Double.parseDouble(fieldOperand2.getText().toString());
                    char operator = fieldOperator.getText().charAt(0);
                    double res = 0;
                    switch (operator) {
                        case '+':
                            res = value1 + value2;
                            break;
                        case '-':
                            res = value1 - value2;
                            break;
                        case '*':
                            res = value1 * value2;
                            break;
                        case '/':
                            res = value2 != 0 ?  value1 / value2 : 0;
                            break;
                    }
                    res = Math.round(res * 100.0) / 100.0; // round to 2 digit after point
                    fieldResult.setText(String.format("= %s", res));
                }
                break;
            default:
                // buttons 0-9 and dot
                // for first TextView
                if(!firstOperandEntered) {
                    if(!tooLongNumber(fieldOperand1)) {
                        if(view.getId() != R.id.dot || view.getId() == R.id.dot && canSetDot(fieldOperand1)) {
                            fieldOperand1.setText(String.format("%s%s", fieldOperand1.getText(), getButtonById(view.getId()).getText()));
                        }
                    }
                }
                // for third TextView
                else {
                    if(!tooLongNumber(fieldOperand2)) {
                        if(view.getId() != R.id.dot || view.getId() == R.id.dot && canSetDot(fieldOperand2)) {
                            fieldOperand2.setText(String.format("%s%s", fieldOperand2.getText(), getButtonById(view.getId()).getText()));
                        }
                    }
                }
                break;
        }
    }

    private void cleanAllFields() {
        fieldOperand1.setText("");
        fieldOperator.setText("");
        fieldOperand2.setText("");
        fieldResult.setText("");
        firstOperandEntered = false;
    }

    private boolean tooLongNumber(TextView field) {
        return field.getText().length() >= maxOperandValue;
    }

    private Button getButtonByNumber(int btnNumber) {
        switch (btnNumber) {
            case 0: return findViewById(R.id.button0);
            case 1: return findViewById(R.id.button1);
            case 2: return findViewById(R.id.button2);
            case 3: return findViewById(R.id.button3);
            case 4: return findViewById(R.id.button4);
            case 5: return findViewById(R.id.button5);
            case 6: return findViewById(R.id.button6);
            case 7: return findViewById(R.id.button7);
            case 8: return findViewById(R.id.button8);
            case 9: return findViewById(R.id.button9);
        }
        return findViewById(R.id.button0); // default (never reaches, just avoid warnings)
    }

    private Button getButtonById(int id) {
        return findViewById(id);
    }

    private boolean canSetDot(TextView field) {
        String text = field.getText().toString();
        // can't set if it's exists already or if it's a first symbol
        return !text.contains(".") && !text.equals("");
    }

    private boolean firstFieldIsNotEmpty() {
        return !fieldOperand1.getText().equals("");
    }

    private boolean thirdFieldIsNotEmpty() {
        return !fieldOperand2.getText().equals("");
    }

    private void removeLastSymbol(TextView field) {
        String text = field.getText().toString();
        field.setText(text.substring(0, text.length() - 1));
    }

    private void setOperator(Button button) {
        if(firstFieldIsNotEmpty()) {
            fieldOperator.setText(button.getText());
            if(!firstOperandEntered) {
                firstOperandEntered = true;
            }
        }
    }
}