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

    public static final String FIELD_PROTECTEDBY = "protectedBy";

    public static final String FIELD_ORIGIN = "origin";

    public static final String FIELD_NUMBEROFBYTES = "numberOfBytes";

    public static final String FIELD_CIPHERTEXT = "ciphertext";

    public static final String FIELD_PLAINTEXT = "plaintext";

    public static final String FIELD_LIMIT = "limit";

    public static final String FIELD_MARKER = "marker";

    public static final String FIELD_WRAPPINGALGORITHM = "wrappingAlgorithm";

    public static final String FIELD_WRAPPINGKEYSPEC = "wrappingKeySpec";

    public static final String FIELD_PUBLICKEYENCODING = "publicKeyEncoding";

    public static final String FIELD_IMPORTTOKEN = "importToken";

    public static final String FIELD_ENCRYPTEDKEY = "encryptedKey";

    public static final String FIELD_ASYMMETRICKEYSPEC = "asymmetricKeySpec";

    public static final String FIELD_ASYMMETRICKEYUSAGE = "asymmetricKeyUsage";

    public static final String FIELD_ENCRYPTEDRSAKEY = "encryptedRsaKey";

    public static final String FIELD_ENCRYPTEDSM2KEY = "encryptedSm2Key";

    public static final String FIELD_ENCRYPTEDKEYENCRYPTIONKEY = "encryptedKeyEncryptionKey";

    public static final String REQUEST_SHOULD_NOT_BE_NULL = "request should not be null";

    public static final String FAIL_TO_GENERATE_JSON = "Fail to generate json";

    public static final String APPLICATION_JSON = "application/json";

    public static final String FAIL_TO_GET_UTF8_BYTES = "Fail to get UTF-8 bytes";

    public static final String FAIL_TO_SUPPORT = "Sorry, don't support";


    public enum KeySpec {
        BAIDU_ASE_256("BAIDU_ASE_256"),

        AES_128("AES_128"),

        AES_256("AES_256"),

        RSA_1024("RSA_1024"),

        RSA_2048("RSA_2048"),

        RSA_4096("RSA_4096");

        private final String specific;

        private KeySpec(String specific) {
            this.specific = specific;
        }

        @Override 
        public String toString() {
            return this.specific;
        }
    }

    public enum Origin {
        BAIDU_KMS("BAIDU_KMS"),

        EXTERNAL("EXTERNAL");

        private final String specific;

        private Origin(String specific) {
            this.specific = specific;
        }

        @Override
        public String toString() {
            return this.specific;
        }
    }

    public enum ProtectedBy {
        HSM("HSM"),

        SOFTWARE("SOFTWARE");

        private final String specific;

        private ProtectedBy(String specific) {
            this.specific = specific;
        }

        @Override
        public String toString() {
            return this.specific;
        }
    }

    public enum PublicKeyEncoding {
        RAW_HEX("RAW_HEX"),

        BASE64("BASE64"),

        PEM("PEM");

        private final String specific;

        private PublicKeyEncoding(String specific) {
            this.specific = specific;
        }

        @Override
        public String toString() {
            return this.specific;
        }
    }
}
