package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.ListDomainAppRequest;
import com.baidubce.services.lss.model.ListDomainAppResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wuyafei on 16/10/14.
 */
public class ListDomainAppTest extends AbstractLssTest {

    private String playDomain = "play.testsdk.iefay.xyz";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testListDomainApp() {
        ListDomainAppResponse response = lssClient.listDomainApp(playDomain);
        // System.out.println(response);
        assertNotNull(response.getAppList());
    }

    @Test
    public void testListDomainAppWithRequest() {
        ListDomainAppRequest request = new ListDomainAppRequest();
        request.setPlayDomain(playDomain);
        ListDomainAppResponse response = lssClient.listDomainApp(request);
        // System.out.println(response);
        assertNotNull(response.getAppList());
    }
}
