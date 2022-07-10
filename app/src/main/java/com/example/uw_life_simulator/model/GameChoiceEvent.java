package com.example.uw_life_simulator.model;

import com.example.uw_life_simulator.data.PlayerAttribute;

abstract public class GameChoiceEvent {
    protected String description = "";
    protected int Id = 0;
    protected String choice1_check = "";
    protected String choice2_check = "";
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
    }
}
