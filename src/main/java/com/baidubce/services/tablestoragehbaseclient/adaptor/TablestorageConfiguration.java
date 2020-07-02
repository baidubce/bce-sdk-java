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
 * Provides interfaces for setting and getting Tablestorage configuration parameters.
 */
public class TablestorageConfiguration {
    public static final String TABLESTORAGE_CLIENT_ENDPOINT = "tablestorage.client.endpoint";
    public static final String TABLESTORAGE_CLIENT_ACCESSKEYID = "tablestorage.client.accesskeyid";
    public static final String TABLESTORAGE_CLIENT_SECRETACCESSKEY = "tablestorage.client.secretaccesskey";
    public static final String TABLESTORAGE_CLIENT_INSTANCENAME = "tablestorage.client.instancename";

    private String endpoint;
    private String accessKeyId;
    private String secretAccessKey;
    private String instanceName;

    public TablestorageConfiguration(String endpoint, String instanceName, String accessKeyId, String secretAccessKey) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.instanceName = instanceName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public String getInstanceName() {
        return instanceName;
    }
}
