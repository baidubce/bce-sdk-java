package com.baidubce.services.geo;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.geo.model.logmodel.GetDomainListLogRequest;
import com.baidubce.services.geo.model.logmodel.GetDomainListLogResponse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

/**
 * Unit test for GEO client.
 */
@Ignore
public class GeoClientTest {
    private static final String AK = "YOUR_ACCESS_KEY";
    private static final String SK = "YOUR_SECRET_KEY";
    private static final String ENDPOINT = "GEO_ENDPOINT";
    private static final String DOMAIN = "TEST_DOMAIN";
    private static final String SITE = "TEST_SITE";

    private GeoClient geoClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        geoClient = new GeoClient(config);
    }

    /**
     * Get multiple domain URLs of logmodel files
     */
    @Test
    public void testGetDomainListLog() throws Exception {
        GetDomainListLogRequest request = new GetDomainListLogRequest()
                .withSite(SITE)
                .withStartTime("2026-03-18T16:00:00Z")
                .withEndTime("2026-03-19T11:06:21Z")
                .withPageNo(1)
                .withPageSize(20)
                .withDomainList(Arrays.asList(DOMAIN));
        GetDomainListLogResponse response = geoClient.getDomainListLog(request);
        System.out.println(response);
    }
}
