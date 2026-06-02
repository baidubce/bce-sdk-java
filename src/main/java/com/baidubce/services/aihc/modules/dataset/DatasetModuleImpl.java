package com.baidubce.services.aihc.modules.dataset;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.aihc.common.AihcConstants;
import com.baidubce.services.aihc.core.AihcClientCore;
import com.baidubce.services.aihc.model.dataset.CreateDatasetRequest;
import com.baidubce.services.aihc.model.dataset.CreateDatasetResponse;
import com.baidubce.services.aihc.model.dataset.ModifyDatasetRequest;
import com.baidubce.services.aihc.model.dataset.ModifyDatasetResponse;
import com.baidubce.services.aihc.model.dataset.CreateDatasetVersionRequest;
import com.baidubce.services.aihc.model.dataset.CreateDatasetVersionResponse;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetsRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetsResponse;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetResponse;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionsRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionsResponse;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionRequest;
import com.baidubce.services.aihc.model.dataset.DescribeDatasetVersionResponse;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetRequest;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetResponse;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetVersionRequest;
import com.baidubce.services.aihc.model.dataset.DeleteDatasetVersionResponse;

/**
 * 数据集管理模块实现类
 */
public class DatasetModuleImpl extends AihcClientCore implements DatasetModule {

    /**
     * 构造函数
     */
    public DatasetModuleImpl() {
        super();
    }

    /**
     * 构造函数
     *
     * @param clientConfiguration BCE客户端配置
     */
    public DatasetModuleImpl(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration);
    }

    @Override
    public CreateDatasetResponse createDataset(CreateDatasetRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.POST, AihcConstants.DatasetActions.CREATE_DATASET);
        
        // Fill payload for POST request
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, CreateDatasetResponse.class);
    }

    @Override
    public ModifyDatasetResponse modifyDataset(ModifyDatasetRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.POST, AihcConstants.DatasetActions.MODIFY_DATASET);

        // Add required query parameters
        if (request.getDatasetId() != null) {
            internalRequest.addParameter("datasetId", request.getDatasetId());
        }

        // Fill payload for POST request
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, ModifyDatasetResponse.class);
    }

    @Override
    public CreateDatasetVersionResponse createDatasetVersion(CreateDatasetVersionRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.POST, AihcConstants.DatasetActions.CREATE_DATASET_VERSION);

        // Add required query parameters
        if (request.getDatasetId() != null) {
            internalRequest.addParameter("datasetId", request.getDatasetId());
        }

        // Fill payload for POST request
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, CreateDatasetVersionResponse.class);
    }

    @Override
    public DescribeDatasetsResponse describeDatasets(DescribeDatasetsRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.GET, AihcConstants.DatasetActions.DESCRIBE_DATASETS);

        // Add query parameters according to API documentation
        if (request.getKeyword() != null) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getStorageType() != null) {
            internalRequest.addParameter("storageType", request.getStorageType());
        }
        if (request.getStorageInstances() != null) {
            internalRequest.addParameter("storageInstances", request.getStorageInstances());
        }
        if (request.getImportFormat() != null) {
            internalRequest.addParameter("importFormat", request.getImportFormat());
        }
        if (request.getPageNumber() != null && request.getPageNumber() > 0) {
            internalRequest.addParameter("pageNumber", String.valueOf(request.getPageNumber()));
        }
        if (request.getPageSize() != null && request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }

        return invokeHttpClient(internalRequest, DescribeDatasetsResponse.class);
    }

    @Override
    public DescribeDatasetResponse describeDataset(DescribeDatasetRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.GET, AihcConstants.DatasetActions.DESCRIBE_DATASET);

        // Add required query parameters
        if (request.getDatasetId() != null) {
            internalRequest.addParameter("datasetId", request.getDatasetId());
        }

        // 添加debug信息
        // System.out.println("DescribeDatasetRequest: " + internalRequest);

        return invokeHttpClient(internalRequest, DescribeDatasetResponse.class);
    }

    @Override
    public DescribeDatasetVersionsResponse describeDatasetVersions(DescribeDatasetVersionsRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.GET, AihcConstants.DatasetActions.DESCRIBE_DATASET_VERSIONS);

        // Add required query parameters
        if (request.getDatasetId() != null) {
            internalRequest.addParameter("datasetId", request.getDatasetId());
        }
        if (request.getPageNumber() != null && request.getPageNumber() > 0) {
            internalRequest.addParameter("pageNumber", String.valueOf(request.getPageNumber()));
        }
        if (request.getPageSize() != null && request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }

        return invokeHttpClient(internalRequest, DescribeDatasetVersionsResponse.class);
    }

    @Override
    public DeleteDatasetResponse deleteDataset(DeleteDatasetRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.POST, AihcConstants.DatasetActions.DELETE_DATASET);

        // Add required query parameters
        if (request.getDatasetId() != null) {
            internalRequest.addParameter("datasetId", request.getDatasetId());
        }

        // Fill payload for POST request
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, DeleteDatasetResponse.class);
    }

    @Override
    public DeleteDatasetVersionResponse deleteDatasetVersion(DeleteDatasetVersionRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.POST, AihcConstants.DatasetActions.DELETE_DATASET_VERSION);

        // Add required query parameters
        if (request.getDatasetId() != null) {
            internalRequest.addParameter("datasetId", request.getDatasetId());
        }
        if (request.getVersionId() != null) {
            internalRequest.addParameter("versionId", request.getVersionId());
        }

        // Fill payload for POST request
        fillPayload(internalRequest, request);

        // 添加debug信息
        System.out.println("DeleteDatasetVersionRequest: " + internalRequest);

        return invokeHttpClient(internalRequest, DeleteDatasetVersionResponse.class);
    }

    @Override
    public DescribeDatasetVersionResponse describeDatasetVersion(DescribeDatasetVersionRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.GET, AihcConstants.DatasetActions.DESCRIBE_DATASET_VERSION);

        // Add required query parameters
        if (request.getDatasetId() != null) {
            internalRequest.addParameter("datasetId", request.getDatasetId());
        }
        if (request.getVersionId() != null) {
            internalRequest.addParameter("versionId", request.getVersionId());
        }

        return invokeHttpClient(internalRequest, DescribeDatasetVersionResponse.class);
    }
} 