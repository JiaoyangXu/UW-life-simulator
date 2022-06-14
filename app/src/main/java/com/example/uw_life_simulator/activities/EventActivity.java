package com.example.uw_life_simulator.activities;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.model.EventFragment;
import com.example.uw_life_simulator.model.event_list_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.util.Log;

public class EventActivity extends AppCompatActivity implements event_list_adapter.ItemClickListener {

    /*public static final String TAG = "EventActivity";
    EventFragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_page);
        if (savedInstanceState == null) {
            mFragment = new EventFragment();
        }

    }*/
    private static final String TAG = "EventActivity";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int EVENTSET_COUNT = 0;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected EventActivity.LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected event_list_adapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> mEventset;
    //protected String[] mDataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_page);
        initEventset();
        mRecyclerView = findViewById(R.id.EventRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new event_list_adapter(mEventset);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked on recyclerview, generate new event");
                String s = String.valueOf(mAdapter.getItemCount());
                String event = "Event called in onClick." + s;
                mAdapter.addEvent(event);
            }
        });
    }
    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick called");
        String s = String.valueOf(mAdapter.getItemCount());
        String event = "Event called in onItemClick" + s;
        addEvent2(event);
    }

    private void initEventset() {
        ArrayList<String> cars = new ArrayList<String>();
        mEventset = new ArrayList<String>();
        /*for (int i = 0; i < EVENTSET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }*/
    }
    public void onButtonClick(View view) {
        String event = "event";
        addEvent2(event);
    }
    private void addEvent2(String event) {
        String item = "Pig";
        int insertIndex = mAdapter.getItemCount();
        mEventset.add(event);
        mAdapter.notifyItemInserted(mAdapter.getItemCount()-1);
    }


}
