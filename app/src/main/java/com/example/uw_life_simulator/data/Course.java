package com.example.uw_life_simulator.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.UUID;

@Entity
public class Course {

    @PrimaryKey
    public int courseID;

    @ColumnInfo(name = "code")
    public String courseCode;

    @ColumnInfo(name = "name")
    public String courseName;

    @ColumnInfo(name = "difficulty")
    public int difficulty;

    @ColumnInfo(name = "usefulness")
    public int usefulness;

    @ColumnInfo(name = "isChecked")
    public int isChecked;

    @ColumnInfo(name = "award")
    public int award;

    @ColumnInfo(name = "prereq")
    public int prereq;

    @Ignore
    public Course(String courseCode, String courseName, int difficulty, int usefulness) {
        UUID uuid = UUID.randomUUID();
        this.courseID = uuid.hashCode();
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.difficulty = difficulty;
        this.usefulness = usefulness;
        this.isChecked = 0;
        this.award = 0;
    }

    public Course(String courseCode, String courseName, int difficulty, int usefulness, int award) {
        UUID uuid = UUID.randomUUID();
        this.courseID = uuid.hashCode();
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.difficulty = difficulty;
        this.usefulness = usefulness;
        this.isChecked = 0;
        this.award = award;
        this.prereq = -1;
    }


    /**
     * Check if the given list of taken courses can satisfy this
     * course's requirement
     *
     * Input: List<Integer> : takenCourses
     * Output: bool : whether the course is valid to be taken
     **/

    public boolean checkValidity(List<Integer> takenCourses)
    {
        if (!takenCourses.contains(prereq) && prereq == -1)
        {
            return false;
        }
        return true;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
}
