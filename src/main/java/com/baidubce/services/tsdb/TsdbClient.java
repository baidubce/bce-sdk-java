package com.baidubce.services.tsdb;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.BceV1Signer;
import com.baidubce.auth.SignOptions;
import com.baidubce.auth.Signer;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
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
import com.baidubce.util.JsonUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Tsdb(Time series database).
 */
public class TsdbClient extends AbstractTsdbBceClient {

    private static final String DATAPOINT = "datapoint";
    private static final String METRIC = "metric";
    private static final String TAG = "tag";
    private static final String QUERY = "query";

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
}
