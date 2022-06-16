package com.example.uw_life_simulator.model;

/**
 * NewEvent generator which generates events based on the eventId
 **/
public class GameEventGenerator {
    public static GameEvent generateEvent(int eventId)
    {
        switch (eventId)
        {
            case 0: return new GameEventNull();
            case 1: return new GameEventMoney();
            case 2: return new GameEventMoney2();
            case 3: return new GameEventGoose();
            case 4: return new GameEventGoose2();

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
     * NewEvent's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(UserAttribute attribute) {
        //doing nothing
    }
}

/**
 * EventID: 1
 * Effect: Wealth+10
 */
class GameEventMoney extends GameEvent{
    public GameEventMoney(){
        this.Id = 1;
        this.description = "There are 10 dollars on the ground!\n";
    }

    /**
     * Event's effect on the player(Money + 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(UserAttribute attribute) {
        attribute.setWealth(attribute.getWealth() + 10);
    }
}

/**
 * EventID: 2
 * Effect: Wealth+10
 */
class GameEventMoney2 extends GameEvent{
    public GameEventMoney2(){
        this.Id = 2;
        this.description = "You lost 10 dollars!\n";
    }

    /**
     * Event's effect on the player(Money - 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(UserAttribute attribute) {
        attribute.setWealth(attribute.getWealth() - 10);
    }
}

/**
 * EventID: 3
 * Effect: Pressure+10
 */
class GameEventGoose extends GameEvent{
    public GameEventGoose(){
        this.Id = 3;
        this.description = "You step on goose shit!\n";
    }

    /**
     * Event's effect on the player(Pressure + 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(UserAttribute attribute) {
        attribute.setWealth(attribute.getPressure() + 10);
    }
}

/**
 * EventID: 4
 * Effect: Pressure+100
 */
class GameEventGoose2 extends GameEvent{
    public GameEventGoose2(){
        this.Id = 4;
        this.description = "Goose shit step on you!\n";
    }

    /**
     * Event's effect on the player(Pressure + 100)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(UserAttribute attribute) {
        attribute.setWealth(attribute.getPressure() + 100);
    }
}
