package com.example.uw_life_simulator.model;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uw_life_simulator.R;

import java.util.List;

public class Summarypage extends AppCompatActivity {
    private String state = null;
    private Player play = null;

    public Summarypage(String term, Player play1){
        state = term;
        play = play1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(state == "study") {
            List<Integer> list = this.play.getCourseManager().getCurrentCourses();

            setContentView(R.layout.summary_page_study);
        }
        if(state == "coop"){

            setContentView(R.layout.summary_page_coop);
        }

    }
}
