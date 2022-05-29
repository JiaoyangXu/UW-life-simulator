package com.example.uw_life_simulator;

public abstract class GameEvent {
    protected String description = "";
    protected int Id = 0;


    // Getter & Setters
    public String getDescription(){ return description; }
    public int getId(){ return Id; }
    public abstract void visit(Player player);

}

