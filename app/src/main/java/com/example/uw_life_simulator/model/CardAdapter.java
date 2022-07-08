package com.example.uw_life_simulator.model;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> card_list;

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
        return null;
    }
}
