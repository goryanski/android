package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    // default fragment
    Fragment fragment = new ChargeBoosterFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check every fragment - if there was optimization already - set menu buttons to optimized state
        SharedPreferences sharedPreferences;

        sharedPreferences = getSharedPreferences("BatterySaverFragmentState", MODE_PRIVATE);
        boolean savedBatteryOptimizationCompleted = sharedPreferences.getBoolean("savedBatteryFragmentState", false);
        if(savedBatteryOptimizationCompleted) {
            ImageButton whiteBatteryBtn = findViewById(R.id.white_battery_btn);
            whiteBatteryBtn.setVisibility(View.VISIBLE);
        }

        sharedPreferences = getSharedPreferences("OptimizerFragmentState", MODE_PRIVATE);
        boolean savedCpuOptimizationCompleted = sharedPreferences.getBoolean("savedOptimizerFragmentState", false);
        if(savedCpuOptimizationCompleted) {
            ImageButton whiteVentilatorBtn = findViewById(R.id.white_ventilator_btn);
            whiteVentilatorBtn.setVisibility(View.VISIBLE);
        }

        sharedPreferences = getSharedPreferences("JunkCleanerFragmentState", MODE_PRIVATE);
        boolean savedJunkCleaningOptimizationCompleted = sharedPreferences.getBoolean("savedJunkCleanerFragmentState", false);
        if(savedJunkCleaningOptimizationCompleted) {
            ImageButton whiteTrashBtn = findViewById(R.id.white_trash_btn);
            whiteTrashBtn.setVisibility(View.VISIBLE);
        }

        // load default fragment (ChargeBoosterFragment)
        loadFragment();
    }

    public void onBtnMenuClicked(View btn) {
        switch (btn.getId()) {
            case R.id.red_rocket_btn:
            case R.id.white_rocket_btn:
                fragment = new ChargeBoosterFragment();
                break;
            case R.id.red_battery_btn:
            case R.id.white_battery_btn:
                fragment = new BatterySaverFragment();
                break;
            case R.id.red_ventilator_btn:
            case R.id.white_ventilator_btn:
                fragment = new OptimizerFragment();
                break;
            case R.id.red_trash_btn:
            case R.id.white_trash_btn:
                fragment = new JunkCleanerFragment();
                break;
        }

        // load selected fragment
        loadFragment();
    }

    private void loadFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fr_place, fragment);
        ft.commit();
    }
}