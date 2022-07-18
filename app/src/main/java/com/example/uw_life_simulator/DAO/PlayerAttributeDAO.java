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

    @Query("UPDATE PlayerAttribute SET IQ = IQ + :num")
    void increaseIQ(Integer num);

    @Query("UPDATE PLAYERATTRIBUTE SET luck = luck + :num")
    void increaseLuck(Integer num);

    @Query("UPDATE PLAYERATTRIBUTE SET wealth = wealth + :num")
    void increaseWealth(Integer num);

    @Query("UPDATE PLAYERATTRIBUTE SET health = health + :num")
    void increaseHealth(Integer num);




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

    @Query("SELECT course1Code FROM PLAYERATTRIBUTE")
    List<String> getCourse1();

    @Query("SELECT course2Code FROM PLAYERATTRIBUTE")
    List<String> getCourse2();

    @Query("SELECT course3Code FROM PLAYERATTRIBUTE")
    List<String> getCourse3();

    @Query("SELECT course4Code FROM PLAYERATTRIBUTE")
    List<String> getCourse4();

    @Query("SELECT iq FROM PlayerAttribute")
    List<Integer> getIQ1();

    @Query("SELECT luck FROM PlayerAttribute")
    List<Integer> getLuck1();

    @Query("SELECT wealth FROM PlayerAttribute")
    List<Integer> getWealth1();

    @Query("SELECT Health FROM PlayerAttribute")
    List<Integer> getHealth1();


}
