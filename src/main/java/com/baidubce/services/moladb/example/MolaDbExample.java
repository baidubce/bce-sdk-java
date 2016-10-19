package com.baidubce.services.moladb.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.moladb.MolaDbClientConfiguration;
import com.baidubce.services.moladb.MolaDbClient;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.AttributeDefinition;
import com.baidubce.services.moladb.model.AttributeValue;
import com.baidubce.services.moladb.model.AttributeValueUpdate;
import com.baidubce.services.moladb.model.BatchGetItemRequest;
import com.baidubce.services.moladb.model.BatchGetItemResponse;
import com.baidubce.services.moladb.model.BatchWriteItemRequest;
import com.baidubce.services.moladb.model.BatchWriteItemResponse;
import com.baidubce.services.moladb.model.CreateInstanceRequest;
import com.baidubce.services.moladb.model.CreateTableRequest;
import com.baidubce.services.moladb.model.CreateTableResponse;
import com.baidubce.services.moladb.model.DeleteInstanceRequest;
import com.baidubce.services.moladb.model.DeleteItemRequest;
import com.baidubce.services.moladb.model.DeleteItemResponse;
import com.baidubce.services.moladb.model.DeleteRequest;
import com.baidubce.services.moladb.model.DeleteTableRequest;
import com.baidubce.services.moladb.model.GetInstanceRequest;
import com.baidubce.services.moladb.model.GetInstanceResponse;
import com.baidubce.services.moladb.model.GetTableRequest;
import com.baidubce.services.moladb.model.GetTableResponse;
import com.baidubce.services.moladb.model.GetItemRequest;
import com.baidubce.services.moladb.model.GetItemResponse;
import com.baidubce.services.moladb.model.Key;
import com.baidubce.services.moladb.model.KeySchemaElement;
import com.baidubce.services.moladb.model.KeysAndAttributes;
import com.baidubce.services.moladb.model.ListInstancesResponse;
import com.baidubce.services.moladb.model.ListTablesRequest;
import com.baidubce.services.moladb.model.ListTablesResponse;
import com.baidubce.services.moladb.model.ProvisionedThroughput;
import com.baidubce.services.moladb.model.PutItemRequest;
import com.baidubce.services.moladb.model.PutItemResponse;
import com.baidubce.services.moladb.model.PutRequest;
import com.baidubce.services.moladb.model.QueryRequest;
import com.baidubce.services.moladb.model.QueryResponse;
import com.baidubce.services.moladb.model.UpdateItemRequest;
import com.baidubce.services.moladb.model.UpdateItemResponse;
import com.baidubce.services.moladb.model.UpdateTableRequest;
import com.baidubce.services.moladb.model.UpdateTableResponse;
import com.baidubce.services.moladb.model.WriteRequest;

public class MolaDbExample {

    private static final String ENDPOINT = "http://moladb.bj.baidubce.com";
    private static final String ACCESS_KEY_ID = "Your access key";
    private static final String SECRET_ACCESS_KEY = "Your secret access key";
    private static final String TABLE1 = "blogData";
    private static final String TABLE2 = "userData";
    private static final String KEYATTR1 = "subject";
    private static final String KEYATTR2 = "date";
    private MolaDbClient molaDbClient;

    public MolaDbExample(MolaDbClient client) {
        this.molaDbClient = client;
    }

