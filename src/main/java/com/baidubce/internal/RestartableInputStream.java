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
package com.baidubce.internal;

import com.baidubce.services.bos.model.BosProgressCallback;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Abstract class Restartable InputStream extends InputStream.
 */
public abstract class RestartableInputStream extends InputStream {
    private BosProgressCallback progressCallback = null;

    public abstract void restart();

    public static RestartableInputStream wrap(byte[] b) {
        ByteArrayInputStream input = new ByteArrayInputStream(b);
        input.mark(b.length);
        return new RestartableResettableInputStream(input);
    }

    public void setProgressCallback(BosProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
    }

    public void doProgressCallback(int count) {
        if (progressCallback != null) {
            progressCallback.addCurrentSize(count);
        }
    }

    public void restartProgressCallback() {
        if (progressCallback != null) {
            progressCallback.setCurrentSize(0);
        }
    }
}
