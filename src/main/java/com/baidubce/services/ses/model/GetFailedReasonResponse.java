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
package com.baidubce.services.ses.model;

/**
 * Response object of getting failed reason, which contains all of reasons while sending email failure.
 */
public class GetFailedReasonResponse extends SesResponse {
    private Integer accsize;
    private Integer address;
    private Integer quotaoverflow;
    private Integer recptcnt;
    private Integer spammail;
    private Integer title;
    private Integer verification;
    private Integer virusmail;
    private Integer emptysender;

    public GetFailedReasonResponse() {
    }

    public GetFailedReasonResponse(Integer accsize, Integer address, Integer quotaoverflow, Integer recptcnt,
            Integer spammail, Integer title, Integer verification, Integer virusmail, Integer emptysender) {
        this.accsize = accsize;
        this.address = address;
        this.quotaoverflow = quotaoverflow;
        this.recptcnt = recptcnt;
        this.spammail = spammail;
        this.title = title;
        this.verification = verification;
        this.virusmail = virusmail;
        this.emptysender = emptysender;
    }

    public Integer getAccsize() {
        return accsize;
    }

    public void setAccsize(Integer accsize) {
        this.accsize = accsize;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getQuotaoverflow() {
        return quotaoverflow;
    }

    public void setQuotaoverflow(Integer quotaoverflow) {
        this.quotaoverflow = quotaoverflow;
    }

    public Integer getRecptcnt() {
        return recptcnt;
    }

    public void setRecptcnt(Integer recptcnt) {
        this.recptcnt = recptcnt;
    }

    public Integer getSpammail() {
        return spammail;
    }

    public void setSpammail(Integer spammail) {
        this.spammail = spammail;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public Integer getVerification() {
        return verification;
    }

    public void setVerification(Integer verification) {
        this.verification = verification;
    }

    public Integer getVirusmail() {
        return virusmail;
    }

    public void setVirusmail(Integer virusmail) {
        this.virusmail = virusmail;
    }

    public Integer getEmptysender() {
        return emptysender;
    }

    public void setEmptysender(Integer emptysender) {
        this.emptysender = emptysender;
    }

    @Override
    public String toString() {
        return "GetFailedReasonResponse [accsize=" + accsize + ", address=" + address + ", quotaoverflow="
                + quotaoverflow + ", recptcnt=" + recptcnt + ", spammail=" + spammail + ", title=" + title
                + ", verification=" + verification + ", virusmail=" + virusmail + ", emptysender=" + emptysender + "]";
    }

}
