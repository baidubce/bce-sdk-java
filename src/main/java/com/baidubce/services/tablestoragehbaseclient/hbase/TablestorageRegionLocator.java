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

package com.baidubce.services.tablestoragehbaseclient.hbase;

import com.baidubce.services.tablestoragehbaseclient.adaptor.Constants;
import com.baidubce.services.tablestoragehbaseclient.adaptor.TableStorageAdaptor;
import com.baidubce.services.tablestoragehbaseclient.adaptor.TablestorageConfiguration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.RegionLocator;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * TablestorageRegionLocator derived from org.apache.hadoop.hbase.client.RegionLocator
 * @date 2019/03/14 17:40:56
 */
public class TablestorageRegionLocator implements RegionLocator {
    private static long MAX_REGION_CACHE_TIME_MS = 10 * 60 * 1000;

    private TableStorageAdaptor adaptor;
    private final TableName tableName;
    private ServerName serverName;
    private List<HRegionLocation> regions;
    private long regionsLastReloadTimeMS;

    /**
     * Construct a TablestorageRegionLocator for the target instance.
     *
     * @param connection The connection used to construct this TablestorageRegionLocator.
     * @param tableName The name of the target table.
     */
    public TablestorageRegionLocator(TablestorageConnection connection, TableName tableName) {
        this.tableName = tableName;
        this.serverName = ServerName.valueOf(connection.getTablestorageConfiguration().getEndpoint(),
                Constants.DEFAULT_SERVER_PORT, Constants.DEFAULT_SERVER_START_CODE);
        TablestorageConfiguration conf = connection.getTablestorageConfiguration();
        this.adaptor = new TableStorageAdaptor(conf.getEndpoint(), conf.getInstanceName(),
                conf.getAccessKeyId(), conf.getSecretAccessKey());
        this.regions = null;
        this.regionsLastReloadTimeMS = 0;
    }

    @Override
    public HRegionLocation getRegionLocation(byte[] row) throws IOException {
        return getRegionLocation(row, false);
    }

    @Override
    public HRegionLocation getRegionLocation(byte[] row, boolean reload) throws IOException {
        for (HRegionLocation region : getRegions(reload)) {
            if (region.getRegionInfo().containsRow(row)) {
                return region;
            }
        }
        throw new IOException("Region not found, row: " + Bytes.toStringBinary(row));
    }

    @Override
    public List<HRegionLocation> getAllRegionLocations() throws IOException {
        return getRegions(false);
    }

    @Override
    public byte[][] getStartKeys() throws IOException {
        return adaptor.getStartKeys(tableName.getNameAsString());
    }

    @Override
    public byte[][] getEndKeys() throws IOException {
        return adaptor.getEndKeys(tableName.getNameAsString());
    }

    @Override
    public Pair<byte[][], byte[][]> getStartEndKeys() throws IOException {
        return adaptor.getStartEndKeys(tableName.getNameAsString());
    }

    @Override
    public TableName getName() {
        return tableName;
    }

    @Override
    public void close() throws IOException {
        adaptor.close();
    }

    private List<HRegionLocation> getRegions(boolean reload) throws IOException {
        if (!reload && regions != null
                && regionsLastReloadTimeMS + MAX_REGION_CACHE_TIME_MS > System.currentTimeMillis()) {
            return regions;
        }

        regions = new ArrayList<HRegionLocation>();
        Pair<byte[][], byte[][]> startEndKeys = adaptor.getStartEndKeys(tableName.getNameAsString());
        byte[][] startKeys = startEndKeys.getFirst();
        byte[][] endKeys = startEndKeys.getSecond();
        for (int i = 0; i < startKeys.length; i++) {
            HRegionInfo regionInfo = new HRegionInfo(tableName, startKeys[i], endKeys[i]);
            HRegionLocation region = new HRegionLocation(regionInfo, serverName, i);
            regions.add(region);
        }
        regionsLastReloadTimeMS = System.currentTimeMillis();
        return regions;
    }
}
