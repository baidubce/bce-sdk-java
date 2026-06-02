/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.eip;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.common.BaseBceRequest;
import com.baidubce.common.BaseBceResponse;
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
import com.baidubce.services.eip.model.AutoRenewEipRequest;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.eip.model.BindEipRequest;
import com.baidubce.services.eip.model.ConvertToPrepayEipRequest;
import com.baidubce.services.eip.model.CancelEipTransferRequest;
import com.baidubce.services.eip.model.CreateEipRequest;
import com.baidubce.services.eip.model.CreateEipResponse;
import com.baidubce.services.eip.model.CreateEipTransferRequest;
import com.baidubce.services.eip.model.CreateEipTransferResponse;
import com.baidubce.services.eip.model.DirectEipRequest;
import com.baidubce.services.eip.model.ListEipTransferRequest;
import com.baidubce.services.eip.model.ListEipTransferResponse;
import com.baidubce.services.eip.model.ListEipsRequest;
import com.baidubce.services.eip.model.ListEipsResponse;
import com.baidubce.services.eip.model.ListRecycleEipsRequest;
import com.baidubce.services.eip.model.ListRecycleEipsResponse;
import com.baidubce.services.eip.model.OptionalReleaseEipRequest;
import com.baidubce.services.eip.model.PurchaseReservedEipRequest;
import com.baidubce.services.eip.model.AcceptEipTransferRequest;
import com.baidubce.services.eip.model.RecycleOperateEipRequest;
import com.baidubce.services.eip.model.RefundEipRequest;
import com.baidubce.services.eip.model.RejectEipTransferRequest;
import com.baidubce.services.eip.model.ReleaseEipRequest;
import com.baidubce.services.eip.model.ResizeEipRequest;
import com.baidubce.services.eip.model.StopAutoRenewEipRequest;
import com.baidubce.services.eip.model.UnbindEipRequest;
import com.baidubce.services.eip.model.UpdateDeleteProtectEipRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * Provides the client for accessing the Elastic Ip Service (EIP).
 */
public class EipClient extends AbstractBceClient {
    /**
     * EIP API pathVersion
     */
    private static final String VERSION = "v1";

    private static final String PREFIX = "eip";

    private static final String CLIENT_TOKEN_IDENTIFY = "clientToken";

