package com.example.uw_life_simulator.model;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.uw_life_simulator.R;

public class CardAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> card_list;

    public CardAdapter(Context context, List<Integer> card_list) {
        this.context = context;
        this.card_list = card_list;
    }

    @Override
    public int getCount() {
        return card_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_draw_spell_card, parent, false);
        }
        convertView.setBackgroundColor(card_list.get(0));
        return convertView;
    }
}
