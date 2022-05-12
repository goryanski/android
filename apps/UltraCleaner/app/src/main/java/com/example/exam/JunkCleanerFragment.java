package com.example.exam;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JunkCleanerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JunkCleanerFragment extends Fragment {

    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    boolean optimizationCompleted = false;
    final String FRAGMENT_STATE = "JunkCleanerFragmentState";

    int progressStatus = 0;
    ImageButton redRocketBtn, redBatteryBtn, redVentilatorBtn,
            redTrashBtn, whiteRocketBtn, whiteBatteryBtn,
            whiteVentilatorBtn, whiteTrashBtn;

    Button btnCleanerStart;
    int selectedBtnColor;
    int unselectedBtnColor;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JunkCleanerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JunkCleanerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JunkCleanerFragment newInstance(String param1, String param2) {
        JunkCleanerFragment fragment = new JunkCleanerFragment();
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
        View view = inflater.inflate(R.layout.fragment_junk_cleaner, container, false);

        // hide progressBar
        progressBar = view.findViewById(R.id.cleaner_progressbar);
        progressBar.setVisibility(View.GONE);

        // check optimization state on this fragment
        sharedPreferences = getActivity().getSharedPreferences(FRAGMENT_STATE, MODE_PRIVATE);
        optimizationCompleted = sharedPreferences.getBoolean("savedJunkCleanerFragmentState", false);

        // find start optimization button
        btnCleanerStart = view.findViewById(R.id.btn_trash_clean_start);
        // find menu button that appear when optimization on this fragment is done
        whiteTrashBtn = getActivity().findViewById(R.id.white_trash_btn);
        // define color of selected/unselected menu button
        selectedBtnColor = getResources().getColor(R.color.selected_btn);
        unselectedBtnColor = getResources().getColor(R.color.white);

        findAllMenuButtons();
        setOtherMenuButtonsUnselected();

        if(optimizationCompleted) {
            setStartButtonToOptimizedState();
            setMenuButtonToOptimizedState();
        }
        else {
            switchFragmentToNotOptimizedState();
        }


        // Ad Mob
        MobileAds.initialize(this.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = view.findViewById(R.id.adView);
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


        // create possibility to change optimization state in this fragment manually - change data to let do optimization again (for testing).
        // For this you have to click on robot image and fragment state will reset
        ImageView basketImg = view.findViewById(R.id.basket_img);
        basketImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optimizationCompleted = false;
                setStartButtonToNotOptimizedState();
                switchFragmentToNotOptimizedState();
            }
        });

        return view;
    }

    private void findAllMenuButtons() {
        // menu define in main activity
        redRocketBtn = getActivity().findViewById(R.id.red_rocket_btn);
        redBatteryBtn = getActivity().findViewById(R.id.red_battery_btn);
        redVentilatorBtn = getActivity().findViewById(R.id.red_ventilator_btn);
        redTrashBtn = getActivity().findViewById(R.id.red_trash_btn);
        whiteBatteryBtn = getActivity().findViewById(R.id.white_battery_btn);
        whiteVentilatorBtn = getActivity().findViewById(R.id.white_ventilator_btn);
        whiteRocketBtn = getActivity().findViewById(R.id.white_rocket_btn);
    }

    private void setOtherMenuButtonsUnselected() {
        // change background and icon color for other menu buttons, except button for this fragment (only for optimized state buttons)
        redBatteryBtn.setBackgroundTintList(ColorStateList.valueOf(unselectedBtnColor));
        redVentilatorBtn.setBackgroundTintList(ColorStateList.valueOf(unselectedBtnColor));
        redRocketBtn.setBackgroundTintList(ColorStateList.valueOf(unselectedBtnColor));

        whiteBatteryBtn.setBackgroundTintList(ColorStateList.valueOf(unselectedBtnColor));
        whiteBatteryBtn.setImageResource(R.drawable.ic_battery_blue_btn);
        whiteVentilatorBtn.setBackgroundTintList(ColorStateList.valueOf(unselectedBtnColor));
        whiteVentilatorBtn.setImageResource(R.drawable.ic_ventilator_blue_btn);
        whiteRocketBtn.setBackgroundTintList(ColorStateList.valueOf(unselectedBtnColor));
        whiteRocketBtn.setImageResource(R.drawable.ic_rocket_blue_btn);
    }

    private void setStartButtonToOptimizedState() {
        btnCleanerStart.setText("Optimized");
        btnCleanerStart.setTextSize(20);
    }

    private void setStartButtonToNotOptimizedState() {
        btnCleanerStart.setText("clean");
        btnCleanerStart.setTextSize(30);
        btnCleanerStart.setEnabled(true);
    }

    private void setMenuButtonToOptimizedState() {
        // set button menu to optimized state
        whiteTrashBtn.setVisibility(View.VISIBLE);
        whiteTrashBtn.setBackgroundTintList(ColorStateList.valueOf(selectedBtnColor));
        // set icon color
        whiteTrashBtn.setImageResource(R.drawable.ic_trash_white_btn);
        // but make button menu of not optimized state as unselected button menu
        redTrashBtn.setBackgroundTintList(ColorStateList.valueOf(unselectedBtnColor));
    }

    private void switchFragmentToNotOptimizedState() {
        setMenuButtonToNotOptimizedState();

        // start boosting
        btnCleanerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                btnCleanerStart.setText("0%");

                // disable all menu buttons during optimization to prevent the user from navigating to the next fragments
                setMenuButtonsAccessibility(false);
                btnCleanerStart.setEnabled(false);

                Handler handler = new Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            progressStatus++;
                            android.os.SystemClock.sleep(80);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    btnCleanerStart.setText(progressStatus + "%");
                                }
                            });
                        }
                        // optimization finished
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                setStartButtonToOptimizedState();
                                setMenuButtonToOptimizedState();
                                setMenuButtonsAccessibility(true);
                                optimizationCompleted = true;
                                // save state before going to FinalScreen activity
                                saveFragmentState();
                                android.os.SystemClock.sleep(50);

                                // go to next activity
                                startActivity(new Intent(JunkCleanerFragment.this.getActivity(), FinalScreen.class));
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void setMenuButtonToNotOptimizedState() {
        // hide button menu of optimized state
        whiteTrashBtn.setVisibility(View.GONE);
        whiteTrashBtn.setBackgroundTintList(ColorStateList.valueOf(unselectedBtnColor));
        // but make button menu of not optimized state as selected button menu
        redTrashBtn.setBackgroundTintList(ColorStateList.valueOf(selectedBtnColor));
    }

    private void setMenuButtonsAccessibility(boolean enable) {
        redRocketBtn.setEnabled(enable);
        redBatteryBtn.setEnabled(enable);
        redVentilatorBtn.setEnabled(enable);
        redTrashBtn.setEnabled(enable);
        whiteRocketBtn.setEnabled(enable);
        whiteBatteryBtn.setEnabled(enable);
        whiteVentilatorBtn.setEnabled(enable);
        whiteTrashBtn.setEnabled(enable);
    }

    // save fragment state before going to next fragment (switch menu buttons)
    @Override
    public void onStop() {
        super.onStop();
        saveFragmentState();
    }

    private void saveFragmentState() {
        sharedPreferences = getActivity().getSharedPreferences(FRAGMENT_STATE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("savedJunkCleanerFragmentState", optimizationCompleted);
        editor.commit();
    }
}