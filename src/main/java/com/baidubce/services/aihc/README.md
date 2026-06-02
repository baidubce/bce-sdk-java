# AIHC (百舸异构计算平台) SDK

AIHC SDK 提供了对百度智能云百舸异构计算平台服务的完整支持，采用模块化架构设计，包括数据集管理和模型管理功能。

## 功能特性

### 模块化架构
- **组合模式设计**: 采用组合模式实现模块化架构
- **向后兼容**: 保持传统API调用方式的同时，支持模块化调用
- **独立模块**: 支持独立使用各个功能模块
- **并行开发**: 减少不同模块间的开发冲突

### 数据集管理
- ✅ 创建数据集 (`createDataset`)
- ✅ 修改数据集 (`modifyDataset`)
- ✅ 创建数据集版本 (`createDatasetVersion`)
- ✅ 查询数据集列表 (`describeDatasets`)
- ✅ 查询数据集详情 (`describeDataset`)
- ✅ 查询数据集版本列表 (`describeDatasetVersions`)
- ✅ 查询数据集版本详情 (`describeDatasetVersion`)
- ✅ 删除数据集 (`deleteDataset`)
- ✅ 删除数据集版本 (`deleteDatasetVersion`)

### 模型管理
- ✅ 查询模型列表 (`describeModels`)
- ✅ 查询模型详情 (`describeModel`)
- ❌ 创建模型 (`createModel`) - 待实现
- ❌ 删除模型 (`deleteModel`) - 待实现
- ❌ 修改模型 (`modifyModel`) - 待实现

## 快速开始

### 1. 环境准备

首先设置百度智能云访问密钥：

```bash
export BCE_ACCESS_KEY_ID="your-access-key"
export BCE_SECRET_ACCESS_KEY="your-secret-key"
```

### 2. 创建客户端

```java
import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.aihc.AihcClient;

// 配置客户端
String accessKey = "your-access-key";
String secretKey = "your-secret-key";
String endpoint = "aihc.bj.baidubce.com";

BceClientConfiguration config = new BceClientConfiguration();
config.setCredentials(new DefaultBceCredentials(accessKey, secretKey));
config.setEndpoint(endpoint);
config.setProtocol(Protocol.HTTPS);

AihcClient client = new AihcClient(config);
```

### 3. 使用方式

#### 方式1：传统调用方式（向后兼容）

```java
// 数据集操作
CreateDatasetRequest createRequest = new CreateDatasetRequest();
createRequest.setName("test-dataset");
createRequest.setStorageType("BOS");
createRequest.setStorageInstance("test-bucket");
createRequest.setImportFormat("FOLDER");
createRequest.setVisibilityScope("ALL_PEOPLE");

CreateDatasetResponse createResponse = client.createDataset(createRequest);
System.out.println("Created Dataset ID: " + createResponse.getId());

// 模型操作
DescribeModelsRequest modelRequest = new DescribeModelsRequest();
modelRequest.setKeyword("test");
modelRequest.setModelType("TENSORFLOW");
modelRequest.setFramework("TF");
modelRequest.setPageNumber(1);
modelRequest.setPageSize(10);

DescribeModelsResponse modelResponse = client.describeModels(modelRequest);
System.out.println("Total Models: " + modelResponse.getTotalCount());
```

#### 方式2：模块化调用方式（推荐）

```java
// 获取数据集模块
DatasetModule datasetModule = client.dataset();

// 数据集操作
CreateDatasetRequest createRequest = new CreateDatasetRequest();
// ... 设置请求参数
CreateDatasetResponse createResponse = datasetModule.createDataset(createRequest);

// 获取模型模块
ModelModule modelModule = client.model();

// 模型操作
DescribeModelsRequest modelRequest = new DescribeModelsRequest();
// ... 设置请求参数
DescribeModelsResponse modelResponse = modelModule.describeModels(modelRequest);
```

#### 方式3：独立模块使用

```java
// 只使用数据集功能
DatasetModuleImpl datasetModule = new DatasetModuleImpl(config);
CreateDatasetResponse response = datasetModule.createDataset(createRequest);

// 只使用模型功能
ModelModuleImpl modelModule = new ModelModuleImpl(config);
DescribeModelsResponse response = modelModule.describeModels(modelRequest);
```

### 4. 运行示例

项目提供了完整的示例代码，可以直接运行：

#### 环境准备
```bash
# 设置环境变量
export BCE_ACCESS_KEY_ID="your-access-key"
export BCE_SECRET_ACCESS_KEY="your-secret-key"

# 编译项目
mvn compile -Dmaven.test.skip=true

# 复制依赖库
mvn dependency:copy-dependencies -DoutputDirectory=target/lib
```

