package com.example.uw_life_simulator.Database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.data.Course;
import com.example.uw_life_simulator.data.CourseSelectionRecord;


@Database(entities = {Course.class, CourseSelectionRecord.class},
        version = 4, exportSchema = false)
public abstract class CourseDatabase  extends RoomDatabase {

    public abstract CourseDao courseDao();
    public abstract CourseSelectionRecordDAO courseSelectionRecordDAO();
}
