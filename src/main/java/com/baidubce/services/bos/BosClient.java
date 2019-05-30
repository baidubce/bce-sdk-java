/*
 * Copyright 2014-2018 Baidu, Inc.
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
package com.baidubce.services.bos;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.BceServiceException.ErrorType;
import com.baidubce.auth.BceV1Signer;
import com.baidubce.auth.SignOptions;
import com.baidubce.auth.Signer;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.StatusCodes;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.*;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.User;
import com.baidubce.services.bos.model.*;
import com.baidubce.util.*;
import com.baidubce.util.HttpUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.google.common.collect.Lists;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Object Service.
 */
public class BosClient extends AbstractBceClient {
    private static Logger logger = LoggerFactory.getLogger(BosClient.class);

    /**
     * Responsible for handling httpResponses from all Bos service calls.
     */
    private static final HttpResponseHandler[] bos_handlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BosMetadataResponseHandler(), new BceErrorResponseHandler(),
            new BosObjectResponseHandler(), new BceJsonResponseHandler()};

    /**
     * Standard BOS storage class
     */
    public static final String STORAGE_CLASS_STANDARD = "STANDARD";

    /**
     * Infrequent access BOS storage class
     */
    public static final String STORAGE_CLASS_STANDARD_IA = "STANDARD_IA";

    /**
     * Cold access BOS storage class
     */
    public static final String STORAGE_CLASS_COLD = "COLD";

    /**
     * Generate signature with specified headers
     */
    private static final String[] HEADERS_TO_SIGN = {Headers.HOST};

    /**
     * Constructs a new client to invoke service methods on Bos.
     */
    public BosClient() {
        this(new BosClientConfiguration());
    }

    /**
     * Constructs a new Bos client using the client configuration to access Bos.
     *
     * @param clientConfiguration The bos client configuration options controlling how this client
     *                            connects to Bos (e.g. proxy settings, retry counts, etc).
     */
    public BosClient(BosClientConfiguration clientConfiguration) {
        super(clientConfiguration, bos_handlers, true);
    }

    /**
     * Gets the current owner of the Bos account that the authenticated sender of the request is using.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key ID that is registered with Bos.
     *
     * @return The account of the authenticated sender
     */
    public User getBosAccountOwner() {
        return this.getBosAccountOwner(new GetBosAccountOwnerRequest());
    }

    /**
     * Gets the current owner of the Bos account that the authenticated sender of the request is using.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key ID that is registered with Bos.
     *
     * @param request This request containing the credentials for getting the account of the authenticated sender.
     *
     * @return The account of the authenticated sender
     */
    public User getBosAccountOwner(GetBosAccountOwnerRequest request) {
        checkNotNull(request, "request should not be null.");

        return this.invokeHttpClient(this.createRequest(request, HttpMethodName.GET), ListBucketsResponse.class)
                .getOwner();
    }

    /**
     * Returns a list of all Bos buckets that the authenticated sender of the request owns.
     *
     * <p>
     * Users must authenticate with a valid BCE Access Key ID that is registered
     * with Bos. Anonymous requests cannot list buckets, and users cannot
     * list buckets that they did not create.
     *
     * @return All of the Bos buckets owned by the authenticated sender of the request.
     */
    public ListBucketsResponse listBuckets() {
        return this.listBuckets(new ListBucketsRequest());
    }

    /**
     * Returns a list of all Bos buckets that the authenticated sender of the request owns.
     *
     * <p>
     * Users must authenticate with a valid BCE Access Key ID that is registered
     * with Bos. Anonymous requests cannot list buckets, and users cannot
     * list buckets that they did not create.
     *
     * @param request The request containing all of the options related to the listing of buckets.
     *
     * @return All of the Bos buckets owned by the authenticated sender of the request.
     */
    public ListBucketsResponse listBuckets(ListBucketsRequest request) {
        checkNotNull(request, "request should not be null.");

        return this.invokeHttpClient(this.createRequest(request, HttpMethodName.GET), ListBucketsResponse.class);
    }

    /**
     * Creates a new Bos bucket with the specified name.
     *
     * @param bucketName The name of the bucket to create.
     *                   All buckets in Bos share a single namespace; ensure the bucket is given a unique name.
     *
     * @return The newly created bucket.
     */
    public CreateBucketResponse createBucket(String bucketName) {
        return this.createBucket(new CreateBucketRequest(bucketName));
    }

    /**
     * Creates a new Bos bucket with the specified name.
     *
     * @param request The request object containing all options for creating a Bos bucket.
     *
     * @return The newly created bucket.
     */
    public CreateBucketResponse createBucket(CreateBucketRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);
        this.setZeroContentLength(internalRequest);
        BosResponse response = this.invokeHttpClient(internalRequest, BosResponse.class);
        CreateBucketResponse result = new CreateBucketResponse();
        result.setName(request.getBucketName());
        result.setLocation(response.getMetadata().getLocation());
        return result;
    }

    /**
     * Checks if the specified bucket exists. Bos buckets are named in a
     * global namespace; use this method to determine if a specified bucket name
     * already exists, and therefore can't be used to create a new bucket.
     *
     * <p>
     * If invalid security credentials are used to execute this method, the
     * client is not able to distinguish between bucket permission errors and
     * invalid credential errors, and this method could return an incorrect
     * result.
     *
     * @param bucketName The name of the bucket to check.
     *
     * @return The value <code>true</code> if the specified bucket exists in Bos;
     * the value <code>false</code> if there is no bucket in Bos with that name.
     */
    public boolean doesBucketExist(String bucketName) {
        return this.doesBucketExist(new DoesBucketExistRequest(bucketName));
    }

    /**
     * Checks if the specified bucket exists. Bos buckets are named in a
     * global namespace; use this method to determine if a specified bucket name
     * already exists, and therefore can't be used to create a new bucket.
     *
     * <p>
     * If invalid security credentials are used to execute this method, the
     * client is not able to distinguish between bucket permission errors and
     * invalid credential errors, and this method could return an incorrect
     * result.
     *
     * @param request The request object containing all options for checking a Bos bucket.
     *
     * @return The value <code>true</code> if the specified bucket exists in Bos;
     * the value <code>false</code> if there is no bucket in Bos with that name.
     */
    public boolean doesBucketExist(DoesBucketExistRequest request) {
        checkNotNull(request, "request should not be null.");
        try {
            this.invokeHttpClient(this.createRequest(request, HttpMethodName.HEAD), BosResponse.class);
            return true;
        } catch (BceServiceException e) {
            // Forbidden means that the bucket exists.
            if (e.getStatusCode() == StatusCodes.FORBIDDEN) {
                return true;
            }
            if (e.getStatusCode() == StatusCodes.NOT_FOUND) {
                return false;
            }
            throw e;
        }
    }

    /**
     * Gets the ACL for the specified Bos bucket.
     *
     * <p>
     * Each bucket and object in Bos has an ACL that defines its access
     * control policy. When a request is made, Bos authenticates the
     * request using its standard authentication procedure and then checks the
     * ACL to verify the sender was granted access to the bucket or object. If
     * the sender is approved, the request proceeds. Otherwise, Bos
     * returns an error.
     *
     * @param bucketName The name of the bucket whose ACL is being retrieved.
     *
     * @return The <code>GetBuckeetAclResponse</code> for the specified Bos bucket.
     */
    public GetBucketAclResponse getBucketAcl(String bucketName) {
        return this.getBucketAcl(new GetBucketAclRequest(bucketName));
    }

    /**
     * Gets the ACL for the specified Bos bucket.
     *
     * <p>
     * Each bucket and object in Bos has an ACL that defines its access
     * control policy. When a request is made, Bos authenticates the
     * request using its standard authentication procedure and then checks the
     * ACL to verify the sender was granted access to the bucket or object. If
     * the sender is approved, the request proceeds. Otherwise, Bos
     * returns an error.
     *
     * @param request The request containing the name of the bucket whose ACL is being retrieved.
     *
     * @return The <code>GetBuckeetAclResponse</code> for the specified Bos bucket.
     */
    public GetBucketAclResponse getBucketAcl(GetBucketAclRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET);
        internalRequest.addParameter("acl", null);

        GetBucketAclResponse response = this.invokeHttpClient(internalRequest, GetBucketAclResponse.class);
        if (response.getVersion() > response.MAX_SUPPORTED_ACL_VERSION) {
            throw new BceClientException("Unsupported acl version.");
        }
        return response;
    }

    /**
     * Gets the Location for the specified Bos bucket.
     *
     * <p>
     * Each bucket and object in Bos has an Location that defines its location
     *
     * @param bucketName The name of the bucket whose Location is being retrieved.
     *
     * @return The <code>GetBuckeetLocationResponse</code> for the specified Bos bucket.
     */
    public GetBucketLocationResponse getBucketLocation(String bucketName) {
        return this.getBucketLocation(new GetBucketLocationRequest(bucketName));
    }

    /**
     * Gets the Location for the specified Bos bucket.
     *
     * <p>
     * Each bucket and object in Bos has an Location that defines its location
     *
     * @param request The request containing the name of the bucket whose Location is being retrieved.
     *
     * @return The <code>GetBuckeetLocationResponse</code> for the specified Bos bucket.
     */
    public GetBucketLocationResponse getBucketLocation(GetBucketLocationRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET);
        internalRequest.addParameter("location", null);

        GetBucketLocationResponse response = this.invokeHttpClient(internalRequest, GetBucketLocationResponse.class);

        return response;
    }

    /**
     * Sets the json style of bucket acl for the specified Bos bucket using one of
     * the pre-configured <code>CannedAccessControlLists</code>.
     *
     * <p>
     * A <code>json style of bucket acl</code>
     * provides a quick way to configure an object or bucket with commonly used
     * access control policies.
     *
     * @param bucketName The name of the bucket whose ACL is being set.
     * @param jsonAcl    The pre-configured <code>CannedAccessControlLists</code> to set for the specified bucket.
     */
    public void setBucketAcl(String bucketName, String jsonAcl) {
        this.setBucketAcl(new SetBucketAclRequest(bucketName, jsonAcl));
    }

    /**
     * Sets the CannedAccessControlList for the specified Bos bucket using one of
     * the pre-configured <code>CannedAccessControlLists</code>.
     *
     * <p>
     * A <code>CannedAccessControlList</code>
     * provides a quick way to configure an object or bucket with commonly used
     * access control policies.
     *
     * @param bucketName The name of the bucket whose ACL is being set.
     * @param acl        The pre-configured <code>CannedAccessControlLists</code> to set for the specified bucket.
     */
    public void setBucketAcl(String bucketName, CannedAccessControlList acl) {
        this.setBucketAcl(new SetBucketAclRequest(bucketName, acl));
    }

    /**
     * Sets the Acl for the specified Bos bucket.
     *
     * @param request The request object containing the bucket to modify and the ACL to set.
     */
    public void setBucketAcl(SetBucketAclRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);
        internalRequest.addParameter("acl", null);

        if (request.getCannedAcl() != null) {
            internalRequest.addHeader(Headers.BCE_ACL, request.getCannedAcl().toString());
            this.setZeroContentLength(internalRequest);
        } else if (request.getAccessControlList() != null) {
            byte[] json = null;
            List<Grant> grants = request.getAccessControlList();
            StringWriter writer = new StringWriter();
            try {
                JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
                jsonGenerator.writeStartObject();
                jsonGenerator.writeArrayFieldStart("accessControlList");
                for (Grant grant : grants) {
                    jsonGenerator.writeStartObject();
                    // set grant
                    jsonGenerator.writeArrayFieldStart("grantee");
                    for (Grantee grantee : grant.getGrantee()) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("id", grantee.getId());
                        jsonGenerator.writeEndObject();
                    }
                    jsonGenerator.writeEndArray();
                    // set permission
                    jsonGenerator.writeArrayFieldStart("permission");
                    for (Permission permission : grant.getPermission()) {
                        jsonGenerator.writeString(permission.toString());
                    }
                    jsonGenerator.writeEndArray();
                    // set resource
                    if (grant.getResource() != null) {
                        jsonGenerator.writeArrayFieldStart("resource");
                        for (String resource : grant.getResource()) {
                            jsonGenerator.writeString(resource);
                        }

                        jsonGenerator.writeEndArray();
                    }
                    // set notResource
                    if (grant.getNotResource() != null) {
                        jsonGenerator.writeArrayFieldStart("notResource");
                        for (String notResource : grant.getNotResource()) {
                            jsonGenerator.writeString(notResource);
                        }

                        jsonGenerator.writeEndArray();
                    }
                    // set condition
                    if (grant.getCondition() != null && grant.getCondition().getReferer() != null) {
                        if (grant.getCondition().getReferer().getStringLike() != null
                                || grant.getCondition().getReferer().getStringEquals() != null) {
                            jsonGenerator.writeObjectFieldStart("condition");
                            jsonGenerator.writeObjectFieldStart("referer");
                            // set refer stringLike
                            if (grant.getCondition().getReferer().getStringLike() != null) {
                                jsonGenerator.writeArrayFieldStart("stringLike");
                                for (String stringLike : grant.getCondition().getReferer().getStringLike()) {
                                    jsonGenerator.writeString(stringLike);
                                }
                                jsonGenerator.writeEndArray();
                            }
                            // set refer stringEquals
                            if (grant.getCondition().getReferer().getStringEquals() != null) {
                                jsonGenerator.writeArrayFieldStart("stringEquals");
                                for (String stringEquals : grant.getCondition().getReferer().getStringEquals()) {
                                    jsonGenerator.writeString(stringEquals);
                                }
                                jsonGenerator.writeEndArray();
                            }
                            jsonGenerator.writeEndObject();

                            if (grant.getCondition().getIpAddress() != null) {
                                jsonGenerator.writeArrayFieldStart("ipAddress");
                                for (String ipAddress : grant.getCondition().getIpAddress()) {
                                    jsonGenerator.writeString(ipAddress);
                                }
                                jsonGenerator.writeEndArray();
                            }
                            jsonGenerator.writeEndObject();
                        }
                    }
                    if (grant.getCondition() != null && grant.getCondition().getReferer() == null
                            && grant.getCondition().getIpAddress() != null) {
                        jsonGenerator.writeObjectFieldStart("condition");
                        jsonGenerator.writeArrayFieldStart("ipAddress");
                        for (String ipAddress : grant.getCondition().getIpAddress()) {
                            jsonGenerator.writeString(ipAddress);
                        }
                        jsonGenerator.writeEndArray();
                        jsonGenerator.writeEndObject();
                    }
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
            } catch (IOException e) {
                throw new BceClientException("Fail to generate json", e);
            }
            try {
                json = writer.toString().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else if (request.getJsonAcl() != null) {
            byte[] json = null;
            try {
                json = request.getJsonAcl().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else {
            checkNotNull(null, "request.acl should not be null.");
        }
        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Sets the Logging for the specified Bos bucket.
     *
     * @param request The request object containing the Logging to set into specified bucket.
     */
    public void setBucketLogging(SetBucketLoggingRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);
        internalRequest.addParameter("logging", null);

        if (request.getJsonPutBucketLogging() != null) {
            byte[] json = null;
            try {
                json = request.getJsonPutBucketLogging().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else {
            byte[] json = null;
            StringWriter writer = new StringWriter();
            try {
                JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("targetBucket", request.getTargetBucket());
                if (request.getTargetPrefix() != null) {
                    jsonGenerator.writeStringField("targetPrefix", request.getTargetPrefix());
                }
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
            } catch (IOException e) {
                throw new BceClientException("Fail to generate json", e);
            }
            try {
                json = writer.toString().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        }
        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Gets the Logging for the specified Bos bucket.
     *
     * @param request The request object containing the specified bucket to get bucket logging.
     */
    public GetBucketLoggingResponse getBucketLogging(GetBucketLoggingRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET);
        internalRequest.addParameter("logging", null);
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        GetBucketLoggingResponse response = this.invokeHttpClient(internalRequest, GetBucketLoggingResponse.class);
        return response;
    }

    /**
     * Delete the Logging for the specified Bos bucket.
     *
     * @param request The request object containing the specified bucket to delete bucket logging.
     */
    public void deleteBucketLogging(DeleteBucketLoggingRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE);
        internalRequest.addParameter("logging", null);
        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Sets the CORS for the specified Bos bucket.
     *
     * @param request The request object containing the CORS to set into specified bucket.
     */
    public void setBucketCors(SetBucketCorsRequest request) {

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);
        internalRequest.addParameter("cors", null);
        if (request.getJsonBucketCors() != null) {
            byte[] json = null;
            try {
                json = request.getJsonBucketCors().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else if (request.getCorsConfigurationsList() != null) {
            byte[] json = null;
            List<CorsConfiguration> corsConfigurations = request.getCorsConfigurationsList();
            StringWriter writer = new StringWriter();
            try {
                JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
                jsonGenerator.writeStartObject();
                jsonGenerator.writeArrayFieldStart("corsConfiguration");
                for (CorsConfiguration corsConfiguration : corsConfigurations) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeArrayFieldStart("allowedOrigins");
                    for (String allowOrigin : corsConfiguration.getAllowedOrigins()) {
                        jsonGenerator.writeString(allowOrigin);
                    }
                    jsonGenerator.writeEndArray();

                    jsonGenerator.writeArrayFieldStart("allowedMethods");
                    for (AllowedMethods allowedMethod : corsConfiguration.getAllowedMethods()) {
                        jsonGenerator.writeString(allowedMethod.toString());
                    }
                    jsonGenerator.writeEndArray();

                    jsonGenerator.writeArrayFieldStart("allowedHeaders");
                    if (corsConfiguration.getAllowedHeaders() != null) {
                        for (String allowHead : corsConfiguration.getAllowedHeaders()) {
                            jsonGenerator.writeString(allowHead);
                        }
                    }

                    jsonGenerator.writeEndArray();

                    jsonGenerator.writeArrayFieldStart("allowedExposeHeaders");
                    if (corsConfiguration.getAllowedExposeHeaders() != null) {
                        for (String allowedExposeHeader : corsConfiguration.getAllowedExposeHeaders()) {
                            jsonGenerator.writeString(allowedExposeHeader);
                        }
                    }
                    jsonGenerator.writeEndArray();
                    jsonGenerator.writeNumberField("maxAgeSeconds", corsConfiguration.getMaxAgeSeconds());
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
            } catch (IOException e) {
                throw new BceClientException("Fail to generate json", e);
            }
            try {
                json = writer.toString().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else {
            checkNotNull(request, "request should not be null.");
        }
        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Gets the CORS for the specified Bos bucket.
     *
     * @param request The request object containing the specified bucket to get bucket CORS.
     */
    public GetBucketCorsResponse getBucketCros(GetBucketCorsRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET);
        internalRequest.addParameter("cors", null);
        GetBucketCorsResponse response = this.invokeHttpClient(internalRequest, GetBucketCorsResponse.class);
        return response;
    }

    /**
     * Delete the CORS for the specified Bos bucket.
     *
     * @param request The request object containing the specified bucket to delete bucket CORS.
     */
    public void deleteBucketCors(DeleteBucketCorsRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE);
        internalRequest.addParameter("cors", null);
        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Sets the Lifecycle for the specified Bos bucket.
     *
     * @param request The request object containing the Lifecycle to set into specified bucket.
     */
    public void setBucketBucketLifecycle(SetBucketLifecycleRequest request) {

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);
        internalRequest.addParameter("lifecycle", null);
        if (request.getJsonBucketLifecycle() != null) {
            byte[] json = null;
            try {
                json = request.getJsonBucketLifecycle().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else if (request.getRuleList() != null) {
            byte[] json = null;
            List<Rule> rules = request.getRuleList();
            StringWriter writer = new StringWriter();
            try {
                JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
                jsonGenerator.writeStartObject();
                jsonGenerator.writeArrayFieldStart("rule");
                for (Rule rule : rules) {
                    jsonGenerator.writeStartObject();
                    // set id
                    jsonGenerator.writeStringField("id", rule.getId());
                    // set status
                    jsonGenerator.writeStringField("status", rule.getStatus());
                    // set resource
                    jsonGenerator.writeArrayFieldStart("resource");

                    for (String resource : rule.getResource()) {
                        jsonGenerator.writeString(resource);
                    }

                    jsonGenerator.writeEndArray();
                    // set condition
                    jsonGenerator.writeObjectFieldStart("condition");
                    jsonGenerator.writeObjectFieldStart("time");
                    jsonGenerator.writeStringField("dateGreaterThan",
                            rule.getCondition().getTime().getDateGreaterThan());
                    jsonGenerator.writeEndObject();
                    jsonGenerator.writeEndObject();
                    // set action
                    jsonGenerator.writeObjectFieldStart("action");
                    jsonGenerator.writeStringField("name", rule.getAction().getName());
                    if (rule.getAction().getStorageClass() != null) {
                        jsonGenerator.writeStringField("storageClass", rule.getAction().getStorageClass());
                    }
                    jsonGenerator.writeEndObject();
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
            } catch (IOException e) {
                throw new BceClientException("Fail to generate json", e);
            }
            try {
                json = writer.toString().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else {
            checkNotNull(request, "request should not be null.");
        }
        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Gets Lifecycle for the specified Bos bucket.
     *
     * @param request The request object containing the specified bucket to get bucket Lifecycle.
     */
    public GetBucketLifecycleResponse getBucketLifecycle(GetBucketLifecycleRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET);
        internalRequest.addParameter("lifecycle", null);
        GetBucketLifecycleResponse response = this.invokeHttpClient(internalRequest, GetBucketLifecycleResponse.class);
        return response;
    }

    /**
     * Delete the Lifecycle for the specified Bos bucket.
     *
     * @param request The request object containing the specified bucket to delete bucket Lifecycle.
     */
    public void deleteBucketLifecycle(DeleteBucketLifecycleRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE);
        internalRequest.addParameter("lifecycle", null);
        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Deletes the specified bucket. All objects in the bucket must be deleted before the bucket itself
     * can be deleted.
     *
     * <p>
     * Only the owner of a bucket can delete it, regardless of the bucket's access control policy.
     *
     * @param bucketName The name of the bucket to delete.
     */
    public void deleteBucket(String bucketName) {
        this.deleteBucket(new DeleteBucketRequest(bucketName));
    }

    /**
     * Deletes the specified bucket. All objects in the bucket must be deleted before the bucket itself
     * can be deleted.
     *
     * <p>
     * Only the owner of a bucket can delete it, regardless of the bucket's access control policy.
     *
     * @param request The request object containing all options for deleting a Bos bucket.
     */
    public void deleteBucket(DeleteBucketRequest request) {
        checkNotNull(request, "request should not be null.");

        this.invokeHttpClient(this.createRequest(request, HttpMethodName.DELETE), BosResponse.class);
    }

    /**
     * Returns a pre-signed URL for accessing a Bos resource.
     *
     * @param bucketName          The name of the bucket containing the desired object.
     * @param key                 The key in the specified bucket under which the desired object is stored.
     * @param expirationInSeconds The expiration after which the returned pre-signed URL will expire.
     *
     * @return A pre-signed URL which expires at the specified time, and can be
     * used to allow anyone to download the specified object from Bos,
     * without exposing the owner's Bce secret access key.
     */
    public URL generatePresignedUrl(String bucketName, String key, int expirationInSeconds) {
        return this.generatePresignedUrl(bucketName, key, expirationInSeconds, HttpMethodName.GET);
    }

    /**
     * Returns a pre-signed URL for accessing a Bos resource.
     *
     * @param bucketName          The name of the bucket containing the desired object.
     * @param key                 The key in the specified bucket under which the desired object is stored.
     * @param expirationInSeconds The expiration after which the returned pre-signed URL will expire.
     * @param method              The HTTP method verb to use for this URL
     *
     * @return A pre-signed URL which expires at the specified time, and can be
     * used to allow anyone to download the specified object from Bos,
     * without exposing the owner's Bce secret access key.
     */
    public URL generatePresignedUrl(String bucketName, String key, int expirationInSeconds, HttpMethodName method) {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key, method);
        request.setExpiration(expirationInSeconds);

        return this.generatePresignedUrl(request);
    }

    /**
     * Returns a pre-signed URL for accessing a Bos resource.
     *
     * @param request The request object containing all the options for generating a
     *                pre-signed URL (bucket name, key, expiration date, etc).
     *
     * @return A pre-signed URL which expires at the specified time, and can be
     * used to allow anyone to download the specified object from Bos,
     * without exposing the owner's Bce secret access key.
     */
    public URL generatePresignedUrl(GeneratePresignedUrlRequest request) {
        checkNotNull(request, "The request parameter must be specified when generating a pre-signed URL");

        HttpMethodName httpMethod = HttpMethodName.valueOf(request.getMethod().toString());

        // If the key starts with a slash character itself, the following method
        // will actually add another slash before the resource path to prevent
        // the HttpClient mistakenly treating the slash as a path delimiter.
        // For presigned request, we need to remember to remove this extra slash
        // before generating the URL.
        String bucketName = ((BosClientConfiguration) this.config).isCnameEnabled() ? null : request.getBucketName();
        InternalRequest internalRequest = new InternalRequest(httpMethod, HttpUtils
                .appendUri(this.getEndpoint(), URL_PREFIX, bucketName, request.getKey()));
        internalRequest.setCredentials(request.getRequestCredentials());
        SignOptions options = new SignOptions();
        options.setExpirationInSeconds(request.getExpiration());

        if (httpMethod != HttpMethodName.GET && httpMethod != HttpMethodName.HEAD) {
            options.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        }

        for (Entry<String, String> entry : request.getRequestHeaders().entrySet()) {
            if (entry.getValue() == null) {
                internalRequest.addHeader(entry.getKey(), "");
            } else {
                internalRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }

        for (Entry<String, String> entry : request.getRequestParameters().entrySet()) {
            if (entry.getValue() == null) {
                internalRequest.addParameter(entry.getKey(), "");
            } else {
                internalRequest.addParameter(entry.getKey(), entry.getValue());
            }
        }

        if (request.getContentType() != null) {
            internalRequest.addHeader(Headers.CONTENT_TYPE, request.getContentType());
        }

        if (request.getContentMd5() != null) {
            internalRequest.addHeader(Headers.CONTENT_MD5, request.getContentMd5());
        }

        addResponseHeaderParameters(internalRequest, request.getResponseHeaders());

        Signer signer = new BceV1Signer();
        signer.sign(internalRequest, this.config.getCredentials(), options);

        // Remove the leading slash (if any) in the resource-path
        return convertRequestToUrl(internalRequest);
    }

    /**
     * Returns ListObjectsResponse containing a list of summary information about the objects in the specified buckets.
     * List results are <i>always</i> returned in lexicographic (alphabetical) order.
     *
     * @param bucketName The name of the Bos bucket to list.
     *
     * @return ListObjectsResponse containing a listing of the objects in the specified bucket, along with any
     * other associated information, such as common prefixes (if a delimiter was specified), the original
     * request parameters, etc.
     */
    public ListObjectsResponse listObjects(String bucketName) {
        return this.listObjects(new ListObjectsRequest(bucketName));
    }

    /**
     * Returns ListObjectsResponse containing a list of summary information about the objects in the specified buckets.
     * List results are <i>always</i> returned in lexicographic (alphabetical) order.
     *
     * @param bucketName The name of the Bos bucket to list.
     * @param prefix     An optional parameter restricting the response to keys beginning with the specified prefix.
     *                   Use prefixes to separate a bucket into different sets of keys, similar to how a file system
     *                   organizes files into directories.
     *
     * @return ListObjectsResponse containing a listing of the objects in the specified bucket, along with any
     * other associated information, such as common prefixes (if a delimiter was specified), the original
     * request parameters, etc.
     */
    public ListObjectsResponse listObjects(String bucketName, String prefix) {
        return this.listObjects(new ListObjectsRequest(bucketName, prefix));
    }

    /**
     * Returns ListObjectsResponse containing a list of summary information about the objects in the specified buckets.
     * List results are <i>always</i> returned in lexicographic (alphabetical) order.
     *
     * @param request The request object containing all options for listing the objects in a specified bucket.
     *
     * @return ListObjectsResponse containing a listing of the objects in the specified bucket, along with any
     * other associated information, such as common prefixes (if a delimiter was specified), the original
     * request parameters, etc.
     */
    public ListObjectsResponse listObjects(ListObjectsRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET);
        if (request.getPrefix() != null) {
            internalRequest.addParameter("prefix", request.getPrefix());
        }
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getDelimiter() != null) {
            internalRequest.addParameter("delimiter", request.getDelimiter());
        }
        if (request.getMaxKeys() >= 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }

        ListObjectsResponse response = this.invokeHttpClient(internalRequest, ListObjectsResponse.class);

        response.setBucketName(request.getBucketName());
        List<BosObjectSummary> contents = response.getContents();
        for (BosObjectSummary object : contents) {
            object.setBucketName(request.getBucketName());
        }

        return response;
    }

    /**
     * Provides an easy way to continue a truncated object listing and retrieve the next page of results.
     *
     * @param previousResponse The previous truncated <code>ListObjectsResponse</code>. If a non-truncated
     *                         <code>ListObjectsResponse</code> is passed in, an empty <code>ListObjectsResponse</code>
     *                         is returned without ever contacting Bos.
     *
     * @return The next set of <code>ListObjectsResponse</code> results, beginning immediately
     * after the last result in the specified previous <code>ListObjectsResponse</code>.
     */
    public ListObjectsResponse listNextBatchOfObjects(ListObjectsResponse previousResponse) {
        checkNotNull(previousResponse, "previousResponse should not be null.");

        if (!previousResponse.isTruncated()) {
            ListObjectsResponse emptyResponse = new ListObjectsResponse();
            emptyResponse.setBucketName(previousResponse.getBucketName());
            emptyResponse.setDelimiter(previousResponse.getDelimiter());
            emptyResponse.setMarker(previousResponse.getNextMarker());
            emptyResponse.setMaxKeys(previousResponse.getMaxKeys());
            emptyResponse.setPrefix(previousResponse.getPrefix());
            emptyResponse.setTruncated(false);
            return emptyResponse;
        }

        return this.listObjects(new ListObjectsRequest(previousResponse.getBucketName())
                .withPrefix(previousResponse.getPrefix())
                .withMarker(previousResponse.getNextMarker())
                .withDelimiter(previousResponse.getDelimiter())
                .withMaxKeys(previousResponse.getMaxKeys()));
    }

    /**
     * Gets the object stored in Bos under the specified bucket and key.
     *
     * @param bucketName The name of the bucket containing the desired object.
     * @param key        The key under which the desired object is stored.
     *
     * @return The object stored in Bos in the specified bucket and key.
     */
    public BosObject getObject(String bucketName, String key) {
        return this.getObject(new GetObjectRequest(bucketName, key));
    }

    /**
     * Gets the object metadata for the object stored in Bos under the specified bucket and key,
     * and saves the object contents to the specified file.
     * Returns <code>null</code> if the specified constraints weren't met.
     *
     * @param bucketName      The name of the bucket containing the desired object.
     * @param key             The key under which the desired object is stored.
     * @param destinationFile Indicates the file (which might already exist)
     *                        where to save the object content being downloading from Bos.
     *
     * @return All Bos object metadata for the specified object.
     * Returns <code>null</code> if constraints were specified but not met.
     */
    public ObjectMetadata getObject(String bucketName, String key, File destinationFile) {
        return this.getObject(new GetObjectRequest(bucketName, key), destinationFile);
    }

    /**
     * Gets the object stored in Bos under the specified bucket and key.
     *
     * @param request The request object containing all the options on how to download the object.
     *
     * @return The object stored in Bos in the specified bucket and key.
     */
    public BosObject getObject(GetObjectRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET);
        long[] range = request.getRange();
        if (range != null) {
            internalRequest.addHeader(Headers.RANGE, "bytes=" + range[0] + "-" + range[1]);
        }

        GetObjectResponse response = this.invokeHttpClient(internalRequest, GetObjectResponse.class);

        BosObject bosObject = response.getObject();
        bosObject.setBucketName(request.getBucketName());
        bosObject.setKey(request.getKey());

        return bosObject;
    }

    /**
     * Gets the object metadata for the object stored in Bos under the specified bucket and key,
     * and saves the object contents to the specified file.
     * Returns <code>null</code> if the specified constraints weren't met.
     *
     * @param request         The request object containing all the options on how to download the Bos object content.
     * @param destinationFile Indicates the file (which might already exist) where to save the object
     *                        content being downloading from Bos.
     *
     * @return All Bos object metadata for the specified object.
     * Returns <code>null</code> if constraints were specified but not met.
     */
    public ObjectMetadata getObject(GetObjectRequest request, File destinationFile) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(destinationFile, "destinationFile should not be null.");

        BosObject bosObject = this.getObject(request);
        this.downloadObjectToFile(bosObject, destinationFile, request.getRange() == null);
        return bosObject.getObjectMetadata();
    }

    /**
     * Gets the object content stored in Bos under the specified bucket and key.
     *
     * @param bucketName The name of the bucket containing the desired object.
     * @param key        The key under which the desired object is stored.
     *
     * @return The object content stored in Bos in the specified bucket and key.
     */
    public byte[] getObjectContent(String bucketName, String key) {
        return this.getObjectContent(new GetObjectRequest(bucketName, key));
    }

    /**
     * Gets the object content stored in Bos under the specified bucket and key.
     *
     * @param request The request object containing all the options on how to download the Bos object content.
     *
     * @return The object content stored in Bos in the specified bucket and key.
     */
    public byte[] getObjectContent(GetObjectRequest request) {
        BosObjectInputStream content = this.getObject(request).getObjectContent();
        try {
            return IOUtils.toByteArray(content);
        } catch (IOException e) {
            try {
                content.close();
            } catch (IOException e1) {
                // ignore, throw e not e1.
            }
            throw new BceClientException("Fail read object content", e);
        } finally {
            try {
                content.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * Checks if the specified object exists. use this method to determine
     * if a specified object already exists
     *
     * <p>
     * If invalid security credentials are used to execute this method, the
     * client is not able to distinguish between bucket permission errors and
     * invalid credential errors, and this method could return an incorrect
     * result.
     *
     * @param bucketName The name of the bucket
     * @param key        The name of the object to check.
     *
     * @return The value <code>true</code> if the specified object exists in Bos;
     * the value <code>false</code> if there is no object in Bos with that name.
     */
    public boolean doesObjectExist(String bucketName, String key) {
        try {
            getObjectMetadata(bucketName, key);
            return true;
        } catch (BceServiceException e) {
            if (e.getStatusCode() == StatusCodes.NOT_FOUND) {
                return false;
            }
            throw e;
        }
    }

    /**
     * Gets the metadata for the specified Bos object without actually fetching the object itself.
     * This is useful in obtaining only the object metadata, and avoids wasting bandwidth on fetching
     * the object data.
     *
     * <p>
     * The object metadata contains information such as content type, content disposition, etc.,
     * as well as custom user metadata that can be associated with an object in Bos.
     *
     * @param bucketName The name of the bucket containing the object's whose metadata is being retrieved.
     * @param key        The key of the object whose metadata is being retrieved.
     *
     * @return All Bos object metadata for the specified object.
     */
    public ObjectMetadata getObjectMetadata(String bucketName, String key) {
        return this.getObjectMetadata(new GetObjectMetadataRequest(bucketName, key));
    }

    /**
     * Gets the metadata for the specified Bos object without actually fetching the object itself.
     * This is useful in obtaining only the object metadata, and avoids wasting bandwidth on fetching
     * the object data.
     *
     * <p>
     * The object metadata contains information such as content type, content disposition, etc.,
     * as well as custom user metadata that can be associated with an object in Bos.
     *
     * @param request The request object specifying the bucket, key whose metadata is being retrieved.
     *
     * @return All Bos object metadata for the specified object.
     */
    public ObjectMetadata getObjectMetadata(GetObjectMetadataRequest request) {
        checkNotNull(request, "request should not be null.");

        return this.invokeHttpClient(this.createRequest(request, HttpMethodName.HEAD), GetObjectResponse.class)
                .getObject().getObjectMetadata();
    }

    /**
     * Uploads the specified file to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param file       The file containing the data to be uploaded to Bos.
     *
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public PutObjectResponse putObject(String bucketName, String key, File file) {
        return this.putObject(new PutObjectRequest(bucketName, key, file));
    }

    /**
     * Uploads the specified file and object metadata to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param file       The file containing the data to be uploaded to Bos.
     * @param metadata   Additional metadata instructing Bos how to handle the uploaded data
     *                   (e.g. custom user metadata, hooks for specifying content type, etc.).
     *
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public PutObjectResponse putObject(String bucketName, String key, File file, ObjectMetadata metadata) {
        return this.putObject(new PutObjectRequest(bucketName, key, file, metadata));
    }

    /**
     * Uploads the specified string to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param value      The string containing the value to be uploaded to Bos.
     *
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public PutObjectResponse putObject(String bucketName, String key, String value) {
        try {
            return this.putObject(bucketName, key, value.getBytes(DEFAULT_ENCODING), new ObjectMetadata());
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes.", e);
        }
    }

    /**
     * Uploads the specified string and object metadata to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param value      The string containing the value to be uploaded to Bos.
     * @param metadata   Additional metadata instructing Bos how to handle the uploaded data
     *                   (e.g. custom user metadata, hooks for specifying content type, etc.).
     *
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public PutObjectResponse putObject(String bucketName, String key, String value, ObjectMetadata metadata) {
        try {
            return this.putObject(bucketName, key, value.getBytes(DEFAULT_ENCODING), metadata);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes.", e);
        }
    }

    /**
     * Uploads the specified bytes to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param value      The bytes containing the value to be uploaded to Bos.
     *
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public PutObjectResponse putObject(String bucketName, String key, byte[] value) {
        return this.putObject(bucketName, key, value, new ObjectMetadata());
    }

    /**
     * Uploads the specified bytes and object metadata to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param value      The bytes containing the value to be uploaded to Bos.
     * @param metadata   Additional metadata instructing Bos how to handle the uploaded data
     *                   (e.g. custom user metadata, hooks for specifying content type, etc.).
     *
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public PutObjectResponse putObject(String bucketName, String key, byte[] value, ObjectMetadata metadata) {
        if (metadata.getContentLength() == -1) {
            metadata.setContentLength(value.length);
        }
        return this.putObject(new PutObjectRequest(bucketName, key, RestartableInputStream.wrap(value), metadata));
    }

    /**
     * Uploads the specified input stream to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param input      The input stream containing the value to be uploaded to Bos.
     *
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public PutObjectResponse putObject(String bucketName, String key, InputStream input) {
        return this.putObject(new PutObjectRequest(bucketName, key, input));
    }

    /**
     * Uploads the specified input stream and object metadata to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param input      The input stream containing the value to be uploaded to Bos.
     * @param metadata   Additional metadata instructing Bos how to handle the uploaded data
     *                   (e.g. custom user metadata, hooks for specifying content type, etc.).
     *
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public PutObjectResponse putObject(String bucketName, String key, InputStream input, ObjectMetadata metadata) {
        return this.putObject(new PutObjectRequest(bucketName, key, input, metadata));
    }

    /**
     * Uploads a new object to the specified Bos bucket. The <code>PutObjectRequest</code> contains all the
     * details of the request, including the bucket to upload to, the key the object will be uploaded under,
     * and the file or input stream containing the data to upload.
     *
     * @param request The request object containing all the parameters to upload a new object to Bos.
     *
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public PutObjectResponse putObject(PutObjectRequest request) {
        checkNotNull(request, "request should not be null.");
        assertStringNotNullOrEmpty(request.getKey(), "object key should not be null or empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);

        BosResponse response = uploadObject(request, internalRequest);
        PutObjectResponse result = new PutObjectResponse();
        result.setETag(response.getMetadata().getETag());

        return result;
    }

    /**
     * Copies a source object to a new destination in Bos.
     *
     * @param sourceBucketName      The name of the bucket containing the source object to copy.
     * @param sourceKey             The key in the source bucket under which the source object is stored.
     * @param destinationBucketName The name of the bucket in which the new object will be created.
     *                              This can be the same name as the source bucket's.
     * @param destinationKey        The key in the destination bucket under which the new object will be created.
     *
     * @return A CopyObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public CopyObjectResponse copyObject(String sourceBucketName, String sourceKey, String destinationBucketName,
                                         String destinationKey) {
        return this.copyObject(new CopyObjectRequest(sourceBucketName, sourceKey, destinationBucketName,
                destinationKey));
    }

    /**
     * Copies a source object to a new destination in Bos.
     *
     * @param request The request object containing all the options for copying an Bos object.
     *
     * @return A CopyObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public CopyObjectResponse copyObject(CopyObjectRequest request) {
        checkNotNull(request, "request should not be null.");
        assertStringNotNullOrEmpty(request.getSourceKey(), "object key should not be null or empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);

        String copySourceHeader = "/" + request.getSourceBucketName() + "/" + request.getSourceKey();
        copySourceHeader = HttpUtils.normalizePath(copySourceHeader);
        internalRequest.addHeader(Headers.BCE_COPY_SOURCE, copySourceHeader);
        if (request.getETag() != null) {
            internalRequest.addHeader(Headers.BCE_COPY_SOURCE_IF_MATCH,
                    "\"" + request.getETag() + "\"");
        }
        if (request.getNoneMatchETagConstraint() != null) {
            internalRequest.addHeader(
                    Headers.BCE_COPY_SOURCE_IF_NONE_MATCH, "\"" + request.getNoneMatchETagConstraint() + "\"");
        }
        if (request.getUnmodifiedSinceConstraint() != null) {
            internalRequest.addHeader(
                    Headers.BCE_COPY_SOURCE_IF_UNMODIFIED_SINCE, request.getUnmodifiedSinceConstraint());
        }
        if (request.getModifiedSinceConstraint() != null) {
            internalRequest.addHeader(
                    Headers.BCE_COPY_SOURCE_IF_MODIFIED_SINCE, request.getModifiedSinceConstraint());
        }
        if (request.getStorageClass() != null) {
            internalRequest.addHeader(Headers.BCE_STORAGE_CLASS, request.getStorageClass());
        }
        ObjectMetadata newObjectMetadata = request.getNewObjectMetadata();
        if (newObjectMetadata != null) {
            internalRequest.addHeader(Headers.BCE_COPY_METADATA_DIRECTIVE, "replace");
            populateRequestMetadata(internalRequest, newObjectMetadata);
        } else {
            internalRequest.addHeader(Headers.BCE_COPY_METADATA_DIRECTIVE, "copy");
        }

        this.setZeroContentLength(internalRequest);

        CopyObjectResponseWithExceptionInfo intermidiateRes = this.invokeHttpClient(internalRequest,
                CopyObjectResponseWithExceptionInfo.class);
        // handle exception
        if (intermidiateRes.getETag() == null
                && intermidiateRes.getLastModified() == null) {
            if (intermidiateRes.getMessage() != null) {
                BceServiceException bse = new BceServiceException(intermidiateRes.getMessage());
                bse.setErrorCode(intermidiateRes.getCode());
                bse.setRequestId(intermidiateRes.getRequestId());
                if (bse.getErrorCode() == "InternalError") {
                    bse.setErrorType(ErrorType.Service);
                } else {
                    bse.setErrorType(ErrorType.Client);
                }
                bse.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                throw bse;
            }
        }
        return (CopyObjectResponse) intermidiateRes;
    }

    /**
     * Fetches a source object to a new destination in Bos.
     *
     * @param bucketName The name of the bucket in which the new object will be created.
     * @param key        The key in the destination bucket under which the new object will be created.
     * @param sourceUrl  The url full path for fetching.
     *
     * @return A FetchObjectResponse object containing the information returned by Bos for the newly fetching.
     */
    public FetchObjectResponse fetchObject(String bucketName, String key, String sourceUrl) {
        FetchObjectRequest request = new FetchObjectRequest(bucketName, key, sourceUrl);
        return this.fetchObject(request);
    }

    /**
     * Fetches a source object to a new destination in Bos.
     *
     * @param bucketName The name of the bucket in which the new object will be created.
     * @param key        The key in the destination bucket under which the new object will be created.
     * @param sourceUrl  The url full path for fetching.
     * @param mode       The mode path for fetching.
     *
     * @return A FetchObjectResponse object containing the information returned by Bos for the newly fetching.
     */
    public FetchObjectResponse fetchObject(String bucketName, String key, String sourceUrl, String mode) {
        FetchObjectRequest request = new FetchObjectRequest(bucketName, key, sourceUrl).withMode(mode);
        return this.fetchObject(request);
    }

    /**
     * Fetches a source object to a new destination in Bos.
     *
     * @param request The request object containing all the options for fetching url to a Bos object.
     *
     * @return A FetchObjectResponse object containing the information returned by Bos for the newly fetching.
     */
    public FetchObjectResponse fetchObject(FetchObjectRequest request) {
        checkNotNull(request, "request should not be null.");
        assertStringNotNullOrEmpty(request.getSourceUrl(), "source should not be null or empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter("fetch", null);

        internalRequest.addHeader(Headers.BCE_FETCH_SOURCE, request.getSourceUrl());
        if (request.getMode() != null) {
            internalRequest.addHeader(Headers.BCE_FETCH_MODE, request.getMode());
        }
        if (request.getStorageClass() != null) {
            internalRequest.addHeader(Headers.BCE_STORAGE_CLASS, request.getStorageClass());
        }
        this.setZeroContentLength(internalRequest);

        FetchObjectResponse response = this.invokeHttpClient(internalRequest, FetchObjectResponse.class);
        return response;
    }

    /**
     * Deletes the specified object in the specified bucket.
     *
     * @param bucketName The name of the Bos bucket containing the object to delete.
     * @param key        The key of the object to delete.
     */
    public void deleteObject(String bucketName, String key) {
        this.deleteObject(new DeleteObjectRequest(bucketName, key));
    }

    /**
     * Deletes the specified object in the specified bucket.
     *
     * @param request The request object containing all options for deleting a Bos object.
     */
    public void deleteObject(DeleteObjectRequest request) {
        checkNotNull(request, "request should not be null.");
        assertStringNotNullOrEmpty(request.getKey(), "object key should not be null or empty");

        this.invokeHttpClient(this.createRequest(request, HttpMethodName.DELETE), BosResponse.class);
    }

    /**
     * Deletes the multiple specified objects in the specified bucket.
     *
     * @param request The request object containing all options for deleting a Bos multiple objects.
     */
    public DeleteMultipleObjectsResponse deleteMultipleObjects(DeleteMultipleObjectsRequest request) {

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter("delete", "");
        if (request.getJsonDeleteObjects() != null) {
            byte[] json = null;
            try {
                json = request.getJsonDeleteObjects().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else if (request.getObjectKeys() != null) {
            if (request.getObjectKeys().size() > 1000) {
                throw new BceClientException("The delete objects number must not more than 1000.");
            }
            List<String> objectKeys = request.getObjectKeys();
            byte[] json = null;
            StringWriter writer = new StringWriter();
            try {
                JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
                jsonGenerator.writeStartObject();
                jsonGenerator.writeArrayFieldStart("objects");
                for (String object : objectKeys) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("key", object);
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
            } catch (IOException e) {
                throw new BceClientException("Fail to generate json", e);
            }
            try {
                json = writer.toString().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else {
            checkNotNull(request, "request should not be null.");
        }
        DeleteMultipleObjectsResponse response = this.invokeHttpClient(internalRequest,
                DeleteMultipleObjectsResponse.class);
        return response;
    }

    /**
     * Set Object Acl for specified object in the specified bucket.
     *
     * @param bucketName
     * @param objectKey
     * @param jsonObjectAcl The json format of specified object.
     */
    public void setObjectAcl(String bucketName, String objectKey, String jsonObjectAcl) {
        this.setObjectAcl(new SetObjectAclRequest(bucketName, objectKey, jsonObjectAcl));
    }

    /**
     * Set Object Acl for specified object in the specified bucket.
     *
     * @param bucketName
     * @param objectKey
     * @param acl        The CannedAccessControlList of specified object.
     */
    public void setObjectAcl(String bucketName, String objectKey, CannedAccessControlList acl) {
        this.setObjectAcl(new SetObjectAclRequest(bucketName, objectKey, acl));
    }

    /**
     * Set Object Acl for specified object in the specified bucket.
     *
     * @param request The request object containing all options for setting a Bos object acl.
     */
    public void setObjectAcl(SetObjectAclRequest request) {

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);
        internalRequest.addParameter("acl", null);

        if (request.getCannedAcl() != null) {
            internalRequest.addHeader(Headers.BCE_ACL, request.getCannedAcl().toString());
            this.setZeroContentLength(internalRequest);
        } else if (request.getAccessControlList() != null) {
            byte[] json = null;
            List<Grant> grants = request.getAccessControlList();
            StringWriter writer = new StringWriter();
            try {
                JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
                jsonGenerator.writeStartObject();
                jsonGenerator.writeArrayFieldStart("accessControlList");
                for (Grant grant : grants) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeArrayFieldStart("grantee");
                    for (Grantee grantee : grant.getGrantee()) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("id", grantee.getId());
                        jsonGenerator.writeEndObject();
                    }
                    jsonGenerator.writeEndArray();
                    jsonGenerator.writeArrayFieldStart("permission");
                    for (Permission permission : grant.getPermission()) {
                        jsonGenerator.writeString(permission.toString());
                    }
                    jsonGenerator.writeEndArray();
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
                jsonGenerator.writeEndObject();
                jsonGenerator.close();
            } catch (IOException e) {
                throw new BceClientException("Fail to generate json", e);
            }
            try {
                json = writer.toString().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else if (request.getJsonObjectAcl() != null) {
            byte[] json = null;
            try {
                json = request.getJsonObjectAcl().getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Fail to get UTF-8 bytes", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
            internalRequest.setContent(RestartableInputStream.wrap(json));
        } else if (request.getxBceGrantRead() != null) {
            internalRequest.addHeader(Headers.BCE_ACL_GRANT_READ, request.getxBceGrantRead());
            this.setZeroContentLength(internalRequest);
        } else if (request.getxBceGrantFullControl() != null) {
            internalRequest.addHeader(Headers.BCE_ACL_GRANT_FULL_CONTROL, request.getxBceGrantFullControl());
            this.setZeroContentLength(internalRequest);
        } else {
            checkNotNull(null, "request.acl should not be null.");
        }
        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Get Object Acl for specified object in the specified bucket.
     *
     * @param request The request object containing all options for getting a Bos object acl.
     */
    public GetObjectAclResponse getObjectAcl(GetObjectAclRequest request) {
        checkNotNull(request, "request should not be null.");
        assertStringNotNullOrEmpty(request.getKey(), "object key should not be null or empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET);
        internalRequest.addParameter("acl", null);

        GetObjectAclResponse response = this.invokeHttpClient(internalRequest, GetObjectAclResponse.class);
        if (response.getVersion() > response.MAX_SUPPORTED_ACL_VERSION) {
            throw new BceClientException("Unsupported acl version.");
        }
        return response;
    }

    /**
     * Delete Object Acl for specified object in the specified bucket.
     *
     * @param request The request object containing all options for deleting a Bos object acl.
     */
    public void deleteObjectAcl(DeleteObjectAclRequest request) {
        checkNotNull(request, "request should not be null.");
        assertStringNotNullOrEmpty(request.getKey(), "object key should not be null or empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE);
        internalRequest.addParameter("acl", null);
        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Initiates a multipart upload and returns an InitiateMultipartUploadResponse
     * which contains an upload ID. This upload ID associates all the parts in
     * the specific upload and is used in each of your subsequent uploadPart requests.
     * You also include this upload ID in the final request to either complete, or abort the multipart
     * upload request.
     *
     * @param bucketName The name of the Bos bucket containing the object to initiate.
     * @param key        The key of the object to initiate.
     *
     * @return An InitiateMultipartUploadResponse from Bos.
     */
    public InitiateMultipartUploadResponse initiateMultipartUpload(String bucketName, String key) {
        return this.initiateMultipartUpload(new InitiateMultipartUploadRequest(bucketName, key));
    }

    /**
     * Initiates a multipart upload and returns an InitiateMultipartUploadResponse
     * which contains an upload ID. This upload ID associates all the parts in
     * the specific upload and is used in each of your subsequent uploadPart requests.
     * You also include this upload ID in the final request to either complete, or abort the multipart
     * upload request.
     *
     * @param request The InitiateMultipartUploadRequest object that specifies all the parameters of this operation.
     *
     * @return An InitiateMultipartUploadResponse from Bos.
     */
    public InitiateMultipartUploadResponse initiateMultipartUpload(InitiateMultipartUploadRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter("uploads", null);
        if (request.getStorageClass() != null) {
            internalRequest.addHeader(Headers.BCE_STORAGE_CLASS, request.getStorageClass());
        }
        this.setZeroContentLength(internalRequest);

        if (request.getObjectMetadata() != null) {
            populateRequestMetadata(internalRequest, request.getObjectMetadata());
        }

        return this.invokeHttpClient(internalRequest, InitiateMultipartUploadResponse.class);
    }

    /**
     * Uploads a part in a multipart upload. You must initiate a multipart
     * upload before you can upload any part.
     *
     * @param request The UploadPartRequest object that specifies all the parameters of this operation.
     *
     * @return An UploadPartResponse from Bos containing the part number and ETag of the new part.
     */
    public UploadPartResponse uploadPart(UploadPartRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getPartSize(), "partSize should not be null");
        checkNotNull(request.getPartNumber(), "partNumber should not be null");

        if (request.getPartSize() > 5 * 1024 * 1024 * 1024L) {
            throw new BceClientException("PartNumber " + request.getPartNumber()
                    + " : Part Size should not be more than 5GB.");
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);
        internalRequest.addParameter("uploadId", request.getUploadId());
        internalRequest.addParameter("partNumber", String.valueOf(request.getPartNumber()));
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(request.getPartSize()));
        if (request.getxBceCrc() != null) {
            internalRequest.addHeader(Headers.BCE_CONTENT_CRC32, String.valueOf(request.getxBceCrc()));
        }

        InputStream input = request.getInputStream();
        MD5DigestCalculatingInputStream md5DigestStream = null;
        if (request.getMd5Digest() == null) {
            try {
                md5DigestStream = new MD5DigestCalculatingInputStream(input);
                input = md5DigestStream;
            } catch (NoSuchAlgorithmException e) {
                logger.warn("Unable to verify data integrity.", e);
            }
        }

        try {
            internalRequest.setContent(this.wrapRestartableInputStream(input));
            BosResponse response = this.invokeHttpClient(internalRequest, BosResponse.class);

            if (md5DigestStream != null) {
                byte[] clientSideHash = md5DigestStream.getMd5Digest();
                byte[] serverSideHash;
                try {
                    serverSideHash = Hex.decodeHex(response.getMetadata().getETag().toCharArray());
                } catch (Exception e) {
                    throw new BceClientException("Unable to verify integrity of data upload.", e);
                }
                if (!Arrays.equals(clientSideHash, serverSideHash)) {
                    throw new BceClientException("Unable to verify integrity of data upload.  "
                            + "Client calculated content hash didn't match hash calculated by "
                            + "BOS.  " + "You may need to delete the data stored in BOS.");
                }
            }
            UploadPartResponse result = new UploadPartResponse();
            result.setETag(response.getMetadata().getETag());
            result.setPartNumber(request.getPartNumber());
            return result;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Uploads a copy part in a multipart upload. You must initiate a multipart
     * upload before you can upload any part.
     *
     * @param request The UploadPartCopyRequest object that specifies all the parameters of this operation.
     *
     * @return An UploadPartCopyResponse from Bos containing the part number and ETag of the new part.
     */
    public UploadPartCopyResponse uploadPartCopy(UploadPartCopyRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getPartNumber(), "partNumber should not be null");
        checkNotNull(request.getSourceBucketName(), "sourceBucketName should not be null");
        checkNotNull(request.getSourceKey(), "sourceKey should not be null");

        if (request.getPartSize() > 5 * 1024 * 1024 * 1024L) {
            throw new BceClientException("PartNumber " + request.getPartNumber()
                    + " : Part Size should not be more than 5GB.");
        }

        String bceCopySource = "/" + request.getSourceBucketName() + "/" + request.getSourceKey();
        bceCopySource = HttpUtils.normalizePath(bceCopySource);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT);
        internalRequest.addParameter("uploadId", request.getUploadId());
        internalRequest.addParameter("partNumber", String.valueOf(request.getPartNumber()));
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(0));
        internalRequest.addHeader(Headers.BCE_COPY_SOURCE, bceCopySource);
        if (request.getxBceCrc() != null) {
            internalRequest.addHeader(Headers.BCE_CONTENT_CRC32, String.valueOf(request.getxBceCrc()));
        }
        if (request.getPartSize() != 0) {
            long offSet = request.getOffSet();
            long partSize = request.getPartSize();
            String bceCopySourceRange = "bytes" + "=" + String.valueOf(offSet)
                    + "-" + String.valueOf(offSet + partSize - 1);
            internalRequest.addHeader(Headers.BCE_COPY_SOURCE_RANGE, bceCopySourceRange);
        }
        UploadPartCopyResponse response = this.invokeHttpClient(internalRequest, UploadPartCopyResponse.class);
        return response;
    }

    /**
     * Lists the parts that have been uploaded for a specific multipart upload.
     *
     * @param bucketName The name of the bucket containing the multipart upload whose parts are being listed.
     * @param key        The key of the associated multipart upload whose parts are being listed.
     * @param uploadId   The ID of the multipart upload whose parts are being listed.
     *
     * @return Returns a ListPartsResponse from Bos.
     */
    public ListPartsResponse listParts(String bucketName, String key, String uploadId) {
        return this.listParts(new ListPartsRequest(bucketName, key, uploadId));
    }

    /**
     * Lists the parts that have been uploaded for a specific multipart upload.
     *
     * @param request The ListPartsRequest object that specifies all the parameters of this operation.
     *
     * @return Returns a ListPartsResponse from Bos.
     */
    public ListPartsResponse listParts(ListPartsRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest interrnalRequest = this.createRequest(request, HttpMethodName.GET);
        interrnalRequest.addParameter("uploadId", request.getUploadId());
        int maxParts = request.getMaxParts();
        if (maxParts >= 0) {
            interrnalRequest.addParameter("maxParts", String.valueOf(maxParts));
        }
        interrnalRequest.addParameter("partNumberMarker", String.valueOf(request.getPartNumberMarker()));

        ListPartsResponse response = this.invokeHttpClient(interrnalRequest, ListPartsResponse.class);
        response.setBucketName(request.getBucketName());
        return response;
    }

    /**
     * Completes a multipart upload by assembling previously uploaded parts.
     *
     * @param bucketName The name of the bucket containing the multipart upload to complete.
     * @param key        The key of the multipart upload to complete.
     * @param uploadId   The ID of the multipart upload to complete.
     * @param partETags  The list of part numbers and ETags to use when completing the multipart upload.
     *
     * @return A CompleteMultipartUploadResponse from Bos containing the ETag for
     * the new object composed of the individual parts.
     */
    public CompleteMultipartUploadResponse completeMultipartUpload(String bucketName, String key, String uploadId,
                                                                   List<PartETag> partETags) {
        return this.completeMultipartUpload(new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags));
    }

    /**
     * Completes a multipart upload by assembling previously uploaded parts.
     *
     * @param bucketName The name of the bucket containing the multipart upload to complete.
     * @param key        The key of the multipart upload to complete.
     * @param uploadId   The ID of the multipart upload to complete.
     * @param partETags  The list of part numbers and ETags to use when completing the multipart upload.
     * @param metadata   Additional metadata instructing Bos how to handle the uploaded data
     *                   (e.g. custom user metadata, hooks for specifying content type, etc.).
     *
     * @return A CompleteMultipartUploadResponse from Bos containing the ETag for
     * the new object composed of the individual parts.
     */
    public CompleteMultipartUploadResponse completeMultipartUpload(String bucketName, String key, String uploadId,
                                                                   List<PartETag> partETags, ObjectMetadata metadata) {
        return this.completeMultipartUpload(new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags,
                metadata));
    }

    /**
     * Completes a multipart upload by assembling previously uploaded parts.
     *
     * @param request The CompleteMultipartUploadRequest object that specifies all the parameters of this operation.
     *
     * @return A CompleteMultipartUploadResponse from Bos containing the ETag for
     * the new object composed of the individual parts.
     */
    public CompleteMultipartUploadResponse completeMultipartUpload(CompleteMultipartUploadRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter("uploadId", request.getUploadId());

        ObjectMetadata metadata = request.getObjectMetadata();
        if (metadata != null) {
            populateRequestMetadata(internalRequest, metadata);
        }

        byte[] json = null;
        List<PartETag> partETags = request.getPartETags();
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeArrayFieldStart("parts");
            for (PartETag partETag : partETags) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("partNumber", partETag.getPartNumber());
                jsonGenerator.writeStringField("eTag", partETag.getETag());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.setContent(RestartableInputStream.wrap(json));

        CompleteMultipartUploadResponse response =
                this.invokeHttpClient(internalRequest, CompleteMultipartUploadResponse.class);
        response.setBucketName(request.getBucketName());
        return response;
    }

    /**
     * Aborts a multipart upload. After a multipart upload is aborted, no
     * additional parts can be uploaded using that upload ID. The storage
     * consumed by any previously uploaded parts will be freed. However, if any
     * part uploads are currently in progress, those part uploads may or may not
     * succeed. As a result, it may be necessary to abort a given multipart
     * upload multiple times in order to completely free all storage consumed by
     * all parts.
     *
     * @param bucketName The name of the bucket containing the multipart upload to abort.
     * @param key        The key of the multipart upload to abort.
     * @param uploadId   The ID of the multipart upload to abort.
     */
    public void abortMultipartUpload(String bucketName, String key, String uploadId) {
        this.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, key, uploadId));
    }

    /**
     * Aborts a multipart upload. After a multipart upload is aborted, no
     * additional parts can be uploaded using that upload ID. The storage
     * consumed by any previously uploaded parts will be freed. However, if any
     * part uploads are currently in progress, those part uploads may or may not
     * succeed. As a result, it may be necessary to abort a given multipart
     * upload multiple times in order to completely free all storage consumed by
     * all parts.
     *
     * @param request The AbortMultipartUploadRequest object that specifies all the parameters of this operation.
     */
    public void abortMultipartUpload(AbortMultipartUploadRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE);
        internalRequest.addParameter("uploadId", request.getUploadId());

        this.invokeHttpClient(internalRequest, BosResponse.class);
    }

    /**
     * Lists in-progress multipart uploads. An in-progress multipart upload is a multipart upload that has
     * been initiated, using the InitiateMultipartUpload request, but has not yet been completed or aborted.
     *
     * @param bucketName The name of the bucket containing the uploads to list.
     *
     * @return A ListMultipartUploadsResponse from Bos.
     */
    public ListMultipartUploadsResponse listMultipartUploads(String bucketName) {
        return this.listMultipartUploads(new ListMultipartUploadsRequest(bucketName));
    }

    /**
     * Lists in-progress multipart uploads. An in-progress multipart upload is a multipart upload that has
     * been initiated, using the InitiateMultipartUpload request, but has not yet been completed or aborted.
     *
     * @param request The ListMultipartUploadsRequest object that specifies all the parameters of this operation.
     *
     * @return A ListMultipartUploadsResponse from Bos.
     */
    public ListMultipartUploadsResponse listMultipartUploads(ListMultipartUploadsRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET);
        internalRequest.addParameter("uploads", null);
        String keyMarker = request.getKeyMarker();
        if (keyMarker != null) {
            internalRequest.addParameter("keyMarker", keyMarker);
        }
        int maxUploads = request.getMaxUploads();
        if (maxUploads >= 0) {
            internalRequest.addParameter("maxUploads", String.valueOf(maxUploads));
        }
        String delimiter = request.getDelimiter();
        if (delimiter != null) {
            internalRequest.addParameter("delimiter", delimiter);
        }
        String prefix = request.getPrefix();
        if (prefix != null) {
            internalRequest.addParameter("prefix", prefix);
        }

        ListMultipartUploadsResponse response =
                this.invokeHttpClient(internalRequest, ListMultipartUploadsResponse.class);
        response.setBucketName(request.getBucketName());
        return response;
    }

    /**
     * Populates the specified request object with the appropriate headers from the ObjectMetadata object.
     *
     * @param request  The request to populate with headers.
     * @param metadata The metadata containing the header information to include in the request.
     */
    private static void populateRequestMetadata(InternalRequest request, ObjectMetadata metadata) {
        if (metadata.getContentType() != null) {
            request.addHeader(Headers.CONTENT_TYPE, metadata.getContentType());
        }
        if (metadata.getContentMd5() != null) {
            request.addHeader(Headers.CONTENT_MD5, metadata.getContentMd5());
        }
        if (metadata.getContentEncoding() != null) {
            request.addHeader(Headers.CONTENT_ENCODING, metadata.getContentEncoding());
        }
        if (metadata.getBceContentSha256() != null) {
            request.addHeader(Headers.BCE_CONTENT_SHA256, metadata.getBceContentSha256());
        }
        if (metadata.getContentDisposition() != null) {
            request.addHeader(Headers.CONTENT_DISPOSITION, metadata.getContentDisposition());
        }
        if (metadata.getETag() != null) {
            request.addHeader(Headers.ETAG, metadata.getETag());
        }
        if (metadata.getExpires() != null) {
            request.addHeader(Headers.EXPIRES, metadata.getExpires());
        }
        if (metadata.getCacheControl() != null) {
            request.addHeader(Headers.CACHE_CONTROL, metadata.getCacheControl());
        }
        if (metadata.getStorageClass() != null) {
            request.addHeader(Headers.BCE_STORAGE_CLASS, metadata.getStorageClass());
        }

        Map<String, String> userMetadata = metadata.getUserMetadata();
        if (userMetadata != null) {
            for (Entry<String, String> entry : userMetadata.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    continue;
                }
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                if (key.length() + value.length() > 1024 * 32) {
                    throw new BceClientException("MetadataTooLarge");
                }
                request.addHeader(Headers.BCE_USER_METADATA_PREFIX + HttpUtils.normalize(key.trim()),
                        HttpUtils.normalize(value));
            }
        }
    }

    /**
     * Creates and initializes a new request object for the specified Bos resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     *
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod) {
        String bucketName = null;
        String key = null;
        // when custom_endpoint, bucketName should be null when set up InternalRequest.
        if (bceRequest instanceof GenericBucketRequest && !((BosClientConfiguration) this.config).isCnameEnabled()) {
            bucketName = ((GenericBucketRequest) bceRequest).getBucketName();
        }
        if (bceRequest instanceof GenericObjectRequest) {
            key = ((GenericObjectRequest) bceRequest).getKey();
        }
        InternalRequest request =
                new InternalRequest(httpMethod, HttpUtils.appendUri(this.getEndpoint(), URL_PREFIX, bucketName, key));
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    private void downloadObjectToFile(BosObject bosObject, File destinationFile, boolean verifyIntegrity) {
        // attempt to create the parent if it doesn't exist
        File parentDirectory = destinationFile.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));
            byte[] buffer = new byte[this.getStreamBufferSize()];
            int bytesRead;
            while ((bytesRead = bosObject.getObjectContent().read(buffer)) > -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            try {
                bosObject.getObjectContent().close();
            } catch (IOException abortException) {
                logger.warn("Couldn't abort stream", abortException);
            }
            throw new BceClientException("Unable to write to disk", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
            }
            try {
                bosObject.getObjectContent().close();
            } catch (Exception e) {
            }
        }
        if (verifyIntegrity) {
            byte[] serverSideHash = null;
            byte[] clientSideHash = null;
            ObjectMetadata objectMetadata = bosObject.getObjectMetadata();
            try {
                if (objectMetadata.getBceContentSha256() != null) {
                    serverSideHash = Hex.decodeHex(objectMetadata.getBceContentSha256().toCharArray());
                    clientSideHash = HashUtils.computeSha256Hash(new FileInputStream(destinationFile));
                } else if (objectMetadata.getContentMd5() != null) {
                    serverSideHash = Base64.decodeBase64(objectMetadata.getContentMd5().getBytes(DEFAULT_ENCODING));
                    clientSideHash = HashUtils.computeMd5Hash(new FileInputStream(destinationFile));
                }
            } catch (Exception e) {
                logger.warn("Unable to verify the integrity of the downloaded file", e);
            }
            if (serverSideHash != null && clientSideHash != null && !Arrays.equals(clientSideHash, serverSideHash)) {
                throw new BceClientException("Integrity verification failed! "
                        + "Client calculated content hash didn't match hash from server. "
                        + "The data stored in '" + destinationFile.getAbsolutePath() + "' may be corrupt.");
            }
        }
    }

    private List<byte[]> readAll(InputStream input, ObjectMetadata metadata) {
        List<byte[]> result = Lists.newArrayList();
        int bufferSize = this.getStreamBufferSize();
        long length = 0;
        for (; ; ) {
            byte[] buffer = new byte[bufferSize];
            result.add(buffer);
            int off = 0;
            while (off < bufferSize) {
                int count;
                try {
                    count = input.read(buffer, off, bufferSize - off);
                } catch (IOException e) {
                    throw new BceClientException("Fail to read data.", e);
                }
                if (count < 0) {
                    metadata.setContentLength(length);
                    return result;
                }
                length += count;
                off += count;
            }
        }
    }

    private RestartableInputStream wrapRestartableInputStream(InputStream input) {
        if (input.markSupported()) {
            return new RestartableResettableInputStream(input);
        } else {
            return new RestartableNonResettableInputStream(input, this.getStreamBufferSize());
        }
    }

    private void setZeroContentLength(InternalRequest req) {
        req.addHeader(Headers.CONTENT_LENGTH, String.valueOf(0));
    }

    private int getStreamBufferSize() {
        return ((BosClientConfiguration) this.config).getStreamBufferSize();
    }

    /**
     * Asserts that the specified parameter value is not <code>null</code> or <code>empty</code> and if it is,
     * throws an <code>IllegalArgumentException</code> with the specified error message.
     *
     * @param parameterValue The parameter value being checked.
     * @param errorMessage   The error message to include in the IllegalArgumentException
     *                       if the specified parameter is null.
     */
    private void assertStringNotNullOrEmpty(String parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (parameterValue.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Adds response headers parameters to the request given, if non-null.
     *
     * @param request         The request to add the response header parameters to.
     * @param responseHeaders The full set of response headers to add, or null for none.
     */
    private void addResponseHeaderParameters(InternalRequest request, ResponseHeaderOverrides responseHeaders) {
        if (responseHeaders != null) {
            if (responseHeaders.getCacheControl() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CACHE_CONTROL,
                        responseHeaders.getCacheControl());
            }
            if (responseHeaders.getContentDisposition() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_DISPOSITION,
                        responseHeaders.getContentDisposition());
            }
            if (responseHeaders.getContentEncoding() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_ENCODING,
                        responseHeaders.getContentEncoding());
            }
            if (responseHeaders.getContentLanguage() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_LANGUAGE,
                        responseHeaders.getContentLanguage());
            }
            if (responseHeaders.getContentType() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_TYPE,
                        responseHeaders.getContentType());
            }
            if (responseHeaders.getExpires() != null) {
                request.addParameter(ResponseHeaderOverrides.RESPONSE_HEADER_EXPIRES, responseHeaders.getExpires());
            }
        }
    }

    /**
     * Converts the specified request object into a URL, containing all the
     * specified parameters, the specified request endpoint, etc.
     *
     * @param request The request to convert into a URL.
     *
     * @return A new URL representing the specified request.
     */
    private URL convertRequestToUrl(InternalRequest request) {
        String resourcePath = HttpUtils.normalizePath(request.getUri().getPath());

        // Removed the padding "/" that was already added into the request's resource path.
        if (resourcePath.startsWith("/")) {
            resourcePath = resourcePath.substring(1);
        }

        // Some http client libraries (e.g. Apache HttpClient) cannot handle
        // consecutive "/"s between URL authority and path components.
        // So we escape "////..." into "/%2F%2F%2F...", in the same way as how
        // we treat consecutive "/"s in AmazonS3Client#presignRequest(...)
        String urlPath = "/" + resourcePath;
        urlPath = urlPath.replaceAll("(?<=/)/", "%2F");
        String urlString = this.getEndpoint() + urlPath;

        boolean firstParam = true;
        for (String param : request.getParameters().keySet()) {
            if (firstParam) {
                urlString += "?";
                firstParam = false;
            } else {
                urlString += "&";
            }

            String value = request.getParameters().get(param);
            urlString += param + "=" + HttpUtils.normalize(value);
        }

        String authorization = request.getHeaders().get(Headers.AUTHORIZATION);
        if (authorization != null) {
            if (firstParam) {
                urlString += "?";
            } else {
                urlString += "&";
            }
            urlString += "authorization" + "=" + HttpUtils.normalize(authorization);
        }

        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new BceClientException("Unable to convert request to well formed URL: " + e.getMessage(), e);
        }
    }

    /**
     * Uploads the specified appendable file to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param file       The appendable file containing the data to be uploaded to Bos.
     *
     * @return An AppendObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public AppendObjectResponse appendObject(String bucketName, String key, File file) {
        return this.appendObject(new AppendObjectRequest(bucketName, key, file));
    }

    /**
     * Uploads the specified appendable file to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param file       The file containing the data to be uploaded to Bos.
     * @param metadata   Additional metadata instructing Bos how to handle the uploaded data
     *                   (e.g. custom user metadata, hooks for specifying content type, etc.).
     *
     * @return An AppendObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public AppendObjectResponse appendObject(String bucketName, String key, File file, ObjectMetadata metadata) {
        return this.appendObject(new AppendObjectRequest(bucketName, key, file, metadata));
    }

    /**
     * Uploads the specified string to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param value      The string containing the value to be uploaded to Bos.
     *
     * @return An AppendObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public AppendObjectResponse appendObject(String bucketName, String key, String value) {
        try {
            return this.appendObject(bucketName, key, value.getBytes(DEFAULT_ENCODING), new ObjectMetadata());
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes.", e);
        }
    }

    /**
     * Uploads the specified string and object metadata to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param value      The string containing the value to be uploaded to Bos.
     * @param metadata   Additional metadata instructing Bos how to handle the uploaded data
     *                   (e.g. custom user metadata, hooks for specifying content type, etc.).
     *
     * @return An AppendObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public AppendObjectResponse appendObject(String bucketName, String key, String value, ObjectMetadata metadata) {
        try {
            return this.appendObject(bucketName, key, value.getBytes(DEFAULT_ENCODING), metadata);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get bytes.", e);
        }
    }

    /**
     * Uploads the specified bytes to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param value      The bytes containing the value to be uploaded to Bos.
     *
     * @return An AppendObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public AppendObjectResponse appendObject(String bucketName, String key, byte[] value) {
        return this.appendObject(bucketName, key, value, new ObjectMetadata());
    }

    /**
     * Uploads the appendable bytes and object metadata to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param value      The bytes containing the value to be uploaded to Bos.
     * @param metadata   Additional metadata instructing Bos how to handle the uploaded data
     *                   (e.g. custom user metadata, hooks for specifying content type, etc.).
     *
     * @return An AppendObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public AppendObjectResponse appendObject(
            String bucketName, String key, byte[] value, ObjectMetadata metadata) {
        checkNotNull(metadata, "metadata should not be null.");
        if (metadata.getContentLength() == -1) {
            metadata.setContentLength(value.length);
        }
        return this.appendObject(
                new AppendObjectRequest(bucketName, key, RestartableInputStream.wrap(value), metadata));
    }

    /**
     * Uploads the appendable input stream to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param input      The input stream containing the value to be uploaded to Bos.
     *
     * @return An AppendObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public AppendObjectResponse appendObject(String bucketName, String key, InputStream input) {
        return this.appendObject(new AppendObjectRequest(bucketName, key, input));
    }

    /**
     * Uploads the appendable input stream and object metadata to Bos under the specified bucket and key name.
     *
     * @param bucketName The name of an existing bucket, to which you have Write permission.
     * @param key        The key under which to store the specified file.
     * @param input      The input stream containing the value to be uploaded to Bos.
     * @param metadata   Additional metadata instructing Bos how to handle the uploaded data
     *                   (e.g. custom user metadata, hooks for specifying content type, etc.).
     *
     * @return An AppendObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public AppendObjectResponse appendObject(
            String bucketName, String key, InputStream input, ObjectMetadata metadata) {
        return this.appendObject(new AppendObjectRequest(bucketName, key, input, metadata));
    }

    /**
     * Uploads a new object to the specified Bos bucket. The <code>AppendObjectRequest</code> contains all the
     * details of the request, including the bucket to upload to, the key the object will be uploaded under,
     * and the file or input stream containing the data to upload.
     *
     * @param request The request object containing all the parameters to upload a new appendable object to Bos.
     *
     * @return An AppendObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public AppendObjectResponse appendObject(AppendObjectRequest request) {
        checkNotNull(request, "request should not be null.");
        assertStringNotNullOrEmpty(request.getKey(), "object key should not be null or empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter("append", null);
        if (request.getOffset() != null) {
            internalRequest.addParameter("offset", request.getOffset().toString());
        }
        BosResponse response = uploadObject(request, internalRequest);
        AppendObjectResponse result = new AppendObjectResponse();
        result.setNextAppendOffset(response.getMetadata().getNextAppendOffset());
        result.setContentMd5(response.getMetadata().getContentMd5());
        result.setETag(response.getMetadata().getETag());
        return result;
    }

    /**
     * Uploads a object to Bos bucket. The PutObjcetRequest contains all the details of the request.The internalRequest
     * represents a request being send to a BCE Service.
     *
     * @param request         The request object containing all the parameters to upload a new appendable object to Bos.
     * @param internalRequest Represents a request being sent to a BCE Service, including the parameters being
     *                        sent as part of the request, the endpoint to which the request should be sent, etc.
     *
     * @return A BosResponse object containing the bos-metadata information returned by Bos for the newly created
     * object
     */
    private BosResponse uploadObject(PutObjectRequest request, InternalRequest internalRequest) {
        ObjectMetadata metadata = request.getObjectMetadata();
        InputStream input = request.getInputStream();
        if (request.getFile() != null) {
            File file = request.getFile();

            if (file.length() > 5 * 1024 * 1024 * 1024L) {
                BceServiceException bse = new BceServiceException("Your proposed upload exceeds the maximum allowed "
                        + "object size.");
                bse.setStatusCode(400);
                bse.setErrorCode("EntityTooLarge");
                bse.setErrorType(BceServiceException.ErrorType.Client);
                throw bse;
            }

            // Always set the content length, even if it's already set
            if (metadata.getContentLength() < 0) {
                metadata.setContentLength(file.length());
            }

            if (metadata.getContentType() == null) {
                metadata.setContentType(Mimetypes.getInstance().getMimetype(file));
            }

            if (metadata.getContentLength() == file.length()) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    metadata.setBceContentSha256(
                            new String(Hex.encodeHex(HashUtils.computeSha256Hash(fileInputStream))));
                } catch (Exception e) {
                    throw new BceClientException("Unable to calculate SHA-256 hash", e);
                } finally {
                    try {
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    } catch (Exception e) {
                        logger.warn("The inputStream accured error");
                    }
                }
            }
            try {
                internalRequest.setContent(new RestartableFileInputStream(file));
            } catch (FileNotFoundException e) {
                throw new BceClientException("Unable to find file to upload", e);
            }
        } else {
            checkNotNull(input, "Either file or inputStream should be set.");
            if (metadata.getContentLength() < 0) {
                logger.warn("No content length specified for stream data. Trying to read them all into memory.");
                List<byte[]> data = this.readAll(input, metadata);
                internalRequest.setContent(new RestartableMultiByteArrayInputStream(data, metadata.getContentLength()));
            } else if (input instanceof RestartableInputStream) {
                internalRequest.setContent((RestartableInputStream) input);
            } else {
                internalRequest.setContent(this.wrapRestartableInputStream(input));
            }
            if (metadata.getContentType() == null) {
                metadata.setContentType(Mimetypes.getInstance().getMimetype(request.getKey()));
            }
        }
        if (request.getStorageClass() != null) {
            metadata.setStorageClass(request.getStorageClass());
        }

        if (metadata.getxBceCrc() != null) {
            internalRequest.addHeader(Headers.BCE_CONTENT_CRC32, String.valueOf(metadata.getxBceCrc()));
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(metadata.getContentLength()));

        populateRequestMetadata(internalRequest, metadata);

        BosResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, BosResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                logger.warn("Fail to close input stream", e);
            }
        }
        return response;
    }
}
