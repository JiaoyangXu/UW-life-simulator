package com.example.uw_life_simulator.model;

import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private Player owner;
    private List<Integer> takenCourses = new ArrayList<>();
    private List<Integer> currentCourses = new ArrayList<>();


    // Getter & Setter
    public List<Integer> getTakenCourses(){return takenCourses;}
    public List<Integer> getCurrentCourses() {return currentCourses;}


    //Constructors
    public CourseManager(Player owner) {
        this.owner = owner;
    }
    public CourseManager(Player owner, List<Integer> takenCourses, List<Integer> currentCourses) {
        this.owner = owner;
        this.takenCourses = takenCourses;
        this.currentCourses = currentCourses;
    }


    // public functions
    /**
     * Finish a term, add all courses from currentCourse to takenCourse
     *
     * Input: void
     * Output: void
     **/
    public void termFinished() {
        takenCourses.addAll(currentCourses);
    }

    /**
     * Add a course with certain courseId to the player's course manager
     * If the player doesn't satisfied the requirement of the adding course, adding
     * will fail and return "false". Otherwise add the course and return true
     *
     * Input: int : courseId
     * Output: bool : whether the adding is successful
     **/
    public boolean addCourse(int courseId){
        if (Factory.generateCourse(courseId) == null)
        {
            return false;
        }
        
        if (Factory.generateCourse(courseId).checkValidity(takenCourses))
        {
            currentCourses.add(courseId);
            return true;
        }
        return false;
    }
}
