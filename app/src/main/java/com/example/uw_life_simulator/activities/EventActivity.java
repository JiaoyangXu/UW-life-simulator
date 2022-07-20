package com.example.uw_life_simulator.activities;
import com.example.uw_life_simulator.MiniGame.ManaTestMainAct;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.model.EventFragment;
import com.example.uw_life_simulator.model.event_list_adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.Gravity;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import com.example.uw_life_simulator.model.*;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import android.app.FragmentManager;
import com.example.uw_life_simulator.model.AlertDialogFragment;
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
import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.data.Course;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.MiniGame.CardGameActivity;
import com.example.uw_life_simulator.MiniGame.ManaInstructionPg;
import androidx.fragment.app.FragmentActivity;
import android.app.AlertDialog;

import android.app.Dialog;
import com.example.uw_life_simulator.R;
import android.graphics.drawable.Drawable;

import static java.util.Map.entry;
import java.util.Map;
import java.util.HashMap;


public class EventActivity extends AppCompatActivity implements event_list_adapter.ItemClickListener,AlertDialogFragment.AlertDialogListener {


    private static final String TAG = "EventActivity";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int EVENTSET_COUNT = 0;
    int result = -1;
    Player mPlayer;
    NewEvent mNewEvent;
    boolean called_by_event = false;
    int Event_Count = 0;
    int course1mark = (int) ((Math.random() * (100 - 80)) + 80);
    int course2mark = (int) ((Math.random() * (100 - 80)) + 80);
    int course3mark = (int) ((Math.random() * (100 - 80)) + 80);
    int course4mark = (int) ((Math.random() * (100 - 80)) + 80);
    String course1type = "";
    String course2type = "";
    String course3type = "";
    String course4type = "";
    int MANA_mark = 0;
    int ATRO_mark = 0;
    int MANA_TEST_NUM = 80;
    int ATRO_TEST_NUM = 80;
    int mini_game_difficulty = 1;

    //tmp map to store all course difficulty:

    Map<String, Integer> myMap = new HashMap<String, Integer>() {{
        put("MANA 100", 20);
        put("HERB 100", 50);
        put("HIST 100", 20);
        put("MEDI 100", 70);
        put("SPEL 100", 80);
        put("ATRO 100", 60);
        put("HERB 200", 30);
        put("MANA 200", 70);
        put("SPEL 200", 90);
        put("HIST 200", 50);
        put("MEDI 200", 70);
        put("ATRO 200", 60);
        put("HERB 300", 30);
        put("MANA 300", 70);
        put("SPEL 300", 90);
        put("HIST 300", 50);
        put("MEDI 300", 70);
        put("ATRO 300", 60);
        put("HERB 400", 30);
        put("MANA 400", 70);
        put("SPEL 400", 90);
        put("HIST 400", 50);
        put("MEDI 400", 70);
        put("ATRO 400", 60);
    }};

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected EventActivity.LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected event_list_adapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> mEventset;


    @Override
    public void onOKClick(DialogFragment dialog) {
        // User touched the dialog's OK button
        Log.d(TAG,"user clicked on OK");
        Intent intent = new Intent(EventActivity.this, Summarypage.class);
        startActivity(intent);
    }
    @Override
    public void onCancelClick(DialogFragment dialog) {
        // User touched the dialog's Cancel button
        dialog.dismiss();
    }


