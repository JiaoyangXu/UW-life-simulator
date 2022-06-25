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
import java.util.List;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import com.example.uw_life_simulator.model.*;
import android.widget.TextView;
import java.io.*;

// Room framework import:
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.uw_life_simulator.DAO.PlayerAttributeDAO;
import com.example.uw_life_simulator.data.PlayerAttribute;
import com.example.uw_life_simulator.Database.PlayerAttributeDatabase;

import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.data.CourseSelectionRecord;
import com.example.uw_life_simulator.Database.CourseDatabase;

public class EventActivity extends AppCompatActivity implements event_list_adapter.ItemClickListener {


    private static final String TAG = "EventActivity";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int EVENTSET_COUNT = 0;
    Player mPlayer;
    NewEvent mNewEvent;

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
        mPlayer = new Player();
        mNewEvent = new NewEvent();
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnTouchListener(new RVClickHandler(mRecyclerView));

        // ****************************************************************************************
        // The following lines of codes serve as an example for the usage of Room SQLite Database
        // to transfer player data in-between different activities.

        // Create a local instance of the PlayerAttribute database (consider it like a local repo)
        // "PlayerAttributes" is the name of the existing database that we want to query data from.
        PlayerAttributeDatabase db = Room.databaseBuilder(getApplicationContext(),
                PlayerAttributeDatabase.class, "PlayerAttributes").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        // DAO is where the queries are setup. You can define new queries in the corresponding DAO
        // files.
        PlayerAttributeDAO playerAttributeDAO = db.playerAttributeDAO();

        // Create a local instance of the Course database. (Changing the string will cause Room to
        // create an empty DB instead of searching on existing ones)
        CourseDatabase db2 = Room.databaseBuilder(getApplicationContext(),
                CourseDatabase.class, "Courses").allowMainThreadQueries().build();
        CourseSelectionRecordDAO courseSelectionRecordDAO = db2.courseSelectionRecordDAO();

        // Get a player instance (a line of data) from Room database.
        List<PlayerAttribute> tmpList = playerAttributeDAO.loadSingle();

        if (tmpList.size() != 0) {
            PlayerAttribute curPlayer = tmpList.get(0);

            // Display the player attributes from database to front-end.
            // Please see PlayerAttribute.java for all available attributes in the database.
            TextView tv1 = (TextView)findViewById(R.id.textView10); // Pressure
            tv1.setText(String.valueOf(curPlayer.getPressure()));
            TextView tv2 = (TextView)findViewById(R.id.textView12); // Wealth
            tv2.setText(String.valueOf(curPlayer.getWealth()));
            TextView tv3 = (TextView)findViewById(R.id.textView14); // GPA
            tv3.setText(String.valueOf(curPlayer.getGPA()));
        }

        // Select current courses (grade = -1) (selectCurrent() will pick all the course selection
        // record that have completionGrade = -1, ie not received final grade yet)
        List<CourseSelectionRecord> curSelection = courseSelectionRecordDAO.selectCurrent();

        // Select TextViews for displaying current course selection
        TextView curCourseTV1 = (TextView)findViewById(R.id.textView23);
        TextView curCourseTV2 = (TextView)findViewById(R.id.textView25);
        TextView curCourseTV3 = (TextView)findViewById(R.id.textView26);
        TextView curCourseTV4 = (TextView)findViewById(R.id.textView27);

        if (curSelection.size() > 0) {

            // Currently let's just assume the player take 4 courses per term for convenience.
            // Get the 4 courses he or she is currently enrolled.
            CourseSelectionRecord curCourse1 = curSelection.get(0);
            CourseSelectionRecord curCourse2 = curSelection.get(1);
            CourseSelectionRecord curCourse3 = curSelection.get(2);
            CourseSelectionRecord curCourse4 = curSelection.get(3);

            // Show the text on the corresponding textviews.
            curCourseTV1.setText(curCourse1.getCourseCode());
            curCourseTV2.setText(curCourse2.getCourseCode());
            curCourseTV3.setText(curCourse3.getCourseCode());
            curCourseTV4.setText(curCourse4.getCourseCode());
        }

        /*mRecyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.d(TAG, "clicked on recyclerview, generate new event");
                String s = String.valueOf(mAdapter.getItemCount());
                String event = "NewEvent called in onClick." + s;
                mAdapter.addEvent(event);
                return false;
            }
        });*/
        Button mNewEventButton = findViewById(R.id.Eventbutton);
        mNewEventButton.setOnClickListener((v) -> {
            Log.d(TAG, "clicked on recyclerview, generate new event");
            String s = String.valueOf(mAdapter.getItemCount());
            String event = "NewEvent called in onClick." + s;
            event = mNewEvent.generateNewEvent(mPlayer,mAdapter.getItemCount());
            mAdapter.addEvent(event);
            mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
        });
        /*mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked on recyclerview, generate new event");
                String s = String.valueOf(mAdapter.getItemCount());
                String event = "NewEvent called in onClick." + s;
                mAdapter.addEvent(event);
            }
        });*/
    }
    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick called");
        String s = String.valueOf(mAdapter.getItemCount());
        String event = "NewEvent called in onItemClick" + s;
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
