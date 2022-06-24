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
    @Insert
    void insertAll(PlayerAttribute playerAttribute);

    @Query("DELETE FROM PlayerAttribute")
    void deleteAll();

    @Query("SELECT * FROM PlayerAttribute")
    List<PlayerAttribute> loadSingle();

}
