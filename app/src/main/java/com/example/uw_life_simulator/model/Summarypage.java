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

        int money = curPlayer.getMoney();

        int pressure = curPlayer.getPressure();

        int term = curPlayer.getNumTerm();

        if(term == 7){
            setContentView(R.layout.finish);
            Button Button3 = findViewById(R.id.button3);
            Button3.setOnClickListener((v) -> {
                Intent intent1 = new Intent(Summarypage.this,
                        MainActivity.class);
                startActivity(intent1);
            });
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
                int count = 4;
                if (count >= 1) {
                    String course1 = playerAttributeDAO.getCourse1().get(0);
                    curCourseTV1.setText(course1+ "       " +
                            courseSelectionRecordDAO.getGradeByCode(course1));

                }
                if (count >= 2) {
                    String course2 = playerAttributeDAO.getCourse2().get(0);
                    curCourseTV2.setText(course2+ "       " +
                            courseSelectionRecordDAO.getGradeByCode(course2));

                }
                if (count >= 3) {
                    String course3 = playerAttributeDAO.getCourse3().get(0);
                    curCourseTV3.setText(course3+ "       " +
                            courseSelectionRecordDAO.getGradeByCode(course3));

                }
                if (count >= 4) {
                    String course4 = playerAttributeDAO.getCourse4().get(0);
                    curCourseTV4.setText(course4+ "       " +
                            courseSelectionRecordDAO.getGradeByCode(course4));

                }


            TextView curCourseTV5 = (TextView) findViewById(R.id.key_attr_text1);
            TextView curCourseTV6 = (TextView) findViewById(R.id.key_attr_text2);
            TextView curCourseTV7 = (TextView) findViewById(R.id.key_attr_text3);
            curCourseTV5.setText("Your total GPA is " + gpa);
            curCourseTV6.setText("You currently have " + money + " $");
            curCourseTV7.setText("Your Pressure is " + pressure);
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