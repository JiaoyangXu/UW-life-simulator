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
            case 10: return new GameChoiceComet();
            case 11: return new GameChoiceHair();
            case 12: return new GameChoiceUnicorn();
            case 13: return new GameChoiceSpell();
            case 14: return new GameChoiceFight();
            case 15: return new GameChoiceIcecream();
            case 16: return new GameChoiceNovel();
            case 17: return new GameChoiceRobe();
            case 18: return new GameChoiceJob();
            case 19: return new GameChoiceClean();
            case 20: return new GameChoiceDrug();

            case 1600: return new GameChoiceLostCity01();
            case 1601: return new GameChoiceLostCity02();
            case 1602: return new GameChoiceLostCity03();
            case 1603: return new GameChoiceLostCity04();
            case 1604: return new GameChoiceLostCity05_1();
            case 1605: return new GameChoiceLostCity05_2();
            case 1606: return new GameChoiceLostCity06();
            case 1607: return new GameChoiceLostCity07();
            case 1608: return new GameChoiceLostCity08();
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
        this.description = "Today is a common nice day\n";
        this.Id = 0;
        this.choice1_description = "OK";
        this.choice2_description = "Nice day!";
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
        this.description = "There are 10 dollars on the ground! Do you want to pick it up?";
        this.Id = 1;
        this.choice1_description = "OK, I will get it";
        this.choice2_description = "No, it's not my money!";
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
        this.description = "There are goose on your way, do you want to go away or attack them?";
        this.Id = 2;
        this.choice1_description = "I will go away";
        this.choice2_description = "Attack before they attack me!";
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
        this.choice1_description = "It's worthy to try new method";
        this.choice2_description = "It's too risky, I will use my old method";
    }
    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse)
        {
            if (playerAttribute.getIQ() < 8)
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
        this.choice1_description = "What a hot day! I will go to school on bus";
        this.choice2_description = "Walking is good for my health, I will still walk to school";
    }
    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse)
        {
            return new GameEventHotDayYes();
        }
        else
        {
            if (playerAttribute.health >= 8)
            {
                return new GameEventHotDayNoGood();
            }
            return new GameEventHotDayNoBad();
        }
    }
}

/**
 * EventID: 5
 **/
class GameChoiceTest extends GameChoiceEvent{
    GameChoiceTest(){
        this.repeatCount = 3;
        this.description = "Today is test date, you need to go for your test";
        this.choice1_description = "OK";
        this.choice2_description = "Good luck for me";
        this.choice1_check="MANA";
        this.choice2_check="MANA";
        this.Id = 5;
    }
    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {

        /*if (repeatCount == 5)
        {
            if (playerAttribute.course1Code != "")
            {
                //test1
                repeatCount --;
                return returnTests(playerAttribute.course1Code);
            }
            repeatCount --;
        }

        if (repeatCount == 4)
        {
            if (playerAttribute.course2Code != "")
            {
                //test2
                repeatCount --;
                return returnTests(playerAttribute.course2Code);
            }
            repeatCount --;
        }*/

        if (repeatCount == 3)
        {
            //test3
            repeatCount --;
            //return returnTests(playerAttribute.course3Code);
            this.choice1_check="HIST";
            this.choice2_check="HIST";
            return returnTests("MANA");
        }

        if (repeatCount == 2)
        {
            //test4
            repeatCount --;
            this.choice1_check="";
            this.choice2_check="";
            //return returnTests(playerAttribute.course4Code);
            return returnTests("ATRO");
        }

        if (repeatCount <= 1)
        {
            repeatCount = 0;
        }
        return new GameTestFinished();
    }

