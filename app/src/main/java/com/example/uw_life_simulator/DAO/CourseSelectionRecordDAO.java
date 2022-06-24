package com.example.uw_life_simulator.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uw_life_simulator.data.CourseSelectionRecord;

import java.util.List;

@Dao
public interface CourseSelectionRecordDAO {
    @Insert
    void insertAll(CourseSelectionRecord courseSelectionRecord);

    @Query("DELETE FROM CourseSelectionRecord")
    void deleteAll();

    @Query("SELECT * FROM CourseSelectionRecord")
    List<CourseSelectionRecord> selectAll();

    @Query("SELECT * FROM CourseSelectionRecord WHERE completionGrade = -1")
    List<CourseSelectionRecord> selectCurrent();

    @Query("SELECT * FROM CourseSelectionRecord")
    List<CourseSelectionRecord> selectAll();
}
