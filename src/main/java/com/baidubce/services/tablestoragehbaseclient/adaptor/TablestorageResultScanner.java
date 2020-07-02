/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.baidubce.services.tablestoragehbaseclient.adaptor;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * For result scanning.
 */
public class TablestorageResultScanner {
    private static final Logger LOG = LoggerFactory.getLogger(TablestorageResultScanner.class);

    private BlockingQueue<Result> resultQueue = new LinkedBlockingQueue<Result>(10);
    private TableStorageAdaptor adaptor;
    CountDownLatch count;
    private Scan scan;
    private String tableName;
    private String startRowkey = "";
    private String errMsg;
    private boolean error = false;
    private boolean stop = false;
    private boolean scanEnd = false;

    public TablestorageResultScanner(TableStorageAdaptor adaptor, ExecutorService pool, Scan scan,
                                     String tableName) {
        this.adaptor = adaptor;
        this.tableName = tableName;
        this.scan = scan;

        pool.submit(new ScanRunnable());
    }

    public Result next() throws IOException {
        Result result;

        while ((result = resultQueue.poll()) == null) {
            if (error) {
                throw new IOException(errMsg);
            }
            if (scanEnd) {
                if (resultQueue.isEmpty()) {
                    LOG.debug("scan end");
                    return null;
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void close() {
        this.stop = true;

        try {
            if (count != null) {
                count.await();
            }
        } catch (InterruptedException e) {
            LOG.debug("count wait failed");
        }
    }

    public class ScanRunnable implements Runnable {

        @Override
        public void run() {
            count = new CountDownLatch(1);
            while (true) {
                ByteArrayOutputStream nextStartRowkeyStream = new ByteArrayOutputStream();
                List<Result> results = new ArrayList<Result>();
                try {
                    results = adaptor.scan(tableName, scan, startRowkey, nextStartRowkeyStream);
                } catch (Exception e) {
                    error = true;
                    errMsg = e.getMessage();
                    e.printStackTrace();
                    break;
                }

                byte[] nextRowkeyBytes = nextStartRowkeyStream.toByteArray();
                startRowkey = Bytes.toString(nextRowkeyBytes);

                for (Result result : results) {
                    while (!resultQueue.offer(result)) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (stop) {
                            scanEnd = true;
                            break;
                        }
                    }
                }

                if (startRowkey == "") {
                    scanEnd = true;
                    break;
                }
            }
            count.countDown();
        }

    }
}
