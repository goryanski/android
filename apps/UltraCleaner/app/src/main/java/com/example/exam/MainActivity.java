package com.example.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment fragment = new ChargeBoosterFragment();

    ImageButton redRocketBtn;
//    ImageButton redBatteryBtn;
//    ImageButton redVentilatorBtn;
//    ImageButton redTrashBtn;
    ImageButton currentRedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton redRocketBtn = findViewById(R.id.red_rocket_btn);
//        redBatteryBtn = findViewById(R.id.red_battery_btn);
//        redVentilatorBtn = findViewById(R.id.red_ventilator_btn);
//        redTrashBtn = findViewById(R.id.red_trash_btn);

        currentRedBtn = redRocketBtn;

        int unselectedBtnColor = getResources().getColor(R.color.white);

        // load default fragment (ChargeBoosterFragment)
        loadFragment();


    }

    public void onBtnMenuClicked(View btn) {
        switch (btn.getId()) {
            case R.id.red_rocket_btn:
                fragment = new ChargeBoosterFragment();
                break;
            case R.id.red_battery_btn:
                fragment = new BatterySaverFragment();
                break;
            case R.id.red_ventilator_btn:
                fragment = new OptimizerFragment();
                break;
            case R.id.red_trash_btn:
                fragment = new JunkCleanerFragment();
                break;
        }
        changeSelectedBtnBackground(btn);

        // load selected fragment
        loadFragment();
    }

    private void changeSelectedBtnBackground(View currentBtn) {
        int unselectedBtnColor = getResources().getColor(R.color.white);
        int selectedBtnColor = getResources().getColor(R.color.selected_btn);
        currentRedBtn.setBackgroundTintList(ColorStateList.valueOf(unselectedBtnColor));
        currentBtn.setBackgroundTintList(ColorStateList.valueOf(selectedBtnColor));

        currentRedBtn = (ImageButton) currentBtn;
    }

    private void loadFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fr_place, fragment);
        ft.commit();
    }
}