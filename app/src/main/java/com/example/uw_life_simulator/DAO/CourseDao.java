package com.example.uw_life_simulator.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uw_life_simulator.data.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM course")
    List<Course> getAll();

    @Query("SELECT code FROM course")
    List<String> getCourseCode();

    @Query("SELECT * FROM course WHERE code = :courseCode")
    List<Course> getCourseWithCode(String courseCode);

    @Query("SELECT * FROM course WHERE isChecked = 0")
    List<Course> getUnCheckedCourse();

    @Query("SELECT * FROM course WHERE code = :pre AND isChecked = 1")
    List<Course> getTakenPreCourse(String pre);

    @Query("SELECT prereq FROM course WHERE code = :name")
    String getPreqByName(String name);

    @Insert
    void insertAll(Course course);

    @Delete
    void delete(Course course);

    // update the isChecked column, given a course Code
    @Query("UPDATE Course SET isChecked = 1 WHERE code = :CourseCode")
    void update_check(String CourseCode);

    // clear the table
    @Query("DELETE FROM Course")
    void deleteAll();

    // Uncheck all courses
    @Query("UPDATE Course SET isChecked = 0 WHERE isChecked = 1")
    void update_uncheckAll();

    @Query("SELECT courseID FROM course")
    List<Integer> selectAllUUID();

    @Query("SELECT * FROM course WHERE courseID =:givenID")
    Course selectCourseFromUUID(Integer givenID);

    @Query("SELECT difficulty FROM course WHERE courseID =:givenID")
    List<Integer> selectDiffByID(String givenID);

}
