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
package com.baidubce.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Simple InputStream wrapper that examines the wrapped stream's contents as they are read and calculates and MD5
 * digest.
 */
public class MD5DigestCalculatingInputStream extends FilterInputStream {

    /**
     * The MD5 message digest being calculated by this input stream
     */
    private MessageDigest digest;

    /**
     * The bytes remain being calculated by this input stream
     */
    private long digestRemain;

    public MD5DigestCalculatingInputStream(InputStream in, long len) throws NoSuchAlgorithmException {
        super(in);

        this.digest = MessageDigest.getInstance("MD5");
        this.digestRemain = len;
    }

    public byte[] getMd5Digest() {
        return this.digest.digest();
    }

    /**
     * Resets the wrapped input stream and the in progress message digest.
     *
     * @see java.io.InputStream#reset()
     */
    @Override
    public synchronized void reset() throws IOException {
        try {
            this.digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            /*
             * Not much to do here. We know the algorithm existed when we created the initial MessageDigest in the
             * constructor, so we can be reasonably sure that it's still going to exist if the stream gets reset.
             */
        }

        this.in.reset();
    }

    /**
     * @see java.io.InputStream#read()
     */
    @Override
    public int read() throws IOException {
        if (digestRemain <= 0) {
            return -1;
        }
        int ch = this.in.read();
        if (ch != -1) {
            this.digest.update((byte) ch);
            digestRemain -= ch;
        }
        return ch;
    }

    /**
     * @see java.io.InputStream#read(byte[], int, int)
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        long curReadMax = Math.min(digestRemain, len);
        if (curReadMax <= 0) {
            return -1;
        }
        int result = this.in.read(b, off, (int) curReadMax);
        if (result != -1) {
            this.digest.update(b, off, result);
            digestRemain -= result;
        }
        return result;
    }
}
