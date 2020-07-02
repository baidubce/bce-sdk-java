/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.bos.model;

import com.baidubce.services.bos.BosObjectInputStream;
import com.baidubce.util.Base64Utils;

import java.io.IOException;
import java.nio.ByteBuffer;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * response of select object, including the parsed query result
 */
public class SelectObjectResponse extends GetObjectResponse {
    /**
     * user specified line break
     */
    private String recordDelimiter;

    /**
     * Message body of query result
     */
    private Messages messages;

    public Messages getMessages() {
        return messages;
    }

    public void initMessages(BosObject object) {
        if (this.messages != null) {
            return;
        }
        this.messages = new Messages(object);
    }

    public String getRecordDelimiter() {
        return recordDelimiter;
    }

    public void setRecordDelimiter(String delimiterBase64) {
        this.recordDelimiter = delimiterBase64;
    }

    public class Messages implements Iterator {
        BosObject object;
        BosObjectInputStream inputStream;
        private String messageType = "";

        Messages(BosObject object) {
            this.object = object;
            inputStream = object.getObjectContent();
        }

        @Override
        public boolean hasNext() {
            if (messageType.equals("End") || messageType.equals("EndMessage lost")) {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    throw new IllegalStateException("the EndMessage may have been lost.", e);
                }
                return false;
            }
            return true;
        }

        @Override
        public CommonMessage next() {
            // parseMessage
            try {
                byte[] temp_4 = new byte[4];
                // totalLen
                inputStream.read(temp_4);
                int totalLen = ByteBuffer.wrap(temp_4).getInt();

                // headersLen
                inputStream.read(temp_4);
                int headerLen = ByteBuffer.wrap(temp_4).getInt();

                // headersVal
                byte[] temp_headers = new byte[headerLen];
                inputStream.read(temp_headers);
                Map<String, String> headers = parseMessages(temp_headers);
                if (headers.get(Constants.MESSAGE_TYPE).equals("Records")) {
                    this.messageType = "Records";

                    // payload part
                    int payloadLen = totalLen - headerLen - 12;
                    byte[] temp_payLoad = new byte[payloadLen];
                    inputStream.read(temp_payLoad);
                    String payload = new String(temp_payLoad);

                    // crc
                    inputStream.read(temp_4);
                    int crc = ByteBuffer.wrap(temp_4).getInt();
                    Prelude prelude = new Prelude(totalLen, headerLen);

                    // default \n
                    String delimiter = "\n";
                    if (recordDelimiter != null) {
                        delimiter = Base64Utils.decode(recordDelimiter);
                    }

                    return new RecordsMessage(prelude, headers, payload.split(delimiter), crc);
                }

                if (headers.get(Constants.MESSAGE_TYPE).equals("Cont")) {
                    this.messageType = "Cont";
                    byte[] temp_8 = new byte[8];

                    inputStream.read(temp_8);
                    int bytesScanned = ByteBuffer.wrap(temp_8).getInt();

                    inputStream.read(temp_8);
                    int bytesReturned = ByteBuffer.wrap(temp_8).getInt();

                    inputStream.read(temp_4);
                    int crc = ByteBuffer.wrap(temp_4).getInt();

                    Prelude prelude = new Prelude(totalLen, headerLen);

                    return new ContinuationMessage(prelude, headers, bytesScanned, bytesReturned, crc);
                }

                if (headers.get(Constants.MESSAGE_TYPE).equals("End")) {
                    this.messageType = "End";
                    inputStream.read(temp_4);

                    int crc = ByteBuffer.wrap(temp_4).getInt();
                    Prelude prelude = new Prelude(totalLen, headerLen);

                    inputStream.close();

                    return new EndMessage(prelude, headers, crc);
                }
            } catch (IOException e) {
                throw new IllegalStateException("Failed to parse parameters.", e);
            }

            this.messageType = "EndMessage lost";

            return null;
        }

        @Override
        @Deprecated
        public void remove() {
            // This method is not supported
        }

    }

    public class Prelude {
        int TotalLen;
        int HeadersLen;

        Prelude(int totalLen, int headersLen) {
            this.TotalLen = totalLen;
            this.HeadersLen = headersLen;
        }

        @Override
        public String toString() {
            return "Prelude{" +
                    "TotalLen=" + TotalLen +
                    ", HeadersLen=" + HeadersLen +
                    '}';
        }
    }

    public class CommonMessage {
        public String Type;
        public Prelude Prelude;
        public Map Headers;
        private int Crc32;
        private String[] Records;

        CommonMessage(Prelude prelude, Map headers, int crc32) {
            this.Prelude = prelude;
            this.Headers = headers;
            this.Crc32 = crc32;
        }

        public int getCrc32() {
            return Crc32;
        }

        public void setCrc32(int crc32) {
            Crc32 = crc32;
        }

        public String[] getRecords() {
            return Records;
        }

        public void setRecords(String[] records) {
            Records = records;
        }
    }

    public class RecordsMessage extends CommonMessage {
        RecordsMessage(Prelude prelude, Map<String, String> headers, String[] records, int crc32) {
            super(prelude, headers, crc32);
            super.setRecords(records);
            super.Type = "Records";
        }

        @Override
        public String toString() {
            return "RecordsMessage{" +
                    "Prelude=" + Prelude.toString() +
                    ", Headers=" + Headers +
                    ", Records=" + Arrays.toString(super.getRecords()) +
                    ", Crc32=" + super.getCrc32() +
                    '}';
        }
    }

    public class ContinuationMessage extends CommonMessage {
        public int BytesScanned;
        public int BytesReturned;

        ContinuationMessage(Prelude prelude, Map<String, String> headers,
                            int bytesScanned, int bytesReturned, int crc32) {
            super(prelude, headers, crc32);
            super.Type = "Cont";
            this.BytesScanned = bytesScanned;
            this.BytesReturned = bytesReturned;
        }

        @Override
        public String toString() {
            return "ContinuationMessage{" +
                    "Prelude=" + Prelude.toString() +
                    ", Headers=" + Headers +
                    ", BytesScanned=" + BytesScanned +
                    ", BytesReturned=" + BytesReturned +
                    ", Crc32=" + super.getCrc32() +
                    '}';
        }
    }

    public class EndMessage extends CommonMessage {
        EndMessage(Prelude prelude, Map<String, String> headers, int crc32) {
            super(prelude, headers, crc32);
            super.Type = "End";
        }

        @Override
        public String toString() {
            return "EndMessage{" +
                    "Prelude=" + Prelude.toString() +
                    ", Headers=" + Headers +
                    ", Crc32=" + super.getCrc32() +
                    '}';
        }
    }

    private Map<String, String> parseMessages(byte[] headers) {
        Map<String, String> map = new HashMap<String, String>();
        int index = 0;

        while (index < headers.length) {
            // headers key length
            int keyLen = headers[index] & 0xff;
            index += 1;

            // headers key
            String headerKey = new String(Arrays.copyOfRange(headers, index, index + keyLen));
            index += keyLen;

            // headers value length
            byte[] v = new byte[4];
            v[0] = 0;
            v[1] = 0;
            v[2] = headers[index];
            v[3] = headers[index + 1];
            int valLen = ByteBuffer.wrap(v).getInt();
            index += 2;

            // headers value
            String headerVal = new String(Arrays.copyOfRange(headers, index, index + valLen));
            index += valLen;
            map.put(headerKey, headerVal);
        }

        return map;
    }
}