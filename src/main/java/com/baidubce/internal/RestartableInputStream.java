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
package com.baidubce.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Abstract class Restartable InputStream extends InputStream.
 */
public abstract class RestartableInputStream extends InputStream {
    public abstract void restart();

    public static RestartableInputStream wrap(byte[] b) {
        ByteArrayInputStream input = new ByteArrayInputStream(b);
        input.mark(b.length);
        return new RestartableResettableInputStream(input);
    }
}
