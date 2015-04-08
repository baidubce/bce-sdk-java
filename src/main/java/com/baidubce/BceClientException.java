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
 * Base exception class for any errors that occur on the client side when attempting to access a BCE service API.
 *
 * <p>
 * For example, there is no network connection available or the network request is timeout, or the server returns an
 * invalid response that the client is unable to parse, etc
 *
 * <p>
 * Error responses from services will be handled as BceServiceExceptions.
 *
 * @see BceServiceException
 */
public class BceClientException extends RuntimeException {
    private static final long serialVersionUID = -9085416005820812953L;

    /**
     * Constructs a new BceClientException with the specified detail message.
     *
     * @param message the detail error message.
     */
    public BceClientException(String message) {
        super(message);
    }

    /**
     * Constructs a new BceClientException with the specified detail message and the underlying cause.
     *
     * @param message the detail error message.
     * @param cause   the underlying cause of this exception.
     */
    public BceClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
