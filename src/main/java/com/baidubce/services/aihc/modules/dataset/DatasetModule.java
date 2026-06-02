package com.baidubce.services.aihc.modules.dataset;

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
 * 数据集管理模块接口
 * 定义所有数据集相关的API方法
 */
public interface DatasetModule {

    /**
     * 创建数据集，同时创建一个初始版本
     * 
     * @param request 创建数据集的请求
     * @return 创建结果，包含数据集ID
     */
    CreateDatasetResponse createDataset(CreateDatasetRequest request);

    /**
     * 修改数据集
     * 
     * @param request 修改数据集的请求
     * @return 修改结果
     */
    ModifyDatasetResponse modifyDataset(ModifyDatasetRequest request);

    /**
     * 创建数据集版本
     * 
     * @param request 创建数据集版本的请求
     * @return 创建结果，包含版本ID
     */
    CreateDatasetVersionResponse createDatasetVersion(CreateDatasetVersionRequest request);

    /**
     * 获取数据集列表，支持分页查询
     * 
     * @param request 获取数据集列表的请求
     * @return 数据集列表信息
     */
    DescribeDatasetsResponse describeDatasets(DescribeDatasetsRequest request);

    /**
     * 获取数据集详情，同时返回最新版本信息
     * 
     * @param request 获取数据集详情的请求
     * @return 数据集详情信息
     */
    DescribeDatasetResponse describeDataset(DescribeDatasetRequest request);

    /**
     * 获取数据集版本列表，支持分页查询
     * 
     * @param request 获取数据集版本列表的请求
     * @return 数据集版本列表信息
     */
    DescribeDatasetVersionsResponse describeDatasetVersions(DescribeDatasetVersionsRequest request);

    /**
     * 获取数据集版本详情
     * 
     * @param request 获取数据集版本详情的请求
     * @return 数据集版本详情信息
     */
    DescribeDatasetVersionResponse describeDatasetVersion(DescribeDatasetVersionRequest request);

    /**
     * 删除数据集，同时删除所有版本
     * 
     * @param request 删除数据集的请求
     * @return 删除结果
     */
    DeleteDatasetResponse deleteDataset(DeleteDatasetRequest request);

    /**
     * 删除数据集版本
     * 
     * @param request 删除数据集版本的请求
     * @return 删除结果
     */
    DeleteDatasetVersionResponse deleteDatasetVersion(DeleteDatasetVersionRequest request);
} 