    public void showAlertDialog() {
        // Create an instance of the dialog fragment and show it
        AlertDialogFragment mAlertDialogFragment= new AlertDialogFragment();
        mAlertDialogFragment.setListener(this);
        mAlertDialogFragment.setCancelable(false);
        FragmentManager fragMan = getFragmentManager();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                if(called_by_event == true){
                    result = (int)data.getDoubleExtra("Result", 0.0);
                    mNewEvent.setLastMark(result);
                    System.out.println(result);
                    String event = "you received a score of " + result;
                    mAdapter.addEvent(event);
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                    event = mNewEvent.generateNewEvent(mPlayer,true);
                    if(event.equals("MANA")){
                        if (course1type.equals("MANA")){
                            if(MANA_TEST_NUM == 0){
                                course1mark = result;
                                MANA_mark = course1mark;
                            }
                            else if(MANA_TEST_NUM == 1){
                                course1mark = (course1mark + result) / 2;
                                MANA_mark = course1mark;
                            }
                            MANA_TEST_NUM += 1;
                        }
                        if (course2type.equals("MANA")){
                            if(MANA_TEST_NUM == 0){
                                course2mark = result;
                                MANA_mark = course2mark;
                            }
                            else if(MANA_TEST_NUM == 1){
                                course2mark = (course1mark + result) / 2;
                                MANA_mark = course2mark;
                            }
                            MANA_TEST_NUM += 1;
                        }
                        if (course3type.equals("MANA")){
                            if(MANA_TEST_NUM == 0){
                                course3mark = result;
                                MANA_mark = course3mark;
                            }
                            else if(MANA_TEST_NUM == 1){
                                course3mark = (course1mark + result) / 2;
                                MANA_mark = course3mark;
                            }
                            MANA_TEST_NUM += 1;
                        }
                        if (course4type.equals("MANA")){
                            if(MANA_TEST_NUM == 0){
                                course4mark = result;
                                MANA_mark = course4mark;
                            }
                            else if(MANA_TEST_NUM == 1){
                                course4mark = (course1mark + result) / 2;
                                MANA_mark = course4mark;
                            }
                            MANA_TEST_NUM += 1;
                        }
                    }
                    if(event.equals("ATRO")){
                        if (course1type.equals("ATRO")){
                            if(ATRO_TEST_NUM == 0){
                                course1mark = result;
                                ATRO_mark = course1mark;
                            }
                            else if(ATRO_TEST_NUM == 1){
                                course1mark = (course1mark + result) / 2;
                                ATRO_mark = course1mark;
                            }
                            ATRO_TEST_NUM += 1;
                        }
                        if (course2type.equals("ATRO")){
                            if(ATRO_TEST_NUM == 0){
                                course2mark = result;
                                ATRO_mark = course2mark;
                            }
                            else if(ATRO_TEST_NUM == 1){
                                course2mark = (course1mark + result) / 2;
                                ATRO_mark = course2mark;
                            }
                            ATRO_TEST_NUM += 1;
                        }
                        if (course3type.equals("ATRO")){
                            if(ATRO_TEST_NUM == 0){
                                course3mark = result;
                                ATRO_mark = course3mark;
                            }
                            else if(ATRO_TEST_NUM == 1){
                                course3mark = (course1mark + result) / 2;
                                ATRO_mark = course3mark;
                            }
                            ATRO_TEST_NUM += 1;
                        }
                        if (course4type.equals("ATRO")){
                            if(ATRO_TEST_NUM == 0){
                                course4mark = result;
                                ATRO_mark = course4mark;
                            }
                            else if(ATRO_TEST_NUM == 1){
                                course4mark = (course1mark + result) / 2;
                                ATRO_mark = course4mark;
                            }
                            ATRO_TEST_NUM += 1;
                        }
                    }
                    if(event.equals("MANA") || event.equals("ATRO")){
                        called_by_event = false;
                        return;
                    }
                    mAdapter.addEvent(event);
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                    called_by_event = false;
                }

            }
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
        //mRecyclerView.setOnTouchListener(new RVClickHandler(mRecyclerView));

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
        CourseDao courseDao = db2.courseDao();

        // Get a player instance (a line of data) from Room database.
        List<PlayerAttribute> tmpList = playerAttributeDAO.loadSingle();

        PlayerAttribute curPlayer = tmpList.get(tmpList.size() -1);

        // Display the player attributes from database to front-end.
        // Please see PlayerAttribute.java for all available attributes in the database.
        TextView tv1 = (TextView)findViewById(R.id.textView10); // Pressure
        tv1.setText(String.valueOf(curPlayer.getPressure()));
        TextView tv2 = (TextView)findViewById(R.id.textView12); // Wealth
        tv2.setText(String.valueOf(curPlayer.getMoney()));
        TextView tv3 = (TextView)findViewById(R.id.textView14); // GPA
        tv3.setText(String.valueOf(curPlayer.getGPA()));

        // Select current courses (grade = -1) (selectCurrent() will pick all the course selection
        // record that have completionGrade = -1, ie not received final grade yet)
        List<CourseSelectionRecord> curSelection = courseSelectionRecordDAO.selectCurrent();

        // Select TextViews for displaying current course selection
        TextView curCourseTV1 = (TextView)findViewById(R.id.textView23);
        TextView curCourseTV2 = (TextView)findViewById(R.id.textView25);
        TextView curCourseTV3 = (TextView)findViewById(R.id.textView26);
        TextView curCourseTV4 = (TextView)findViewById(R.id.textView27);

        TextView curTerm = (TextView)findViewById(R.id.textView28);

