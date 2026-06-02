# AIHC 模型管理示例

本目录包含了AIHC SDK模型管理功能的所有示例代码。

## 环境准备

在运行示例之前，请确保：

1. **设置环境变量**：
```bash
export BCE_ACCESS_KEY_ID="your-access-key"
export BCE_SECRET_ACCESS_KEY="your-secret-key"
```

2. **编译项目**：
```bash
mvn compile -Dmaven.test.skip=true
```

3. **复制依赖库**：
```bash
mvn dependency:copy-dependencies -DoutputDirectory=target/lib
```

## 运行示例

### 1. 查询模型列表
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.model.ExampleDescribeModels
```

### 2. 查询模型详情
```bash
java -cp "target/classes:target/lib/*" com.baidubce.examples.aihc.model.ExampleDescribeModel
```
