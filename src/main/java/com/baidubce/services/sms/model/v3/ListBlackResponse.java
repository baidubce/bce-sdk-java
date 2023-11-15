/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsResponse;

import java.util.List;

public class ListBlackResponse extends SmsResponse {
    /**
     * Total results
     */
    private int totalCount;

    /**
     * current page
     */
    private int pageNo;

    /**
     * perPage Size
     */
    private int pageSize;

    /**
     * result list
     */
    private List<BlackDetail> blacklists;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<BlackDetail> getBlacklists() {
        return blacklists;
    }

    public void setBlacklists(List<BlackDetail> blacklists) {
        this.blacklists = blacklists;
    }


    static class BlackDetail {
        /**
         * black phone
         */
        private String phone;

        /**
         * black type
         * The value of type could be MerchantBlack or SignatureBlack
         */
        private String type;

        /**
         * smsType
         * the limited smsType when send sms
         */
        private String smsType;

        /**
         * signatureIdStr
         * the limited signatureIdStr when send sms
         */
        private String signatureIdStr;

        /**
         * black updateDate
         */
        private String updateDate;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSmsType() {
            return smsType;
        }

        public void setSmsType(String smsType) {
            this.smsType = smsType;
        }

        public String getSignatureIdStr() {
            return signatureIdStr;
        }

        public void setSignatureIdStr(String signatureIdStr) {
            this.signatureIdStr = signatureIdStr;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        @Override
        public String toString() {
            return "BlackDetail{" +
                    "phone='" + phone + '\'' +
                    ", type='" + type + '\'' +
                    ", smsType='" + smsType + '\'' +
                    ", signatureIdStr='" + signatureIdStr + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    '}';
        }
    }


}
