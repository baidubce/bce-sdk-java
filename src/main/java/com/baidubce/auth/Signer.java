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
package com.baidubce.auth;

import com.baidubce.internal.InternalRequest;

/**
 * A strategy for applying cryptographic signatures to a request, proving that the request was made by someone in
 * possession of the given set of credentials without transmitting the secret key over the wire.
 */
public interface Signer {
    /**
     * Equivalent to sign(request, credentials, SignOptions.DEFAULT).
     *
     * @param request     the request to sign.
     * @param credentials the credentials to sign the request with.
     * @throws NullPointerException if any parameter is null.
     */
    public void sign(InternalRequest request, BceCredentials credentials);

    /**
     * Sign the given request with the given set of credentials. Modifies the passed-in request to apply the signature.
     *
     * @param request     the request to sign.
     * @param credentials the credentials to sign the request with.
     * @param options     the options for signing.
     * @throws NullPointerException if any parameter is null.
     */
    public void sign(InternalRequest request, BceCredentials credentials, SignOptions options);
}
