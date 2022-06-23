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

<<<<<<< HEAD
    @Query("DELETE FROM PlayerAttribute")
    void deleteAll();
=======
    @Query("SELECT * FROM PlayerAttribute")
    List<PlayerAttribute> loadSingle();
>>>>>>> 256757e495d9234d59e40aa553e48356ff4fa69d
}
