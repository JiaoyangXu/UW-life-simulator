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

    @ColumnInfo
    public int pressure;

    @ColumnInfo
    public int GPA;

    @ColumnInfo
    public int numFailedCourses;

    @ColumnInfo
    public boolean employed;

    @ColumnInfo
    public int resumeScore;

    @ColumnInfo
    public int workTermEvalAvg;

    @ColumnInfo
    public int availablePts;

    @ColumnInfo
    public int MathSkill;

    @ColumnInfo
    public int CSSkill;

    @ColumnInfo
    public int EconSkill;

    @ColumnInfo
    public int LangSkill;

    @ColumnInfo
    public int SciSkill;

    @ColumnInfo
    public int ArtsSkill;

    @Override
    public String toString() {
        return "PlayerAttribute{" +
                "playerID=" + playerID +
                ", playerName='" + playerName + '\'' +
                ", programID=" + programID +
                ", numTerm=" + numTerm +
                ", IQ=" + IQ +
                ", luck=" + luck +
                ", wealth=" + wealth +
                ", health=" + health +
                ", pressure=" + pressure +
                ", GPA=" + GPA +
                ", numFailedCourses=" + numFailedCourses +
                ", employed=" + employed +
                ", resumeScore=" + resumeScore +
                ", workTermEvalAvg=" + workTermEvalAvg +
                ", availablePts=" + availablePts +
                ", MathSkill=" + MathSkill +
                ", CSSkill=" + CSSkill +
                ", EconSkill=" + EconSkill +
                ", LangSkill=" + LangSkill +
                ", SciSkill=" + SciSkill +
                ", ArtsSkill=" + ArtsSkill +
                '}';
    }

    public PlayerAttribute(int IQ, int luck, int wealth, int health) {
        this.programID = 1;
        this.playerName = "Mr.Goose";
        this.numTerm = 0;
        this.IQ = IQ;
        this.luck = luck;
        this.wealth = wealth;
        this.health = health;
        this.pressure = 0;
        this.GPA = 100;
        this.numFailedCourses = 0;
        this.employed = false;
        this.resumeScore = 0;
        this.workTermEvalAvg = -1;
        this.availablePts = 0;
        this.MathSkill = 0;
        this.CSSkill = 0;
        this.EconSkill = 0;
        this.LangSkill = 0;
        this.SciSkill = 0;
        this.ArtsSkill = 0;
    }

    // Getters for basic identification information
    public int getProgramID() { return programID; }
    public String getPlayerName() { return playerName; }
    public int getNumTerm() { return numTerm; }

    // Getters for basic player attributes
    public int getIQ() { return IQ; }
    public int getLuck() { return luck; }
    public int getHealth() { return health; }
    public int getWealth() { return wealth; }
    public int getPressure() { return pressure; }
    public int getGPA() { return GPA; }

    // Getters for extra academic and career indicators
    public int getNumFailedCourses() { return numFailedCourses; }
    public boolean getEmployed() { return employed; }
    public int getResumeScore() { return resumeScore; }
    public int getWorkTermEvalAvg() { return workTermEvalAvg; }
    public int getAvailablePts() { return availablePts; }

    // Getters for skills attribute
    public int getMathSkill() { return MathSkill; }
    public int getCSSkill() { return CSSkill; }
    public int getEconSkill() { return EconSkill; }
    public int getLangSkill() { return LangSkill; }
    public int getSciSkill() { return SciSkill; }
    public int getArtsSkill() { return ArtsSkill; }
}

