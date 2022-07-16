package com.example.uw_life_simulator.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.activities.CourseSelectionActivity;
import com.example.uw_life_simulator.activities.EventActivity;
import com.example.uw_life_simulator.data.CourseSelectionRecord;

import java.util.List;

public class Summarypage extends AppCompatActivity {
    private String state = null;
    private Player play = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_page_study);

        //Create database
        CourseDatabase db2 = Room.databaseBuilder(getApplicationContext(),
                CourseDatabase.class, "Courses").allowMainThreadQueries().build();
        CourseSelectionRecordDAO courseSelectionRecordDAO = db2.courseSelectionRecordDAO();
        List<CourseSelectionRecord> curSelection = courseSelectionRecordDAO.selectCurrent();

        TextView curCourseTV1 = (TextView)findViewById(R.id.textView1);
        TextView curCourseTV2 = (TextView)findViewById(R.id.textView2);
        TextView curCourseTV3 = (TextView)findViewById(R.id.textView3);
        TextView curCourseTV4 = (TextView)findViewById(R.id.textView4);

        //display
        if (curSelection.size() > 0) {
            int count = curSelection.size();
            if (count >= 1) {
                curCourseTV1.setText(curSelection.get(0).getCourseCode() + "       " + curSelection.get(0).getCompletionGrade());

            }
            if (count >= 2) {
                curCourseTV2.setText(curSelection.get(1).getCourseCode()
                        + "       " + curSelection.get(1).getCompletionGrade());

            }
            if (count >= 3) {
                curCourseTV3.setText(curSelection.get(2).getCourseCode()
                        + "       " + curSelection.get(2).getCompletionGrade());

            }
            if (count >= 4) {
                curCourseTV4.setText(curSelection.get(3).getCourseCode()
                        + "       " + curSelection.get(3).getCompletionGrade());

            }
        }

        //button
        Button Button10 = findViewById(R.id.button10);
        Button10.setOnClickListener((v) -> {
            Intent intent = new Intent(Summarypage.this,
                    CourseSelectionActivity.class);
            startActivity(intent);
        });

        //button
        Button Button2 = findViewById(R.id.button2);
        Button2.setOnClickListener((v) -> {
            Intent intent = new Intent(Summarypage.this,
                    MainActivity.class);
            startActivity(intent);
        });


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
        /*
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

         */
    }


}
