package com.example.uw_life_simulator.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.uw_life_simulator.model.Subject;
import com.example.uw_life_simulator.model.Visitor;

@Entity
public class PlayerAttribute implements Subject {
    // Define all the columns here.
    // autoGenerate = True means Room will automatically set the next inserted instance to max+1 for
    // this column.

    @PrimaryKey(autoGenerate = true)
    public int playerID;

    @ColumnInfo
    public String playerName;

    @ColumnInfo
    public int programID;

    public void setIQ(int IQ) {
        this.IQ = IQ;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void setWealth(int wealth) {
        this.wealth = wealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

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
    public int ManaSkill;

    @ColumnInfo
    public int SpelSkill;

    @ColumnInfo
    public int money;

    @ColumnInfo
    public int HerbSkill;

    @ColumnInfo
    public int HistSkill;

    @ColumnInfo
    public int MediSkill;

    @ColumnInfo
    public int AtroSkill;

    @ColumnInfo
    public int curCourseLoad;

    @ColumnInfo
    public String course1Code;

    @ColumnInfo
    public String course2Code;

    @ColumnInfo
    public String course3Code;

    @ColumnInfo
    public String course4Code;

    @ColumnInfo
    public int eventChain1Status;

    @ColumnInfo
    public int eventChain2Status;

    @ColumnInfo
    public int eventChain3Status;

    @ColumnInfo
    public int eventChain4Status;

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
                ", money=" + money +
                ", numFailedCourses=" + numFailedCourses +
                ", employed=" + employed +
                ", resumeScore=" + resumeScore +
                ", workTermEvalAvg=" + workTermEvalAvg +
                ", availablePts=" + availablePts +
                ", ManaSkill=" + ManaSkill +
                ", SpelSkill=" + SpelSkill +
                ", HerbSkill=" + HerbSkill +
                ", HistSkill=" + HistSkill +
                ", MediSkill=" + MediSkill +
                ", AtroSkill=" + AtroSkill +
                ", curCourseLoad=" + curCourseLoad +
                ", course1Code='" + course1Code + '\'' +
                ", course2Code='" + course2Code + '\'' +
                ", course3Code='" + course3Code + '\'' +
                ", course4Code='" + course4Code + '\'' +
                ", eventChain1Status=" + eventChain1Status +
                ", eventChain2Status=" + eventChain2Status +
                ", eventChain3Status=" + eventChain3Status +
                ", eventChain4Status=" + eventChain4Status +
                '}';
    }

    // Constructor.
    public PlayerAttribute(int IQ, int luck, int wealth, int health) {
        this.programID = 1;
        this.playerName = "Mr.Goose";
        this.numTerm = 0;
        this.IQ = IQ;
        this.luck = luck;
        this.wealth = wealth;
        // this.money = wealth * 1000;
        this.health = health;
        this.pressure = 30 - health * 3;
        this.GPA = 80 + IQ * 2;
        this.money = 1000 + wealth * 100;
        this.numFailedCourses = 0;
        this.employed = false;
        this.resumeScore = 0;
        this.workTermEvalAvg = -1;
        this.availablePts = 0;
        this.ManaSkill = 0;
        this.SpelSkill = 0;
        this.HerbSkill = 0;
        this.HistSkill = 0;
        this.MediSkill = 0;
        this.AtroSkill = 0;
        this.curCourseLoad = 0;
        this.course1Code = "";
        this.course2Code = "";
        this.course3Code = "";
        this.course4Code = "";
        this.eventChain1Status = 0;
        this.eventChain2Status = 0;
        this.eventChain3Status = 0;
        this.eventChain4Status = 0;
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
    public int getMoney() {return money;}
    public int getGPA() { return GPA; }

    // Getters for extra academic and career indicators
    public int getNumFailedCourses() { return numFailedCourses; }
    public boolean getEmployed() { return employed; }
    public int getResumeScore() { return resumeScore; }
    public int getWorkTermEvalAvg() { return workTermEvalAvg; }
    public int getAvailablePts() { return availablePts; }

    public int getPlayerID() {
        return playerID;
    }

    public boolean isEmployed() {
        return employed;
    }

    public int getManaSkill() {
        return ManaSkill;
    }

    public int getSpelSkill() {
        return SpelSkill;
    }

    public int getHerbSkill() {
        return HerbSkill;
    }

    public int getHistSkill() {
        return HistSkill;
    }

    public int getMediSkill() {
        return MediSkill;
    }

    public int getAtroSkill() {
        return AtroSkill;
    }

    // Getters for current courses
    public int getCurCourseLoad() { return curCourseLoad; }
    public String getCourse1Code() { return course1Code; }
    public String getCourse2Code() { return course2Code; }
    public String getCourse3Code() { return course3Code; }
    public String getCourse4Code() { return course4Code; }

    public int getEventChain1Status() { return eventChain1Status; }
    public int getEventChain2Status() { return eventChain2Status; }
    public int getEventChain3Status() { return eventChain3Status; }
    public int getEventChain4Status() { return eventChain4Status; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void updateAttribute() {
        this.money = 1000 + wealth * 100;
        this.pressure = 30 - health * 3;
        this.GPA = 80 + IQ * 2;
    }
}

