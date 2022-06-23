package com.example.uw_life_simulator.Service;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.data.Course;
import com.example.uw_life_simulator.data.CourseSelectionRecord;

import java.util.List;

public class CourseSelectionService {
    private CourseDao courseDao;
    private CourseSelectionRecordDAO courseSelectionRecordDAO;


    public CourseSelectionService(CourseDatabase db) {
        this.courseDao = db.courseDao();
        this.courseSelectionRecordDAO = db.courseSelectionRecordDAO();
    }

    public List<Course> getCheckedCourses(){
        return null;

    }

}
