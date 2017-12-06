package com.baidubce.services.tsdb.model;

/**
 * Field filter. Support filter by value in query.
 *
 * @author Zhu Hongwei(zhuhongwei@baidu.com)
 */
public class FieldFilter {

    /**
     * The field name for field filter.
     */
    private String field;

    /**
     * Optional.
     * The value filter. It contains two parts. The first part is a operation such as <, <=, =, !=, > and >=.
     * The second part is a value which can be a long type number, a double type number, a string surrounding by
     * single quotations or a tag key.
     * Support <, <=, =, !=, > and >= operations.
     * Example: "= 111", "> 1.1", "!= 'abc'" or "> tag1".
     */
    private String value;

    public FieldFilter() {
    }

    public FieldFilter(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public FieldFilter(String field, ValueFilter valueFilter) {
        this.field = field;
        this.value = valueFilter.getOperation() + " " + valueFilter.getValue();
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Set field for field filter.
     *
     * @param field The field for field filter
     * @return FieldFilter
     */
    public FieldFilter withField(String field) {
        this.field = field;
        return this;
    }

    /**
     * Set value for field filter.
     *
     * @param value The value for field filter
     * @return FieldFilter
     */
    public FieldFilter withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Set value for field filter.
     *
     * @param value The value for field filter.
     * @return Filters
     */
    public FieldFilter withValue(ValueFilter value) {
        this.value = value.getOperation() + " " + value.getValue();
        return this;
    }

}
