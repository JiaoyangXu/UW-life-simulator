package com.example.uw_life_simulator.model;

import com.example.uw_life_simulator.data.PlayerAttribute;
import com.example.uw_life_simulator.model.Factory;

import java.util.ArrayList;
import java.util.List;

public class NewEvent {
    static GameChoiceEvent gce = null;
    static int prev_event_num = -1;
    public static List<String> generateNewChoice(Player player, int totalnum)
    {
        PlayerAttribute pa = player.getPlayerAttribute();
        if (gce != null)
        {
            if (!gce.repeated())
            {
                if (totalnum == 9 || totalnum == 19)
                {
                    gce = Factory.generateTestEvent();
                }
                else
                {
                    gce = Factory.generateRandomChoiceEvent(pa);
                    while (gce.Id == prev_event_num)
                    {
                        gce = Factory.generateRandomChoiceEvent(pa);
                    }
                }
            }
        }
        else
        {
            if (totalnum == 9 || totalnum == 19)
            {
                gce = Factory.generateTestEvent();
            }
            else
            {
                gce = Factory.generateRandomChoiceEvent(pa);
                while (gce.Id == prev_event_num)
                {
                    gce = Factory.generateRandomChoiceEvent(pa);
                }
            }
        }
        List<String> ret = new ArrayList<>();
        ret.add(gce.description);
        ret.add(gce.choice1_description);
        ret.add(gce.choice2_description);
        prev_event_num = gce.Id;
        return ret;
    }

    // response is True for choice1 and False for choice2
    public String generateNewEvent(Player player, boolean response)
    {
        PlayerAttribute pa = player.getPlayerAttribute();
        GameEvent ge = gce.generateEvent(response, pa);
        ge.visit(pa);
        return ge.description;

    }

    public boolean isFinished()
    {
        return gce.repeated();
    }

    public String getTestClassName(boolean playerResponse)
    {
        if (playerResponse)
        {
            return gce.choice1_check;
        }
        return gce.choice2_check;
    }

    public void setLastMark(double mark)
    {
        gce.setLastMark(mark);
    }
}
