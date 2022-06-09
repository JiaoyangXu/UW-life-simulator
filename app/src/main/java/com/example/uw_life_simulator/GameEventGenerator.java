package com.example.uw_life_simulator;

/**
 * Event generator which generates events based on the eventId
 **/
public class GameEventGenerator {
    public static GameEvent generateEvent(int eventId)
    {
        switch (eventId)
        {
            case 0: return new GameEventNull();

            default: return null;
        }
    }
}

/**
 * NULL events which does nothing
**/
class GameEventNull extends GameEvent{
    public GameEventNull(){
        this.Id = 0;
        this.description = "This is a null event\n";
    }

    /**
     * Event's effect on the player
     *
     * Input: Player : player
     * Output: void
     **/
    @Override
    public void visit(Player player) {
        //doing nothing
    }
}
