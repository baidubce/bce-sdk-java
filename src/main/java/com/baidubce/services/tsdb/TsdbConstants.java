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
    public static final String AGGREGATOR_NAME_RATE = "Rate";
    public static final String AGGREGATOR_NAME_ADJACENT_UNIQUE = "AdjacentUnique";

    // GroupInfo name.
    @Deprecated
    public static final String GROUP_INFO_NAME_TYPE = "Type";
    public static final String GROUP_INFO_NAME_TAG = "Tag";
    @Deprecated
    public static final String GROUP_INFO_NAME_TIME = "Time";
    @Deprecated
    public static final String GROUP_INFO_NAME_VALUE = "Value";

    // GroupInfo type.
    @Deprecated
    public static final String GROUP_INFO_TYPE_NUMBER = "Number";
    @Deprecated
    public static final String GROUP_INFO_TYPE_STRING = "String";

    // GroupBy name.
    public static final String GROUP_BY_NAME_TAG = "Tag";
    @Deprecated
    public static final String GROUP_BY_NAME_TIME = "Time";
    @Deprecated
    public static final String GROUP_BY_NAME_VALUE = "Value";

    // Field type.
    public static final String FIELD_TYPE_NUMBER = "Number";
    public static final String FIELD_TYPE_STRING = "String";
    public static final String FIELD_TYPE_BYTES = "Bytes";

    // Datapoint value type.
    public static final String TYPE_BYTES = "Bytes";
    public static final String TYPE_LONG = "Long";
    public static final String TYPE_DOUBLE = "Double";
    public static final String TYPE_STRING = "String";

    // Query order.
    public static final String ORDER_ASC = "Asc";
    public static final String ORDER_DESC = "Desc";

    // Date format.
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    // Time unit.
    public static final String TIME_UNIT_MILLISECOND = "millisecond";
    public static final String TIME_UNIT_SECOND = "second";
    public static final String TIME_UNIT_MINUTE = "minute";
    public static final String TIME_UNIT_HOUR = "hour";
    public static final String TIME_UNIT_DAY = "day";
    public static final String TIME_UNIT_WEEK = "week";
    public static final String TIME_UNIT_MONTH = "month";
    public static final String TIME_UNIT_YEAR = "year";

    // Fill type.
    public static final String FILL_TYPE_LINEAR = "Linear";
    public static final String FILL_TYPE_PREVIOUS = "Previous";
    public static final String FILL_TYPE_FIXED = "Fixed";
}
