/**
 * Copyright 2020 Baidu, Inc.
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
import com.baidubce.services.bes.model.BesClusterDetailRequest;
import com.baidubce.services.bes.model.BesClusterDetailResponse;
import com.baidubce.services.bes.model.BesConfigTuple;
import com.baidubce.services.bes.model.BesCreateClusterRequest;
import com.baidubce.services.bes.model.BesCreateClusterResponse;
import com.baidubce.services.bes.model.BesDeleteClusterRequest;
import com.baidubce.services.bes.model.BesDeleteClusterResponse;
import com.baidubce.services.bes.model.BesListClusterRequest;
import com.baidubce.services.bes.model.BesListClusterResponse;
import com.baidubce.services.bes.model.BesResizeClusterRequest;
import com.baidubce.services.bes.model.BesStartClusterRequest;
import com.baidubce.services.bes.model.BesStartClusterResponse;
import com.baidubce.services.bes.model.BesStartInstanceRequest;
import com.baidubce.services.bes.model.BesStartInstanceResponse;
import com.baidubce.services.bes.model.BesStopClusterRequest;
import com.baidubce.services.bes.model.BesStopClusterResponse;
import com.baidubce.services.bes.model.BesStopInstanceRequest;
import com.baidubce.services.bes.model.BesStopInstanceResponse;
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

    private static final String STOP_CLUSTER_URL = "api/bes/cluster/stop";
    private static final String START_CLUSTER_URL = "api/bes/cluster/start";
    private static final String DELETE_CLUSTER_URL = "api/bes/cluster/delete";
    private static final String CLUSTER_LIST_URL = "api/bes/cluster/list";
    private static final String CLUSTER_DETAIL_URL = "api/bes/cluster/detail";
    private static final String CREATE_CLUSTER_URL = "api/bes/cluster/create";
    private static final String RESIZE_CLUSTER_URL = "api/bes/cluster/resize";
    private static final String START_CLUSTER_INSTANCE_URL = "api/bes/cluster/instance/start";
    private static final String STOP_CLUSTER_INSTANCE_URL = "api/bes/cluster/instance/stop";
    private static final String X_REGION = "X-Region";
    private String region = "";

    /**
     * Responsible for handling HttpResponse from all BES service calls.
     */
    private static final HttpResponseHandler[] BES_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on BES.
     */
    public BesClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new BES client using the client configuration to access BES.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public BesClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, BES_HANDLERS);
        region = getRegion(clientConfiguration.getEndpoint());
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

        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("clusterId", besStopClusterRequest.getClusterId());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besStopClusterRequest, HttpMethodName.POST, STOP_CLUSTER_URL);

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.setContent(RestartableInputStream.wrap(json));

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

        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("clusterId", besStartClusterRequest.getClusterId());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besStartClusterRequest, HttpMethodName.POST, START_CLUSTER_URL);

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.setContent(RestartableInputStream.wrap(json));

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

        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("clusterId", besDeleteClusterRequest.getClusterId());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besDeleteClusterRequest, HttpMethodName.POST, DELETE_CLUSTER_URL);

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.setContent(RestartableInputStream.wrap(json));

        BesDeleteClusterResponse besDeleteClusterResponse =
                this.invokeHttpClient(internalRequest, BesDeleteClusterResponse.class);
        return besDeleteClusterResponse;
    }

    /**
     * List BES clusters owned by the authenticated user.
     * Users must authenticate with a valid BCE Access Key ID, and the response
     * contains all the BES clusters owned by the user.
     *
     * @param besListClusterRequest
     * @return The response containing a list of the BES clusters owned by the authenticated sender of the request.
     */
    public BesListClusterResponse clusterList(BesListClusterRequest besListClusterRequest) {
        checkNotNull(besListClusterRequest, "request should not be null.");
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("pageNo", besListClusterRequest.getPageNo());
            jsonGenerator.writeNumberField("pageSize", besListClusterRequest.getPageSize());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        InternalRequest internalRequest = this.createRequest(
                besListClusterRequest, HttpMethodName.POST, CLUSTER_LIST_URL);

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.setContent(RestartableInputStream.wrap(json));

        BesListClusterResponse deployResponse = this.invokeHttpClient(internalRequest, BesListClusterResponse.class);
        return deployResponse;
    }

    /**
     * Get cluster detail owned by the authenticated user.
     * Users must authenticate with a valid BCE Access Key ID, and the response
     * contains all the BES clusters owned by the user.
     *
     * @param besClusterDetailRequest
     * @return Get cluster information or error code
     */
    public BesClusterDetailResponse clusterDetail(BesClusterDetailRequest besClusterDetailRequest) {
        checkNotNull(besClusterDetailRequest, "request should not be null.");
        checkStringNotEmpty(besClusterDetailRequest.getClusterId(),
                "The parameter clusterId should not be null or empty string.");

        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("clusterId", besClusterDetailRequest.getClusterId());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest = this.createRequest(
                besClusterDetailRequest, HttpMethodName.POST, CLUSTER_DETAIL_URL);

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.setContent(RestartableInputStream.wrap(json));

        BesClusterDetailResponse deployResponse =
                this.invokeHttpClient(internalRequest, BesClusterDetailResponse.class);
        return deployResponse;
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
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", request.getName());
            jsonGenerator.writeStringField("password", request.getPassword());
            jsonGenerator.writeArrayFieldStart("modules");
            for (BesCreateClusterRequest.ModuleInfo modules : request.getModules()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("type", modules.getType());
                jsonGenerator.writeNumberField("instanceNum", modules.getInstanceNum());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            BesCreateClusterRequest.ClusterBilling billing = request.getBilling();
            jsonGenerator.writeObjectFieldStart("billing");
            jsonGenerator.writeStringField("payment", billing.getPayment());
            jsonGenerator.writeNumberField("time", billing.getTime());
            jsonGenerator.writeEndObject();
            jsonGenerator.writeStringField("version", request.getVersion());
            jsonGenerator.writeStringField("slotType", request.getSlotType());
            jsonGenerator.writeBooleanField("isOpenService", request.isOpenService());
            jsonGenerator.writeStringField("availableZone", request.getAvailableZone());
            jsonGenerator.writeStringField("securityGroupId", request.getSecurityGroupId());
            jsonGenerator.writeStringField("subnetUuid", request.getSubnetUuid());
            jsonGenerator.writeStringField("vpcId", request.getVpcId());
            jsonGenerator.writeStringField("serviceType", request.getServiceType());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }
        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, CREATE_CLUSTER_URL);

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.setContent(RestartableInputStream.wrap(json));
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
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("deployId", request.getDeployId());
            jsonGenerator.writeStringField("name", request.getName());
            jsonGenerator.writeStringField("region", request.getRegion());
            jsonGenerator.writeStringField("productType", request.getProductType());
            jsonGenerator.writeArrayFieldStart("modules");
            for (BesResizeClusterRequest.ModuleDesc module : request.getModules()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("slot_type", module.getSlotType());
                jsonGenerator.writeNumberField("desireInstanceNum", module.getDesireInstanceNum());
                jsonGenerator.writeStringField("version", module.getVersion());
                jsonGenerator.writeStringField("type", module.getType());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeArrayFieldStart("configs");
            for (BesConfigTuple config : request.getConfigs()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("id", config.getId());
                jsonGenerator.writeStringField("value", config.getValue());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }
        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, RESIZE_CLUSTER_URL);

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.addParameter("orderType", "RESIZE");
        internalRequest.setContent(RestartableInputStream.wrap(json));
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

        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("instanceId", request.getInstanceId());
            jsonGenerator.writeStringField("clusterId", request.getClusterId());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, START_CLUSTER_INSTANCE_URL);

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.setContent(RestartableInputStream.wrap(json));

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
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("instanceId", request.getInstanceId());
            jsonGenerator.writeStringField("clusterId", request.getClusterId());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, STOP_CLUSTER_INSTANCE_URL);

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.addHeader(X_REGION, region);
        internalRequest.setContent(RestartableInputStream.wrap(json));

        BesStopInstanceResponse besStopInstanceResponse =
                this.invokeHttpClient(internalRequest, BesStopInstanceResponse.class);
        return besStopInstanceResponse;
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
     *  Gets the region and adds it to the request header
     *
     * @param endpoint
     * @return region
     */
    private String getRegion(String endpoint) {
        String region = endpoint.split("\\.")[1];
        return region;
    }

}
