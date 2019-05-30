/*
 * Copyright (c) 2018 Baidu.com, Inc. All Rights Reserved
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.tag;

import static com.baidubce.util.Validate.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.tag.model.CreateTagsRequest;
import com.baidubce.services.tag.model.DeleteTagsRequest;
import com.baidubce.services.tag.model.ListTagResourcesRequest;
import com.baidubce.services.tag.model.ListTagResourcesResponse;
import com.baidubce.services.tag.model.ListTagsRequest;
import com.baidubce.services.tag.model.ListTagsResponse;
import com.baidubce.services.tag.model.Tag;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the tag service.
 *
 */
public class TagClient extends AbstractBceClient {
    private static final String VERSION = "v1/tag";

    /**
     * Responsible for handling httpResponses from all tag service calls.
     */
    private static final HttpResponseHandler[] TAG_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on tag.
     */
    public TagClient() {
        this(new TagClientConfiguration());
    }

    /**
     * Constructs a new tag client using the client configuration to access tag.
     *
     * @param clientConfiguration The tag client configuration options controlling how this client
     *                            connects to tag service(e.g. proxy settings, retry counts, etc).
     */
    public TagClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, TAG_HANDLERS);
    }

    /**
     * Creates and initializes a new request object for the specified tag resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();

        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     * @param bceRequest The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson = null;
            try {
                requestJson = strJson.getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Unsupported encode.", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        }
    }

    /**
     * Create tags.
     *
     * Tag will be ignored if it is already created.
     * The default quota of tags is 200.
     * Receive an exception if the total amount of tags exceeds the quota.
     *
     * @param tags List of tag to be created.
     * @throws BceClientException
     */
    public void createTags(List<Tag> tags) {
        createTags(new CreateTagsRequest().withTags(tags));
    }

    /**
     * Create tags.
     *
     * Tag will be ignored if it is already created.
     * The default quota of tags is 200.
     * Receive an exception if the total amount of tags exceeds the quota.
     *
     * @param request The request containing all options for creating the tags.
     * @throws BceClientException
     */
    public void createTags(CreateTagsRequest request) {
        checkNotNull(request, "request for creating should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, "");
        internalRequest.addParameter("create", null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Listing all tags.
     *
     * @return Listing result
     * @throws BceClientException
     */
    public ListTagsResponse listTags() {
        return listTags(null, null);
    }

    /**
     * Listing tags.
     *
     * @param tagKey Filtered by tag key
     * @return Listing result
     * @throws BceClientException
     */
    public ListTagsResponse listTags(String tagKey) {
        return listTags(tagKey, null);
    }

     /**
      * Listing tags.
      *
      * @param tagKey Filtered by tag key, set value to null
      *              if you only want to filtered by tag value
      * @param tagValue Filtered by tag value
      * @return Listing result
      * @throws BceClientException
      */
    public ListTagsResponse listTags(String tagKey, String tagValue) {
        return listTags(new ListTagsRequest().withTagKey(tagKey).withTagValue(tagValue));
    }

    /**
     * Listing tags.
     *
     * @param request The request containing all options for listing the tags.
     * @return Listing result
     * @throws BceClientException
     */
    public ListTagsResponse listTags(ListTagsRequest request) {
        checkNotNull(request, "request for listing should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, "");
        if (!Strings.isNullOrEmpty(request.getTagKey())) {
            internalRequest.addParameter("tagKey", request.getTagKey());
        }
        if (request.getTagValue() != null) {
            internalRequest.addParameter("tagValue", request.getTagValue());
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ListTagsResponse.class);
    }

    /**
     * Delete tags.
     *
     * Tag will be ignored if it is not exists.
     *
     * @param tags List of tag to be deleted.
     * @throws BceClientException
     */
    public void deleteTags(List<Tag> tags) {
        deleteTags(new DeleteTagsRequest().withTags(tags));
    }

    /**
     * Delete tags.
     *
     * Tag will be ignored if it is not exists.
     *
     * @param request The request containing all options for deleting the tags.
     * @throws BceClientException
     */
    public void deleteTags(DeleteTagsRequest request) {
        checkNotNull(request, "request for deleting should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, "");
        internalRequest.addParameter("delete", null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Listing all tag resources.
     *
     * @return Listing result
     * @throws BceClientException
     */
    public ListTagResourcesResponse listTagResources() {
        return listTagResources(null, null);
    }

    /**
     * Listing tag resources.
     *
     * For other filter conditions(resource type, region),
     * use the {@link TagClient#listTagResources(ListTagResourcesRequest)} method
     *
     * @param tagKey Filtered by tag key, set value to null
     *              if you only want to filtered by tag value
     * @param tagValue Filtered by tag value
     * @return Listing result
     * @throws BceClientException
     */
    public ListTagResourcesResponse listTagResources(String tagKey, String tagValue) {
        return listTagResources(new ListTagResourcesRequest().withTagKey(tagKey).withTagValue(tagValue));
    }

    /**
     * Listing tag resources.
     *
     * @param request The request containing all options for listing the tag resources.
     * @return Listing result
     * @throws BceClientException
     */
    public ListTagResourcesResponse listTagResources(ListTagResourcesRequest request) {
        checkNotNull(request, "request for listing should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, "/tagResources");
        if (!Strings.isNullOrEmpty(request.getTagKey())) {
            internalRequest.addParameter("tagKey", request.getTagKey());
        }
        if (request.getTagValue() != null) {
            internalRequest.addParameter("tagValue", request.getTagValue());
        }
        if (!Strings.isNullOrEmpty(request.getResourceType())) {
            internalRequest.addParameter("resourceType", request.getResourceType());
        }
        if (!Strings.isNullOrEmpty(request.getRegion())) {
            internalRequest.addParameter("region", request.getRegion());
        }

        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ListTagResourcesResponse.class);
    }

}
