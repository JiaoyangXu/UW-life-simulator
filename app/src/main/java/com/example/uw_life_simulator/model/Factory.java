package com.example.uw_life_simulator.model;
import com.example.uw_life_simulator.data.Course;

import com.example.uw_life_simulator.DAO.CourseSelectionRecordDAO;
import com.example.uw_life_simulator.data.CourseSelectionRecord;
import com.example.uw_life_simulator.Database.CourseDatabase;
import com.example.uw_life_simulator.data.PlayerAttribute;

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
    static public GameChoiceEvent generateRandomChoiceEvent(PlayerAttribute playerAttribute) // Implementation needed
    {
        RandomEventListCommon eventListCommon = new RandomEventListCommon();
        int iq = playerAttribute.IQ;
        int wealth = playerAttribute.wealth;
        int luck = playerAttribute.luck;
        int health = playerAttribute.health;
        int pressure = playerAttribute.pressure;
        int gpa = playerAttribute.GPA;

        Random random = new Random();
        int rand = random.nextInt(2 * (iq + wealth + luck + health));

        if (rand < iq)
        {
            rand = random.nextInt(eventListCommon.IqList.size()-1);
            return generateChoiceEvent(rand);
        }
        else if (rand < iq + wealth)
        {
            rand = random.nextInt(eventListCommon.WealthList.size()-1);
            return generateChoiceEvent(rand);
        }
        else if (rand < iq + wealth + luck)
        {
            rand = random.nextInt(eventListCommon.LuckList.size()-1);
            return generateChoiceEvent(rand);
        }
        else if (rand < iq + wealth + luck + health)
        {
            rand = random.nextInt(eventListCommon.HealthList.size()-1);
            return generateChoiceEvent(rand);
        }
        else
        {
            rand = random.nextInt(eventListCommon.GeneralList.size()-1);
            return generateChoiceEvent(rand);
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
    List<Integer> IqList;
    List<Integer> WealthList;
    List<Integer> HealthList;
    List<Integer> LuckList;
    List<Integer> GeneralList;
    RandomEventListCommon()
    {
        WealthList.add(1);
        LuckList.add(2);
    }
}

class RandomEventListWork
{
    List<Integer> IqList;
}

