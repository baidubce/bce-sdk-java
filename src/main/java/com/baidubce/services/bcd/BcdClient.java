/*
 * Copyright (c) 2021 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcd;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.baidubce.AbstractBceClient;
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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcd.model.AddDomainResolveRequest;
import com.baidubce.services.bcd.model.AuditTemplateInfoRequest;
import com.baidubce.services.bcd.model.ChangeDnsRequest;
import com.baidubce.services.bcd.model.CheckOrderRequest;
import com.baidubce.services.bcd.model.CheckOrderResponse;
import com.baidubce.services.bcd.model.DeleteDomainResolveRequest;
import com.baidubce.services.bcd.model.DeleteTemplateInfoRequest;
import com.baidubce.services.bcd.model.DomainOrderResponse;
import com.baidubce.services.bcd.model.GetDomainAuditRequest;
import com.baidubce.services.bcd.model.GetDomainAuditResponse;
import com.baidubce.services.bcd.model.GetDomainDetailRequest;
import com.baidubce.services.bcd.model.GetDomainDetailResponse;
import com.baidubce.services.bcd.model.GetDomainPriceRequest;
import com.baidubce.services.bcd.model.GetDomainPriceResponse;
import com.baidubce.services.bcd.model.GetTemplateInfoRequest;
import com.baidubce.services.bcd.model.GetTemplateInfoResponse;
import com.baidubce.services.bcd.model.ListDomainResolveRequest;
import com.baidubce.services.bcd.model.ListDomainResolveResponse;
import com.baidubce.services.bcd.model.ListTemplateInfoRequest;
import com.baidubce.services.bcd.model.ListTemplateInfoResponse;
import com.baidubce.services.bcd.model.ModifyTemplateInfoRequest;
import com.baidubce.services.bcd.model.ModifyTemplateInfoResponse;
import com.baidubce.services.bcd.model.OrderPageListRequest;
import com.baidubce.services.bcd.model.PageListRequest;
import com.baidubce.services.bcd.model.RegisterDomainRequest;
import com.baidubce.services.bcd.model.RegisterDomainResponse;
import com.baidubce.services.bcd.model.RenewDomainRequest;
import com.baidubce.services.bcd.model.RenewDomainResponse;
import com.baidubce.services.bcd.model.SearchDomainRequest;
import com.baidubce.services.bcd.model.SearchDomainResponse;
import com.baidubce.services.bcd.model.TemplateRegisterDomainRequest;
import com.baidubce.services.bcd.model.TemplateUpdateOwnerRequest;
import com.baidubce.services.bcd.model.UpdateContactRequest;
import com.baidubce.services.bcd.model.UpdateDomainResolveRequest;
import com.baidubce.services.bcd.model.UpdateOwnerRequest;
import com.baidubce.services.bcd.model.UploadAuditDataRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author yangzhensheng
 * @date 2021/5/26
 * @desc Provides the client for accessing the Baidu Domain Service.
 */
public class BcdClient extends AbstractBceClient {

    private static final String VERSION_ONE = "v1";
    private static final String VERSION_TWO = "v2";
    private static final String TEMPLATE_URI_PREFIX = "/contact/template";
    private static final String DOMAIN_RESOLVE_URI_PREFIX = "/domain/resolve";
    private static final String RESOLVE_ADD = "/add";
    private static final String RESOLVE_DELETE = "/delete";
    private static final String RESOLVE_EDIT = "/edit";
    private static final String RESOLVE_LIST = "/list";
    private static final String DOMAIN_AUDIT = "/domain/audit";
    private static final String OWNER_EDIT = "/domain/owner/edit";
    private static final String CONTACT_EDIT = "/domain/contact/edit";
    private static final String CONTACT_EDIT_SUFFIX = "/contact";
    private static final String CHANGE_DNS = "/domain/changeDns";
    private static final String DOMAIN_DETAIL = "/domain/detail";
    private static final String DOMAIN_API_PREFIX = "/domain";
    private static final String DOMAIN_SEARCH = "/domain/search";
    private static final String DOMAIN_PRICE = "/domain/price";
    private static final String DOMAIN_REGISTER = "/domain/register";
    private static final String DOMAIN_TEMPLATE_REGISTER = "/domain/registerByTemplate";
    private static final String DOMAIN_ASYNC_REGISTER = "/domain/register_async";
    private static final String DOMAIN_ASYNC_TEMPLATE_REGISTER = "/domain/register_asyncByTemplate";
    private static final String DOMAIN_RENEW = "/domain/renew";
    private static final String DOMAIN_ASYNC_RENEW = "/domain/renew_async";
    private static final String CHECK_DOMAIN_ORDER = "/order/check";

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all bcd service calls.
     */
    private static final HttpResponseHandler[] BCD_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on bcd.
     */
    public BcdClient() {
        this(new BcdClientConfiguration());
    }

