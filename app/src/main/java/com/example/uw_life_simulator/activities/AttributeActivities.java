package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.data.Course;
import com.example.uw_life_simulator.model.MainActivity;
import com.example.uw_life_simulator.model.UserAttribute;

import java.util.List;
import java.util.UUID;

public class AttributeActivities extends AppCompatActivity {
    UserAttribute talent;

    private void database_test() {
        CourseDatabase db = Room.databaseBuilder(getApplicationContext(),
                CourseDatabase.class, "Courses").allowMainThreadQueries().build();
        CourseDao courseDao = db.courseDao();

        UUID uuid = UUID.randomUUID();

        //Course course = new Course(uuid.hashCode(), "CS136", "C",100,100);
        //courseDao.insertAll(course);

        List<Course> courses = courseDao.getAll();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        talent = new UserAttribute(10);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_talent_selection);
        database_test();

    }

    // update current points we have, parameter
    // increaseOrDecrease = 0 when increase point,
    // increaseOrDecrease = 1 when decrease point
    // this function does not check for the validity of input, it assumes
    // the button click is valid
    private void updatePoint(int increaseOrDecrease, TextView textView1, TextView textView2) {
        int availablePoint = Integer.parseInt(textView1.getText().toString());
        int currentPoint = Integer.parseInt(textView2.getText().toString());

        // if the user hits minus button
        if(increaseOrDecrease == 0) {
            textView1.setText(String.valueOf(++availablePoint));
            textView2.setText(String.valueOf(--currentPoint));
        } else {    // if the user his plus button
            textView1.setText(String.valueOf(--availablePoint));
            textView2.setText(String.valueOf(++currentPoint));
        }
    }

    // increase available point by 1 (when the minus button is hit)
    // this function checks for the validity of input
    private void hitMinusButton(TextView totalAvailablePoint, TextView totalCurrentPoint) {
        int availablePoint = Integer.parseInt(totalAvailablePoint.getText().toString());
        int currentPoint = Integer.parseInt(totalCurrentPoint.getText().toString());
        // check for invalid operation : when available point exceeds limit or
        // current point is zero
        if (availablePoint >= talent.getTotalPoint() || currentPoint < 1) {
            return;
        }
        updatePoint(0,totalAvailablePoint,totalCurrentPoint);
    }


    // decrease available point by 1 (when the plus button is hit)
    // this function checks for the validity of input
    private void hitAddButton(TextView totalAvailablePoint, TextView totalCurrentPoint) {
        int availablePoint = Integer.parseInt(totalAvailablePoint.getText().toString());
        int currentPoint = Integer.parseInt(totalCurrentPoint.getText().toString());
        // check for invalid operation : when available point exceeds limit or
        // current point is zero
        if (availablePoint < 1) {
            return;
        }
        updatePoint(1,totalAvailablePoint,totalCurrentPoint);
    }

    // Increment IQ if the addIQ button is hit
    public void addIQ(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentIQPoint = findViewById(R.id.talentIQAmount);
        hitAddButton(totalAvailablePoint, currentIQPoint);
    }

    // decrease IQ if the minusIQ button is hit
    public void minusIQ(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentIQPoint = findViewById(R.id.talentIQAmount);
        hitMinusButton(totalAvailablePoint, currentIQPoint);
    }

    // decrease Luck if the minusLuck button is hit
    public void minusLuck(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for Luck
        TextView currentLuckPoint = findViewById(R.id.talentLuckAmount);
        hitMinusButton(totalAvailablePoint, currentLuckPoint);
    }

    // Increment Luck if the addLuck button is hit
    public void addLuck(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentLuckPoint = findViewById(R.id.talentLuckAmount);
        hitAddButton(totalAvailablePoint, currentLuckPoint);
    }

    // Increment Wealth if the addWealth button is hit
    public void addWealth(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentWealthPoint = findViewById(R.id.talentWealthAmount);
        hitAddButton(totalAvailablePoint, currentWealthPoint);
    }

    // decrease wealth if the minusWealth button is hit
    public void minusWealth(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for Luck
        TextView currentWealthPoint = findViewById(R.id.talentWealthAmount);
        hitMinusButton(totalAvailablePoint, currentWealthPoint);
    }

    // Increment Health if the addHealth button is hit
    public void addHealth(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentHealthPoint = findViewById(R.id.talentHealthAmount);
        hitAddButton(totalAvailablePoint, currentHealthPoint);
    }

    // decrease wealth if the minusWealth button is hit
    public void minusHealth(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for Luck
        TextView currentHealthPoint = findViewById(R.id.talentHealthAmount);
        hitMinusButton(totalAvailablePoint, currentHealthPoint);
    }








}