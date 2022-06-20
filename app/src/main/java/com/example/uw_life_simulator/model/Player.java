package com.example.uw_life_simulator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player { // Implementation needed
    private String name = "";
    private int programId = 0;
    private int numTerm = 2;

    private BuffManager buffManager = new BuffManager(this);
    private CourseManager courseManager = new CourseManager(this);
<<<<<<< HEAD
=======
    //Math, CS, Econ, Languages, Science, Arts
    private List<Integer> sixMajorAbilities = new ArrayList<
            Integer>(Arrays.asList(0,0,0,0,0,0));
>>>>>>> 8d60a2696d5f4a7bb2484d05df2bc48de8175f1c
    private UserAttribute userAttribute = new UserAttribute();
    private List<String> Classtaken = new ArrayList<>();

    //Getter & Setters
    public String getName(){ return name;}
    public void setName(String n){ name = n;}
    public int getProgramId(){ return programId;}
    public void setProgramId(int n){ programId = n;}
    public int getNumTerm(){ return numTerm;}
    public void setNumTerm(int n){ numTerm = n;}
    public BuffManager getTalentManager(){ return buffManager; }
    public UserAttribute getUserAttribute(){ return userAttribute; }
    public List<String> getClasstaken(){return Classtaken; }
    public CourseManager getCourseManager(){return courseManager; }
    /**
     * Accept GameEvents' impact
     *
     * Input: GameEvent : gameEvent
     * Output: void
     **/
    public void accept(GameEvent gameEvent)
    {
        gameEvent.visit(userAttribute);
    }

    /**
     * Accept talents' impact
     *
     * Input: Talent : talent
     * Output: void
     **/
    public void accept(Buff buff){ buff.visit(userAttribute); }

}
