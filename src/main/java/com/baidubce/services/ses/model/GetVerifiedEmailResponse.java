package com.baidubce.services.ses.model;

/**
 * Response object of getting verified email.
 */
public class GetVerifiedEmailResponse extends SesResponse {
    /**
     * The detail of email, which is returned after getting verified email successfully.
     * 
     * @see EmailDetailModel
     */
    private EmailDetailModel detail;

    public EmailDetailModel getDetail() {
        return detail;
    }

    public void setDetail(EmailDetailModel detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "GetEmailAddressResponse [detail=" + detail + "]";
    }

}