    private void createTable() {
        try {
            // create first table
            String tableName = TABLE1;
            ProvisionedThroughput prov = new ProvisionedThroughput();
            prov.withReadCapacityUnits(100L).withWriteCapacityUnits(200L);
            List<AttributeDefinition> attributeDefs = new ArrayList<AttributeDefinition>();
            attributeDefs.add(new AttributeDefinition().withAttributeName(KEYATTR1)
                              .withAttributeType(AttributeValue.ATTRIBUTE_TYPE_STRING));
            attributeDefs.add(new AttributeDefinition().withAttributeName(KEYATTR2)
                              .withAttributeType(AttributeValue.ATTRIBUTE_TYPE_STRING));
            CreateTableRequest req = new CreateTableRequest(tableName).withAttributeDefinitions(attributeDefs);
            req.withProvisionedThroughput(prov);
            
            List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();

            keySchema.add(new KeySchemaElement().withAttributeName(KEYATTR1)
                          .withKeyType(KeySchemaElement.HASH_KEY_TYPE));
            keySchema.add(new KeySchemaElement().withAttributeName(KEYATTR2)
                          .withKeyType(KeySchemaElement.RANGE_KEY_TYPE));
            req.withKeySchema(keySchema);

            CreateTableResponse result = null; 
            result = molaDbClient.createTable(req);
            System.out.println("result:" + result.toString());
            
            // create second table and only need to define hash key and range key only
            tableName = TABLE2;
            attributeDefs = new ArrayList<AttributeDefinition>();
            attributeDefs.add(new AttributeDefinition().withAttributeName("city")
                              .withAttributeType(AttributeValue.ATTRIBUTE_TYPE_STRING));
            attributeDefs.add(new AttributeDefinition().withAttributeName("autherName")
                              .withAttributeType(AttributeValue.ATTRIBUTE_TYPE_STRING));
            req = new CreateTableRequest(tableName)
                          .withAttributeDefinitions(attributeDefs);
            req.withProvisionedThroughput(prov);
            keySchema = new ArrayList<KeySchemaElement>();
            keySchema.add(new KeySchemaElement().withAttributeName("city")
                          .withKeyType(KeySchemaElement.HASH_KEY_TYPE));
            keySchema.add(new KeySchemaElement().withAttributeName("autherName")
                          .withKeyType(KeySchemaElement.RANGE_KEY_TYPE));
            req.withKeySchema(keySchema);

            result = null; 
            result = molaDbClient.createTable(req);
            System.out.println("result:" + result.toString());
            
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void updateTable() {
        try {
            String tableName = TABLE1;

            UpdateTableRequest req = new UpdateTableRequest(tableName);
            ProvisionedThroughput prov = new ProvisionedThroughput();
            int index = 0;
            long read = 100;
            long write = 200;
            while (index < 6) {
                read = read * 2;
                write = write * 2;
                prov.withReadCapacityUnits(read).withWriteCapacityUnits(write);
                req.withProvisionedThroughput(prov);

                UpdateTableResponse result = molaDbClient.updateTable(req);
                System.out.println("result:" + result.toString());
                ++index;
            }
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void deleteTable() {
        try {
            DeleteTableRequest req1 = new DeleteTableRequest(TABLE1);
            DeleteTableRequest req2 = new DeleteTableRequest(TABLE2);
            molaDbClient.deleteTable(req1);
            molaDbClient.deleteTable(req2);
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void listTables() {
        try {
            ListTablesRequest req = new ListTablesRequest();
            ListTablesResponse result = molaDbClient.listTables(req);
            List<String> tables = result.getTableNames();
            for (int i = 0; i < tables.size(); ++i) {
                System.out.println("tables:" + tables.get(i));
            }
            System.out.println("all tables:" + tables.toString());
            System.out.println("result:" + result.toString());
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void getTable() {
        try {
            GetTableRequest req = new GetTableRequest(TABLE1);
            GetTableResponse result = molaDbClient.getTable(req);
            System.out.println("result:" + result.toString());
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putItem() {
        try {
            String tableName = TABLE1;
            Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
            item.put(KEYATTR1, new AttributeValue().withS("how to use moladb?"));
            item.put(KEYATTR2, new AttributeValue().withS("2015/01/02"));
            item.put("body", new AttributeValue().withB("so easy".getBytes()));

            PutItemRequest putItemRequest = new PutItemRequest(tableName).withItem(item);
            PutItemResponse result = molaDbClient.putItem(putItemRequest);
            System.out.println("result:" + result.toString());
            
            Map<String, AttributeValue> item3 = new HashMap<String, AttributeValue>();
            item3.put(KEYATTR1, new AttributeValue("how to use moladb?"));
            item3.put(KEYATTR2, new AttributeValue("2015/01/02"));
            item3.put("desc", new AttributeValue().withS("it is an artical about how to use moladb in bce"));
            PutItemRequest req3 = new PutItemRequest(tableName);
            req3.withItem(item3);
            PutItemResponse result3 = molaDbClient.putItem(req3);

            System.out.println("result3:" + result3.toString());
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getItem() {
        try {
            System.out.println("=====================Start.=======================");
            String tableName = TABLE1;
            Key keyToGet = new Key();
            keyToGet.withAttribute(KEYATTR1, new AttributeValue().withS("how to use moladb?"));
            keyToGet.withAttribute(KEYATTR2, new AttributeValue().withS("2015/01/02"));
            List<String> attributesToGet = new ArrayList<String>();
            attributesToGet.add("desc");
            attributesToGet.add("body");
            GetItemRequest request = new GetItemRequest(tableName);
            request.withKey(keyToGet).withConsistentRead(false);
            request.withAttributesToGet(attributesToGet);

            GetItemResponse result = molaDbClient.getItem(request);
            System.out.println("result:" + result.toString());
            System.out.println("=====================End.=======================");
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateItem() {
        try {
            System.out.println("=====================Start.=======================");
            String tableName = TABLE1;

            Map<String, AttributeValueUpdate> updateItems = new HashMap<String, AttributeValueUpdate>();
            Key keyToUpdate = new Key();
            keyToUpdate.withAttribute(KEYATTR1, new AttributeValue().withS("how to use moladb?"));
            keyToUpdate.withAttribute(KEYATTR2, new AttributeValue().withS("2015/01/02"));

            updateItems.put("tags", new AttributeValueUpdate()
                                        .withAction(AttributeValueUpdate.ACTION_PUT)
                                        .withValue(new AttributeValue().withS("c++ java php")));
            updateItems.put("desc", new AttributeValueUpdate()
                                        .withAction(AttributeValueUpdate.ACTION_DELETE));
            UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName(tableName)
                                                        .withKey(keyToUpdate)
                                                        .withAttributeUpdates(updateItems);
            UpdateItemResponse result = molaDbClient.updateItem(updateItemRequest);
            System.out.println("result:" + result.toString());
            System.out.println("=====================End.=======================");
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void deleteItem() {
        try {
            System.out.println("=====================Start.=======================");
            String tableName = TABLE1;
            Key keyToDel = new Key();

            keyToDel.withAttribute(KEYATTR1, new AttributeValue().withS("how to use moladb?"));
            keyToDel.withAttribute(KEYATTR2, new AttributeValue().withS("2015/01/02"));
            DeleteItemRequest request = new DeleteItemRequest()
                                                .withTableName(tableName)
                                                .withKey(keyToDel);

            DeleteItemResponse result = molaDbClient.deleteItem(request);
            System.out.println("result:" + result.toString());
            System.out.println("=====================End.=======================");
        } catch (BceServiceException mse) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + mse.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + mse.getStatusCode());
            System.out.println("Moladb Error Code:   " + mse.getErrorCode());
            System.out.println("Error Type:       " + mse.getErrorType());
            System.out.println("Request ID:       " + mse.getRequestId());
            mse.printStackTrace();
        } catch (BceClientException mce) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + mce.getMessage());
            mce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void batchWriteItem() {
        try {
            System.out.println("=====================Start.=======================");
            String tableName1 = TABLE1;
            String tableName2 = TABLE2;


            // Create a map for the requests in the batch
            Map<String, List<WriteRequest>> requestItems = new HashMap<String, List<WriteRequest>>();

            // Create a PutRequest for a new Forum item
            Map<String, AttributeValue> item1 = new HashMap<String, AttributeValue>();
            item1.put("body", new AttributeValue().withB("bce is provided by baidu".getBytes()));
            item1.put("auther", new AttributeValue().withS("moladb"));
            item1.put(KEYATTR1, new AttributeValue().withS("how to use bce"));
            item1.put(KEYATTR2, new AttributeValue().withS("2015/04/01"));

            List<WriteRequest> actionOnTable1 = new ArrayList<WriteRequest>();
            actionOnTable1.add(new PutRequest().withItem(item1));
            requestItems.put(tableName1, actionOnTable1);

            // Create a PutRequest for a new Thread item
            Map<String, AttributeValue> item2 = new HashMap<String, AttributeValue>();
            item2.put("body", new AttributeValue().withB("bcc is very fast".getBytes()));
            item2.put("auther", new AttributeValue().withS("baidubce"));
            item2.put(KEYATTR1, new AttributeValue().withS("how to use bcc?"));
            item2.put(KEYATTR2, new AttributeValue().withS("2015/03/09"));

            actionOnTable1.add(new PutRequest().withItem(item2));

            // operation on TABLE2
            List<WriteRequest> actionOnTable2 = new ArrayList<WriteRequest>();
            Key delKey1 = new Key();
            delKey1.withAttribute("city", new AttributeValue().withS("shanghai"));
            delKey1.withAttribute("autherName", new AttributeValue().withS("moladb"));

            Map<String, AttributeValue> item3 = new HashMap<String, AttributeValue>();
            item3.put("age", new AttributeValue().withN("100"));
            item3.put("city", new AttributeValue().withS("nanjing"));
            item3.put("autherName", new AttributeValue().withS("moladb"));

            actionOnTable2.add(new DeleteRequest().withKey(delKey1));
            actionOnTable2.add(new PutRequest().withItem(item3));
            
            requestItems.put(tableName2, actionOnTable2);

            BatchWriteItemResponse result;
            BatchWriteItemRequest batchWriteItemRequest = new BatchWriteItemRequest();
            batchWriteItemRequest.withRequestItems(requestItems);
            
            System.out.println("request:" + batchWriteItemRequest.toString());
            int backoff = 1000;
            int maxRetry = 10;
            int retryCount = 0;
            result = molaDbClient.batchWriteItem(batchWriteItemRequest);
            while (result.getUnprocessedItems() != null 
                    && !result.getUnprocessedItems().isEmpty()
                    && retryCount < maxRetry) {
                backoff *= 2;
                Thread.sleep(backoff);
                batchWriteItemRequest.withRequestItems(result.getUnprocessedItems());
                result = molaDbClient.batchWriteItem(batchWriteItemRequest);
                ++retryCount;
            }
            System.out.println("=====================End.=======================");
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void batchGetItem() {
        try {
            
            System.out.println("=====================Start.=======================");
            String tableName1 = TABLE1;
            String tableName2 = TABLE2;

            HashMap<String, KeysAndAttributes> reqs = new HashMap<String, KeysAndAttributes>();

            ArrayList<Key> keys1 = new ArrayList<Key>();

            Key table1key1 = new Key();
            table1key1.withAttribute(KEYATTR1, new AttributeValue().withS("how to use moladb?"));
            table1key1.withAttribute(KEYATTR2, new AttributeValue().withS("2015/01/02"));

            keys1.add(table1key1);
            Key table1key2 = new Key();
            table1key2.withAttribute(KEYATTR1, new AttributeValue().withS("how to use bce"));
            table1key2.withAttribute(KEYATTR2, new AttributeValue().withS("2015/04/01"));
            keys1.add(table1key2);
            List<String> attrs = new ArrayList<String>();
            attrs.add("body");
            reqs.put(tableName1,
                    new KeysAndAttributes().withKeys(keys1).withAttributesToGet(attrs));

            // operations on second table
            ArrayList<Key> keys2 = new ArrayList<Key>();
            Key table2key1 = new Key();
            table2key1.withAttribute("city", new AttributeValue().withS("shanghai"));
            table2key1.withAttribute("autherName", new AttributeValue().withS("moladb"));
            keys2.add(table2key1);
            Key table2key2 = new Key();
            table2key2.withAttribute("city", new AttributeValue().withS("nanjing"));
            table2key2.withAttribute("autherName", new AttributeValue().withS("moladb"));
            keys2.add(table2key2);

            Key table2key3 = new Key();
            table2key3.withAttribute("city", new AttributeValue().withS("beijing"));
            table2key3.withAttribute("autherName", new AttributeValue().withS("bcc"));
            keys2.add(table2key3);

            reqs.put(tableName2,
                    new KeysAndAttributes().withKeys(keys2).withConsistentRead(true));

            BatchGetItemRequest batchGetItemRequest = new BatchGetItemRequest().withRequestItems(reqs);
            BatchGetItemResponse result = molaDbClient.batchGetItem(batchGetItemRequest);
            int maxRetryCount = 10;
            int retryCount = 0;
            int backoff = 1000;
            while (result.getUnprocessedItems() != null 
                    && !result.getUnprocessedItems().isEmpty()
                    && retryCount < maxRetryCount) {
                backoff *= 2;
                Thread.sleep(backoff);
                batchGetItemRequest.withRequestItems(result.getUnprocessedItems());
                result = molaDbClient.batchGetItem(batchGetItemRequest);
                ++retryCount;
            }

            System.out.println("result:" + result.toString());
            System.out.println("=====================End.=======================");
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void query() {
        try {
            String tableName = TABLE1;
            String format = "2013/%02d/%02d";
            for (int i = 0; i < 10; ++i) {
                Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
                int mounth = (i * 17) % 12 + 1;
                int day = (i * 13) % 27 + 1;
                String date = String.format(format, mounth, day);
                System.out.println("date:" + date);
                item.put(KEYATTR1, new AttributeValue("how to use moladb?"));
                item.put(KEYATTR2, new AttributeValue(date));
                item.put("body", new AttributeValue("yes"));
                System.out.println("put record:" + item.toString());

                PutItemRequest putItemRequest = new PutItemRequest(tableName).withItem(item);
                molaDbClient.putItem(putItemRequest);
            }
            System.out.println("=====================Start.=======================");
            
            // 查询的hash key的值
            AttributeValue keyAttr1 = new AttributeValue().withS("how to use moladb?");
            // 用于查询的range key的下限
            AttributeValue lowBound = new AttributeValue().withS("2013/01/01");
            // 用于查询的range key的上限
            AttributeValue upBound = new AttributeValue().withS("2015/02/03");
            
            String keyExpression = KEYATTR1 + " = " + MolaDbConstants.JSON_HASH_KEY_VALUE + " AND " 
                    + KEYATTR2 + " BETWEEN " + MolaDbConstants.JSON_LOW_BOUND 
                    + " AND " + MolaDbConstants.JSON_UP_BOUND;
            Map<String, AttributeValue> expressionAttributes = new HashMap<String, AttributeValue>();

            expressionAttributes.put(MolaDbConstants.JSON_HASH_KEY_VALUE, keyAttr1);
            expressionAttributes.put(MolaDbConstants.JSON_LOW_BOUND, lowBound);
            expressionAttributes.put(MolaDbConstants.JSON_UP_BOUND, upBound);
            
            // 创建查询请求
            QueryRequest request = new QueryRequest(tableName);

            List<String> attributesToGet = new ArrayList<String>();
            attributesToGet.add(KEYATTR1);
            attributesToGet.add(KEYATTR2);

            request.withAttributesToGet(attributesToGet)  // 指定需要返回的列名
                    .withLimit(100) // 每次最多返回100条record
                    .withKeyConditionExpression(keyExpression)
                    .withExpressionAttributeValues(expressionAttributes);

            QueryResponse result = molaDbClient.query(request);
            List<Map<String, AttributeValue>> allRecord = result.getItems();
            // lastEvaluatedKey非空，表示还有更多的记录需要再次查询
            while (result.getLastEvaluatedKey() != null) { 
                // 使用上次一返回的lastEvaluatedKey 作为下一次查询的起点
                request.withExclusiveStartKey(result.getLastEvaluatedKey());
                // 继续查询
                result = molaDbClient.query(request);
                allRecord.addAll(result.getItems());
            }
            
            System.out.println("all record:\n" + allRecord.toString());
            System.out.println("=====================End.=======================");
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createInstanceExample(String insName) {
        try {
            CreateInstanceRequest req = new CreateInstanceRequest();
            req.withInstanceName(insName).withDescription("blog data");
            molaDbClient.createInstance(req);
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void statInstanceExample(String insName) {
        try {
            GetInstanceRequest req = new GetInstanceRequest(insName);
            GetInstanceResponse getRet = molaDbClient.getInstance(req);
            System.out.println("instance:" + insName + " description:" + getRet.getDescription());
            List<String> tables = getRet.getTableNames();
            for (Iterator<String> iter = tables.iterator(); iter.hasNext(); ) {
                System.out.println("instance:" + insName + " has table:" + iter.next());
            }
            ListInstancesResponse listRet = molaDbClient.listInstances();
            List<String> names = listRet.getInstanceNames();
            for (Iterator<String> iter = names.iterator(); iter.hasNext();) {
                System.out.println("list instance, return:" + iter.next());
            }
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void deleteInstance(String insName) {
        try {
            DeleteInstanceRequest req = new DeleteInstanceRequest(insName);
            molaDbClient.deleteInstance(req);
        } catch (BceServiceException ase) {
            System.out.println("Caught an moladb, which means your request made it "
                    + "to moladb, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("Moladb Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an MoladbClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with Moladb, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("===========================================");
        System.out.println("Getting Started with Baidu Moladb");
        System.out.println("===========================================\n");

        BceCredentials credentials = null;
        try {
            credentials = new DefaultBceCredentials(ACCESS_KEY_ID,
                    SECRET_ACCESS_KEY);
        } catch (Exception e) {
            throw new BceClientException(
                    "Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "and is in valid format.", e);
        }
        String insName = "blogDatabase";
        MolaDbClientConfiguration conf = new MolaDbClientConfiguration();
        
        conf.setCredentials(credentials);
        conf.setEndpoint(ENDPOINT);

        MolaDbClient molaDbClient = new MolaDbClient(conf);
        molaDbClient.setDefaultInstanceName(insName);
        MolaDbExample example = new MolaDbExample(molaDbClient);
        example.createInstanceExample(insName);
        example.statInstanceExample(insName);
        example.createTable();

        example.updateTable();
        example.getTable();
        example.listTables();

        example.putItem();
        example.getItem();
        example.updateItem();
        example.deleteItem();
        example.batchWriteItem();
        example.batchGetItem();
        example.query();
        example.deleteTable();
        example.deleteInstance(insName);

        System.out.println("end now");
    }
}
