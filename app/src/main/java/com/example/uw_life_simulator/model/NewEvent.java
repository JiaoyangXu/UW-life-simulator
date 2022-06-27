package com.example.uw_life_simulator.model;

import com.example.uw_life_simulator.data.PlayerAttribute;
import com.example.uw_life_simulator.model.Factory;

public class NewEvent {
    public static String generateNewEvent(Player player, int totalnum)
    {
        GameEvent gm = Factory.generateEvent(totalnum % 4);
        //String s = "New Event " + totalnum + " is: " + gm.description;
        String s = gm.description;
        PlayerAttribute pa = player.getPlayerAttribute();
        pa.GPA -= 1;
        player.setPlayerAttribute(pa);
        return s;
    }
}
