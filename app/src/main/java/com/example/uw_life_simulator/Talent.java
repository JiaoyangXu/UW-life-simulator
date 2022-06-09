package com.example.uw_life_simulator;

/**
 * general structure of Talents
 **/
public abstract class Talent implements Visitor {
    String description = "";
    int Id = 0;


    // Getter & Setters
    public String getDescription(){ return description; }
    public int getId(){return Id;}
}
