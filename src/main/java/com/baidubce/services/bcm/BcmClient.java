package com.baidubce.services.bcm;

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
import com.baidubce.services.bcm.model.ListMetricDataRequest;
import com.baidubce.services.bcm.model.ListMetricDataResponse;
import com.baidubce.services.bcm.model.MetricDataRequest;
import com.baidubce.services.bcm.model.MetricDataResponse;
import com.baidubce.services.bcm.model.Statistics;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.StringFormatUtils.stringFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Cloud Compute Service(bcm).
 */
public class BcmClient extends AbstractBceClient {

    /**
     * Parameters
     */
    private static final String PREFIX = "json-api";
    private static final String V1 = "v1";
    private static final String V3 = "v3";
    private static final String METRIC_DATA = "metricdata";
    private static final String METRIC_NAME = "metricName";

    /**
     * Exceptions
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String PERIOD_ERROR_MESSAGE = "request %s should be a multiple of 60.";
    private static final String USER_ID_MESSAGE_KEY = "userId";
    private static final String SCOPE_MESSAGE_KEY = "scope";
    private static final String STATISTICS_ARR_MESSAGE_KEY = "statistics[]";
    private static final String START_TIME_MESSAGE_KEY = "startTime";
    private static final String END_TIME_MESSAGE_KEY = "endTime";
    private static final String PERIOD_MESSAGE_KEY = "periodInSecond";

    private static final String DIMENSIONS_MESSAGE_KEY = "dimensions";
    private static final String METRIC_NAME_MESSAGE_KEY = "metricName";
    private static final String METRIC_NAMES_MESSAGE_KEY = "metricName[]";
    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all bcm service calls.
     */
    private static final HttpResponseHandler[] BCM_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on bcm.
     */
    public BcmClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new bbc client using the client configuration to access bcm.
     *
     * @param clientConfiguration The bcc client configuration options controlling how this client
     *                            connects to bbc (e.g. proxy settings, retry counts, etc).
     */
    public BcmClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, BCM_HANDLERS);
    }

    /**
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
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
        path.add(PREFIX);
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
     * check v1 request and warp
     *
     * @param request
     */
    private void checkV1Request(AbstractBceRequest request, InternalRequest internalRequest,
                                String userId, String scope,
                                Statistics[] statistics, String startTime, String endTime, Integer periodInSecond) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(userId, checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check service
        checkStringNotEmpty(scope, checkEmptyExceptionMessageFormat(SCOPE_MESSAGE_KEY));
        // check statistics
        checkNotNull(statistics, checkEmptyExceptionMessageFormat(STATISTICS_ARR_MESSAGE_KEY));
        checkArgument(statistics.length != 0, checkEmptyExceptionMessageFormat(STATISTICS_ARR_MESSAGE_KEY));
        // check startTime
        checkStringNotEmpty(startTime, checkEmptyExceptionMessageFormat(START_TIME_MESSAGE_KEY));
        // check endTime
        checkStringNotEmpty(endTime, checkEmptyExceptionMessageFormat(END_TIME_MESSAGE_KEY));
        // check periodInSecond
        checkNotNull(periodInSecond, checkEmptyExceptionMessageFormat(PERIOD_MESSAGE_KEY));
        checkArgument((periodInSecond / 60) != 0, stringFormat(PERIOD_ERROR_MESSAGE, PERIOD_MESSAGE_KEY));
        // warp parameters
        internalRequest.addParameter(STATISTICS_ARR_MESSAGE_KEY, StringUtils.join(statistics, ","));
        internalRequest.addParameter(START_TIME_MESSAGE_KEY, startTime);
        internalRequest.addParameter(END_TIME_MESSAGE_KEY, endTime);
        internalRequest.addParameter(PERIOD_MESSAGE_KEY, String.valueOf(periodInSecond));
    }

    /**
     * Get Metric Data.
     *
     * @param request metric data request.
     * @return
     */
    public MetricDataResponse getMetricData(MetricDataRequest request) {
        // check metricName
        checkStringNotEmpty(request.getMetricName(), checkEmptyExceptionMessageFormat(METRIC_NAME_MESSAGE_KEY));
        // Internal Request
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, V1, METRIC_DATA,
                request.getUserId(), request.getScope(), request.getMetricName());
        checkV1Request(request, internalRequest, request.getUserId(), request.getScope(), request.getStatistics(),
                request.getStartTime(), request.getEndTime(), request.getPeriodInSecond());
        // check dimensions
        checkStringNotEmpty(request.getDimensions(), checkEmptyExceptionMessageFormat(DIMENSIONS_MESSAGE_KEY));
        internalRequest.addParameter(DIMENSIONS_MESSAGE_KEY, request.getDimensions());
        return invokeHttpClient(internalRequest, MetricDataResponse.class);
    }

    /**
     * Get List Metric Data.
     *
     * @param request List Metric Data request.
     * @return
     */
    public ListMetricDataResponse getMetricData(ListMetricDataRequest request) {
        // Internal Request
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, V1, METRIC_DATA,
                METRIC_NAME, request.getUserId(), request.getScope());
        checkV1Request(request, internalRequest, request.getUserId(), request.getScope(), request.getStatistics(),
                request.getStartTime(), request.getEndTime(), request.getPeriodInSecond());
        // check metricName
        checkNotNull(request.getMetricNames(), checkEmptyExceptionMessageFormat(METRIC_NAMES_MESSAGE_KEY));
        checkArgument(request.getMetricNames().length != 0,
                checkEmptyExceptionMessageFormat(METRIC_NAMES_MESSAGE_KEY));
        internalRequest.addParameter(METRIC_NAMES_MESSAGE_KEY, StringUtils.join(request.getMetricNames(), ","));
        // check dimensions
        checkStringNotEmpty(request.getDimensions(), checkEmptyExceptionMessageFormat(DIMENSIONS_MESSAGE_KEY));
        internalRequest.addParameter(DIMENSIONS_MESSAGE_KEY, request.getDimensions());
        return invokeHttpClient(internalRequest, ListMetricDataResponse.class);
    }

}
