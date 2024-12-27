package com.baidubce.services.bcc.model.region;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * The request for getting endpoint of different regions.
 */
@Data
public class DescribeRegionsRequest extends AbstractBceRequest {
    /**
     * An ASCII string whose length is less than 64.
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     *     BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * The region
     */
    private String region;

    /**
     * Configure request credential for the request.
     * @param credentials a valid instance of BceCredentials.
     * @return DescribeRegionsRequest with credentials.
     */
    @Override
    public DescribeRegionsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
