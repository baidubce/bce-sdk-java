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
 
/**
 * @file TableStorageConnection.java
 * @date 2019/02/25 17:48:07
 * @brief
 */

package com.baidubce.services.tablestoragehbaseclient.hbase;

import com.baidubce.services.tablestoragehbaseclient.adaptor.TablestorageConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutatorParams;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.RegionLocator;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.security.User;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TableStorageConnection derived from hbase.client.Connection
 * A connection encapsulating the corresponding configure to TableStorage.
 *
 * @date 2019/02/25 17:48:07
 */
public class TablestorageConnection implements Connection {
    private final Configuration config;
    private final TablestorageConfiguration tablestorageConfiguration;
    private volatile boolean closed = false;
    private volatile boolean aborted = false;

    /**
     * Construct a connection to TableStorage with configuration.
     *
     * @param conf the configuration used to connect TableStorage.
     * @throws IOException
     */
    public TablestorageConnection(Configuration conf) throws IOException {
        config = conf;
        tablestorageConfiguration = toTablestorageConfiguration(config);
    }

    public TablestorageConnection(Configuration conf, boolean managed, ExecutorService pool, User user) {
        if (managed) {
            throw new UnsupportedOperationException("Tablestorage does not support managed connections.");
        }

        config = conf;
        tablestorageConfiguration = toTablestorageConfiguration(config);
    }

    private TablestorageConfiguration toTablestorageConfiguration(Configuration config) {
        String endpoint = config.get(TablestorageConfiguration.TABLESTORAGE_CLIENT_ENDPOINT);
        String ak = config.get(TablestorageConfiguration.TABLESTORAGE_CLIENT_ACCESSKEYID);
        String sk = config.get(TablestorageConfiguration.TABLESTORAGE_CLIENT_SECRETACCESSKEY);
        String instanceName = config.get(TablestorageConfiguration.TABLESTORAGE_CLIENT_INSTANCENAME);

        return new TablestorageConfiguration(endpoint, instanceName, ak, sk);
    }

    public TablestorageConfiguration getTablestorageConfiguration() {
        return tablestorageConfiguration;
    }

    @Override
    public Configuration getConfiguration() {
        return this.config;
    }

    @Override
    public Table getTable(TableName tableName) throws IOException {
        return getTable(tableName, null);
    }
    @Override
    public Table getTable(TableName tableName, ExecutorService pool)  throws IOException {
        return new TablestorageTable(this, tableName);
    }

    @Override
    public BufferedMutator getBufferedMutator(TableName tableName) throws IOException {
        return new TablestorageBufferedMutator(this, tableName);
    }

    @Override
    public BufferedMutator getBufferedMutator(BufferedMutatorParams params) throws IOException {
        checkNotNull(params, "params should not be null");

        return new TablestorageBufferedMutator(this, params.getTableName());
    }

    @Override
    public RegionLocator getRegionLocator(TableName tableName) throws IOException {
        return new TablestorageRegionLocator(this, tableName);
    }

    @Override
    public Admin getAdmin() throws IOException {
        return new TablestorageAdmin(this);
    }

    @Override
    public void close() throws IOException {
        this.closed = true;
    }

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public void abort(String why, Throwable e) {
        this.aborted = true;
        throw new RuntimeException(why, e);
    }

    @Override
    public boolean isAborted() {
        return this.aborted;
    }
}

/* vim: set expandtab ts=4 sw=4 sts=4 tw=100: */
