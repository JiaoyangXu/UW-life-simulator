package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bakerj.infinitecards.InfiniteCardView;
import com.bakerj.infinitecards.transformer.DefaultTransformerToBack;
import com.bakerj.infinitecards.transformer.DefaultTransformerToFront;
import com.bakerj.infinitecards.transformer.DefaultZIndexTransformerCommon;
import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.model.CardAdapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawSpellCardActivity extends AppCompatActivity {
    InfiniteCardView cardStack;
    CardAdapter cardAdapter;
    List<Integer> card_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_spell_card);

        card_list.add(R.drawable.img);
        card_list.add(R.drawable.img_1);
        card_list.add(R.drawable.img_2);
        card_list.add(R.drawable.img_3);

        cardAdapter = new CardAdapter(this, card_list);

        cardStack = (InfiniteCardView) findViewById(R.id.view);

        //cardStack.setClickable(true);

        cardStack.setAnimType(InfiniteCardView.ANIM_TYPE_FRONT);
        cardStack.setAnimInterpolator(new LinearInterpolator());
        cardStack.setTransformerToFront(new DefaultTransformerToFront());
        cardStack.setTransformerToBack(new DefaultTransformerToBack());
        cardStack.setZIndexTransformerToBack(new DefaultZIndexTransformerCommon());
        cardStack.setAdapter(cardAdapter);

        for (int i = 0; i < 10; i++) {
            cardStack.bringCardToFront(i);
        }

    }
}