        /*if (curSelection.size() > 0) {
            int count = curSelection.size();
            if (count >= 1) {
                //curCourseTV1.setText(curSelection.get(0).getCourseCode());
                curPlayer.course1Code = curSelection.get(0).getCourseCode();
            }
            if (count >= 2) {
                curCourseTV2.setText(curSelection.get(1).getCourseCode());
                curPlayer.course2Code = curSelection.get(1).getCourseCode();
            }
            if (count >= 3) {
                curCourseTV3.setText(curSelection.get(2).getCourseCode());
                curPlayer.course3Code = curSelection.get(2).getCourseCode();
            }
            if (count >= 4) {
                curCourseTV4.setText(curSelection.get(3).getCourseCode());
                curPlayer.course4Code = curSelection.get(3).getCourseCode();
            }
        }*/

        curCourseTV1.setText(curPlayer.course1Code);
        curCourseTV2.setText(curPlayer.course2Code);
        curCourseTV3.setText(curPlayer.course3Code);
        curCourseTV4.setText(curPlayer.course4Code);

        //update numTerm
        if (curPlayer.numTerm == 0){
            curPlayer.numTerm = 1;
        }

        String term = "Current Term :\n" + String.valueOf(curPlayer.getNumTerm());
        curTerm.setText(term);

        String course1type = curPlayer.course1Code.substring(0,4);
        String course2type = curPlayer.course2Code.substring(0,4);
        String course3type = curPlayer.course3Code.substring(0,4);
        String course4type = curPlayer.course4Code.substring(0,4);