    /**
     * Constructs a new bcd client using the client configuration to access bcd.
     *
     * @param clientConfiguration The bcd client configuration options controlling how this client
     *                            connects to bcd (e.g. proxy settings, retry counts, etc).
     */
    public BcdClient(BcdClientConfiguration clientConfiguration) {
        super(clientConfiguration, BCD_HANDLERS);
    }

    /**
     * get all contact template infos through filter params
     *
     * @param request the request param contains a map which define the filter condition to get the template infos.
     * @return a list response object contain the total record count and the detail info about template infos.
     */
    public ListTemplateInfoResponse listTemplateInfo(ListTemplateInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, VERSION_TWO, TEMPLATE_URI_PREFIX);
        addOrderPageListParamIfNotNull(request, internalRequest);
        addParamsIfNotNull("userType", request.getUserType(), internalRequest);
        addParamsIfNotNull("email", request.getEmail(), internalRequest);
        return this.invokeHttpClient(internalRequest, ListTemplateInfoResponse.class);
    }

    /**
     * add a contact template info into the user account
     *
     * @param request the object contain some field to build a complete contact template info.
     * @return return a result templateId.
     */
    public ModifyTemplateInfoResponse addTemplateInfo(ModifyTemplateInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_TWO, TEMPLATE_URI_PREFIX);
        fillPayload(internalRequest, request);
        return this.invokeHttpClient(internalRequest, ModifyTemplateInfoResponse.class);
    }

    /**
     * get a contact template info by the templateId
     *
     * @param request the object contain a templateId to get a contact template info.
     * @return the detail info about a contact template.
     */
    public GetTemplateInfoResponse getTemplateInfo(GetTemplateInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, VERSION_TWO, TEMPLATE_URI_PREFIX,
                        request.getTemplateId());
        return this.invokeHttpClient(internalRequest, GetTemplateInfoResponse.class);
    }

    /**
     * delete a contact template info by templateId
     *
     * @param request the object contain a templateId to delete a contact template info.
     */
    public void deleteTemplateInfo(DeleteTemplateInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, VERSION_TWO, TEMPLATE_URI_PREFIX,
                        request.getTemplateId());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * update a contact template info
     *
     * @param request the object contain some new field info to update a complete contact template info. attention the
     *                templateId is must.
     */
    public void updateTemplateInfo(ModifyTemplateInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VERSION_TWO, TEMPLATE_URI_PREFIX,
                        request.getTemplateId());
        internalRequest.addParameter("update", null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * audit a contact template info
     *
     * @param request the object contain a templateId define which template to audit, and contain a audit info object.
     */
    public void auditTemplateInfo(AuditTemplateInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VERSION_TWO, TEMPLATE_URI_PREFIX,
                        request.getTemplateId());
        internalRequest.addParameter("audit", null);
        fillPayload(internalRequest, request.getAuditInfo());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * add domain resolve record
     *
     * @param request the object contain some field to build a domain resolve record.
     */
    public void addDomainResolve(AddDomainResolveRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getDomain(), checkEmptyExceptionMessageFormat("domain"));
        checkStringNotEmpty(request.getView(), checkEmptyExceptionMessageFormat("view"));
        checkStringNotEmpty(request.getRdType(), checkEmptyExceptionMessageFormat("rdType"));
        Preconditions.checkArgument(request.getTtl() >= 60 && request.getTtl() <= 3600,
                checkEmptyExceptionMessageFormat("ttl"));
        checkStringNotEmpty(request.getRdata(), checkEmptyExceptionMessageFormat("rdata"));
        checkStringNotEmpty(request.getZoneName(), checkEmptyExceptionMessageFormat("zoneName"));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_RESOLVE_URI_PREFIX, RESOLVE_ADD);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * delete domain resolve record by recordId and zoneName
     *
     * @param request the object contain a recordId and a zoneName define which domain resolve to delete
     */
    public void deleteDomainResolve(DeleteDomainResolveRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getRecordId(), checkEmptyExceptionMessageFormat("recordId"));
        checkStringNotEmpty(request.getZoneName(), checkEmptyExceptionMessageFormat("zoneName"));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_RESOLVE_URI_PREFIX,
                        RESOLVE_DELETE);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * update a domain resolve record info
     *
     * @param request the object contain some new field info to update a domain resolve record. attention the
     *                recordId is must.
     */
    public void updateDomainResolve(UpdateDomainResolveRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getDomain(), checkEmptyExceptionMessageFormat("domain"));
        checkStringNotEmpty(request.getView(), checkEmptyExceptionMessageFormat("view"));
        checkStringNotEmpty(request.getRdType(), checkEmptyExceptionMessageFormat("rdType"));
        Preconditions.checkArgument(request.getTtl() >= 60 && request.getTtl() <= 3600,
                checkEmptyExceptionMessageFormat("ttl"));
        checkStringNotEmpty(request.getRdata(), checkEmptyExceptionMessageFormat("rdata"));
        checkStringNotEmpty(request.getZoneName(), checkEmptyExceptionMessageFormat("zoneName"));
        Preconditions.checkNotNull(request.getRecordId(), checkEmptyExceptionMessageFormat("recordId"));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_RESOLVE_URI_PREFIX, RESOLVE_EDIT);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * get all the domain resolve record list.
     *
     * @param request the request param contain some field which define the filter condition to get the record infos,
     *                which the domain field is necessary.
     * @return a list response object contain the total record count and the detail info about domain record.
     */
    public ListDomainResolveResponse listDomainResolve(ListDomainResolveRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_RESOLVE_URI_PREFIX, RESOLVE_LIST);
        fillPayload(internalRequest, request);
        return this.invokeHttpClient(internalRequest, ListDomainResolveResponse.class);
    }

    /**
     * change the owner info the domain
     *
     * @param request the request object contain the change information to modify
     */
    public void updateOwner(UpdateOwnerRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VERSION_ONE, OWNER_EDIT);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * change the owner info the domain by template
     *
     * @param request the request object contain the templates
     */
    public void updateOwnerByTemplate(TemplateUpdateOwnerRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getDomain(), "param domain should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, VERSION_TWO,
                DOMAIN_API_PREFIX, request.getDomain(), CONTACT_EDIT_SUFFIX);
        internalRequest.addParameter("update", "");
        addParamsIfNotNull("templateId", request.getTemplateId(), internalRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * change the contact info the domain
     *
     * @param request the request object contain the change information to modify
     */
    public void updateContact(UpdateContactRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VERSION_ONE, CONTACT_EDIT);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * get the audit status about the domain
     *
     * @param request the request object contain which domain audit status to get.
     * @return the response object contain the description and audit status about the request domain.
     */
    public GetDomainAuditResponse getDomainAudit(GetDomainAuditRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkArgument(StringUtils.isNotBlank(request.getDomain()), "domain should not be null");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VERSION_ONE, DOMAIN_AUDIT);
        internalRequest.addParameter("domain", request.getDomain());
        fillPayload(internalRequest, request);
        return this.invokeHttpClient(internalRequest, GetDomainAuditResponse.class);
    }

    /**
     * change the dns configuration of request domain
     *
     * @param request the request object contain which domain audit status to get.
     * @return the response object contain the description and audit status about the request domain.
     */
    public void changeDns(ChangeDnsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VERSION_ONE, CHANGE_DNS);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * get the detail info about request domain
     *
     * @param request the request object contain which domain info to get.
     * @return the response object contain the detail info about the request domain.
     */
    public GetDomainDetailResponse getDomainDetail(GetDomainDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VERSION_ONE, DOMAIN_DETAIL);
        internalRequest.addParameter("domain", request.getDomain());
        fillPayload(internalRequest, request);
        return this.invokeHttpClient(internalRequest, GetDomainDetailResponse.class);
    }

    /**
     * audit a domain info
     *
     * @param request the object contain some filed to audit the request domain to a normal state.
     */
    public void uploadAudit(UploadAuditDataRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_AUDIT);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * search domain register
     *
     * @param request contains the domain to search.
     */
    public SearchDomainResponse searchDomain(SearchDomainRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VERSION_ONE, DOMAIN_SEARCH);
        addParamsIfNotNull("domain", request.getDomain(), internalRequest);
        return invokeHttpClient(internalRequest, SearchDomainResponse.class);
    }

    /**
     * get domain price
     *
     * @param request contains the domain to get price.
     */
    public GetDomainPriceResponse getDomainPrice(GetDomainPriceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VERSION_ONE, DOMAIN_PRICE);
        addParamsIfNotNull("domain", request.getDomain(), internalRequest);
        return invokeHttpClient(internalRequest, GetDomainPriceResponse.class);
    }

    /**
     * register domain
     *
     * @param request domain register detail
     */
    public RegisterDomainResponse registerDomain(RegisterDomainRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_REGISTER);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RegisterDomainResponse.class);
    }

    /**
     * async register domain
     *
     * @param request domain register detail
     */
    public DomainOrderResponse asyncRegisterDomain(RegisterDomainRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_ASYNC_REGISTER);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DomainOrderResponse.class);
    }

    /**
     * register domain by template id
     *
     * @param request domain register detail and template id
     */
    public RegisterDomainResponse registerDomainByTemplate(TemplateRegisterDomainRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_TEMPLATE_REGISTER);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RegisterDomainResponse.class);
    }

    /**
     * async register domain by template id
     *
     * @param request domain register detail and template id
     */
    public DomainOrderResponse asyncRegisterDomainByTemplate(TemplateRegisterDomainRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_ASYNC_TEMPLATE_REGISTER);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DomainOrderResponse.class);
    }

    /**
     * check order status after async register domain
     *
     * @param request
     */
    public CheckOrderResponse checkOrderStatus(CheckOrderRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, VERSION_ONE, CHECK_DOMAIN_ORDER);
        addParamsIfNotNull("bceOrderId", request.getBceOrderId(), internalRequest);
        return invokeHttpClient(internalRequest, CheckOrderResponse.class);
    }

    /**
     * renew domain
     *
     * @param request domain renew detail
     */
    public RenewDomainResponse renewDomain(RenewDomainRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_RENEW);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RenewDomainResponse.class);
    }

    /**
     * renew domain async
     *
     * @param request domain renew detail
     */
    public DomainOrderResponse asyncRenewDomain(RenewDomainRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VERSION_ONE, DOMAIN_ASYNC_RENEW);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DomainOrderResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified bcd resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param version       the version which the service method to invoke
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod, String version,
                                          String... pathVariables) {
        List<String> path = Lists.newArrayList();

        path.add(version);

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
     * the method to fill the internalRequest's content field with bceRequest
     * only support HttpMethodName.POST or HttpMethodName.PUT
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

    private void addOrderPageListParamIfNotNull(OrderPageListRequest request, InternalRequest internalRequest) {
        addPageListParamIfNotNull(request, internalRequest);
        addParamsIfNotNull("order", request.getOrder(), internalRequest);
        addParamsIfNotNull("orderBy", request.getOrderBy(), internalRequest);
    }

    private void addPageListParamIfNotNull(PageListRequest request, InternalRequest internalRequest) {
        addParamsIfNotNull("pageNo", request.getPageNo(), internalRequest);
        addParamsIfNotNull("pageSize", request.getPageSize(), internalRequest);
    }

    private void addParamsIfNotNull(String name, Object value, InternalRequest internalRequest) {
        if (value == null) {
            return;
        }
        internalRequest.addParameter(name, value.toString());
    }
}