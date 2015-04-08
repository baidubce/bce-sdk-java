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
package com.baidubce.auth;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;
import java.util.Set;

/**
 * Options for signing the request.
 *
 * <p>
 * There are 3 options available:
 * <table>
 * <tr>
 * <th>Option</th>
 * <th>Description</th>
 * </tr>
 * <tr>
 * <td>headersToSign</td>
 * <td>The set of headers to be signed. If this option is not set or set to null, only the following headers are signed
 * <ul>
 * <li>Host</li>
 * <li>Content-Length</li>
 * <li>Content-Type</li>
 * <li>Content-MD5</li>
 * <li>All headers starts with "x-bce-"</li>
 * </ul>
 * </td>
 * </tr>
 * <tr>
 * <td>timestamp</td>
 * <td>The time when the signature was created. If this option is not set or set to null, the signer will use the time
 * when the sign method is invoked.</td>
 * </tr>
 * <tr>
 * <td>expirationInSeconds</td>
 * <td>The time until the signature will expire, which starts from the timestamp. By default, it is set to 1800 (half an
 * hour). The expiration <i>MUST NOT</i> be less than 300 (5 minutes), or greater than 129600 (1.5 days).</td>
 * </tr>
 * *
 * </table>
 */
public class SignOptions {
    /**
     * The default sign options, which is {headersToSign:null, timestamp:null, expirationInSeconds:1800}.
     */
    public static final SignOptions DEFAULT = new SignOptions();

    public static final int DEFAULT_EXPIRATION_IN_SECONDS = 1800;

    public static final int DEFAULT_MIN_EXPIRATION_IN_SECONDS = 300;

    public static final int DEFAULT_MAX_EXPIRATION_IN_SECONDS = 129600;

    /**
     * The set of headers to be signed.
     */
    private Set<String> headersToSign = null;

    /**
     * The time when the signature was created.
     */
    private Date timestamp = null;

    /**
     * The time until the signature will expire.
     */
    private int expirationInSeconds = DEFAULT_EXPIRATION_IN_SECONDS;

    /**
     * Returns the set of headers to be signed.
     *
     * @return the set of headers to be signed.
     */
    public Set<String> getHeadersToSign() {
        return this.headersToSign;
    }

    /**
     * Sets the set of headers to be signed.
     *
     * @param headersToSign the set of headers to be signed.
     */
    public void setHeadersToSign(Set<String> headersToSign) {
        this.headersToSign = headersToSign;
    }

    /**
     * Returns the time when the signature was created.
     *
     * @return the time when the signature was created.
     */
    public Date getTimestamp() {
        return this.timestamp;
    }

    /**
     * Sets the time when the signature was created.
     *
     * @param timestamp the time when the signature was created.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the time until the signature will expire.
     *
     * @return the time until the signature will expire.
     */
    public int getExpirationInSeconds() {
        return this.expirationInSeconds;
    }

    /**
     * Sets the time until the signature will expire.
     *
     * @param expirationInSeconds The time until the signature will expire.
     *
     * @throws IllegalArgumentException The expirationInSeconds expected in range [300, 129600].
     */
    public void setExpirationInSeconds(int expirationInSeconds) {
        checkArgument(expirationInSeconds >= DEFAULT_MIN_EXPIRATION_IN_SECONDS
                && expirationInSeconds <= DEFAULT_MAX_EXPIRATION_IN_SECONDS,
                        "Invalid expirationInSeconds, expected in range [300, 129600], actual %s",
                                expirationInSeconds);

        this.expirationInSeconds = expirationInSeconds;
    }
}
