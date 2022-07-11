package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.uw_life_simulator.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;


public class SwipeSpellCardActivity extends AppCompatActivity {

    ArrayList<String> s;
    ArrayAdapter arrayAdapter;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_spell_card);

        s = new ArrayList<>();
        s.add("ONE");
        s.add("TWO");
        s.add("THREE");
        s.add("FIVE");
        s.add("SIX");
        s.add("SEVEN");



        SwipeFlingAdapterView swipeFlingAdapterView = (SwipeFlingAdapterView) findViewById(R.id.card);
        arrayAdapter = new ArrayAdapter(this, R.layout.swipe_card, R.id.cardTextView,s);
        swipeFlingAdapterView.setAdapter(arrayAdapter);

        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                s.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });

    }
}