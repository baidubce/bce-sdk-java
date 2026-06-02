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
 * @file TablestorageAdmin.java
 * @date 2019/02/26 15:26:21
 * @brief
 */

package com.baidubce.services.tablestoragehbaseclient.hbase;
import com.baidubce.services.tablestorage.TableStorageConstants;
import com.baidubce.services.tablestorage.model.CompressType;
import com.baidubce.services.tablestoragehbaseclient.adaptor.Constants;
import com.baidubce.services.tablestoragehbaseclient.adaptor.TableStorageAdaptor;
import com.baidubce.services.tablestoragehbaseclient.adaptor.TablestorageConfiguration;
import com.baidubce.services.tablestoragehbaseclient.adaptor.TablestorageConvertor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.CacheEvictionStats;
import org.apache.hadoop.hbase.ClusterMetrics;
import org.apache.hadoop.hbase.ClusterMetrics.Option;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.RegionMetrics;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.BalanceRequest;
import org.apache.hadoop.hbase.client.BalanceResponse;
import org.apache.hadoop.hbase.client.CompactType;
import org.apache.hadoop.hbase.client.CompactionState;
import org.apache.hadoop.hbase.client.RegionInfo;
import org.apache.hadoop.hbase.client.SnapshotDescription;
import org.apache.hadoop.hbase.client.LogEntry;
import org.apache.hadoop.hbase.client.ServerType;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.NormalizeTableFilterParams;
import org.apache.hadoop.hbase.client.replication.TableCFs;
import org.apache.hadoop.hbase.client.security.SecurityCapability;
import org.apache.hadoop.hbase.ipc.CoprocessorRpcChannel;
import org.apache.hadoop.hbase.quotas.QuotaFilter;
import org.apache.hadoop.hbase.quotas.QuotaRetriever;
import org.apache.hadoop.hbase.quotas.QuotaSettings;
import org.apache.hadoop.hbase.quotas.SpaceQuotaSnapshotView;
import org.apache.hadoop.hbase.replication.ReplicationPeerConfig;
import org.apache.hadoop.hbase.replication.ReplicationPeerDescription;
import org.apache.hadoop.hbase.security.access.GetUserPermissionsRequest;
import org.apache.hadoop.hbase.security.access.Permission;
import org.apache.hadoop.hbase.security.access.UserPermission;
import org.apache.hadoop.hbase.snapshot.HBaseSnapshotException;
import org.apache.hadoop.hbase.snapshot.RestoreSnapshotException;
import org.apache.hadoop.hbase.snapshot.SnapshotCreationException;
import org.apache.hadoop.hbase.snapshot.UnknownSnapshotException;
import org.apache.hadoop.hbase.util.Pair;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.regex.Pattern;


