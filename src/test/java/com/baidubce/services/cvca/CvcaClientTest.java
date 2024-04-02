package com.baidubce.services.cvca;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cvca.model.ChatResponse;

/**
 * cvca client test
 *
 * @author wujinlin
 */
public class CvcaClientTest {
    private static final String TEST_ACCESSKEY = "bae11bc2c07e41598190f1dae049b171";
    private static final String TEST_SECRETKEY = "bb2b3e9438c24e2d987b47407526ee01";
    private static final String TEST_ENVIROMENT_ENDPOINT = "http://cp01-cvca.epc.baidu.com:8088";

    private CvcaClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_ACCESSKEY, TEST_SECRETKEY))
                .withEndpoint(TEST_ENVIROMENT_ENDPOINT);
        client = new CvcaClient(config);
    }

    @Test
    public void testChat() {
        ChatResponse response = client.chat("9633ac93-6b41-4393-baa5-9ab42fb92e5e", "查询天气", null);
        Assert.assertEquals("天气挺好的", response.getAnswer());
    }
}
