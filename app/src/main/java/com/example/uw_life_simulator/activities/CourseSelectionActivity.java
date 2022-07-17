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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CourseSelectionActivity extends AppCompatActivity {

    public static boolean DeleteOption = false;
    public static boolean initializeDbOption = false;
    public static int MAX_COURSE_NUMBER = 24;

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
        if (!courseSelectionService.InsertCheckedCourses()) return;


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


        if (courseDao.getCourseCode().size() < MAX_COURSE_NUMBER) {
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

        courseSelectionService = new CourseSelectionService(db, courseSelectionComponent, this);
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

        //displayCourseInfo(courseDao);
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
        int courseId = 0;

        List<Course> uncheckedCourses = courseDao.getUnCheckedCourse();

        for (int checkboxId = 0; checkboxId < 10; ++checkboxId) {
            CheckBox checkBox = courseSelectionComponent.getCheckBoxes().get(checkboxId);
            TextView textView = courseSelectionComponent.getTextViews().get(checkboxId);


            if (checkboxId >= uncheckedCourses.size()) {
                checkBox.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                continue;
            }

            Course course = uncheckedCourses.get(checkboxId);

            String courseCode = uncheckedCourses.get(checkboxId).courseCode;

            String courseInfo =
                    course.getCourseName() + "\nDifficulty: "
                            + course.getDifficulty() + " | Usefulness: "
                            + course.getUsefulness();

            checkBox.setText(courseCode);
            textView.setText(courseInfo);
            courseId++;

        }
    }

    /**
     * Initialize all available courses
     * @return the list of courses
     */
    private List<Course> initializeCourses() {

        Course MANA100 = new Course("MANA 100", "Introduction to Magic", 20, 80,1);
        Course HERB100 = new Course("HERB 100", "Introduction to Herbology", 50, 90,1);
        Course HIST100 = new Course("HIST 100", "History of Magic", 20, 30,1);
        Course MEDI100 = new Course("MEDI 100", "Introduction to Medication", 70, 80,1);
        Course SPEL100 = new Course("SPEL 100", "Introduction to Spells", 80, 80,1);
        Course ATRO100 = new Course("ATRO 100", "Introduction to Astronomy", 60, 30,1);

        Course MANA200 = new Course("MANA 200", "Flying", 70, 90,2);
        Course HERB200 = new Course("HERB 200", "Wondrous Water Plants", 30, 70,2);
        Course SPEL200 = new Course("SPEL 200", "Water-Making Spell", 90, 100,2);
        Course HIST200 = new Course("SPEL 200", "Medieval Assembly of European Wizards", 50, 40,2);
        Course MEDI200 = new Course("MEDI 200", "Potions", 70, 80,2);
        Course ATRO200 = new Course("ATRO 200", "Star charts", 60, 30,2);

        List<Course> courses = new ArrayList<>();


        courses.add(MANA100);
        courses.add(HERB100);
        courses.add(HIST100);
        courses.add(MEDI100);
        courses.add(SPEL100);
        courses.add(ATRO100);
        courses.add(HERB200);
        courses.add(MANA200);
        courses.add(SPEL200);
        courses.add(HIST200);
        courses.add(MEDI200);
        courses.add(ATRO200);



//        courses.add(course1);
//        courses.add(course2);
//        courses.add(course3);
//        courses.add(course4);
//        courses.add(course5);
//        courses.add(course6);
//        courses.add(course7);
//        courses.add(course8);
//        courses.add(course9);
//        courses.add(course10);
//        courses.add(CS245);
//        courses.add(CS251);
//        courses.add(CS341);
//        courses.add(CS343);
//        courses.add(CS348);
//        courses.add(CS349);
//        courses.add(CS350);
//        courses.add(CS370);
//        courses.add(CS444);
//        courses.add(CS446);


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