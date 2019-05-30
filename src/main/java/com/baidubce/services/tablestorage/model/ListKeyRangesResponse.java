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

import com.baidubce.services.tablestorage.TableStorageConstants;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents the output of a ListKeyRanges operation. Only used by TableStorage HBase Client.
 */
public class ListKeyRangesResponse extends AbstractTableStorageResponse {
    private List<Pair<String, String>> keyRanges = new ArrayList<Pair<String, String>>();

    public boolean setKeyRanges(List<Pair<String, String>> keyRanges) {
        Collections.sort(keyRanges, new StringPairCompare());
        if (!isKeyRangeListLegal(keyRanges)) {
            return false;
        }
        this.keyRanges = keyRanges;
        return true;
    }

    private boolean isKeyRangeListLegal(List<Pair<String, String>> keyRangeList) {
        String lastStopKey = "";
        for (Pair<String, String> keyRange : keyRangeList) {
            if (!keyRange.getKey().equals(lastStopKey)) {
                return false;
            }
            lastStopKey = keyRange.getValue();
        }
        return lastStopKey.equals(TableStorageConstants.MAX_ROWKEY);
    }

    public Pair<String, String> getKeyRange(String rowkey) {
        for (Pair<String, String> keyRange : keyRanges) {
            if (keyRange.getKey().compareTo(rowkey) < 0) {
                continue;
            }
            return keyRange;
        }
        return null;
    }

    public List<Pair<String, String>> getKeyRanges() {
        return Collections.unmodifiableList(keyRanges);
    }

    private static class StringPairCompare implements Comparator<Pair<String, String>> {
        @Override
        public int compare(Pair<String, String> o1, Pair<String, String> o2) {
            int ret = o1.getKey().compareTo(o2.getKey());
            if (ret == 0) {
                ret = o1.getValue().compareTo(o2.getValue());
            }
            return ret;
        }
    }

}
