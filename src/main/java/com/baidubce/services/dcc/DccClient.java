/*
 * Copyright (c) 2019-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dcc;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
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
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.bcc.model.Reservation;
import com.baidubce.services.dcc.model.CreateDccInstanceRequest;
import com.baidubce.services.dcc.model.CreateDccInstanceResponse;
import com.baidubce.services.dcc.model.CreateDccRequest;
import com.baidubce.services.dcc.model.CreateDccResponse;
import com.baidubce.services.dcc.model.DccAction;
import com.baidubce.services.dcc.model.DccBindTagsRequest;
import com.baidubce.services.dcc.model.DccDetailRequest;
import com.baidubce.services.dcc.model.DccDetailResponse;
import com.baidubce.services.dcc.model.DccModel;
import com.baidubce.services.dcc.model.DccRenameRequest;
import com.baidubce.services.dcc.model.DccUnbindTagsRequest;
import com.baidubce.services.dcc.model.ListDccRequest;
import com.baidubce.services.dcc.model.ListDccResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Dedicated Cloud Compute Service(dcc).
 */
public class DccClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DccClient.class);

    private static final String VERSION = "v1";
    private static final String DCC_PREFIX = "dedicatedHost";
    private static final String INSTANCE_PREFIX = "instance";
    private static final String TAG = "tag";
    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String FLAVOR_NULL_MESSAGE_KEY = "flavor";
    private static final String DCC_ID_MESSAGE_KEY = "dcc id";
    private static final String INSTANCEID_MESSAGE_KEY = "instanceId";
    private static final String NAME_MESSAGE_KEY = "name";

    /**
     * Responsible for handling httpResponses from all dcc service calls.
     */
    private static final HttpResponseHandler[] dcc_handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on dcc.
     */
    public DccClient() {
        this(new DccClientConfiguration());
    }

    /**
     * Constructs a new dcc client using the client configuration to access dcc.
     *
     * @param clientConfiguration The dcc client configuration options controlling how this client
     *                            connects to dcc (e.g. proxy settings, retry counts, etc).
     */
    public DccClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, dcc_handlers);
    }


    /**
     * Creates and initializes a new request object for the specified dcc resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
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
     *                        any additional headers or parameters, and execute.
     * @param bceRequest      The original request, as created by the user.
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
     * The default method to generate the random String for clientToken if the optional parameter clientToken
     * is not specified by the user.
     * <p>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * The encryption implement for AES-128 algorithm for BCE password encryption.
     * Only the first 16 bytes of privateKey will be used to encrypt the content.
     * <p>
     * See more detail on
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     * BCE API doc</a>
     *
     * @param content    The content String to encrypt.
     * @param privateKey The security key to encrypt.
     *                   Only the first 16 bytes of privateKey will be used to encrypt the content.
     * @return The encrypted string of the original content with AES-128 algorithm.
     * @throws GeneralSecurityException
     */
    private String aes128WithFirst16Char(String content, String privateKey) throws GeneralSecurityException {
        byte[] crypted = null;
        SecretKeySpec skey = new SecretKeySpec(privateKey.substring(0, 16).getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        crypted = cipher.doFinal(content.getBytes());
        return new String(Hex.encodeHex(crypted));
    }

    /**
     * The method to generate a default Billing which is Postpaid.
     *
     * @return The Billing object with Postpaid PaymentTiming.
     */
    private Billing generateDefaultBilling() {
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        Reservation reservation = new Reservation();
        reservation.setReservationLength(1);
        billing.setReservation(reservation);
        return billing;
    }

    /**
     * The method to generate a default Billing with default Reservation which default ReservationLength is 1.
     *
     * @return The Billing object with default Reservation which default ReservationLength is 1
     */
    private Billing generateDefaultBillingWithReservation() {
        Billing billing = new Billing();
        billing.withReservation(new Reservation().withReservationLength(1));
        return billing;
    }

    /**
     * Create a dcc host with specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param request CreateDccRequest
     * @return CreateDccResponse
     */
    public CreateDccResponse createDcc(CreateDccRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }

        request.checkAutoRenewTime();
        request.checkCloudDiskType();
        checkStringNotEmpty(request.getFlavor(), checkEmptyExceptionMessageFormat(FLAVOR_NULL_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                DCC_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateDccResponse.class);
    }

    /**
     * Create a dcc instance with specified options.
     *
     * @param request CreateDccInstanceRequest
     * @return CreateDccInstanceResponse
     */
    public CreateDccInstanceResponse createDccInstance(CreateDccInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                DCC_PREFIX, INSTANCE_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateDccInstanceResponse.class);
    }

    /**
     * Create a dcc instance with specified options.
     * This interface will encrypt the password
     *
     * @param request CreateDccInstanceRequest
     * @return CreateDccInstanceResponse
     */
    public CreateDccInstanceResponse createDccInstanceWithEncryption(CreateDccInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                DCC_PREFIX, INSTANCE_PREFIX);
        if (!Strings.isNullOrEmpty(request.getAdminPass())) {
            BceCredentials credentials = config.getCredentials();
            if (internalRequest.getCredentials() != null) {
                credentials = internalRequest.getCredentials();
            }
            try {
                request.setAdminPass(this.aes128WithFirst16Char(request.getAdminPass(), credentials.getSecretKey()));
            } catch (GeneralSecurityException e) {
                throw new BceClientException("Encryption procedure exception", e);
            }
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateDccInstanceResponse.class);
    }

    /**
     * Bind tags to dcc. Tags will also be bound to instances created on the dcc.
     *
     * @param request DccBindTagsRequest
     */
    public void dccBindTags(DccBindTagsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getDccId(), checkEmptyExceptionMessageFormat(DCC_ID_MESSAGE_KEY));
        if (request.getChangeTags() == null
                || request.getChangeTags().size() == 0) {
            throw new IllegalArgumentException("dcc bind tags: tags is empty");
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, DCC_PREFIX,
                request.getDccId(), TAG);
        internalRequest.addParameter(DccAction.bind.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Unbind tags from dcc.
     *
     * @param request DccUnbindTagsRequest
     */
    public void dccUnbindTags(DccUnbindTagsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getDccId(), checkEmptyExceptionMessageFormat(DCC_ID_MESSAGE_KEY));
        if (request.getChangeTags() == null
                || request.getChangeTags().size() == 0) {
            throw new IllegalArgumentException("dcc unbind tags: tags is empty");
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, DCC_PREFIX,
                request.getDccId(), TAG);
        internalRequest.addParameter(DccAction.unbind.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Renaming dcc instance.
     *
     * @param request
     */
    public void dccRename(DccRenameRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                DCC_PREFIX, request.getInstanceId());
        internalRequest.addParameter(DccAction.modifyAttribute.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query detail information of a dedicated host.
     *
     * @param request DccDetailRequest
     * @return DccModel
     */
    public DccModel dccDetail(DccDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getDccId(), checkEmptyExceptionMessageFormat(DCC_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                DCC_PREFIX, request.getDccId());
        DccDetailResponse response = invokeHttpClient(internalRequest, DccDetailResponse.class);
        return response.getDedicatedHost();
    }

    /**
     * Listing dcc hosts owned by the user.
     * zoneName can be specified in the request to list dcc in the target zone.
     *
     * @param request ListDccRequest
     * @return ListDccResponse
     */
    public ListDccResponse dccList(ListDccRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                DCC_PREFIX);
        return invokeHttpClient(internalRequest, ListDccResponse.class);
    }
}
