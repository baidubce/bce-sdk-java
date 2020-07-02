/*
 * Copyright 2018-2019 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iothisk.device.model;

import static com.baidubce.services.iothisk.device.utils.ByteUtils.getCounterBytes;
import static com.google.common.primitives.Bytes.concat;

/**
 * Represent plain message, including message counter and message content.
 */
public class PlainMessage {

    /**
     * Message counter
     */
    private long counter;

    /**
     * Message content
     */
    private byte[] message;

    /**
     * Construct plain message
     *
     * @param counter message counter
     * @param message message content
     */
    public PlainMessage(long counter, byte[] message) {
        this.counter = counter;
        this.message = message;
    }

    /**
     * Construct plain message
     */
    public PlainMessage() {

    }

    /**
     * Get message whole content
     *
     * @return message whole content
     */
    public byte[] getBytes() {
        return concat(getCounterBytes(counter), message);
    }

    /**
     * Get message counter
     *
     * @return message counter
     */
    public long getCounter() {
        return counter;
    }

    /**
     * Set message counter
     *
     * @param counter message counter
     */
    public void setCounter(long counter) {
        this.counter = counter;
    }

    /**
     * Get message content
     *
     * @return message content
     */
    public byte[] getMessage() {
        return message;
    }

    /**
     * Set message content
     *
     * @param message message context
     */
    public void setMessage(byte[] message) {
        this.message = message;
    }

}
