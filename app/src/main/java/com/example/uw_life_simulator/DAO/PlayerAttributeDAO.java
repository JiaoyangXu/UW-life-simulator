package com.example.uw_life_simulator.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uw_life_simulator.data.PlayerAttribute;

import java.util.List;

public interface PlayerAttributeDAO {
    @Insert
    void insertAll(PlayerAttribute playerAttribute);
}