    private static final String CONSTANT_TRANSFER = "transfer";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static HttpResponseHandler[] eipHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public EipClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new InstanceClient to invoke service methods on eip instance.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public EipClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, eipHandlers);
    }

    /**
     * Create an eip with the specified options.
     * @param bandwidthInMbps specify the bandwidth in Mbps
     * @return
     */
    public CreateEipResponse createEip(int bandwidthInMbps) {
        return createEip(new CreateEipRequest().withBandwidthInMbps(bandwidthInMbps));
    }

    /**
     * Create an eip with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for creating an eip.
     * @return created eip address
     */
    public CreateEipResponse createEip(CreateEipRequest request) {
        checkNotNull(request.getBandwidthInMbps(), "bandwidthInMbps should not be null");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        checkNotNull(request.getBandwidthInMbps(), "bandwidthInMbps should not be null");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, null);

        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEipResponse.class);
    }

    /**
     * Resizing eip
     *
     * @param eip eip address to be resized
     * @param newBandwidthInMbps specify new bandwidth in Mbps for eip
     */
    public void resizeEip(String eip, int newBandwidthInMbps) {
        this.resizeEip(new ResizeEipRequest().withEip(eip).withNewBandwidthInMbps(newBandwidthInMbps));
    }

    /**
     * Resizing eip
     * The Prepaid eip can not be downgrade.
     * This is an asynchronous interface.
     *
     * @param request eip & newBandwidthInMbps must be provided
     */
    public void resizeEip(ResizeEipRequest request) {
        checkNotNull(request.getNewBandwidthInMbps(), "newBandwidthInMbps should not be null");
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getEip());
        internalRequest.addParameter("resize", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * PurchaseReserved eip with specified duration in month
     * @param eip
     * @param reservationLength
     */
    public void purchaseReservedEipInMonth(String eip, int reservationLength) {
        Billing billing = new Billing();
        billing.setReservation(new Billing.Reservation().withReservationLength(reservationLength));
        this.purchaseReservedEip(new PurchaseReservedEipRequest().withEip(eip).withBilling(billing));
    }

    /**
     * PurchaseReserved eip with fixed duration
     * only Prepaid eip can do this
     * @param eip eip address to be renewed
     * @param reservationLength purchase length
     * @param reservationTimeUnit time unit of purchasing, optional parameter default value 'Month'
     */
    public void purchaseReservedEip(String eip, int reservationLength, String reservationTimeUnit) {
        Billing billing = new Billing();
        billing.setReservation(new Billing.Reservation().withReservationLength(reservationLength)
                .withReservationTimeUnit(reservationTimeUnit));

        this.purchaseReservedEip(new PurchaseReservedEipRequest().withEip(eip).withBilling(billing));
    }

    /**
     * PurchaseReserved eip with fixed duration
     * only Prepaid eip can do this
     *
     * This is an asynchronous interface
     * @param request The request containing all options for renewing eip with fixed duration.
     */
    public void purchaseReservedEip(PurchaseReservedEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultReservation());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getEip());
        internalRequest.addParameter("purchaseReserved", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * AutoRenew eip with fixed duration
     * only Prepaid eip can do this
     * <p>
     * This is an asynchronous interface
     *
     * @param request The request containing all options for starting autorenew eip.
     */
    public void startAutoRenew(AutoRenewEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getEip());
        internalRequest.addParameter("startAutoRenew", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Stop autoRenew eip.
     * only Prepaid eip can do this
     *
     * This is an asynchronous interface
     *
     * @param request The request containing all options for stopping autorenew eip.
     */
    public void stopAutoRenew(StopAutoRenewEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getEip());
        internalRequest.addParameter("stopAutoRenew", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Direct eip.
     *
     * This is an asynchronous interface
     *
     * @param request The request containing all options for directing on eip.
     */
    public void directEip(DirectEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getEip());
        internalRequest.addParameter("direct", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        internalRequest.addHeader(Headers.CONTENT_LENGTH, "0");
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * UnDirect eip.
     *
     * This is an asynchronous interface
     *
     * @param request The request containing all options for undirecting on eip.
     */
    public void unDirectEip(DirectEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getEip());
        internalRequest.addParameter("unDirect", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        internalRequest.addHeader(Headers.CONTENT_LENGTH, "0");
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * bind the eip to a specified instanceId and instanceType(BCC|BLB).
     *
     * @param eip eip address to be bound
     * @param instanceId id of instance to be bound
     * @param instanceType type of instance to be bound
     */
    public void bindEip(String eip, String instanceId, String instanceType) {
        this.bindEip(new BindEipRequest().withEip(eip).withInstanceId(instanceId).withInstanceType(instanceType));
    }

    /**
     * bind the eip to a specified instanceId and instanceType(BCC|BLB).
     *
     * @param eip eip address to be bound
     * @param instanceId id of instance to be bound
     * @param instanceIp ip of instance to be bound
     * @param instanceType type of instance to be bound
     */
    public void bindEip(String eip, String instanceId, String instanceIp, String instanceType) {
        this.bindEip(new BindEipRequest()
                .withEip(eip)
                .withInstanceId(instanceId)
                .withInstanceIp(instanceIp)
                .withInstanceType(instanceType));
    }

    /**
     * bind the eip to a specified instanceId and instanceType(BCC|BLB).
     * the status of eip must be available and thd instance not be expired or boundByEip
     * @param request The request containing all options for binding eip
     */
    public void bindEip(BindEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getEip());
        internalRequest.addParameter("bind", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * unbind the eip from a specified instance
     * @param eip eip address to be unbound
     */
    public void unbindEip(String eip) {
        this.unbindEip(new UnbindEipRequest().withEip(eip));
    }

    /**
     * unbind the eip from a specified instance
     * @param request The request containing all options for unbinding eip
     */
    public void unbindEip(UnbindEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getEip());
        internalRequest.addParameter("unbind", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        internalRequest.addHeader(Headers.CONTENT_LENGTH, "0");
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * release the eip(delete operation)
     * @param eip eip address to be released
     */
    public void releaseEip(String eip) {
        this.releaseEip(new ReleaseEipRequest().withEip(eip));
    }

    /**
     * release the eip(delete operation)
     * Only the Postpaid instance or Prepaid which is expired can be released.
     * if the eip has been bound, must unbind before releasing
     *
     * @param request The request containing all options for releasing eip
     */
    public void releaseEip(ReleaseEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, request.getEip());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * get a list of eips owned by the authenticated user and default conditions
     * @return
     */
    public ListEipsResponse listEips() {
        ListEipsRequest request = new ListEipsRequest();
        return listEips(request);
    }

    /**
     * get a list of eips owned by the authenticated user and specified conditions
     *
     * we can Also get a single eip function  through this interface by eip condition
     *
     * if query by the instanceId or instanceType condition, must provides both of them at the same time
     * @param request The request containing all options for query
     * @return
     */
    public ListEipsResponse listEips(ListEipsRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, null);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() >= 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getEip())) {
            internalRequest.addParameter("eip", request.getEip());
        }
        if (!Strings.isNullOrEmpty(request.getInstanceId())) {
            internalRequest.addParameter("instanceId", request.getInstanceId());
            if (Strings.isNullOrEmpty(request.getInstanceType())) {
                throw new IllegalArgumentException("there is not instanceType");
            }
        }
        if (!Strings.isNullOrEmpty(request.getInstanceType())) {
            internalRequest.addParameter("instanceType", request.getInstanceType());
        }
        if (!Strings.isNullOrEmpty(request.getIpVersion())) {
            internalRequest.addParameter("ipVersion", request.getIpVersion());
        }
        if (!Strings.isNullOrEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (!Strings.isNullOrEmpty(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }
        if (!Strings.isNullOrEmpty(request.getEipIds())) {
            internalRequest.addParameter("eipIds", request.getEipIds());
        }
        return invokeHttpClient(internalRequest, ListEipsResponse.class);
    }

    /**
     * get a list of eips from recycle owned by the authenticated user and specified conditions
     *
     * @param request The request containing all options for query
     * @return
     */
    public ListRecycleEipsResponse listRecycleEips(ListRecycleEipsRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, "recycle");
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() >= 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getEip())) {
            internalRequest.addParameter("eip", request.getEip());
        }
        if (!Strings.isNullOrEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        return invokeHttpClient(internalRequest, ListRecycleEipsResponse.class);
    }


    /**
     * optional release the eip(Including directly deleting the eip or putting the eip into the recycle bin)
     * Only the Postpaid instance or Prepaid which is expired can be released.
     * if the eip has been bound, must unbind before releasing
     *
     * @param request The request containing all options for optional releasing eip
     */
    public void optionalReleaseEip(OptionalReleaseEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, request.getEip());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        internalRequest.addParameter("releaseToRecycle", String.valueOf(request.isReleaseToRecycle()));
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }



    /**
     * release the eip from recycle.
     *
     * @param request The request containing all options for releasing eip from recycle.
     * @return
     */
    public void releaseEipFromRecycle(RecycleOperateEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE,
                "recycle/" + request.getEip());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * restore the eip from recycle.
     *
     * @param request The request containing all options for restoring eip from recycle.
     * @return
     */
    public void restoreEipFromRecycle(RecycleOperateEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                "recycle/" + request.getEip());
        internalRequest.addParameter("restore", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        internalRequest.addHeader(Headers.CONTENT_LENGTH, "0");
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific
     *         parameters to send.
     */
    private InternalRequest createBaseRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<String>();
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
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific
     *         parameters to send.
     */
    private InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);
        path.add(PREFIX);

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
     * the method to fill the internalRequest's content field with bceRequest
     * only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     * @param bceRequest The original request, as created by the user.
     */
    protected void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
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
     *
     * The default algorithm is using {@link UUID} to generate a random UUID,
     * @return An random String generated by {@link UUID}.
     */
    private String generateDefaultClientToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * The method to generate a default Billing which is Postpaid.
     *
     * @return The Billing object with Postpaid PaymentTiming.
     */
    private Billing generateDefaultBilling() {
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        billing.setBillingMethod("ByBandwidth");
        return billing;
    }

    /**
     * The method to generate a default Billing with default Reservation which default ReservationLength is 1.
     *
     * @return The Billing object with default Reservation which default ReservationLength is 1
     */
    private Billing generateDefaultReservation() {
        Billing billing = new Billing();
        Billing.Reservation reservation = new Billing.Reservation();
        billing.setReservation(reservation);
        reservation.setReservationLength(1);
        return billing;
    }

    /**
     * Convert postpaid EIP to prepaid EIP.
     *
     * @param request The request containing all options for converting EIP billing type
     */
    public void convertToPrepayEip(ConvertToPrepayEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        checkNotNull(request.getPurchaseLength(), "purchaseLength should not be null");
        checkNotNull(request.getBandWidth(), "bandWidth should not be null");

        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getEip());
        internalRequest.addParameter("action", "TO_PREPAY");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update EIP delete protection switch.
     *
     * @param request The request containing all options for updating delete protection
     */
    public void updateDeleteProtect(UpdateDeleteProtectEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");
        checkNotNull(request.getDeleteProtect(), "deleteProtect should not be null");

        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                request.getEip(), "deleteProtect");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Refund prepaid EIP with early cancellation.
     * Only prepaid EIP can be refunded.
     *
     * @param request The request containing all options for refunding EIP
     */
    public void refundEip(RefundEipRequest request) {
        checkStringNotEmpty(request.getEip(), "eip should not be empty");

        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                "refund", request.getEip());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * createEipTransfer
     *
     * @param request 入参结构体
     * @return CreateEipTransferResponse
     */
    public CreateEipTransferResponse createEipTransfer(CreateEipTransferRequest request) {
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        // verify the required parameter 'transferType' is set
        checkNotNull(request.getTransferType(), "Missing the required parameter 'transferType' when calling createEipTransfer");
        // verify the required parameter 'transferResourceList' is set
        checkNotNull(request.getTransferResourceList(), "Missing the required parameter 'transferResourceList' when calling createEipTransfer");
        // verify the required parameter 'toUserId' is set
        checkNotNull(request.getToUserId(), "Missing the required parameter 'toUserId' when calling createEipTransfer");
        InternalRequest internalRequest = this.createBaseRequest(request, HttpMethodName.POST, VERSION, CONSTANT_TRANSFER);
        if (request.getClientToken() != null) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEipTransferResponse.class);
    }

    /**
     * listEipTransfer
     *
     * @param request 入参结构体
     * @return ListEipTransferResponse
     */
    public ListEipTransferResponse listEipTransfer(ListEipTransferRequest request) {
        InternalRequest internalRequest = this.createBaseRequest(new BaseBceRequest(), HttpMethodName.GET, VERSION, CONSTANT_TRANSFER);
        if (request.getMaxKeys() != null) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getDirection() != null) {
            internalRequest.addParameter("direction", request.getDirection());
        }
        if (request.getTransferId() != null) {
            internalRequest.addParameter("transferId", request.getTransferId());
        }
        if (request.getStatus() != null) {
            internalRequest.addParameter("status", request.getStatus());
        }
        if (request.getFuzzyTransferId() != null) {
            internalRequest.addParameter("fuzzyTransferId", request.getFuzzyTransferId());
        }
        if (request.getFuzzyInstanceId() != null) {
            internalRequest.addParameter("fuzzyInstanceId", request.getFuzzyInstanceId());
        }
        if (request.getFuzzyInstanceName() != null) {
            internalRequest.addParameter("fuzzyInstanceName", request.getFuzzyInstanceName());
        }
        if (request.getFuzzyInstanceIp() != null) {
            internalRequest.addParameter("fuzzyInstanceIp", request.getFuzzyInstanceIp());
        }
        return invokeHttpClient(internalRequest, ListEipTransferResponse.class);
    }

    /**
     * receiveEipTransfer
     *
     * @param request 入参结构体
     */
    public void acceptEipTransfer(AcceptEipTransferRequest request) {
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        // verify the required parameter 'transferIdList' is set
        checkNotNull(request.getTransferIdList(), "Missing the required parameter 'transferIdList' when calling receiveEipTransfer");
        InternalRequest internalRequest = this.createBaseRequest(request, HttpMethodName.PUT, VERSION, CONSTANT_TRANSFER);
        internalRequest.addParameter("accept", null);
        if (request.getClientToken() != null) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * rejectEipTransfer
     *
     * @param request 入参结构体
     */
    public void rejectEipTransfer(RejectEipTransferRequest request) {
        // verify the required parameter 'action' is set
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        // verify the required parameter 'transferIdList' is set
        checkNotNull(request.getTransferIdList(), "Missing the required parameter 'transferIdList' when calling rejectEipTransfer");
        InternalRequest internalRequest = this.createBaseRequest(request, HttpMethodName.PUT, VERSION, CONSTANT_TRANSFER);
        internalRequest.addParameter("reject", null);
        if (request.getClientToken() != null) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * cancelEipTransfer
     *
     * @param request 入参结构体
     */
    public void cancelEipTransfer(CancelEipTransferRequest request) {
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        // verify the required parameter 'transferIdList' is set
        checkNotNull(request.getTransferIdList(), "Missing the required parameter 'transferIdList' when calling cancelEipTransfer");
        InternalRequest internalRequest = this.createBaseRequest(request, HttpMethodName.PUT, VERSION, CONSTANT_TRANSFER);
        internalRequest.addParameter("cancel", null);
        if (request.getClientToken() != null) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }
}
