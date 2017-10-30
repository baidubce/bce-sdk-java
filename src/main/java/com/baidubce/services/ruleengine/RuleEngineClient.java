package com.baidubce.services.ruleengine;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
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
import com.baidubce.model.GenericAccountRequest;

import com.baidubce.services.ruleengine.model.CreateRuleRequest;
import com.baidubce.services.ruleengine.model.DeleteRulesRequest;
import com.baidubce.services.ruleengine.model.Destination;
import com.baidubce.services.ruleengine.model.ListRuleRequest;
import com.baidubce.services.ruleengine.model.ListRuleResponse;
import com.baidubce.services.ruleengine.model.Rule;
import com.baidubce.services.ruleengine.model.UpdateRuleRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanyoujun on 2016/10/9.
 *
 * Client to create/update/delete/list rules for IoT
 */
public class RuleEngineClient extends AbstractBceClient {

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String VERSION = "v1";
    private static final String RULES = "rules";
    private static final String DISABLE = "disable";
    private static final String ENABLE = "enable";
    private static final String BATCH = "batch";
    private static final String DELETE = "delete";
    private static final String DESTINATIONS = "destinations";
    private static final String ENDPOINT = "re.iot.gz.baidubce.com";

    /**
     * Responsible for handling HttpResponse from all Tsdb service calls.
     */
    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public RuleEngineClient(BceClientConfiguration config) {

        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, HANDLERS);
    }

    public RuleEngineClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                .withEndpoint(ENDPOINT));
    }

    /**
     * list all the rules under this account
     * @param request: specify the pageNo, pageSize etc.
     * @return all or a subset of rules according to pageNo, and pageSize
     */
    public ListRuleResponse listRules(ListRuleRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.GET, RULES);
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        return this.invokeHttpClient(internalRequest, ListRuleResponse.class);
    }

    public Rule getRule(String uuid) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.GET, RULES, uuid);
        return this.invokeHttpClient(internalRequest, Rule.class);
    }

    public Rule createRule(CreateRuleRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, RULES);
        return this.invokeHttpClient(internalRequest, Rule.class);
    }

    public Rule updateRule(UpdateRuleRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, RULES, request.getUuid());
        return this.invokeHttpClient(internalRequest, Rule.class);
    }

    public void deleteRule(DeleteRulesRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, RULES, BATCH, DELETE);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void disableRule(String ruleId) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.PUT, RULES, ruleId, DISABLE);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void enableRule(String ruleId) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.PUT, RULES, ruleId, ENABLE);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public Rule createDestination(Destination request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, DESTINATIONS);
        return this.invokeHttpClient(internalRequest, Rule.class);
    }

    public void deleteDestination(String uuid) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.DELETE, DESTINATIONS, uuid);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

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

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillInHeadAndBody(bceRequest, request);
        }

        return request;
    }

    private void fillInHeadAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }

    private byte[] toJson(AbstractBceRequest bceRequest) {
        String jsonStr = JsonUtils.toJsonString(bceRequest);
        try {
            return jsonStr.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }
}
