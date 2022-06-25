package com.example.uw_life_simulator.model;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
            //List<Integer> list = this.play.getCourseManager().getCurrentCourses();

            setContentView(R.layout.summary_page_study);
        }
        if(state == "coop"){

            setContentView(R.layout.summary_page_coop);
        }


    }


    private void update( List<Integer> list) {
        int index = 1;
        for(int i = 0; i < list.size();i++){
            if(80 >= list.get(i)){
                index = 2;
            } else if(60 >= list.get(i)) {
                index = 3;
            }
        }
        TextView edit;
        TextView edit1;
        edit1 = findViewById(R.id.textView1);
        edit = findViewById(R.id.textView);
        if(index == 1){

            edit.append("Congratulation");
            edit1.append("You get high GPA in this term");

        } else if(index == 2){
            edit.append("Congratulation");
            edit1.append("You finish this term");
        }else if(index == 3){
            edit.append("Sorry");
            edit1.append("You fail some class, work hard on next term!");
        }

    }
}
