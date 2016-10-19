package com.baidubce;


import org.junit.Assert;
import org.junit.Test;


import com.baidubce.services.bos.BosClientConfiguration;

public class BceClientConfigurationTest {
    @Test
    public void testProtocolInProvidedUrl() {
        BceClientConfiguration config = null;

        // http
        String endpoint = "http://bcebos.com";

        config = new BosClientConfiguration();
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTPS);
        // protocol does not take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), endpoint);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "https");

        config = new BosClientConfiguration();
        config.setProtocol(Protocol.HTTPS);
        config.setEndpoint(endpoint);
        // protocol does not take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), endpoint);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "https");

        config = new BosClientConfiguration();
        config.setEndpoint(endpoint);
        Assert.assertEquals(config.getEndpoint(), endpoint);
        // default protocol is http
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "http");

        // https
        endpoint = "https://bcebos.com";

        config = new BosClientConfiguration();
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTP);
        // protocol does not take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), endpoint);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "http");

        config = new BosClientConfiguration();
        config.setProtocol(Protocol.HTTP);
        config.setEndpoint(endpoint);
        // protocol does not take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), endpoint);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "http");

        config = new BosClientConfiguration();
        config.setEndpoint(endpoint);
        Assert.assertEquals(config.getEndpoint(), endpoint);
        // default protocol is http
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "http");
    }
    
    @Test
    public void testProtocolNotInProvidedUrl() {
        // https
        String endpoint = "bcebos.com";
        BosClientConfiguration config = null;

        config = new BosClientConfiguration();
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTP);
        // protocol take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), "http://" + endpoint);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "http");

        config = new BosClientConfiguration();
        config.setProtocol(Protocol.HTTP);
        config.setEndpoint(endpoint);
        // protocol take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), "http://" + endpoint);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "http");

        config = new BosClientConfiguration();
        config.setEndpoint(endpoint);
        config.setProtocol(Protocol.HTTPS);
        // protocol take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), "https://" + endpoint);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "https");

        config = new BosClientConfiguration();
        config.setProtocol(Protocol.HTTPS);
        config.setEndpoint(endpoint);
        // protocol take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), "https://" + endpoint);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "https");

        // default protocal
        config = new BosClientConfiguration();
        config.setEndpoint(endpoint);
        Assert.assertEquals(config.getEndpoint(), "http://" + endpoint);
        // default protocol is http
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "http");
    }
    
    @Test
    public void testProtocolOnly() {
        BosClientConfiguration config = null;

        config = new BosClientConfiguration();
        config.setProtocol(Protocol.HTTP);
        // protocol take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), null);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "http");

        config = new BosClientConfiguration();
        config.setProtocol(Protocol.HTTPS);
        // protocol take effect to endpiont
        Assert.assertEquals(config.getEndpoint(), null);
        Assert.assertEquals(config.getProtocol().toString().toLowerCase(), "https");

    }
}
