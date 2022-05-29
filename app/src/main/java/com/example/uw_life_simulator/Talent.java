package com.example.uw_life_simulator;

public abstract class Talent {
    String description;
    int Id;


    // Getter & Setters
    public String getDescription(){ return description; }
    public int getId(){return Id;}


    //Constructor
    public Talent(String description, int Id){
        this.description = description;
        this.Id = Id;
    }


    // public functions
    /*Talent's effect on the player*/
    public abstract void talentEffect(Player owner); // Implementation needed
}
