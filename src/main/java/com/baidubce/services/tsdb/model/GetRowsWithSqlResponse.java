package com.baidubce.services.tsdb.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response of getting rows.
 */
public class GetRowsWithSqlResponse extends AbstractBceResponse {

    /**
     * The columns.
     */
    private List<Column> columns;

    /**
     * The rows of data.
     */
    private List<List<Object>> rows;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

}
