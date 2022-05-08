package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class LoadingScreen extends AppCompatActivity {

    ProgressBar progressBar;
    TextView loadingLabel;
    int progressStatus = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setProgress(0);

        loadingLabel = findViewById(R.id.loading_label);
        loadingLabel.setText("0%");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus++;
                    android.os.SystemClock.sleep(50);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            loadingLabel.setText(progressStatus + "%");
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        android.os.SystemClock.sleep(50);
                        // go to next activity
                        startActivity(new Intent(LoadingScreen.this, MainActivity.class));
                        finish();
                    }
                });
            }
        }).start();
    }
}