#### 数据集管理示例
```bash
# 查询数据集列表
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDescribeDatasets

# 创建数据集
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleCreateDataset

# 查询数据集详情
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDescribeDataset

# 修改数据集
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleModifyDataset

# 创建数据集版本
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleCreateDatasetVersion

# 查询数据集版本列表
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDescribeDatasetVersions

# 查询数据集版本详情
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDescribeDatasetVersion

# 删除数据集版本
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDeleteDatasetVersion

# 删除数据集
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDeleteDataset
```

#### 模型管理示例
```bash
# 查询模型列表
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.model.ExampleDescribeModels

# 查询模型详情
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.model.ExampleDescribeModel
```

#### 推理管理示例
```bash
# 应用管理
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleCreateApp
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleListApp
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleAppDetails
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleUpdateApp
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleDeleteApp

# Pod管理
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleListPod
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleBlockPod
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleDeletePod

# 变更管理
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleListChange
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleChangeDetail

# 资源池管理
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleListResPoolBrief
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleResPoolDetail

# 应用操作
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleScaleApp
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExamplePubAccess

# 统计信息
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.inference.ExampleListStat
```

#### 批量运行脚本
```bash
# 数据集示例批量运行
cd src/main/java/com/baidubce/examples/aihc/dataset
./run_all_dataset_examples.sh

# 模型示例批量运行
cd src/main/java/com/baidubce/examples/aihc/model
./run_all_model_examples.sh

# 推理示例批量运行
cd src/main/java/com/baidubce/examples/aihc/inference
./run_all_inference_examples.sh
```

#### Windows环境
```cmd
# 数据集示例
java -cp "target\classes;target\lib\*" com.baidubce.examples.aihc.dataset.ExampleDescribeDatasets

# 模型示例
java -cp "target\classes;target\lib\*" com.baidubce.examples.aihc.model.ExampleDescribeModels

# 推理示例
java -cp "target\classes;target\lib\*" com.baidubce.examples.aihc.inference.ExampleListApp
```

## 数据集管理示例

### 创建数据集

```java
CreateDatasetRequest request = new CreateDatasetRequest();
request.setName("test-dataset");
request.setStorageType("BOS");
request.setStorageInstance("test-bucket");
request.setImportFormat("FOLDER");
request.setDescription("测试数据集");
request.setVisibilityScope("ALL_PEOPLE");

// 设置初始版本信息
CreateDatasetRequest.DatasetVersionEntry versionEntry = new CreateDatasetRequest.DatasetVersionEntry();
versionEntry.setDescription("初始版本");
versionEntry.setStoragePath("/test/path");
versionEntry.setMountPath("/mnt/test");
request.setInitVersionEntry(versionEntry);

CreateDatasetResponse response = client.createDataset(request);
System.out.println("Created Dataset ID: " + response.getId());
```

### 查询数据集列表

```java
DescribeDatasetsRequest request = new DescribeDatasetsRequest();
request.setKeyword("test");
request.setStorageType("BOS");
request.setPageNumber(1);
request.setPageSize(10);

DescribeDatasetsResponse response = client.describeDatasets(request);

for (DescribeDatasetsResponse.Dataset dataset : response.getDatasets()) {
    System.out.println("Dataset ID: " + dataset.getId());
    System.out.println("Dataset Name: " + dataset.getName());
    System.out.println("Storage Type: " + dataset.getStorageType());
    System.out.println("Latest Version: " + dataset.getLatestVersion());
}
```

### 查询数据集详情

```java
DescribeDatasetRequest request = new DescribeDatasetRequest();
request.setDatasetId("ds-test123");

DescribeDatasetResponse response = client.describeDataset(request);
System.out.println("Dataset ID: " + response.getId());
System.out.println("Dataset Name: " + response.getName());
System.out.println("Latest Version ID: " + response.getLatestVersionId());
System.out.println("Latest Version: " + response.getLatestVersion());
```

### 创建数据集版本

```java
CreateDatasetVersionRequest request = new CreateDatasetVersionRequest();
request.setDatasetId("ds-test123");
request.setDescription("新版本");
request.setStoragePath("/test/new-version");
request.setMountPath("/mnt/test/v2");

CreateDatasetVersionResponse response = client.createDatasetVersion(request);
System.out.println("Created Version ID: " + response.getId());
```

### 修改数据集

```java
ModifyDatasetRequest request = new ModifyDatasetRequest();
request.setDatasetId("ds-test123");
request.setName("modified-dataset");
request.setDescription("修改后的数据集");
request.setVisibilityScope("USER_GROUP");

ModifyDatasetResponse response = client.modifyDataset(request);
System.out.println("Modified Dataset ID: " + request.getDatasetId());
```

