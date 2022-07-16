package com.example.uw_life_simulator.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SpellCard {

    @PrimaryKey(autoGenerate = true)
    public int picID;

    @ColumnInfo
    public int address;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public int selected;

    @ColumnInfo
    public int used;

    public SpellCard(int address, String name) {
        this.address = address;
        this.name = name;
        this.selected = 0;
        this.used = 0;
    }

    public int getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }



}
