package com.example.uw_life_simulator.Service;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.data.Course;
import com.example.uw_life_simulator.data.CourseSelectionRecord;

import java.util.List;

public class CourseSelectionService {
    private CourseDao courseDao;
    private CourseSelectionRecordDAO courseSelectionRecordDAO;


    public CourseSelectionService(CourseDao courseDao, CourseSelectionRecordDAO courseSelectionRecordDAO) {
        this.courseDao = courseDao;
        this.courseSelectionRecordDAO = courseSelectionRecordDAO;
    }

    public List<Course> getCheckedCourses(){
        return null;
    }

}
