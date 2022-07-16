package com.example.uw_life_simulator.component;

import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.uw_life_simulator.R;


public class PopUpClass {
    View view;
    View popUpView;
    PopupWindow popupWindow;

    public PopUpClass(View view, int windowID) {
        this.view = view;
        LayoutInflater layoutInflater = (LayoutInflater) view.getContext().
                getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        popUpView = layoutInflater.inflate(windowID,null);


        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        popupWindow = new PopupWindow(popUpView, width, height, true);
    }

    public void showPopUp() {
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // Modify the text in TextView popup
        modifyTextViewText(popUpView, R.id.spell_card_popup_text, "You selected card");
    }

    public void modifyTextViewText(View view, int field, String text) {
        TextView textView = view.findViewById(field);
        textView.setText(text);
    }
}
