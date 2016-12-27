/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.batch;

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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.batch.model.CancelJobRequest;
import com.baidubce.services.batch.model.CreateJobRequest;
import com.baidubce.services.batch.model.CreateJobResponse;
import com.baidubce.services.batch.model.GetJobRequest;
import com.baidubce.services.batch.model.GetJobResponse;
import com.baidubce.services.batch.model.ListJobsRequest;
import com.baidubce.services.batch.model.ListJobsResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Batch-Compute service.
 */
public class BatchClient extends AbstractBceClient {
    private static final String VERSION = "v1";
    private static final String JOB = "job";
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling HttpResponse from all Batch-Compute service calls.
     */
    private static final HttpResponseHandler[] BATCH_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on Batch-Compute.
     */
    public BatchClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client using the client configuration to access Batch-Compute.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public BatchClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, BATCH_HANDLERS);
    }

    /**
     * List Batch-Compute jobs owned by the authenticated user.
     * <p>
     * <p>
     * Users must authenticate with a valid BCE Access Key ID, and the response
     * contains all the Batch-Compute jobs owned by the user.
     *
     * @param request The request containing valid query parameters.
     * @return The response containing a list of the Batch-Compute jobs owned by the authenticated sender of the request.
     */
    public ListJobsResponse listJobs(ListJobsRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, JOB);
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() >= 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }

        return this.invokeHttpClient(internalRequest, ListJobsResponse.class);
    }

    /**
     * List Batch-Compute jobs owned by the authenticated user.
     *
     * @return The response containing a list of the Batch-Compute jobs owned by the authenticated sender of the request.
     */
    public ListJobsResponse listJobs() {
        return listJobs(new ListJobsRequest());
    }

    /**
     * List Batch-Compute jobs owned by the authenticated user.
     *
     * @param maxKeys The maximum number of jobs returned.
     * @return The response containing a list of the Batch-Compute jobs owned by the authenticated sender of the request.
     * And the size of list is limited below maxKeys.
     */
    public ListJobsResponse listJobs(int maxKeys) {
        return listJobs(new ListJobsRequest().withMaxKeys(maxKeys));
    }

    /**
     * List Batch-Compute jobs owned by the authenticated user.
     *
     * @param marker  The start record of jobs.
     * @param maxKeys The maximum number of jobs returned.
     * @return The response containing a list of the Batch-Compute jobs owned by the authenticated sender of the request.
     * The jobs' records start from the marker and the size of list is limited below maxKeys.
     */
    public ListJobsResponse listJobs(String marker, int maxKeys) {
        return listJobs(new ListJobsRequest().withMaxKeys(maxKeys).withMarker(marker));
    }

    /**
     * Describe the detail information of the target job.
     *
     * @param request The request object containing the ID of the target job.
     * @return response containing the detail information of the target job.
     */
    public GetJobResponse getJob(GetJobRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getJobId(), "The parameter jobId should not be null or empty string.");

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, JOB, request.getJobId());

        return this.invokeHttpClient(internalRequest, GetJobResponse.class);
    }

    /**
     * Describe the detail information of the target job.
     *
     * @param jobId The ID of the target job.
     * @return The response containing the detail information of the target job.
     */
    public GetJobResponse getJob(String jobId) {
        return getJob(new GetJobRequest().withJobId(jobId));
    }

    /**
     * Create a Batch-Compute job with the specified options.
     *
     * @param request The request containing all options for creating a Batch-Compute job.
     * @return The response containing the ID of the newly created job.
     */
    public CreateJobResponse createJob(CreateJobRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getName(), "The name should not be null or empty string.");
        checkStringNotEmpty(request.getVmType(), "The vmType should not be null or empty string.");
        checkStringNotEmpty(request.getJobDagJson(), "The jobDagJson should not be null or empty string.");
        checkIsTrue(request.getVmCount() > 0, "The vmCount should greater than 0");

        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", request.getName());
            jsonGenerator.writeStringField("vmType", request.getVmType());
            jsonGenerator.writeNumberField("vmCount", request.getVmCount());
            jsonGenerator.writeStringField("jobDagJson", request.getJobDagJson());
            if (request.getJobTimeoutInSeconds() != null) {
                jsonGenerator.writeNumberField("jobTimeoutInSeconds", request.getJobTimeoutInSeconds());
            }
            if (request.getMemo() != null) {
                jsonGenerator.writeStringField("memo", request.getMemo());
            }
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, JOB);
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.setContent(RestartableInputStream.wrap(json));

        internalRequest.addParameter("run", "immediate");
        if (request.getClientToken() != null) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }

        return this.invokeHttpClient(internalRequest, CreateJobResponse.class);
    }

    /**
     * Cancel a Batch-Compute job.
     *
     * @param request The request containing the ID of the job to be cancelled.
     */
    public void cancelJob(CancelJobRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getJobId(), "The parameter jobId should not be null or empty string.");

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, JOB, request.getJobId());
        internalRequest.addParameter("cancel", null);

        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Cancel a Batch-Compute job.
     *
     * @param jobId The ID of the job to be cancelled.
     */
    public void cancelJob(String jobId) {
        cancelJob(new CancelJobRequest().withJobId(jobId));
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest    The original BCE request created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific
     * parameters to send.
     */
    private InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);

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
}