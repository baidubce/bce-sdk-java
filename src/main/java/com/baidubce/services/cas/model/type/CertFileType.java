/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

/**
 * Package format of the downloaded certificate file.
 */
public enum CertFileType {
    PEM("PEM"),
    PEM_APACHE("PEM_Apache"),
    PEM_NGINX("PEM_Nginx"),
    PEM_HAPROXY("PEM_Haproxy"),
    JKS_TOMCAT("JKS_TOMCAT8.5+"),
    JKS("JKS"),
    PKCS12("PKCS12"),
    ALL("ALL");

    private String value;

    CertFileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getSuffix() {
        switch (this) {
            case JKS:
                return ".jks";
            case PEM:
                return ".crt";
            case PKCS12:
                return ".p12";
            default:
                return "";
        }
    }
}
