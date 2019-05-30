/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bsc;

import com.baidu.bce.internalsdk.core.BceClient;
import com.baidu.bce.internalsdk.core.BceClientConfig;
import com.baidu.bce.internalsdk.core.Entity;
import com.baidubce.services.bsc.model.cert.DeleteCertResponse;
import com.baidubce.services.bsc.model.cert.UploadCertResponse;
import com.baidubce.services.bsc.model.code.DeleteCommitCodeResponse;
import com.baidubce.services.bsc.model.code.GetCommitCodeResponse;
import com.baidubce.services.bsc.model.core.BscCerts;
import com.baidubce.services.bsc.model.core.BscJob;
import com.baidubce.services.bsc.model.core.BscTemplate;
import com.baidubce.services.bsc.model.core.BscCommitCode;
import com.baidubce.services.bsc.model.core.BscJobExecution;
import com.baidubce.services.bsc.model.operation.GetResponse;
import com.baidubce.services.bsc.model.operation.ListOpRequest;
import com.baidubce.services.bsc.model.operation.CanRestartResponse;
import com.baidubce.services.bsc.model.operation.StartResponse;
import com.baidubce.services.bsc.model.operation.StopResponse;
import com.baidubce.services.bsc.model.operation.StartRequest;
import com.baidubce.services.bsc.model.template.AddTemplateJobRequest;
import com.baidubce.services.bsc.model.template.CreateTemplateResponse;
import com.baidubce.services.bsc.model.template.CreateTemplateRequest;
import com.baidubce.services.bsc.model.template.UpdateTemplateRequest;
import com.baidubce.services.bsc.model.template.UpdateTemplateResponse;
import com.baidubce.services.bsc.model.template.GetTemplateDetailResponse;
import com.baidubce.services.bsc.model.template.ListTemplateRequest;
import com.baidubce.services.bsc.model.template.DeleteTemplateResponse;
import com.baidubce.services.bsc.model.template.AddTemplateJobResponse;
import com.baidubce.services.bsc.model.executorlog.ExecutorListResponse;
import com.baidubce.services.bsc.model.executorlog.ExecutorLogRequest;
import com.baidubce.services.bsc.model.job.CommitJobRequest;
import com.baidubce.services.bsc.model.job.CreateJobRequest;
import com.baidubce.services.bsc.model.job.UpdateJobRequest;
import com.baidubce.services.bsc.model.job.CreateSparkJobRequest;
import com.baidubce.services.bsc.model.job.CreateJobResponse;
import com.baidubce.services.bsc.model.job.CommitJobResponse;
import com.baidubce.services.bsc.model.job.UpdateJobResponse;
import com.baidubce.services.bsc.model.job.UpdateSparkJobRequest;
import com.baidubce.services.bsc.model.job.GetJobDetailResponse;
import com.baidubce.services.bsc.model.job.ListJobRequest;
import com.baidubce.services.bsc.model.job.DebugJobResponse;
import com.baidubce.services.bsc.model.job.DebugJobRequest;
import com.baidubce.services.bsc.model.job.GetDebugStatusResponse;
import com.baidubce.services.bsc.model.job.GetDebugStatusRequest;
import com.baidubce.services.bsc.model.job.DeleteJobResponse;
import com.baidubce.services.bsc.model.job.CheckCodeResponse;
import com.baidubce.services.bsc.model.job.CheckCodeRequest;
import com.baidubce.services.bsc.model.util.PageResp;
import com.baidubce.services.bsc.model.util.Resp;
import com.baidubce.services.bsc.util.Helper;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.media.multipart.internal.MultiPartWriter;
import org.springframework.util.StringUtils;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Provides the client for accessing the Baidu Streaming Compute.
 */
public class BscClient extends BceClient {
    private static final String prefix = "/api/v2/seniorbsc";
    private static final String certURL = prefix + "/cert";
    private static final String codeURL = prefix + "/code";
    private static final String jobURL = prefix + "/job";
    private static final String logURL = prefix + "/log";
    private static final String operationURL = prefix;
    private static final String stsURL = prefix + "/service";
    private static final String templateURL = prefix + "/template";
    private static final String debugValue = "debugValue";

    /**
     * Constructs a new Bsc client using the endpoint, ak, sk to access Bsc
     * @param endpoint
     * @param ak
     * @param sk
     */
    public BscClient(String endpoint, String ak, String sk) {
        super(endpoint, ak, sk);
    }

