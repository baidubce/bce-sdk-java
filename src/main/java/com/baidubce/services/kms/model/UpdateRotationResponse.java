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
 * Provides options for enableKey master key.
 */
public class UpdateRotationResponse extends KmsResponse {

    private int success;

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getSuccess() {
        return this.success;
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120
