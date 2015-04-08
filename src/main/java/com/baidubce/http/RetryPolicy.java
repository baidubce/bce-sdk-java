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
package com.baidubce.http;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;

/**
 * Retry policy that can be configured on a specific service client using {@link BceClientConfiguration}.
 */
public interface RetryPolicy {

    /**
     * SDK default max retry count.
     */
    public static final int DEFAULT_MAX_ERROR_RETRY = 3;
    /**
     * Maximum exponential back-off time before retrying a request.
     */
    public static final int DEFAULT_MAX_DELAY_IN_MILLIS = 20 * 1000;
    /**
     * SDK default retry policy.
     */
    public static final DefaultRetryPolicy DEFAULT_RETRY_POLICY = new DefaultRetryPolicy();

    /**
     * Returns the maximum number of retry attempts.
     *
     * @return The maximum number of retry attempts.
     */
    public int getMaxErrorRetry();

    /**
     * Returns the maximum delay time (in milliseconds) before retrying a request.
     *
     * @return the maximum delay time (in milliseconds) before retrying a request.
     */
    public long getMaxDelayInMillis();

    /**
     * Returns the delay (in milliseconds) before next retry attempt. A negative value indicates that no more retries
     * should be made.
     *
     * @param exception        the exception from the failed request, represented as an BceClientException object.
     * @param retriesAttempted the number of times the current request has been attempted
     *         (not including the next attempt after the delay).
     *
     * @return the delay (in milliseconds) before next retry attempt.A negative value indicates that no more retries
     *         should be made.
     */
    public long getDelayBeforeNextRetryInMillis(BceClientException exception, int retriesAttempted);
}
