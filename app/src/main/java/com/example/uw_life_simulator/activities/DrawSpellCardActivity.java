package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.R;

import android.content.Context;

public class DrawSpellCardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);
    }
}

