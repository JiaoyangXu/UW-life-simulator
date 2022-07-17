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
import com.example.uw_life_simulator.DAO.PlayerAttributeDAO;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.Database.PlayerAttributeDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.activities.CourseSelectionActivity;
import com.example.uw_life_simulator.activities.EventActivity;
import com.example.uw_life_simulator.data.CourseSelectionRecord;
import com.example.uw_life_simulator.data.PlayerAttribute;

import java.util.List;

public class Summarypage extends AppCompatActivity {
    private String state = null;
    private Player play = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        PlayerAttributeDatabase db = Room.databaseBuilder(getApplicationContext(),
                PlayerAttributeDatabase.class, "PlayerAttributes").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        PlayerAttributeDAO playerAttributeDAO = db.playerAttributeDAO();
        List<PlayerAttribute> tmpList = playerAttributeDAO.loadSingle();

        PlayerAttribute curPlayer = tmpList.get(tmpList.size() -1);



        int gpa = curPlayer.getGPA();

        int money = curPlayer.getWealth();

        int pressure = curPlayer.getPressure();

        int term = curPlayer.getNumTerm();
        int next_term = term + 1;
        if(term == 6){

        } else if(money < 0 || pressure > 100 || gpa < 50) {
            setContentView(R.layout.summary_page_coop);
            Button Button3 = findViewById(R.id.button3);
            Button3.setOnClickListener((v) -> {
                Intent intent1 = new Intent(Summarypage.this,
                        MainActivity.class);
                startActivity(intent1);
            });
        }   else {
            setContentView(R.layout.summary_page_study);

            //Create database
            CourseDatabase db2 = Room.databaseBuilder(getApplicationContext(),
                    CourseDatabase.class, "Courses").allowMainThreadQueries().build();
            CourseSelectionRecordDAO courseSelectionRecordDAO = db2.courseSelectionRecordDAO();
            List<CourseSelectionRecord> curSelection = courseSelectionRecordDAO.selectCurrent();

            TextView curCourseTV1 = (TextView) findViewById(R.id.course_grade_text1);
            TextView curCourseTV2 = (TextView) findViewById(R.id.course_grade_text2);
            TextView curCourseTV3 = (TextView) findViewById(R.id.course_grade_text3);
            TextView curCourseTV4 = (TextView) findViewById(R.id.course_grade_text4);

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

            TextView curCourseTV5 = (TextView) findViewById(R.id.key_attr_text1);
            TextView curCourseTV6 = (TextView) findViewById(R.id.key_attr_text2);
            TextView curCourseTV7 = (TextView) findViewById(R.id.key_attr_text3);
            curCourseTV5.setText("Your total GPA is" + gpa);
            curCourseTV6.setText("You currently have" + money + "$");
            curCourseTV7.setText("Your Pressure is" + pressure);
            //button
            Button Button10 = findViewById(R.id.button10);
            Button10.setOnClickListener((v) -> {
                Intent intent1 = new Intent(Summarypage.this,
                        CourseSelectionActivity.class);
                startActivity(intent1);
            });

            //button
            Button Button2 = findViewById(R.id.button2);
            Button2.setOnClickListener((v) -> {
                Intent intent1 = new Intent(Summarypage.this,
                        MainActivity.class);
                startActivity(intent1);
            });
        }

    }



}