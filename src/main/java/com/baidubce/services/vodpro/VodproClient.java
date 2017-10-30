package com.baidubce.services.vodpro;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.vodpro.model.common.Path;
import com.baidubce.services.vodpro.model.request.CreateMediaRequest;
import com.baidubce.services.vodpro.model.request.GetMediaRequest;
import com.baidubce.services.vodpro.model.response.GetVcaResultResponse;
import com.baidubce.services.vodpro.model.response.GetVcrResultResponse;
import com.baidubce.services.vodpro.model.response.MediaResponse;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.baidubce.services.vodpro.Constants.PATH_MEDIA;
import static com.baidubce.services.vodpro.Constants.PATH_PROJECT;
import static com.baidubce.services.vodpro.Constants.PATH_SPACE;
import static com.baidubce.services.vodpro.Constants.TYPE_VCA;
import static com.baidubce.services.vodpro.Constants.TYPE_VCR;
import static com.baidubce.services.vodpro.Constants.VERSION;
import static com.baidubce.util.HttpUtils.appendUri;
import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * Created on 17/8/24
 *
 * @author liumin08
 */
public class VodproClient extends AbstractBceClient {

    private static HttpResponseHandler[] vodproHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };


    public VodproClient() {
        this(new BceClientConfiguration());
    }

    public VodproClient(BceClientConfiguration config) {
        super(config, vodproHandlers);
    }

    public MediaResponse createMedia(String projectName, String spaceName,
            String path, String triggerName,
            String notificationName, String description) {
        CreateMediaRequest request = new CreateMediaRequest();
        request.setPath(new Path(path));
        request.setDescription(description);
        request.setNotificationName(notificationName);
        request.setTriggerName(triggerName);
        return createMedia(request, projectName, spaceName);
    }

    public MediaResponse createMedia(CreateMediaRequest request,
            String projectName, String spaceName) throws BceServiceException {

        checkStringNotEmpty(projectName,
                "The parameter projectName should not be null or empty string.");
        checkStringNotEmpty(spaceName,
                "The parameter spaceName should not be null or empty string.");
        checkStringNotEmpty(request.getPath().getMe(),
                "The parameter path should not be null or empty string.");

        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, VERSION, PATH_PROJECT,
                projectName, PATH_SPACE, spaceName, PATH_MEDIA);

        String json = JsonUtils.toJsonString(request);
        byte[] requestJson = null;
        try {
            requestJson = json.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        return invokeHttpClient(internalRequest, MediaResponse.class);
    }

    public MediaResponse getMedia(String projectName, String spaceName, String path) {
        GetMediaRequest request = new GetMediaRequest();
        request.setProjectName(projectName);
        request.setSpaceName(spaceName);
        request.setPath(new Path(path));
        return getMedia(request);

    }

    private MediaResponse getMedia(GetMediaRequest request) {

        InternalRequest internalRequest =
            createRequest(HttpMethodName.GET, request, VERSION, PATH_PROJECT, request.getProjectName(),
                PATH_SPACE, request.getSpaceName(), PATH_MEDIA, request.getPath().getMe());
        return this.invokeHttpClient(internalRequest, MediaResponse.class);
    }

    public GetVcaResultResponse getVcaResult(
            String projectName, String spaceName, String path, String type) {
        if (TYPE_VCA.equals(type)) {
            GetMediaRequest request = new GetMediaRequest();
            request.setProjectName(projectName);
            request.setSpaceName(spaceName);
            request.setPath(new Path(path));
            request.setType(TYPE_VCA);
            return getVcaResult(request);
        } else {
            throw new BceClientException("The type is wrong, maybe you should set type to vca");
        }
    }

    private GetVcaResultResponse getVcaResult(GetMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, VERSION,
            PATH_PROJECT, request.getProjectName(), PATH_SPACE, request.getSpaceName(), PATH_MEDIA,
                request.getPath().getMe());
        internalRequest.addParameter(TYPE_VCA, null);

        return this.invokeHttpClient(internalRequest, GetVcaResultResponse.class);
    }

    public GetVcrResultResponse getVcrResult(String projectName,
            String spaceName,
            String path,
            String type) {
        if (TYPE_VCR.equals(type)) {
            GetMediaRequest request = new GetMediaRequest();
            request.setProjectName(projectName);
            request.setSpaceName(spaceName);
            request.setPath(new Path(path));
            request.setType(TYPE_VCR);
            return getVcrResult(request);
        } else {
            throw new BceClientException("The type is wrong, maybe you should set type to vcr");
        }
    }

    private GetVcrResultResponse getVcrResult(GetMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
            VERSION,
            PATH_PROJECT, request.getProjectName(), PATH_SPACE, request.getSpaceName(), PATH_MEDIA,
                request.getPath().getMe());
        internalRequest.addParameter(TYPE_VCR, null);
        return this.invokeHttpClient(internalRequest, GetVcrResultResponse.class);
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
}
