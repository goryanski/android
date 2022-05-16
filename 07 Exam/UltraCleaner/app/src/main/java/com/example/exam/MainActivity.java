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


        /*
        * Instruction how to reset optimizing and start again - just click on image in every fragment (image with robot, battery, cpu or trash basket) -
        * after this you can start optimizing again
        */


        // set menu buttons appearance
        // check every fragment (except default fragment ChargeBoosterFragment) - if there was optimization already - set menu buttons to optimized state
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



        // set fragment to load first
        // check SharedPreferences from final screen (if there chose fragment to load here)
        sharedPreferences = getSharedPreferences("SelectedFragmentOnFinalScreen", MODE_PRIVATE);
        String fragmentName = sharedPreferences.getString("savedFragmentName", "");
        if(!fragmentName.equals("")) {
            switch (fragmentName) {
                case "ChargeBoosterFragment":
                    fragment = new ChargeBoosterFragment();
                    break;
                case "BatterySaverFragment":
                    fragment = new BatterySaverFragment();
                    break;
                case "OptimizerFragment":
                    fragment = new OptimizerFragment();
                    break;
                case "JunkCleanerFragment":
                    fragment = new JunkCleanerFragment();
                    break;
            }
        }

        // if there are no fragment to load from final screen - load default fragment (ChargeBoosterFragment)
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