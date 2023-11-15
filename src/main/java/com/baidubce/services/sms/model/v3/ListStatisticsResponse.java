/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

public class ListStatisticsResponse extends SmsResponse {
    /**
     * Result list
     */
    private List<StatisticsResult> statisticsResults;

    public List<StatisticsResult> getStatisticsResults() {
        return statisticsResults;
    }

    public void setStatisticsResults(List<StatisticsResult> statisticsResults) {
        this.statisticsResults = statisticsResults;
    }

    public static class StatisticsResult {
        /**
         * Statistics date, like "2020-04-21", "2021-07" or "合计"
         */
        private String datetime;

        /**
         * Country code, such as "CN", "AU"
         */
        private String countryAlpha2Code;

        /**
         * The count number of submitted messages.
         */
        private String submitCount;

        /**
         * The count number of submitted long messages.
         */
        private String submitLongCount;

        /**
         * The count of success response of sending messages
         */
        private String responseSuccessCount;

        /**
         * The percentage of success response of sending messages
         */
        private String responseSuccessProportion;

        /**
         * The count number of successfully delivered messages.
         */
        private String deliverSuccessCount;

        /**
         * The count number of successfully delivered long messages.
         */
        private String deliverSuccessLongCount;

        /**
         * The percentage of successfully delivered messages.<br/>
         * Deliver Success Proportion = Success Count / Submit Count
         */
        private String deliverSuccessProportion;

        /**
         * The count number of delivered unsuccessfully messages.
         */
        private String deliverFailureCount;

        /**
         * The percentage of unsuccessfully delivered messages.<br/>
         * Deliver Failure Proportion = Failure Count / Submit Count
         */
        private String deliverFailureProportion;

        /**
         * The percentage of receipts received.<br/>
         * Receipt Proportion = (Success Count + Failure Count) / Submit Count
         */
        private String receiptProportion;

        /**
         * The count number of unknown deliver result messages.
         */
        private String unknownCount;

        /**
         * The percentage of unknown deliver result messages.<br/>
         * Unknown Proportion = Unknown Count / Submit Count
         */
        private String unknownProportion;

        /**
         * The count of timeout response of sending messages
         */
        private String responseTimeoutCount;

        /**
         * The count of failure due to unknown errors
         */
        private String unknownErrorCount;

        /**
         * The count of failure due to cell phone numbers not existed
         */
        private String notExistCount;

        /**
         * The count of failure due to signature or template error
         */
        private String signatureOrTemplateCount;

        /**
         * The count of failure due to carriers network error
         */
        private String abnormalCount;

        /**
         * The count of failure due to gateway submitting overclocking
         */
        private String overclockingCount;

        /**
         * The count of failure due to other error
         */
        private String otherErrorCount;

        /**
         * The count of failure due to blacklisting cell phone numbers
         */
        private String blacklistCount;

        /**
         * The count of failure due to message route or channel
         */
        private String routeErrorCount;

        /**
         * The count of failure due to carriers submitting error
         */
        private String issueFailureCount;

        /**
         * The count of failure due to parameters error
         */
        private String parameterErrorCount;

        /**
         * The count of failure due to illegal words
         */
        private String illegalWordCount;

        /**
         * The count of failure due to cell phone device abnormal
         */
        private String anomalyCount;

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getCountryAlpha2Code() {
            return countryAlpha2Code;
        }

        public void setCountryAlpha2Code(String countryAlpha2Code) {
            this.countryAlpha2Code = countryAlpha2Code;
        }

        public String getSubmitCount() {
            return submitCount;
        }

        public void setSubmitCount(String submitCount) {
            this.submitCount = submitCount;
        }

        public String getSubmitLongCount() {
            return submitLongCount;
        }

        public void setSubmitLongCount(String submitLongCount) {
            this.submitLongCount = submitLongCount;
        }

        public String getResponseSuccessCount() {
            return responseSuccessCount;
        }

        public void setResponseSuccessCount(String responseSuccessCount) {
            this.responseSuccessCount = responseSuccessCount;
        }

        public String getResponseSuccessProportion() {
            return responseSuccessProportion;
        }

        public void setResponseSuccessProportion(String responseSuccessProportion) {
            this.responseSuccessProportion = responseSuccessProportion;
        }

