package com.baidubce.services.iotalarm.model;

/**
 * Created by yuanyoujun on 2017/6/20.
 */
public class AlarmTrigger {
    public static final String COUNT = "COUNT:";

    public static String createCountType(int cnt) {
        if (cnt <= 0) {
            throw new IllegalArgumentException("cnt must > 0");
        }

        return COUNT + String.valueOf(cnt);
    }
}