import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TablestorageAdmin derived from hbase.client.Admin
* @date 2019/02/26 15:26:21
*/
public class TablestorageAdmin implements Admin {
    private TableStorageAdaptor adaptor;
    private TablestorageConnection connection;
    private boolean aborted;
    private final Configuration config;
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
        4,           
        8,         
        60,          
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(100),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.AbortPolicy()
    );
    /**
     * Construct a TablestorageAdmin for the target instance.
    *
    * @param connection The connection used to construct this TablestorageAdmin.
    */
    public TablestorageAdmin(TablestorageConnection connection) {
        TablestorageConfiguration conf = connection.getTablestorageConfiguration();
        this.adaptor = new TableStorageAdaptor(conf.getEndpoint(), conf.getInstanceName(),
                conf.getAccessKeyId(), conf.getSecretAccessKey());
        this.connection = connection;
        this.aborted = false;
        this.config = new Configuration();
    }

    @Override
    public int getSyncWaitTimeout() {
        return config.getInt("hbase.client.sync.wait.timeout.msec", 60000); // Default to 60 seconds
    }

    @Override
    public List<TableDescriptor> listTableDescriptors() throws IOException {
        throw new UnsupportedOperationException("listTableDescriptors()");
    }

    @Override
    public List<TableDescriptor> listTableDescriptors(Pattern pattern, boolean includeSysTables) throws IOException {
        throw new UnsupportedOperationException("listTableDescriptors(Pattern pattern, boolean includeSysTables)");
    }

    @Override
    public List<TableDescriptor> listTableDescriptors(List<TableName> tableNames) throws IOException {
        throw new UnsupportedOperationException("listTableDescriptors(List<TableName> tableNames)");
    }

    @Override
    public TableDescriptor getDescriptor(TableName tableName) throws TableNotFoundException, IOException {
        throw new UnsupportedOperationException("getDescriptor(TableName tableName)");
    }

    @Override
    public void createTable(TableDescriptor desc, byte[] startKey, byte[] endKey, int numRegions) throws IOException {
        throw new UnsupportedOperationException("createTable(TableDescriptor desc, byte[] startKey, byte[] endKey, int numRegions)");
    }

    @Override
    public Future<Void> createTableAsync(final TableDescriptor desc) throws IOException {
        return CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    checkNotNull(desc, "desc should not be null");
        
                    if (desc.getColumnFamilies().length > Constants.MAX_COLUMN_FAMILY_NUM) {
                        throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
                    }
            
                    CompressType type = CompressType.DEFAULT;
                    int maxVersions = TableStorageConstants.DEFAULT_TABLE_MAX_VERSIONS;
                    int ttl = TableStorageConstants.DEFAULT_LIVE_TIME;
                    for (ColumnFamilyDescriptor descriptor : desc.getColumnFamilies()) {
                        if (!descriptor.getNameAsString().equals(Constants.DEFAULT_FAMILY)) {
                            throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
                        }
                        type = TablestorageConvertor.toCompressionType(descriptor.getCompressionType());
                        if (descriptor.getMaxVersions() > 0){
                            maxVersions = descriptor.getMaxVersions();
                        }
                        String descriptorValue = Bytes.toString(descriptor.getValue(Bytes.toBytes(HColumnDescriptor.TTL)));
                        ttl = TableStorageConstants.DEFAULT_LIVE_TIME;
                        if (descriptorValue != null) {
                            ttl = Integer.parseInt(descriptorValue);
                        }
                        if (ttl == HConstants.FOREVER) {
                            ttl = TableStorageConstants.FORERVER_LIVE_TIME;
                        }
                    }
            
                    adaptor.createTable(desc.getTableName().getNameAsString(), type, maxVersions, ttl);
                } catch (IOException e) {
                    // 处理可能的异常
                    throw new RuntimeException("Failed to create table: " + desc.getTableName(), e);
                }
            }
        }, executor);
    }

    @Override
    public Future<Void> createTableAsync(TableDescriptor desc, byte[][] splitKeys) throws IOException {
        throw new UnsupportedOperationException("createTableAsync(TableDescriptor desc, byte[][] splitKeys)");
    }

    @Override
    public Future<Void> deleteTableAsync(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("deleteTableAsync(TableName tableName)");
    }

    @Override
    public Future<Void> truncateTableAsync(TableName tableName, boolean preserveSplits) throws IOException {
        throw new UnsupportedOperationException("truncateTableAsync(TableName tableName, boolean preserveSplits)");
    }

    @Override
    public Future<Void> addColumnFamilyAsync(TableName tableName, ColumnFamilyDescriptor columnFamily) throws IOException {
        throw new UnsupportedOperationException("addColumnFamilyAsync(TableName tableName, ColumnFamilyDescriptor columnFamily)");
    }

    @Override
    public Future<Void> deleteColumnFamilyAsync(TableName tableName, byte[] columnFamily) throws IOException {
        throw new UnsupportedOperationException("deleteColumnFamilyAsync(TableName tableName, byte[] columnFamily");
    }

    @Override
    public Future<Void> modifyColumnFamilyAsync(final TableName tableName, final ColumnFamilyDescriptor columnFamily) throws IOException {
        return CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    checkNotNull(tableName, "tableName should not be null");
                    checkNotNull(columnFamily, "descriptor should not be null");
            
                    if (!columnFamily.getNameAsString().equals(Constants.DEFAULT_FAMILY)) {
                        throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
                    }
                    CompressType type = TablestorageConvertor.toCompressionType(columnFamily.getCompressionType());
                    int maxVersions = columnFamily.getMaxVersions();
                    String descriptorValue = columnFamily.getValue(HColumnDescriptor.TTL);
                    int ttl = TableStorageConstants.DEFAULT_LIVE_TIME;
                    if (descriptorValue != null) {
                        ttl = Integer.parseInt(descriptorValue);
                    }
                    if (ttl == HConstants.FOREVER) {
                        ttl = TableStorageConstants.FORERVER_LIVE_TIME;
                    }
            
                    adaptor.updateTable(tableName.getNameAsString(), type, maxVersions, ttl);
                } catch (IOException e) {
                    // 处理可能的异常
                    throw new RuntimeException("Failed to modify column family", e);
                }
            }
        }, executor);
    }

    @Override
    public Future<Void> modifyColumnFamilyStoreFileTrackerAsync(TableName tableName, byte[] family, String dstSFT) throws IOException {
        throw new UnsupportedOperationException("modifyColumnFamilyStoreFileTrackerAsync(TableName tableName, byte[] family, String dstSFT)");
    }

    @Override
    public void flush(TableName tableName, byte[] columnFamily) throws IOException {
        throw new UnsupportedOperationException("flush(TableName tableName, byte[] columnFamily)");
    }

    @Override
    public void flushRegion(byte[] regionName, byte[] columnFamily) throws IOException {
        throw new UnsupportedOperationException("flushRegion(byte[] regionName, byte[] columnFamily)");
    }

    @Override
    public void flushRegionServer(ServerName serverName) throws IOException {
        throw new UnsupportedOperationException("flushRegionServer(ServerName serverName) ");
    }

    @Override
    public void compact(TableName tableName, CompactType compactType) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("compact(TableName tableName, CompactType compactType)");
    }

    @Override
    public void compact(TableName tableName, byte[] columnFamily, CompactType compactType) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("compact(TableName tableName, byte[] columnFamily, CompactType compactType)");
    }

    @Override
    public void majorCompact(TableName tableName, CompactType compactType) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("majorCompact(TableName tableName, CompactType compactType)");
    }

    @Override
    public void majorCompact(TableName tableName, byte[] columnFamily, CompactType compactType) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("majorCompact(TableName tableName, byte[] columnFamily, CompactType compactType)");
    }

    @Override
    public Map<ServerName, Boolean> compactionSwitch(boolean switchState, List<String> serverNamesList) throws IOException {
        throw new UnsupportedOperationException("compactionSwitch(boolean switchState, List<String> serverNamesList)");
    }

    @Override
    public void compactRegionServer(ServerName serverName) throws IOException {
        throw new UnsupportedOperationException("compactRegionServer(ServerName serverName)");
    }

    @Override
    public void majorCompactRegionServer(ServerName serverName) throws IOException {
        throw new UnsupportedOperationException("majorCompactRegionServer(ServerName serverName)");
    }

    @Override
    public void move(byte[] encodedRegionName) throws IOException {
        throw new UnsupportedOperationException("move(byte[] encodedRegionName)");
    }

    @Override
    public void move(byte[] encodedRegionName, ServerName destServerName) throws IOException {
        throw new UnsupportedOperationException("move(byte[] encodedRegionName, ServerName destServerName)");
    }

    @Override
    public void unassign(byte[] regionName) throws IOException {
        throw new UnsupportedOperationException("unassign(byte[] regionName)");
    }

    @Override
    public boolean balancerSwitch(boolean onOrOff, boolean synchronous) throws IOException {
        throw new UnsupportedOperationException("balancerSwitch(boolean onOrOff, boolean synchronous)");
    }

    @Override
    public BalanceResponse balance(BalanceRequest request) throws IOException {
        throw new UnsupportedOperationException("balance(BalanceRequest request)");
    }

    @Override
    public CacheEvictionStats clearBlockCache(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("clearBlockCache(TableName tableName)");
    }

    @Override
    public boolean normalize(NormalizeTableFilterParams ntfp) throws IOException {
        throw new UnsupportedOperationException("normalize(NormalizeTableFilterParams ntfp)");
    }

    @Override
    public boolean normalizerSwitch(boolean on) throws IOException {
        throw new UnsupportedOperationException("normalizerSwitch(boolean on)");
    }

    @Override
    public boolean catalogJanitorSwitch(boolean onOrOff) throws IOException {
        throw new UnsupportedOperationException("catalogJanitorSwitch(boolean onOrOff)");
    }

    @Override
    public int runCatalogJanitor() throws IOException {
        throw new UnsupportedOperationException("runCatalogJanitor()");
    }

    @Override
    public boolean cleanerChoreSwitch(boolean onOrOff) throws IOException {
        throw new UnsupportedOperationException("cleanerChoreSwitch(boolean onOrOff)");
    }

    @Override
    public boolean runCleanerChore() throws IOException {
        throw new UnsupportedOperationException("runCleanerChore()");
    }

    @Override
    public boolean isCleanerChoreEnabled() throws IOException {
        throw new UnsupportedOperationException("isCleanerChoreEnabled()");
    }

    @Override
    public Future<Void> mergeRegionsAsync(byte[][] nameofRegionsToMerge, boolean forcible) throws IOException {
        throw new UnsupportedOperationException("mergeRegionsAsync(byte[][] nameofRegionsToMerge, boolean forcible)");
    }

    @Override
    public Future<Void> splitRegionAsync(byte[] regionName) throws IOException {
        throw new UnsupportedOperationException("splitRegionAsync(byte[] regionName)");
    }

    @Override
    public Future<Void> splitRegionAsync(byte[] regionName, byte[] splitPoint) throws IOException {
        throw new UnsupportedOperationException("splitRegionAsync(byte[] regionName, byte[] splitPoint)");
    }

    @Override
    public Future<Void> modifyTableAsync(TableDescriptor td, boolean reopenRegions) throws IOException {
        throw new UnsupportedOperationException("modifyTableAsync(TableDescriptor td, boolean reopenRegions)");
    }

    @Override
    public Future<Void> modifyTableStoreFileTrackerAsync(TableName tableName, String dstSFT) throws IOException {
        throw new UnsupportedOperationException("modifyTableStoreFileTrackerAsync(TableName tableName, String dstSFT)");
    }

    @Override
    public boolean isMasterInMaintenanceMode() throws IOException {
        throw new UnsupportedOperationException("isMasterInMaintenanceMode()");
    }

    @Override
    public ClusterMetrics getClusterMetrics(EnumSet<ClusterMetrics.Option> options) throws IOException {
        throw new UnsupportedOperationException("getClusterMetrics(EnumSet<ClusterMetrics.Option> options)");
    }

    @Override
    public List<RegionMetrics> getRegionMetrics(ServerName serverName, TableName tableName) throws IOException {
        throw new UnsupportedOperationException("getRegionMetrics(ServerName serverName, TableName tableName)");
    }

    @Override
    public Future<Void> createNamespaceAsync(NamespaceDescriptor descriptor) throws IOException {
        throw new UnsupportedOperationException("createNamespaceAsync(NamespaceDescriptor descriptor)");
    }

    @Override
    public Future<Void> modifyNamespaceAsync(NamespaceDescriptor descriptor) throws IOException {
        throw new UnsupportedOperationException("modifyNamespaceAsync(NamespaceDescriptor descriptor)");
    }

    @Override
    public Future<Void> deleteNamespaceAsync(String name) throws IOException {
        throw new UnsupportedOperationException("deleteNamespaceAsync(String name)");
    }

    @Override
    public String[] listNamespaces() throws IOException {
        throw new UnsupportedOperationException("listNamespaces()");
    }

    @Override
    public List<TableDescriptor> listTableDescriptorsByNamespace(byte[] name) throws IOException {
        throw new UnsupportedOperationException("listTableDescriptorsByNamespace(byte[] name)");
    }

    @Override
    public List<RegionInfo> getRegions(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("getRegions(TableName tableName)");
    }

    @Override
    public List<RegionInfo> getRegions(ServerName serverName) throws IOException {
        throw new UnsupportedOperationException("getRegions(ServerName serverName)");
    }

    @Override
    public String getProcedures() throws IOException {
        throw new UnsupportedOperationException("getProcedures()");
    }

    @Override
    public String getLocks() throws IOException {
        throw new UnsupportedOperationException("getLocks()");
    }

    @Override
    public CompactionState getCompactionState(TableName tableName, CompactType compactType) throws IOException {
        throw new UnsupportedOperationException("getCompactionState(TableName tableName, CompactType compactType) ");
    }

    @Override
    public CompactionState getCompactionState(TableName tableName)
            throws IOException {
        throw new UnsupportedOperationException("getCompactionState(TableName tableName)");
    }

    @Override
    public void snapshot(SnapshotDescription snapshot) throws IOException, SnapshotCreationException, IllegalArgumentException {
        throw new UnsupportedOperationException("snapshot(SnapshotDescription snapshot)");
    }

    @Override
    public Future<Void> snapshotAsync(SnapshotDescription snapshot) throws IOException, SnapshotCreationException {
        throw new UnsupportedOperationException("snapshotAsync(SnapshotDescription snapshot)");
    }

    @Override
    public boolean isSnapshotFinished(SnapshotDescription snapshot) throws IOException, HBaseSnapshotException, UnknownSnapshotException {
        throw new UnsupportedOperationException("isSnapshotFinished(SnapshotDescription snapshot)");
    }

    @Override
    public Future<Void> restoreSnapshotAsync(String snapshotName) throws IOException, RestoreSnapshotException {
        throw new UnsupportedOperationException("restoreSnapshotAsync(String snapshotName)");
    }

    @Override
    public void restoreSnapshot(String snapshotName, boolean takeFailSafeSnapshot, boolean restoreAcl) throws IOException, RestoreSnapshotException {
        throw new UnsupportedOperationException("restoreSnapshot(String snapshotName, boolean takeFailSafeSnapshot, boolean restoreAcl)");
    }

    @Override
    public Future<Void> cloneSnapshotAsync(String snapshotName, TableName tableName, boolean restoreAcl, String customSFT) 
                                            throws IOException, TableExistsException, RestoreSnapshotException {
        throw new UnsupportedOperationException("cloneSnapshotAsync(String snapshotName, TableName tableName, boolean restoreAcl, String customSFT)");
    }

    @Override
    public byte[] execProcedureWithReturn(String signature, String instance, Map<String, String> props) throws IOException {
        throw new UnsupportedOperationException("execProcedureWithReturn(String signature, String instance, Map<String, String> props)");
    }

    @Override
    public List<SnapshotDescription> listTableSnapshots(String tableNameRegex, String snapshotNameRegex) throws IOException {
        throw new UnsupportedOperationException("listTableSnapshots(String tableNameRegex, String snapshotNameRegex)");
    }

    @Override
    public List<SnapshotDescription> listTableSnapshots(Pattern tableNamePattern, Pattern snapshotNamePattern) throws IOException {
        throw new UnsupportedOperationException("listTableSnapshots(Pattern tableNamePattern, Pattern snapshotNamePattern)");
    }

    @Override
    public void deleteTableSnapshots(String tableNameRegex, String snapshotNameRegex) throws IOException {
        throw new UnsupportedOperationException("deleteTableSnapshots(String tableNameRegex, String snapshotNameRegex)");
    }

    @Override
    public void deleteTableSnapshots(Pattern tableNamePattern, Pattern snapshotNamePattern) throws IOException {
        throw new UnsupportedOperationException("deleteTableSnapshots(Pattern tableNamePattern, Pattern snapshotNamePattern)");
    }

    @Override
    public List<QuotaSettings> getQuota(QuotaFilter filter) throws IOException {
        throw new UnsupportedOperationException("getQuota(QuotaFilter filter)");
    }

    @Override
    public boolean splitSwitch(boolean enabled, boolean synchronous) throws IOException {
        throw new UnsupportedOperationException("splitSwitch(boolean enabled, boolean synchronous)");
    }

    @Override
    public boolean mergeSwitch(boolean enabled, boolean synchronous) throws IOException {
        throw new UnsupportedOperationException("mergeSwitch(boolean enabled, boolean synchronous)");
    }

    @Override
    public boolean isSplitEnabled() throws IOException {
        throw new UnsupportedOperationException("isSplitEnabled()");
    }

    @Override
    public boolean isMergeEnabled() throws IOException {
        throw new UnsupportedOperationException("isMergeEnabled()");
    }

    @Override
    public Future<Void> addReplicationPeerAsync(String peerId, ReplicationPeerConfig peerConfig, boolean enabled) throws IOException {
        throw new UnsupportedOperationException("addReplicationPeerAsync(String peerId, ReplicationPeerConfig peerConfig, boolean enabled)");
    }

    @Override
    public Future<Void> removeReplicationPeerAsync(String peerId) throws IOException {
        throw new UnsupportedOperationException("removeReplicationPeerAsync(String peerId)");
    }

    @Override
    public Future<Void> enableReplicationPeerAsync(String peerId) throws IOException {
        throw new UnsupportedOperationException("enableReplicationPeerAsync(String peerId)");
    }

    @Override
    public Future<Void> disableReplicationPeerAsync(String peerId) throws IOException {
        throw new UnsupportedOperationException("disableReplicationPeerAsync(String peerId)");
    }

    @Override
    public ReplicationPeerConfig getReplicationPeerConfig(String peerId) throws IOException {
        throw new UnsupportedOperationException("ReplicationPeerConfig getReplicationPeerConfig(String peerId)");
    }

    @Override
    public Future<Void> updateReplicationPeerConfigAsync(String peerId, ReplicationPeerConfig peerConfig) throws IOException {
        throw new UnsupportedOperationException("updateReplicationPeerConfigAsync(String peerId, ReplicationPeerConfig peerConfig)");
    }

    @Override
    public List<ReplicationPeerDescription> listReplicationPeers() throws IOException {
        throw new UnsupportedOperationException("listReplicationPeers()");
    }

    @Override
    public List<ReplicationPeerDescription> listReplicationPeers(Pattern pattern) throws IOException {
        throw new UnsupportedOperationException("listReplicationPeers(Pattern pattern)");
    }

    @Override
    public void decommissionRegionServers(List<ServerName> servers, boolean offload) throws IOException {
        throw new UnsupportedOperationException("decommissionRegionServers(List<ServerName> servers, boolean offload)");
    }

    @Override
    public List<ServerName> listDecommissionedRegionServers() throws IOException {
        throw new UnsupportedOperationException("listDecommissionedRegionServers()");
    }

    @Override
    public void recommissionRegionServer(ServerName server, List<byte[]> encodedRegionNames) throws IOException {
        throw new UnsupportedOperationException("recommissionRegionServer(ServerName server, List<byte[]> encodedRegionNames)");
    }

    @Override
    public List<TableCFs> listReplicatedTableCFs() throws IOException {
        throw new UnsupportedOperationException("listReplicatedTableCFs()");
    }

    @Override
    public void enableTableReplication(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("enableTableReplication(TableName tableName)");
    }

    @Override
    public void disableTableReplication(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("disableTableReplication(TableName tableName)");
    }

    @Override
    public void clearCompactionQueues(ServerName serverName, Set<String> queues) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("clearCompactionQueues(ServerName serverName, Set<String> queues)");
    }

    @Override
    public List<ServerName> clearDeadServers(List<ServerName> servers) throws IOException {
        throw new UnsupportedOperationException("clearDeadServers(List<ServerName> servers)");
    }

    @Override
    public void cloneTableSchema(TableName tableName, TableName newTableName, boolean preserveSplits) throws IOException {
        throw new UnsupportedOperationException("cloneTableSchema(TableName tableName, TableName newTableName, boolean preserveSplits)");
    }

    @Override
    public boolean switchRpcThrottle(boolean enable) throws IOException {
        throw new UnsupportedOperationException("switchRpcThrottle(boolean enable) ");
    }

    @Override
    public boolean isRpcThrottleEnabled() throws IOException {
        throw new UnsupportedOperationException("isRpcThrottleEnabled()");
    }

    @Override
    public boolean exceedThrottleQuotaSwitch(boolean enable) throws IOException {
        throw new UnsupportedOperationException("exceedThrottleQuotaSwitch(boolean enable)");
    }

    @Override
    public Map<TableName, Long> getSpaceQuotaTableSizes() throws IOException {
        throw new UnsupportedOperationException("getSpaceQuotaTableSizes()");
    }

    @Override
    public Map<TableName, ? extends SpaceQuotaSnapshotView> getRegionServerSpaceQuotaSnapshots(ServerName serverName) throws IOException {
        throw new UnsupportedOperationException("getRegionServerSpaceQuotaSnapshots(ServerName serverName)");
    }

    @Override
    public SpaceQuotaSnapshotView getCurrentSpaceQuotaSnapshot(String namespace) throws IOException {
        throw new UnsupportedOperationException("getCurrentSpaceQuotaSnapshot(String namespace)");
    }

    @Override
    public SpaceQuotaSnapshotView getCurrentSpaceQuotaSnapshot(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("getCurrentSpaceQuotaSnapshot(TableName tableName)");
    }

    @Override
    public void grant(UserPermission userPermission, boolean mergeExistingPermissions) throws IOException {
        throw new UnsupportedOperationException("grant(UserPermission userPermission, boolean mergeExistingPermissions)");
    }

    @Override
    public void revoke(UserPermission userPermission) throws IOException {
        throw new UnsupportedOperationException("revoke(UserPermission userPermission)");
    }

    @Override
    public List<UserPermission> getUserPermissions(GetUserPermissionsRequest getUserPermissionsRequest) throws IOException {
        throw new UnsupportedOperationException("getUserPermissions(GetUserPermissionsRequest getUserPermissionsRequest)");
    }

    @Override
    public List<Boolean> hasUserPermissions(String userName, List<Permission> permissions) throws IOException {
        throw new UnsupportedOperationException("hasUserPermissions(String userName, List<Permission> permissions)");
    }

    @Override
    public boolean snapshotCleanupSwitch(boolean on, boolean synchronous) throws IOException {
        throw new UnsupportedOperationException("snapshotCleanupSwitch(boolean on, boolean synchronous)");
    }

    @Override
    public boolean isSnapshotCleanupEnabled() throws IOException {
        throw new UnsupportedOperationException("isSnapshotCleanupEnabled()");
    }

    @Override
    public List<Boolean> clearSlowLogResponses(Set<ServerName> serverNames) throws IOException {
        throw new UnsupportedOperationException("clearSlowLogResponses(Set<ServerName> serverNames)");
    }

    @Override
    public List<LogEntry> getLogEntries(Set<ServerName> serverNames, String logType, ServerType serverType, int limit, Map<String, Object> filterParams) throws IOException {
        throw new UnsupportedOperationException("getLogEntries(Set<ServerName> serverNames, String logType," +
                "ServerType serverType, int limit, Map<String, Object> filterParams)");
    }

    @Override
    public void flushMasterStore() throws IOException {
        throw new UnsupportedOperationException("flushMasterStore()");
    }

    @Override
    public int getOperationTimeout() {
        throw new UnsupportedOperationException("getOperationTimeout()");
    }

    @Override
    public void abort(String why, Throwable e) {
        aborted = true;
    }

    @Override
    public boolean isAborted() {
        return aborted;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean tableExists(TableName tableName) throws IOException {
        return adaptor.getTable(tableName.getNameAsString()) != null;
    }

    @Override
    public HTableDescriptor[] listTables() throws IOException {
        List<String> names = adaptor.listTable();
        return getTableDescriptors(names);
    }

    @Override
    public HTableDescriptor[] listTables(Pattern pattern) throws IOException {
        List<String> names = adaptor.listTable();

        List<HTableDescriptor> tableDescriptors = new ArrayList<HTableDescriptor>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (pattern.matcher(name).matches()) {
                HTableDescriptor descriptor = getTableDescriptor(TableName.valueOf(name));
                tableDescriptors.add(descriptor);
            }
        }

        HTableDescriptor[] tableDescriptorArray = new HTableDescriptor[tableDescriptors.size()];
        return tableDescriptors.toArray(tableDescriptorArray);
    }

    @Override
    public HTableDescriptor[] listTables(String regex) throws IOException {
        return listTables(Pattern.compile(regex));
    }

    @Override
    public HTableDescriptor[] listTables(Pattern pattern, boolean includeSysTables) throws IOException {
        return listTables(pattern);
    }

    @Override
    public HTableDescriptor[] listTables(String regex, boolean includeSysTables) throws IOException {
        return listTables(Pattern.compile(regex));
    }

    @Override
    public TableName[] listTableNames() throws IOException {
        List<String> names = adaptor.listTable();

        TableName[] tableNameArray = new TableName[names.size()];
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            tableNameArray[i] = TableName.valueOf(name);
        }

        return tableNameArray;
    }

    @Override
    public TableName[] listTableNames(Pattern pattern) throws IOException {
        List<String> names = adaptor.listTable();

        List<TableName> tableNameList = new ArrayList<TableName>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (pattern.matcher(name).matches()) {
                tableNameList.add(TableName.valueOf(name));
            }
        }

        TableName[] tableNameArray = new TableName[tableNameList.size()];
        return tableNameList.toArray(tableNameArray);
    }

    @Override
    public TableName[] listTableNames(String regex) throws IOException {
        return listTableNames(Pattern.compile(regex));
    }

    @Override
    public TableName[] listTableNames(Pattern pattern, boolean includeSysTables) throws IOException {
        return listTableNames(pattern);
    }

    @Override
    public TableName[] listTableNames(String regex, boolean includeSysTables) throws IOException {
        return listTableNames(Pattern.compile(regex));
    }

    @Override
    public HTableDescriptor getTableDescriptor(TableName tableName) throws TableNotFoundException, IOException {
        checkNotNull(tableName, "tableName should not be null");

        HTableDescriptor descriptor =  adaptor.getTable(tableName.getNameAsString());
        if (descriptor == null) {
            throw new TableNotFoundException(tableName);
        }
        return descriptor;
    }

    public void createTable(HTableDescriptor desc) throws IOException {
        checkNotNull(desc, "desc should not be null");

        if (desc.getFamilies().size() > Constants.MAX_COLUMN_FAMILY_NUM) {
            throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
        }

        CompressType type = CompressType.DEFAULT;
        int maxVersions = TableStorageConstants.DEFAULT_TABLE_MAX_VERSIONS;
        int ttl = TableStorageConstants.DEFAULT_LIVE_TIME;
        for (HColumnDescriptor descriptor : desc.getFamilies()) {
            if (!descriptor.getNameAsString().equals(Constants.DEFAULT_FAMILY)) {
                throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
            }
            type = TablestorageConvertor.toCompressionType(descriptor.getCompressionType());
            maxVersions = descriptor.getMaxVersions();

            String descriptorValue = descriptor.getValue(HColumnDescriptor.TTL);
            ttl = TableStorageConstants.DEFAULT_LIVE_TIME;
            if (descriptorValue != null) {
                ttl = Integer.parseInt(descriptorValue);
            }
            if (ttl == HConstants.FOREVER) {
                ttl = TableStorageConstants.FORERVER_LIVE_TIME;
            }
        }

        adaptor.createTable(desc.getNameAsString(), type, maxVersions, ttl);
    }

    public void createTable(HTableDescriptor desc, byte[] startKey, byte[] endKey, int numRegions) throws IOException {
        throw new UnsupportedOperationException(
                "createTable(HTableDescriptor desc, byte[] startKey, byte[] endKey, int numRegions)");
    }

    public void createTable(HTableDescriptor desc, byte[][] splitKeys) throws IOException {
        throw new UnsupportedOperationException("createTable(HTableDescriptor desc, byte[][] splitKeys)");

    }

    public void createTableAsync(HTableDescriptor desc, byte[][] splitKeys) throws IOException {
        throw new UnsupportedOperationException("createTableAsync(HTableDescriptor desc, byte[][] splitKeys)");
    }

    @Override
    public void deleteTable(TableName tableName) throws IOException {
        checkNotNull(tableName, "tableName should not be null");

        adaptor.dropTable(tableName.getNameAsString());
    }

    @Override
    public HTableDescriptor[] deleteTables(String regex) throws IOException {
        return deleteTables(Pattern.compile(regex));
    }

    @Override
    public HTableDescriptor[] deleteTables(Pattern pattern) throws IOException {
        List<String> names = adaptor.listTable();

        List<HTableDescriptor> tableDescriptors = new ArrayList<HTableDescriptor>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            if (pattern.matcher(name).matches()) {
                TableName tableName = TableName.valueOf(name);
                tableDescriptors.add(getTableDescriptor(tableName));
                deleteTable(tableName);
            }
        }

        HTableDescriptor[] tableDescriptorArray = new HTableDescriptor[tableDescriptors.size()];
        return tableDescriptors.toArray(tableDescriptorArray);
    }

    @Override
    public void truncateTable(TableName tableName, boolean preserveSplits) throws IOException {
        throw new UnsupportedOperationException("truncateTable(TableName tableName, boolean preserveSplits)");
    }

    @Override
    public void enableTable(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("enableTable(TableName tableName)");
    }

    @Override
    public Future<Void> enableTableAsync(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("enableTable(TableName tableName)");
    }

    @Override
    public HTableDescriptor[] enableTables(String regex) throws IOException {
        throw new UnsupportedOperationException("enableTable(String regex)");
    }

    @Override
    public HTableDescriptor[] enableTables(Pattern pattern) throws IOException {
        throw new UnsupportedOperationException("enableTable(Pattern pattern)");
    }

    @Override
    public Future<Void> disableTableAsync(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("disableTable(TableName tableName)");
    }

    @Override
    public void disableTable(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("disableTable(TableName tableName)");
    }

    @Override
    public HTableDescriptor[] disableTables(String regex) throws IOException {
        throw new UnsupportedOperationException("disableTable(String regex)");
    }

    @Override
    public HTableDescriptor[] disableTables(Pattern pattern) throws IOException {
        throw new UnsupportedOperationException("disableTable(Pattern pattern)");
    }

    @Override
    public boolean isTableEnabled(TableName tableName) throws IOException {
        return false;
    }

    @Override
    public boolean isTableDisabled(TableName tableName) throws IOException {
        return false;
    }

    @Override
    public boolean isTableAvailable(TableName tableName) throws IOException {
        checkNotNull(tableName, "tableName should not be null");

        return adaptor.isTableAvailable(tableName.getNameAsString());
    }

    @Override
    public boolean isTableAvailable(TableName tableName, byte[][] splitKeys) throws IOException {
        checkNotNull(tableName, "tableName should not be null");

        return isTableAvailable(tableName);
    }

    @Override
    public Pair<Integer, Integer> getAlterStatus(TableName tableName) throws IOException {
        return null;
    }

    @Override
    public Pair<Integer, Integer> getAlterStatus(byte[] tableName) throws IOException {
        return null;
    }

    public void addColumn(TableName tableName, HColumnDescriptor column) throws IOException {
        throw new UnsupportedOperationException("addColumn(TableName tableName, HColumnDescriptor column)");
    }

    @Override
    public void deleteColumn(TableName tableName, byte[] columnName) throws IOException {
        throw new UnsupportedOperationException("deleteColumn(TableName tableName, byte[] columnName)");
    }

    public void modifyColumn(TableName tableName, HColumnDescriptor descriptor) throws IOException {
        checkNotNull(tableName, "tableName should not be null");
        checkNotNull(descriptor, "descriptor should not be null");

        if (!descriptor.getNameAsString().equals(Constants.DEFAULT_FAMILY)) {
            throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
        }
        CompressType type = TablestorageConvertor.toCompressionType(descriptor.getCompressionType());
        int maxVersions = descriptor.getMaxVersions();
        String descriptorValue = descriptor.getValue(HColumnDescriptor.TTL);
        int ttl = TableStorageConstants.DEFAULT_LIVE_TIME;
        if (descriptorValue != null) {
            ttl = Integer.parseInt(descriptorValue);
        }
        if (ttl == HConstants.FOREVER) {
            ttl = TableStorageConstants.FORERVER_LIVE_TIME;
        }

        adaptor.updateTable(tableName.getNameAsString(), type, maxVersions, ttl);
    }

    @Override
    public Configuration getConfiguration() {
        return connection.getConfiguration();
    }

    @Override
    public void close() throws IOException {
        adaptor.close();
    }

    @Override
    public HTableDescriptor[] getTableDescriptorsByTableName(List<TableName> tableNames) throws IOException {
        if (tableNames == null) {
            return new HTableDescriptor[0];
        }

        HTableDescriptor[] tableDescriptors = new HTableDescriptor[tableNames.size()];
        for (int i = 0; i < tableNames.size(); i++) {
            TableName tableName = tableNames.get(i);
            tableDescriptors[i] = getTableDescriptor(tableName);
        }
        return tableDescriptors;
    }

    @Override
    public HTableDescriptor[] getTableDescriptors(List<String> names) throws IOException {
        if (names == null) {
            return new HTableDescriptor[0];
        }

        HTableDescriptor[] tableDescriptors = new HTableDescriptor[names.size()];
        for (int i = 0; i < names.size(); i++) {
            TableName tableName = TableName.valueOf(names.get(i));
            tableDescriptors[i] = getTableDescriptor(tableName);
        }
        return tableDescriptors;
    }

    @Override
    public void closeRegion(String regionname, String serverName) throws IOException {
        throw new UnsupportedOperationException("closeRegion(String regionname, String serverName)");
    }

    @Override
    public void closeRegion(byte[] regionname, String serverName) throws IOException {
        throw new UnsupportedOperationException("closeRegion(byte[] regionname, String serverName)");
    }

    @Override
    public void closeRegion(ServerName sn, HRegionInfo hri) throws IOException {
        throw new UnsupportedOperationException("closeRegion(ServerName sn, HRegionInfo hri)");
    }

    @Override
    public boolean closeRegionWithEncodedRegionName(String encodedRegionName, String serverName) throws IOException {
        throw new UnsupportedOperationException(
                "closeRegionWithEncodedRegionName(String encodedRegionName, String serverName)");
    }

    @Override
    public List<HRegionInfo> getOnlineRegions(ServerName sn) throws IOException {
        throw new UnsupportedOperationException("getOnlineRegions(ServerName sn)");
    }

    @Override
    public void flush(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("flush(TableName tableName)");
    }

    @Override
    public void flushRegion(byte[] regionName) throws IOException {
        throw new UnsupportedOperationException("flushRegion(byte[] regionName)");

    }

    @Override
    public void compact(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("compact(TableName tableName)");

    }

    @Override
    public void compact(TableName tableName, byte[] columnFamily) throws IOException {
        throw new UnsupportedOperationException("compact(TableName tableName, byte[] columnFamily)");

    }

    @Override
    public void compactRegion(byte[] regionName) throws IOException {
        throw new UnsupportedOperationException("compactRegion(byte[] regionName)");

    }

    @Override
    public void compactRegion(byte[] regionName, byte[] columnFamily) throws IOException {
        throw new UnsupportedOperationException("compactRegion(byte[] regionName, byte[] columnFamily)");

    }

    @Override
    public void majorCompact(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("majorCompact(TableName tableName)");

    }

    @Override
    public void majorCompact(TableName tableName, byte[] columnFamily) throws IOException {
        throw new UnsupportedOperationException("majorCompact(TableName tableName, byte[] columnFamily)");

    }

    @Override
    public void majorCompactRegion(byte[] regionName) throws IOException {
        throw new UnsupportedOperationException("majorCompactRegion(byte[] regionName)");

    }

    @Override
    public void majorCompactRegion(byte[] regionName, byte[] columnFamily) throws IOException {
        throw new UnsupportedOperationException("majorCompactRegion(byte[] regionName, byte[] columnFamily)");

    }

    @Override
    public void compactRegionServer(ServerName sn, boolean major) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("compactRegionServer(ServerName sn, boolean major)");

    }

    @Override
    public void move(byte[] encodedRegionName, byte[] destServerName) throws IOException {
        throw new UnsupportedOperationException("move(byte[] encodedRegionName, byte[] destServerName)");

    }

    @Override
    public void assign(byte[] regionName) throws IOException {
        throw new UnsupportedOperationException("assign(byte[] regionName)");

    }

    @Override
    public void unassign(byte[] regionName, boolean force) throws IOException {
        throw new UnsupportedOperationException("unassign(byte[] regionName, boolean force)");

    }

    @Override
    public void offline(byte[] regionName) throws IOException {
        throw new UnsupportedOperationException("offline(byte[] regionName)");
    }

    @Override
    public boolean setBalancerRunning(boolean on, boolean synchronous) throws IOException {
        throw new UnsupportedOperationException("setBalancerRunning(boolean on, boolean synchronous)");
    }

    @Override
    public boolean balancer() throws IOException {
        throw new UnsupportedOperationException("balancer()");
    }

    @Override
    public boolean isBalancerEnabled() throws IOException {
        return false;
    }

    @Override
    public boolean normalize() throws IOException {
        throw new UnsupportedOperationException("normalize()");
    }

    @Override
    public boolean isNormalizerEnabled() throws IOException {
        return false;
    }

    @Override
    public boolean setNormalizerRunning(boolean on) throws IOException {
        throw new UnsupportedOperationException("setNormalizerRunning(boolean on)");
    }

    @Override
    public boolean enableCatalogJanitor(boolean enable) throws IOException {
        throw new UnsupportedOperationException("enableCatalogJanitor(boolean enable)");
    }

    @Override
    public int runCatalogScan() throws IOException {
        throw new UnsupportedOperationException("runCatalogScan()");
    }

    @Override
    public boolean isCatalogJanitorEnabled() throws IOException {
        return false;
    }

    @Override
    public void mergeRegions(byte[] nameOfRegionA, byte[] nameOfRegionB, boolean forcible) throws IOException {
        throw new UnsupportedOperationException(
                "mergeRegions(byte[] nameOfRegionA, byte[] nameOfRegionB, boolean forcible)");

    }

    @Override
    public void split(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("split(TableName tableName)");

    }

    @Override
    public void split(TableName tableName, byte[] splitPoint) throws IOException {
        throw new UnsupportedOperationException("split(TableName tableName, byte[] splitPoint)");

    }

    @Override
    public void splitRegion(byte[] regionName) throws IOException {
        throw new UnsupportedOperationException("splitRegion(byte[] regionName)");

    }

    @Override
    public void splitRegion(byte[] regionName, byte[] splitPoint) throws IOException {
        throw new UnsupportedOperationException("splitRegion(byte[] regionName, byte[] splitPoint)");

    }

    public void modifyTable(TableName tableName, HTableDescriptor htd) throws IOException {
        throw new UnsupportedOperationException("modifyTable(TableName tableName, HTableDescriptor htd)");
    }

    @Override
    public void shutdown() throws IOException {
        throw new UnsupportedOperationException("shutdown()");
    }

    @Override
    public void stopMaster() throws IOException {
        throw new UnsupportedOperationException("stopMaster()");
    }

    @Override
    public void stopRegionServer(String hostnamePort) throws IOException {
        throw new UnsupportedOperationException("stopRegionServer(String hostnamePort)");
    }

    @Override
    public ClusterStatus getClusterStatus() throws IOException {
        throw new UnsupportedOperationException("getClusterStatus()");
    }

    @Override
    public void createNamespace(NamespaceDescriptor descriptor) throws IOException {
        throw new UnsupportedOperationException("createNamespace(NamespaceDescriptor descriptor)");
    }

    @Override
    public void modifyNamespace(NamespaceDescriptor descriptor) throws IOException {
        throw new UnsupportedOperationException("modifyNamespace(NamespaceDescriptor descriptor)");
    }

    @Override
    public void deleteNamespace(String name) throws IOException {
        throw new UnsupportedOperationException("deleteNamespace(String name)");
    }

    @Override
    public NamespaceDescriptor getNamespaceDescriptor(String name) throws IOException {
        throw new UnsupportedOperationException("getNamespaceDescriptor(String name)");
    }

    @Override
    public NamespaceDescriptor[] listNamespaceDescriptors() throws IOException {
        throw new UnsupportedOperationException("listNamespaceDescriptors()");
    }

    @Override
    public HTableDescriptor[] listTableDescriptorsByNamespace(String name) throws IOException {
        throw new UnsupportedOperationException("listTableDescriptorsByNamespace(String name)");
    }

    @Override
    public TableName[] listTableNamesByNamespace(String name) throws IOException {
        throw new UnsupportedOperationException("listTableNamesByNamespace(String name)");
    }

    @Override
    public List<HRegionInfo> getTableRegions(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("getTableRegions(TableName tableName)");
    }

    @Override
    public boolean abortProcedure(long procId, boolean mayInterruptIfRunning) throws IOException {
        throw new UnsupportedOperationException("abortProcedure(long procId, boolean mayInterruptIfRunning)");
    }

    @Override
    public Future<Boolean> abortProcedureAsync(long procId, boolean mayInterruptIfRunning) throws IOException {
        throw new UnsupportedOperationException("abortProcedureAsync(long procId, boolean mayInterruptIfRunning)");
    }

    @Override
    public void rollWALWriter(ServerName serverName) throws IOException {
        throw new UnsupportedOperationException("rollWALWriter(ServerName serverName)");
    }

    @Override
    public String[] getMasterCoprocessors() throws IOException {
        throw new UnsupportedOperationException("getMasterCoprocessors()");
    }

    @Override
    public CompactionState getCompactionStateForRegion(byte[] regionName)
            throws IOException {
        throw new UnsupportedOperationException("getCompactionStateForRegion(byte[] regionName)");
    }

    @Override
    public long getLastMajorCompactionTimestamp(TableName tableName) throws IOException {
        throw new UnsupportedOperationException("getLastMajorCompactionTimestamp(TableName tableName)");
    }

    @Override
    public long getLastMajorCompactionTimestampForRegion(byte[] regionName) throws IOException {
        throw new UnsupportedOperationException("getLastMajorCompactionTimestampForRegion(byte[] regionName)");
    }

    @Override
    public void snapshot(String snapshotName, TableName tableName) throws IOException, IllegalArgumentException {
        throw new UnsupportedOperationException("snapshot(String snapshotName, TableName tableName)");
    }

    @Override
    public void snapshot(byte[] snapshotName, TableName tableName) throws IOException, IllegalArgumentException {
        throw new UnsupportedOperationException("snapshot(byte[] snapshotName, TableName tableName)");
    }

    @Override
    public void restoreSnapshot(byte[] snapshotName) throws IOException {
        throw new UnsupportedOperationException("restoreSnapshot(byte[] snapshotName)");
    }

    @Override
    public void restoreSnapshot(String snapshotName) throws IOException {
        throw new UnsupportedOperationException("restoreSnapshot(String snapshotName)");
    }

    @Override
    public void restoreSnapshot(byte[] snapshotName, boolean takeFailSafeSnapshot) throws IOException {
        throw new UnsupportedOperationException("restoreSnapshot(byte[] snapshotName, boolean takeFailSafeSnapshot)");
    }

    @Override
    public void restoreSnapshot(String snapshotName, boolean takeFailSafeSnapshot) throws IOException {
        throw new UnsupportedOperationException("restoreSnapshot(String snapshotName, boolean takeFailSafeSnapshot)");
    }

    @Override
    public void cloneSnapshot(byte[] snapshotName, TableName tableName) throws IOException {
        throw new UnsupportedOperationException("cloneSnapshot(byte[] snapshotName, TableName tableName)");
    }

    @Override
    public void cloneSnapshot(String snapshotName, TableName tableName) throws IOException {
        throw new UnsupportedOperationException("cloneSnapshot(String snapshotName, TableName tableName)");
    }

    @Override
    public void execProcedure(String signature, String instance, Map<String, String> props) throws IOException {
        throw new UnsupportedOperationException(
                "execProcedure(String signature, String instance, Map<String, String> props)");
    }

    @Override
    public byte[] execProcedureWithRet(String signature, String instance, Map<String, String> props)
            throws IOException {
        throw new UnsupportedOperationException(
                "execProcedureWithRet(String signature, String instance, Map<String, String> props)");
    }

    @Override
    public boolean isProcedureFinished(String signature, String instance, Map<String, String> props)
            throws IOException {
        throw new UnsupportedOperationException(
                "isProcedureFinished(String signature, String instance, Map<String, String> props)");
    }

    @Override
    public List<SnapshotDescription> listSnapshots() throws IOException {
        throw new UnsupportedOperationException("listSnapshots()");
    }

    @Override
    public List<SnapshotDescription> listSnapshots(String regex) throws IOException {
        throw new UnsupportedOperationException("listSnapshots(String regex)");
    }

    @Override
    public List<SnapshotDescription> listSnapshots(Pattern pattern) throws IOException {
        throw new UnsupportedOperationException("listSnapshots(Pattern pattern)");
    }

    @Override
    public void deleteSnapshot(byte[] snapshotName) throws IOException {
        throw new UnsupportedOperationException("deleteSnapshot(byte[] snapshotName)");
    }

    @Override
    public void deleteSnapshot(String snapshotName) throws IOException {
        throw new UnsupportedOperationException("deleteSnapshot(String snapshotName)");
    }

    @Override
    public void deleteSnapshots(String regex) throws IOException {
        throw new UnsupportedOperationException("deleteSnapshots(String regex)");
    }

    @Override
    public void deleteSnapshots(Pattern pattern) throws IOException {
        throw new UnsupportedOperationException("deleteSnapshot(Pattern pattern)");
    }

    @Override
    public void setQuota(QuotaSettings quota) throws IOException {
        throw new UnsupportedOperationException("setQuota(QuotaSettings quota)");
    }

    @Override
    public QuotaRetriever getQuotaRetriever(QuotaFilter filter) throws IOException {
        throw new UnsupportedOperationException("getQuotaRetriever(QuotaFilter filter)");
    }

    @Override
    public CoprocessorRpcChannel coprocessorService() {
        throw new UnsupportedOperationException("coprocessorService()");
    }

    @Override
    public CoprocessorRpcChannel coprocessorService(ServerName sn) {
        throw new UnsupportedOperationException("coprocessorService(ServerName sn)");
    }

    @Override
    public void updateConfiguration(ServerName server) throws IOException {
        throw new UnsupportedOperationException("updateConfiguration(ServerName server)");
    }

    @Override
    public void updateConfiguration() throws IOException {
        throw new UnsupportedOperationException("updateConfiguration()");
    }

    @Override
    public int getMasterInfoPort() throws IOException {
        throw new UnsupportedOperationException("getMasterInfoPort()");
    }

    @Override
    public List<SecurityCapability> getSecurityCapabilities() throws IOException {
        throw new UnsupportedOperationException("getSecurityCapabilities()");
    }
}
