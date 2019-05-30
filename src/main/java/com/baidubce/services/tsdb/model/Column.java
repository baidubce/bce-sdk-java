package com.baidubce.services.tsdb.model;

/**
 * Represent the column of sql result.
 */
public class Column {

    /**
     * The column name.
     */
    private String name;

    public Column withName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
