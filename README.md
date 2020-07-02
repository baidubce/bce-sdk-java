# 概述

本文档主要介绍百度云JAVA语言版的开发者工具包（SDK），用户可基于该SDK使用JAVA语言接入百度云的各项产品（详见支持产品列表）。
SDK封装了便捷的调用接口，保持了多种编程语言版的使用方式、调用接口相似，提供了统一的错误码和返回格式，方便开发者调试。

## 安装SDK工具包

### 运行环境

JAVA SDK可以在Java1.7，Java1.8环境下运行。

### 前提条件

[**AK/SK**](https://cloud.baidu.com/doc/Reference/s/9jwvz2egb)：SDK 认证时必须传入 AK/SK 参数，在[安全认证页面](https://console.bce.baidu.com/iam/#/iam/accesslist) 获取 Access Key和Secret Key。

用户可以通过两种方式与百度智能云进行交互，包括认证方式和匿名方式。对于认证方式，需要通过使用Access Key / Secret Key加密的方法来验证某个请求的发送者身份。Access Key(AK) 用于标示用户，Secret Key(SK) 是用户用于加密认证字符串和百度智能云用来验证认证字符串的密钥，其中SK必须保密，只有用户和百度智能云知道。

### 安装SDK

**使用Maven安装**

在Maven的pom.xml文件中添加bce-java-sdk的依赖：

```xml
<dependency>
    <groupId>com.baidubce</groupId>
    <artifactId>bce-java-sdk</artifactId>
    <version>{version}</version>
</dependency>
```

**SDK目录结构**

```text
com.baidubce
├── auth                                      //BCE签名相关类
├── http                                      //BCE的Http通信相关类
├── internal                                  //SDK内部类
├── model                                     //BCE公用model类
├── services
│    └─ bcc                                   //BCC服务相关类                             
│    └─ billing                               //BILLING服务相关类     
│    └─ bos                                   //BOS服务相关类     
│    └─ cdn                                   //CDN服务相关类     
│    └─ iothub                                //IOTHUB服务相关类     
│    └─ lss                                   //LSS服务相关类     
│    └─ vpc                                   //VPC服务相关类
│    └─ ...                                   //其它服务相关类          
├── util                                      //BCE公用工具类
├── BceClientConfiguration.class              //对BCE的HttpClient的配置
├── BceClientException.class                  //BCE客户端的异常类
├── BceServiceException.class                 //与BCE服务端交互后的异常类
├── ErrorCode.class                           //BCE通用的错误码
└── Region.class                              //BCE提供服务的区域
```

### 卸载SDK

预期卸载SDK时，删除下载的源码即可。

## 快速开始

### 确认Endpoint

在使用SDK之前，需确认您将接入的百度云产品的Endpoint（服务域名）。以百度云服务器产品为例，可阅读[BCC访问域名](https://cloud.baidu.com/doc/BCC/s/0jwvyo603)的部分，理解Endpoint相关的概念。其他服务类似，需理解并确认对应服务的Endpoint。

### 创建Client对象

每种具体的服务都有一个`Client`对象，为开发者与对应的服务进行交互封装了一系列易用的方法。

### 调用功能接口

开发者基于创建的对应服务的`Client`对象，即可调用相应的功能接口，使用百度云产品的功能。

### 示例

下面以百度云服务器（BCC）为例，给出一个基本的使用示例，详细使用说明请参考各服务的详细说明文档。

```java
public class Sample {
    public static void main(String[] args) {
        String ak = <your-access-key-id>;               // 用户的Access Key ID
        String sk = <your-secret-access-key>;           // 用户的Secret Access Key

        // 初始化一个BccClient
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        BccClient client = new BccClient(config);
        }
}
```

在上面代码中，变量`AK`与`SK`是由系统分配给用户的，均为字符串，用于标识用户，为访问BCC做签名验证。 其中`AK`对应控制台中的“Access Key ID”，`SK`对应控制台中的“Access Key Secret”，获取方式请参考《操作指南 [获取ACCESSKEY](https://cloud.baidu.com/doc/Reference/s/9jwvz2egb)》。

上面的方式使用默认域名作为BCC的服务地址，如果用户需要自己指定域名，可以通过传入ENDPOINT参数来指定。

```java
String ak = <your-access-key-id>;               // 用户的Access Key ID
String sk = <your-secret-access-key>;           // 用户的Secret Access Key
String endpoint = <domain-name>;                // 用户自己指定的域名

BceClientConfiguration config = new BceClientConfiguration();
config.setCredentials(new DefaultBceCredentials(ak, sk));
config.setEndpoint(endpointy);
BccClient client = new BccClient(config);
```

> **注意：** ENDPOINT参数只能用指定的包含区域的域名来进行定义，不指定时默认为北京区域`http://bcc.bj.baidubce.com`。百度智能云目前开放了多区域支持，请参考[区域选择说明](https://cloud.baidu.com/doc/Reference/s/2jwvz23xx)。

## 配置

### 使用HTTPS协议

该SDK支持使用HTTPS协议访问百度云的服务产品，您可以通过如下两种方式在BCC Java SDK中使用HTTPS访问BCC服务：

- 在endpoint中指明https:

```java
String ak = "ak";
String sk = "sk";
BceClientConfiguration config = new BceClientConfiguration();
config.setCredentials(new DefaultBceCredentials(ak, sk));
BccClient client = new BccClient(config);
```

- 通过调用setProtocol方法设置https协议:

```java
String endpoint = "bcc.bj.baidubce.com"; // endpoint中不包含protocol
String ak = "ak";
String sk = "sk";
BceClientConfiguration config = new BceClientConfiguration();
config.setCredentials(new DefaultBceCredentials(ak, sk));
config.setEndpoint(endpoint);
config.setProtocol(Protocol.HTTPS); // 如果不指明, 则使用http
BccClient client = new BccClient(config);
```

> 注意：如果在endpoint中指明了protocol, 则endpoint中的生效, 另外单独再调用setProtocol()不起作用。

```java
String endpoint = "http://bcc.bj.baidubce.com";
String ak = "ak";
String sk = "sk";
BceClientConfiguration config = new BceClientConfiguration();
config.setCredentials(new DefaultBceCredentials(ak, sk));
config.setEndpoint(endpoint);    
config.setProtocol(Protocol.HTTPS); // endpoint中已经指明, 此为无效操作, 对http也是如此
BccClient client = new BccClient(config);
```

### 配置BccClient

如果用户需要配置BccClient的一些细节的参数，可以在构造BccClient的时候传入BceClientConfiguration对象。 BceClientConfiguration是服务的公用配置类，可以为客户端配置代理，最大连接数等参数。

### 使用代理

下面一段代码可以让客户端使用代理访问BCC服务：

```java
String ak = <your-access-key-id>;               // 用户的Access Key ID
String sk = <your-secret-access-key>;           // 用户的Secret Access Key
String endpoint = <domain-name>;                // 用户自己指定的域名

// 创建BceClientConfiguration实例
BceClientConfiguration config = new BceClientConfiguration();

// 配置代理为本地8080端口
config.setProxyHost("127.0.0.1");
config.setProxyPort(8080);

// 创建BCC客户端
config.setCredentials(new DefaultBceCredentials(ak, sk));
config.setEndpoint(endpoint);
BccClient client = new BccClient(config);
```



使用上面的代码段，客户端的所有操作都会通过127.0.0.1地址的8080端口做代理执行。 对于有用户验证的代理，可以通过下面的代码段配置用户名和密码：

```java
// 创建BceClientConfiguration实例
BceClientConfiguration config = new BceClientConfiguration();

// 配置代理为本地8080端口
config.setProxyHost("127.0.0.1");
config.setProxyPort(8080);

//设置用户名和密码
config.setProxyUsername(<username>);                             //用户名
config.setProxyPassword(<password>);                             //密码
```

### 设置网络参数

用户可以用BceClientConfiguration对基本网络参数进行设置：

```java
BceClientConfiguration config = new BceClientConfiguration();

// 设置HTTP最大连接数为10
config.setMaxConnections(10);

// 设置TCP连接超时为5000毫秒
config.setConnectionTimeout(5000);

// 设置Socket传输数据超时的时间为2000毫秒
config.setSocketTimeout(2000);
```

**参数说明**

通过BceClientConfiguration能指定的所有参数如下表所示：

| 参数                                    | 说明                                           |
| --------------------------------------- | ---------------------------------------------- |
| UserAgent用户代理，指HTTP的User-Agent头 |                                                |
| Protocol                                | 连接协议类型                                   |
| ProxyDomain                             | 访问NTLM验证的代理服务器的Windows域名          |
| ProxyHost                               | 代理服务器主机地址                             |
| ProxyPort                               | 代理服务器端口                                 |
| ProxyUsername                           | 代理服务器验证的用户名                         |
| ProxyPassword                           | 代理服务器验证的密码                           |
| ProxyPreemptiveAuthenticationEnabled    | 是否设置用户代理认证                           |
| ProxyWorkstation                        | NTLM代理服务器的Windows工作站名称              |
| LocalAddress                            | 本地地址                                       |
| ConnectionTimeoutInMillis               | 建立连接的超时时间（单位：毫秒）               |
| SocketTimeoutInMillis                   | 通过打开的连接传输数据的超时时间（单位：毫秒） |
| MaxConnections                          | 允许打开的最大HTTP连接数                       |
| RetryPolicy                             | 连接重试策略                                   |
| SocketBufferSizeInBytes                 | Socket缓冲区大小                               |
| StreamBufferSize                        | 流文件缓冲区大小                               |

## 错误处理

BCC异常提示有如下两种方式：

| 异常方法           | 说明       |
| ------------------ | ---------- |
| BceClientException | 客户端异常 |
| BceServerException | 服务器异常 |

用户可以使用try获取某个事件所产生的异常，例如：

```java
try {
    bccClient.listInstances();
} catch (BceServiceException bce){
    System.out.println(bce.getMessage());
} catch (BceClientException bce){
    System.out.println(bce.getMessage());
}
```

### SDK日志

BCC Java SDK发布版本中增加了logback作为slf4j的实现，如果用户没有自己的实现可以直接用，如果工程中有其他的如log4j则可以替代。

### 默认日志

如果用户使用默认的logback，则需要配置logback.xml到classpath中。如果没有这个配置文件，日志级别默认为DEBUG。

```xml
<configuration>
    <property name="LOG_HOME" value="./log/"/>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type
             ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${LOG_HOME}/BccUnitTest.%d{yyyy-MM-dd}.log</FileNamePattern>
            <MaxHistory>30</MaxHistory>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>10MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <root level="info">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```

### 自有日志模块

若用户使用自己的日志实现模块，例如项目依赖于Maven，则可以类似下面的配置到pom.xml中来去除logback。

```xml
<?xml version="1.0" encoding="utf-8"?>

<dependency>      
  <groupId>com.baidubce</groupId>      
  <artifactId>bce-java-sdk</artifactId>      
  <version>${bce.sdk.version}</version>      
  <exclusions>        
    <exclusion>          
      <groupId>ch.qos.logback</groupId>          
      <artifactId>logback-classic</artifactId>        
    </exclusion>        
    <exclusion>          
      <groupId>ch.qos.logback</groupId>          
      <artifactId>logback-core</artifactId>        
    </exclusion>        
    <exclusion>          
      <groupId>org.slf4j</groupId>          
      <artifactId>jcl-over-slf4j</artifactId>        
    </exclusion>      
  </exclusions>    
</dependency>
```

## 支持产品列表

详见[SDK平台](https://cloud.baidu.com/doc/Developer/index.html)

## 测试
参照**快速开始**，使用自己的AK、SK进行调用测试。

## 如何贡献
本项目是维护各产品线提供的SDK集合，需要产品线自行补充完善

## 讨论
https://github.com/baidubce/bce-sdk-java/issues
