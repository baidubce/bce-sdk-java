/*
 * Copyright 2018 Baidu, Inc.
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

package com.baidubce.services.kms.model;

/**
 * Contains constants of kms source code
 */
public class Constants {


    public static final String ACTION = "action";

    public static final String FIELD_DESCRIPTION = "description";

    public static final String FIELD_KEYUSAGE = "keyUsage";

    public static final String FIELD_KEYID = "keyId";

    public static final String FIELD_PENDINGWINDOWINDAYS = "pendingWindowInDays";
    
    public static final String FIELD_KEYSPEC = "keySpec";

    public static final String FIELD_NUMBEROFBYTES = "numberOfBytes";

    public static final String FIELD_CIPHERTEXT = "ciphertext";

    public static final String FIELD_PLAINTEXT = "plaintext";

    public static final String FIELD_LIMIT = "limit";

    public static final String FIELD_MARKER = "marker";

    public static final String REQUEST_SHOULD_NOT_BE_NULL = "request should not be null";

    public static final String FAIL_TO_GENERATE_JSON = "Fail to generate json";

    public static final String APPLICATION_JSON = "application/json";

    public static final String FAIL_TO_GET_UTF8_BYTES = "Fail to get UTF-8 bytes";

    public enum KeySpec {

        AES_128("AES_128"),

        AES_256("AES_256");

        private final String specific;

        private KeySpec(String specific) {
            this.specific = specific;
        }

        @Override 
        public String toString() {
            return this.specific;
        }
    }
}
