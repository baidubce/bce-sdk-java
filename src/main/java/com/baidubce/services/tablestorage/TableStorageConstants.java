/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.tablestorage;

/**
 * TableStorageConstants used by the Baidu TableStorage Java client.
 */
public class TableStorageConstants {

    /**
     * Default encoding used for text data
     */
    public static String DEFAULT_ENCODING = "UTF-8";

    /**
     * Content type json in HTTP header
     */
    public static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * Max rowkey in TableStorage
     */
    public static final String MAX_ROWKEY = new String(
            new byte[]{
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
            });

    /**
     * TableVersion's value. Used in createTable operation.
     */
    public static final int CREATE_TABLE_VERSION = 0;

    /**
     * TimeToLive's value. Indicates that the data is always valid until it is deleted.
     */
    public static final int FORERVER_LIVE_TIME = 0;

    /**
     * TimeToLive's value. In CreateTable operations, it is equal to FORERVER_LIVE_TIME.
     * In UpdateTable operations, it means don't update TimeToLive's value.
     */
    public static final int DEFAULT_LIVE_TIME = -1;

    /**
     * Default MaxVersions. Used in TableOption.
     */
    public static final int DEFAULT_TABLE_MAX_VERSIONS = 0;

    /**
     * Minimum value of MaxVersions. Used in TableOption.
     */
    public static final int MIN_TABLE_MAX_VERSIONS = 0;

    /**
     * Default MaxVersions. Used in get, batchGet and scan operations.
     */
    public static final int DEFAULT_MAX_VERSIONS = 1;

    /**
     * Empty Json String.
     */
    public static final String EMPTY_JSON_STR = "";

    /**
     * Default startRowkey in ScanRequest.
     */
    public static final String DEFAULT_START_ROWKEY = "";

    /**
     * Default stopRowkey in ScanRequest.
     */
    public static final String DEFAULT_STOP_ROWKEY = MAX_ROWKEY;

    /**
     * The bts service identification in HTTP header.
     */
    public static final String X_BCE_BTS_METHOD_KEY            = "x-bce-bts-method";

    /**
     * Get Method in HTTP header
     */
    public static final String X_BCE_BTS_METHOD_GET            = "GET";

    /**
     * Put Method in HTTP header
     */
    public static final String X_BCE_BTS_METHOD_PUT            = "PUT";

    /**
     * Delete Method in HTTP header
     */
    public static final String X_BCE_BTS_METHOD_DELETE         = "DELETE";

    /**
     * URL prefix of bts service
     */
    public static final String URL_PREFIX                      = "v1";

    /**
     * The instance resource identification in url
     */
    public static final String URI_INSTANCE                    = "instance";

    /**
     * The tables resource identification in url
     */
    public static final String URI_TABLES                      = "tables";

    /**
     * The table resource identification in url
     */
    public static final String URI_TABLE                       = "table";

    /**
     * The row resource identification in url
     */
    public static final String URI_ROW                         = "row";

    /**
     * The rows resource identification in url
     */
    public static final String URI_ROWS                        = "rows";

    /**
     * The slices resource identification in url
     */
    protected static final String URI_SLICES                   = "slices";

    /**
     * Only get table state in ShowTable operation
     */
    protected static final String ONLY_STATE                   = "onlyState";

    /**
     * The InstanceName, TableName and ColumnName should satisfy the regular expression
     */
    public static final String NAME_PATTERN_STR = "[_a-zA-Z][_a-zA-Z0-9]{0,254}";

    /**
     * The InstanceName should not contain these words
     */
    public static final String[] INSTANCE_NAME_NOT_CONTAIN_WORDS = {"baidu", "bidu", "bce", "tablestorage"};

    /**
     * The InstanceName should not start with these words
     */
    public static final String[] INSTANCE_NAME_NOT_START_WITH_WORDS = {"__"};

    /**
     * Max row num in BatchPutRow and BatchDeleteRow
     */
    public static final int MAX_WRITE_ROW_NUM                  = 200;

    /**
     * Max row num in BatchGetRow
     */
    public static final int MAX_READ_ROW_NUM                   = 100;

    /**
     * Max rowkey size
     */
    public static final int MAX_ROWKEY_SIZE                    = 4096;

    /**
     * Max row size. Row size is equal to the sum of cell sizes
     */
    public static final int MAX_ROW_SIZE                       = 8 * 1024 * 1024;

    /**
     * Max cell num in PutRow, DeleteRow, BatchPutRow and BatchDeleteRow
     */
    public static final int MAX_WRITE_CELL_NUM                 = 1024;

    /**
     * Max cell num in GetRow, BatchGetRow and Scan
     */
    public static final int MAX_READ_CELL_NUM                  = 128;

    /**
     * Max cell value size
     */
    public static final int MAX_CELL_VALUE_SIZE                = 2 * 1024 * 1024;

    /**
     * Max body size in request
     */
    public static final int MAX_REQUEST_BODY_SIZE              = 10 * 1024 * 1024;


    /**
     * The instance key in json response
     */
    public static final String JSON_INSTANCE                   = "instance";

    /**
     * The tables key in json response
     */
    public static final String JSON_TABLES                     = "tables";

    /**
     * The table name key in json response
     */
    public static final String JSON_TABLE_NAME                 = "tableName";

    /**
     * The table state key in json response
     */
    public static final String JSON_TABLE_STATE                = "tableState";

    /**
     * The table version key in json response
     */
    public static final String JSON_TABLE_VERSION              = "tableVersion";

    /**
     * The compress type key in json response
     */
    public static final String JSON_COMPRESS_TYPE              = "compressType";

    /**
     * The ttl key in json response
     */
    public static final String JSON_TTL                        = "ttl";

    /**
     * The max version key in json response
     */
    public static final String JSON_MAX_VERSION                = "maxVersion";

    /**
     * The create time key in json response
     */
    public static final String JSON_CREATE_TIME                = "createTime";

    /**
     * The next_start_key key in json response
     */
    public static final String JSON_NEXT_START_KEY             = "nextStartKey";

    /**
     * The result key in json response
     */
    public static final String JSON_RESULT                     = "result";

    /**
     * The rowkey key in json response
     */
    public static final String JSON_ROWKEY                     = "rowkey";

    /**
     * The cells key in json response
     */
    public static final String JSON_CELLS                      = "cells";

    /**
     * The column key in json response
     */
    public static final String JSON_COLUMN                     = "column";

    /**
     * The value key in json response
     */
    public static final String JSON_VALUE                      = "value";

    /**
     * The timestamp key in json response
     */
    public static final String JSON_TIMESTAMP                  = "timestamp";

    /**
     * The keyRanges key in json response
     */
    public static final String JSON_KEY_RANGES                 = "keyRanges";

    /**
     * The start key ranges key in json response
     */
    public static final String JSON_START_KEY                  = "startKey";

    /**
     * The stop key ranges key in json response
     */
    public static final String JSON_END_KEY                    = "endKey";
}
