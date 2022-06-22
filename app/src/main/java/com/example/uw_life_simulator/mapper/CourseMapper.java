package com.example.uw_life_simulator.mapper;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uw_life_simulator.model.Course;

import java.util.ArrayList;


public class CourseMapper extends SQLiteOpenHelper {

    private Context context;
    private static final String COURSE_DATABASE_NAME = "CourseLibrary.db";
    private static final int COURSE_DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "CourseList";
    private static final String COURSE_ID = "id";
    private static final String COURSE_TITLE = "name";
    private static final String COURSE_DIFFICULTY = "difficulty";
    private static final String COURSE_USEFULNESS = "usefulness";
    //private static final String COURSE_DESCRIPTION = "description";

    public CourseMapper(@Nullable Context context) {
        super(context, COURSE_DATABASE_NAME , null, COURSE_DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COURSE_ID + " TEXT," +
                        COURSE_TITLE + " TEXT," +
                        COURSE_DIFFICULTY + " INTEGER," +
                        COURSE_USEFULNESS + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // create courses and save them to database
    public void initializeCourseList() {
        ArrayList<Course> courses = new ArrayList<>();
        Course course1 = new Course("CS116", "Python", 0,50);
        Course course2 = new Course("CS199", "React", 50, 100);
        Course course3 = new Course("CS241", "Compiler", 100,0);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        // add courses to database
        for (Course c : courses) {
            addToCourseList(c);
        }
    }

    // put course info to database
    public void addToCourseList(Course course ) {
        System.out.println("initializeCourseList");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COURSE_ID, course.getCourseId());
        values.put(COURSE_TITLE, course.getName());
        values.put(COURSE_DIFFICULTY, course.getDifficulty());
        values.put(COURSE_USEFULNESS, course.getUsefulness());

        long result = db.insert(TABLE_NAME, null, values);
    }
}
