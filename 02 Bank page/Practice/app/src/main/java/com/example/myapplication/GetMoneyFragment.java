package com.example.myapplication;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GetMoneyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetMoneyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GetMoneyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GetMoneyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GetMoneyFragment newInstance(String param1, String param2) {
        GetMoneyFragment fragment = new GetMoneyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get view to use method findViewById()
        View view = inflater.inflate(R.layout.fragment_get_money, container, false);

        // fields validation
        EditText cardNumberField = view.findViewById(R.id.card_number_input);
        cardNumberField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                boolean valid = false;
                if(!hasFocus && cardNumberField.length() != 0) {
                    if(cardNumberField.length() == 12) {
                        valid = true;
                    }
                }
                if(valid) {
                    cardNumberField.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                } else {
                    cardNumberField.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        EditText cardMonthField = view.findViewById(R.id.month_input);
        cardMonthField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                boolean valid = false;
                if(!hasFocus && cardMonthField.length() != 0) {
                    if(cardMonthField.length() == 2) {
                        int firstNumber = Character.getNumericValue(cardMonthField.getText().toString().charAt(0));
                        int secondNumber = Character.getNumericValue(cardMonthField.getText().toString().charAt(1));

                        if((firstNumber == 0 && (secondNumber >= 1 && secondNumber <= 9)) ||
                                firstNumber == 1 && (secondNumber >= 0 && secondNumber <= 2)) {
                            valid = true;
                        }
                    }
                }
                if(valid) {
                    cardMonthField.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                } else {
                    cardMonthField.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        EditText cardYearField = view.findViewById(R.id.year_input);
        cardYearField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                boolean valid = false;
                if(!hasFocus && cardYearField.length() != 0) {
                    int year = Integer.parseInt(cardYearField.getText().toString());
                    Calendar date = Calendar.getInstance();
                    int currentYear = date.get(Calendar.YEAR);

                    if(year > currentYear && year < currentYear + 4) {
                        valid = true;
                    }
                }
                if(valid) {
                    cardYearField.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                } else {
                    cardYearField.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        EditText cardCvvField = view.findViewById(R.id.cvv_input);
        cardCvvField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                boolean valid = false;
                if(!hasFocus && cardCvvField.length() != 0) {
                    if(cardCvvField.length() == 3) {
                        valid = true;
                    }
                }
                if(valid) {
                    cardCvvField.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                } else {
                    cardCvvField.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        return view;
    }
}