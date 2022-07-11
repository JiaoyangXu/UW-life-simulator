package com.example.uw_life_simulator.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uw_life_simulator.data.SpellCard;

import java.util.List;

@Dao
public interface SpellCardDAO {
    @Query("SELECT * FROM SpellCard")
    List<SpellCard> selectAll();

    @Insert
    void insertAll(SpellCard spellCard);

    // clear the table
    @Query("DELETE FROM spellcard")
    void deleteAll();

    @Query("UPDATE spellcard SET selected = 0 WHERE selected = 1")
    void unselectAll();

    @Query("UPDATE spellcard SET selected = 1 WHERE address =:addr")
    void updateSelected(Integer addr);

    @Query("SELECT * FROM spellcard WHERE address =:addr")
    SpellCard getSpellCard(Integer addr);
}