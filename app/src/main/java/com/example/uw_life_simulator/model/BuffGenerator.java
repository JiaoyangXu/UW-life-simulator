package com.example.uw_life_simulator.model;

public class BuffGenerator {
    public static Buff generateBuff(int buffId)
    {
        switch (buffId)
        {
            case 0: return new BuffNull();

            default: return null;
        }
    }
};

/**
 * NULL talent which does nothing
 **/
class BuffNull extends Buff {
    //Constructor
    public BuffNull(){
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
    public void visit(Subject subject) {
        //doing nothing
    }
};


