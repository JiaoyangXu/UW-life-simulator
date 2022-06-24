package com.example.uw_life_simulator.model;

import com.example.uw_life_simulator.data.PlayerAttribute;

public interface Visitor {
    // Public functions
    public abstract void visit(PlayerAttribute attribute);
}
