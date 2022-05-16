package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class FinalScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);

        int optimizedElementsCount = 0;

        // find elements
        LinearLayout boostLayout = findViewById(R.id.boost_layout);
        LinearLayout boostBatteryLayout = findViewById(R.id.boost_battery_layout);
        LinearLayout optimizeLayout = findViewById(R.id.optimize_layout);
        LinearLayout cleanLayout = findViewById(R.id.clean_layout);


        // check SharedPreferences
        SharedPreferences sharedPreferences;

        sharedPreferences = getSharedPreferences("ChargeBoosterFragmentState", MODE_PRIVATE);
        boolean savedChargeBoosterOptimizationCompleted = sharedPreferences.getBoolean("savedFragmentState", false);
        if(savedChargeBoosterOptimizationCompleted) {
            optimizedElementsCount++;
            boostLayout.setVisibility(View.GONE);
        }

        sharedPreferences = getSharedPreferences("BatterySaverFragmentState", MODE_PRIVATE);
        boolean savedBatteryOptimizationCompleted = sharedPreferences.getBoolean("savedBatteryFragmentState", false);
        if(savedBatteryOptimizationCompleted) {
            optimizedElementsCount++;
            boostBatteryLayout.setVisibility(View.GONE);
        }

        sharedPreferences = getSharedPreferences("OptimizerFragmentState", MODE_PRIVATE);
        boolean savedCpuOptimizationCompleted = sharedPreferences.getBoolean("savedOptimizerFragmentState", false);
        if(savedCpuOptimizationCompleted) {
            optimizedElementsCount++;
            optimizeLayout.setVisibility(View.GONE);
        }

        sharedPreferences = getSharedPreferences("JunkCleanerFragmentState", MODE_PRIVATE);
        boolean savedJunkCleaningOptimizationCompleted = sharedPreferences.getBoolean("savedJunkCleanerFragmentState", false);
        if(savedJunkCleaningOptimizationCompleted) {
            optimizedElementsCount++;
            cleanLayout.setVisibility(View.GONE);
        }


        // set count of optimized elements
        final int elementsGeneralCount = 4;
        TextView optimizedElementsCountText = findViewById(R.id.optimized_elements_count);
        optimizedElementsCountText.setText(optimizedElementsCount + " / " + elementsGeneralCount);

        // set text gradient
        TextView cpuTemperatureText = findViewById(R.id.temperature_value_text);
        setGradientToText(cpuTemperatureText);


        // if already all is optimized
        if(optimizedElementsCount == elementsGeneralCount) {
            TextView allOptimizedText = findViewById(R.id.all_optimized_text);
            allOptimizedText.setVisibility(View.VISIBLE);
        }

        setAdvertisement();
    }

    private void setAdvertisement() {
        // Ad Mob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView =findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }


    private void setGradientToText(TextView cpuTemperatureText) {
        int startColor = getResources().getColor(R.color.start_orange_gradient);
        int endColor = getResources().getColor(R.color.end_orange_gradient);
        float angleInRadians = (float) Math.toRadians(0);
        float length = cpuTemperatureText.getPaint().getTextSize();

        float endX = (float) Math.sin(angleInRadians) * length;
        float endY = (float) Math.cos(angleInRadians) * length;

        Shader textShader = new LinearGradient(0, 0, endX, endY,
                new int[]{startColor, endColor},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        cpuTemperatureText.getPaint().setShader(textShader);
    }

    public void onOptimizationButtonClicked(View btn) {
        //Fragment fragment = new ChargeBoosterFragment();
        String fragmentName = "";
        switch (btn.getId()) {
            case R.id.btn_final_boost:
                //fragment = new ChargeBoosterFragment();
                fragmentName = "ChargeBoosterFragment";
                break;
            case R.id.btn_final_boost_battery:
                //fragment = new BatterySaverFragment();
                fragmentName = "BatterySaverFragment";
                break;
            case R.id.btn_final_optimize:
                //fragment = new OptimizerFragment();
                fragmentName = "OptimizerFragment";
                break;
            case R.id.btn_final_clean:
                //fragment = new JunkCleanerFragment();
                fragmentName = "JunkCleanerFragment";
                break;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("SelectedFragmentOnFinalScreen", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("savedFragmentName", fragmentName);
        editor.commit();

        // go to main activity
        startActivity(new Intent(FinalScreen.this, MainActivity.class));
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        //ft.replace(R.id.fr_place, fragment);
//        ft.commit();
    }

    public void onButtonBackClicked(View btnBack) {
        finish();
    }
}