    /**
     * load ${cert} for ${jobId}job
     * @param jobId
     * @param cert
     * @return
     */
    public Resp<UploadCertResponse> uploadCert(Long jobId, File cert) {
        MultiPart multiPart = new FormDataMultiPart().bodyPart(
            new FileDataBodyPart("file", cert, MediaType.MULTIPART_FORM_DATA_TYPE)
        );

        BceClientConfig config = BceClientConfig.newDefaultBceClientConfig()
                                                .withMessageBodyWriterClass(MultiPartWriter.class);

        return super.createAuthorizedRequest(config)
                    .path(certURL + "/upload/" + jobId)
                    .postWithResponse(new Entity(multiPart, MediaType.MULTIPART_FORM_DATA))
                    .getEntity(new GenericType<Resp<UploadCertResponse>>() { });
    }

    /**
     * list cert for ${jobId}
     * @param jobId
     * @return
     */
    public PageResp<BscCerts> listCert(Long jobId) {
        return super.createAuthorizedRequest()
                    .path(certURL + "/list/" + jobId)
                    .getWitResponse()
                    .getEntity(new GenericType<PageResp<BscCerts>>() { });
    }

    /**
     * delete ${certId} in ${jobId}
     * @param jobId
     * @param certId
     * @return
     */
    public Resp<DeleteCertResponse> deleteCert(Long jobId, Long certId) {
        return super.createAuthorizedRequest()
                    .path(certURL + "/delete/" + jobId + "&" + certId)
                    .postWithResponse(null)
                    .getEntity(new GenericType<Resp<DeleteCertResponse>>() { });
    }

    // 代码发布相关接口

