package com.baidubce.services.aihc.core;

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
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * AIHC客户端核心抽象类
 * 提供公共的HTTP请求处理功能，供各个模块继承使用
 */
public abstract class AihcClientCore extends AbstractBceClient {

    /**
     * AIHC API 路径前缀
     */
    protected static final String APIPREFIX = "/";

    /**
     * AIHC API 版本
     */
    protected static final String VERSION = "v2";

    /**
     * HTTP响应处理器
     */
    private static HttpResponseHandler[] aihcHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * 构造函数
     */
    public AihcClientCore() {
        this(new BceClientConfiguration());
    }

    /**
     * 构造函数
     *
     * @param clientConfiguration BCE客户端配置
     */
    public AihcClientCore(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, aihcHandlers);
    }

    /**
     * 重写computeServiceId方法，避免包名检查错误
     */
    @Override
    public String computeServiceId() {
        return "aihc";
    }

    /**
     * 填充请求体内容
     * 仅支持POST或GET方法
     *
     * @param internalRequest 内部请求对象
     * @param bceRequest 原始BCE请求
     */
    protected void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.GET) {
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
     * 创建并初始化请求对象
     *
     * @param bceRequest 原始BCE请求
     * @param httpMethod HTTP方法
     * @param prefix URI前缀
     * @param pathVariables 路径变量
     * @return 内部请求对象
     */
    protected InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String prefix, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(prefix);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setCredentials(bceRequest.getRequestCredentials());
        request.addHeader("version", VERSION);

        return request;
    }

    /**
     * 创建标准AIHC请求
     *
     * @param bceRequest 原始BCE请求
     * @param httpMethod HTTP方法
     * @param action API动作
     * @return 内部请求对象
     */
    protected InternalRequest createAihcRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String action) {
        InternalRequest internalRequest = this.createRequest(bceRequest, httpMethod, APIPREFIX);
        internalRequest.addParameter("action", action);
        return internalRequest;
    }
} 