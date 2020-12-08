package com.test.haulmont.model;

import com.test.haulmont.RecipePriority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PriorityConverter implements AttributeConverter<RecipePriority, String>{


    @Override
    public String convertToDatabaseColumn(RecipePriority recipePriority) {
        if(recipePriority == null){
            return null;
        }
        return recipePriority.getPriority();
    }

    @Override
    public RecipePriority convertToEntityAttribute(String s) {
        if(s == null){
            return null;
        }
        return Stream.of(RecipePriority.values()).filter(p -> p.getPriority().equals(s)).findFirst().get();
    }
}