    /**
     * commit code
     * @param request
     * @return
     */
    public Resp<CommitJobResponse> commitCode(CommitJobRequest request) {
        return super.createAuthorizedRequest()
                    .path(jobURL + "/commit")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<CommitJobResponse>>() { });
    }

    public Resp<GetCommitCodeResponse> getCommitCode(Long jobId, Integer version) {
        return super.createAuthorizedRequest()
                    .path(codeURL + "/detail/" + jobId + "&" + version)
                    .getWitResponse()
                    .getEntity(new GenericType<Resp<GetCommitCodeResponse>>() { });
    }

    public PageResp<BscCommitCode> listCommitCode(Long jobId) {
        return super.createAuthorizedRequest()
                    .path(codeURL + "/list/" + jobId)
                    .getWitResponse()
                    .getEntity(new GenericType<PageResp<BscCommitCode>>() { });
    }

    public Resp<DeleteCommitCodeResponse> deleteCommitCode(Long jobId, Integer version) {
        return super.createAuthorizedRequest()
                    .path(codeURL + "/delete/" + jobId + "&" + version)
                    .postWithResponse(null)
                    .getEntity(new GenericType<Resp<DeleteCommitCodeResponse>>() { });
    }

    // 作业相关接口
    public Resp<CreateJobResponse> createJob(CreateJobRequest request) {
        return  super.createAuthorizedRequest()
                        .path(jobURL + "/create")
                        .postWithResponse(Entity.json(request))
                        .getEntity(new GenericType<Resp<CreateJobResponse>>() { });
    }

    public Resp<UpdateJobResponse> updateJob(UpdateJobRequest request) {
        if (!StringUtils.isEmpty(request.getCode())) {
            request.setCode(Helper.base64Encode(request.getCode()));
        }

        return super.createAuthorizedRequest()
                    .path(jobURL + "/update")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<UpdateJobResponse>>() { });
    }

    public Resp<CreateJobResponse> createSparkJob(CreateSparkJobRequest request) {
        return super.createAuthorizedRequest()
                    .path(jobURL + "/spark/create")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<CreateJobResponse>>() { });
    }

    public Resp<UpdateJobResponse> updateSparkJob(UpdateSparkJobRequest request) {
        return super.createAuthorizedRequest()
                    .path(jobURL + "/spark/update")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<UpdateJobResponse>>() { });
    }

    public Resp<GetJobDetailResponse> getJob(Long jobId) {
        return super.createAuthorizedRequest()
                    .path(jobURL + "/detail/" + jobId)
                    .getWitResponse()
                    .getEntity(new GenericType<Resp<GetJobDetailResponse>>() { });
    }

    public PageResp<BscJob> listJob(ListJobRequest request) {
        return super.createAuthorizedRequest()
                    .path(jobURL + "/list")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<PageResp<BscJob>>() { });
    }

    public Resp<DebugJobResponse> debugJob(DebugJobRequest request, Long jobId) {
        if (!StringUtils.isEmpty(request.getCode())) {
            request.setCode(Helper.base64Encode(request.getCode()));
        }

        for (HashMap<String, String> data : request.getDebugData()) {
            if (data.containsKey(debugValue)) {
                data.put(debugValue, Helper.base64Encode(data.get(debugValue)));
            }
        }

        return super.createAuthorizedRequest()
                    .path(jobURL + "/debug/" + jobId)
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<DebugJobResponse>>() { });
    }

    public Resp<GetDebugStatusResponse> getDebugStatus(GetDebugStatusRequest request, Long jobId) {
        return super.createAuthorizedRequest()
                    .path(jobURL + "/getDebugStatus/" + jobId)
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<GetDebugStatusResponse>>() { });
    }

    public Resp<DeleteJobResponse> deleteJob(Long jobId) {
        return super.createAuthorizedRequest()
                    .path(jobURL + "/delete/" + jobId)
                    .postWithResponse(null)
                    .getEntity(new GenericType<Resp<DeleteJobResponse>>() { });
    }

    public Resp<CheckCodeResponse> checkGrammar(CheckCodeRequest request) {
        if (!StringUtils.isEmpty(request.getCode())) {
            request.setCode(Helper.base64Encode(request.getCode()));
        }

        return super.createAuthorizedRequest()
                    .path(jobURL + "/grammarCheck")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<CheckCodeResponse>>() { });
    }

    // 作业日志相关接口
    public Resp<List<ExecutorListResponse>> listExecutor(String region, Long opId) {
        return super.createAuthorizedRequest()
                    .header("X-Region", region)
                    .path(logURL + "/executors/" + opId)
                    .getWitResponse()
                    .getEntity(new GenericType<Resp<List<ExecutorListResponse>>>() { });
    }

    public Resp<String> getExecutorLog(ExecutorLogRequest request) {
        return super.createAuthorizedRequest()
                    .path(logURL + "/content")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<String>>() { });
    }

    // 作业启停相关接口
    public Resp<StartResponse> startJob(StartRequest request) {
        return super.createAuthorizedRequest()
                    .path(operationURL + "/start")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<StartResponse>>() { });
    }

    public Resp<StopResponse> stopJob(Long opId) {
        return super.createAuthorizedRequest()
                    .path(operationURL + "/stop/" + opId)
                    .postWithResponse(null)
                    .getEntity(new GenericType<Resp<StopResponse>>() { });
    }

    public Resp<GetResponse> getOperation(Long opId) {
        return super.createAuthorizedRequest()
                    .path(operationURL + "/detail/" + opId)
                    .getWitResponse()
                    .getEntity(new GenericType<Resp<GetResponse>>() { });
    }

    public PageResp<BscJobExecution> listOperation(ListOpRequest request) {
        return super.createAuthorizedRequest()
                    .path(operationURL + "/list")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<PageResp<BscJobExecution>>() { });
    }

    public Resp<String> getJobErrMsg(Long jobId) {
        return super.createAuthorizedRequest()
                    .path(operationURL + "/errormsg/" + jobId)
                    .getWitResponse()
                    .getEntity(new GenericType<Resp<String>>() { });
    }

    public Resp<CanRestartResponse> isJobCanRestart(Long opId) {
        return super.createAuthorizedRequest()
                    .path(operationURL + "/canrestart/" + opId)
                    .getWitResponse()
                    .getEntity(new GenericType<Resp<CanRestartResponse>>() { });
    }

    // 模版相关接口
    public Resp<CreateTemplateResponse> createTemplate(CreateTemplateRequest request) {
        if (!StringUtils.isEmpty(request.getCode())) {
            request.setCode(Helper.base64Encode(request.getCode()));
        }

        return super.createAuthorizedRequest()
                    .path(templateURL + "/create")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<CreateTemplateResponse>>() { });
    }

    public Resp<UpdateTemplateResponse> updateTemplate(UpdateTemplateRequest request) {
        if (!StringUtils.isEmpty(request.getCode())) {
            request.setCode(Helper.base64Encode(request.getCode()));
        }

        return super.createAuthorizedRequest()
                    .path(templateURL + "/update")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<UpdateTemplateResponse>>() { });
    }

    public Resp<GetTemplateDetailResponse> getTemplate(Long templateId) {
        return super.createAuthorizedRequest()
                    .path(templateURL + "/detail/" + templateId)
                    .getWitResponse()
                    .getEntity(new GenericType<Resp<GetTemplateDetailResponse>>() { });
    }

    public PageResp<BscTemplate> listTemplate(ListTemplateRequest request) {
        return super.createAuthorizedRequest()
                    .path(templateURL + "/list")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<PageResp<BscTemplate>>() { });
    }

    public Resp<DeleteTemplateResponse> deleteTemplate(Long templateId) {
        return super.createAuthorizedRequest()
                    .path(templateURL + "/delete/" + templateId)
                    .postWithResponse(null)
                    .getEntity(new GenericType<Resp<DeleteTemplateResponse>>() { });
    }

    public Resp<AddTemplateJobResponse> addJob(AddTemplateJobRequest request) {
        return super.createAuthorizedRequest()
                    .path(templateURL + "/addjob")
                    .postWithResponse(Entity.json(request))
                    .getEntity(new GenericType<Resp<AddTemplateJobResponse>>() { });
    }
}
