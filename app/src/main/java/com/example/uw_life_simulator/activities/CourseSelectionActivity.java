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

        initializeAll(db);


    }

    /**
     * Initialize Courses, put them to database and to the course selection UI
     * @param db is the Course Database
     */
    private void initializeAll(CourseDatabase db) {
        CourseDao courseDao = db.courseDao();

        // initialize Courses
        List<Course> courses = initializeCourses();

        // Insert courses to db
        insertListOfCourses(courseDao, courses);
        if (courseDao.getCourseCode().size() < 19) {
            initializeDbOption = true;
        }

        if (initializeDbOption == true) {
            initializeAll(db);
            initializeDbOption = false;

        }

        initialize_UI(db);
        initializeCourseInfo(courseDao);

    }

    /**
     * Extract course details from database
     * display course details to the course selection activity UI
     * @param courseDao
     */
    private void initializeCourseInfo(CourseDao courseDao) {
        ArrayList<String> allCourseInfo = new ArrayList<>();
        List<Course> courses = courseDao.getAll();

        int courseCounter = 0;

        for (Course course : courses) {
            String courseInfo =
                    course.getCourseName() + "\nDifficulty: "
                    + course.getDifficulty() + " | Usefulness: "
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
        TextView v7 = findViewById(R.id.textView7);
        TextView v8 = findViewById(R.id.textView8);
        TextView v9 = findViewById(R.id.textView9);
        TextView v10 = findViewById(R.id.textView10);
        List<TextView> textViews = new ArrayList<>();

        textViews.add(v1);
        textViews.add(v2);
        textViews.add(v3);
        textViews.add(v4);
        textViews.add(v5);
        textViews.add(v6);
        textViews.add(v7);
        textViews.add(v8);
        textViews.add(v9);
        textViews.add(v10);

        courseCounter = 0;

        for (TextView textView : textViews) {
            textView.setText(allCourseInfo.get(courseCounter));
            courseCounter++;
        }

    }

    /**
     * Extract course code from database
     * display course code to the course selection activity UI
     * @param db
     */
    private void initialize_UI(CourseDatabase db) {
        CourseDao courseDao = db.courseDao();

        // display Course code
        CheckBox c1 = findViewById(R.id.checkbox_meat1);
        CheckBox c2 = findViewById(R.id.checkbox_meat2);
        CheckBox c3 = findViewById(R.id.checkbox_meat3);
        CheckBox c4 = findViewById(R.id.checkbox_meat4);
        CheckBox c5 = findViewById(R.id.checkbox_meat5);
        CheckBox c6 = findViewById(R.id.checkbox_meat6);
        CheckBox c7 = findViewById(R.id.checkbox_meat7);
        CheckBox c8 = findViewById(R.id.checkbox_meat8);
        CheckBox c9 = findViewById(R.id.checkbox_meat9);
        CheckBox c10 = findViewById(R.id.checkbox_meat10);

        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(c1);
        checkBoxes.add(c2);
        checkBoxes.add(c3);
        checkBoxes.add(c4);
        checkBoxes.add(c5);
        checkBoxes.add(c6);
        checkBoxes.add(c7);
        checkBoxes.add(c8);
        checkBoxes.add(c9);
        checkBoxes.add(c10);

        displayCourseCode(checkBoxes,courseDao);
    }


    /**
     * Delete all course in our database
     * @param courseDao
     */
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

    /**
     * Initialize all available courses
     * @return the list of courses
     */
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
        Course CS245 = new Course("CS245", "Logic", 43, 24);
        Course CS251 = new Course("CS251", "Computer Design", 15, 71);
        Course CS341 = new Course("CS341", "Algorithms", 46, 99);
        Course CS343 = new Course("CS343", "Concurrent Programming", 67, 59);
        Course CS348 = new Course("CS348", "Intro to Database", 30, 63);
        Course CS349 = new Course("CS349", "User Interfaces", 25, 75);
        Course CS350 = new Course("CS350", "Operating Systems", 54, 94);
        Course CS370 = new Course("CS370", "Numerical Computing", 40, 86);
        Course CS444 = new Course("CS444", "Compiler Construction", 20, 99);
        Course CS446 = new Course("CS446", "Software Design", 30, 101);

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
        courses.add(CS245);
        courses.add(CS251);
        courses.add(CS341);
        courses.add(CS343);
        courses.add(CS348);
        courses.add(CS349);
        courses.add(CS350);
        courses.add(CS370);
        courses.add(CS444);
        courses.add(CS446);

        return courses;
    }


    /**
     * Put course code to the Course Section UI
     * @param checkBoxes is a list of Checkboxes
     * @param courseDao is a list of Course DAOs
     */
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