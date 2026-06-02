package com.baidubce.services.bls;

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
import com.baidubce.services.bls.model.logrecord.PullLogRecordRequest;
import com.baidubce.services.bls.model.logrecord.PullLogRecordResponse;
import com.baidubce.services.bls.model.logrecord.PushLogRecordRequest;
import com.baidubce.services.bls.model.logrecord.PushLogRecordResponse;
import com.baidubce.services.bls.model.logrecord.QueryLogHistogramRequest;
import com.baidubce.services.bls.model.logrecord.QueryLogHistogramResponse;
import com.baidubce.services.bls.model.logrecord.QueryLogRecordRequest;
import com.baidubce.services.bls.model.logrecord.QueryLogRecordResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.baidubce.util.Validate.checkListNotEmpty;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;


public class BlsClient extends AbstractBceClient {

    private static final String VERSION_V1 = "v1";
    private static final String VERSION_V2 = "v2";

    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    public static final String PROJECT = "project";
    public static final String LOG_STORE_NAME = "logStoreName";
    public static final String LOG_STREAM_NAME = "logStreamName";
    public static final String START_DATE_TIME = "startDateTime";
    public static final String END_DATE_TIME = "endDateTime";
    public static final String LIMIT = "limit";
    public static final String MARKER = "marker";
    public static final String QUERY = "query";
    public static final String SORT = "sort";

    public static final String LOG_STORE = "logstore";
    public static final String LOG_RECORD = "logrecord";
    public static final String LOG_HISTOGRAM = "loghistogram";

    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";

    private static final String LOG_STORE_NAME_NULL_ERROR_MESSAGE = "logStoreName should not be null.";

    public static final String START_TIME_NULL_ERROR_MESSAGE = "startDateTime should not be null.";

    public static final String END_TIME_NULL_ERROR_MESSAGE = "endDateTime should not be null.";

    public static final String LIMIT_EXCEED_ERROR_MESSAGE = "limit should not be greater than 1000.";

    public static final String QUERY_NULL_ERROR_MESSAGE = "query should not be null.";

    public static final String LOG_RECORD_NULL_ERROR_MESSAGE = "log records should not be null.";

    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };


    /**
     * Constructs a new client to invoke service methods on bls
     */
    public BlsClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new bcc client using the client configuration to access bcc.
     *
     * @param config The bls client configuration options controlling how this client
     *                            connects to bls (e.g. proxy settings, retry counts, etc).
     */
    public BlsClient(BceClientConfiguration config) {
        super(config, HANDLERS);
    }

    public void pushLogRecord(PushLogRecordRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getLogStoreName(), LOG_STORE_NAME_NULL_ERROR_MESSAGE);
        checkListNotEmpty(request.getLogRecords(), LOG_RECORD_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, LOG_STORE,
                request.getLogStoreName(), LOG_RECORD);
        if (request.getProject() != null && !request.getProject().isEmpty()) {
            internalRequest.addParameter(PROJECT, request.getProject());
        }
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, PushLogRecordResponse.class);
    }

    public PullLogRecordResponse pullLogRecord(PullLogRecordRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getLogStoreName(), LOG_STORE_NAME_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getStartDateTime(), START_TIME_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEndDateTime(), END_TIME_NULL_ERROR_MESSAGE);
        if (request.getLimit() > 1000) {
            throw new IllegalArgumentException(LIMIT_EXCEED_ERROR_MESSAGE);
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, LOG_STORE,
                request.getLogStoreName(), LOG_RECORD);

        if (request.getProject() != null && !request.getProject().isEmpty()) {
            internalRequest.addParameter(PROJECT, request.getProject());
        }
        if (request.getLogStreamName() != null && !request.getLogStreamName().isEmpty()) {
            internalRequest.addParameter(LOG_STREAM_NAME, request.getLogStreamName());
        }
        if (request.getMarker() != null && !request.getMarker().isEmpty()) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getLimit() > 0 ) {
            internalRequest.addParameter(LIMIT, String.valueOf(request.getLimit()));
        }
        internalRequest.addParameter(START_DATE_TIME, request.getStartDateTime());
        internalRequest.addParameter(END_DATE_TIME, request.getEndDateTime());
        return invokeHttpClient(internalRequest, PullLogRecordResponse.class);
    }

    public QueryLogRecordResponse queryLogRecord(QueryLogRecordRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getLogStoreName(), LOG_STORE_NAME_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getStartDateTime(), START_TIME_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEndDateTime(), END_TIME_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getQuery(), QUERY_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, LOG_STORE,
                request.getLogStoreName(), LOG_RECORD);
        if (request.getProject() != null && !request.getProject().isEmpty()) {
            internalRequest.addParameter(PROJECT, request.getProject());
        }
        if (request.getLogStreamName() != null && !request.getLogStreamName().isEmpty()) {
            internalRequest.addParameter(LOG_STREAM_NAME, request.getLogStreamName());
        }
        internalRequest.addParameter(QUERY, request.getQuery());
        internalRequest.addParameter(START_DATE_TIME, request.getStartDateTime());
        internalRequest.addParameter(END_DATE_TIME, request.getEndDateTime());
        if (request.getMarker() != null && !request.getMarker().isEmpty()) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getLimit() > 0 ) {
            internalRequest.addParameter(LIMIT, String.valueOf(request.getLimit()));
        }
        if (request.getSort() != null && !request.getSort().isEmpty()) {
            internalRequest.addParameter(SORT, request.getSort());
        }
        return invokeHttpClient(internalRequest, QueryLogRecordResponse.class);
    }

    public QueryLogHistogramResponse queryLogHistogram(QueryLogHistogramRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getLogStoreName(), LOG_STORE_NAME_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getQuery(), QUERY_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getStartDateTime(), START_TIME_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEndDateTime(), END_TIME_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequestV2(request, HttpMethodName.GET, LOG_STORE,
                request.getLogStoreName(), LOG_HISTOGRAM);
        if (request.getProject() != null && !request.getProject().isEmpty()) {
            internalRequest.addParameter(PROJECT, request.getProject());
        }

        if (request.getLogStreamName() != null && !request.getLogStreamName().isEmpty()) {
            internalRequest.addParameter(LOG_STREAM_NAME, request.getLogStreamName());
        }
        internalRequest.addParameter(QUERY, request.getQuery());
        internalRequest.addParameter(START_DATE_TIME, request.getStartDateTime());
        internalRequest.addParameter(END_DATE_TIME, request.getEndDateTime());
        return invokeHttpClient(internalRequest, QueryLogHistogramResponse.class);
    }


    /**
     * Creates and initializes a new request object for the specified bls resource. This method is responsible
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

        path.add(VERSION_V1);

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

    private InternalRequest createRequestV2(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION_V2);

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
}
