package com.example.uw_life_simulator;

/**
 * General events structure
 **/
abstract class GameEvent implements Visitor {
    protected String description = "";
    protected int Id = 0;

    // Getters & Setters
    public int getId() {
        return Id;
    }
    public String getDescription() {
        return description;
    }
}