    private GameEvent returnTests(String classCode)
    {
        this.description = "It is still test day...";
        classCode = classCode.split(" ")[0];
        switch (classCode) {
            case "MANA":
                return new GameTestMana();
            case "HERB":
                return new GameTestMana();
            case "ATRO":
                return new GameTestAtro();
            case "SPEL":
                return new GameTestAtro();
            case "HIST":
                return new GameTestAtro();
            default:
                return new GameTestMana();
        }
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
        this.choice1_description = "Interesting... I will look at it...";
        this.choice2_description = "It's dangerous! i wouldn't even touch it! But it's way of releasing mana is interesting";
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
 * EventID: 10
 **/
class GameChoiceComet extends GameChoiceEvent {
    GameChoiceComet() {
        this.description = "A comet passed by, the rumor of world ending spread in your class...";
        this.choice1_description = "It's an interesting topic of conversation!";
        this.choice2_description = "I wish I lived in more enlightened times...";
        this.Id = 10;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventCometYes();
        }
        return new GameEventCometNo();
    }
}

/**
 * EventID: 11
 **/
class GameChoiceHair extends GameChoiceEvent {
    GameChoiceHair() {
        this.description = "You accidentally dropped your hair into the potion";
        this.choice1_description = "Pick it up";
        this.choice2_description = "Just let it go";
        this.Id = 11;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventHairYes();
        }
        return new GameEventHairNo();
    }
}

/**
 * EventID: 12
 **/
class GameChoiceUnicorn extends GameChoiceEvent {
    GameChoiceUnicorn() {
        this.description = "You found a unicorn near SLC!";
        this.choice1_description = "Take a ride on the unicorn";
        this.choice2_description = "Watch and pray";
        this.Id = 12;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventUnicornYes();
        }
        return new GameEventUnicornNo();
    }
}

/**
 * EventID: 13
 **/
class GameChoiceSpell extends GameChoiceEvent {
    GameChoiceSpell() {
        this.description = "You have an upcoming spell test!";
        this.choice1_description = "Take one week to review";
        this.choice2_description = "Give up and play";
        this.Id = 13;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventSpellYes();
        }
        return new GameEventSpellNo();
    }
}

/**
 * EventID: 14
 **/
class GameChoiceFight extends GameChoiceEvent {
    GameChoiceFight() {
        this.description = "You witnessed a fight in front of the library";
        this.choice1_description = "Run away quickly";
        this.choice2_description = "Interfere both sides";
        this.Id = 14;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventFightYes();
        }
        return new GameEventFightNo();
    }
}

/**
 * EventID: 15
 **/
class GameChoiceIcecream extends GameChoiceEvent {
    GameChoiceIcecream() {
        this.description = "There is a new ice cream shop on campus.";
        this.choice1_description = "Go to buy one";
        this.choice2_description = "I don't like dessert";
        this.Id = 15;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventIcecreamYes();
        }
        return new GameEventIcecreamNo();
    }
}

/**
 * EventID: 16
 **/
class GameChoiceNovel extends GameChoiceEvent {
    GameChoiceNovel() {
        this.description = "You found your classmate Fischer’s lost novel.";
        this.choice1_description = "Return to Lost and Found.";
        this.choice2_description = "Read and discuss with Fisher.";
        this.Id = 16;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventNovelYes();
        }
        return new GameEventNovelNo();
    }
}


/**
 * EventID: 17
 **/
class GameChoiceRobe extends GameChoiceEvent {
    GameChoiceRobe() {
        this.description = "You want to buy a new wizard robe, you are deciding the color you want:";
        this.choice1_description = "Green";
        this.choice2_description = "Orange";
        this.Id = 17;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventRobeYes();
        }
        return new GameEventRobeNo();
    }
}

/**
 * EventID: 18
 **/
class GameChoiceJob extends GameChoiceEvent {
    GameChoiceJob() {
        this.description = "You want to get a part time job, which job would you like?";
        this.choice1_description = "Clerk at a wand store.";
        this.choice2_description = "Clerk at a crystal ball store.";
        this.Id = 18;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventJobYes();
        }
        return new GameEventJobNo();
    }
}

/**
 * EventID: 19
 **/
class GameChoiceClean extends GameChoiceEvent {
    GameChoiceClean() {
        this.description = "You want to clean your room in the fastest way possible.";
        this.choice1_description = "Use a broom";
        this.choice2_description = "Use wind magic";
        this.Id = 19;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventCleanYes();
        }
        return new GameEventCleanNo();
    }
}
/**
 * EventID: 20
 **/
