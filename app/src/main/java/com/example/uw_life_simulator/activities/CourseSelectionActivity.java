package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.data.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseSelectionActivity extends AppCompatActivity {

    public static boolean DeleteOption = false;
    public static boolean initializeDbOption = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);


        CourseDatabase db = Room.databaseBuilder(getApplicationContext(),
                CourseDatabase.class, "Courses").allowMainThreadQueries().build();
        CourseDao courseDao = db.courseDao();

        if (DeleteOption == true) {
            deleteAllCourses(courseDao);
        }

        if (initializeDbOption == true) {
            initializeAll(db);
        }

        initialize_UI(db);
        initializeCourseInfo(courseDao);


    }

    private void initializeAll(CourseDatabase db) {
        CourseDao courseDao = db.courseDao();

        // initialize Courses
        List<Course> courses = initializeCourses();

        // Insert courses to db
        insertListOfCourses(courseDao, courses);

    }

    private void initializeCourseInfo(CourseDao courseDao) {
        ArrayList<String> allCourseInfo = new ArrayList<>();
        List<Course> courses = courseDao.getAll();

        int courseCounter = 0;

        for (Course course : courses) {
            String courseInfo = "Course Name: "
                    + course.getCourseName() + " Difficulty: "
                    + course.getDifficulty() + " Usefulness: "
                    + course.getUsefulness();
            allCourseInfo.add(courseInfo);
            courseCounter++;
        }

        TextView v1 = findViewById(R.id.textView1);
        TextView v2 = findViewById(R.id.textView2);
        TextView v3 = findViewById(R.id.textView3);
        TextView v4 = findViewById(R.id.textView4);
        TextView v5 = findViewById(R.id.textView5);
        TextView v6 = findViewById(R.id.textView6);
        List<TextView> textViews = new ArrayList<>();

        textViews.add(v1);
        textViews.add(v2);
        textViews.add(v3);
        textViews.add(v4);
        textViews.add(v5);
        textViews.add(v6);

        courseCounter = 0;

        for (TextView textView : textViews) {
            textView.setText(allCourseInfo.get(courseCounter));
            courseCounter++;
        }

    }
    private void initialize_UI(CourseDatabase db) {
        CourseDao courseDao = db.courseDao();

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


    private void deleteAllCourses(CourseDao courseDao) {
        List<Course> courses = courseDao.getAll();

        for (Course course : courses) {
            courseDao.delete(course);
        }
    }


    /**
     * Insert a course to the Course table in Courses Database
     * Return true if the insertion is valid
     * Return false if the code code is duplicate and print to log
     **/
    private boolean insertCourse(CourseDao dao, Course course) {
        List<String> courses = dao.getCourseCode();
        String currentCourseCode = course.getCourseCode();

        // if course name isn't already exist in our table, insert it
        if(!courses.contains(currentCourseCode)) {
            dao.insertAll(course);
            return true;
        }
        return false;
    }


    /**
     * Insert a list of courses to the Course table in Courses Database
     *
     **/
    private void insertListOfCourses(CourseDao courseDao, List<Course> courses) {
        for (Course course : courses) {
            insertCourse(courseDao, course);
        }
    }

    private List<Course> initializeCourses() {
        Course course1 = new Course("CS135", "Racket", 50,50);
        Course course2 = new Course("CS136", "C", 30,90);
        Course course3 = new Course("CS246", "C++", 60,100);
        Course course4 = new Course("CS241", "Compiler", 80, 30);
        Course course5 = new Course("CS348", "Database", 50,60);
        Course course6 = new Course("Math 137", "Calculus I", 60, 70);
        Course course7 = new Course("Math 138", "Calculus II", 60, 70);
        Course course8 = new Course("Math 136", "Linear Algebra", 30, 80);
        Course course9 = new Course("Econ 101", "Microeconomics", 0, 10);
        Course course10 = new Course("Game100", "League of Legends", 0, 100);

        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);
        courses.add(course8);
        courses.add(course9);
        courses.add(course10);

        return courses;
    }


    private void displayCourseCode(List<CheckBox> checkBoxes, CourseDao courseDao) {
        List<String> courses = courseDao.getCourseCode();
        int checkboxId = 0;
        for (CheckBox checkBox : checkBoxes) {
            String course = courses.get(checkboxId);
            checkBox.setText(course);
            checkboxId++;
        }
    }

}