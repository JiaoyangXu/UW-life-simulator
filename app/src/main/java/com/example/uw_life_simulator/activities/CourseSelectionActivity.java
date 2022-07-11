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
import com.example.uw_life_simulator.Service.CourseSelectionService;
import com.example.uw_life_simulator.component.CourseSelectionComponent;
import com.example.uw_life_simulator.data.Course;
import com.example.uw_life_simulator.data.CourseSelectionRecord;
import com.example.uw_life_simulator.model.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CourseSelectionActivity extends AppCompatActivity {

    public static boolean DeleteOption = false;
    public static boolean initializeDbOption = true;
    public static int MAX_COURSE_NUMBER = 19;

    public CourseSelectionComponent courseSelectionComponent;
    private CourseSelectionService courseSelectionService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);


        CourseDatabase db = Room.databaseBuilder(getApplicationContext(),
                CourseDatabase.class, "Courses").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();
        CourseDao courseDao = db.courseDao();

        if (DeleteOption == true) {
            deleteAllCourses(courseDao);
        }

        initializeAll(db);


    }

    /**
     * Save all checked courses to database and jump to main page
     * @param view
     */
    public void courseReturnMain(View view) {
        courseSelectionService.InsertCheckedCourses();

        Intent intent = new Intent(CourseSelectionActivity.this, MainActivity.class);
        startActivity(intent);
    }



    /**
     * Save all checked course to database and jump to the event page
     * @param view
     */
    public void submitCourseSelection(View view) {
        courseSelectionService.InsertCheckedCourses();

        // pass intent to Event Activity
        Intent intent = new Intent(CourseSelectionActivity.this, EventActivity.class);
        startActivity(intent);
    }


    /**
     * Initialize Courses, put them to database and to the course selection UI
     * @param db is the Course Database
     */
    private void initializeAll(CourseDatabase db) {
        CourseDao courseDao = db.courseDao();

        // initialize Courses
        List<Course> courses = initializeCourses();


        if (courseDao.getCourseCode().size() < 19) {
            initializeDbOption = true;
        }

        // ensure the Course table in database Courses to contain all courses
        if (initializeDbOption == true) {
            // Insert courses to db
            insertListOfCourses(courseDao, courses);
            initializeDbOption = false;
        }

        initializeClass(db,courseDao);

        initialize_UI(db);
    }


    /**
     * Initialize 3 attributes in this activity
     * 1. checkBoxes -> List that contains 10 checkboxes
     * 2. textViews -> List that contains 10 textViews
     * 3. availableCourses -> List that contain all courses
     * @param courseDao
     */
    private void initializeClass(CourseDatabase db, CourseDao courseDao) {
        List<TextView> textViews = initializeTextViewsInstance();
        List<CheckBox> checkBoxes = initializeCheckBoxesInstance();
        List<Course> courses = initializeAvailableCourses(courseDao);
        List<String> courseCodes = initializeCourseCode(courseDao);
        courseSelectionComponent = new CourseSelectionComponent(checkBoxes, textViews,courses,courseCodes);

        courseSelectionService = new CourseSelectionService(db, courseSelectionComponent);
    }

    private List<TextView> initializeTextViewsInstance() {
        List<TextView> textViews = new ArrayList<>();
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
        Collections.addAll(textViews,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10);
        return textViews;
    }

    private List<CheckBox> initializeCheckBoxesInstance() {
        List<CheckBox> checkBoxes = new ArrayList<>();
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
        Collections.addAll(checkBoxes,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10);
        return checkBoxes;
    }

    private List<Course> initializeAvailableCourses(CourseDao courseDao) {
        return courseDao.getAll();
    }

    private List<String> initializeCourseCode(CourseDao courseDao) {
        return courseDao.getCourseCode();
    }



    /**
     * Extract course code from database
     * display course code to the course selection activity UI
     * @param db
     */
    private void initialize_UI(CourseDatabase db) {
        CourseDao courseDao = db.courseDao();

        displayCourseInfo(courseDao);
        displayCourseCode(courseDao);
    }



    /**
     * Extract course details from database
     * display course details to the course selection activity UI
     * @param courseDao
     */
    private void displayCourseInfo(CourseDao courseDao) {
        ArrayList<String> allCourseInfo = new ArrayList<>();

        int courseCounter = 0;

        for (Course course : courseSelectionComponent.getAvailableCourses()) {
            if(course.isChecked == 1) {
                continue;
            }

            String courseInfo =
                    course.getCourseName() + "\nDifficulty: "
                            + course.getDifficulty() + " | Usefulness: "
                            + course.getUsefulness();
            allCourseInfo.add(courseInfo);
            courseCounter++;
        }

        courseCounter = 0;
        int maxCourses = allCourseInfo.size();

        for (TextView textView : courseSelectionComponent.getTextViews()) {
            if (maxCourses > courseCounter) {
                textView.setText(allCourseInfo.get(courseCounter));
                courseCounter++;
            } else {
                break;
            }
        }
    }



    /**
     * Put course code to the Course Section UI
     * @param courseDao is a list of Course DAOs
     */
    private void displayCourseCode(CourseDao courseDao) {
        int checkboxId = 0;
        int courseId = 0;

        for (CheckBox checkBox : courseSelectionComponent.getCheckBoxes()) {

            if (checkboxId >= MAX_COURSE_NUMBER) {
                break;
            }

            while(courseSelectionComponent.getAvailableCourses().get(checkboxId).isChecked == 1) {
                checkboxId++;
                if (checkboxId >= 19) return;
            }

            String course = courseSelectionComponent.getAvailableCourseCodes().get(checkboxId);
            checkBox.setText(course);
            checkboxId++;
        }
    }

    /**
     * Initialize all available courses
     * @return the list of courses
     */
    private List<Course> initializeCourses() {
        Course course1 = new Course("CS135", "Racket", 50,50,1);
        Course course2 = new Course("CS136", "C", 30,90,1);
        Course course3 = new Course("CS246", "C++", 60,100,2);
        Course course4 = new Course("CS241", "Compiler", 80, 30,2);
        Course course5 = new Course("CS348", "Database", 50,60,3);
        Course course6 = new Course("Math 137", "Calculus I", 60, 70,1);
        Course course7 = new Course("Math 138", "Calculus II", 60, 70,1);
        Course course8 = new Course("Math 136", "Linear Algebra", 30, 80,1);
        Course course9 = new Course("Econ 101", "Microeconomics", 0, 10,1);
        Course course10 = new Course("Game100", "League of Legends", 0, 100,100);
        Course CS245 = new Course("CS245", "Logic", 43, 24,2);
        Course CS251 = new Course("CS251", "Computer Design", 15, 71,2);
        Course CS341 = new Course("CS341", "Algorithms", 46, 99,3);
        Course CS343 = new Course("CS343", "Concurrent Programming", 67, 59,3);
        Course CS348 = new Course("CS348", "Intro to Database", 30, 63,3);
        Course CS349 = new Course("CS349", "User Interfaces", 25, 75,3);
        Course CS350 = new Course("CS350", "Operating Systems", 54, 94,3);
        Course CS370 = new Course("CS370", "Numerical Computing", 40, 86,3);
        Course CS444 = new Course("CS444", "Compiler Construction", 20, 99,4);
        Course CS446 = new Course("CS446", "Software Design", 30, 101,4);

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
}