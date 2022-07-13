package com.example.uw_life_simulator.Database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.uw_life_simulator.DAO.PlayerAttributeDAO;
import com.example.uw_life_simulator.data.PlayerAttribute;

@Database(entities = {PlayerAttribute.class}, version = 7, exportSchema = false)

public abstract class PlayerAttributeDatabase extends RoomDatabase {
    public abstract PlayerAttributeDAO playerAttributeDAO();
}
