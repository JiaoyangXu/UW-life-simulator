package com.example.uw_life_simulator.Database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.data.Course;


@Database(entities = {Course.class},
        version = 1)
public abstract class CourseDatabase  extends RoomDatabase {

    public abstract CourseDao courseDao();
}
