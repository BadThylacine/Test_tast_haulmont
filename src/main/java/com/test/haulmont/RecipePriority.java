package com.test.haulmont;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Converter;


public enum RecipePriority {
    NORMAL("N"),
    CITO("C"),
    STATIM("S");
    private String priority;


    RecipePriority(String priority) {
        this.priority = priority;
    }

    @JsonValue
    public String getPriority() {
        return priority;
    }

    @JsonCreator
    public static RecipePriority of(String s) throws Exception {
        if(s == null){
            return null;
        }

        for(RecipePriority recipePriority: RecipePriority.values()){
            if(s.equals(recipePriority.priority)){
                return recipePriority;
            }
        }

        throw new EnumException("This symbol is not in our Enum");
    }

}
