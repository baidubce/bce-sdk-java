package com.baidubce.services.iotviz;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by chengrong01
 * 20/11/2017.
 */
public class IotVizClientTest {
    private static String AK = "";
    private static String SK = "";

    private IotVizClient client;

    @Before
    public void setUp() {
        BceClientConfiguration configuration = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint("nmg01-hpc-w1139.nmg01.baidu.com:8000/api");
        client = new IotVizClient(configuration);
    }

    @Test
    public void createTokenTest() {
        String response = client.createToken(30);
        assertThat(response, notNullValue());
    }
}
