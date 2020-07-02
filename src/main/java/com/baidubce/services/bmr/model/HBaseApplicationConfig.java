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
package com.baidubce.services.bmr.model;

/**
 * Represent an HBase application.
 * <p>
 * An HBase application can be configured with properties about backup and restore.
 */
public class HBaseApplicationConfig extends ApplicationConfig {
    private static final String HBASE_APPLICATION = "hbase";
    private static final String BACKUP_ENABLED = "backupEnabled";
    private static final String BACKUP_LOCATION = "backupLocation";
    private static final String BACKUP_INTERVAL_IN_MINUTES = "backupIntervalInMinutes";
    private static final String BACKUP_START_DATETIME = "backupStartDateTime";
    private static final String RESTORE_ENABLED = "restoreEnabled";
    private static final String RESTORE_LOCATION = "restoreLocation";
    private static final String RESTORE_VERSION = "restoreVersion";

    public HBaseApplicationConfig() {
        this.setName(HBASE_APPLICATION);
    }

    /**
     * Configure the version of HBase.
     * The reference version is as follows:
     * <p>
     * image type |  image version | hbase version supported
     * hadoop    |    0.1.0       |    0.98.0
     * hadoop    |    0.1.0       |    0.98.0
     *
     * @param version The version of HBase.
     *
     * @return HBaseApplicationConfig
     */
    public HBaseApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }

    /**
     * Configure the switch of HBase backup. If backup is enabled, then
     * the properties of backup-location, backup-internal and backup start time should be set.
     *
     * @param backupEnabled The switch of backup. Turn it on by setting true.
     *
     * @return HBaseApplicationConfig
     */
    public HBaseApplicationConfig withBackupEnabled(boolean backupEnabled) {
        this.addProperty(BACKUP_ENABLED, backupEnabled);
        return this;
    }

    /**
     * Configure the BOS path for HBase backup.
     * This property must be set if the backupEnabled is set true.
     *
     * @param backupLocation The BOS path for backup.
     *
     * @return HBaseApplicationConfig
     */
    public HBaseApplicationConfig withBackupLocation(String backupLocation) {
        this.addProperty(BACKUP_LOCATION, backupLocation);
        return this;
    }

    /**
     * Configure the interval time for backup.
     * This property must be set if the backupEnabled is set true.
     *
     * @param minutes The number of minutes for backup interval time.
     *
     * @return HBaseApplicationConfig
     */
    public HBaseApplicationConfig withBackupIntervalInMinutes(int minutes) {
        this.addProperty(BACKUP_INTERVAL_IN_MINUTES, minutes);
        return this;
    }

    /**
     * Configure the start dateTime for backup.
     * This property must be set if the backupEnabled is set true.
     *
     * @param startDatetime The start date time for HBase backup.
     *
     * @return HBaseApplicationConfig
     */
    public HBaseApplicationConfig withBackupStartDatetime(String startDatetime) {
        this.addProperty(BACKUP_START_DATETIME, startDatetime);
        return this;
    }

    /**
     * Configure the switch of HBase restore. If HBase restore is enabled, then
     * the HBase of the newly cluster will restore from the configured restore location.
     * If restore is not enabled, then the HBase will be a purely new one.
     *
     * @param restoreEnabled The switch of restore. Turn it on by setting true.
     *
     * @return HBaseApplicationConfig
     */
    public HBaseApplicationConfig withRestoreEnabled(boolean restoreEnabled) {
        this.addProperty(RESTORE_ENABLED, restoreEnabled);
        return this;
    }

    /**
     * Configure the BOS path for HBase restore.
     * This property must be set if the restoreEnabled is set true.
     *
     * @param restoreLocation The BOS path for restore.
     *
     * @return HBaseApplicationConfig
     */
    public HBaseApplicationConfig withRestoreLocation(String restoreLocation) {
        this.addProperty(RESTORE_LOCATION, restoreLocation);
        return this;
    }

    /**
     * Configure the version of backup files for HBase restore.
     * This property must be set if the restoreEnabled is set true.
     *
     * @param restoreVersion The version of HBase backup files.
     *
     * @return HBaseApplicationConfig
     */
    public HBaseApplicationConfig withRestoreVersion(String restoreVersion) {
        this.addProperty(RESTORE_VERSION, restoreVersion);
        return this;
    }
}
