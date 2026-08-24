package com.baidubce.services.bes.v2;

import static com.google.common.base.Preconditions.checkNotNull;

import com.baidubce.BceClientException;
import com.baidubce.internal.RestartableInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Used to stream BES v2 multipart/form-data upload
 * bodies without buffering the file content into memory.
 * Scoped to the BES v2 client only.
 */
class MultipartFormStream extends RestartableInputStream {

    private static final int SEGMENT_PREAMBLE = 0;
    private static final int SEGMENT_FILE = 1;
    private static final int SEGMENT_TRAILER = 2;
    private static final int SEGMENT_DONE = 3;

    private final byte[] preamble;
    private final File file;
    private final byte[] trailer;

    private int segment;
    private int preamblePos;
    private FileInputStream fileInput;
    private int trailerPos;

    MultipartFormStream(byte[] preamble, File file, byte[] trailer) throws FileNotFoundException {
        checkNotNull(preamble, "preamble should not be null.");
        checkNotNull(file, "file should not be null.");
        checkNotNull(trailer, "trailer should not be null.");
        this.preamble = preamble;
        this.file = file;
        this.trailer = trailer;
        this.fileInput = new FileInputStream(file);
        this.segment = SEGMENT_PREAMBLE;
    }

    @Override
    public void restart() {
        try {
            this.fileInput.close();
            this.fileInput = new FileInputStream(this.file);
            this.segment = SEGMENT_PREAMBLE;
            this.preamblePos = 0;
            this.trailerPos = 0;
            super.restartProgressCallback();
        } catch (IOException e) {
            throw new BceClientException("Fail to restart.", e);
        }
    }

    @Override
    public int read() throws IOException {
        byte[] singleByte = new byte[1];
        int count = read(singleByte, 0, 1);
        return count < 0 ? -1 : (singleByte[0] & 0xff);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int totalCount = 0;
        while (len > 0) {
            if (segment == SEGMENT_PREAMBLE) {
                if (preamblePos < preamble.length) {
                    int copyLength = Math.min(len, preamble.length - preamblePos);
                    System.arraycopy(preamble, preamblePos, b, off, copyLength);
                    preamblePos += copyLength;
                    off += copyLength;
                    len -= copyLength;
                    totalCount += copyLength;
                    continue;
                }
                segment = SEGMENT_FILE;
            }
            if (segment == SEGMENT_FILE) {
                int count = fileInput.read(b, off, len);
                if (count < 0) {
                    segment = SEGMENT_TRAILER;
                    continue;
                }
                if (count == 0) {
                    break;
                }
                off += count;
                len -= count;
                totalCount += count;
                continue;
            }
            if (segment == SEGMENT_TRAILER) {
                if (trailerPos < trailer.length) {
                    int copyLength = Math.min(len, trailer.length - trailerPos);
                    System.arraycopy(trailer, trailerPos, b, off, copyLength);
                    trailerPos += copyLength;
                    off += copyLength;
                    len -= copyLength;
                    totalCount += copyLength;
                    continue;
                }
                segment = SEGMENT_DONE;
            }
            break;
        }
        if (totalCount == 0) {
            return -1;
        }
        super.doProgressCallback(totalCount);
        return totalCount;
    }

    @Override
    public void close() throws IOException {
        this.fileInput.close();
    }
}
