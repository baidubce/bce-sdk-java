package com.baidubce.services.scs;

import com.baidubce.BceClientException;
import com.baidubce.services.scs.model.ScsBilling;
import com.baidubce.util.Validate;
import org.apache.commons.lang.StringUtils;

/**
 * Scs exception
 */
public class ScsArgumentUtil {

    private ScsArgumentUtil() {

    }

    static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";

    static final String REQUEST_STRING_EMPTY_ERROR_MESSAGE = "request %s should not be null or empty string";

    static final String OBJECT_NULL_ERROR_MESSAGE = "%s should not be null";

    static void checkString(String value, String keyword) {
        Validate.checkStringNotEmpty(value, String.format(REQUEST_STRING_EMPTY_ERROR_MESSAGE, keyword));
    }

    static void checkNull(Object value, String keyword) {
        Validate.checkNotNull(value, String.format(OBJECT_NULL_ERROR_MESSAGE, keyword));
    }

    static void checkBilling(ScsBilling billing) {
        if (billing != null && "Prepaid".equalsIgnoreCase(billing.getPaymentTiming()) &&
                billing.getReservation() == null) {
            throw new BceClientException("Reservation can not be null when billing is prepaid payment");
        }
    }

    static void checkClusterName(String clusterName) {
        String pattern = "^[a-zA-Z\\u4e00-\\u9fa5]([\\u4e00-\\u9fa5]|\\w|-|\\.|\\/){0,63}$";
        Validate.checkPattern(clusterName, pattern, "Cluster name is illegal");
    }

    static void checkClusterPort(int port) {
        if (port <= 1025 || port == 22222 || port >= 65535) {
            throw new IllegalArgumentException("Port values are as follows: 1025 < port <22222 ，22222 < port < 65535");
        }
    }

    static void checkPurchaseCount(int purchaseCount) {
        if (purchaseCount > 10 || purchaseCount < 1) {
            throw new IllegalArgumentException("Purchase count value is as follows: 1 <= purchaseCount <=10");
        }
    }

    static void checkAutoRenewTimeUnit(String autoRenewTimeUnit) {
        if (StringUtils.isNotEmpty(autoRenewTimeUnit) &&
                !"month".equals(autoRenewTimeUnit) &&
                !"year".equals(autoRenewTimeUnit)) {
            throw new IllegalArgumentException("AutoRenewTimeUnit is illegal,please set month or year");
        }
    }

    static void checkAutoRenewTime(Integer autoRenewTime, String autoRenewTimeUnit) {
        if (autoRenewTime == null) {
            return;
        }
        if (StringUtils.isEmpty(autoRenewTimeUnit) && autoRenewTime > 0) {
            throw new IllegalArgumentException("Please set autoRenewTimeUnit and autoRenewTime together");
        }
        if ("month".equals(autoRenewTimeUnit) && (autoRenewTime < 1 || autoRenewTime > 9)) {
            throw new IllegalArgumentException("AutoRenewTime value is as follow: 1 <= autoRenewTime <= 9,when " +
                    "autoRenewTimeUnit is month");
        }
        if ("year".equals(autoRenewTimeUnit) && (autoRenewTime < 1 || autoRenewTime > 3)) {
            throw new IllegalArgumentException("AutoRenewTime value is as follow: 1 <= autoRenewTime <= 3,when " +
                    "autoRenewTimeUnit is year");
        }
    }

    static void checkDbIndex(Integer dbIndex) {
        if (dbIndex != null) {
            if (dbIndex < 0 || dbIndex > 255) {
                throw new IllegalArgumentException("dbIndex should range in [0,255]");
            }
        }
    }

    static void checkPage(Integer pageNow, Integer pageSize) {
        if (pageNow != null && pageNow < 0) {
            throw new IllegalArgumentException("pageNow is invalid");
        }
        if (pageSize != null && pageSize > 1000) {
            throw new IllegalArgumentException("pageSize is invalid");
        }
    }

    public static void checkClientAuth(String clientAuth) {
        if (clientAuth.length() < 8 || clientAuth.length() > 16) {
            throw new BceClientException("clientAuth length error");
        }
        if (StringUtils.isNumeric(clientAuth) || clientAuth.matches("[a-zA-Z]+")) {
            throw new BceClientException("clientAuth too simple");
        }
    }
}
