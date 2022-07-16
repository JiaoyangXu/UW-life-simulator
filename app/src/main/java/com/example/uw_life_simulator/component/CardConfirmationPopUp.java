package com.example.uw_life_simulator.component;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.activities.EventActivity;
import com.example.uw_life_simulator.activities.SwipeSpellCardActivity;

public class CardConfirmationPopUp extends PopUpClass{
    Button confirmationButton;
    Boolean switchContext;


    public CardConfirmationPopUp(View view, int windowID, Boolean currContext) {
        super(view,windowID);

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


}
