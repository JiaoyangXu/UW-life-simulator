package com.example.uw_life_simulator;

import java.util.List;

public class Company {
    private String name;
    private String description;
    //Math, CS, Econ, Languages, Science, Arts
    // Required points on each major ability
    private List<Integer> requiredSkillLevel;


    //Getters & Setters
    public String getDescription(){return description;}
    public String getName(){return name;}
    public List<Integer> getRequiredSkillLevel(){return requiredSkillLevel;}


    //Constructors
    public Company(String company_name, String company_description, List<Integer> requirements){
        this.name = company_name;
        this.description = company_description;
        this.requiredSkillLevel = requirements;
    }


    //public functions
    /* Check if the given Major ability can satisfy this company's requirement*/
    public boolean checkValidity(List<Integer> skillsLevel)
    {
        for (int i = 0; i < requiredSkillLevel.size(); i ++)
        {
            if (skillsLevel.get(i) < requiredSkillLevel.get(i))
            {
                return false;
            }
        }
        return true;
    }
}
