package com.baidubce.services.bos.callback;

/**
 * Callback interface used for get progress information
 */
public interface ProgressCallback<T> {
    void onProgress(long currentSize, long totalSize, T data);
}
