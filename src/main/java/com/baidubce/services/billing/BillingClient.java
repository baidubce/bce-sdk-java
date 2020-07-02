/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.billing;

import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.baidubce.services.billing.model.ResourceMonthBillRequest;
import com.baidubce.services.billing.model.ResourceMonthBillResponse;
import com.baidubce.services.billing.model.bill.PrepayShareBillRequest;
import com.baidubce.services.billing.model.bill.PrepayShareBillResponse;
import com.baidubce.services.billing.model.finance.SupervisorBalanceTransferRequest;
import com.baidubce.services.billing.model.finance.SupervisorTransactionPageRequest;
import com.baidubce.services.billing.model.finance.SupervisorTransactionResponse;
import com.baidubce.services.billing.model.finance.TransferResultResponse;
import com.baidubce.services.billing.model.order.OrderListRequest;
import com.baidubce.services.billing.model.order.OrderListResponse;
import com.baidubce.services.billing.model.price.CpcPricingRequest;
import com.baidubce.services.billing.model.price.CptPricingRequest;
import com.baidubce.services.billing.model.price.PricingQueryResponse;
import com.baidubce.services.billing.model.renew.RenewResourceListRequest;
import com.baidubce.services.billing.model.renew.RenewResourceResponse;
import com.baidubce.services.billing.model.renew.ResourceAutoRenewRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Provides the client for accessing the Baidu Billing Service(billing).
 */
