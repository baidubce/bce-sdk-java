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

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.baidubce.BceClientException;
import com.baidubce.services.bos.model.BosProgressCallback;

/**
 * Restartable File InputStream extends Restartable InputStream.
 */
public class RestartableFileInputStream extends RestartableInputStream {

    private File file;

    private FileInputStream input;

    public RestartableFileInputStream(File file) throws FileNotFoundException {
        checkNotNull(file, "file should not be null.");
        this.file = file;
        this.input = new FileInputStream(file);
    }

    public RestartableFileInputStream(File file, BosProgressCallback progressCallback) throws FileNotFoundException {
        this(file);
        super.setProgressCallback(progressCallback);
    }

    @Override
    public void restart() {
        try {
            this.input.close();
            this.input = new FileInputStream(this.file);
            super.restartProgressCallback();
        } catch (IOException e) {
            throw new BceClientException("Fail to restart.", e);
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
