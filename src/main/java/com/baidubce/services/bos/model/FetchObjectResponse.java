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
package com.baidubce.services.bos.model;

/**
 * Contains the data returned by Baidu Bos from the
 * {@link com.baidubce.services.bos.BosClient#fetchObject(FetchObjectRequest fetchObjectRequest)} call.
 * This result may be ignored if not needed; otherwise, use this result
 * to access information about the new object created from the fetchObject call.
 */
public class FetchObjectResponse extends BosResponse {

    /**
     * The code of this fetching that was created in the associated FetchObjectRequest.
     */
    private String code;

    /**
     * The message of this fetching that was created in the associated FetchObjectRequest.
     */
    private String message;

    /**
     * The requestId of this fetching that was created in the associated FetchObjectRequest.
     */
    private String requestId;

    /**
     * The jobId of this fetching that was created in the associated FetchObjectRequest.
     */
    private String jobId;

    /**
     * Gets the code of this fetching that was created in the associated FetchObjectRequest.
     *
     * @return The code of the fetch object response.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of this fetching that was created in the associated FetchObjectRequest.
     *
     * @param code The code of this fetching that was created in the associated FetchObjectRequest.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the message of this fetching that was created in the associated FetchObjectRequest.
     *
     * @return The message of the fetch object response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of this fetching that was created in the associated FetchObjectRequest.
     *
     * @param message The message of this fetching that was created in the associated FetchObjectRequest.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the requestId of this fetching that was created in the associated FetchObjectRequest.
     *
     * @return The requestId of the fetch object response.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the requestId of this fetching that was created in the associated FetchObjectRequest.
     *
     * @param requestId The requestId of this fetching that was created in the associated FetchObjectRequest.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Gets the jobId of this fetching that was created in the associated FetchObjectRequest.
     *
     * @return The jobId of the fetch object response.
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * Sets the jobId of this fetching that was created in the associated FetchObjectRequest.
     *
     * @param jobId The jobId of this fetching that was created in the associated FetchObjectRequest.
     */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
