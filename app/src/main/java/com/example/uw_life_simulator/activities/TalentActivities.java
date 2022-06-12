package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.model.Talent;

public class TalentActivities extends AppCompatActivity {
    Talent talent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        talent = new Talent(10);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_talent_selection);
    }

}