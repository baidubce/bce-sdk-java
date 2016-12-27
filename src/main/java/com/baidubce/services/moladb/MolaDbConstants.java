/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.moladb;

/**
 * MolaDbConstants used by the Baidu Moladb Java client.
 */
public class MolaDbConstants {

    /** Default hostname for the Moladb service endpoint */
    public static String MOLADB_DEFAULT_HOSTNAME = "moladb.bj.baidubce.com";

    /** Default encoding used for text data */
    public static String DEFAULT_ENCODING = "UTF-8";
    public static final String CONTENT_TYPE_JSON = "application/json";

    public static final String JSON_ATTRIBUTE_DEFINITIONS      = "attributeDefinitions";
    public static final String JSON_ATTRIBUTE_NAME             = "attributeName";
    public static final String JSON_ATTRIBUTE_TYPE             = "attributeType";
    public static final String JSON_CREATE_DATE_TIME           = "creationDateTime";
    public static final String JSON_HASH_KEY                   = "Hash";
    public static final String JSON_ITEM_COUNT                 = "itemCount";
    public static final String JSON_KEY_SCHEMA                 = "keySchema";
    public static final String JSON_KEY_TYPE                   = "keyType";
    public static final String JSON_LAST_DECREASE_TIME         = "lastDecreaseDateTime";
    public static final String JSON_LAST_EVALUATED_TABLE_NAME  = "lastEvaluatedTableName";
    public static final String JSON_LAST_INCREASE_TIME         = "lastIncreaseDateTime";
    public static final String JSON_LIST_TABLES_LIMT           = "limit";
    public static final String JSON_NUM_DECREASE_TODAY         = "numberOfDecreasesToday";
    public static final String JSON_PROVISION_THROUGHPUT       = "provisionedThroughput";
    public static final String JSON_RANGE_KEY                  = "Range";
    public static final String JSON_READ_CAPACITY_UNITS        = "readCapacityUnits";
    public static final String JSON_START_TABLE_NAME           = "exclusiveStartTableName";
    public static final String JSON_STORAGE_CAPCITY_IN_B       = "reservedStorageCapacityInBytes";
    public static final String JSON_TABLE                      = "table";
    public static final String JSON_TABLENAMES                 = "tableNames";
    public static final String JSON_TABLES                     = "tables";
    public static final String JSON_TABLE_SIZE_IN_B            = "tableSizeInBytes";
    public static final String JSON_TABLE_STATUS            = "tableStatus";
    public static final String JSON_ACCOUNT                 = "account";
    public static final String JSON_WRITE_CAPACITY_UNITS    = "writeCapacityUnits";
    public static final String JSON_ACTION                  = "action";
    public static final String JSON_ATTRIBUTES              = "attributes";
    public static final String JSON_ATTRIBUTES_TO_GET       = "attributesToGet";
    public static final String JSON_ATTRIBUTE_LIST          = "attributeList";
    public static final String JSON_ATTRIBUTE_UPDATES       = "attributeUpdates";
    public static final String JSON_B                       = "B";
    public static final String JSON_CONSISTENT_READ         = "consistentRead";
    public static final String JSON_DELETE                  = "Delete";
    public static final String JSON_DELETE_REQUEST          = "deleteRequest";
    public static final String JSON_ERROR_MSG               = "errorMsg";
    public static final String JSON_ERROR_NO                = "errorNo";
    public static final String JSON_EXIST                   = "exist";
    public static final String JSON_EXPECT                  = "expect";
    public static final String JSON_FALSE                   = "false";
    public static final String JSON_ITEM                    = "item";
    public static final String JSON_ITEMS                   = "items";
    public static final String JSON_KEY                     = "key";
    public static final String JSON_KEYS                    = "keys";
    public static final String JSON_N                       = "N";
    public static final String JSON_NAME                    = "name";
    public static final String JSON_PUT                     = "Put";
    public static final String JSON_PUT_REQUEST             = "putRequest";
    public static final String JSON_REQUEST_ITEMS           = "requestItems";
    public static final String JSON_RESPONSES               = "responses";
    public static final String JSON_S                       = "S";
    public static final String JSON_TABLENAME               = "tableName";
    public static final String JSON_TRUE                    = "true";
    public static final String JSON_UNPROCESSED_ITEMS       = "unprocessedItems";
    public static final String JSON_VALUE                   = "value";
    public static final String URI_CONSISTENT_READ          = "consistentRead";
    public static final String URI_ATTRIBUTES_TO_GET        = "attributesToGet";
    public static final String URI_INSTANCE                 = "instance";
    public static final String URI_TABLE                    = "table";
    public static final String URI_BATCH_GET                = "batchGet";
    public static final String URI_BATCH_WRITE              = "batchWrite";
    public static final String URI_ITEM                     = "item";
    public static final String URI_QUERY                    = "query";

    public static final String JSON_EXCLUSIVE_START_KEY = "exclusiveStartKey";
    public static final String JSON_LIMIT = "limit";
    public static final String JSON_KEY_CONDITION_EXPRESSION = "keyConditionExpression";
    public static final String JSON_ORDER = "order";
    public static final String JSON_ASC = "Asc";
    public static final String JSON_DESC = "Desc";

    public static final String JSON_HASH_KEY_VALUE = ":HashKeyValue";
    public static final String JSON_LOW_BOUND = ":LowBound";
    public static final String JSON_UP_BOUND = ":UpBound";
    public static final String JSON_EXPRESSION_ATTRIBUTEVALUES = "expressionAttributeValues";
    public static final String JSON_LAST_EVALUATED_KEY = "lastEvaluatedKey";

    public static final String JSON_START_INSTANCE_NAME = "exclusiveStartInstanceName";
    public static final String JSON_INSTANCE_NAMES = "instanceNames";
    public static final String JSON_LAST_EVALUATED_INSTANCE_NAME = "lastEvaluatedInstanceName";
    public static final String JSON_DESCRIPTION = "desc";
    
    public static final int DEFAULT_LIST_TABLES_LIMIT = 10;
    public static final int DEFAULT_LIST_INSTANCE_LIMIT = 10;
}