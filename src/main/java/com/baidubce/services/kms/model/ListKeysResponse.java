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

import java.util.List;

/**
 * Contains the details returned from Kms after calling the ListKeys operation.
 */
public class ListKeysResponse extends KmsResponse {
    
    private String nextMarker;

    private boolean truncated;

    private List<Key> keys;

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public boolean getTruncated() {
        return truncated;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public static class Key {
        /**
         *  keyId for ListKeys
         */
        private String keyId;

        public Key() {
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getKeyId() {
            return keyId;
        }
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120
