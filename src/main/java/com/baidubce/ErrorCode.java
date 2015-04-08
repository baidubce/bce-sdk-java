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
 * The BCE error code.
 */
public enum ErrorCode {
    ACCESS_DENIED("AccessDenied"),
    INAPPROPRIATE_JSON("InappropriateJSON"),
    INTERNAL_ERROR("InternalError"),
    INVALID_ACCESS_KEY_ID("InvalidAccessKeyId"),
    INVALID_HTTP_AUTH_HEADER("InvalidHTTPAuthHeader"),
    INVALID_HTTP_REQUEST("InvalidHTTPRequest"),
    INVALID_URI("InvalidURI"),
    MALFORMED_JSON("MalformedJSON"),
    INVALID_VERSION("InvalidVersion"),
    OPT_IN_REQUIRED("OptInRequired"),
    PRECONDITION_FAILED("PreconditionFailed"),
    REQUEST_EXPIRED("RequestExpired"),
    SIGNATURE_DOES_NOT_MATCH("SignatureDoesNotMatch");

    private String code;

    private ErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }

    public boolean equals(String code) {
        return this.code.equals(code);
    }
}
