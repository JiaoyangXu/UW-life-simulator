package com.example.uw_life_simulator.model;

import java.util.ArrayList;
import java.util.List;

public class BuffManager {
    private Player owner;
    private List<Integer> talents = new ArrayList<Integer>();


    // Getter & Setters
    public List<Integer> getTalent(){ return talents; }
    public void setTalent(List<Integer> n){ talents = n; }
    public void insertTalent(Integer talentId){ talents.add(talentId); }


    //Constructors
    public BuffManager(Player owner)
    {
        this.owner = owner;
    }


    //public functions
    /**
     * Trigger the talent effect of all talents
     *
     * Input: void
     * Output: void
     **/
    public void triggerAllTalents() {
        for(int id : talents) {
            Factory.generateBuff(id).visit(owner.getUserAttribute());
        }
    }

    /**
     * Trigger the talent effect of the talent if it exists
     *
     * Input: int : talentId
     * Output: void
     **/
    public void triggerTalent(int talentId){
        if(talents.contains(talentId))
        {
            Factory.generateBuff(talentId).visit(owner.getUserAttribute());
        }
    }

}
