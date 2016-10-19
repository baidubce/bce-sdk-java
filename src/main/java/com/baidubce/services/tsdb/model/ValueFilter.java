package com.baidubce.services.tsdb.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Value filter. Support filter by value in query.
 *
 * @author linpengxiang
 */
public class ValueFilter {

    /**
     * Represent the operation for comparing.
     */
    private String operation;

    /**
     * Represent the value for comparing with.
     */
    private String value;

    private ValueFilter(String operation, String value) {
        this.operation = operation;
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public String getValue() {
        return value;
    }

    private static final String SINGLE_QUOTATION = "'";

    // Operations.
    public static final String EQUAL = "=";

    public static final String NOT_EQUAL = "!=";

    public static final String GREATER = ">";

    public static final String LESS = "<";

    public static final String GREATER_OR_EQUAL = ">=";

    public static final String LESS_OR_EQUAL = "<=";

    public static final List<String> LONG_DOUBLE_SUPPORTED_OPERATION = Arrays.asList(EQUAL, NOT_EQUAL, GREATER, LESS,
            GREATER_OR_EQUAL, LESS_OR_EQUAL);

    public static final List<String> STRING_SUPPORTED_OPERATION = Arrays.asList(EQUAL, NOT_EQUAL);

    /**
     * Create value filter for String type.
     *
     * @param operation Operation for comparing which only support = and !=.
     * @param value Value for comparing with.
     * @return ValueFilter
     */
    public static ValueFilter createValueFilter(String operation, String value) {
        checkArgument(STRING_SUPPORTED_OPERATION.contains(operation), "String value only support operations in "
                + STRING_SUPPORTED_OPERATION.toString());
        ValueFilter valueFilter = new ValueFilter(operation, SINGLE_QUOTATION + value + SINGLE_QUOTATION);
        return valueFilter;
    }

    /**
     * Create value filter for Double type.
     *
     * @param operation Operation for comparing which support =, !=, >, <, >= and <=.
     * @param value Value for comparing with.
     * @return ValueFilter
     */
    public static ValueFilter createValueFilter(String operation, Double value) {
        checkArgument(LONG_DOUBLE_SUPPORTED_OPERATION.contains(operation), "Double value only support operations in "
                + LONG_DOUBLE_SUPPORTED_OPERATION.toString());
        ValueFilter valueFilter = new ValueFilter(operation, String.valueOf(value));
        return valueFilter;
    }

    /**
     * Create value filter for Long type.
     *
     * @param operation Operation for comparing which support =, !=, >, <, >= and <=.
     * @param value Value for comparing with.
     * @return ValueFilter
     */
    public static ValueFilter createValueFilter(String operation, Long value) {
        checkArgument(LONG_DOUBLE_SUPPORTED_OPERATION.contains(operation), "Long value only support operations in "
                + LONG_DOUBLE_SUPPORTED_OPERATION.toString());
        ValueFilter valueFilter = new ValueFilter(operation, String.valueOf(value));
        return valueFilter;
    }
}
