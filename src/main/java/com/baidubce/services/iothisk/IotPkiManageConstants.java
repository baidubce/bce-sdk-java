package com.baidubce.services.iothisk;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * IotPkiManage Constants used by the IotPkiManage Java client.
 */
public class IotPkiManageConstants {

    public static final String ENDPOINT = "pkiiov.baidubce.com";
    public static final String VERSION = "v1";

    public static final String PKI = "pki";
    public static final String OCSP = "ocsp";
    public static final String CERT = "cert";
    public static final String CRL = "crl";
    public static final String CERT_GROUP = "certgroup";
    public static final String ROOT_CERT = "rootcert";
    public static final String CLIENT_CERT = "clientcert";
    public static final String SEVER_CERT = "servercert";
    public static final String JOB = "job";
    public static final String RENEW = "renew";
    public static final String CLIENT_TOKEN = "clientToken";
    public static final String QUERY = "query";
    public static final String ROOT_CERT_ID = "rootCertId";
    public static final String GROUP_ID = "groupId";
    public static final String GET_STATUS = "getstatus";

    public static final String CMD = "cmd";
    public static final String FORMAT = "format";
    public static final String PEM = "PEM";
    public static final String ISSUER = "issuer";
    public static final Map<String, String> CRL_PARAMS = ImmutableMap.of(CMD, CRL, FORMAT, PEM);
    public static final int MAX_CLIENT_TOKEN_LENGTH = 64;
    public static final int CERT_ID_LENGTH = 32;
    public static final int JOB_ID_LENGTH = 32;

    public static final String NULL_REQUEST = "Request should not be null.";
    public static final String EMPTY_CLIENT_TOKEN = "Client token can not be empty";
    public static final String DOWNLOAD_CERT_FAILED = "Download cert failed";
    public static final String DOWNLOAD_CRL_FAILED = "Download crl failed";
    public static final String INVALID_CERT_TYPE = "Invalid cert type";
    public static final String INVALID_CERT_ID = "Invalid cert ID";
    public static final String INVALID_JOB_ID = "Invalid job ID";
    public static final String INVALID_OCSP_REQUEST = "Invalid ocsp request";
    public static final String INVALID_ISSUER_DN = "Invalid issuer DN";
    public static final String TOO_LONG_CLIENT_TOKEN =
            String.format("Client token can not be longer than %d", MAX_CLIENT_TOKEN_LENGTH);
    public static final String EMPTY_ADDRESS = "Address should not be empty when created server cert.";


}
