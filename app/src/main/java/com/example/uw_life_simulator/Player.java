package com.example.uw_life_simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player { // Implementation needed
    private String name = "";
    private int money = 0;
    private float gpa = 0;
    private float pressure = 0;
    private int iq = 0;
    private int energy = 0;
    private int luck = 0;
    private int programId = 0;
    private int numTerm = 2;
    private int numFailedCourses = 0;
    private boolean employeed = false;
    private int resumeScore = 0;
    private int workTermScore = 0;
    private BuffManager buffManager = new BuffManager(this);
    private CourseManager courseManager = new CourseManager(this);
    //Math, CS, Econ, Languages, Science, Arts
    private List<Integer> sixMajorAbilities = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0));


    //Getter & Setters
    public String getName(){ return name;}
    public void setName(String n){ name = n;}
    public int getMoney(){ return money;}
    public void setMoney(int n){ money = n;}
    public float getGpa(){ return gpa; }
    public void setGpa(float n){ gpa = n; }
    public float getPressure(){ return pressure; }
    public void setPressure(float n){ pressure = n; }
    public int getIq(){ return iq;}
    public void setIq(int n){ iq = n;}
    public int getEnergy(){ return energy;}
    public void setEnergy(int n){ energy = n;}
    public int getLuck(){ return luck;}
    public void setLuck(int n){ luck = n;}
    public int getProgramId(){ return programId;}
    public void setProgramId(int n){ programId = n;}
    public int getNumTerm(){ return numTerm;}
    public void setNumTerm(int n){ numTerm = n;}
    public int getNumFailedCourses(){ return numFailedCourses;}
    public void setNumFailedCourses(int n){ numFailedCourses = n;}
    public boolean getEmployeed(){ return employeed; }
    public void setEmployeed(boolean n){ employeed = n; }
    public int getResumeScore(){ return resumeScore;}
    public void setResumeScore(int n){ resumeScore = n;}
    public int getWorkTermScore(){ return workTermScore;}
    public void setWorkTermScore(int n){ workTermScore = n;}
    public BuffManager getTalentManager(){ return buffManager; }
    public void setSixMajorAbilities(int index, int val){ sixMajorAbilities.set(index, val); }
    public List<Integer> getSixMajorAbilities(){return sixMajorAbilities; }

    /**
     * Accept GameEvents' impact
     *
     * Input: GameEvent : gameEvent
     * Output: void
     **/
    public void accept(GameEvent gameEvent)
    {
        gameEvent.visit(this);
    }

    /**
     * Accept talents' impact
     *
     * Input: Talent : talent
     * Output: void
     **/
    public void accept(Buff buff){ buff.visit(this); }

}
