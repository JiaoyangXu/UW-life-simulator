package com.example.uw_life_simulator;

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
    /*Finish a term, add all courses from currentCourse to takenCourse*/
    public void termFinished() {
        takenCourses.addAll(currentCourses);
    }


    public boolean addCourse(int courseId){
        if (Factory.generateCourse(courseId).checkValidity(takenCourses))
        {
            currentCourses.add(courseId);
            return true;
        }
        return false;
    }
}
