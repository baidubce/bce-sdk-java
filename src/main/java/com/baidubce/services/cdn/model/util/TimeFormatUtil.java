package com.baidubce.services.cdn.model.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeFormatUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TimeFormatUtil.class);
    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:SS'Z'";

    /**
     * convert timestamp to time
     * @param timestamp
     * @param format
     * @param isSecond
     * @return result
     */
    public static String formatToStr(long timestamp, String format, boolean isSecond) {
        long ts = 0;
        if (isSecond) {
            ts = timestamp * 1000;
        } else {
            ts = timestamp;
        }
        DateTime dateTime = new DateTime(ts);
        String result = null;
        try {
            format = format == null ? FORMAT : format;
            result = dateTime.toString(format);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
        return result;
    }

    /**
     * convert time to timestamp
     * @param timeStr
     * @param format
     * @param isSecond
     * @return result
     */
    public static long formatToTs(String timeStr, String format, boolean isSecond) {
        long ret = 0;
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        DateTime dt = DateTime.parse(timeStr, formatter);
        long ts = dt.getMillis();

        if (isSecond) {
            ret = ts / 1000;
        } else {
            ret = ts;
        }

        return ret;
    }

    public static String formatTimeStr(String timeStr, String inputFormat,
            String outputFormat) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(inputFormat);
        DateTime dt = DateTime.parse(timeStr, formatter);
        long ts = dt.getMillis();

        DateTime dateTime = new DateTime(ts);
        String result = null;
        try {
            result = dateTime.toString(outputFormat);
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
        return result;
    }
}