        mPlayer.setPlayerAttribute(curPlayer);
        Button mNewEventButton = findViewById(R.id.Eventbutton);
        mNewEventButton.setOnClickListener((v) -> {
            Event_Count += 1;
            if(Event_Count >= 22){
                //end random generation
                courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course1Code,course1mark);
                courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course2Code,course2mark);
                courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course3Code,course3mark);
                courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course4Code,course4mark);

                //Remove the effect of current course load on player pressure (since the courses are over now)
                int diff1 = myMap.get(curPlayer.course1Code);
                int diff2 = myMap.get(curPlayer.course2Code);
                int diff3 = myMap.get(curPlayer.course3Code);
                int diff4 = myMap.get(curPlayer.course4Code);

                curPlayer.pressure = curPlayer.pressure - diff1/10 - diff2/10 - diff3/10 - diff4/10;

                PlayerAttribute playerAttribute = mPlayer.getPlayerAttribute();
                playerAttribute.setIQ(playerAttributeDAO.getIQ1().get(0));
                playerAttribute.setLuck(playerAttributeDAO.getLuck1().get(0));
                playerAttribute.setHealth(playerAttributeDAO.getHealth1().get(0));
                playerAttribute.setWealth(playerAttributeDAO.getWealth1().get(0));

                mPlayer.setPlayerAttribute(playerAttribute);


                playerAttributeDAO.deleteAll();
                playerAttributeDAO.insertAll(mPlayer.getPlayerAttribute());

                AlertDialog alertDialog = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                        .setMessage(R.string.proceed_to_summary)
                        .setCancelable(true)
                        .setTitle("    END OF TERM")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Send the TryAgain button event back to the host fragment
                                Intent intent = new Intent(EventActivity.this, Summarypage.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d(TAG,"clicked on cancel");
                            }
                        })
                        .show();
                Drawable drawable = getResources().getDrawable(R.drawable.panel);
                alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 550);
                alertDialog.getWindow().setBackgroundDrawable(drawable);

            }
            else{
                Log.d(TAG, "clicked on recyclerview, generate new event");
                String s = String.valueOf(mAdapter.getItemCount());
                String event = "NewEvent called in onClick." + s;
                List<String> event_list = new ArrayList<>();
                //event= mNewEvent.generateNewEvent(mPlayer,mAdapter.getItemCount());
                boolean return_last_event = false;
                event_list = mNewEvent.generateNewChoice(mPlayer,Event_Count);

                //Set test difficulty
                if (Event_Count >= 11)
                {
                    mini_game_difficulty = 2;
                }
                else
                {
                    mini_game_difficulty = 1;
                }

                //2022 0713
                String event_description = event_list.get(0);
                String event_choice1 = event_list.get(1);
                String event_choice2 = event_list.get(2);

                //
                return_last_event = mNewEvent.isFinished();


                //pop up window dialog
                AlertDialog alertDialog = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                //AlertDialog alertDialog = new AlertDialog.Builder(this,R.style.AlertDialogCustom)
                        .setMessage(event_description)
                        .setTitle("    New Event!")
                        .setCancelable(false)
                        .setPositiveButton(event_choice1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Send the TryAgain button event back to the host fragment
                                //鉴定
                                String game_type = mNewEvent.getTestClassName(true);
                                if(game_type.equals("HIST")){
                                    called_by_event = true;
                                    Intent intent = new Intent(EventActivity.this, CardGameActivity.class);
                                    intent.putExtra("Diff", mini_game_difficulty);
                                    startActivityForResult(intent, 0);
                                    double score = intent.getDoubleExtra("Result",0.0);
                                    String event = "You started the test";
                                    mAdapter.addEvent(event);
                                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                                    tv1.setText(String.valueOf(mPlayer.getPlayerAttribute().getPressure()));
                                    // Wealth
                                    tv2.setText(String.valueOf(mPlayer.getPlayerAttribute().getMoney()));
                                    // GPA
                                    tv3.setText(String.valueOf(mPlayer.getPlayerAttribute().getGPA()));

                                }
                                else if(game_type.equals("MANA")){
                                    called_by_event = true;
                                    Intent intent = new Intent(EventActivity.this, ManaTestMainAct.class);
                                    intent.putExtra("Diff", mini_game_difficulty);
                                    startActivityForResult(intent, 0);
                                    double score = intent.getDoubleExtra("Result",0.0);
                                    String event = "You started the test";
                                    mAdapter.addEvent(event);
                                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                                    tv1.setText(String.valueOf(mPlayer.getPlayerAttribute().getPressure()));
                                    // Wealth
                                    tv2.setText(String.valueOf(mPlayer.getPlayerAttribute().getMoney()));
                                    // GPA
                                    tv3.setText(String.valueOf(mPlayer.getPlayerAttribute().getGPA()));
                                }
                                else if (game_type.equals("")){
                                    String event = mNewEvent.generateNewEvent(mPlayer,true);
                                    /*if (event == "MANA"){
                                        Intent intent = new Intent(EventActivity.this, ManaTestMainAct.class);
                                        intent.putExtra("Diff", 2);
                                        startActivityForResult(intent, 0);
                                        double score = intent.getDoubleExtra("Result",0.0);
                                        //event = "You started the MANA test";
                                       // mAdapter.addEvent(event);
                                        //mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                                    }
                                    if(event == "ATRO"){
                                        Intent intent = new Intent(EventActivity.this, CardGameActivity.class);
                                        intent.putExtra("Diff", 2);
                                        startActivityForResult(intent, 0);
                                        double score = intent.getDoubleExtra("Result",0.0);
                                        event = "You started the HIST test";
                                        mAdapter.addEvent(event);
                                        mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                                    }*/
                                    mAdapter.addEvent(event);
                                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                                    tv1.setText(String.valueOf(mPlayer.getPlayerAttribute().getPressure()));
                                    // Wealth
                                    tv2.setText(String.valueOf(mPlayer.getPlayerAttribute().getMoney()));
                                    // GPA
                                    tv3.setText(String.valueOf(mPlayer.getPlayerAttribute().getGPA()));
                                }


                            }
                        })
                        .setNegativeButton(event_choice2, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                /*String event = mNewEvent.generateNewEvent(mPlayer,false);
                                mAdapter.addEvent(event);
                                mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);*/
                                String game_type = mNewEvent.getTestClassName(false);
                                if(game_type.equals("HIST")){
                                    called_by_event = true;
                                    Intent intent = new Intent(EventActivity.this, CardGameActivity.class);
                                    intent.putExtra("Diff", mini_game_difficulty);
                                    startActivityForResult(intent, 0);
                                    double score = intent.getDoubleExtra("Result",0.0);
                                    String event = "You started the test";
                                    mAdapter.addEvent(event);
                                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);

                                }
                                else if(game_type.equals("MANA")){
                                    called_by_event = true;
                                    Intent intent = new Intent(EventActivity.this, ManaTestMainAct.class);
                                    intent.putExtra("Diff", mini_game_difficulty);
                                    startActivityForResult(intent, 0);
                                    double score = intent.getDoubleExtra("Result",0.0);
                                    String event = "You started the test";
                                    mAdapter.addEvent(event);
                                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                                }
                                else if (game_type.equals("")){
                                    String event = mNewEvent.generateNewEvent(mPlayer,false);
                                    /*if (event == "MANA"){
                                        Intent intent = new Intent(EventActivity.this, ManaTestMainAct.class);
                                        intent.putExtra("Diff", 2);
                                        startActivityForResult(intent, 0);
                                        double score = intent.getDoubleExtra("Result",0.0);
                                        event = "You started the MANA test";
                                        mAdapter.addEvent(event);
                                        mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                                    }
                                    if(event == "ATRO"){
                                        Intent intent = new Intent(EventActivity.this, CardGameActivity.class);
                                        intent.putExtra("Diff", 2);
                                        startActivityForResult(intent, 0);
                                        double score = intent.getDoubleExtra("Result",0.0);
                                        event = "You started the HIST test";
                                        mAdapter.addEvent(event);
                                        mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                                    }*/
                                    mAdapter.addEvent(event);
                                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                                    tv1.setText(String.valueOf(mPlayer.getPlayerAttribute().getPressure()));
                                    // Wealth
                                    tv2.setText(String.valueOf(mPlayer.getPlayerAttribute().getMoney()));
                                    // GPA
                                    tv3.setText(String.valueOf(mPlayer.getPlayerAttribute().getGPA()));
                                }
                            }
                        })
                        .show();


                //Alert Dialog Theme
                int stringSize = event_description.length() + event_choice1.length() + event_choice2.length();
                int buttonSize = event_choice1.length() + event_choice2.length();
                int alertHeight = 650;
                if(stringSize < 200){
                    alertHeight = 500;
                }

                if(buttonSize > 45){
                    alertHeight = 650;
                }

                if(stringSize > 290){
                    alertHeight = 750;
                }

                if(event_choice1.equals("Good luck for me") || event_choice2.equals("Good luck for me")){
                    alertHeight = 400;
                }

                Drawable drawable = getResources().getDrawable(R.drawable.panel);
                alertDialog.getWindow().setBackgroundDrawable(drawable);
                alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, alertHeight);



                //alertDialog.getWindow().setLayout(800, 400);

                //END Alert Dialog Theme
                //set player attribute after every newly generated event
                // Pressure
                tv1.setText(String.valueOf(mPlayer.getPlayerAttribute().getPressure()));
                // Wealth
                tv2.setText(String.valueOf(mPlayer.getPlayerAttribute().getMoney()));
                // GPA
                tv3.setText(String.valueOf(mPlayer.getPlayerAttribute().getGPA()));


            }
        });
        Button mSummaryButton = findViewById(R.id.SummaryButton);
        mSummaryButton.setOnClickListener((v) -> {
            //delete previous player attribute and re-insert current player attribute
            courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course1Code,course1mark);
            courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course2Code,course2mark);
            courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course3Code,course3mark);
            courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course4Code,course4mark);

            //Remove the effect of current course load on player pressure (since the courses are over now)
            int diff1 = myMap.get(curPlayer.course1Code);
            int diff2 = myMap.get(curPlayer.course2Code);
            int diff3 = myMap.get(curPlayer.course3Code);
            int diff4 = myMap.get(curPlayer.course4Code);

            curPlayer.pressure = curPlayer.pressure - diff1/10 - diff2/10 - diff3/10 - diff4/10;

            PlayerAttribute playerAttribute = mPlayer.getPlayerAttribute();
            playerAttribute.setIQ(playerAttributeDAO.getIQ1().get(0));
            playerAttribute.setLuck(playerAttributeDAO.getLuck1().get(0));
            playerAttribute.setHealth(playerAttributeDAO.getHealth1().get(0));
            playerAttribute.setWealth(playerAttributeDAO.getWealth1().get(0));

            mPlayer.setPlayerAttribute(playerAttribute);


            playerAttributeDAO.deleteAll();
            playerAttributeDAO.insertAll(mPlayer.getPlayerAttribute());
            //update grade

            Intent intent = new Intent(EventActivity.this, Summarypage.class);
            startActivity(intent);
        });
        Button mQuitButton = findViewById(R.id.QuitButton);
        mQuitButton.setOnClickListener((v) -> {
            courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course1Code,course1mark);
            courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course2Code,course2mark);
            courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course3Code,course3mark);
            courseSelectionRecordDAO.updateGradeByCourseCode(curPlayer.course4Code,course4mark);


            PlayerAttribute playerAttribute = mPlayer.getPlayerAttribute();
            playerAttribute.setIQ(playerAttributeDAO.getIQ1().get(0));
            playerAttribute.setLuck(playerAttributeDAO.getLuck1().get(0));
            playerAttribute.setHealth(playerAttributeDAO.getHealth1().get(0));
            playerAttribute.setWealth(playerAttributeDAO.getWealth1().get(0));

            mPlayer.setPlayerAttribute(playerAttribute);


            playerAttributeDAO.deleteAll();
            playerAttributeDAO.insertAll(mPlayer.getPlayerAttribute());
            /*Intent intent = new Intent(EventActivity.this, MainActivity.class);
            startActivity(intent);*/
            finish();
        });
        Button mProfileButton = findViewById(R.id.ProfileButton);
        mProfileButton.setOnClickListener((v) -> {
            onButtonShowPopupWindowClick(v, mPlayer.getPlayerAttribute());
        });
        Button mGameButton = findViewById(R.id.SettingButton);
        mGameButton.setOnClickListener((v) -> {
            AlertDialog alertDialog = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                    .setMessage("Which game would you like to play?")
                    .setCancelable(true)
                    .setTitle("Mini Games")
                    .setPositiveButton("MANA", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Send the TryAgain button event back to the host fragment
                            Intent intent = new Intent(EventActivity.this, ManaTestMainAct.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("ARTO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(EventActivity.this, CardGameActivity.class);
                            startActivity(intent);
                        }
                    })
                    .show();
            Drawable drawable = getResources().getDrawable(R.drawable.panel);
            alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 450);
            alertDialog.getWindow().setBackgroundDrawable(drawable);
        });


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

    }

    private void addEvent2(String event) {
        String item = "Pig";
        int insertIndex = mAdapter.getItemCount();
        mEventset.add(event);
        mAdapter.notifyItemInserted(mAdapter.getItemCount()-1);
    }

    //generate course mark
    public void MarkAutoGeneration() {
        if (!course1type.equals("MANA")) {
            course1mark =  (int) ((Math.random() * (100 - MANA_mark)) + MANA_mark);
        }
        if (!course2type.equals("MANA")) {
            course2mark =  (int) ((Math.random() * (100 - MANA_mark)) + MANA_mark);
        }
        if (!course3type.equals("MANA")) {
            course3mark =  (int) ((Math.random() * (100 - MANA_mark)) + MANA_mark);
        }
        if (!course4type.equals("MANA")){
            course4mark =  (int) ((Math.random() * (100 - MANA_mark)) + MANA_mark);
        }
        if (!course1type.equals("ATRO")) {
            course1mark =  (int) ((Math.random() * (100 - ATRO_mark)) + ATRO_mark);
            return;
        }
        if (!course2type.equals("ATRO")) {
            course2mark =  (int) ((Math.random() * (100 - ATRO_mark)) + ATRO_mark);
            return;
        }
        if (!course3type.equals("ATRO")) {
            course3mark =  (int) ((Math.random() * (100 - ATRO_mark)) + ATRO_mark);
            return;
        }
        if (!course4type.equals("ATRO")){
            course4mark =  (int) ((Math.random() * (100 - ATRO_mark)) + ATRO_mark);
            return;
        }
    }
    public void onButtonShowPopupWindowClick(View view, PlayerAttribute p) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.profile_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView ProfileText = (TextView)popupView.findViewById(R.id.ProfileText); // Pressure
        if(p != null){
            Log.d(TAG,"print profile: "+ toProfileString(p));
            ProfileText.setText(toProfileString(p));
        }

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public String toProfileString(PlayerAttribute p) {
        return "Term = " + p.numTerm + '\n' +
                "IQ = " + p.IQ + '\n' +
                "luck = " + p.luck + '\n' +
                "wealth = " + p.money + '\n' +
                "health = " + p.health + '\n' +
                "pressure = " + p.pressure + '\n' +
                "GPA = " + p.GPA + '\n' +
                "MANA skill = " + p.ManaSkill + '\n' +
                "SPELL skill = " + p.SpelSkill + '\n' +
                "HERBOLOGY skill = " + p.getHerbSkill() + '\n' +
                "HISTORY skill = " + p.getHistSkill() + '\n' +
                "POTION skill = " + p.MediSkill + '\n' +
                "ASTROLOGY skill = " + p.getAtroSkill();
    }

    public void useSpellCard(View view){
        Intent intent = new Intent(EventActivity.this, SwipeSpellCardActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setMessage(R.string.proceed_to_summary)
                .setCancelable(true)
                .setTitle("Quit Game?")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Continue game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { }
                })
                .show();
        Drawable drawable = getResources().getDrawable(R.drawable.panel);
        alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        alertDialog.getWindow().setBackgroundDrawable(drawable);
    }

}

