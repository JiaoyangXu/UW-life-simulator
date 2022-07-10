package com.example.uw_life_simulator.model;

import com.example.uw_life_simulator.data.PlayerAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
            case 3: return new GameChoiceInspire();
            case 4: return new GameChoiceHotDay();
            case 5: return new GameChoiceTest();
            case 6: return new GameChoiceLostCityBegin();

            case 1600: return new GameChoiceLostCity01();
            case 1601: return new GameChoiceLostCity02();
            default: return null;
        }
    }
}

/**
 * NULL events which does nothing
 **/
class GameChoiceNull extends GameChoiceEvent{
    GameChoiceNull()
    {
        this.description = "Today is dull and nothing happened\n";
        this.Id = 0;
    }
    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        return new GameEventNull();
    }
}

/**
 * EventID: 1
 **/
class GameChoiceMoney extends GameChoiceEvent{
    GameChoiceMoney()
    {
        this.description = "There are 10 dollars on the ground! Do you want to pick it up?\n";
        this.Id = 1;
    }
    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
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
    GameChoiceGoose()
    {
        this.description = "There are goose on your way, do you want to go away or attack them?\n";
        this.Id = 2;
    }
    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
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
 * EventID: 3
 **/
class GameChoiceInspire extends GameChoiceEvent{
    GameChoiceInspire()
    {
        this.description = "You figured out a new way for yourself to understand the course content\n" +
                "Do you want to apply it to your learning method?";
        this.Id = 3;
    }
    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse)
        {
            if (playerAttribute.getIQ() > 8)
            {
                return new GameEventInspireYesBad();
            }
            return new GameEventInspireYesGood();
        }
        else
        {
            return new GameEventInspireNo();
        }
    }
}

/**
 * EventID: 4
 **/
class GameChoiceHotDay extends GameChoiceEvent{
    GameChoiceHotDay()
    {
        this.description = "Today is really hot, though your dormitory is close to school," +
                " do you want to go to school on a bus?";
        this.Id = 4;
    }
    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse)
        {
            return new GameEventHotDayYes();
        }
        else
        {
            return new GameEventHotDayNo();
        }
    }
}

/**
 * EventID: 5
 **/
class GameChoiceTest extends GameChoiceEvent{
    GameChoiceTest(){
        this.repeatCount = 4;
        this.description = "Today is test date";
        this.Id = 5;
    }
    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {

        if (repeatCount == 4)
        {
            if (playerAttribute.course1Code != "")
            {
                //test1
                repeatCount --;
                return null;/////////////////
            }
            repeatCount --;
        }

        if (repeatCount == 3)
        {
            if (playerAttribute.course2Code != "")
            {
                //test2
                repeatCount --;
                return null;/////////////////
            }
            repeatCount --;
        }

        if (repeatCount == 2)
        {
            if (playerAttribute.course2Code != "")
            {
                //test3
                repeatCount --;
                return null;/////////////////
            }
            repeatCount --;
        }

        if (repeatCount == 1)
        {
            if (playerAttribute.course2Code != "")
            {
                //test4
                repeatCount --;
                return null;/////////////////
            }
            repeatCount --;
        }

        return new GameTestFinished();
    }
}

/**
 * EventID: 6
 **/
class GameChoiceLostCityBegin extends GameChoiceEvent {
    GameChoiceLostCityBegin() {
        this.description = "Today you are reading book in library, but you are attracted by a book." +
                "The book looks really normal, but it seems it is releasing strange mana vibration," +
                "just like... it's talking?\n Do you want to have a look?";
        this.Id = 6;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventLostCity1();
        }
        playerAttribute.eventChain1Status = -2;
        return new GameEventInspireYesGood();

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

/**
 * EventID: 6
 * Effect: Pressure -5, ALL taken course skill + 1
 */
class GameEventInspireYesGood extends GameEvent{
    public GameEventInspireYesGood(){
        this.Id = 6;
        this.description = "You are inspired and you can learn new things faster!\n";
    }

