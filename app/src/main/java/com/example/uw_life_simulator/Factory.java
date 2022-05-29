package com.example.uw_life_simulator;

public class Factory {
    static public GameEvent generateEvent(int eventId) // Implementation needed
    {
        switch (eventId)
        {
            case 0: return new GameEventNull();
            default: return null;
        }
    }

    static public Company generateCompany(int companyId) // Implementation needed
    {
        return null;
    }

    static public Talent generateTalent(int talentId) // Implementation needed
    {
        return null;
    }

    static public Course generateCourse(int CourseId) // Implementation needed
    {
        return null;
    }
}

class GameEventNull extends GameEvent{
    public GameEventNull(){
        this.Id = 0;
        this.description = "This is a null event\n";
    }
    @Override
    public void visit(Player player) {
        //doing nothing
    }
}
