package com.example.uw_life_simulator.model;
import com.example.uw_life_simulator.data.Course;

import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.data.CourseSelectionRecord;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.data.PlayerAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Factory {

    /**
     * NewEvent generator which generates events based on the eventId
     * Events are implemented in GameEventGenerator.Java
     *
     * Input: int : eventId
     * Output: GameEvent : Generated event
     **/
    static public GameChoiceEvent generateRandomChoiceEvent(PlayerAttribute playerAttribute)
    {
        RandomEventListCommon eventListCommon = new RandomEventListCommon();
        int iq = playerAttribute.IQ;
        int wealth = playerAttribute.wealth;
        int luck = playerAttribute.luck;
        int health = playerAttribute.health;
        int pressure = playerAttribute.pressure;
        int gpa = playerAttribute.GPA;

        Random random = new Random();
        if (playerAttribute.eventChain1Status > 0 || playerAttribute.eventChain2Status > 0 ||
                playerAttribute.eventChain3Status > 0 || playerAttribute.eventChain4Status > 0)
        {
            if (random.nextInt(2) == 1)
            {
                List<Integer> chainAvaliable = new ArrayList<>();
                chainAvaliable.add(playerAttribute.eventChain1Status);
                chainAvaliable.add(playerAttribute.eventChain2Status);
                chainAvaliable.add(playerAttribute.eventChain3Status);
                chainAvaliable.add(playerAttribute.eventChain4Status);

                int rand = chainAvaliable.get(random.nextInt(4));
                if (rand != 0)
                {
                    return generateChoiceEvent(rand);
                }
            }
        }

        int rand = random.nextInt((iq + wealth + luck + health) + eventListCommon.GeneralList.size());
        if (rand < iq)
        {
            rand = random.nextInt(eventListCommon.IqList.size());
            //Event has been triggered
            int id = eventListCommon.IqList.get(rand);
            if (id == 6 && playerAttribute.eventChain1Status != 0)
            {
                id = 0;
            }
            return generateChoiceEvent(id);
        }
        else if (rand < iq + wealth)
        {
            rand = random.nextInt(eventListCommon.WealthList.size());
            return generateChoiceEvent(eventListCommon.WealthList.get(rand));
        }
        else if (rand < iq + wealth + luck)
        {
            rand = random.nextInt(eventListCommon.LuckList.size());
            return generateChoiceEvent(eventListCommon.LuckList.get(rand));
        }
        else if (rand < iq + wealth + luck + health)
        {
            rand = random.nextInt(eventListCommon.HealthList.size());
            return generateChoiceEvent(eventListCommon.HealthList.get(rand));
        }
        else
        {
            rand = random.nextInt(eventListCommon.GeneralList.size());
            return generateChoiceEvent(eventListCommon.GeneralList.get(rand));
        }
    }

    /**
     * NewEvent generator which generates events based on the eventId
     * Events are implemented in GameEventGenerator.Java
     *
     * Input: int : eventId
     * Output: GameEvent : Generated event
     **/
    static public GameChoiceEvent generateChoiceEvent(int eventId) // Implementation needed
    {
        return GameEventGenerator.generateChoiceEvent(eventId);
    }

    /**
     * NewEvent generator which generates events based on the eventId
     * Events are implemented in GameEventGenerator.Java
     *
     * Input: int : eventId
     * Output: GameEvent : Generated event
     **/
    static public GameEvent generateEvent(int eventId) // Implementation needed
    {
        return GameEventGenerator.generateEvent(eventId);
    }

    /**
     * Talent generator which generates events based on the talentId
     * Talents are implemented in TalentGenerator.Java
     *
     * Input: int : talentId
     * Output: Talent : Generated talent
     **/
    static public Buff generateBuff(int buffId) // Implementation needed
    {
        return BuffGenerator.generateBuff(buffId);
    }

    /**
     * Company generator which generates events based on the companyId
     * Company info is stored in database file
     *
     * Input: int : companyId
     * Output: Company : Generated company
     **/
    static public Company generateCompany(int companyId) // Implementation needed
    {
        return null;
    }

    /**
     * Course generator which generates events based on the courseId
     * Course info is stored in database file
     *
     * Input: int : courseId
     * Output: Course : Generated course
     **/
    /*static public Course generateCourse(int courseId) // Implementation needed
    {
        return null;
    }*/
}

class RandomEventListCommon
{
    List<Integer> IqList = new ArrayList<>();
    List<Integer> WealthList = new ArrayList<>();
    List<Integer> HealthList = new ArrayList<>();
    List<Integer> LuckList = new ArrayList<>();
    List<Integer> GeneralList = new ArrayList<>();
    RandomEventListCommon()
    {
        IqList.add(3);
        IqList.add(6);
        WealthList.add(1);
        HealthList.add(4);
        LuckList.add(2);
        LuckList.add(10);
        GeneralList.add(0);

    }
}

