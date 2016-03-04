package com.baidubce.services.moladb.model;

import java.util.List;
import com.baidubce.model.AbstractBceResponse;

/**
 * Represents the output of a ListTables operation.
 */
public class ListTablesResponse extends AbstractBceResponse {
    private List<String> tableNames;

    /**
     * Constructs a new ListTablesResponse object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public ListTablesResponse() {
    }

    /**
     * Get the names of all tables got from server side.
     * 
     * @return The names of all tables got from server side, the type is in List<String>.
     */
    public List<String> getTableNames() {
        return this.tableNames;
    }

    
    /**
     * Set the names of all tables got from server side.
     * 
     * @param names The names of all tables got from server side, the type is in ArrayList<String>.
     */
    public void setTableNames(List<String> names) {
        this.tableNames = names;
    }
}
