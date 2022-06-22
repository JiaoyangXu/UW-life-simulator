package com.example.uw_life_simulator.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public Course(int courseID, String courseCode, String courseName, int difficulty, int usefulness) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.difficulty = difficulty;
        this.usefulness = usefulness;
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
