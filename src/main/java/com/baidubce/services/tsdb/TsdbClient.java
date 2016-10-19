package com.baidubce.services.tsdb;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.BceV1Signer;
import com.baidubce.auth.SignOptions;
import com.baidubce.auth.Signer;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.tsdb.model.Datapoint;
import com.baidubce.services.tsdb.model.GetMetricsRequest;
import com.baidubce.services.tsdb.model.GetMetricsResponse;
import com.baidubce.services.tsdb.model.GetTagsRequest;
import com.baidubce.services.tsdb.model.GetTagsResponse;
import com.baidubce.services.tsdb.model.Query;
import com.baidubce.services.tsdb.model.QueryDatapointsRequest;
import com.baidubce.services.tsdb.model.QueryDatapointsResponse;
import com.baidubce.services.tsdb.model.WriteDatapointsRequest;
import com.baidubce.services.tsdb.model.WriteDatapointsResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Provides the client for accessing the Tsdb(Time series database).
 */
public class TsdbClient extends AbstractBceClient {

    private static final String VERSION = "v1";
    private static final String DATAPOINT = "datapoint";
    private static final String METRIC = "metric";
    private static final String TAG = "tag";
    private static final String QUERY = "query";
    private static final String[] HEADERS_TO_SIGN = { Headers.HOST, Headers.BCE_DATE };
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String GZIP = "gzip";
    private static final String UTF8 = "UTF-8";

    /**
     * Responsible for handling HttpResponse from all Tsdb service calls.
     */
    private static final HttpResponseHandler[] TSDB_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public TsdbClient(BceClientConfiguration config) {
        super(config, TSDB_HANDLERS);
    }

    public WriteDatapointsResponse writeDatapoints(List<Datapoint> datapoints) {
        return writeDatapoints(new WriteDatapointsRequest().withDatapoints(datapoints));
    }

    public WriteDatapointsResponse writeDatapoints(WriteDatapointsRequest writeDatapointsRequest) {
        checkNotNull(writeDatapointsRequest, "request should not be null.");
        return writeDatapoints(writeDatapointsRequest, true);
    }

    public WriteDatapointsResponse writeDatapoints(WriteDatapointsRequest writeDatapointsRequest, boolean isGzip) {
        checkNotNull(writeDatapointsRequest, "request should not be null.");

        InternalRequest internalRequest = createRequest(writeDatapointsRequest, HttpMethodName.POST, DATAPOINT);

        if (isGzip) {
            byte[] bytes = toGzipBytes(writeDatapointsRequest);
            fillInHeadAndBodyForGzip(internalRequest, bytes);

            WriteDatapointsResponse response = null;
            try {
                response = this.invokeHttpClient(internalRequest, WriteDatapointsResponse.class);
            } finally {
                try {
                    internalRequest.getContent().close();
                } catch (IOException e) {
                    throw new BceClientException("Close content stream failed.", e);
                }
            }

            return response;
        } else {
            fillInHeadAndBody(writeDatapointsRequest, internalRequest);
            return this.invokeHttpClient(internalRequest, WriteDatapointsResponse.class);
        }
    }

    public GetMetricsResponse getMetrics() {
        return getMetrics(new GetMetricsRequest());
    }

