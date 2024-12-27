package com.baidubce.services.bec.model.overview;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * The request for getting vm node level overview information.
 */
@Data
public class GetBecVmNodeLevelMetricsRequest extends AbstractBceRequest {

    /**
     * The type of the metrics.
     */
    private String type;

    /**
     * Start time, Unix timestamp.
     */
    private long start;

    /**
     * Termination time, Unix timestamp.
     */
    private long end;

    /**
     * Region.
     */
    private String region;

    /**
     * City.
     */
    private String city;

    /**
     * ServiceProvider.
     */
    private String serviceProvider;

    /**
     * Network.
     */
    private String network;

    /**
     * StepInMin
     */
    private Integer stepInMin;


    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetBecVmNodeLevelMetricsRequest with credentials.
     */
    public GetBecVmNodeLevelMetricsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
