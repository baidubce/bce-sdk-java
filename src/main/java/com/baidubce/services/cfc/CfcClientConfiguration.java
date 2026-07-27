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
package com.baidubce.services.cfc;

import com.baidubce.BceClientConfiguration;

/**
 * Client configurations for CFC clients.
 * <p>
 * Overrides the default connection / socket timeouts to 310 seconds, matching the maximum CFC
 * function invocation duration. Users can still customize timeouts via
 * {@link #setConnectionTimeoutInMillis(int)} / {@link #setSocketTimeoutInMillis(int)}.
 */
public class CfcClientConfiguration extends BceClientConfiguration {

    /**
     * The default invoke max timeout for creating new connections.
     */
    public static final int DEFAULT_INVOKE_CONNECTION_TIMEOUT_IN_MILLIS = 310 * 1000;

    /**
     * The default invoke max timeout for reading from a connected socket.
     */
    public static final int DEFAULT_INVOKE_SOCKET_TIMEOUT_IN_MILLIS = 310 * 1000;

    public CfcClientConfiguration() {
        super();
        this.setConnectionTimeoutInMillis(DEFAULT_INVOKE_CONNECTION_TIMEOUT_IN_MILLIS);
        this.setSocketTimeoutInMillis(DEFAULT_INVOKE_SOCKET_TIMEOUT_IN_MILLIS);
    }
}
