package com.example.uw_life_simulator.model;

import com.example.uw_life_simulator.data.PlayerAttribute;


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
            case 2: return new GameEventMoneyLose();
            case 3: return new GameEventGoose();
            case 4: return new GameEventGoose2();

            default: return null;
        }
    }

    public static GameChoiceEvent generateChoiceEvent(int eventId)
    {
        switch (eventId)
        {
            case 0: return new GameChoiceNull();
            case 1: return new GameChoiceMoney();
            case 2: return new GameChoiceGoose();
            default: return null;
        }
    }
}

/**
 * NULL events which does nothing
 **/
class GameChoiceNull extends GameChoiceEvent{
    @Override
    public GameEvent generateEvent(boolean playerResponse) {
        this.description = "Nothing happened!\n";
        this.Id = 0;
        return new GameEventNull();
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, double check_res) {
        this.description = "Nothing happened!\n";
        this.Id = 0;
        return new GameEventNull();
    }
}

/**
 * EventID: 1
 **/
class GameChoiceMoney extends GameChoiceEvent{
    @Override
    public GameEvent generateEvent(boolean playerResponse) {
        this.description = "There are 10 dollars on the ground! Do you want to pick it up?\n";
        this.Id = 1;
        if (playerResponse)
        {
            return new GameEventMoney();
        }
        else
        {
            return new GameEventMoneyNo();
        }
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, double check_res) {
        this.description = "There are 10 dollars on the ground! Do you want to pick it up?\n";
        this.Id = 1;
        if (playerResponse)
        {
            return new GameEventMoney();
        }
        else
        {
            return new GameEventMoneyNo();
        }
    }
}

/**
 * EventID: 2
 **/
class GameChoiceGoose extends GameChoiceEvent{
    @Override
    public GameEvent generateEvent(boolean playerResponse) {
        this.description = "There are goose on your way, do you want to go away or attack them?\n";
        this.Id = 2;
        if (playerResponse)
        {
            return new GameEventGoose();
        }
        else
        {
            return new GameEventGoose2();
        }
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, double check_res) {
        this.description = "There are goose on your way, do you want to go away or attack them?\n";
        this.Id = 2;
        if (playerResponse)
        {
            return new GameEventGoose();
        }
        else
        {
            return new GameEventGoose2();
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
    public void visit(PlayerAttribute attribute) {
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
        this.description = "You got 10 dollars!\n";
    }

    /**
     * Event's effect on the player(Money + 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.wealth += 10.0;
    }
}

/**
 * EventID: 2
 * Effect: pressure-1
 */
class GameEventMoneyNo extends GameEvent{
    public GameEventMoneyNo(){
        this.Id = 2;
        this.description = "You feel you have done the right thing\n";
    }

    /**
     * Event's effect on the player(pressure-1)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure -= 1.0;
    }
}

/**
 * EventID: 3
 * Effect: Wealth-10
 */
class GameEventMoneyLose extends GameEvent{
    public GameEventMoneyLose(){
        this.Id = 3;
        this.description = "You lost 10 dollars!\n";
    }

    /**
     * Event's effect on the player(Money - 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.wealth -= 10.0;
    }
}

/**
 * EventID: 4
 * Effect: Pressure+10
 */
class GameEventGoose extends GameEvent{
    public GameEventGoose(){
        this.Id = 4;
        this.description = "Goose attacked you!\n";
    }

    /**
     * Event's effect on the player(Pressure + 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure += 10.0;
    }
}

/**
 * EventID: 5
 * Effect: Pressure-10
 */
class GameEventGoose2 extends GameEvent{
    public GameEventGoose2(){
        this.Id = 5;
        this.description = "You attacked goose!\n";
    }

    /**
     * Event's effect on the player(Pressure - 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure -= 10.0;
    }
}
