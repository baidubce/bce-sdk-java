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
package com.baidubce;

/**
 * Extension of BceClientException that represents an error response returned by a BCE service.
 * Receiving an exception of this type indicates that the caller's request was correctly transmitted to the service,
 * but for some reason, the service was not able to process it, and returned an error response instead.
 *
 * <p>
 * BceServiceException provides callers several pieces of information that can be used to obtain more information
 * about the error and why it occurred. In particular, the errorType field can be used to determine if the caller's
 * request was invalid, or the service encountered an error on the server side while processing it.
 */
public class BceServiceException extends BceClientException {
    private static final long serialVersionUID = 1483785729559154396L;

    /**
     * Indicates who is responsible (if known) for a failed request.
     */
    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    /**
     * The unique BCE identifier for the service request the caller made. The BCE request ID can uniquely identify
     * the BCE request, and is used for reporting an error to BCE support team.
     */
    private String requestId;

    /**
     * The BCE error code represented by this exception.
     */
    private String errorCode;

    /**
     * Indicates (if known) whether this exception was the fault of the caller or the service.
     */
    private ErrorType errorType = ErrorType.Unknown;

    /**
     * The error message as returned by the service.
     */
    private String errorMessage;

    /**
     * The HTTP status code that was returned with this error.
     */
    private int statusCode;

    /**
     * Constructs a new BceServiceException with the specified message.
     *
     * @param errorMessage An error message describing what went wrong.
     */
    public BceServiceException(String errorMessage) {
        super(null);
        this.errorMessage = errorMessage;
    }

    /**
     * Constructs a new BceServiceException with the specified message and exception indicating the root cause.
     *
     * @param errorMessage An error message describing what went wrong.
     * @param cause The root exception that caused this exception to be thrown.
     */
    public BceServiceException(String errorMessage, Exception cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
    }

    /**
     * Sets the BCE requestId for this exception.
     *
     * @param requestId The unique identifier for the service request the caller made.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Returns the BCE request ID that uniquely identifies the service request the caller made.
     *
     * @return The BCE request ID that uniquely identifies the service request the caller made.
     */
    public String getRequestId() {
        return this.requestId;
    }

    /**
     * Sets the BCE error code represented by this exception.
     *
     * @param errorCode The BCE error code represented by this exception.
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Returns the BCE error code represented by this exception.
     *
     * @return The BCE error code represented by this exception.
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * Sets the type of error represented by this exception (sender, receiver, or unknown),
     * indicating if this exception was the caller's fault, or the service's fault.
     *
     * @param errorType The type of error represented by this exception (sender or receiver),
     *     indicating if this exception was the caller's fault or the service's fault.
     */
    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    /**
     * Indicates who is responsible for this exception (caller, service, or unknown).
     *
     * @return A value indicating who is responsible for this exception (caller, service, or unknown).
     */
    public ErrorType getErrorType() {
        return this.errorType;
    }

    /**
     * Sets the human-readable error message provided by the service.
     *
     * @param errorMessage the human-readable error message provided by the service.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the human-readable error message provided by the service.
     *
     * @return the human-readable error message provided by the service.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Sets the HTTP status code that was returned with this service exception.
     *
     * @param statusCode The HTTP status code that was returned with this service exception.
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Returns the HTTP status code that was returned with this service exception.
     *
     * @return The HTTP status code that was returned with this service exception.
     */
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public String getMessage() {
        return this.getErrorMessage() + " (Status Code: " + this.getStatusCode() + "; Error Code: "
                + this.getErrorCode() + "; Request ID: " + this.getRequestId() + ")";
    }
}
