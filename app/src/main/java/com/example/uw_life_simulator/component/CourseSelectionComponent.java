package com.example.uw_life_simulator.component;

import android.widget.CheckBox;
import android.widget.TextView;

import com.example.uw_life_simulator.data.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseSelectionComponent {
    private List<CheckBox> checkBoxes;
    private List<TextView> textViews;
    private List<Course> availableCourses;
    private List<String> availableCourseCodes;

    private HashMap<String,Course> CourseMap = new HashMap<>();



    public CourseSelectionComponent(List<CheckBox> checkBoxes, List<TextView> textViews, List<Course> availableCourses, List<String> availableCourseCodes) {
        this.checkBoxes = checkBoxes;
        this.textViews = textViews;
        this.availableCourses = availableCourses;
        this.availableCourseCodes = availableCourseCodes;
        constructCourseMap();
    }

    public void constructCourseMap() {
        for(Course course : availableCourses) {
            CourseMap.put(course.getCourseCode(), course);
        }
    }

    public HashMap<String, Course> getCourseMap() {
        return CourseMap;
    }

    @Override
    public String toString() {
        return "CourseSelectionComponent{" +
                "checkBoxes=" + checkBoxes +
                ", textViews=" + textViews +
                ", availableCourses=" + availableCourses +
                ", availableCourseCodes=" + availableCourseCodes +
                '}';
    }

    public List<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public void setCheckBoxes(List<CheckBox> checkBoxes) {
        this.checkBoxes = checkBoxes;
    }

    public List<TextView> getTextViews() {
        return textViews;
    }

    public void setTextViews(List<TextView> textViews) {
        this.textViews = textViews;
    }

    public List<Course> getAvailableCourses() {
        return availableCourses;
    }

    public void setAvailableCourses(List<Course> availableCourses) {
        this.availableCourses = availableCourses;
    }

    public List<String> getAvailableCourseCodes() {
        return availableCourseCodes;
    }

    public void setAvailableCourseCodes(List<String> availableCourseCodes) {
        this.availableCourseCodes = availableCourseCodes;
    }
}
