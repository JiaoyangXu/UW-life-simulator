package com.example.uw_life_simulator.model;

import java.util.ArrayList;
import java.util.List;

public class BuffManager {
    private Player owner;
    private List<Integer> buffs = new ArrayList<Integer>();



    // Getter & Setters
    public List<Integer> getBuffs(){ return buffs; }
    public void setTalent(List<Integer> n){ buffs = n; }
    public void insertTalent(Integer talentId){ buffs.add(talentId); }


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
        for(int id : buffs) {
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
        if(buffs.contains(talentId))
        {
            Factory.generateBuff(talentId).visit(owner.getUserAttribute());
        }
    }

}
