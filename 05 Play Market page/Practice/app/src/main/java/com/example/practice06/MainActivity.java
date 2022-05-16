package com.example.practice06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<SimilarGame> games = new ArrayList<SimilarGame>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialDate();
        RecyclerView recyclerView = findViewById(R.id.recycle);
        SimilarGameAdapter adapter = new SimilarGameAdapter(this, games);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialDate() {
        games.add(new SimilarGame("NEW STATE Mobile", R.drawable.new_state, "KRAFTON, Inc.", 5f));
        games.add(new SimilarGame("PUBG MOBILE", R.drawable.pubg, "Level Infinite", 4.5f));
        games.add(new SimilarGame("Modern Ops Online", R.drawable.modern_ops, "dkon Games GmbH", 4f));
        games.add(new SimilarGame("Garena Free Fire: New heroes", R.drawable.garena_free_fire, "Garena International I.", 3.5f));
    }
}