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
package com.baidubce.services.blb.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The constants of listener
 */
public class ListenerConstant {

    public static final String HTTPS_LISTENER = "HTTPS";
    public static final String HTTP_LISTENER = "HTTP";
    public static final String TCP_LISTENER = "TCP";
    public static final String UDP_LISTENER = "UDP";

    public static final Set<String> LISTENER_SET = new HashSet<String>(Arrays.asList(HTTPS_LISTENER, HTTP_LISTENER,
            TCP_LISTENER, UDP_LISTENER));

}
