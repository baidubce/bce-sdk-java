package com.baidubce.services.bos.model;

import com.baidubce.services.bos.callback.ProgressCallback;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Callback interface used for get progress information
 */

public abstract class BosProgressCallback<T> implements ProgressCallback<T> {
    private AtomicLong currentSize = new AtomicLong(0L);
    private AtomicLong totalSize = new AtomicLong(0L);;
    private T data = null;

    @Override
    public void onProgress(long currentSize, long totalSize, T data) {
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public BosProgressCallback<T> withData(T data) {
        this.setData(data);
        return this;
    }

    public long getCurrentSize() {
        return currentSize.get();
    }

    public void setCurrentSize(long currentSize) {
        this.currentSize.set(currentSize);
    }

    public void addCurrentSize(long size) {
        if (currentSize.get() + size > totalSize.get()) {
            currentSize.set(totalSize.get());
        } else {
            currentSize.addAndGet(size);
        }
        onProgress(currentSize.get(), totalSize.get(), data);
    }

    public long getTotalSize() {
        return totalSize.get();
    }

    public void setTotalSize(long totalSize) {
        this.totalSize.set(totalSize);
    }
}

