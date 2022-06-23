package com.example.uw_life_simulator.Service;

import android.content.Context;

import androidx.room.Room;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.DAO.PlayerAttributeDAO;
import com.example.uw_life_simulator.DAO.PlayerAttributeDAO_Impl;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.Database.PlayerAttributeDatabase;
import com.example.uw_life_simulator.data.CourseSelectionRecord;
import com.example.uw_life_simulator.data.PlayerAttribute;

public class DbCleanService {
    CourseDatabase courseDb;
    PlayerAttributeDatabase PlayerDb;

    CourseDao courseDao;
    CourseSelectionRecordDAO recordDAO;
    PlayerAttributeDAO playerDAO;


    public DbCleanService(Context context) {
        PlayerDb = Room.databaseBuilder(context,
                PlayerAttributeDatabase.class, "PlayerAttributes").allowMainThreadQueries().build();
        courseDb = Room.databaseBuilder(context,
                        CourseDatabase.class, "Courses").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();
        courseDao = courseDb.courseDao();
        recordDAO = courseDb.courseSelectionRecordDAO();
        playerDAO = PlayerDb.playerAttributeDAO();

    }

    public void cleanDb() {
        uncheckAllCourses();
        cleanCourseRecordTable();
        cleanPlayerTable();
    }

    public void cleanCoursesTable() {
        courseDao.deleteAll();
    }

    public void cleanCourseRecordTable() {
        recordDAO.deleteAll();
    }

    public void uncheckAllCourses() {
        courseDao.update_uncheckAll();
    }

    public void cleanPlayerTable() {
        playerDAO.deleteAll();
    }
}
