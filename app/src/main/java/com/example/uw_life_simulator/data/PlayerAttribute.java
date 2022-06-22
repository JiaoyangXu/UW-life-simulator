package com.example.uw_life_simulator.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlayerAttribute {
    @PrimaryKey(autoGenerate = true)
    public int playerID;

    @ColumnInfo
    public String playerName;

    @ColumnInfo
    public int programID;

    @ColumnInfo
    public int numTerm;

    @ColumnInfo
    public int IQ;

    @ColumnInfo
    public int luck;

    @ColumnInfo
    public int wealth;

    @ColumnInfo
    public int health;

    public PlayerAttribute(int playerID, int IQ, int luck, int wealth, int health) {
        this.programID = 1;
        this.playerName = "Mr.Goose";
        this.numTerm = 0;
        this.IQ = IQ;
        this.luck = luck;
        this.wealth = wealth;
        this.health = health;
    }
}

