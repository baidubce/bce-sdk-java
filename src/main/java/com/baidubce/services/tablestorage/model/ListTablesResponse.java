/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.tablestorage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the output of a ListTables operation.
 */
public class ListTablesResponse extends AbstractTableStorageResponse {
    /**
     * The table info consists of attributes of table in TableStorage.
     */
    public static class TableInfo {
        private String tableName;
        private TableState tableState;
        private long tableVersion;

        /**
         * Get the table name.
         * @return The table name.
         */
        public String getTableName() {
            return tableName;
        }

        /**
         * Set the table name.
         * @param tableName The table name set to table info.
         */
        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        /**
         * Get the table state.
         *
         * @return The table state.
         */
        public TableState getTableState() {
            return tableState;
        }

        /**
         * Set the table state.
         *
         * @param tableState The table state set to table info.
         */
        public void setTableState(TableState tableState) {
            this.tableState = tableState;
        }

        /**
         * Get the table version.
         * @return The table version.
         */
        public long getTableVersion() {
            return tableVersion;
        }

        /**
         * Set the table version.
         *
         * @param tableVersion The table version set to table info.
         */
        public void setTableVersion(long tableVersion) {
            this.tableVersion = tableVersion;
        }

        /**
         * Convert this object to string.
         *
         * @return the string represent this table info object.
         */
        @Override
        public String toString() {
            return "TableInfo [\n  tableName=" + tableName
                    + ", \n  tableState=" + tableState
                    + ", \n  tableVersion=" + tableVersion + "\n]";
        }
    }

    private List<TableInfo> tables = new ArrayList<TableInfo>();

    /**
     * Get list of table info objects of this list table response.
     *
     * @return The list of table info objects of this list table response.
     */
    public List<TableInfo> getTables() {
        return tables;
    }

    /**
     * Get the table info of the target table name.
     *
     * @param tableName the target table name.
     * @return The table info of the target table.
     */
    public TableInfo getTableInfo(String tableName) {
        for (TableInfo info : tables) {
            if (info.getTableName().equals(tableName)) {
                return info;
            }
        }
        return null;
    }

    /**
     * Set the table info objects to this list tables request.
     *
     * @param tables The list of table info objects.
     */
    public void setTables(List<TableInfo> tables) {
        this.tables = tables;
    }

    /**
     * Convert this object to string.
     *
     * @return The string represent of this object.
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("ListTablesResponse [\n");
        for (TableInfo info : tables) {
            buffer.append(info);
            buffer.append(",\n");
        }
        buffer.append("]");
        return buffer.toString();
    }
}
