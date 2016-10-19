/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bos.model;

public class CopyObjectResponseWithExceptionInfo extends CopyObjectResponse {
    /**
     * The error code for copied object.
     */
    private String code;

    /**
     * The error message for copied object.
     */
    private String message;

    /**
     * The requestId for copied object.
     */
    private String requestId;

    /**
     * Gets the detail error message.
     * 
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     * 
     * @param message the detail error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Returns the error code which represents the error type.
     * 
     * @return the bos error code which represents the error type.
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Sets the error code which represents the error type;
     * 
     * @param code the bos error code which represents the error type.
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Returns the requestID.
     * 
     * @return the bos requestID.
     */
    public String getRequestId() {
        return requestId;
    }
    
    /**
     * Sets the requestID.
     * 
     * @param requestId
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

}
