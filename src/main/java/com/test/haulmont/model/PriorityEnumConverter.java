package com.test.haulmont.model;

import com.test.haulmont.RecipePriority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PriorityEnumConverter implements Converter<String, RecipePriority> {
    @Override
    public RecipePriority convert(String s) {
        if(s == null){
            return null;
        }
        return RecipePriority.valueOf(s);
    }
}
