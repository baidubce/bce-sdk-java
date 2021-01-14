package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsResponse;

import java.util.List;

public class ListSignatureResponse extends SmsResponse {

    public List<GetSignatureResponse> getSignatureApplies() {
        return signatureApplies;
    }

    public void setSignatureApplies(List<GetSignatureResponse> signatureApplies) {
        this.signatureApplies = signatureApplies;
    }

    private List<GetSignatureResponse> signatureApplies;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * The total count of the querying signatures.
     * TotalCount is null when byPage of the listSignatureRequest is false.
     */
    private int totalCount;

    @Override
    public String toString() {
        return "ListSignatureResponse [signatureList=" + signatureApplies + "]";
    }
}
