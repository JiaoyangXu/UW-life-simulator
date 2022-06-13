package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.uw_life_simulator.MainActivity;
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

    // Increment IQ if the addIQ button is hit
    public void addIQ(View view) {
        // get current IQ amount
        TextView textView = findViewById(R.id.talentIQAmount);
        String IQString = textView.getText().toString();
        int IQAmount = Integer.parseInt(IQString);
        // increment IQ amount
        IQAmount++;
        textView.setText(String.valueOf(IQAmount));
    }

    // decrease IQ if the minusIQ button is hit
    public void minusIQ(View view) {
        // get current IQ amount
        TextView textView = findViewById(R.id.talentIQAmount);
        String IQString = textView.getText().toString();
        int IQAmount = Integer.parseInt(IQString);
        // decrease IQ amount
        IQAmount = Math.max(--IQAmount,0);
        textView.setText(String.valueOf(IQAmount));
    }





}