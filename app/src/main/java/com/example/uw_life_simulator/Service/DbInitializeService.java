package com.example.uw_life_simulator.Service;

import android.content.Context;

import androidx.room.Room;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.DAO.PlayerAttributeDAO;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.Database.PlayerAttributeDatabase;
import com.example.uw_life_simulator.Database.SpellCardDatabase;
import com.example.uw_life_simulator.activities.CourseSelectionActivity;
import com.example.uw_life_simulator.data.Course;

import java.util.ArrayList;
import java.util.List;

public class DbInitializeService extends DbCleanService{;

    public DbInitializeService(Context context) {
        super(context);
    }

    public void initializeAll() {
        cleanDb();
        initializeCourse();
    }

    public void initializeCourse() {
        insertListOfCourses(courseDao, createCourses());
    }


    private List<Course> createCourses() {

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


        return courses;
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

}
