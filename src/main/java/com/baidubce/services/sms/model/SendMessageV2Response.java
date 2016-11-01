package com.baidubce.services.sms.model;

/**
 * SendMessageV2Response
 */
public class SendMessageV2Response extends BaseResponse {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMessageV2Response{");
        sb.append("requestId=\"").append(this.getRequestId()).append("\"");
        sb.append(", code=\"").append(this.getCode()).append("\"");
        sb.append(", message=\"").append(this.getMessage()).append("\"");
        sb.append("}");
        return sb.toString();
    }

}
