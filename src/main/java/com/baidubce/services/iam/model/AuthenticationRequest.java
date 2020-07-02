/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.iam.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationRequest extends BaseBceRequest {
    /**
     * auth
     */
    private AuthenticationRequestAuth auth;

    public void setAuth(AuthenticationRequestAuth auth) {
        this.auth = auth;
    }

    public AuthenticationRequestAuth getAuth() {
        return this.auth;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{"
                + "auth=" + auth + "\n"
                + "}";
    }

    public static class AuthenticationRequestAuth {
        private String authorization;
    
        private String securityToken;
    
        private SignValidateRequest request;
    
        public void setAuthorization(String authorization) {
            this.authorization = authorization;
        }
    
        public String getAuthorization() {
            return this.authorization;
        }
    
        public void setSecurityToken(String securityToken) {
            this.securityToken = securityToken;
        }
    
        public String getSecurityToken() {
            return this.securityToken;
        }
    
        public void setRequest(SignValidateRequest request) {
            this.request = request;
        }
    
        public SignValidateRequest getRequest() {
            return this.request;
        }
    
        @Override
        public String toString() {
            return "AuthenticationRequestAuth{"
                    + "authorization=" + authorization + "\n"
                    + "securityToken=" + securityToken + "\n"
                    + "request=" + request + "\n"
                    + "}";
        }
    
        public static class SignValidateRequest {
            private String method;
        
            private String uri;
        
            private SignValidateRequestParams params;
        
            private SignValidateRequestHeaders headers;
        
            public void setMethod(String method) {
                this.method = method;
            }
        
            public String getMethod() {
                return this.method;
            }
        
            public void setUri(String uri) {
                this.uri = uri;
            }
        
            public String getUri() {
                return this.uri;
            }
        
            public void setParams(SignValidateRequestParams params) {
                this.params = params;
            }
        
            public SignValidateRequestParams getParams() {
                return this.params;
            }
        
            public void setHeaders(SignValidateRequestHeaders headers) {
                this.headers = headers;
            }
        
            public SignValidateRequestHeaders getHeaders() {
                return this.headers;
            }
        
            @Override
            public String toString() {
                return "SignValidateRequest{"
                        + "method=" + method + "\n"
                        + "uri=" + uri + "\n"
                        + "params=" + params + "\n"
                        + "headers=" + headers + "\n"
                        + "}";
            }
        
            public static class SignValidateRequestParams {
                @Override
                public String toString() {
                    return "SignValidateRequestParams{"
                            + "}";
                }
            }        
        
            public static class SignValidateRequestHeaders {
                @Override
                public String toString() {
                    return "SignValidateRequestHeaders{"
                            + "}";
                }
            }        
        }    
    }

}