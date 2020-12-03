/**
 * Copyright 2020 Baidu, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bes;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
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
import com.baidubce.services.bes.model.BesCreateAutoRenewRuleRequest;
import com.baidubce.services.bes.model.BesCreateAutoRenewRuleResponse;
import com.baidubce.services.bes.model.BesCreateClusterRequest;
import com.baidubce.services.bes.model.BesCreateClusterResponse;
import com.baidubce.services.bes.model.BesDeleteAutoRenewRuleRequest;
import com.baidubce.services.bes.model.BesDeleteAutoRenewRuleResponse;
import com.baidubce.services.bes.model.BesDeleteClusterRequest;
import com.baidubce.services.bes.model.BesDeleteClusterResponse;
import com.baidubce.services.bes.model.BesGetAutoRenewRuleListRequest;
import com.baidubce.services.bes.model.BesGetAutoRenewRuleListResponse;
import com.baidubce.services.bes.model.BesGetClusterDetailRequest;
import com.baidubce.services.bes.model.BesGetClusterDetailResponse;
import com.baidubce.services.bes.model.BesGetClusterListRequest;
import com.baidubce.services.bes.model.BesGetClusterListResponse;
import com.baidubce.services.bes.model.BesGetRenewListRequest;
import com.baidubce.services.bes.model.BesGetRenewListResponse;
import com.baidubce.services.bes.model.BesRenewClusterRequest;
import com.baidubce.services.bes.model.BesRenewClusterResponse;
import com.baidubce.services.bes.model.BesResizeClusterRequest;
import com.baidubce.services.bes.model.BesStartClusterRequest;
import com.baidubce.services.bes.model.BesStartClusterResponse;
import com.baidubce.services.bes.model.BesStartInstanceRequest;
import com.baidubce.services.bes.model.BesStartInstanceResponse;
import com.baidubce.services.bes.model.BesStopClusterRequest;
import com.baidubce.services.bes.model.BesStopClusterResponse;
import com.baidubce.services.bes.model.BesStopInstanceRequest;
import com.baidubce.services.bes.model.BesStopInstanceResponse;
import com.baidubce.services.bes.model.BesUpdateAutoRenewRuleRequest;
import com.baidubce.services.bes.model.BesUpdateAutoRenewRuleResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Elasticsearch service
 */
public class BesClient extends AbstractBceClient {
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    private static final String BASE_PATH = "/api/bes/cluster";
    private static final String STOP =  "/stop";
    private static final String START =  "/start";
    private static final String DELETE = "/delete";
    private static final String LIST = "/list";
    private static final String DETAIL = "/detail";
    private static final String CREATE = "/create";
    private static final String RESIZE = "/resize";
    private static final String UPDATE = "/update";
    private static final String INSTANCE = "/instance";
    private static final String RENEW = "/renew";
    private static final String AUTO_RENEW_RULE = "/auto_renew_rule";
    private static final String X_REGION = "X-Region";
    private static final String BES_SERVICE_TYPE = "BES";
    private String region;
    /**
     * Responsible for handling HttpResponse from all BES service calls.
     */
    private static final HttpResponseHandler[] BES_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public BesClient() {
        this(new BceClientConfiguration());
    }