### 删除数据集

```java
DeleteDatasetRequest request = new DeleteDatasetRequest();
request.setDatasetId("ds-test123");

DeleteDatasetResponse response = client.deleteDataset(request);
System.out.println("Deleted Dataset ID: " + request.getDatasetId());
```

## 模型管理示例

### 查询模型列表

```java
DescribeModelsRequest request = new DescribeModelsRequest();
request.setKeyword("test");
request.setModelType("TENSORFLOW");
request.setFramework("TF");
request.setPageNumber(1);
request.setPageSize(10);

DescribeModelsResponse response = client.describeModels(request);

for (DescribeModelsResponse.Model model : response.getModels()) {
    System.out.println("Model ID: " + model.getId());
    System.out.println("Model Name: " + model.getName());
    System.out.println("Model Type: " + model.getModelType());
    System.out.println("Framework: " + model.getFramework());
    System.out.println("Status: " + model.getStatus());
    System.out.println("Owner: " + model.getOwnerName());
}
```

### 查询模型详情

```java
DescribeModelRequest request = new DescribeModelRequest();
request.setModelId("mo-vnufEepi");

DescribeModelResponse response = client.describeModel(request);
System.out.println("Model ID: " + response.getId());
System.out.println("Model Name: " + response.getName());
System.out.println("Model Format: " + response.getModelFormat());
System.out.println("Description: " + response.getDescription());
System.out.println("Owner: " + response.getOwnerName());
System.out.println("Visibility Scope: " + response.getVisibilityScope());

// 显示版本信息
if (response.getVersionEntry() != null) {
    DescribeModelResponse.ModelVersionEntry versionEntry = response.getVersionEntry();
    System.out.println("Latest Version: " + versionEntry.getVersion());
    System.out.println("Version ID: " + versionEntry.getId());
    System.out.println("Source: " + versionEntry.getSource());
    System.out.println("Storage Bucket: " + versionEntry.getStorageBucket());
    System.out.println("Storage Path: " + versionEntry.getStoragePath());
}
```

## API 参考

### 数据集管理

#### CreateDatasetRequest
- `name`: 数据集名称（必选）
- `storageType`: 存储类型（必选，PFS/BOS）
- `storageInstance`: 存储实例（必选）
- `importFormat`: 导入格式（必选，FILE/FOLDER）
- `description`: 描述（可选）
- `owner`: 所有者（可选）
- `visibilityScope`: 可见范围（必选，ALL_PEOPLE/ONLY_OWNER/USER_GROUP）
- `visibilityUser`: 用户权限列表（可选）
- `visibilityGroup`: 用户组权限列表（可选）
- `initVersionEntry`: 初始版本信息（必选）

#### DescribeDatasetsRequest
- `keyword`: 关键词搜索（可选）
- `storageType`: 存储类型（可选）
- `importFormat`: 导入格式（可选）
- `pageNumber`: 页码（可选，默认1）
- `pageSize`: 每页大小（可选）

#### DescribeDatasetRequest
- `datasetId`: 数据集ID（必选）

#### CreateDatasetVersionRequest
- `datasetId`: 数据集ID（必选）
- `description`: 版本描述（可选）
- `storagePath`: 存储路径（必选）
- `mountPath`: 挂载路径（必选）

#### ModifyDatasetRequest
- `datasetId`: 数据集ID（必选，查询参数）
- `name`: 数据集名称（可选）
- `description`: 描述（可选）
- `visibilityScope`: 可见范围（可选）
- `visibilityUser`: 用户权限列表（可选）
- `visibilityGroup`: 用户组权限列表（可选）

#### DeleteDatasetRequest
- `datasetId`: 数据集ID（必选）

#### DeleteDatasetVersionRequest
- `datasetId`: 数据集ID（必选）
- `versionId`: 版本ID（必选）

#### DescribeDatasetVersionsRequest
- `datasetId`: 数据集ID（必选）
- `pageNumber`: 页码（可选，默认1）
- `pageSize`: 每页大小（可选）

#### DescribeDatasetVersionRequest
- `datasetId`: 数据集ID（必选）
- `versionId`: 版本ID（必选）

### 模型管理

#### DescribeModelsRequest
- `keyword`: 关键词搜索（可选）
- `modelType`: 模型类型（可选，TENSORFLOW/PYTORCH/ONNX）
- `framework`: 框架（可选，TF/PT/ONNX）
- `status`: 状态（可选）
- `pageNumber`: 页码（可选，默认1）
- `pageSize`: 每页大小（可选）

