package com.example.uw_life_simulator.model;

import java.util.List;

public class Course_deprived {
    private String courseId;
    private String name;
    private String description;
    private int difficulty;
    private int usefulness;
    //prerequisite for the current course
    private List<Integer> requiredCourses;
    private int inSubject;
    private int award;

    public Course_deprived(String courseId, String name, int difficulty, int usefulness) {
        this.courseId = courseId;
        this.name = name;
        this.difficulty = difficulty;
        this.usefulness = usefulness;
    }

    // Getter & Setter
    public int getAward() {return award;}
    public int getInSubject() { return inSubject; }
    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public int getUsefulness() {
        return usefulness;
    }
    public void setUsefulness(int usefulness) {
        this.usefulness = usefulness;
    }

    //Constructors
    public Course_deprived(String course_name, String course_description, int subject,
                           List<Integer> requirements, int finish_award){
        this.name = course_name;
        this.description = course_description;
        this.requiredCourses = requirements;
        this.inSubject = subject;
        this.award = finish_award;
    }


    // public functions
    /**
     * Check if the given list of taken courses can satisfy this
     * course's requirement
     *
     * Input: List<Integer> : takenCourses
     * Output: bool : whether the course is valid to be taken
     **/
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