    public GetMetricsResponse getMetrics(GetMetricsRequest getMetricsRequest) {
        checkNotNull(getMetricsRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(getMetricsRequest, HttpMethodName.GET, METRIC);
        return this.invokeHttpClient(internalRequest, GetMetricsResponse.class);
    }

    public GetTagsResponse getTags(String metric) {
        return getTags(new GetTagsRequest().withMetric(metric));
    }

    public GetTagsResponse getTags(GetTagsRequest getTagsRequest) {
        checkNotNull(getTagsRequest, "request should not be null.");
        InternalRequest internalRequest =
                createRequest(getTagsRequest, HttpMethodName.GET, METRIC, getTagsRequest.getMetric(), TAG);
        return this.invokeHttpClient(internalRequest, GetTagsResponse.class);
    }

    public QueryDatapointsResponse queryDatapoints(List<Query> queries) {
        return queryDatapoints(new QueryDatapointsRequest().withQueries(queries));
    }

    public QueryDatapointsResponse queryDatapoints(QueryDatapointsRequest queryDatapointsRequest) {
        checkNotNull(queryDatapointsRequest, "request should not be null.");
        InternalRequest internalRequest =
                createRequest(queryDatapointsRequest, HttpMethodName.PUT, DATAPOINT);
        internalRequest.addParameter(QUERY, null);
        fillInHeadAndBody(queryDatapointsRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryDatapointsResponse.class);
    }

    public URL generatePresignedUrlForQueryDatapoints(QueryDatapointsRequest queryDatapointsRequest) {
        return generatePresignedUrlForQueryDatapoints(queryDatapointsRequest,
                SignOptions.DEFAULT_EXPIRATION_IN_SECONDS);
    }

    public URL generatePresignedUrlForQueryDatapoints(List<Query> queries) {
        return generatePresignedUrlForQueryDatapoints(queries, SignOptions.DEFAULT_EXPIRATION_IN_SECONDS);
    }

    public URL generatePresignedUrlForQueryDatapoints(List<Query> queries, int expirationInSeconds) {
        return generatePresignedUrlForQueryDatapoints(new QueryDatapointsRequest().withQueries(queries),
                expirationInSeconds);
    }

    public URL generatePresignedUrlForQueryDatapoints(QueryDatapointsRequest queryDatapointsRequest,
            int expirationInSeconds) {
        InternalRequest internalRequest = createRequest(queryDatapointsRequest, HttpMethodName.GET, DATAPOINT);
        internalRequest.addParameter(QUERY, JsonUtils.toJsonString(queryDatapointsRequest));

        BceCredentials credentials = queryDatapointsRequest.getRequestCredentials();
        if (credentials == null) {
            credentials = this.config.getCredentials();
        }

        SignOptions signOptions = new SignOptions();
        signOptions.setExpirationInSeconds(expirationInSeconds);
        Signer signer = new BceV1Signer();
        signer.sign(internalRequest, credentials, signOptions);

        return convertRequestToUrl(internalRequest);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
            String... pathVariables) {
        return createRequest(bceRequest, httpMethod, null, pathVariables);
    }

    private byte[] toGzipBytes(WriteDatapointsRequest writeDatapointsRequest) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = null;
        try {
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(JsonUtils.toJsonString(writeDatapointsRequest).getBytes(UTF8));
        } catch (IOException e) {
            throw new BceClientException("Create gzip bytes failed.", e);
        } finally {
            if (gzipOutputStream != null) {
                try {
                    gzipOutputStream.close();
                } catch (IOException e) {
                    throw new BceClientException("Close gzip stream failed.", e);
                }
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new BceClientException("Close byte stream failed.", e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param signOptions The options for signature.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific parameters to send.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
            SignOptions signOptions, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        if (signOptions == null) {
            signOptions = new SignOptions();
            signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        }
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());

        return request;
    }

    private void fillInHeadAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }

    private void fillInHeadAndBodyForGzip(InternalRequest request, byte[] bytes) {
        request.setContent(RestartableInputStream.wrap(bytes));
        request.addHeader(Headers.CONTENT_LENGTH, String.valueOf(bytes.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.addHeader(Headers.CONTENT_ENCODING, GZIP);
    }

    private byte[] toJson(AbstractBceRequest bceRequest) {
        String jsonStr = JsonUtils.toJsonString(bceRequest);
        try {
            return jsonStr.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }

    /**
     * Converts the specified request object into a URL, containing all the
     * specified parameters, the specified request endpoint, etc.
     *
     * @param request The request to convert into a URL.
     * @return A new URL representing the specified request.
     */
    private URL convertRequestToUrl(InternalRequest request) {
        String resourcePath = HttpUtils.normalizePath(request.getUri().getPath());

        // Removed the padding "/" that was already added into the request's resource path.
        if (resourcePath.startsWith("/")) {
            resourcePath = resourcePath.substring(1);
        }

        // Some http client libraries (e.g. Apache HttpClient) cannot handle
        // consecutive "/"s between URL authority and path components.
        // So we escape "////..." into "/%2F%2F%2F...", in the same way as how
        // we treat consecutive "/"s in AmazonS3Client#presignRequest(...)
        String urlPath = "/" + resourcePath;
        urlPath = urlPath.replaceAll("(?<=/)/", "%2F");
        String urlString = this.getEndpoint() + urlPath;

        boolean firstParam = true;
        for (String param : request.getParameters().keySet()) {
            if (firstParam) {
                urlString += "?";
                firstParam = false;
            } else {
                urlString += "&";
            }

            String value = request.getParameters().get(param);
            urlString += param + "=" + HttpUtils.normalize(value);
        }

        String authorization = request.getHeaders().get(Headers.AUTHORIZATION);
        if (authorization != null) {
            if (firstParam) {
                urlString += "?";
            } else {
                urlString += "&";
            }
            urlString += "authorization" + "=" + HttpUtils.normalize(authorization);
        }

        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new BceClientException("Unable to convert request to well formed URL: " + e.getMessage(), e);
        }
    }
}
