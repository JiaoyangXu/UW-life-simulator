package com.example.uw_life_simulator.DAO;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uw_life_simulator.data.PlayerAttribute;
import com.example.uw_life_simulator.model.Player;

import java.util.List;

@Dao
public interface PlayerAttributeDAO {
    // Define your queries here.
    @Insert
    void insertAll(PlayerAttribute playerAttribute);

    @Query("DELETE FROM PlayerAttribute")
    void deleteAll();

    @Query("SELECT * FROM PlayerAttribute")
    List<PlayerAttribute> loadSingle();

    @Query("UPDATE PLAYERATTRIBUTE SET course1Code = :code")
    void updateCourse1(String code);

    @Query("UPDATE PLAYERATTRIBUTE SET course2Code = :code")
    void updateCourse2(String code);

    @Query("UPDATE PLAYERATTRIBUTE SET course3Code = :code")
    void updateCourse3(String code);

    @Query("UPDATE PLAYERATTRIBUTE SET course4Code = :code")
    void updateCourse4(String code);


}
