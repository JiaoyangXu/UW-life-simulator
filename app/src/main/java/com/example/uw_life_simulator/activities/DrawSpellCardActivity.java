package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bakerj.infinitecards.AnimationTransformer;
import com.bakerj.infinitecards.InfiniteCardView;
import com.bakerj.infinitecards.transformer.DefaultTransformerToBack;
import com.bakerj.infinitecards.transformer.DefaultTransformerToFront;
import com.bakerj.infinitecards.transformer.DefaultZIndexTransformerCommon;
import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.SpellCardDAO;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.Database.SpellCardDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.data.SpellCard;
import com.example.uw_life_simulator.model.CardAdapter;
import com.google.android.material.snackbar.Snackbar;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawSpellCardActivity extends AppCompatActivity {
    Button nextCard, prevCard, buyCard, finishCardPurchase;
    InfiniteCardView cardStack;
    CardAdapter cardAdapter;
    List<Integer> card_list = new ArrayList<>();
    List<SpellCard> spellCards;
    SpellCardDAO spellCardDAO;
    int cardLeft = 3;



    int picCounter;
    private static long mLastClickTime = 0L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_spell_card);

        picCounter = 0;

        initializeButton();
        initializeCardStack();
        initializeDb();
    }

    private void initializeDb() {
        SpellCardDatabase db = Room.databaseBuilder(getApplicationContext(),
                        SpellCardDatabase.class, "SpellCard").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();

        spellCards = new ArrayList<>();
        spellCardDAO = db.spellCardDAO();

        spellCards = spellCardDAO.selectAll();

        for (Integer integer : card_list) {
            boolean isStored = false;
            SpellCard spellCard = new SpellCard(integer,Integer.toString(integer));

            for (SpellCard card : spellCards) {
                if (card.address == spellCard.address) {
                    isStored = true;
                    return;
                }
            }

            if (!isStored) {
                spellCardDAO.insertAll(spellCard);
                spellCards.add(spellCard);
            }
        }
        spellCardDAO.updateNameByAddress("health_card",card_list.get(0));
        spellCardDAO.updateNameByAddress("iq_card",card_list.get(1));
        spellCardDAO.updateNameByAddress("wealth_card",card_list.get(2));
        spellCardDAO.updateNameByAddress("luck_card",card_list.get(3));






    }

    private void initializeCardStack() {
        initializeCardList();

        cardAdapter = new CardAdapter(this, card_list);

        cardStack = (InfiniteCardView) findViewById(R.id.view);
        cardStack.setClickable(true);
        cardStack.setAnimType(InfiniteCardView.ANIM_TYPE_FRONT);
        cardStack.setAnimInterpolator(new LinearInterpolator());
        cardStack.setTransformerToFront(new DefaultTransformerToFront());
        cardStack.setTransformerToBack(new DefaultTransformerToBack());
        cardStack.setZIndexTransformerToBack(new DefaultZIndexTransformerCommon());
        cardStack.setAdapter(cardAdapter);
    }

    private void initializeCardList() {
        card_list.add(R.drawable.health_card);
        card_list.add(R.drawable.iq_card);
        card_list.add(R.drawable.wealth_card);
        card_list.add(R.drawable.luck_card);
    }

    private void initializeButton() {
        nextCard = (Button) findViewById(R.id.nextCard);
        prevCard = (Button) findViewById(R.id.prevCard);
        buyCard = (Button) findViewById(R.id.BuyCard);
        finishCardPurchase = (Button) findViewById(R.id.finishCardPurchase);
        nextCard.setVisibility(View.GONE);

        nextCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStack.bringCardToFront(1);
            }
        });

        prevCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenRecently())  {
                    generateSnackBar(view, "Not ready yet, please let the animation finish");
                    return;
                }
                cardStack.bringCardToFront(cardAdapter.getCount() - 1);
                updateCurrentCard();
                System.out.println("Current Card " + picCounter);
            }
        });

        buyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpenRecently())  {
                    generateSnackBar(view, "Not ready yet, please let the animation finish");
                    return;
                }

                if (--cardLeft == 0) {
                    showFinishMessage();
                }
                updateCardLeftMessage();

                if (card_list.size() == 0) return;

                updateSelected(picCounter);
                card_list.remove(picCounter);
                cardStack.setAdapter(cardAdapter);

                picCounter = 0;
            }
        });

        finishCardPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // pass intent to Event Activity
                Intent intent = new Intent(DrawSpellCardActivity.this, CourseSelectionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateSelected(int index) {
        if (card_list.size() == 0) {
            return;
        }

        int addr = card_list.get(index);
        SpellCard spellCard = spellCardDAO.getSpellCard(addr);
        System.out.println("updateSelected: " + addr);
        spellCardDAO.updateSelected(addr);
        spellCard.selected = 1;
    }

    public static boolean isOpenRecently() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1500) {
            return true;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        return false;
    }

    private void updateCurrentCard() {
        picCounter = (picCounter == 0) ? cardAdapter.getCount() - 1 : picCounter-1;
        //if (picCounter == 0) picCounter = cardAdapter.getCount()-1;
    }

    private void generateSnackBar(View view, String text) {
        Snackbar mySnackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }

    private void showFinishMessage() {
        AlertDialog alertDialog = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setMessage("You Have Purchased 3 Cards, Now You Can Begin Your Adventure!")
                .setCancelable(true)
                .setTitle("END OF PURCHASE")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the TryAgain button event back to the host fragment
                        dialog.dismiss();
                        Intent intent = new Intent(DrawSpellCardActivity.this, CourseSelectionActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                })
                .show();
    }

    private void updateCardLeftMessage() {
        TextView textView = findViewById(R.id.tvCardLeft);
        textView.setText("You Can Buy " + cardLeft + " More Card");
    }
    

}

