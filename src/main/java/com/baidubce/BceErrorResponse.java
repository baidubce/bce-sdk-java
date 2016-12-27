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
package com.baidubce;

public class BceErrorResponse {

    private String requestId;

    /**
     * The BCE error code which represents the error type.
     */
    private String code;

    /**
     * The detail error message.
     */
    private String message;

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Returns the BCE error code which represents the error type.
     *
     * @return the BCE error code which represents the error type.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the BCE error code which represents the error type.
     *
     * @param code the BCE error code which represents the error type.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the detail error message.
     *
     * @return the detail error message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the detail error message.
     *
     * @param message the detail error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
