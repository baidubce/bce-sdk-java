/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bsc.model.constant;

/**
 * constants for job status
 */
public class JobStatusConstant {
    public static String NEW = "NEW";
    public static String SUBMITTING = "SUBMITTING";
    public static String SUCCESS = "SUCCESS";
    public static String FAILED = "FAILED";
    public static String KILLED = "KILLED";
    public static String TIMEOUT = "TIMEOUT";
    public static String STOPPED = "STOPPED";

    public static String UNKNOWN = "UNKNOWN";

    public static String RUNNING = "RUNNING";

    public static boolean stopStatus(String status) {
        return status.equals(SUCCESS)
                || status.equals(FAILED)
                || status.equals(KILLED)
                || status.equals(TIMEOUT)
                || status.equals(STOPPED)
                || status.equals(NEW);
    }
}
