package com.example.uw_life_simulator.model;

import com.example.uw_life_simulator.data.PlayerAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player { // Implementation needed
    private String name = "";
    private int programId = 0;
    private int numTerm = 2;

    private BuffManager buffManager = new BuffManager(this);
    //private CourseManager courseManager = new CourseManager(this);
    private UserAttribute userAttribute = new UserAttribute();
    private PlayerAttribute playerAttribute = new PlayerAttribute(0,0,0,0);

    //Getter & Setters
    public String getName(){ return name;}
    public void setName(String n){ name = n;}
    public int getProgramId(){ return programId;}
    public void setProgramId(int n){ programId = n;}
    public int getNumTerm(){ return numTerm;}
    public void setNumTerm(int n){ numTerm = n;}
    public BuffManager getTalentManager(){ return buffManager; }
    public UserAttribute getUserAttribute(){ return userAttribute; }
    public PlayerAttribute getPlayerAttribute(){ return playerAttribute; }
    //public CourseManager getCourseManager(){return courseManager; }
    /**
     * Accept GameEvents' impact
     *
     * Input: GameEvent : gameEvent
     * Output: void
     **/
    public void accept(GameEvent gameEvent)
    {
        gameEvent.visit(playerAttribute);
    }

    /**
     * Accept talents' impact
     *
     * Input: Talent : talent
     * Output: void
     **/
    public void accept(Buff buff){ buff.visit(playerAttribute); }

}
