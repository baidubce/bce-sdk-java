package com.baidubce.services.acl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.acl.model.AclRule;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * aclClient test
 *
 * @author duxiangyu
 * @since 2018/9/12
 */
public class AclClientTest {

    private static final Logger logger = LoggerFactory.getLogger(AclClientTest.class);
    private static final String ak = "d1dd575046864692b5fe235089d87a12";
    private static final String sk = "96581de66d5f48d4b5184dd1b6b74f4c";
    private AclClient aclClient;

    @Before
    public void setUp() {
        AclClientConfiguration config = new AclClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("bcc.bce-api.baidu.com");
        aclClient = new AclClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createAclTest() {
        AclRule aclRule = new AclRule();
        aclRule.setSubnetId("sbn-fk8m7a9hfisj");
        aclRule.setDescription("test");
        aclRule.setProtocol("all");
        aclRule.setSourceIpAddress("all");
        aclRule.setDestinationIpAddress("all");
        aclRule.setSourcePort("8080");
        aclRule.setDestinationPort("8080");
        aclRule.setPosition(3);
        aclRule.setDirection("ingress");
        aclRule.setAction("allow");
        List<AclRule> aclRules = new ArrayList<AclRule>();
        aclRules.add(aclRule);
        aclClient.createAcl(aclRules);
    }

    @Test
    public void listAclRulesTest() {
        toJsonPrettyString("list aclRuls Results", aclClient.listAclRules("sbn-fk8m7a9hfisj"));
    }

    @Test
    public void getAclTest() {
        toJsonPrettyString("get acl Results", aclClient.getAcl("vpc-rtdt6f5bkfp4"));
    }

    @Test
    public void deleteAclTest() {
        aclClient.deleteAcl("ar-zwqv42hk2znd");
    }

    @Test
    public void modifyAclRuleAttributesTest() {
        AclRule aclRule = new AclRule();
        aclRule.setSubnetId("sbn-fk8m7a9hfisj");
        aclRule.setDescription("desc");
        aclRule.setProtocol("all");
        aclRule.setSourceIpAddress("all");
        aclRule.setDestinationIpAddress("all");
        aclRule.setSourcePort("8085");
        aclRule.setDestinationPort("8085");
        aclRule.setPosition(10);
        aclRule.setDirection("ingress");
        aclRule.setAction("allow");
        aclClient.modifyAclRuleAttributes("ar-xe7amqmxi80m", aclRule);
    }

}
