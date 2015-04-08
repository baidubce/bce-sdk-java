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

import static com.google.common.base.Preconditions.checkArgument;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.ErrorCode;

/**
 * Retry policy that can be configured on a specific service client using {@link BceClientConfiguration}. This class is
 * immutable, therefore safe to be shared by multiple clients.
 *
 * @see BceClientConfiguration
 */
public class DefaultRetryPolicy implements RetryPolicy {

    private static Logger logger = LoggerFactory.getLogger(DefaultRetryPolicy.class);

    /**
     * Base sleep time (milliseconds) for general exceptions. *
     */
    private static final int SCALE_FACTOR = 300;

    /**
     * Non-negative integer indicating the max retry count.
     */
    private int maxErrorRetry;

    /**
     * Max delay time in millis.
     */
    private long maxDelayInMillis;

    /**
     * Constructs a new DefaultRetryPolicy.
     */
    public DefaultRetryPolicy() {
        this(RetryPolicy.DEFAULT_MAX_ERROR_RETRY, RetryPolicy.DEFAULT_MAX_DELAY_IN_MILLIS);
    }

    /**
     * Constructs a new retry policy.
     *
     * @param maxErrorRetry    Maximum number of retry attempts for failed requests.
     * @param maxDelayInMillis Maximum delay time (in milliseconds) before next retry attempt.
     * @see BceClientConfiguration
     */
    public DefaultRetryPolicy(int maxErrorRetry, long maxDelayInMillis) {
        checkArgument(maxErrorRetry >= 0, "maxErrorRetry should be a non-negative.");
        checkArgument(maxDelayInMillis >= 0, "maxDelayInMillis should be a non-negative.");

        this.maxErrorRetry = maxErrorRetry;
        this.maxDelayInMillis = maxDelayInMillis;
    }

    /**
     * Returns the maximum number of retry attempts.
     *
     * @return The maximum number of retry attempts.
     */
    @Override
    public int getMaxErrorRetry() {
        return this.maxErrorRetry;
    }

    /**
     * Returns the maximum delay time (in milliseconds) before retrying a request.
     *
     * @return the maximum delay time (in milliseconds) before retrying a request.
     */
    @Override
    public long getMaxDelayInMillis() {
        return this.maxDelayInMillis;
    }

    /**
     * Returns the delay (in milliseconds) before next retry attempt. A negative value indicates that no more retries
     * should be made.
     *
     * @param exception        the exception from the failed request, represented as an BceClientException object.
     * @param retriesAttempted the number of times the current request has been attempted
     *         (not including the next attempt after the delay).
     * @return the delay (in milliseconds) before next retry attempt.A negative value indicates that no more retries
     *         should be made.
     */
    @Override
    public long getDelayBeforeNextRetryInMillis(BceClientException exception, int retriesAttempted) {
        if (!this.shouldRetry(exception, retriesAttempted)) {
            return -1;
        }
        if (retriesAttempted < 0) {
            return 0;
        }
        return (1 << (retriesAttempted + 1)) * SCALE_FACTOR;
    }

    /**
     * Returns whether a failed request should be retried according to the given request context. In the following
     * circumstances, the request will fail directly without consulting this method:
     * <ul>
     * <li>if it has already reached the max retry limit,
     * <li>if the request contains non-repeatable content,
     * <li>if any RuntimeException or Error is thrown when executing the request.
     * </ul>
     *
     * @param exception        the exception from the failed request, represented as a BceClientException object.
     * @param retriesAttempted the number of times the current request has been attempted.
     * @return true if the failed request should be retried.
     */
    protected boolean shouldRetry(BceClientException exception, int retriesAttempted) {
        // Always retry on client exceptions caused by IOException
        if (exception.getCause() instanceof IOException) {
            logger.debug("Retry for IOException.");
            return true;
        }

        // Only retry on a subset of service exceptions
        if (exception instanceof BceServiceException) {
            BceServiceException e = (BceServiceException) exception;

            /*
             * For 500 internal server errors and 503 service unavailable errors, we want to retry, but we need to use
             * an exponential back-off strategy so that we don't overload a server with a flood of retries.
             */
            if (e.getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                logger.debug("Retry for internal server error.");
                return true;
            }
            if (e.getStatusCode() == HttpStatus.SC_SERVICE_UNAVAILABLE) {
                logger.debug("Retry for service unavailable.");
                return true;
            }

            String errorCode = e.getErrorCode();
            if (ErrorCode.REQUEST_EXPIRED.equals(errorCode)) {
                logger.debug("Retry for request expired.");
                return true;
            }
        }

        return false;
    }
}
