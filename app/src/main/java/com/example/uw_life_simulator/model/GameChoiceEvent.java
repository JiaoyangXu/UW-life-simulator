package com.example.uw_life_simulator.model;

abstract public class GameChoiceEvent {
    protected String description = "";
    protected int Id = 0;

    // Getters & Setters
    public int getId() {
        return Id;
    }
    public String getDescription() {
        return description;
    }
    public abstract GameEvent generateEvent(boolean playerResponse);
}
