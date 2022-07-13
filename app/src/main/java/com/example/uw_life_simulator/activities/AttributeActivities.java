package com.example.uw_life_simulator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.uw_life_simulator.DAO.CourseDao;
import com.example.uw_life_simulator.DAO.PlayerAttributeDAO;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.Database.PlayerAttributeDatabase;
import com.example.uw_life_simulator.R;
import com.example.uw_life_simulator.data.Course;
import com.example.uw_life_simulator.data.CourseSelectionRecord;
import com.example.uw_life_simulator.data.PlayerAttribute;
import com.example.uw_life_simulator.model.MainActivity;
import com.example.uw_life_simulator.model.Player;
import com.example.uw_life_simulator.model.UserAttribute;

import java.util.List;
import java.util.UUID;

public class AttributeActivities extends AppCompatActivity {
    UserAttribute talent;
    PlayerAttribute playerAttribute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_talent_selection);

        talent = new UserAttribute(10);
        playerAttribute = new PlayerAttribute(0,0,0,0);

    }

    /** Button Listener that save player's attribute to database
     *  jump to event activity when the button being hit
     *
     */
    public void confirmTalentSelection (View view) {
        playerAttribute.updateAttribute();

        PlayerAttributeDatabase db = Room.databaseBuilder(getApplicationContext(),
                PlayerAttributeDatabase.class, "PlayerAttributes").
                allowMainThreadQueries().
                fallbackToDestructiveMigration().build();
        PlayerAttributeDAO playerAttributeDAO = db.playerAttributeDAO();
        playerAttributeDAO.insertAll(playerAttribute);


        // pass intent to Event Activity
        Intent intent = new Intent(AttributeActivities.this, DrawSpellCardActivity.class);
        startActivity(intent);
    }

    /** update current points we have, parameter
     increaseOrDecrease = 0 when increase point,
     increaseOrDecrease = 1 when decrease point
     this function does not check for the validity of input, it assumes
     the button click is valid
     **/
    private void updatePoint(int increaseOrDecrease, TextView textView1, TextView textView2) {
        int availablePoint = Integer.parseInt(textView1.getText().toString());
        int currentPoint = Integer.parseInt(textView2.getText().toString());

        // if the user hits minus button
        if(increaseOrDecrease == 0) {
            textView1.setText(String.valueOf(++availablePoint));
            textView2.setText(String.valueOf(--currentPoint));
        } else {    // if the user his plus button
            textView1.setText(String.valueOf(--availablePoint));
            textView2.setText(String.valueOf(++currentPoint));
        }
    }
    /**
     increase available point by 1 (when the minus button is hit)
     this function checks for the validity of input
     **/
    private boolean hitMinusButton(TextView totalAvailablePoint, TextView totalCurrentPoint) {
        int availablePoint = Integer.parseInt(totalAvailablePoint.getText().toString());
        int currentPoint = Integer.parseInt(totalCurrentPoint.getText().toString());
        // check for invalid operation : when available point exceeds limit or
        // current point is zero
        if (availablePoint >= talent.getTotalPoint() || currentPoint < 1) {
            return false;
        }
        updatePoint(0,totalAvailablePoint,totalCurrentPoint);
        return true;
    }


    // decrease available point by 1 (when the plus button is hit)
    // this function checks for the validity of input
    private boolean hitAddButton(TextView totalAvailablePoint, TextView totalCurrentPoint) {
        int availablePoint = Integer.parseInt(totalAvailablePoint.getText().toString());
        int currentPoint = Integer.parseInt(totalCurrentPoint.getText().toString());
        // check for invalid operation : when available point exceeds limit or
        // current point is zero
        if (availablePoint < 1) {
            return false;
        }
        updatePoint(1,totalAvailablePoint,totalCurrentPoint);
        return true;
    }

    // Increment IQ if the addIQ button is hit
    public void addIQ(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentIQPoint = findViewById(R.id.talentIQAmount);
        if (hitAddButton(totalAvailablePoint, currentIQPoint) == true){
            playerAttribute.IQ ++;
        }
    }

    // decrease IQ if the minusIQ button is hit
    public void minusIQ(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentIQPoint = findViewById(R.id.talentIQAmount);
        if(hitMinusButton(totalAvailablePoint, currentIQPoint)) {
            playerAttribute.IQ --;
        }
    }

    // decrease Luck if the minusLuck button is hit
    public void minusLuck(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for Luck
        TextView currentLuckPoint = findViewById(R.id.talentLuckAmount);
        if(hitMinusButton(totalAvailablePoint, currentLuckPoint)) {
            playerAttribute.luck--;
        }
    }

    // Increment Luck if the addLuck button is hit
    public void addLuck(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentLuckPoint = findViewById(R.id.talentLuckAmount);
        if(hitAddButton(totalAvailablePoint, currentLuckPoint)) {
            playerAttribute.luck++;
        }
    }

    // Increment Wealth if the addWealth button is hit
    public void addWealth(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentWealthPoint = findViewById(R.id.talentWealthAmount);
        if(hitAddButton(totalAvailablePoint, currentWealthPoint)) {
            playerAttribute.wealth++;
        }
    }

    // decrease wealth if the minusWealth button is hit
    public void minusWealth(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for Luck
        TextView currentWealthPoint = findViewById(R.id.talentWealthAmount);
        if(hitMinusButton(totalAvailablePoint, currentWealthPoint)) {
            playerAttribute.wealth--;
        }
    }

    // Increment Health if the addHealth button is hit
    public void addHealth(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for IQ
        TextView currentHealthPoint = findViewById(R.id.talentHealthAmount);
        if(hitAddButton(totalAvailablePoint, currentHealthPoint)) {
            playerAttribute.health++;
        }
    }

    // decrease wealth if the minusWealth button is hit
    public void minusHealth(View view) {
        // get available point
        TextView totalAvailablePoint = findViewById(R.id.availableTalentAmount);
        // get current point for Luck
        TextView currentHealthPoint = findViewById(R.id.talentHealthAmount);
        if(hitMinusButton(totalAvailablePoint, currentHealthPoint)) {
            playerAttribute.health--;
        }
    }
}
