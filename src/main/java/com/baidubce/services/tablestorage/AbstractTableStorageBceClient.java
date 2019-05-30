/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.tablestorage;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.tablestorage.model.AbstractTableStorageRequest;
import com.baidubce.util.DateUtils;
import com.baidubce.util.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Provides the client for accessing the TableStorage api.
 */
public abstract class AbstractTableStorageBceClient extends AbstractBceClient {

    /**
     * Responsible for handling HttpResponse from all TableStorage service calls.
     */
    private static final HttpResponseHandler[] TABLE_STORAGE_HANDLERS =
            new HttpResponseHandler[] {
                new BceMetadataResponseHandler(),
                new BceErrorResponseHandler(),
                new TableStorageJsonResponseHandler()
            };

    /**
     * Constructs a new AbstractTableStorageBceClient client.
     */
    public AbstractTableStorageBceClient(BceClientConfiguration config, boolean isHttpAsyncPutEnabled) {
        super(config, TABLE_STORAGE_HANDLERS, isHttpAsyncPutEnabled);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific parameters to send.
     */
    protected InternalRequest createRequest(AbstractBceRequest bceRequest,
                                            HttpMethodName httpMethod,
                                            String...pathVariables) {
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(TableStorageConstants.URL_PREFIX);
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }
        InternalRequest request = new InternalRequest(httpMethod, HttpUtils.appendUri(this.getEndpoint(),
                pathComponents.toArray(new String[pathComponents.size()])));
        request.setCredentials(bceRequest.getRequestCredentials());

        request.addHeader(Headers.CONTENT_TYPE, TableStorageConstants.CONTENT_TYPE_JSON);
        request.addHeader(Headers.BCE_DATE, DateUtils.formatAlternateIso8601Date(new Date()));

        return request;
    }

    /**
     * Fill Header and Body in InternalRequest
     *
     * @param tableStorageRequest The original BCE request created by the user.
     * @param request A request object ready for callers to populate any additional headers or parameters, and execute.
     */
    protected void fillInHeadAndBody(AbstractTableStorageRequest tableStorageRequest, InternalRequest request) {
        try {
            byte[] json = tableStorageRequest.toJsonString().getBytes(DEFAULT_ENCODING);
            if (json.length > TableStorageConstants.MAX_REQUEST_BODY_SIZE) {
                throw new BceClientException("Request body's size should not exceed the limit "
                        + TableStorageConstants.MAX_REQUEST_BODY_SIZE + ". request="
                        + tableStorageRequest.getClass().getSimpleName() + ", tableName="
                        + tableStorageRequest.getTableName() + ", bodySize=" + json.length + ".");
            }
            request.setContent(RestartableInputStream.wrap(json));
            request.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Request body don't support " + DEFAULT_ENCODING + " encode.", e);
        }

    }

}

