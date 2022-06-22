package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.CheckBox;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.data.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);
        initialize_db();
    }

    private void displayCourseCode(List<CheckBox> checkBoxes, CourseDao courseDao) {
        List<String> courses = courseDao.getCourseCode();
        int checkboxId = 0;
        for (String course : courses) {
            CheckBox currentBox = checkBoxes.get(checkboxId);
            currentBox.setText(course);
            checkboxId++;
        }
    }

    private void initialize_db() {
        CourseDatabase db = Room.databaseBuilder(getApplicationContext(),
                CourseDatabase.class, "Courses").allowMainThreadQueries().build();
        CourseDao courseDao = db.courseDao();

        UUID uuid = UUID.randomUUID();



        //Course course = new Course(uuid.hashCode(), "CS245", "C++",50,100);
        //courseDao.insertAll(course);

        List<Course> courses = courseDao.getAll();
        System.out.println(courses.get(0).courseCode);

        // display Course code
        CheckBox c1 = findViewById(R.id.checkbox_meat1);
        CheckBox c2 = findViewById(R.id.checkbox_meat2);
        CheckBox c3 = findViewById(R.id.checkbox_meat3);
        CheckBox c4 = findViewById(R.id.checkbox_meat4);
        CheckBox c5 = findViewById(R.id.checkbox_meat5);
        CheckBox c6 = findViewById(R.id.checkbox_meat6);

        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(c1);
        checkBoxes.add(c2);
        checkBoxes.add(c3);
        checkBoxes.add(c4);
        checkBoxes.add(c5);
        checkBoxes.add(c6);

        displayCourseCode(checkBoxes,courseDao);
    }
}