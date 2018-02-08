/*
 * Copyright 2015 Baidu, Inc.
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

package com.baidubce.services.media;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.baidubce.services.media.model.Audio;
import com.baidubce.services.media.model.Clip;
import com.baidubce.services.media.model.CreateJobRequest;
import com.baidubce.services.media.model.CreateJobResponse;
import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.CreatePipelineResponse;
import com.baidubce.services.media.model.CreatePresetRequest;
import com.baidubce.services.media.model.CreatePresetResponse;
import com.baidubce.services.media.model.CreateThumbnailJobRequest;
import com.baidubce.services.media.model.CreateThumbnailJobResponse;
import com.baidubce.services.media.model.CreateTranscodingJobRequest;
import com.baidubce.services.media.model.CreateTranscodingJobResponse;
import com.baidubce.services.media.model.CreateWaterMarkRequest;
import com.baidubce.services.media.model.CreateWaterMarkResponse;
import com.baidubce.services.media.model.DeletePipelineRequest;
import com.baidubce.services.media.model.DeletePresetRequest;
import com.baidubce.services.media.model.DeletePresetResponse;
import com.baidubce.services.media.model.DeleteWaterMarkRequest;
import com.baidubce.services.media.model.DeleteWaterMarkResponse;
import com.baidubce.services.media.model.DelogoArea;
import com.baidubce.services.media.model.Encryption;
import com.baidubce.services.media.model.GetJobRequest;
import com.baidubce.services.media.model.GetJobResponse;
import com.baidubce.services.media.model.GetMediaInfoOfFileRequest;
import com.baidubce.services.media.model.GetMediaInfoOfFileResponse;
import com.baidubce.services.media.model.GetPipelineRequest;
import com.baidubce.services.media.model.GetPipelineResponse;
import com.baidubce.services.media.model.GetPresetRequest;
import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.GetThumbnailJobRequest;
import com.baidubce.services.media.model.GetThumbnailJobResponse;
import com.baidubce.services.media.model.GetTranscodingJobRequest;
import com.baidubce.services.media.model.GetTranscodingJobResponse;
import com.baidubce.services.media.model.GetWaterMarkRequest;
import com.baidubce.services.media.model.GetWaterMarkResponse;
import com.baidubce.services.media.model.ListJobsRequest;
import com.baidubce.services.media.model.ListJobsResponse;
import com.baidubce.services.media.model.ListPipelinesRequest;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.ListPresetsRequest;
import com.baidubce.services.media.model.ListPresetsResponse;
import com.baidubce.services.media.model.ListThumbnailJobsRequest;
import com.baidubce.services.media.model.ListThumbnailJobsResponse;
import com.baidubce.services.media.model.ListTranscodingJobsRequest;
import com.baidubce.services.media.model.ListTranscodingJobsResponse;
import com.baidubce.services.media.model.ListWaterMarkRequest;
import com.baidubce.services.media.model.ListWaterMarkResponse;
import com.baidubce.services.media.model.PipelineConfig;
import com.baidubce.services.media.model.Source;
import com.baidubce.services.media.model.SourceClip;
import com.baidubce.services.media.model.Target;
import com.baidubce.services.media.model.ThumbnailCapture;
import com.baidubce.services.media.model.ThumbnailSource;
import com.baidubce.services.media.model.ThumbnailTarget;
import com.baidubce.services.media.model.Video;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.baidubce.util.Validate.checkNotNull;


/**
 * Client for accessing Media Transcoder Services. All service calls made
 * using this client are blocking, and will not return until the service call
 * completes.
 * Created by xuchuan on 2015/4/20.
 */
public class MediaClient extends AbstractBceClient {

    /**
     * The version information for Media service APIs as URI prefix.
     */
    private static final String VERSION = "v3";

    /**
     * The common URI prefix for job services.
     */
    private static final String TRANSCODE_JOB = "job/transcoding";

    /**
     * The common URI prefix for pipeline services.
     */
    private static final String PIPELINE = "pipeline";

    /**
     * The common URI prefix for preset services.
     */
    private static final String PRESET = "preset";

    /**
     * The common URI prefix for media-info services.
     */
    private static final String MEDIAINFO = "mediainfo";

    /**
     * The common URI prefix for water mark services.
     */
    private static final String WATER_MARK = "watermark";

