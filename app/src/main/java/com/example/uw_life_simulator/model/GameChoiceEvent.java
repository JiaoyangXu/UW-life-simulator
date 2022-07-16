package com.example.uw_life_simulator.model;

import com.example.uw_life_simulator.data.PlayerAttribute;

abstract public class GameChoiceEvent {
    protected String description = "";
    protected int Id = 0;
    public String choice1_description = "";
    public String choice2_description = "";
    public String choice1_check = "";
    public String choice2_check = "";
    protected int repeatCount = 0;

    double lastMark = -1;

    // Getters & Setters
    public int getId() {
        return Id;
    }
    public String getDescription() {
        return description;
    }
    public abstract GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute);

    /**
     * Set the mark for previous test
     *
     * Input: double : mark for last test
     * Output: void
     **/
    public void setLastMark(double mark)
    {
        lastMark = mark;
        System.out.println(lastMark);/////////////////////////////
    }


    /**
     * Check if the event will repeat for more times
     *
     * Input: None
     * Output: boolean: will event continue repeat?
     **/
    public boolean repeated(){return repeatCount > 0;}
}