class GameChoiceDrug extends GameChoiceEvent {
    GameChoiceDrug() {
        this.description = "Smart drugs are selling in a secluded store.";
        this.choice1_description = "You bought some and used one for Herb test.";
        this.choice2_description = "You ignored them since you don't believe the effect.";
        this.Id = 20;
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventDrugYes();
        }
        return new GameEventDrugNo();
    }
}


/**
 * NULL events which does nothing
**/
class GameEventNull extends GameEvent{
    public GameEventNull(){
        this.Id = 0;
        this.description = "Nothing happened this day";
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
 * Effect: Money+10
 */
class GameEventMoney extends GameEvent{
    public GameEventMoney(){
        this.Id = 1;
        this.description = "You got 10 dollars!";
    }

    /**
     * Event's effect on the player(Money + 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money += 10.0;
    }
}

/**
 * EventID: 2
 * Effect: pressure-5
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
        attribute.pressure -= 5.0;
    }
}

/**
 * EventID: 3
 * Effect: Money-10
 */
class GameEventMoneyLose extends GameEvent{
    public GameEventMoneyLose(){
        this.Id = 3;
        this.description = "You lost 10 dollars!";
    }

    /**
     * Event's effect on the player(Money - 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money -= 10.0;
    }
}

/**
 * EventID: 4
 * Effect: Pressure+10
 */
class GameEventGoose extends GameEvent{
    public GameEventGoose(){
        this.Id = 4;
        this.description = "Goose attacked you!";
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
        this.description = "You attacked goose, that was refreshing! But you are punished for that.";
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
        attribute.money -= 100.0;
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
 * Effect: Money -= 10
 */
class GameEventHotDayYes extends GameEvent{
    public GameEventHotDayYes(){
        this.Id = 9;
        this.description = "You decided to go to school on a bus";
    }

    /**
     * Event's effect on the player(Money -= 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money -= 10;
    }
}

/**
 * EventID: 10
 * Effect: Health -= 5, Pressure += 5
 */
class GameEventHotDayNoBad extends GameEvent{
    public GameEventHotDayNoBad(){
        this.Id = 10;
        this.description = "You decided to walk to school, but it's too hot and you got heat stroke";
    }

    /**
     * Event's effect on the player(Health -= 5, Pressure += 5)
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
 * EventID: 10
 * Effect: Health += 1, Pressure -= 5
 */
class GameEventHotDayNoGood extends GameEvent{
    public GameEventHotDayNoGood(){
        this.Id = 10;
        this.description = "You walked to school as a morning workout and feel released";
    }

    /**
     * Event's effect on the player(Health -= 5, Pressure += 5)
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
 * EventID: 11
 * Effect: Pressure += 10
 */
class GameTestFinished extends GameEvent{
    public GameTestFinished(){
        this.Id = 11;
        this.description = "You have finished all tests, maybe you need a break";
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
 * EventID: 12
 * Effect: inform mana test
 */
class GameTestMana extends GameEvent{
    public GameTestMana(){
        this.Id = 12;
        this.description = "MANA";
    }

    /**
     * Event's effect on the player(None)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {

    }
}

/**
 * EventID: 13
 * Effect: inform astrology test
 */
class GameTestAtro extends GameEvent{
    public GameTestAtro(){
        this.Id = 13;
        this.description = "ATRO";
    }

    /**
     * Event's effect on the player(None)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {

    }
}

/**
 * EventID: 14
 * Effect: inform herbology test
 */
class GameTestHerb extends GameEvent{
    public GameTestHerb(){
        this.Id = 14;
        this.description = "HERB";
    }

    /**
     * Event's effect on the player(None)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {

    }
}

/**
 * EventID: 15
 * Effect: inform history test
 */
class GameTestHist extends GameEvent{
    public GameTestHist(){
        this.Id = 15;
        this.description = "HIST";
    }

    /**
     * Event's effect on the player(None)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {

    }
}

/**
 * EventID: 16
 * Effect: inform spell test
 */
class GameTestSpel extends GameEvent{
    public GameTestSpel(){
        this.Id = 16;
        this.description = "SPEL";
    }

    /**
     * Event's effect on the player(None)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {

    }
}

/**
 * EventID: 17
 * Effect: inform medicine test
 */
class GameTestMedi extends GameEvent{
    public GameTestMedi(){
        this.Id = 17;
        this.description = "MEDI";
    }

    /**
     * Event's effect on the player(None)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {

    }
}

/**
 * EventID: 18
 * Effect: Pressure - 10
 */
class GameEventCometYes extends GameEvent{
    public GameEventCometYes(){
        this.Id = 18;
        this.description = "You feel released when you talk with your friends about it";
    }

    /**
     * Event's effect on the player(Pressure - 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure -= 10;
    }
}

/**
 * EventID: 19
 * Effect: Pressure + 5, ATRO + 1
 */
class GameEventCometNo extends GameEvent{
    public GameEventCometNo(){
        this.Id = 19;
        this.description = "You feel it's a good topic of studying astrology";
    }

    /**
     * Event's effect on the player(Pressure + 5, ATRO + 1)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure += 5;
        attribute.AtroSkill += 1;
    }
}

/**
 * EventID: 20
 * Effect: Pressure - 5, HERB + 1
 */
class GameEventHairYes extends GameEvent{
    public GameEventHairYes(){
        this.Id = 20;
        this.description = "You now have a clone of yourself!";
    }

    /**
     * Event's effect on the player(Pressure - 5, HERB + 1)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure -= 5;
        attribute.HerbSkill += 1;
    }
}

/**
 * EventID: 21
 * Effect: Health - 5, GPA + 1
 */
class GameEventHairNo extends GameEvent{
    public GameEventHairNo(){
        this.Id = 21;
        this.description = "You are bald and stronger.";
    }

    /**
     * Event's effect on the player(Health - 5, GPA + 1)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.health -= 5;
        attribute.GPA += 1;
    }
}

/**
 * EventID: 22
 * Effect: Money - 300
 */
class GameEventUnicornYes extends GameEvent{
    public GameEventUnicornYes(){
        this.Id = 22;
        this.description = "You are brave, but unicorn and the school guard seems angry.";
    }

    /**
     * Event's effect on the player(Money - 300)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money -= 300;
        attribute.health += 1;
    }
}

/**
 * EventID: 23
 * Effect: Luck + 10
 */
class GameEventUnicornNo extends GameEvent{
    public GameEventUnicornNo(){
        this.Id = 23;
        this.description = "Go and buy lottery tickets";
    }

    /**
     * Event's effect on the player(Luck + 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.luck += 10;
    }
}


/**
 * EventID: 24
 * Effect: SpelSkill + 20
 */
class GameEventSpellYes extends GameEvent{
    public GameEventSpellYes(){
        this.Id = 24;
        this.description = "You reach mid-level on Spell";
    }

    /**
     * Event's effect on the player(SpelSkill + 20)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.SpelSkill += 20;
    }
}

/**
 * EventID: 25
 * Effect: Health + 10, SpelSkill - 5
 */
class GameEventSpellNo extends GameEvent{
    public GameEventSpellNo(){
        this.Id = 25;
        this.description = "You gain happiness but failed the spell test.";
    }

    /**
     * Event's effect on the player(Health + 10, SpelSkill - 5)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.health += 10;
        attribute.SpelSkill -= 5;
    }
}


/**
 * EventID: 26
 * Effect: Money - 500
 */
class GameEventFightYes extends GameEvent{
    public GameEventFightYes(){
        this.Id = 26;
        this.description = "They destroyed the front door and all students are penalized";
    }

    /**
     * Event's effect on the player(Money - 500)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money -= 500;
    }
}

/**
 * EventID: 27
 * Effect: IQ - 5, Money + 500
 */
class GameEventFightNo extends GameEvent{
    public GameEventFightNo(){
        this.Id = 27;
        this.description = "They hit your brain but you are awarded because of doing boldly what is righteous.";
    }

    /**
     * Event's effect on the player(IQ - 5, Money + 500)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.IQ -= 5;
        attribute.money += 500;
    }
}


/**
 * EventID: 28
 * Effect: Pressure - 5, Money - 10
 */
class GameEventIcecreamYes extends GameEvent{
    public GameEventIcecreamYes(){
        this.Id = 28;
        this.description = "Your cream turn into a TORNADO";
    }

    /**
     * Event's effect on the player(Pressure - 5, Money - 10)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure -= 5;
        attribute.money -= 10;
    }
}

/**
 * EventID: 29
 * Effect: Pressure + 5
 */
class GameEventIcecreamNo extends GameEvent{
    public GameEventIcecreamNo(){
        this.Id = 29;
        this.description = "You save money but are depressed.";
    }

    /**
     * Event's effect on the player(Pressure + 5)
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
 * EventID: 30
 * Effect: Luck + 5
 */
class GameEventNovelYes extends GameEvent{
    public GameEventNovelYes(){
        this.Id = 30;
        this.description = "After several days, you find a crow doll in your locker.";
    }

    /**
     * Event's effect on the player(Luck + 5)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.luck += 5;
    }
}

/**
 * EventID: 31
 * Effect: Pressure + 5, Health - 5
 */
class GameEventNovelNo extends GameEvent{
    public GameEventNovelNo(){
        this.Id = 31;
        this.description = "Fisher became angry from embarrassment and you are beaten up by her summon Auz.";
    }

    /**
     * Event's effect on the player(Pressure + 5, Health - 5)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.pressure += 5;
        attribute.health -= 5;
    }
}

/**
 * EventID: 32
 * Effect: Money - 20
 */
class GameEventRobeYes extends GameEvent{
    public GameEventRobeYes(){
        this.Id = 32;
        this.description = "You got a green robe.";
    }

    /**
     * Event's effect on the player(Money - 20)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money -= 20;
    }
}

/**
 * EventID: 33
 * Effect: Money - 20
 */
class GameEventRobeNo extends GameEvent{
    public GameEventRobeNo(){
        this.Id = 33;
        this.description = "You got an orange robe.";
    }

    /**
     * Event's effect on the player(Money - 20)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money -= 20;
    }
}


/**
 * EventID: 34
 * Effect: Money + 700
 */
class GameEventJobYes extends GameEvent{
    public GameEventJobYes(){
        this.Id = 34;
        this.description = "You got a job and earn $700 coins";
    }

    /**
     * Event's effect on the player(Money + 700)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money += 700;
    }
}

/**
 * EventID: 35
 * Effect: Money + 700
 */
class GameEventJobNo extends GameEvent{
    public GameEventJobNo(){
        this.Id = 35;
        this.description = "You got a job and earn $700 coins";
    }

    /**
     * Event's effect on the player(Money + 700)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money += 700;
    }
}

/**
 * EventID: 36
 * Effect: Health + 5
 */
class GameEventCleanYes extends GameEvent{
    public GameEventCleanYes(){
        this.Id = 36;
        this.description = "You cleaned your room successfully!";
    }

    /**
     * Event's effect on the player(Health + 5)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.health += 5;
    }
}

/**
 * EventID: 37
 * Effect: Health - 5
 */
class GameEventCleanNo extends GameEvent{
    public GameEventCleanNo(){
        this.Id = 37;
        this.description = "You’ve blown up your room and it is a mess now";
    }

    /**
     * Event's effect on the player(Health - 5)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.health -= 5;
    }
}

/**
 * EventID: 38
 * Effect: Money - 500, IQ - 5
 */
class GameEventDrugYes extends GameEvent{
    public GameEventDrugYes(){
        this.Id = 38;
        this.description = "You have a splitting headache and found at the bottom of the drug which showed it had passed expiry date.";
    }

    /**
     * Event's effect on the player(Money - 500, IQ - 5)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.money -= 500;
        attribute.IQ -= 5;

    }
}

/**
 * EventID: 39
 * Effect: GPA + 1
 */
class GameEventDrugNo extends GameEvent{
    public GameEventDrugNo(){
        this.Id = 39;
        this.description = "You got 100 on the test all by your own.";
    }

    /**
     * Event's effect on the player(GPA + 1)
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.GPA += 1;
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
        this.choice1_description = "Yes, I will have a look";
        this.choice2_description = "No, it's too weird";
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
 * EventID: 1601
 **/
class GameChoiceLostCity02 extends GameChoiceEvent {
    GameChoiceLostCity02() {
        this.description = "The portal has been lasted for a while, do you want to get in and " +
                "look what's inside?";
        this.Id = 1601;
        this.choice1_description = "Yes, I will have a look";
        this.choice2_description = "No, it's too weird";
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventLostCity2Continue();
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
        attribute.eventChain1Status = -2;
    }
}

/**
 * EventID: 1602
 * Effect: continue Event
 */
class GameEventLostCity2Continue extends GameEvent{
    public GameEventLostCity2Continue(){
        this.description = "You decide to go into the portal later";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1602;
    }
}

/**
 * EventID: 1602
 **/
class GameChoiceLostCity03 extends GameChoiceEvent {
    GameChoiceLostCity03() {
        this.description = "You entered the portal, it was an ancient city, people on the street seems cannot see you" +
                "but in the center of the city there is a huge clock tower looks really special?";
        this.Id = 1602;
        this.choice1_check = "HIST";
        this.choice1_description = "Let me check which era should this city in\n(Require history test)";
        this.choice2_check = "MANA";
        this.choice2_description = "These are just illusions, pay attention to the flow of mana\n(Require mana test)";
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            if (lastMark >= 50)
            {
                return new GameEventLostCity3Hist();
            }
            return new GameEventLostCity3Lost();
        }
        if (lastMark >= 50)
        {
            return new GameEventLostCity3Mana();
        }
        return new GameEventLostCity3Lost();

    }
}

/**
 * EventID: 1602
 * Effect: continue Event
 */
class GameEventLostCity3Lost extends GameEvent{
    public GameEventLostCity3Lost(){
        this.description = "Your result has some problem, you got lost in the city. Finally, you found you are at the entrance of the portal";
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
 * EventID: 1602
 * Effect: continue Event
 */
class GameEventLostCity3Mana extends GameEvent{
    public GameEventLostCity3Mana(){
        this.description = "The flow of mana takes you to the clock tower, but when you enter the tower, you are back to your room. It seems you can only be in the portal for a limited amount of time";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.ManaSkill += 1;
        attribute.eventChain1Status = 1603;
    }
}

/**
 * EventID: 1602
 * Effect: continue Event
 */
class GameEventLostCity3Hist extends GameEvent{
    public GameEventLostCity3Hist(){
        this.description = "The era of the surrounding buildings should be ancient magic era, but the clock tower is clearly modern. When you realize this, you are back to your room again. It seems you can only be in the portal for a limited amount of time";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.HistSkill += 1;
        attribute.eventChain1Status = 1603;
    }
}

/**
 * EventID: 1603
 **/
class GameChoiceLostCity04 extends GameChoiceEvent {
    GameChoiceLostCity04() {
        this.description = "Next day, you entered the portal again, this time you found you are inside the clock tower, the giant clock is in front of you, there is a button seems can control the time of the clock";
        this.Id = 1603;
        this.choice1_description = "I will try this button...";
        this.choice2_description = "It's really dangerous, let's check other things";
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventLostCity3Switch();
        }
        return new GameEventLostCity3Wander();

    }
}

/**
 * EventID: 1603
 * Effect: continue Event
 */
class GameEventLostCity3Switch extends GameEvent{
    public GameEventLostCity3Switch(){
        this.description = "You are teleported back to home, there should be something changed";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1604;
    }
}

/**
 * EventID: 1603
 * Effect: continue Event
 */
class GameEventLostCity3Wander extends GameEvent{
    public GameEventLostCity3Wander(){
        this.description = "You decided not to touch the button until you know what is it for";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1605;
    }
}

/**
 * EventID: 1604
 **/
class GameChoiceLostCity05_1 extends GameChoiceEvent {
    GameChoiceLostCity05_1() {
        this.description = "Next day, when you went back to the portal, you found the environment changed, you are in a magic potion lab now and the city is in fire. On the sky, there was a dragon. Dragon found you, and start attack you";
        this.Id = 1604;
        this.choice1_description = "I will check if there are usable potions\n(Requires magical potion(mana) test)";
        this.choice2_description = "I need to run away as fast as possible";
        this.choice1_check="MANA";
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            if (lastMark >= 50)
            {
                return new GameEventLostCity5PotionGood();
            }
            return new GameEventLostCity5PotionBad();
        }
        return new GameEventLostCity5Run();

    }
}

/**
 * EventID: 1604
 * Effect: continue Event
 */
class GameEventLostCity5PotionGood extends GameEvent{
    public GameEventLostCity5PotionGood(){
        this.description = "You found fire resistance potion and survived the dragon fire, but you are hit by the dragon's tail. When you wake up again, you found you are in your room";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1603;
        attribute.MediSkill+=2;
        attribute.health --;
        attribute.pressure += 5;
    }
}

/**
 * EventID: 1604
 * Effect: continue Event
 */
class GameEventLostCity5PotionBad extends GameEvent{
    public GameEventLostCity5PotionBad(){
        this.description = "You didn't find anything and is directly hit by dragon's breath. When you wake up again, you found you are in your room";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1603;
        attribute.health -= 2;
        attribute.pressure += 15;
    }
}

/**
 * EventID: 1604
 * Effect: continue Event
 */
class GameEventLostCity5Run extends GameEvent{
    public GameEventLostCity5Run(){
        this.description = "You tried found a hidden place, and managed to reached exit before the whole city is melt down by the dragon.";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1603;
        attribute.health -= 1;
        attribute.pressure += 10;
    }
}

/**
 * EventID: 1605
 **/
class GameChoiceLostCity05_2 extends GameChoiceEvent {
    GameChoiceLostCity05_2() {
        this.description = "This time, you found a strange door in the tower. The door seems burned by fire.";
        this.Id = 1605;
        this.choice1_description = "What's inside?";
        this.choice2_description = "I don't really care what is inside...";
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            Random random = new Random();
            if (random.nextBoolean())
            {
                if (playerAttribute.health >= 7 && playerAttribute.ManaSkill >= 7 && random.nextBoolean())
                {
                    return new GameEventLostCity5OpenGood();
                }
            }
            return new GameEventLostCity5OpenBad();
        }
        return new GameEventLostCity5WalkAway();
    }
}

/**
 * EventID: 1605
 * Effect: continue Event
 */
class GameEventLostCity5OpenGood extends GameEvent{
    public GameEventLostCity5OpenGood(){
        this.description = "You opened the door, there is an egg in the center of the room. You took it and went out of the portal";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1608;
    }
}

/**
 * EventID: 1605
 * Effect: continue Event
 */
class GameEventLostCity5OpenBad extends GameEvent{
    public GameEventLostCity5OpenBad(){
        this.description = "You opened the door, fire suddenly flushed out on you. When you wake up, you are in your room again";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1605;
        attribute.health -= 1;
        attribute.pressure += 5;
    }
}

/**
 * EventID: 1605
 * Effect: continue Event
 */
class GameEventLostCity5WalkAway extends GameEvent{
    public GameEventLostCity5WalkAway(){
        this.description = "You decided not to open the door";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1606;
    }
}

/**
 * EventID: 1606
 **/
class GameChoiceLostCity06 extends GameChoiceEvent {
    GameChoiceLostCity06() {
        this.description = "You entered another room, the room is messy and seems people evacuated in a hurry. You also found some herbs and empty potion bottles";
        this.Id = 1606;
        this.choice1_check = "MANA";
        this.choice1_description = "I will check which potions did they used before\nRequire Herb(Mana) test";
        this.choice2_description = "Those unused potions are mine";
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            if (lastMark >= 50)
            {
                return new GameEventLostCity6CheckPotionGood();
            }
            return new GameEventLostCity6CheckPotionBad();
        }
        return new GameEventLostCity6Finish();
    }
}

/**
 * EventID: 1606
 * Effect: continue Event
 */
class GameEventLostCity6Finish extends GameEvent{
    public GameEventLostCity6Finish(){
        this.description = "You took unused potions away, but you found the portal never opened again after that";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = -2;
        attribute.MediSkill += 3;
        attribute.HerbSkill += 3;
        attribute.health += 4;
        attribute.money += 500;
    }
}

/**
 * EventID: 1606
 * Effect: continue Event
 */
class GameEventLostCity6CheckPotionGood extends GameEvent{
    public GameEventLostCity6CheckPotionGood(){
        this.description = "People seems used fire resistance potion before evacuation. You managed to make some";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.HerbSkill += 1;
        attribute.MediSkill += 1;
        attribute.eventChain1Status = 1607;
    }
}

/**
 * EventID: 1606
 * Effect: continue Event
 */
class GameEventLostCity6CheckPotionBad extends GameEvent{
    public GameEventLostCity6CheckPotionBad(){
        this.description = "It took you a long time to check the potions, but you didn't come up any result";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = 1606;
        attribute.pressure += 10;
        attribute.HerbSkill += 1;
        attribute.MediSkill += 1;
    }
}

/**
 * EventID: 1607
 **/
class GameChoiceLostCity07 extends GameChoiceEvent {
    GameChoiceLostCity07() {
        this.description = "You went back to the burnt door";
        this.Id = 1607;
        this.choice2_check = "MANA";
        this.choice1_description = "Drink potion and go in there";
        this.choice2_description = "Not today";
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventLostCity5OpenGood();
        }
        return new GameEventNull();
    }
}

/**
 * EventID: 1608
 **/
class GameChoiceLostCity08 extends GameChoiceEvent {
    GameChoiceLostCity08() {
        this.description = "You went back to the portal with the egg, the egg suddenly float and pressed the button and take you to a burnt city. A black dragon is staring at you";
        this.Id = 1608;
        this.choice1_description = "The dragon must be looking for this egg!";
        this.choice2_description = "No way, this egg is mine!";
    }

    @Override
    public GameEvent generateEvent(boolean playerResponse, PlayerAttribute playerAttribute) {
        if (playerResponse) {
            return new GameEventLostCity8EndGood();
        }
        return new GameEventLostCity8EndBad();
    }
}

/**
 * EventID: 1608
 * Effect: finish Event
 */
class GameEventLostCity8EndGood extends GameEvent{
    public GameEventLostCity8EndGood(){
        this.description = "The dragon flew away when you handed the egg back, and you found there is a golden scale on the ground";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = -2;
        attribute.pressure -= 10;
        attribute.HerbSkill += 2;
        attribute.MediSkill += 2;
        attribute.ManaSkill += 2;
        attribute.HistSkill += 2;
        attribute.SpelSkill += 2;
        attribute.AtroSkill += 2;
        attribute.health += 3;
        attribute.wealth += 1;
        attribute.money += 1000;
    }
}

/**
 * EventID: 1608
 * Effect: finish Event
 */
class GameEventLostCity8EndBad extends GameEvent{
    public GameEventLostCity8EndBad(){
        this.description = "The dragon is irritate by your action and sent you back with angry breath. But you got the egg.";
    }

    /**
     * Event's effect on the player
     *
     * Input: UserAttribute : attribute
     * Output: void
     **/
    @Override
    public void visit(PlayerAttribute attribute) {
        attribute.eventChain1Status = -2;
        attribute.pressure += 20;
        attribute.health -= 3;
        attribute.wealth += 1;
        attribute.money += 5000;
    }
}