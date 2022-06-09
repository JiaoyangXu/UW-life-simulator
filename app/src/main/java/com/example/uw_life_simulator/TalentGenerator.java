package com.example.uw_life_simulator;

public class TalentGenerator {
    public static Talent generateTalent(int talentId)
    {
        switch (talentId)
        {
            case 0: return new TalentNull();

            default: return null;
        }
    }
};

/**
 * NULL talent which does nothing
 **/
class TalentNull extends Talent {
    //Constructor
    public TalentNull(){
        this.description = "This is a null Talent\n";
        this.Id = 0;
    }

    /**
     * Talent's effect on the player
     *
     * Input: Player : player
     * Output: void
     **/
    @Override
    public void visit(Player player) {
        //doing nothing
    }
};