    /**
     * The common URI prefix for water mark services.
     */
    private static final String THUMBNAIL = "job/thumbnail";

    /**
     * The default capacity of a new pipeline.
     */
    private static final int DEFAULT_PIPELINE_CAPACITY = 20;

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static final HttpResponseHandler[] mediaHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new Media client to invoke service methods on Media Transcoder.
     */
    public MediaClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client using the client configuration to access Media Transcoder services.
     *
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to Media services (e.g. proxy settings, retry counts, etc).
     */
    public MediaClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, mediaHandlers);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param sourceKey    The key of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * 
     * @return The newly created job ID.
     * @deprecated As of release 0.8.5, replaced by {@link #createTranscodingJob(String, String, String, String)}
     */
    @Deprecated
    public CreateJobResponse createJob(String pipelineName, String sourceKey, String targetKey, String presetName) {
        CreateJobRequest request = new CreateJobRequest();
        request.setPipelineName(pipelineName);
        Source source = new Source();
        source.setSourceKey(sourceKey);
        request.setSource(source);
        Target target = new Target();
        target.setTargetKey(targetKey);
        target.setPresetName(presetName);
        request.setTarget(target);

        return createJob(request);
    }
    
    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param request The request object containing all options for creating a job.
     *     
     * @return The newly created job ID.
     * @deprecated As of release 0.8.5, replaced by {@link #createTranscodingJob(CreateTranscodingJobRequest)}}
     */
    @Deprecated
    public CreateJobResponse createJob(CreateJobRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        
        checkStringNotEmpty(request.getPipelineName(),
                "The parameter pipelineName should NOT be null or empty string.");
        checkNotNull(request.getSource(), "The parameter source should NOT be null.");
        checkStringNotEmpty(request.getSource().getSourceKey(),
                "The parameter sourceKey should NOT be null or empty string.");
        checkNotNull(request.getTarget(), "The parameter target should NOT be null.");
        checkStringNotEmpty(request.getTarget().getTargetKey(),
                "The parameter targetKey should NOT be null or empty string.");
        checkStringNotEmpty(request.getTarget().getPresetName(),
                "The parameter presetName should NOT be null or empty string.");
        
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, TRANSCODE_JOB);

        return invokeHttpClient(internalRequest, CreateJobResponse.class);
    }

    /**
     * List all transcoder jobs on specified pipeline.
     *
     * @param pipelineName   The name of a pipeline.
     *     
     * @return The list of job IDs.
     * @deprecated As of release 0.8.5, replaced by {@link #listTranscodingJobs(String)}
     */
    @Deprecated
    public ListJobsResponse listJobs(String pipelineName) {
        ListJobsRequest request = new ListJobsRequest();
        request.setPipelineName(pipelineName);
        return listJobs(request);
    }

    /**
     * List all transcoder jobs on specified pipeline.
     *
     * @param request The request object containing all options for list jobs.
     *     
     * @return The list of job IDs.
     * @deprecated As of release 0.8.5, replaced by {@link #listTranscodingJobs(ListTranscodingJobsRequest)}
     */
    @Deprecated
    public ListJobsResponse listJobs(ListJobsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getPipelineName(),
                "The parameter pipelineName should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, TRANSCODE_JOB);
        internalRequest.addParameter("pipelineName", request.getPipelineName());
        return invokeHttpClient(internalRequest, ListJobsResponse.class);
    }
    
    /**
     * Retrieve the status of a job.
     *
     * @param jobId  The ID of a job.
     *     
     * @return The status of a job.
     * @deprecated As of release 0.8.5, replaced by {@link #getTranscodingJob(String)}
     */
    @Deprecated
    public GetJobResponse getJob(String jobId) {
        GetJobRequest request = new GetJobRequest();
        request.setJobId(jobId);
        return getJob(request);
    }

    /**
     * Retrieve the status of a job.
     *
     * @param request The request object containing all options for retrieving job status.
     *     
     * @return The status of a job.
     * @deprecated As of release 0.8.5, replaced by {@link #getTranscodingJob(GetTranscodingJobRequest)}
     */
    @Deprecated
    public GetJobResponse getJob(GetJobRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getJobId(), "The parameter jobId should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, TRANSCODE_JOB, request.getJobId());

        return invokeHttpClient(internalRequest, GetJobResponse.class);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param sourceKey    The key of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * 
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, String sourceKey, String targetKey, String presetName) {
        return createTranscodingJob(pipelineName, sourceKey, targetKey, presetName,
                null, null);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset, watermarkId, and
     * delogoArea.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param sourceKey    The key of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * @param watermarkId  Single watermarkId associated with the job.
     * @param delogoArea   The delogo area (x, y, width, height).
     *
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, String sourceKey, String targetKey, String presetName,
            String watermarkId, DelogoArea delogoArea) {
        CreateTranscodingJobRequest request = new CreateTranscodingJobRequest();
        request.setPipelineName(pipelineName);
        Source source = new Source();
        source.setSourceKey(sourceKey);
        request.setSource(source);
        Target target = new Target();
        target.setTargetKey(targetKey);
        target.setPresetName(presetName);
        if (!Strings.isNullOrEmpty(watermarkId)) {
            List<String> watermarkIds = Collections.singletonList(watermarkId);
            target.setWatermarkIds(watermarkIds);
        }
        if (delogoArea != null) {
            target.setDelogoArea(delogoArea);
        }
        request.setTarget(target);

        return createTranscodingJob(request);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param clips    The keys of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     *
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, List<SourceClip> clips, String targetKey, String presetName) {
        return createTranscodingJob(pipelineName, clips, targetKey, presetName,
                null, null);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset and watermarkId
     * associated with the job.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param clips    The keys of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * @param watermarkId  Single watermarkId associated with the job.
     *
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, List<SourceClip> clips, String targetKey, String presetName,
            String watermarkId) {
        return createTranscodingJob(pipelineName, clips, targetKey, presetName,
                watermarkId, null);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset, watermarkId, and
     * delogoArea.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param clips    The keys of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * @param watermarkId  Single watermarkId associated with the job.
     * @param delogoArea   The delogo area (x, y, width, height).
     *
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, List<SourceClip> clips, String targetKey, String presetName,
            String watermarkId, DelogoArea delogoArea) {
        CreateTranscodingJobRequest request = new CreateTranscodingJobRequest();
        request.setPipelineName(pipelineName);
        Source source = new Source();
        for (SourceClip clip : clips) {
            source.addClip(clip);
        }
        request.setSource(source);
        Target target = new Target();
        target.setTargetKey(targetKey);
        target.setPresetName(presetName);
        if (!Strings.isNullOrEmpty(watermarkId)) {
            List<String> watermarkIds = Collections.singletonList(watermarkId);
            target.setWatermarkIds(watermarkIds);
        }
        if (delogoArea != null) {
            target.setDelogoArea(delogoArea);
        }
        request.setTarget(target);

        return createTranscodingJob(request);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param request The request object containing all options for creating a job.
     *     
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(CreateTranscodingJobRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        
        checkStringNotEmpty(request.getPipelineName(),
                "The parameter pipelineName should NOT be null or empty string.");
        checkNotNull(request.getSource(), "The parameter source should NOT be null.");
        checkNotNull(request.getTarget(), "The parameter target should NOT be null.");
        checkStringNotEmpty(request.getTarget().getTargetKey(),
                "The parameter targetKey should NOT be null or empty string.");
        checkStringNotEmpty(request.getTarget().getPresetName(),
                "The parameter presetName should NOT be null or empty string.");
        
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, TRANSCODE_JOB);

        return invokeHttpClient(internalRequest, CreateTranscodingJobResponse.class);
    }

    /**
     * List all transcoder jobs on specified pipeline.
     *
     * @param pipelineName   The name of a pipeline.
     *     
     * @return The list of job IDs.
     */
    public ListTranscodingJobsResponse listTranscodingJobs(String pipelineName) {
        ListTranscodingJobsRequest request = new ListTranscodingJobsRequest();
        request.setPipelineName(pipelineName);
        return listTranscodingJobs(request);
    }

    /**
     * List all transcoder jobs on specified pipeline.
     *
     * @param request The request object containing all options for list jobs.
     *     
     * @return The list of job IDs.
     */
    public ListTranscodingJobsResponse listTranscodingJobs(ListTranscodingJobsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getPipelineName(),
                "The parameter pipelineName should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, TRANSCODE_JOB);
        internalRequest.addParameter("pipelineName", request.getPipelineName());
        return invokeHttpClient(internalRequest, ListTranscodingJobsResponse.class);
    }
    
    /**
     * Retrieve the status of a job.
     *
     * @param jobId  The ID of a job.
     *     
     * @return The status of a job.
     */
    public GetTranscodingJobResponse getTranscodingJob(String jobId) {
        GetTranscodingJobRequest request = new GetTranscodingJobRequest();
        request.setJobId(jobId);
        return getTranscodingJob(request);
    }

    /**
     * Retrieve the status of a job.
     *
     * @param request The request object containing all options for retrieving job status.
     *     
     * @return The status of a job.
     */
    public GetTranscodingJobResponse getTranscodingJob(GetTranscodingJobRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getJobId(), "The parameter jobId should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, TRANSCODE_JOB, request.getJobId());

        return invokeHttpClient(internalRequest, GetTranscodingJobResponse.class);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param pipelineName The name of the new pipeline.
     * @param sourceBucket The name of source bucket in Bos.
     * @param targetBucket The name of target bucket in Bos.
     * @param capacity     The concurrent capability of the new pipeline.
     * 
     */
    public CreatePipelineResponse createPipeline(
            String pipelineName, String sourceBucket, String targetBucket, int capacity) {
        return createPipeline(pipelineName, null, sourceBucket, targetBucket, capacity);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param pipelineName The name of the new pipeline.
     * @param sourceBucket The name of source bucket in Bos.
     * @param targetBucket The name of target bucket in Bos.
     * 
     */
    public CreatePipelineResponse createPipeline(
            String pipelineName, String sourceBucket, String targetBucket) {
        return createPipeline(pipelineName, null, sourceBucket, targetBucket, DEFAULT_PIPELINE_CAPACITY);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param pipelineName The name of new pipeline.
     * @param description  The optional description of the new pipeline.
     * @param sourceBucket The name of source bucket in Bos.
     * @param targetBucket The name of target bucket in Bos.
     * @param capacity     The concurrent capability of the new pipeline.
     *
     */
    public CreatePipelineResponse createPipeline(
            String pipelineName, String description,  String sourceBucket, String targetBucket, int capacity) {
        return createPipeline(pipelineName, description, sourceBucket, targetBucket, capacity, null);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param pipelineName The name of new pipeline.
     * @param description  The optional description of the new pipeline.
     * @param sourceBucket The name of source bucket in Bos.
     * @param targetBucket The name of target bucket in Bos.
     * @param capacity     The concurrent capability of the new pipeline.
     * @param notification The name of notification
     * 
     */
    public CreatePipelineResponse createPipeline(
            String pipelineName, String description, String sourceBucket, String targetBucket, int capacity,
            String notification) {
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setDescription(description);
        request.setSourceBucket(sourceBucket);
        request.setTargetBucket(targetBucket);
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(capacity);
        config.setNotification(notification);
        request.setConfig(config);
        
        return createPipeline(request);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param request The request object containing all options for creating new pipeline.
     * 
     */
    public CreatePipelineResponse createPipeline(CreatePipelineRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getPipelineName(), 
                "The parameter pipelineName should NOT be null or empty string.");
        checkStringNotEmpty(request.getSourceBucket(), 
                "The parameter sourceBucket should NOT be null or empty string.");
        checkStringNotEmpty(request.getTargetBucket(), 
                "The parameter targetBucket should NOT be null or empty string.");
        if (request.getConfig() == null || request.getConfig().getCapacity() == null) {
            PipelineConfig config = new PipelineConfig();
            config.setCapacity(DEFAULT_PIPELINE_CAPACITY);
            request.setConfig(config);
        }
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, PIPELINE);
        
        return invokeHttpClient(internalRequest, CreatePipelineResponse.class);
    }

    /**
     * List all your pipelines.
     *
     * @return The list of all your pipelines 
     */
    public ListPipelinesResponse listPipelines() {
        ListPipelinesRequest request = new ListPipelinesRequest();
        return listPipelines(request);
    }

    /**
     * List all your pipelines.
     *
     * @param request The request object containing all options for listing all pipelines.
     * 
     * @return The list of all your pipelines 
     */
    public ListPipelinesResponse listPipelines(ListPipelinesRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PIPELINE);
        return invokeHttpClient(internalRequest, ListPipelinesResponse.class);
    }

    /**
     * Gets a pipeline with the specified pipeline name.
     *
     * @param pipelineName The name of your pipeline.
     * 
     * @return The information of your pipeline. 
     */
    public GetPipelineResponse getPipeline(String pipelineName) {
        GetPipelineRequest request = new GetPipelineRequest();
        request.setPipelineName(pipelineName);
        return getPipeline(request);
    }

    /**
     * Gets a pipeline with the specified pipeline name.
     *
     * @param request The request object containing all options for getting a pipelines.
     * 
     * @return The information of your pipeline. 
     */
    public GetPipelineResponse getPipeline(GetPipelineRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getPipelineName(), 
                "The parameter pipelineName should NOT be null or empty string.");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, PIPELINE, request.getPipelineName());

        return invokeHttpClient(internalRequest, GetPipelineResponse.class);
    }
    
    /**
     * Gets a pipeline with the specified pipeline name.
     *
     * @param pipelineName The name of your pipeline.
     *  
     */
    public void deletePipeline(String pipelineName) {
        DeletePipelineRequest request = new DeletePipelineRequest();
        request.setPipelineName(pipelineName);
        deletePipeline(request);
    }

    /**
     * Deletes a pipeline with the specified pipeline name.
     *
     * @param request The request object containing all options for deleting a pipelines.
     * 
     */
    public void deletePipeline(DeletePipelineRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getPipelineName(), 
                "The parameter pipelineName should NOT be null or empty string.");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.DELETE, request, PIPELINE, request.getPipelineName());

        invokeHttpClient(internalRequest, CreatePipelineResponse.class);
    }

    /**
     * Create a preset which help to convert audio files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param audio       Specify the audio format of target file.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String container, Audio audio) {
        return createPreset(presetName, null, container, false, null, audio, null, null, null);
    }
        
    /**
     * Create a preset which help to convert audio files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param audio       Specify the audio format of target file.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String description, String container, Audio audio) {
        return createPreset(presetName, description, container, false, null, audio, null, null, null);
    }
        
    /**
     * Create a preset which help to convert audio files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param encryption  Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String container, Clip clip, Audio audio, Encryption encryption) {
        return createPreset(presetName, null, container, false, clip, audio, null, encryption, null);
    }
        
    /**
     * Create a preset which help to convert audio files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param encryption  Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, Clip clip, Audio audio, Encryption encryption) {
        return createPreset(presetName, description, container, false, clip, audio, null, encryption, null);
    }
        
    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String container, Audio audio, Video video) {
        return createPreset(presetName, null, container, false, null, audio, video, null, null);
    }

    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, Audio audio, Video video) {
        return createPreset(presetName, description, container, false, null, audio, video, null, null);
    }

    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName The name of the new preset.
     * @param container The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip The clip property of the preset.
     * @param audio Specify the audio format of target file.
     * @param video Specify the video format of target file.
     * @param encryption Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption) {
        return createPreset(presetName, null, container, false, clip, audio, video, encryption, null);
    }

    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName The name of the new preset.
     * @param description The description of the new preset
     * @param container The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip The clip property of the preset.
     * @param audio Specify the audio format of target file.
     * @param video Specify the video format of target file.
     * @param encryption Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, Clip clip, Audio audio, Video video,
            Encryption encryption) {
        return createPreset(presetName, description, container, false, clip, audio, video, encryption, null);
    }

    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * @param encryption  Specify the encryption property of target file.
     * @param watermarkId Specify the watermarkId.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption,
            String watermarkId) {
        return createPreset(presetName, null, container, false, clip, audio, video, encryption, watermarkId);
    }
        
    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * @param encryption  Specify the encryption property of target file.
     * @param watermarkId Specify the watermarkId.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, Clip clip, Audio audio, Video video,
            Encryption encryption, String watermarkId) {
        return createPreset(presetName, description, container, false, clip, audio, video, encryption, watermarkId);
    }
        
    /**
     * Create a preset which only convert source media file to a different container format without changing the file
     * contents.
     * 
     * @param presetName The name of the new preset.
     * @param container The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String container) {
        return createPreset(presetName, null, container, true, null, null, null, null, null);
    }
        
    /**
     * Create a preset which only convert source media file to a different container format without changing the file
     * contents.
     * 
     * @param presetName The name of the new preset.
     * @param description The description of the new preset
     * @param container The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String description, String container) {
        return createPreset(presetName, description, container, true, null, null, null, null, null);
    }
        
    /**
     * Create a preset which help to convert media files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param transmux    If true, means only convert source media file to a different container format without changing
     *            the file contents. 
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * @param encryption  Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, boolean transmux, Clip clip, Audio audio,
            Video video, Encryption encryption) {
        return createPreset(presetName, description, container, false, clip, audio, video, encryption, null);
    }
    
    /**
     * Create a preset which help to convert media files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param transmux    If true, means only convert source media file to a different container format without changing
     *            the file contents. 
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * @param encryption  Specify the encryption property of target file.
     * @param watermarkId Specify the watermarkId.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, boolean transmux, Clip clip, Audio audio,
            Video video, Encryption encryption, String watermarkId) {
        CreatePresetRequest request = new CreatePresetRequest();
        request.setPresetName(presetName);
        request.setDescription(description);
        request.setContainer(container);
        request.setTransmux(transmux);
        request.setClip(clip);
        request.setAudio(audio);
        request.setVideo(video);
        request.setEncryption(encryption);
        request.setWatermarkId(watermarkId);
        
        return createPreset(request);
    }

    /**
     * Create a preset which help to convert media files on be played in a wide range of devices.
     * 
     * @param request The request object containing all options for deleting presets.
     */
    public CreatePresetResponse createPreset(CreatePresetRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, PRESET);

        return invokeHttpClient(internalRequest, CreatePresetResponse.class);
    }

    /**
     * List all system and user's preset.
     *
     * @return The list of all available preset. 
     */
    public ListPresetsResponse listPresets() {
        ListPresetsRequest request = new ListPresetsRequest();
        return listPresets(request);
    }

    /**
     * List all system and user's preset.
     * 
     * @param request The request object containing all options for listing presets.
     *
     * @return The list of all available preset. 
     */
    public ListPresetsResponse listPresets(ListPresetsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PRESET);
        return invokeHttpClient(internalRequest, ListPresetsResponse.class);
    }

    /**
     * Gets a preset with specified name.
     *
     * @param presetName The name of a preset.
     * 
     * @return The information of the preset. 
     */
    public GetPresetResponse getPreset(String presetName) {
        GetPresetRequest request = new GetPresetRequest();
        request.setPresetName(presetName);
        return getPreset(request);
    }

    /**
     * Gets a preset with specified name.
     *
     * @param request The request object containing all options for getting a preset.
     * 
     * @return The information of the preset. 
     */
    public GetPresetResponse getPreset(GetPresetRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getPresetName(), "The parameter presetName should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PRESET, request.getPresetName());

        return invokeHttpClient(internalRequest, GetPresetResponse.class);
    }

    /**
     * Deletes a preset with specified name.
     *
     * @param presetName The name of a preset.
     * 
     */
    public void deletePreset(String presetName) {
        DeletePresetRequest request = new DeletePresetRequest();
        request.setPresetName(presetName);
        deletePreset(request);
    }

    /**
     * Deletes a preset with specified name.
     *
     * @param request The request object containing all options for deleting a preset.
     * 
     */
    public void deletePreset(DeletePresetRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getPresetName(), "The parameter presetName should NOT be null or empty string.");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.DELETE, request, PRESET, request.getPresetName());

        invokeHttpClient(internalRequest, DeletePresetResponse.class);
    }

    /**
     * Retrieve the media information of an object in Bos bucket.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     *     
     * @return The media information of an object in Bos bucket.
     */
    public GetMediaInfoOfFileResponse getMediaInfoOfFile(String bucket, String key) {
        GetMediaInfoOfFileRequest request = new GetMediaInfoOfFileRequest();
        request.setBucket(bucket);
        request.setKey(key);
        return getMediaInfoOfFile(request);
    }

    /**
     * Retrieve the media information of an object in Bos bucket.
     *
     * @param request The request object containing all options for retrieving media information.
     *     
     * @return The media information of an object in Bos bucket.
     */
    public GetMediaInfoOfFileResponse getMediaInfoOfFile(GetMediaInfoOfFileRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getBucket(), "The parameter bucket should NOT be null or empty string.");
        checkStringNotEmpty(request.getKey(), "The parameter key should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, MEDIAINFO);
        internalRequest.addParameter("bucket", request.getBucket());
        internalRequest.addParameter("key", request.getKey());
        return invokeHttpClient(internalRequest, GetMediaInfoOfFileResponse.class);
    }
    
    /**
     * Creates a water mark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalOffsetInPixel  The horizontal offset in pixels.
     * @param verticalOffsetInPixel    The vertical offset in pixels.
     * 
     * @return watermarkId the unique ID of the new water mark.
     */
    @Deprecated
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, int horizontalOffsetInPixel, int verticalOffsetInPixel) {
        
        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalOffsetInPixel(horizontalOffsetInPixel)
                        .withVerticalOffsetInPixel(verticalOffsetInPixel);
        return createWaterMark(request);
    }
    
    /**
     * Creates a water mark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalAlignment  The horizontal alignment, includes left, center, right.
     * @param verticalAlignment    The vertical alignment, includes top, center, bottom.
     * 
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, String horizontalAlignment, String verticalAlignment) {

        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalAlignment(horizontalAlignment)
                        .withVerticalAlignment(verticalAlignment);

        return createWaterMark(request);
    }
    
    /**
     * Creates a water mark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalAlignment  The horizontal alignment, includes left, center, right.
     * @param verticalAlignment    The vertical alignment, includes top, center, bottom.
     * @param horizontalOffsetInPixel  The horizontal offset in pixels.
     * @param verticalOffsetInPixel    The vertical offset in pixels.
     * 
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, String horizontalAlignment, String verticalAlignment,
            int horizontalOffsetInPixel, int verticalOffsetInPixel) {

        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalAlignment(horizontalAlignment)
                        .withVerticalAlignment(verticalAlignment)
                        .withHorizontalOffsetInPixel(horizontalOffsetInPixel)
                        .withVerticalOffsetInPixel(verticalOffsetInPixel);

        return createWaterMark(request);
    }
    
    /**
     * Creates a water mark and return water mark ID
     *
     * @param request The request object containing all options for creating new water mark.
     *
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(CreateWaterMarkRequest request) {
        checkStringNotEmpty(request.getBucket(), "The parameter bucket should NOT be null or empty string.");
        checkStringNotEmpty(request.getKey(), "The parameter key should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, WATER_MARK);
        
        return invokeHttpClient(internalRequest, CreateWaterMarkResponse.class);
    }

    /**
     * Get a water mark for a given water mark ID. 
     *
     * @param watermarkId The ID of water mark.
     * @return The information of the water mark.
     * 
     */
    public GetWaterMarkResponse getWaterMark(String watermarkId) {
        GetWaterMarkRequest request = new GetWaterMarkRequest().withWatermarkId(watermarkId);
        return getWaterMark(request);
    }

    /**
     * Get a water mark for a given water mark ID. 
     *
     * @param request The request object containing all options for getting water mark.
     * @return The information of the water mark.
     */
    public GetWaterMarkResponse getWaterMark(GetWaterMarkRequest request) {
        checkStringNotEmpty(request.getWatermarkId(), "The parameter watermarkId should NOT be null or empty string.");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, WATER_MARK, request.getWatermarkId());
        
        return invokeHttpClient(internalRequest, GetWaterMarkResponse.class);
    }
    
    /**
     * List all water mark.
     *
     * @return The list of all user's water mark.
     * 
     */
    public ListWaterMarkResponse listWaterMark() {
        ListWaterMarkRequest request = new ListWaterMarkRequest();
        return listWaterMark(request);
    }
    
    /**
     * List all water mark.
     *
     * @return The list of all user's water mark.
     * 
     */
    public ListWaterMarkResponse listWaterMark(ListWaterMarkRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, WATER_MARK);
        
        return invokeHttpClient(internalRequest, ListWaterMarkResponse.class);
    }

    /**
     * Delete a water mark. 
     *
     * @param watermarkId The ID of water mark.
     * 
     */
    public void deleteWaterMark(String watermarkId) {
        DeleteWaterMarkRequest request = new DeleteWaterMarkRequest().withWatermarkId(watermarkId);
        deleteWaterMark(request);
    }

    /**
     * Delete a water mark. 
     *
     * @param request The request object containing all options for deleting water mark.
     * 
     */
    public void deleteWaterMark(DeleteWaterMarkRequest request) {
        checkStringNotEmpty(request.getWatermarkId(), "The parameter watermarkId should NOT be null or empty string.");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.DELETE, request, WATER_MARK, request.getWatermarkId());
        
        invokeHttpClient(internalRequest, DeleteWaterMarkResponse.class);
    }
    
    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     * @param target The property container of target object.
     * @param capture The property container of thumbnail generating policies.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(
            String pipelineName, String sourceKey, ThumbnailTarget target, ThumbnailCapture capture) {
        
        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);
        
        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withSource(source).withTarget(target)
                        .withCapture(capture);
        
        return createThumbnailJob(request);
    }

    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     * @param target The property container of target object.
     * @param capture The property container of thumbnail generating policies.
     * @param delogoArea The property container of delogo Area.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(
            String pipelineName, String sourceKey, ThumbnailTarget target,
            ThumbnailCapture capture, DelogoArea delogoArea) {

        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);

        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withSource(source).withTarget(target)
                        .withCapture(capture).withDelogoArea(delogoArea);

        return createThumbnailJob(request);
    }
    
    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(String pipelineName, String sourceKey) {
        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);
        
        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withSource(source);
        
        return createThumbnailJob(request);
    }
    
    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param request The request object containing all options for creating new water mark.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(CreateThumbnailJobRequest request) {
        checkStringNotEmpty(request.getPipelineName(), 
                "The parameter pipelineName should NOT be null or empty string.");
        checkNotNull(request.getSource(), "The parameter source should NOT be null.");
        checkStringNotEmpty(request.getSource().getKey(),
                "The parameter source key should NOT be null or empty string.");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, THUMBNAIL);
        
        return invokeHttpClient(internalRequest, CreateThumbnailJobResponse.class);
    }
    
    /**
     * Get information of thumbnail job.
     *
     * @param jobId The unique ID of thumbnail job.
     *
     * @return The information of the thumbnail job.
     */
    public GetThumbnailJobResponse getThumbnailJob(String jobId) {
        GetThumbnailJobRequest request = new GetThumbnailJobRequest().withJobId(jobId);
        
        return getThumbnailJob(request);
    }
    
    /**
     * Get information of thumbnail job.
     *
     * @param request The request object containing all options for creating new water mark.
     *
     * @return The information of the thumbnail job.
     */
    public GetThumbnailJobResponse getThumbnailJob(GetThumbnailJobRequest request) {
        checkStringNotEmpty(request.getJobId(), "The parameter jobId should NOT be null or empty string.");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, THUMBNAIL, request.getJobId());
        
        return invokeHttpClient(internalRequest, GetThumbnailJobResponse.class);
    }
    
    /**
     * List thumbnail jobs for a given pipeline.
     *
     * @param pipelineName The name of a pipeline.
     *
     * @return List of thumbnail jobs.
     */
    public ListThumbnailJobsResponse listThumbnailJobs(String pipelineName) {
        ListThumbnailJobsRequest request = new ListThumbnailJobsRequest().withPipeline(pipelineName);
        
        return listThumbnailJobs(request);
    }
    
    /**
     * List thumbnail jobs for a given pipeline.
     *
     * @param request The request object containing all options for creating new water mark.
     *
     * @return List of thumbnail jobs.
     */
    public ListThumbnailJobsResponse listThumbnailJobs(ListThumbnailJobsRequest request) {
        checkStringNotEmpty(request.getPipeline(), "The parameter pipelineName should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, THUMBNAIL);
        internalRequest.addParameter("pipelineName", request.getPipeline()); 
        return invokeHttpClient(internalRequest, ListThumbnailJobsResponse.class);
    }
    
    /**
     * Creates and initializes a new request object for the specified resource.
     * This method is responsible for determining HTTP method, URI path, 
     * credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.  
     * </p>
     * @param httpMethod
     *            The HTTP method to use when sending the request.
     * @param request
     *            The original request, as created by the user.
     * @param pathVariables
     *            The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path,
     *         ready for callers to populate any additional headers or
     *         parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, AbstractBceRequest request, String...pathVariables) {

        // build URL paths
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(VERSION);

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(getEndpoint(), pathComponents.toArray(new String[pathComponents.size()]));

        // get a InternalRequest instance and set headers
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        internalRequest.setCredentials(request.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST) {
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