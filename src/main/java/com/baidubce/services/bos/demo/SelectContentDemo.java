package com.baidubce.services.bos.demo;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.InputSerialization;
import com.baidubce.services.bos.model.OutputSerialization;
import com.baidubce.services.bos.model.SelectObjectRequest;
import com.baidubce.services.bos.model.SelectObjectResponse;

import java.io.ByteArrayInputStream;

/**
 * 选取特定文件内容的demo
 */
public class SelectContentDemo {
    // 选取CSV文件类型的内容
    public static void selectCsv() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        final String csvContent = "header1,header2,header3\r\n" +
                "1,2,3.4\r\n" +
                "a,b,c\r\n" +
                "\"d\",\"e\",\"f\"\r\n" +
                "true,false,true\r\n" +
                "2006-01-02 15:04:06,2006-01-02 16:04:06,2006-01-02 17:04:06";
        client.putObject("bucketName", "test-csv", new ByteArrayInputStream(csvContent.getBytes()));

        SelectObjectRequest request = new SelectObjectRequest("bucketName", "test-csv")
                .withSelectType("csv")
                .withExpression("select * from BosObject limit 3")
                .withInputSerialization(new InputSerialization()
                        .withCompressionType("NONE")
                        .withFileHeaderInfo("NONE")
                        .withRecordDelimiter("\r\n")
                        .withFieldDelimiter(",")
                        .withQuoteCharacter("\"")
                        .withCommentCharacter("#"))
                .withOutputSerialization(new OutputSerialization()
                        .withOutputHeader(false)
                        .withQuoteFields("ALWAYS")
                        .withRecordDelimiter("\n")
                        .withFieldDelimiter(",")
                        .withQuoteCharacter("\""))
                .withRequestProgress(false);
        SelectObjectResponse response = client.selectObject(request);

        // 输出返回的记录
        SelectObjectResponse.Messages messages = response.getMessages();
        while (messages.hasNext()) {
            SelectObjectResponse.CommonMessage message = messages.next();
            if (message.Type.equals("Records")) {
                for (String record: message.getRecords()) {
                    System.out.println(record);
                }
            }
        }

        /**
         * 输出结果：
         *
         * "header1","header2","header3"
         * "1","2","3.4"
         * "a","b","c"
         *
         */

        // 关闭客户端
        client.shutdown();
    }

    // 选取JSON文件类型的内容
    public static void selectJson() {
        String ACCESS_KEY_ID = "akxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";             // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "skxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";         // 用户的Secret Access Key
        String ENDPOINT = "bj.bcebos.com";                                     // 用户自己指定的域名，参考说明文档

        // 初始化一个BosClient
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);

        final String jsonContent = "{\n" +
                "\t\"name\": \"Smith\",\n" +
                "\t\"age\": 16,\n" +
                "\t\"org\": null\n" +
                "}\n" +
                "{\n" +
                "\t\"name\": \"charles\",\n" +
                "\t\"age\": 27,\n" +
                "\t\"org\": \"baidu\"\n" +
                "}\n" +
                "{\n" +
                "\t\"name\": \"jack\",\n" +
                "\t\"age\": 35,\n" +
                "\t\"org\": \"bos\"\n" +
                "}";
        client.putObject("bucketName", "test-json", new ByteArrayInputStream(jsonContent.getBytes()));

        SelectObjectRequest request = new SelectObjectRequest("bucketName", "test-json")
                .withSelectType("json")
                .withExpression("select * from BosObject where age > 20")
                .withInputSerialization(new InputSerialization()
                        .withCompressionType("NONE")
                        .withJsonType("LINES"))
                .withOutputSerialization(new OutputSerialization()
                        .withRecordDelimiter("\n"))
                .withRequestProgress(false);
        SelectObjectResponse response = client.selectObject(request);

        // 输出返回的记录
        SelectObjectResponse.Messages messages = response.getMessages();
        while (messages.hasNext()) {
            SelectObjectResponse.CommonMessage message = messages.next();
            if (message.Type.equals("Records")) {
                for (String record: message.getRecords()) {
                    System.out.println(record);
                }
            }
        }

        /**
         * 输出结果：
         *
         * {"name":"charles","age":27,"org":"baidu"}
         * {"name":"jack","age":35,"org":"bos"}
         *
         */

        // 关闭客户端
        client.shutdown();
    }

}
