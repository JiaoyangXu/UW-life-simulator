package com.example.uw_life_simulator.model;

import com.example.uw_life_simulator.data.PlayerAttribute;
import com.example.uw_life_simulator.model.Factory;

import java.util.ArrayList;
import java.util.List;

public class NewEvent {
    static GameChoiceEvent gce = null;
    public static List<String> generateNewChoice(Player player, int totalnum)
    {
        PlayerAttribute pa = player.getPlayerAttribute();
        gce = Factory.generateRandomChoiceEvent(pa);
        List<String> ret = new ArrayList<>();
        ret.add(gce.description);
        ret.add(gce.choice1_description);
        ret.add(gce.choice2_description);
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
}
