package com.example.uw_life_simulator.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uw_life_simulator.data.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM course")
    List<Course> getAll();

    @Insert
    void insertAll(Course course);
}
