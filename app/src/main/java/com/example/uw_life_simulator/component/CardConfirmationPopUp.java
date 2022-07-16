package com.example.uw_life_simulator.component;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.room.Room;

import com.example.uw_life_simulator.DAO.SpellCardDAO;
import com.example.uw_life_simulator.Database.SpellCardDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.activities.EventActivity;
import com.example.uw_life_simulator.activities.SwipeSpellCardActivity;
import com.example.uw_life_simulator.data.SpellCard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CardConfirmationPopUp extends PopUpClass{
    Button confirmationButton;
    Boolean switchContext;
    List<SpellCard> spellCards;




    public CardConfirmationPopUp(View view, int windowID, Boolean currContext, List<SpellCard> cards)  {
        super(view,windowID);

        this.spellCards = cards;
        this.switchContext = currContext;

        confirmationButton = (Button) popUpView.findViewById(R.id.spell_card_popup_button);

        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchContext = true;
                Intent intent = new Intent(popUpView.getContext(), EventActivity.class);
                popUpView.getContext().startActivity(intent);
            }
        });
    }

    private String Display () {
        return null;
    }


}
