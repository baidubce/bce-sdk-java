package com.baidubce.services.aihc;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.services.aihc.modules.dataset.DatasetModule;
import com.baidubce.services.aihc.modules.dataset.DatasetModuleImpl;
import com.baidubce.services.aihc.modules.model.ModelModule;
import com.baidubce.services.aihc.modules.model.ModelModuleImpl;

/**
 * AIHC (百舸异构计算平台) 客户端
 * 采用模块化设计，支持数据集、模型、推理等功能模块
 * 
 * 使用示例:
 * <pre>
 * // 创建客户端
 * AihcClient client = new AihcClient();
 * 
 * // 方式1：通过模块访问（推荐）
 * DatasetModule datasetModule = client.dataset();
 * CreateDatasetRequest createRequest = new CreateDatasetRequest();
 * createRequest.setName("test1");
 * createRequest.setStorageType("BOS");
 * createRequest.setStorageInstance("bucket1");
 * createRequest.setImportFormat("FOLDER");
 * createRequest.setDescription("test dataset");
 * createRequest.setVisibilityScope("USER_GROUP");
 * 
 * CreateDatasetResponse response = datasetModule.createDataset(createRequest);
 * System.out.println("Created Dataset ID: " + response.getId());
 * 
 * // 方式2：独立使用模块
 * DatasetModuleImpl datasetModule = new DatasetModuleImpl(config);
 * CreateDatasetResponse response = datasetModule.createDataset(createRequest);
 * 
 * // 方式3：链式调用
 * CreateDatasetResponse response = client.dataset().createDataset(createRequest);
 * </pre>
 */
public class AihcClient extends AbstractBceClient {

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static HttpResponseHandler[] aihcHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * 数据集管理模块
     */
    private final DatasetModule datasetModule;

    /**
     * 模型管理模块
     */
    private final ModelModule modelModule;

    public AihcClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new AihcClient to invoke service methods on aihc.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public AihcClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, aihcHandlers);
        this.datasetModule = new DatasetModuleImpl(clientConfiguration);
        this.modelModule = new ModelModuleImpl(clientConfiguration);
    }

    /**
     * 获取数据集管理模块
     * 
     * @return 数据集管理模块
     */
    public DatasetModule dataset() {
        return datasetModule;
    }

    /**
     * 获取模型管理模块
     * 
     * @return 模型管理模块
     */
    public ModelModule model() {
        return modelModule;
    }
}

