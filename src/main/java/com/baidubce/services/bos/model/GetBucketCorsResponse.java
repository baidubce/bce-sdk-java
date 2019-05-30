package com.baidubce.services.bos.model;

import java.util.List;

/**
 * Get Bucket Cors Response
 */
public class GetBucketCorsResponse extends BosResponse {

    /**
     * corsConfiguration of GetBucketCorsResponse.
     */
    private List<CorsConfiguration> corsConfiguration;

    /**
     * Gets corsConfiguration of GetBucketCorsResponse.
     * @return corsConfiguration of GetBucketCorsResponse.
     */
    public List<CorsConfiguration> getCorsConfiguration() {
        return corsConfiguration;
    }

    /**
     * Sets corsConfiguration of GetBucketCorsResponse.
     * @param corsConfiguration The corsConfiguration of GetBucketCorsResponse.
     */
    public void setCorsConfiguration(List<CorsConfiguration> corsConfiguration) {
        this.corsConfiguration = corsConfiguration;
    }

    /**
     * Constructs a void constructor for GetBucketCorsResponse.
     */
    public GetBucketCorsResponse() {
    }

    /**
     * Constructs a new GetBucketCorsResponse object for GetBucketCorsResponse.
     * @param corsConfiguration
     */
    public GetBucketCorsResponse(List<CorsConfiguration> corsConfiguration) {
        this.corsConfiguration = corsConfiguration;
    }

    @Override
    public String toString() {
        return "GetBucketCorsResponse{"
                + "corsConfiguration=" + corsConfiguration
                + '}';
    }
}
