package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class BatchChangeToPostpaidRequest extends AbstractBceRequest {

    private List<PostpayConfig> config;

    public BatchChangeToPostpaidRequest withConfig(List<PostpayConfig> config) {
        this.config = config;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Data
    public static class PostpayConfig {

        private String instanceId;

        private String effectiveType;

        public PostpayConfig withInstanceId(String instanceId) {
            this.instanceId = instanceId;
            return this;
        }

        public PostpayConfig withEffectiveType(String effectiveType) {
            this.effectiveType = effectiveType;
            return this;
        }
    }
}
