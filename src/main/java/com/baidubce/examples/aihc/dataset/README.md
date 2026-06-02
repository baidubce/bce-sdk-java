# AIHC 数据集管理示例

本目录包含了AIHC SDK数据集管理功能的所有示例代码。

## 环境准备

在运行示例之前，请确保：

1. **设置环境变量**：
```bash
export BCE_ACCESS_KEY_ID=your-access-key
export BCE_SECRET_ACCESS_KEY=your-secret-key
export ENDPOINT=aihc-endpoint
export DATASET_ID=your-test-datasetid
export DATASET_VERSION_ID=your-test-datasetversionid
```
百舸OpenAPI域名参考：https://cloud.baidu.com/doc/AIHC/s/amaz0nqs7

2. **编译项目**：
```bash
mvn compile -Dmaven.test.skip=true
```

3. **复制依赖库**：
```bash
mvn dependency:copy-dependencies -DoutputDirectory=target/lib
```

## 运行示例

### 1. 查询数据集列表
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDescribeDatasets
```

### 2. 创建数据集
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleCreateDataset
```

### 3. 查询数据集详情
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDescribeDataset
```

### 4. 修改数据集
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleModifyDataset
```

### 5. 创建数据集版本
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleCreateDatasetVersion
```

### 6. 查询数据集版本列表
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDescribeDatasetVersions
```

### 7. 查询数据集版本详情
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDescribeDatasetVersion
```

### 8. 删除数据集版本
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDeleteDatasetVersion
```

### 9. 删除数据集
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.dataset.ExampleDeleteDataset
```
