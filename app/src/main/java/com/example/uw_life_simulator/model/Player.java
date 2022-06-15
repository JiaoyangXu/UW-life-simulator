package com.example.uw_life_simulator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player { // Implementation needed
    private String name = "";
    private int programId = 0;
    private int numTerm = 2;
    private int numFailedCourses = 0;
    private boolean employed = false;
    private int resumeScore = 0;
    private int workTermScore = 0;
    private BuffManager buffManager = new BuffManager(this);
    private CourseManager courseManager = new CourseManager(this);
    //Math, CS, Econ, Languages, Science, Arts
    private List<Integer> sixMajorAbilities = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0));
    private UserAttribute userAttribute = new UserAttribute();


    //Getter & Setters
    public String getName(){ return name;}
    public void setName(String n){ name = n;}
    public int getProgramId(){ return programId;}
    public void setProgramId(int n){ programId = n;}
    public int getNumTerm(){ return numTerm;}
    public void setNumTerm(int n){ numTerm = n;}
    public int getNumFailedCourses(){ return numFailedCourses;}
    public void setNumFailedCourses(int n){ numFailedCourses = n;}
    public boolean getEmployed(){ return employed; }
    public void setEmployed(boolean n){ employed = n; }
    public int getResumeScore(){ return resumeScore;}
    public void setResumeScore(int n){ resumeScore = n;}
    public int getWorkTermScore(){ return workTermScore;}
    public void setWorkTermScore(int n){ workTermScore = n;}
    public BuffManager getTalentManager(){ return buffManager; }
    public void setSixMajorAbilities(int index, int val){ sixMajorAbilities.set(index, val); }
    public List<Integer> getSixMajorAbilities(){return sixMajorAbilities; }
    public UserAttribute getUserAttribute(){ return userAttribute; }

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