#### DescribeModelRequest
- `modelId`: 模型ID（必选）

## 响应结构

### 数据集响应

#### CreateDatasetResponse
- `id`: 数据集ID

#### DescribeDatasetsResponse
- `datasets`: 数据集列表
- `Dataset`: 数据集信息
  - `id`: 数据集ID
  - `name`: 数据集名称
  - `storageType`: 存储类型
  - `storageInstance`: 存储实例
  - `importFormat`: 导入格式
  - `description`: 描述
  - `owner`: 所有者
  - `ownerName`: 所有者名称
  - `visibilityScope`: 可见范围
  - `permission`: 权限
  - `latestVersionId`: 最新版本ID
  - `latestVersion`: 最新版本
  - `createdAt`: 创建时间
  - `updatedAt`: 更新时间

#### DescribeDatasetResponse
- 包含数据集的所有信息
- `latestVersionEntry`: 最新版本详细信息

#### CreateDatasetVersionResponse
- `id`: 版本ID

#### DescribeDatasetVersionsResponse
- `totalCount`: 版本总数
- `versions`: 版本列表
- `DatasetVersionEntry`: 版本信息
  - `id`: 版本ID
  - `version`: 版本号
  - `description`: 版本描述
  - `storagePath`: 存储路径
  - `mountPath`: 挂载路径
  - `createUser`: 创建用户
  - `createUserName`: 创建用户名
  - `createdAt`: 创建时间
  - `updatedAt`: 更新时间

### 模型响应

#### DescribeModelsResponse
- `totalCount`: 总数量
- `models`: 模型列表
- `Model`: 模型信息
  - `id`: 模型ID
  - `name`: 模型名称
  - `description`: 描述
  - `modelType`: 模型类型
  - `framework`: 框架
  - `status`: 状态
  - `owner`: 所有者
  - `ownerName`: 所有者名称
  - `createdAt`: 创建时间
  - `updatedAt`: 更新时间

#### DescribeModelResponse
- `name`: 模型名称
- `id`: 模型ID
- `initSource`: 模型创建时的来源
- `modelFormat`: 模型格式
- `description`: 描述
- `createdAt`: 创建时间
- `updatedAt`: 更新时间
- `owner`: 所有者
- `ownerName`: 所有者名称
- `visibilityScope`: 可见范围
- `versionEntry`: 最新版本的详细信息
  - `version`: 版本号
  - `id`: 版本ID
  - `source`: 来源
  - `taskId`: 任务ID
  - `storageBucket`: 存储桶
  - `storagePath`: 存储路径
  - `modelMetrics`: 模型指标
  - `description`: 描述
  - `createdAt`: 创建时间
  - `createUser`: 创建用户
  - `createUserName`: 创建用户名

## 错误处理

所有API调用都可能抛出 `BceClientException` 异常，建议使用 try-catch 进行错误处理：

```java
try {
    CreateDatasetResponse response = client.createDataset(request);
    System.out.println("Success: " + response.getId());
} catch (BceClientException e) {
    System.err.println("API调用失败: " + e.getMessage());
    e.printStackTrace();
}
```

## 模块化架构

### 核心组件

#### AihcClientCore
- 提供通用的HTTP请求处理功能
- 封装了BCE客户端的基础操作

#### AihcConstants
- 集中管理API动作名称和常量
- 提供存储类型、导入格式、可见范围等枚举值

#### DatasetModule / DatasetModuleImpl
- 数据集管理模块接口和实现
- 包含所有数据集相关的API方法

#### ModelModule / ModelModuleImpl
- 模型管理模块接口和实现
- 包含所有模型相关的API方法

### 使用优势

1. **模块化开发**: 不同团队可以并行开发不同模块
2. **代码复用**: 通用功能在核心组件中复用
3. **易于维护**: 每个模块职责单一，便于维护
4. **向后兼容**: 保持传统API调用方式
5. **灵活使用**: 支持多种使用方式

## 注意事项

1. **权限要求**: 请确保使用有效的 Access Key 和 Secret Key
2. **Endpoint配置**: 根据实际需求选择合适的 endpoint
3. **数据安全**: 删除操作不可逆，请谨慎操作
4. **错误处理**: 建议在生产环境中使用适当的错误处理和重试机制
5. **模块化使用**: 推荐使用模块化调用方式，便于代码维护

## 开发计划

### 已实现功能
- ✅ 数据集管理完整功能
- ✅ 模型查询功能（describeModels, describeModel）
- ✅ 模块化架构

### 待实现功能
- ❌ 模型创建功能
- ❌ 模型删除功能
- ❌ 模型修改功能
- ❌ 推理相关功能
- ❌ 更多高级功能 