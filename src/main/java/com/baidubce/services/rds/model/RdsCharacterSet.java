package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The character set name
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum RdsCharacterSet {
    UTF8("utf8"),
    GBK("gbk"),
    LATIN1("latin1"),
    // BIG5("BIG5"),
    // EUC_CN("EUC_CN"),
    UTF8MB4("utf8mb4"),
    CHINESE_PRC_CI_AS("Chinese_PRC_CI_AS"),
    CHINESE_PRC_CS_AS("Chinese_PRC_CS_AS"),
    SQL_LATIN1_GENERAL_CP1_CI_AS("SQL_Latin1_General_CP1_CI_AS"),
    SQL_LATIN1_GENERAL_CP1_CS_AS("SQL_Latin1_General_CP1_CS_AS"),
    CHINESE_PRC_BIN("Chinese_PRC_BIN");
    private final String value;

    RdsCharacterSet(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static RdsCharacterSet create(String value) {
        if (UTF8MB4.value.equalsIgnoreCase(value)) {
            return UTF8MB4;
        } else if (LATIN1.value.equalsIgnoreCase(value)) {
            return LATIN1;
        } else if (GBK.value.equalsIgnoreCase(value)) {
            return GBK;
        } else if (CHINESE_PRC_CI_AS.value.equalsIgnoreCase(value)) {
            return CHINESE_PRC_CI_AS;
        } else if (CHINESE_PRC_CS_AS.value.equalsIgnoreCase(value)) {
            return CHINESE_PRC_CS_AS;
        } else if (SQL_LATIN1_GENERAL_CP1_CI_AS.value.equalsIgnoreCase(value)) {
            return SQL_LATIN1_GENERAL_CP1_CI_AS;
        } else if (SQL_LATIN1_GENERAL_CP1_CS_AS.value.equalsIgnoreCase(value)) {
            return SQL_LATIN1_GENERAL_CP1_CS_AS;
        } else if (CHINESE_PRC_BIN.value.equalsIgnoreCase(value)) {
            return CHINESE_PRC_BIN;
        } else {
            return UTF8;
        }
    }
}
