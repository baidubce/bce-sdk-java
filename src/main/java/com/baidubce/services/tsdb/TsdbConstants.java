package com.baidubce.services.tsdb;

/**
 * Represent the constants for tsdb cleint.
 *
 * @author linpengxiang
 */
public class TsdbConstants {
    // Aggregator name.
    public static final String AGGREGATOR_NAME_AVG = "Avg";
    public static final String AGGREGATOR_NAME_DEV = "Dev";
    public static final String AGGREGATOR_NAME_COUNT = "Count";
    public static final String AGGREGATOR_NAME_FIRST = "First";
    public static final String AGGREGATOR_NAME_LAST = "Last";
    public static final String AGGREGATOR_NAME_LEASTSQUARES = "LeastSquares";
    public static final String AGGREGATOR_NAME_MAX = "Max";
    public static final String AGGREGATOR_NAME_MIN = "Min";
    public static final String AGGREGATOR_NAME_PERCENTILE = "Percentile";
    public static final String AGGREGATOR_NAME_SUM = "Sum";
    public static final String AGGREGATOR_NAME_DIFF = "Diff";
    public static final String AGGREGATOR_NAME_DIV = "Div";
    public static final String AGGREGATOR_NAME_SCALE = "Scale";

    // GroupInfo name.
    public static final String GROUP_INFO_NAME_TYPE = "Type";
    public static final String GROUP_INFO_NAME_TAG = "Tag";
    public static final String GROUP_INFO_NAME_TIME = "Time";
    public static final String GROUP_INFO_NAME_VALUE = "Value";

    // GroupInfo type.
    public static final String GROUP_INFO_TYPE_NUMBER = "Number";
    public static final String GROUP_INFO_TYPE_STRING = "String";

    // GroupBy name.
    public static final String GROUP_BY_NAME_TAG = "Tag";
    public static final String GROUP_BY_NAME_TIME = "Time";
    public static final String GROUP_BY_NAME_VALUE = "Value";

    // Datapoint value type.
    public static String TYPE_BYTES = "Bytes";
    public static String TYPE_LONG = "Long";
    public static String TYPE_DOUBLE = "Double";
    public static String TYPE_STRING = "String";
}
