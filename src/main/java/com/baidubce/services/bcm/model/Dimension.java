package com.baidubce.services.bcm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * the dimension for metric.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dimension {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Dimension withName(String name) {
        this.name = name;
        return this;
    }

    public Dimension withValue(String value) {
        this.value = value;
        return this;
    }
}
