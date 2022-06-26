package com.example.uw_life_simulator.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CourseSelectionRecord {
    @PrimaryKey(autoGenerate = true)
    public int recordID;

    @ColumnInfo
    public int playerID;

    @ColumnInfo
    public String courseCode;

    @ColumnInfo
    public int completionGrade;

    public CourseSelectionRecord(int playerID, String courseCode) {
        this.playerID = playerID;
        this.courseCode = courseCode;
        this.completionGrade = -1;
    }

    @Override
    public String toString() {
        return "CourseSelectionRecord{" +
                "recordID=" + recordID +
                ", playerID=" + playerID +
                ", courseCode='" + courseCode + '\'' +
                ", completionGrade=" + completionGrade +
                '}';
    }

    public String getCourseCode() { return courseCode;}

    public int getCompletionGrade() { return completionGrade;}
}
