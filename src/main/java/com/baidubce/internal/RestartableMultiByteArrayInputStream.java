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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.List;

/**
 * Restartable MultiByteArray InputStream extends Restartable InputStream.
 */
public class RestartableMultiByteArrayInputStream extends RestartableInputStream {

    private List<byte[]> byteArrayList;

    private long pos = 0;
    private int blockSize;
    private long length;

    public RestartableMultiByteArrayInputStream(List<byte[]> byteArrayList, long length) {
        checkNotNull(byteArrayList, "byteArrayList should not be null.");
        checkArgument(!byteArrayList.isEmpty(), "byteArrayList should not be empty.");
        long total = 0;
        for (byte[] byteArray : byteArrayList) {
            checkNotNull(byteArray, "byteArrayList should not contain null element.");
            checkArgument(byteArray.length > 0, "byteArrayList should not contain empty byte array.");
            total += byteArray.length;
        }
        checkArgument(total >= length,
                "The specified length(%s) is greater than the total length(%s) of elements in byteArrayList.",
                length, total);
        this.blockSize = byteArrayList.get(0).length;
        for (int i = 1; i < byteArrayList.size() - 1; ++i) {
            int len = byteArrayList.get(i).length;
            checkArgument(len == this.blockSize,
                    "All elements in byteArrayList except the last one should have the same length. " +
                            "The first element's length is %s but the %sth element's length is %s.",
                    this.blockSize, i, len);
        }
        this.byteArrayList = byteArrayList;
        this.length = length;
    }

    public RestartableMultiByteArrayInputStream(List<byte[]> byteArrayList, long length,
                                                BosProgressCallback progressCallback) {
        this(byteArrayList, length);
        super.setProgressCallback(progressCallback);
    }

    @Override
    public void restart() {
        this.pos = 0;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        checkNotNull(b, "b should not be null.");
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }
        if (this.pos == this.length) {
            return -1;
        }
        int count = 0;
        while (len > 0 && this.pos < this.length) {
            int index = (int) (this.pos / this.blockSize);
            int offset = (int) (this.pos % this.blockSize);
            byte[] byteArray = this.byteArrayList.get(index);
            int copyLength = byteArray.length - offset;
            if (copyLength > len) {
                copyLength = len;
            }
            System.arraycopy(byteArray, offset, b, off, copyLength);
            this.pos += copyLength;
            off += copyLength;
            len -= copyLength;
            count += copyLength;
        }
        super.doProgressCallback(count);

        return count;
    }

    @Override
    public int read() {
        if (this.pos == this.length) {
            return -1;
        }
        int index = (int) (this.pos / this.blockSize);
        int offset = (int) (this.pos % this.blockSize);
        ++this.pos;
        super.doProgressCallback(1);
        return this.byteArrayList.get(index)[offset] & 0xff;
    }
}