    /**
     * @param clientConfiguration The BCE client configuration options.
     */
    public BesClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, BES_HANDLERS);
    }

    /**
     * @param clientConfiguration The BCE client configuration options.
     * @param region client configuration options Defaults to bj
     */
    public BesClient(BceClientConfiguration clientConfiguration, String region) {
        super(clientConfiguration, BES_HANDLERS);
        this.region = region;
    }

    /**
     * Stop cluster owned by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @param besStopClusterRequest
     * @return Returns a request response code or error message
     */
    public BesStopClusterResponse stopCluster(BesStopClusterRequest besStopClusterRequest) {
        checkNotNull(besStopClusterRequest, "request should not be null.");
        checkStringNotEmpty(besStopClusterRequest.getClusterId(),
                "The parameter clusterId should not be null or empty string.");

        String json = null;
        try {
            json = besStopClusterRequest.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besStopClusterRequest, HttpMethodName.POST, BASE_PATH, STOP);

        addCommonHeader(internalRequest, json);

        BesStopClusterResponse besStopClusterResponse =
                this.invokeHttpClient(internalRequest, BesStopClusterResponse.class);
        return besStopClusterResponse;
    }

    /**
     * Start cluster owned by the authenticated user.
     * Users must authenticate with a valid BCE Access Key ID, and the response
     * contains all the BES clusters owned by the user.
     *
     * @param besStartClusterRequest
     * @return Returns a request response code or error message
     */
    public BesStartClusterResponse startCluster(BesStartClusterRequest besStartClusterRequest) {
        checkNotNull(besStartClusterRequest, "request should not be null.");
        checkStringNotEmpty(besStartClusterRequest.getClusterId(),
                "The parameter clusterId should not be null or empty string.");

        String json = null;
        try {
            json = besStartClusterRequest.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besStartClusterRequest, HttpMethodName.POST, BASE_PATH, START);

        addCommonHeader(internalRequest, json);

        BesStartClusterResponse besStartClusterResponse =
                this.invokeHttpClient(internalRequest, BesStartClusterResponse.class);
        return besStartClusterResponse;
    }

    /**
     *  Delete cluster owned by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @param besDeleteClusterRequest
     * @return Returns a request response code or error message
     */
    public BesDeleteClusterResponse deleteCluster(BesDeleteClusterRequest besDeleteClusterRequest) {
        checkNotNull(besDeleteClusterRequest, "request should not be null.");
        checkStringNotEmpty(besDeleteClusterRequest.getClusterId(), "" +
                "The parameter clusterId should not be null or empty string.");

        String json = null;
        try {
            json = besDeleteClusterRequest.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besDeleteClusterRequest, HttpMethodName.POST, BASE_PATH, DELETE);

        addCommonHeader(internalRequest, json);

        BesDeleteClusterResponse besDeleteClusterResponse =
                this.invokeHttpClient(internalRequest, BesDeleteClusterResponse.class);
        return besDeleteClusterResponse;
    }

    /**
     * List BES clusters owned by the authenticated user.
     * Users must authenticate with a valid BCE Access Key ID, and the response
     * contains all the BES clusters owned by the user.
     *
     * @param besGetClusterListRequest
     * @return The response containing a list of the BES clusters owned by the authenticated sender of the request.
     */
    public BesGetClusterListResponse getClusterList(BesGetClusterListRequest besGetClusterListRequest) {
        checkNotNull(besGetClusterListRequest, "request should not be null.");

        String json = null;
        try {
            json = besGetClusterListRequest.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besGetClusterListRequest, HttpMethodName.POST, BASE_PATH, LIST);

        addCommonHeader(internalRequest, json);

        BesGetClusterListResponse besGetClusterListResponse =
                this.invokeHttpClient(internalRequest, BesGetClusterListResponse.class);
        return besGetClusterListResponse;
    }

    /**
     * Get cluster detail owned by the authenticated user.
     * Users must authenticate with a valid BCE Access Key ID, and the response
     * contains all the BES clusters owned by the user.
     *
     * @param besGetClusterDetailRequest
     * @return Get cluster information or error code
     */
    public BesGetClusterDetailResponse getClusterDetail(BesGetClusterDetailRequest besGetClusterDetailRequest) {
        checkNotNull(besGetClusterDetailRequest, "request should not be null.");
        checkStringNotEmpty(besGetClusterDetailRequest.getClusterId(),
                "The parameter clusterId should not be null or empty string.");

        String json = null;
        try {
            json = besGetClusterDetailRequest.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besGetClusterDetailRequest, HttpMethodName.POST, BASE_PATH, DETAIL);

        addCommonHeader(internalRequest, json);

        BesGetClusterDetailResponse besGetClusterDetailResponse =
                this.invokeHttpClient(internalRequest, BesGetClusterDetailResponse.class);
        return besGetClusterDetailResponse;
    }

    /**
     * Create a cluster
     * Users must authenticate with a valid BCE Access Key ID, and the response
     * contains all the BES clusters owned by the user.
     *
     * @param request
     * @return Create the order ID for the cluster
     */
    public BesCreateClusterResponse createCluster(BesCreateClusterRequest request) {
        checkNotNull(request, "request should not be null.");

        String json = null;
        try {
            json = request.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, BASE_PATH, CREATE);

        addCommonHeader(internalRequest, json);

        BesCreateClusterResponse deployResponse =
                this.invokeHttpClient(internalRequest, BesCreateClusterResponse.class);
        return deployResponse;
    }

    /**
     * Resize cluster detail owned by the authenticated user.
     * Users must authenticate with a valid BCE Access Key ID, and the response
     * contains all the BES clusters owned by the user.
     *
     * @param request
     * @return Create the order ID for the cluster
     */
    public BesCreateClusterResponse resizeCluster(BesResizeClusterRequest request) {
        checkNotNull(request, "request should not be null.");

        String json = null;
        try {
            json = request.toJson(region);
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, BASE_PATH, RESIZE);

        internalRequest.addParameter("orderType", "RESIZE");
        addCommonHeader(internalRequest, json);

        BesCreateClusterResponse deployResponse =
                this.invokeHttpClient(internalRequest, BesCreateClusterResponse.class);
        return deployResponse;
    }

    /**
     *  Start cluster instance owned by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @param request
     * @return Returns a request response code or error message
     */
    public BesStartInstanceResponse startClusterInstance(BesStartInstanceRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(),
                "The parameter clusterId should not be null or empty string.");
        String json = null;
        try {
            json = request.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }


        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, BASE_PATH, INSTANCE, START);

        addCommonHeader(internalRequest, json);

        BesStartInstanceResponse besStartInstanceResponse =
                this.invokeHttpClient(internalRequest, BesStartInstanceResponse.class);
        return besStartInstanceResponse;
    }

    /**
     *  Stop cluster instance owned by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @param request
     * @return Returns a request response code or error message
     */
    public BesStopInstanceResponse stopClusterInstance(BesStopInstanceRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(),
                "The parameter clusterId should not be null or empty string.");
        String json = null;
        try {
            json = request.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, BASE_PATH, INSTANCE, STOP);

        addCommonHeader(internalRequest, json);

        BesStopInstanceResponse besStopInstanceResponse =
                this.invokeHttpClient(internalRequest, BesStopInstanceResponse.class);
        return besStopInstanceResponse;
    }

    /**
     *  Get BES renew list owned by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @param request
     * @return Returns a request response code or error message
     */
    public BesGetRenewListResponse getRenewList(BesGetRenewListRequest request) {
        checkNotNull(request, "request should not be null.");

        String json = null;
        try {
            json = request.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, BASE_PATH, RENEW, LIST);

        addCommonHeader(internalRequest, json);

        BesGetRenewListResponse besGetRenewListResponse =
                this.invokeHttpClient(internalRequest, BesGetRenewListResponse.class);
        return besGetRenewListResponse;
    }

    /**
     *  Renew cluster owned by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @param request
     * @return Returns a request response code or error message
     */
    public BesRenewClusterResponse renewCluster(BesRenewClusterRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(),
                "The parameter clusterId should not be null or empty string.");
        String json = null;
        try {
            json = request.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, BASE_PATH, RENEW);

        internalRequest.addParameter("orderType", "RENEW");
        addCommonHeader(internalRequest, json);

        BesRenewClusterResponse besRenewClusterResponse =
                this.invokeHttpClient(internalRequest, BesRenewClusterResponse.class);
        return besRenewClusterResponse;
    }

    /**
     *  Get BES auto renew list owned by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @return Returns a request response code or error message
     */
    public BesGetAutoRenewRuleListResponse getAutoRenewRuleList() {
        BesGetAutoRenewRuleListRequest besGetAutoRenewRuleListRequest = new BesGetAutoRenewRuleListRequest();
        besGetAutoRenewRuleListRequest.setServiceType(BES_SERVICE_TYPE);
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("serviceType", BES_SERVICE_TYPE);
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besGetAutoRenewRuleListRequest, HttpMethodName.POST, BASE_PATH, AUTO_RENEW_RULE, LIST);

        addCommonHeader(internalRequest, writer.toString());

        BesGetAutoRenewRuleListResponse besGetAutoRenewRuleResponse =
                this.invokeHttpClient(internalRequest, BesGetAutoRenewRuleListResponse.class);
        return besGetAutoRenewRuleResponse;
    }

    /**
     *  Create auto renew rule by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @param request
     * @return Returns a request response code or error message
     */
    public BesCreateAutoRenewRuleResponse createAutoRenewRule(BesCreateAutoRenewRuleRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getClusterIds(),
                "The parameter clusterIds should not be null or empty string.");
        String json = null;
        try {
            json = request.toJson(region);
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, BASE_PATH, AUTO_RENEW_RULE, CREATE);

        addCommonHeader(internalRequest, json);

        BesCreateAutoRenewRuleResponse besCreateAutoRenewRuleResponse =
                this.invokeHttpClient(internalRequest, BesCreateAutoRenewRuleResponse.class);
        return besCreateAutoRenewRuleResponse;
    }

    /**
     *  Update auto renew rule owned by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @param request
     * @return Returns a request response code or error message
     */
    public BesUpdateAutoRenewRuleResponse updateAutoRenewRule(BesUpdateAutoRenewRuleRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(),
                "The parameter clusterIds should not be null or empty string.");

        String json = null;
        try {
            json = request.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, BASE_PATH, AUTO_RENEW_RULE, UPDATE);

        addCommonHeader(internalRequest, json);

        BesUpdateAutoRenewRuleResponse besUpdateAutoRenewRuleResponse =
                this.invokeHttpClient(internalRequest, BesUpdateAutoRenewRuleResponse.class);
        return besUpdateAutoRenewRuleResponse;
    }

    /**
     *  Delete auto renew rule owned by the authenticated user.
     *  Users must authenticate with a valid BCE Access Key ID, and the response
     *  contains all the BES clusters owned by the user.
     *
     * @param request
     * @return Returns a request response code or error message
     */
    public BesDeleteAutoRenewRuleResponse deleteAutoRenewRule(BesDeleteAutoRenewRuleRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(),
                "The parameter clusterIds should not be null or empty string.");

        String json = null;
        try {
            json = request.toJson();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, BASE_PATH, AUTO_RENEW_RULE, DELETE);

        addCommonHeader(internalRequest, json);

        BesDeleteAutoRenewRuleResponse besDeleteAutoRenewRuleResponse =
                this.invokeHttpClient(internalRequest, BesDeleteAutoRenewRuleResponse.class);
        return besDeleteAutoRenewRuleResponse;
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest    The original BCE request created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     *
     * @return A new request object populated with endpoint, resource path and specific
     * parameters to send.
     */
    private InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<String>();

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());

        return request;
    }

    /**
     * The encryption implement for AES-128 algorithm for BCE password encryption.
     * Only the first 16 bytes of privateKey will be used to encrypt the content.
     *
     * @param content    The content String to encrypt.
     * @param privateKey The security key to encrypt.
     *                   Only the first 16 bytes of privateKey will be used to encrypt the content.
     *
     * @return The encrypted string of the original content with AES-128 algorithm.
     *
     * @throws GeneralSecurityException
     */
    private String aes128EncryptWithFirst16Char(String content, String privateKey) throws GeneralSecurityException {
        if (privateKey == null || privateKey.length() < 16) {
            throw new GeneralSecurityException("account secretKey is wrong");
        }
        byte[] crypted = null;
        SecretKeySpec skey = new SecretKeySpec(privateKey.substring(0, 16).getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        crypted = cipher.doFinal(content.getBytes());
        return new String(Hex.encodeHex(crypted));
    }

    /**
     * Add the common request header to the request object
     *
     * @param internalRequest A new request object populated with endpoint, resource path and specific
     * parameters to send.
     * @param json Json transmission of the request body
     */
    private void addCommonHeader(InternalRequest internalRequest, String json) {
        byte[] bytes = null;
        try {
            bytes = json.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(bytes.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.setContent(RestartableInputStream.wrap(bytes));
    }
}