package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.uw_life_simulator.DAO.SpellCardDAO;
import com.example.uw_life_simulator.Database.SpellCardDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.data.SpellCard;
import com.example.uw_life_simulator.model.CardAdapter;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;


public class SwipeSpellCardActivity extends AppCompatActivity {

    ArrayList<Integer> card_list;
    CardAdapter cardAdapter;
    SpellCardDatabase spellCardDatabase;
    SpellCardDAO spellCardDAO;


    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_spell_card);

        initializeData();
        initializeDb();



        SwipeFlingAdapterView swipeFlingAdapterView = (SwipeFlingAdapterView) findViewById(R.id.card);

        cardAdapter = new CardAdapter(this,card_list);
        swipeFlingAdapterView.setAdapter(cardAdapter);

        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                card_list.remove(0);
                cardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });

    }

    private void initializeDb() {
         spellCardDatabase = Room.databaseBuilder(getApplicationContext(),
                        SpellCardDatabase.class, "SpellCard").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();
         spellCardDAO = spellCardDatabase.spellCardDAO();

         List<SpellCard> cards = spellCardDAO.getSelectedSpellCard();

         for (SpellCard card : cards) {
             if (card == null) break;
             card_list.add(card.address);
         }

    }

    private void initializeData() {
        card_list = new ArrayList<>();
    }
}