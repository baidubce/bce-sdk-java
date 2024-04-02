package com.baidubce.services.lss.cases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.AntiLeech;
import com.baidubce.services.lss.model.Auth;
import com.baidubce.services.lss.model.Encryption;
import com.baidubce.services.lss.model.GetSecurityPolicyResponse;
import com.baidubce.services.lss.model.HlsEncryption;
import com.baidubce.services.lss.model.IP;
import com.baidubce.services.lss.model.Refer;
import com.baidubce.services.lss.model.UpdateSecurityPolicyRequest;

/**
 * Created by shidaiting01 on 2016/1/13.
 */
public class UpdateLiveSecurityPolicyTest extends AbstractLssTest {
    private String sessionId;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    @Test
    public void test() {

        Auth auth = new Auth().withPlay(true).withPush(true);

        List<String> black = new ArrayList<String>();
        black.add("http://refer.demo.com");
        List<String> white = new ArrayList<String>();
        white.add("8.8.8.8");

        Refer refer = new Refer().withBlacklist(black);
        IP ip = new IP().withWhitelist(white);

        AntiLeech antiLeech = new AntiLeech().withRefer(refer).withIp(ip);

        // SafeCode safeCode = new SafeCode().withPlayerType("web").withSafeCode("code1");
        // List<SafeCode> safeCodes = new ArrayList<SafeCode>();
        // safeCodes.add(safeCode);

        HlsEncryption hlsEncryption = new HlsEncryption().withStrategy("PlayerBinding");
        Encryption encryption = new Encryption().withHls(hlsEncryption);

        lssClient.updateSecurityPolicy("default", auth, antiLeech, encryption);

        GetSecurityPolicyResponse response = lssClient.getSecurityPolicy("default");
        System.out.println(response.toString());
        assertEquals(response.getName(), "default");



        lssClient.updateSecurityPolicy(new UpdateSecurityPolicyRequest().withName("default"));


    }
}
