/*
 * Copyright (c) 2020 Baidu. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.auth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.BceConstants;
import com.baidubce.http.Headers;
import com.baidubce.internal.BaseRequest;
import com.baidubce.internal.InternalRequest;

/**
 * The V1 implementation of Signature verification with the BCE signing protocol.
 *
 * @author chenjiayi05
 * @date 2020/04/28
 */
public class Signature {
    private static final Logger LOGGER = LoggerFactory.getLogger(Signature.class);

    private static final String AUTH_PREFIX = "bce-auth-v1";

    /**
     * verify the signature with default auth header
     *
     * @param baseRequest origin request info
     * @param credentials identity
     * @return result
     */
    public boolean verify(BaseRequest baseRequest, BceCredentials credentials) {
        return verify(baseRequest, credentials, Headers.AUTHORIZATION);
    }

    /**
     * verify the signature
     *
     * @param baseRequest origin request info
     * @param credentials identity
     * @param authHeader  auth header
     * @return result
     */
    public boolean verify(BaseRequest baseRequest, BceCredentials credentials, String authHeader) {
        if (baseRequest == null || credentials == null || authHeader == null) {
            return false;
        }
        Map<String, String> headers = baseRequest.getHeaders();
        if (headers == null || headers.get(Headers.HOST) == null && headers.get(Headers.HOST.toLowerCase()) == null) {
            return false;
        }

        // format: auth_prefix/ak/timestamp/expirationPeriodInSeconds/signedHeaders/signature
        String authString = headers.get(authHeader);
        if (authString == null) {
            // compatibility mode
            for (Map.Entry<String, String> header : headers.entrySet()) {
                if (header.getKey().equalsIgnoreCase(authHeader)) {
                    authString = header.getValue();
                    break;
                }
            }
        }
        if (authString == null) {
            return false;
        }

        // verify length
        String[] parts = authString.split("/", 6);
        if (parts.length != 6) {
            return false;
        }

        // verify auth_prefix
        if (!parts[0].equals(AUTH_PREFIX)) {
            return false;
        }

        // verify ak
        String accessKey = parts[1];
        if (!accessKey.equals(credentials.getAccessKeyId())) {
            return false;
        }

        // extract timestamp
        Date date;
        SimpleDateFormat format = new SimpleDateFormat(BceConstants.DEFAULT_DATETIME_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone(BceConstants.DEFAULT_TIME_ZONE));
        try {
            date = format.parse(parts[2]);
        } catch (ParseException e) {
            LOGGER.error("parse timestamp error.");
            return false;
        }
        SignOptions options = new SignOptions();
        options.setTimestamp(date);

        // extract expirationPeriodInSeconds
        options.setExpirationInSeconds(Integer.parseInt(parts[3]));

        // extract signedHeaders
        if (StringUtils.isEmpty(parts[4])) {
            // default signature
            options = SignOptions.DEFAULT;
            options.setTimestamp(date);
        } else {
            String[] signedHeaders = parts[4].split(";");
            options.setHeadersToSign(new TreeSet<String>(Arrays.asList(signedHeaders)));
        }

        // verify signature
        InternalRequest request = BaseRequest.toInternalRequest(baseRequest);
        BceV1Signer signer = new BceV1Signer();
        request.setSignOptions(options);
        signer.sign(request, credentials);
        String verifiedAuth = request.getHeaders().get(Headers.AUTHORIZATION);

        if (authString.split("/")[5].equals(verifiedAuth.split("/")[5])) {
            return true;
        } else {
            return false;
        }
    }
}
