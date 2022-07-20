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
    public static int NUMBER_COURSE_ONE_PAGE = 10;

    private int pageCounter;

    public CourseSelectionComponent courseSelectionComponent;
    private CourseSelectionService courseSelectionService;

    private CourseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_selection);


         db = Room.databaseBuilder(getApplicationContext(),
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
        finish();
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
        finish();
    }

    private void uncheckAll() {
        for (int checkboxId = 0; checkboxId < 10; ++checkboxId) {
            CheckBox checkBox = courseSelectionComponent.getCheckBoxes().get(checkboxId);

            checkBox.setChecked(false);
        }
    }

    public void nextCourseSelection(View view) {
        pageCounter++;
        uncheckAll();
        initialize_UI(db);
    }

    public void prevCourseSelection(View view){
        pageCounter --;
        uncheckAll();
        initialize_UI(db);
    }


    /**
     * Initialize Courses, put them to database and to the course selection UI
     * @param db is the Course Database
     */
    private void initializeAll(CourseDatabase db) {
        pageCounter = 0;

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

    private void changeToVisible() {
        for (int checkboxId = 0; checkboxId < 10; ++checkboxId) {
            CheckBox checkBox = courseSelectionComponent.getCheckBoxes().get(checkboxId);
            TextView textView = courseSelectionComponent.getTextViews().get(checkboxId);

            checkBox.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
    }



    /**
     * Put course code to the Course Section UI
     * @param courseDao is a list of Course DAOs
     */
    private void displayCourseCode(CourseDao courseDao) {

        pageCounter = Math.max(0, pageCounter);
        changeToVisible();

        List<Course> uncheckedCourses = courseDao.getUnCheckedCourse();

        if ( uncheckedCourses.size() > pageCounter * NUMBER_COURSE_ONE_PAGE) {
            uncheckedCourses = uncheckedCourses.subList(pageCounter * NUMBER_COURSE_ONE_PAGE,
                    uncheckedCourses.size());
        } else {
            pageCounter--;
            uncheckedCourses = uncheckedCourses.subList(pageCounter * NUMBER_COURSE_ONE_PAGE,
                    uncheckedCourses.size());
        }

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
        }
    }

    /**
     * Initialize all available courses
     * @return the list of courses
     */
    private List<Course> initializeCourses() {
        List<Course> courses = new ArrayList<>();


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