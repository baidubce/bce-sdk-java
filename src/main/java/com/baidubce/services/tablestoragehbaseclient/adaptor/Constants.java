/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.baidubce.services.tablestoragehbaseclient.adaptor;

/**
 * TableStorageConstants used by the Baidu TableStorage HBase client.
 */
public class Constants {

    /**
     * Default family in TableStorage.
     */
    public static final String DEFAULT_FAMILY = "cf0";

    /**
     * The error code from TableStorage when table not exist.
     */
    public static final String TABLE_NOT_EXIST_CODE = "TableNotExist";

    /**
     * The error code from TableStorage when table not exist.
     */
    public static final String INVALID_TABLE_VERSION_CODE = "InvalidTableVersion";

    /**
     * Wait time after create table. Default 1 min.
     */
    public static final int CREATE_TABLE_WAIT_TIME = 60 * 1000;

    /**
     * No Row type in batch.
     */
    public static final int NON_TYPE = 0;

    /**
     * Row PUT type in batch.
     */
    public static final int PUT_TYPE = 1;

    /**
     * Row DELETE type in batch.
     */
    public static final int DELETE_TYPE = 2;

    /**
     * Row GET type in batch.
     */
    public static final int GET_TYPE = 3;

    /**
     * Maximum column family in TableStorage.
     */
    public static final int MAX_COLUMN_FAMILY_NUM = 1;

    /**
     * Default write buffer size for TableStorageBufferedMutator.
     */
    public static final int DEFAULT_WIRTE_BUFFER_SIZE = 2 * 1024 * 1024;

    /**
     * Default server port in TablestorageRegionLocator.
     */
    public static final int DEFAULT_SERVER_PORT = 0;

    /**
     * Default server start code in TablestorageRegionLocator.
     */
    public static final int DEFAULT_SERVER_START_CODE = 0;

    /**
     * Error message for wrong column family.
     */
    public static final String WRONG_COLUMN_FAMILY_SIZE_MSG =
            "Table only support one Family named " + Constants.DEFAULT_FAMILY;
}
