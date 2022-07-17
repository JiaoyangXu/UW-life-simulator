package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.uw_life_simulator.DAO.SpellCardDAO;
import com.example.uw_life_simulator.Database.SpellCardDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.component.CardConfirmationPopUp;
import com.example.uw_life_simulator.component.PopUpClass;
import com.example.uw_life_simulator.data.SpellCard;
import com.example.uw_life_simulator.model.CardAdapter;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.example.uw_life_simulator.adapter.cardConfirmationAdapter;

import java.util.ArrayList;
import java.util.List;


public class SwipeSpellCardActivity extends AppCompatActivity {

    List<SpellCard> spellCards;
    ArrayList<Integer> card_list;
    CardAdapter cardAdapter;
    SpellCardDatabase spellCardDatabase;
    SpellCardDAO spellCardDAO;
    Boolean switchContext;
    ArrayList<String> cardInfo_list;


    cardConfirmationAdapter cardConfirmationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_spell_card);

        initializeAll();

        SwipeFlingAdapterView swipeFlingAdapterView = (SwipeFlingAdapterView) findViewById(R.id.card);

        cardAdapter = new CardAdapter(this,card_list);
        swipeFlingAdapterView.setAdapter(cardAdapter);

        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

                String myString = spellCardDAO.getNameByAddr(card_list.get(0));

                cardInfo_list.add(myString);
                card_list.remove(0);
                cardAdapter.notifyDataSetChanged();

                if (card_list.size() == 0) {
                    backEventPage();
                }

            }

            @Override
            public void onLeftCardExit(Object o) {
                cardInfo_list.remove(cardInfo_list.size()-1);
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

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void backEventPage() {
        PopUpClass popUpClass = new CardConfirmationPopUp(findViewById(R.id.swipe_card_view),
                R.layout.spell_card_popup,
                switchContext, spellCardDAO.getUsedSpellCard());
//        popUpClass.showPopUp();

        showDialog();

    }


    void showDialog() {
        final Dialog dialog = new Dialog(SwipeSpellCardActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(true);

        dialog.setContentView(R.layout.spell_card_popup);

        Button confirmationButton;
        confirmationButton = (Button) dialog.findViewById(R.id.spell_card_popup_button);

        // set up recyclerView
        RecyclerView recyclerView = dialog.findViewById(R.id.rvCardConfirmation);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardConfirmationAdapter = new cardConfirmationAdapter(this, cardInfo_list);

        recyclerView.setAdapter(cardConfirmationAdapter);


        dialog.show();

        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchContext = true;
                dialog.dismiss();
                finish();
            }
        });

    }

    private void initializeAll () {
        switchContext = false;

        initializeData();
        initializeDb();
    }



    private void initializeDb() {
         spellCardDatabase = Room.databaseBuilder(getApplicationContext(),
                        SpellCardDatabase.class, "SpellCard").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();
         spellCardDAO = spellCardDatabase.spellCardDAO();

         spellCards = spellCardDAO.getSelectedSpellCard();

         for (SpellCard card : spellCards) {
             if (card == null) break;
             card_list.add(card.address);
         }

    }

    private void initializeData() {
        card_list = new ArrayList<>();
        cardInfo_list = new ArrayList<>();
    }

    private void storeUsedCard(Integer addr) {
        spellCardDAO.updateUsed(addr);
    }
}