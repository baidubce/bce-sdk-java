package com.baidubce.services.bmr.model;

/**
 * Created by xushiyue on 2015/11/23.
 */
public enum PeriodUnitType {
    MINUTE("/%s * * * *", "*/%s * * * *", "/([0-9]+) \\* \\* \\* \\*", 60 * 1000l, 5, Integer.MAX_VALUE),
    HOUR("* /%s * * *", "* */%s * * *", "\\* /([0-9]+) \\* \\* \\*", 60 * 60 * 1000l, 1, Integer.MAX_VALUE),
    DAY("* * /%s * *", "* * */%s * *", "\\* \\* /([0-9]+) \\* \\*", 24 * 60 * 60 * 1000l, 1, Integer.MAX_VALUE);

    private String format;

    private String bmrFormat;

    private String pattern;

    private Long unitInMs;

    private int maxValue;

    private int minValue;

    private PeriodUnitType(String format, String bmrFormat, String Pattern, Long unitInMs, int minValue, int maxValue) {
        this.format = format;
        this.bmrFormat = bmrFormat;
        this.pattern = Pattern;
        this.unitInMs = unitInMs;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public String getFormat() {
        return format;
    }

    public String getBmrFormat() {
        return bmrFormat;
    }

    public String getPattern() {
        return pattern;
    }

    public Long getUnitInMs() {
        return unitInMs;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getMinValue() {
        return minValue;
    }
}
