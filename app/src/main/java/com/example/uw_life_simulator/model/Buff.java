package com.example.uw_life_simulator.model;

/**
 * general structure of Talents
 **/
public abstract class Buff implements Visitor {
    String description = "";
    int Id = 0;
    int count = 0;

    // Getter & Setters
    public String getDescription(){ return description; }
    public int getId(){return Id;}

    /**
     * Decrease time counter until 0 to indicate left turns for the buff
     *
     * Input: void
     * Output: void
     **/
    void timePass()
    {
        if (count > 0)
        {
            count --;
        }
    }
}
