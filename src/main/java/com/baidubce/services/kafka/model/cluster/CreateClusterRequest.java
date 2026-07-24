package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateClusterRequest extends AbstractBceRequest {

    private String name;

    private Mode mode;

    private Type type;

    /**
     * Optional. Engine type. Values: {@code Kafka}. Server default: {@code Kafka}.
     */
    private String engineType;

    /**
     * Optional. Cluster metadata storage mode. Values: {@code Zookeeper}, {@code KRaft}.
     * Server default: {@code Zookeeper}.
     */
    private String metadataMode;

    private Provisioned provisioned;

    private List<Tag> tags;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetInstanceRequest with credentials.
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