public class BillingClient extends AbstractBceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingClient.class);

    private static final String VERSION_V1 = "v1";
    private static final String BILL = "bill";
    private static final String RESOURCE = "resource";
    private static final String RENEW = "renew";
    private static final String MONTH = "month";
    private static final String SHARE_PREPAY = "share/prepay";
    private static final String PRICE = "price";
    private static final String CPC = "cpc";
    private static final String CPT = "cpt";
    private static final String ORDER = "order";
    private static final String LIST = "list";

    private static final String FINANCE = "finance";
    private static final String SUPERVISOR = "supervisor";
    private static final String BALANCE = "balance";
    private static final String TRANSFER = "transfer";
    private static final String GATHER = "gather";
    private static final String CASH = "cash";
    private static final String TRANSACTION = "transaction";

    /**
     * Responsible for handling httpResponses from all billing service calls.
     */
    private static HttpResponseHandler[] billingHandlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on billing with default configuration.
     */
    public BillingClient() {
        this(new BillingClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on billing default configuration.
     */
    public BillingClient(BceClientConfiguration config) {
        super(config, billingHandlers);
    }

    /**
     * Query the detail resource month bills.
     * Set the parameter queryAccountId if want to query the sub account's bills whole is in finance circle.
     *
     * @param request The request containing all options for getting the bills info.
     * @return detailed resource month bill list.
     */
    public ResourceMonthBillResponse getResourceMonthBill(ResourceMonthBillRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkIsTrue(!StringUtils.isEmpty(request.getBeginTime()) || !StringUtils.isEmpty(request.getEndTime())
                        || !StringUtils.isEmpty(request.getMonth()),
                "Parameters month , beginTime and endTime cannot all be empty!");
        checkIsTrue(!(StringUtils.isEmpty(request.getBeginTime()) && !StringUtils.isEmpty(request.getEndTime())),
                "If parameter endTime is not empty, parameter beginTime cannot be empty!");
        checkIsTrue(!(!StringUtils.isEmpty(request.getBeginTime()) && StringUtils.isEmpty(request.getEndTime())),
                "If parameter beginTime is not empty, parameter endTime cannot be empty!");
        checkStringNotEmpty(request.getProductType(), "The parameter productType should NOT be null");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VERSION_V1, BILL, RESOURCE,
                MONTH);
        if (request.getMonth() != null) {
            internalRequest.addParameter(MONTH, request.getMonth());
        }
        if (request.getBeginTime() != null) {
            internalRequest.addParameter("beginTime", request.getBeginTime());
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        internalRequest.addParameter("productType", request.getProductType());

        if (request.getServiceType() != null) {
            internalRequest.addParameter("serviceType", request.getServiceType());
        }
        if (request.getQueryAccountId() != null) {
            internalRequest.addParameter("queryAccountId", request.getQueryAccountId());
        }
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }

        return invokeHttpClient(internalRequest, ResourceMonthBillResponse.class);
    }

    /**
     * Query the detail prepay share bills.
     * Set the parameter queryAccountId if want to query the sub account's bills whole is in finance circle.
     *
     * @param request The request containing all options for getting the bills info.
     * @return detailed prepay share bill list.
     */
    public PrepayShareBillResponse queryPrepayShareBill(PrepayShareBillRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkIsTrue(!StringUtils.isEmpty(request.getMonth()), "The parameter month cannot be empty!");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VERSION_V1, BILL, SHARE_PREPAY);
        if (request.getMonth() != null) {
            internalRequest.addParameter(MONTH, request.getMonth());
        }
        if (request.getServiceType() != null) {
            internalRequest.addParameter("serviceType", request.getServiceType());
        }
        if (request.getQueryAccountId() != null) {
            internalRequest.addParameter("queryAccountId", request.getQueryAccountId());
        }
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }

        return invokeHttpClient(internalRequest, PrepayShareBillResponse.class);
    }

    /**
     * Query the order list
     *
     * @param request The request containing all options for getting the order info.
     * @return detailed order information list.
     */
    public OrderListResponse getOrderList(OrderListRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VERSION_V1, ORDER, LIST);

        return invokeHttpClient(internalRequest, OrderListResponse.class);
    }

    /**
     * Query the detail price of charge items
     *
     * @param request The request containing all options for getting the price info.
     * @return detail price.
     */
    public PricingQueryResponse getSpecificCpcPrice(CpcPricingRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getServiceType(), "The parameter serviceType should NOT be null");
        checkStringNotEmpty(request.getProductType(), "The parameter productType should NOT be null");
        checkStringNotEmpty(request.getRegion(), "The parameter region should NOT be null");
        checkNotNull(request.getFlavor(), "The parameter flavor should NOT be null");
        checkNotNull(request.getFlavor().getChargeItems(), "The parameter chargeItems should NOT be null");
        checkIsTrue(request.getFlavor().getChargeItems().size() > 0, "The parameter chargeItems should NOT be empty!");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VERSION_V1, PRICE, CPC);

        return invokeHttpClient(internalRequest, PricingQueryResponse.class);
    }

    /**
     * Query the detail price of charge items
     *
     * @param request The request containing all options for getting the price info.
     * @return detail price.
     */
    public PricingQueryResponse getSpecificCptPrice(CptPricingRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getServiceType(), "The parameter serviceType should NOT be null");
        checkStringNotEmpty(request.getProductType(), "The parameter productType should NOT be null");
        checkStringNotEmpty(request.getRegion(), "The parameter region should NOT be null");
        checkNotNull(request.getFlavor(), "The parameter flavor should NOT be null");
        checkNotNull(request.getFlavor().getChargeItems(), "The parameter chargeItems should NOT be null");
        checkIsTrue(request.getFlavor().getChargeItems().size() > 0, "The parameter chargeItems should NOT be empty!");
        checkNotNull(request.getPeriod(), "The parameter period should NOT be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VERSION_V1, PRICE, CPT);

        return invokeHttpClient(internalRequest, PricingQueryResponse.class);
    }

    /**
     * Query auto renew resource list
     * Set the parameter queryAccountId if want to query the sub account's bills whole is in finance circle.
     *
     * @param request The request containing all options for getting the resource list info.
     * @return detailed prepay resource list
     */
    public RenewResourceResponse queryRenewResourceList(RenewResourceListRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkIsTrue(!StringUtils.isEmpty(request.getServiceType()), "The parameter serviceType cannot be empty!");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VERSION_V1, RENEW,
                "/resource/list");

        return invokeHttpClient(internalRequest, RenewResourceResponse.class);
    }

    /**
     * SET auto renew resource rule
     * Set the parameter accountId if want to manage the sub account's resource who is in finance circle.
     *
     * @param request The request containing all options for set the resource list renew rule.
     */
    public void setRenewResourceRule(ResourceAutoRenewRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getServiceType(), "The parameter serviceType should NOT be null");
        checkStringNotEmpty(request.getRegion(), "The parameter region should NOT be null");
        checkStringNotEmpty(request.getInstanceId(), "The parameter instanceId should NOT be null");
        checkNotNull(request.getRenewTime(), "The parameter renewTime should NOT be null.");
        checkNotNull(request.getRenewTimeUnit(), "The parameter renewTimeUnit should NOT be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VERSION_V1, RENEW,
                "/resource/rule/create");

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * do balance transfer form supervisor to supervised account
     *
     * @param request request contain supervisedId and amount
     * @return transfer result
     */
    public TransferResultResponse balanceTransfer(SupervisorBalanceTransferRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getSupervisedId(), "The parameter supervisedId should NOT be null");
        checkNotNull(request.getAmount(), "The parameter amount should NOT be null");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VERSION_V1, FINANCE, SUPERVISOR,
                BALANCE, TRANSFER);
        return invokeHttpClient(internalRequest, TransferResultResponse.class);
    }

    /**
     * do balance gather form supervised account to supervisor
     *
     * @param request request contain supervisedId and amount
     * @return gather result
     */
    public TransferResultResponse balanceGather(SupervisorBalanceTransferRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getSupervisedId(), "The parameter supervisedId should NOT be null");
        checkNotNull(request.getAmount(), "The parameter amount should NOT be null");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VERSION_V1, FINANCE, SUPERVISOR,
                BALANCE, GATHER);
        return invokeHttpClient(internalRequest, TransferResultResponse.class);
    }

    /**
     * get the transfer record
     *
     * @param request request contain time and page param
     * @return page result
     */
    public SupervisorTransactionResponse transactionList(SupervisorTransactionPageRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getBeginTime(), "The parameter beginTime should NOT be null");
        checkStringNotEmpty(request.getEndTime(), "The parameter endTime should NOT be null");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VERSION_V1, FINANCE, SUPERVISOR,
                CASH, TRANSACTION);
        return invokeHttpClient(internalRequest, SupervisorTransactionResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified billing resource. This method is responsible
     * for determining HTTP method, URI path,credentials and request body for POST method.
     *
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
            String... pathVariables) {
        List<String> path = new ArrayList<String>();    // build URL paths

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));

        // get a InternalRequest instance and set headers
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setCredentials(bceRequest.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillRequestPayload(request, bceRequest);
        }
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
    private void fillRequestPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
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

}
