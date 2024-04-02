package com.baidubce.services.oos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    private String name;
    private boolean required;
    private String type;
    private String label;
    private boolean multiple;
    private String description;
    private List<Object> options;
    private Object value;
    private Object defaultValue;
    private String unit;
    private List<Property> properties;
}
