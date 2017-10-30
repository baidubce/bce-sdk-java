package com.baidubce.services.vodpro;

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
import com.baidubce.services.vodpro.model.adaptor.RequestType;
import com.baidubce.services.vodpro.model.adaptor.request.QueryVcaRequest;
import com.baidubce.services.vodpro.model.adaptor.request.QueryVcrRequest;
import com.baidubce.services.vodpro.model.adaptor.request.TaskStartRequest;
import com.baidubce.services.vodpro.model.adaptor.response.QueryVcaResponse;
import com.baidubce.services.vodpro.model.adaptor.response.QueryVcrResponse;
import com.baidubce.services.vodpro.model.adaptor.response.TaskStartResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 17/10/9
 *
 * @author liumin08
 */
public class AdaptorClient extends AbstractBceClient {

    private static final String VERSION = "v1";

    private static final String PATH_TASK = "task";

    private static HttpResponseHandler[] vodProAdaptorHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public AdaptorClient() {
        this(new BceClientConfiguration());
    }

    public AdaptorClient(BceClientConfiguration config) {
        super(config, vodProAdaptorHandlers);
    }

    public TaskStartResponse startTask(RequestType type, String url, String description, String preset) {
        TaskStartRequest request = new TaskStartRequest();
        request.setType(type);
        request.setDescription(description);
        request.setPreset(preset);
        request.setUrl(url);
        return startTask(request);
    }

    private TaskStartResponse startTask(TaskStartRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.POST,
                request, VERSION, PATH_TASK);
        internalRequest.addParameter("type", request.getType().toString());
        return this.invokeHttpClient(internalRequest, TaskStartResponse.class);
    }

    public QueryVcaResponse queryAdaptorVca(String url) {
        QueryVcaRequest request = new QueryVcaRequest();
        request.setUrl(url);
        return queryAdaptorVca(request);
    }

    private QueryVcaResponse queryAdaptorVca(QueryVcaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, VERSION, PATH_TASK);
        internalRequest.addParameter("vca", null);
        internalRequest.addParameter("url", request.getUrl());

        return this.invokeHttpClient(internalRequest, QueryVcaResponse.class);
    }

    public QueryVcrResponse queryAdaptorVcr(String url) {
        QueryVcrRequest request = new QueryVcrRequest();
        request.setUrl(url);
        return queryAdaptorVcr(request);
    }

    private QueryVcrResponse queryAdaptorVcr(QueryVcrRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, VERSION, PATH_TASK);
        internalRequest.addParameter("vcr", null);
        internalRequest.addParameter("url", request.getUrl());
        return this.invokeHttpClient(internalRequest, QueryVcrResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     * This method is responsible for determining HTTP method, URI path,
     * credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.
     * </p>
     *
     * @param httpMethod The HTTP method to use when sending the request.
     * @param request The original request, as created by the user.
     * @param pathVariables The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path,
     * ready for callers to populate any additional headers or
     * parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, AbstractBceRequest request, String... pathVariables) {

        // build URL paths
        List<String> pathComponents = new ArrayList<String>();

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        URI uri = appendUri(getEndpoint(), pathComponents.toArray(new String[pathComponents.size()]));

        // get a InternalRequest instance and set headers
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        internalRequest.setCredentials(request.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillRequestPayload(internalRequest, request);
        }
        return internalRequest;
    }

    private InternalRequest fillRequestPayload(InternalRequest internalRequest, AbstractBceRequest request) {
        String strJson = JsonUtils.toJsonString(request);
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));

        return internalRequest;
    }

    public URI appendUri(URI baseUri, String... pathComponents) {
        StringBuilder builder = new StringBuilder(baseUri.toASCIIString());
        for (String path : pathComponents) {
            if (path != null && path.length() > 0) {
                path = HttpUtils.normalize(path).replace("%2F", "/").replace("%3F", "?");
                if (path.startsWith("/")) {
                    if (builder.charAt(builder.length() - 1) == '/') {
                        builder.setLength(builder.length() - 1);
                    }
                } else {
                    if (builder.charAt(builder.length() - 1) != '/') {
                        builder.append('/');
                    }
                }
                builder.append(path);
            }
        }
        try {
            return new URI(builder.toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Unexpected error", e);
        }
    }
}
