/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.ses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceV1Signer;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.HttpUtils;

/**
 * This class is abstract. It contains all common methods which the {@link com.baidubce.services.ses.SesClient} class
 * should need.
 */
public abstract class SesClientSupport extends AbstractBceClient {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final Pattern DEFAULT_EMAIL_PATTERN = Pattern
            .compile("[-+.\\w]+@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    protected static final HttpResponseHandler[] SES_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    protected SesClientSupport(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        super(config, responseHandlers);
    }

    protected InternalRequest createRequest(String resourceKey, AbstractBceRequest bceRequest,
            HttpMethodName httpMethod, String...pathVariables) {
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(URL_PREFIX);

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        assertStringNotNullOrEmpty(resourceKey, "String resourceKey should not be null or empty");
        pathComponents.add(resourceKey);
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        // get a InternalRequest instance
        InternalRequest request =
                new InternalRequest(httpMethod, HttpUtils.appendUri(this.getEndpoint(),
                        pathComponents.toArray(new String[pathComponents.size()])));
        request.setCredentials(bceRequest.getRequestCredentials());

        // set headersToSign
        SignOptions options = SignOptions.DEFAULT;
        Set<String> headersToSign = new HashSet<String>();
        //headersToSign.add("content-type");
        headersToSign.add("host");
        headersToSign.add("x-bce-date");
        headersToSign.add("x-bce-request-id");
        options.setHeadersToSign(headersToSign);

        new BceV1Signer().sign(request, request.getCredentials(), options);

        return request;
    }

    protected InternalRequest fillRequestPayload(InternalRequest internalRequest, String strJson) {
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, SesConstant.CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));

        return internalRequest;
    }

    protected void assertStringNotNullOrEmpty(String parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (parameterValue.isEmpty() || parameterValue.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    protected void assertStringArrayNotNullOrEmpty(String[] parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (parameterValue.length <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    protected void assertListNotNullOrEmpty(List<?> parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (parameterValue.size() <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    protected String getBASE64EncoderStrFromInputStream(InputStream in) throws IOException {
        StringBuffer stringBuffer = new StringBuffer("");

        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size = 0;
        try {
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            byte[] bytes = out.toByteArray();
            stringBuffer.append(Base64.encodeBase64String(bytes));
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return stringBuffer.toString();
    }

    protected void checkIsEmail(String email) {
        Matcher matcher = DEFAULT_EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("illegal email.");
        }
    }

    protected void checkIsInteger(String str) {
        String regExp = "^-?\\d+$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(str);

        if (!matcher.find()) {
            throw new IllegalArgumentException("illegal int value.");
        }
    }
}