        public String getDeliverSuccessCount() {
            return deliverSuccessCount;
        }

        public void setDeliverSuccessCount(String deliverSuccessCount) {
            this.deliverSuccessCount = deliverSuccessCount;
        }

        public String getDeliverSuccessLongCount() {
            return deliverSuccessLongCount;
        }

        public void setDeliverSuccessLongCount(String deliverSuccessLongCount) {
            this.deliverSuccessLongCount = deliverSuccessLongCount;
        }

        public String getDeliverSuccessProportion() {
            return deliverSuccessProportion;
        }

        public void setDeliverSuccessProportion(String deliverSuccessProportion) {
            this.deliverSuccessProportion = deliverSuccessProportion;
        }

        public String getDeliverFailureCount() {
            return deliverFailureCount;
        }

        public void setDeliverFailureCount(String deliverFailureCount) {
            this.deliverFailureCount = deliverFailureCount;
        }

        public String getDeliverFailureProportion() {
            return deliverFailureProportion;
        }

        public void setDeliverFailureProportion(String deliverFailureProportion) {
            this.deliverFailureProportion = deliverFailureProportion;
        }

        public String getReceiptProportion() {
            return receiptProportion;
        }

        public void setReceiptProportion(String receiptProportion) {
            this.receiptProportion = receiptProportion;
        }

        public String getUnknownCount() {
            return unknownCount;
        }

        public void setUnknownCount(String unknownCount) {
            this.unknownCount = unknownCount;
        }

        public String getUnknownProportion() {
            return unknownProportion;
        }

        public void setUnknownProportion(String unknownProportion) {
            this.unknownProportion = unknownProportion;
        }

        public String getResponseTimeoutCount() {
            return responseTimeoutCount;
        }

        public void setResponseTimeoutCount(String responseTimeoutCount) {
            this.responseTimeoutCount = responseTimeoutCount;
        }

        public String getUnknownErrorCount() {
            return unknownErrorCount;
        }

        public void setUnknownErrorCount(String unknownErrorCount) {
            this.unknownErrorCount = unknownErrorCount;
        }

        public String getNotExistCount() {
            return notExistCount;
        }

        public void setNotExistCount(String notExistCount) {
            this.notExistCount = notExistCount;
        }

        public String getSignatureOrTemplateCount() {
            return signatureOrTemplateCount;
        }

        public void setSignatureOrTemplateCount(String signatureOrTemplateCount) {
            this.signatureOrTemplateCount = signatureOrTemplateCount;
        }

        public String getAbnormalCount() {
            return abnormalCount;
        }

        public void setAbnormalCount(String abnormalCount) {
            this.abnormalCount = abnormalCount;
        }

        public String getOverclockingCount() {
            return overclockingCount;
        }

        public void setOverclockingCount(String overclockingCount) {
            this.overclockingCount = overclockingCount;
        }

        public String getOtherErrorCount() {
            return otherErrorCount;
        }

        public void setOtherErrorCount(String otherErrorCount) {
            this.otherErrorCount = otherErrorCount;
        }

        public String getBlacklistCount() {
            return blacklistCount;
        }

        public void setBlacklistCount(String blacklistCount) {
            this.blacklistCount = blacklistCount;
        }

        public String getRouteErrorCount() {
            return routeErrorCount;
        }

        public void setRouteErrorCount(String routeErrorCount) {
            this.routeErrorCount = routeErrorCount;
        }

        public String getIssueFailureCount() {
            return issueFailureCount;
        }

        public void setIssueFailureCount(String issueFailureCount) {
            this.issueFailureCount = issueFailureCount;
        }

        public String getParameterErrorCount() {
            return parameterErrorCount;
        }

        public void setParameterErrorCount(String parameterErrorCount) {
            this.parameterErrorCount = parameterErrorCount;
        }

        public String getIllegalWordCount() {
            return illegalWordCount;
        }

        public void setIllegalWordCount(String illegalWordCount) {
            this.illegalWordCount = illegalWordCount;
        }

        public String getAnomalyCount() {
            return anomalyCount;
        }

        public void setAnomalyCount(String anomalyCount) {
            this.anomalyCount = anomalyCount;
        }
    }


}
