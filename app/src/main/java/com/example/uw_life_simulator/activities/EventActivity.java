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
import android.view.MotionEvent;
import android.widget.Button;

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

    public class RVClickHandler implements View.OnTouchListener {

        private RecyclerView mRecyclerView;
        private float mStartX;
        private float mStartY;

        public RVClickHandler(RecyclerView recyclerView) {
            mRecyclerView = recyclerView;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            boolean isConsumed = false;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    mStartX = event.getX();
                    mStartY = event.getY();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    float endX = event.getX();
                    float endY = event.getY();
                    if(detectClick(mStartX, mStartY, endX, endY)) {
                        //Ideally it would never be called when a child View is clicked.
                        //I am not so sure about this.
                        View itemView = mRecyclerView.findChildViewUnder(endX, endY);
                        if(itemView == null) {
                            //RecyclerView clicked
                            mRecyclerView.performClick();
                            isConsumed = true;
                        }
                    }
                    break;
                }
            }
            return isConsumed;
        }

        private boolean detectClick(float startX, float startY, float endX, float endY) {
            return Math.abs(startX-endX) < 3.0 && Math.abs(startY-endY) < 3.0;
        }

    }

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
        mRecyclerView.setOnTouchListener(new RVClickHandler(mRecyclerView));
        /*mRecyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.d(TAG, "clicked on recyclerview, generate new event");
                String s = String.valueOf(mAdapter.getItemCount());
                String event = "Event called in onClick." + s;
                mAdapter.addEvent(event);
                return false;
            }
        });*/
        Button mNewEventButton = findViewById(R.id.Eventbutton);
        mNewEventButton.setOnClickListener((v) -> {
            Log.d(TAG, "clicked on recyclerview, generate new event");
            String s = String.valueOf(mAdapter.getItemCount());
            String event = "Event called in onClick." + s;
            mAdapter.addEvent(event);
            mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
        });
        /*mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked on recyclerview, generate new event");
                String s = String.valueOf(mAdapter.getItemCount());
                String event = "Event called in onClick." + s;
                mAdapter.addEvent(event);
            }
        });*/
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
