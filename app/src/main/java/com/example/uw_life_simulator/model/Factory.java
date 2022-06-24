package com.example.uw_life_simulator.model;
import com.example.uw_life_simulator.data.Course;

public class Factory {
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
    static public Course generateCourse(int courseId) // Implementation needed
    {
        return null;
    }
}

