package com.example.uw_life_simulator;

import java.util.List;

public class Course {
    private String name;
    private String description;
    //prerequisite for the current course
    private List<Integer> requiredCourses;
    private int inSubject;
    private int award;


    // Getter & Setter
    public int getAward() {return award;}
    public int getInSubject() { return inSubject; }
    public String getName() {return name;}
    public String getDescription() {return description;}


    //Constructors
    public Course(String course_name, String course_description, int subject,
                  List<Integer> requirements, int finish_award){
        this.name = course_name;
        this.description = course_description;
        this.requiredCourses = requirements;
        this.inSubject = subject;
        this.award = finish_award;
    }


    // public functions
    /* Check if the given list of taken courses can satisfy this course's requirement*/
    public boolean checkValidity(List<Integer> takenCourses)
    {
        for(int course : requiredCourses)
        {
            if (!takenCourses.contains(course))
            {
                return false;
            }
        }
        return true;
    }
}
