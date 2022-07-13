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
    public int money;

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
                ", MathSkill=" + MathSkill +
                ", CSSkill=" + CSSkill +
                ", EconSkill=" + EconSkill +
                ", LangSkill=" + LangSkill +
                ", SciSkill=" + SciSkill +
                ", ArtsSkill=" + ArtsSkill +
                ", curCourseLoad=" + curCourseLoad +
                ", course1Code=" + course1Code +
                ", course2Code=" + course2Code +
                ", course3Code=" + course3Code +
                ", course4Code=" + course4Code +
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
        this.health = health;
        this.pressure = 30 - health * 3;
        this.GPA = 80 + IQ * 2;
        this.money = 1000 + wealth * 100;
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
        this.curCourseLoad = 0;
        this.course1Code = "";
        this.course2Code = "";
        this.course3Code = "";
        this.course4Code = "";
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
    public int getMoney() { return money; }

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

    // Getters for current courses
    public int getCurCourseLoad() { return curCourseLoad; }
    public String getCourse1Code() { return course1Code; }
    public String getCourse2Code() { return course2Code; }
    public String getCourse3Code() { return course3Code; }
    public String getCourse4Code() { return course4Code; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

