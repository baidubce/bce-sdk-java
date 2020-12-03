package com.baidubce.services.vpn.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Preconditions {

    public static void checkStrIsBlank(String str, String field) {
        if (StringUtils.isBlank(str)) {
            throw new NullPointerException(field + " should not be null or empty");
        }
    }

    public static void checkListIsBlank(List list, String field) {
        if (list == null || list.size() == 0) {
            throw new NullPointerException(field + " should not be null or empty");
        }
    }

}
