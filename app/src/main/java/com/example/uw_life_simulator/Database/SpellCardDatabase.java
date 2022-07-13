package com.example.uw_life_simulator.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.uw_life_simulator.DAO.PlayerAttributeDAO;
import com.example.uw_life_simulator.DAO.SpellCardDAO;
import com.example.uw_life_simulator.data.PlayerAttribute;
import com.example.uw_life_simulator.data.SpellCard;

@Database(entities = {SpellCard.class}, version = 2, exportSchema = false)
public abstract class SpellCardDatabase extends RoomDatabase {
    public abstract SpellCardDAO spellCardDAO();
}
