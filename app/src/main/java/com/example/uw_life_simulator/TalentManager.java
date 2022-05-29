package com.example.uw_life_simulator;

import java.util.ArrayList;
import java.util.List;

public class TalentManager {
    private Player owner;
    private List<Integer> talents = new ArrayList<Integer>();


    // Getter & Setters
    public List<Integer> getTalent(){ return talents; }
    public void setTalent(List<Integer> n){ talents = n; }
    public void insertTalent(Integer talentId){ talents.add(talentId); }


    //Constructors
    public TalentManager(Player owner)
    {
        this.owner = owner;
    }


    //public functions
    /*Trigger the talent effect of all talents*/
    public void triggerAllTalents() {
        for(int id : talents) {
            Factory.generateTalent(id).talentEffect(owner);
        }
    }

    /*Trigger the talent effect of the talent if it exists*/
    public void triggerTalent(int talentId){
        if(talents.contains(talentId))
        {
            Factory.generateTalent(talentId).talentEffect(owner);
        }
    }

}
