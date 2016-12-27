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

import org.apache.http.annotation.NotThreadSafe;

import com.baidubce.BceClientException;

/**
 * Used to perform length check to ensure the number of bytes read from the underlying input stream is the same as the
 * expected total.
 */
@NotThreadSafe
public class LengthCheckInputStream extends FilterInputStream {
    public static final boolean INCLUDE_SKIPPED_BYTES = true;
    public static final boolean EXCLUDE_SKIPPED_BYTES = false;
    /**
     * Total number of bytes expected to be read from the underlying input stream.
     */
    private final long expectedLength;
    /**
     * True if skipped bytes are to be included as part of the data length; false otherwise.
     */
    private final boolean includeSkipped;
    /**
     * The length of the data read from the underlying input stream so far.
     */
    private long dataLength;
    private long marked; // used for mark-and-reset purposes

    /**
     * Constructs an input stream that performs length check to ensure the number of bytes read from the underlying
     * input stream is the same as the expected total.
     *
     * @param in             the underlying input stream
     * @param expectedLength the total length of the data in bytes expected to be read from the underlying input stream;
     *                       must be non-negative.
     * @param includeSkipped true if bytes skipped are to be considered as part of the data length; false otherwise.
     *                       Typically, this parameter should be set to false for uploading data to BCE,
     *                       but set to true for receiving data from BCE.
     */
    public LengthCheckInputStream(InputStream in, long expectedLength, boolean includeSkipped) {
        super(in);
        if (expectedLength < 0) {
            throw new IllegalArgumentException();
        }
        this.expectedLength = expectedLength;
        this.includeSkipped = includeSkipped;
    }

    /**
     * {@inheritDoc}
     *
     * @throws com.baidubce.BceClientException if the data length read has exceeded the expected total, or if the total
     *                                         data length is not the same as the expected total.
     */
    @Override
    public int read() throws IOException {
        final int c = super.read();
        if (c >= 0) {
            this.dataLength++;
        }
        this.checkLength(c == -1);
        return c;
    }

    /**
     * {@inheritDoc}
     *
     * @throws com.baidubce.BceClientException if the data length read has exceeded the expected total, or if the total
     *                                         data length is not the same as the expected total.
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int readLen = super.read(b, off, len);
        this.dataLength += readLen >= 0 ? readLen : 0;
        this.checkLength(readLen == -1);
        return readLen;
    }

    @Override
    public void mark(int readlimit) {
        super.mark(readlimit);
        this.marked = this.dataLength;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        if (super.markSupported()) {
            this.dataLength = this.marked;
        }
    }

    /**
     * Checks the data length read so far against the expected total.
     *
     * @param eof true if end of stream has been encountered; false otherwise
     * @throws com.baidubce.BceClientException if the data length read has exceeded the expected total, or if the total
     *                                         data length is not the same as the expected total.
     */
    private void checkLength(boolean eof) {
        if (eof) {
            if (this.dataLength != this.expectedLength) {
                throw new BceClientException("Data read (" + this.dataLength +
                        ") has a different length than the expected (" + this.expectedLength +
                        ")");
            }
        } else if (this.dataLength > this.expectedLength) {
            throw new BceClientException("More data read (" + this.dataLength + ") than expected (" +
                    this.expectedLength + ")");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws com.baidubce.BceClientException if {@link #includeSkipped} is true and the data length skipped has
     *                                         exceeded the expected total.
     */
    @Override
    public long skip(long n) throws IOException {
        final long skipped = super.skip(n);
        if (this.includeSkipped && skipped > 0) {
            this.dataLength += skipped;
            this.checkLength(false);
        }
        return skipped;
    }
}
