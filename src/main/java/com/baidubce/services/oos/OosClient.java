package com.baidubce.services.oos;

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
import com.baidubce.services.oos.model.common.TemplateType;
import com.baidubce.services.oos.model.request.BaseTemplateRequest;
import com.baidubce.services.oos.model.request.BaseExecutionRequest;
import com.baidubce.services.oos.model.request.OperatorListRequest;
import com.baidubce.services.oos.model.request.TemplateListRequest;
import com.baidubce.services.oos.model.response.BaseExecutionResponse;
import com.baidubce.services.oos.model.response.BaseTemplateResponse;
import com.baidubce.services.oos.model.response.CheckTemplateResponse;
import com.baidubce.services.oos.model.response.OperatorListResponse;
import com.baidubce.services.oos.model.response.TemplateListResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;

import static com.baidubce.services.media.MediaClient.REQUEST_NULL_ERROR_MESSAGE;
import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

public class OosClient extends AbstractBceClient {

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all oos service calls.
     */
    private static final HttpResponseHandler[] OOS_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };


    /**
     * Constructs a new client to invoke service methods on oos.
     */
    public OosClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new bbc client using the client configuration to access oos.
     *
     * @param clientConfiguration The bcc client configuration options controlling how this client
     *                            connects to bbc (e.g. proxy settings, retry counts, etc).
     */
    public OosClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, OOS_HANDLERS);
    }

    /**
     * create get request with url
     * @param bceRequest request body object
     * @param httpMethod method name，such as put, post, delete et
     * @param url method url
     * @return InternalRequest
     */
    private InternalRequest createRequestWithUrl(AbstractBceRequest bceRequest, HttpMethodName httpMethod, String url) {
        URI uri = HttpUtils.appendUri(this.getEndpoint(), url);
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * create request with json format body
     * @param bceRequest request body object
     * @param httpMethod method name，such as put, post, delete et
     * @param url method url
     * @return InternalRequest
     */
    private InternalRequest createBodyRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod, String url) {
        URI uri = HttpUtils.appendUri(this.getEndpoint(), url);
        InternalRequest request = new InternalRequest(httpMethod, uri);
        fillPayload(request, bceRequest);
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
    protected void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT
                || internalRequest.getHttpMethod() == HttpMethodName.PATCH) {
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
     * 创建模板接口
     * @param request
     * @return
     */
    public BaseTemplateResponse createTemplate(BaseTemplateRequest request) {
        checkBaseTemplateRequest(request);
        InternalRequest internalRequest =  this.createBodyRequest(request, HttpMethodName.POST,
                "/api/logic/oos/v2/template");
        return invokeHttpClient(internalRequest, BaseTemplateResponse.class);
    }

    /**
     * 校验模板接口
     * @param request
     * @return
     */
    public CheckTemplateResponse checkTemplate(BaseTemplateRequest request) {
        checkBaseTemplateRequest(request);
        InternalRequest internalRequest =  this.createBodyRequest(request, HttpMethodName.POST,
                "/api/logic/oos/v2/template/check");
        return invokeHttpClient(internalRequest, CheckTemplateResponse.class);
    }

    /**
     * 更新模板接口
     * @param request
     * @return
     */
    public BaseTemplateResponse updateTemplate(BaseTemplateRequest request) {
        checkBaseTemplateRequest(request);
        InternalRequest internalRequest =  this.createBodyRequest(request, HttpMethodName.PUT,
                "/api/logic/oos/v2/template");
        return invokeHttpClient(internalRequest, BaseTemplateResponse.class);
    }

    /**
     * 删除模板接口
     * @param templateId
     * @return
     */
    public BaseTemplateResponse deleteTemplate(String templateId) {
        checkStringNotEmpty(templateId, checkEmptyExceptionMessageFormat("templateId"));
        BaseTemplateRequest request = new BaseTemplateRequest();
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.DELETE,
                "/api/logic/oos/v2/template");
        internalRequest.addParameter("id", templateId);
        return invokeHttpClient(internalRequest, BaseTemplateResponse.class);
    }

    /**
     * 获取模板详情接口
     * @param templateName
     * @param templateType
     * @return
     */
    public BaseTemplateResponse getTemplateDetail(String templateName, TemplateType templateType) {
        checkStringNotEmpty(templateName, checkEmptyExceptionMessageFormat("templateName"));
        BaseTemplateRequest request = new BaseTemplateRequest();
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET,
                "/api/logic/oos/v2/template");
        internalRequest.addParameter("name", templateName);
        internalRequest.addParameter("type", templateType.toString());
        return invokeHttpClient(internalRequest, BaseTemplateResponse.class);
    }

    /**
     * 获取模板列表接口
     * @param request
     * @return
     */
    public TemplateListResponse getTemplateList(TemplateListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkIsTrue(request.getPageNo() > 0, "pageNo should gt 0");
        checkIsTrue(request.getPageSize() > 0 && request.getPageSize() <= 100,
                "pageSize should gt 0 and lt 100");
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/api/logic/oos/v2/template/list");
        return invokeHttpClient(internalRequest, TemplateListResponse.class);
    }

    /**
     * 获取算子列表接口
     * @param request
     * @return
     */
    public OperatorListResponse getOperatorList(OperatorListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkIsTrue(request.getPageNo() > 0, "pageNo should gt 0");
        checkIsTrue(request.getPageSize() > 0 && request.getPageSize() <= 100,
                "pageSize should gt 0 and lt 100");
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/api/logic/oos/v1/operator/list");
        return invokeHttpClient(internalRequest, OperatorListResponse.class);
    }

    /**
     * 创建执行接口
     * @param request
     * @return
     */
    public BaseExecutionResponse createExecution(BaseExecutionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkIsTrue(null != request.getTemplate(), "template should not be null");
        checkIsTrue(StringUtils.isNotEmpty(request.getTemplate().getName()) ||
                StringUtils.isNotEmpty(request.getTemplate().getRef()),
                "neither template ref nor template name is set");
        InternalRequest internalRequest =  this.createBodyRequest(request, HttpMethodName.POST,
                "/api/logic/oos/v2/execution");
        return invokeHttpClient(internalRequest, BaseExecutionResponse.class);
    }

    /**
     * 获取执行详情接口
     * @param executionId
     * @return
     */
    public BaseExecutionResponse getExecutionDetail(String executionId) {
        checkStringNotEmpty(executionId, checkEmptyExceptionMessageFormat("executionId"));
        BaseExecutionRequest request = new BaseExecutionRequest();
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET,
                "/api/logic/oos/v2/execution");
        internalRequest.addParameter("id", executionId);
        return invokeHttpClient(internalRequest, BaseExecutionResponse.class);
    }

    private void checkBaseTemplateRequest(BaseTemplateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat("name"));
        checkIsTrue(CollectionUtils.isNotEmpty(request.getOperators()), "operators should not be empty");
        if (CollectionUtils.isEmpty(request.getLinks())) {
            checkIsTrue(request.isLinear(), "linear is false, links should not be empty");
        }
    }
}