    /**
     * Event's effect on the player(Pressure -5, ALL taken course skill + 1)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        List<String> takenCouses = new ArrayList<>();
        if (!attribute.course1Code.equals(""))
        {
            takenCouses.add(attribute.course1Code.split(" ")[0]);
        }
        if (!attribute.course2Code.equals(""))
        {
            takenCouses.add(attribute.course2Code.split(" ")[0]);
        }
        if (!attribute.course3Code.equals(""))
        {
            takenCouses.add(attribute.course3Code.split(" ")[0]);
        }
        if (!attribute.course4Code.equals(""))
        {
            takenCouses.add(attribute.course4Code.split(" ")[0]);
        }

        for (String course : takenCouses)
        {
            switch (course) {
                case "MANA":
                    attribute.ManaSkill += 1;
                    break;
                case "HERB":
                    attribute.HerbSkill++;
                    break;
                case "SPEL":
                    attribute.SpelSkill++;
                    break;
                case "ATRO":
                    attribute.AtroSkill++;
                    break;
                case "MEDI":
                    attribute.MediSkill++;
                    break;
                case "HIST":
                    attribute.HistSkill++;
                    break;
                default:
                    break;
            }
        }
        attribute.pressure -= 5;
    }
}

/**
 * EventID: 7
 * Effect: Pressure + 5, ALL taken course skill - 1
 */
class GameEventInspireYesBad extends GameEvent{
    public GameEventInspireYesBad(){
        this.Id = 7;
        this.description = "There is some problem with your understanding, the method doesn't work...\n";
    }

    /**
     * Event's effect on the player(Pressure +5, ALL taken course skill - 1)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure += 5;
    }
}

/**
 * EventID: 8
 * Effect: IQ not moving
 */
class GameEventInspireNo extends GameEvent{
    public GameEventInspireNo(){
        this.Id = 8;
        this.description = "You decided not to use it\n";
    }

    /**
     * Event's effect on the player(nothing changed)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {}
}

/**
 * EventID: 9
 * Effect: Wealth -= 10
 */
class GameEventHotDayYes extends GameEvent{
    public GameEventHotDayYes(){
        this.Id = 9;
        this.description = "You decided to go to school on a bus\n";
    }

    /**
     * Event's effect on the player(Wealth -= 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.wealth -= 10;
    }
}

/**
 * EventID: 10
 * Effect: Health -= 5, Pressure += 5
 */
class GameEventHotDayNo extends GameEvent{
    public GameEventHotDayNo(){
        this.Id = 10;
        this.description = "You decided to go to school on foot, but it's too hot and you got heat stroke\n";
    }

    /**
     * Event's effect on the player(Health -= 5, Pressure += 5)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.health -= 5;
        attribute.pressure += 5;
    }
}

/**
 * EventID: 11
 * Effect: Pressure += 10
 */
class GameTestFinished extends GameEvent{
    public GameTestFinished(){
        this.Id = 11;
        this.description = "You have finished all tests, maybe you need a break\n";
    }

    /**
     * Event's effect on the player(Pressure += 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure += 10;
    }
}

/**
 * EventID: 1600
 * Effect: continue event
 */
class GameEventLostCity1 extends GameEvent{
    public GameEventLostCity1(){
        this.Id = 1600;
        this.description = "You took the book to home, maybe it is just a normal but interesting book?\n";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1600;
    }
}

/**
 * EventID: 1600
 **/
class GameChoiceLostCity01 extends GameChoiceEvent {
    GameChoiceLostCity01() {
        this.description = "This day, when you come home, you find the mana vibration of book become more fierce" +
                "Do you want to open it and have a look?";
        this.Id = 1600;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventLostCity2();
        }
        return new GameEventNull();

    }
}

/**
 * EventID: 1601
 * Effect: continue event
 */
class GameEventLostCity2 extends GameEvent{
    public GameEventLostCity2(){
        this.Id = 1601;
        this.description = "You opened the book, tons of mana suddenly flushed out and formed a portal in your room" +
                "Currently the portal seems peace, but the school guards gonna be mad if they see this...\n";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure += 10;
        attribute.eventChain1Status = 1601;
    }
}

/**
 * EventID: 1601 Implementing...
 **/
class GameChoiceLostCity02 extends GameChoiceEvent {
    GameChoiceLostCity02() {
        this.description = "The portal has been lasted for a while, do you want to get in and " +
                "look what's inside?";
        this.Id = 1601;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            playerAttribute.eventChain1Status = -2;
            return new GameEventNull();
        }
        return new GameEventLostCity2Finish();

    }
}

/**
 * EventID: 1601
 * Effect: finish event (IQ + 1, mana_skill += 1)
 */
class GameEventLostCity2Finish extends GameEvent{
    public GameEventLostCity2Finish(){
        this.description = "The portal disappeared after a few days, you returned the book back to the library\n" +
                "But you found your mana sensitivity increased";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.IQ += 1;
        attribute.ManaSkill += 2;
    }
}

