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

import com.baidubce.BceClientException;
import com.baidubce.services.bos.model.BosProgressCallback;

import java.io.IOException;
import java.io.InputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Restartable InputStream can Reset.
 */
public class RestartableResettableInputStream extends RestartableInputStream {
    private InputStream input;

    public RestartableResettableInputStream(InputStream input) {
        checkNotNull(input, "input should not be null.");
        checkArgument(input.markSupported(), "input does not support mark.");
        this.input = input;
    }

    public RestartableResettableInputStream(InputStream input, BosProgressCallback progressCallback) {
        this(input);
        super.setProgressCallback(progressCallback);
    }

    @Override
    public void restart() {
        try {
            this.input.reset();
            super.restartProgressCallback();
        } catch (IOException e) {
            throw new BceClientException("Fail to reset the underlying input stream.", e);
        }
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int count = this.input.read(b, off, len);
        if (count < 0) {
            return count;
        }
        super.doProgressCallback(count);

        return count;
    }

    @Override
    public int read() throws IOException {
        int count = this.input.read();
        if (count < 0) {
            return count;
        }
        super.doProgressCallback(1);
        return count;
    }

    @Override
    public void close() throws IOException {
        this.input.close();
    }
}
