package com.baidubce.services.iotsmsreceiver;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotalarm.model.CommonResponse;
import com.baidubce.services.iotalarm.model.UuidResponse;
import com.baidubce.services.iotsmsreceiver.model.CreateIotSmsReceiverRequest;
import com.baidubce.services.iotsmsreceiver.model.IotSmsReceiver;
import com.baidubce.services.iotsmsreceiver.model.ListIotSmsReceiverRequest;
import com.baidubce.services.iotsmsreceiver.model.ListIotSmsReceiverResponse;
import com.baidubce.services.iotsmsreceiver.model.UpdateIotSmsReceiverRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yuanyoujun on 2017/8/13.
 */
public class IotSmsReceiverClientTest {
    private static final String TEST_ACCESSKEY = "2647e9da8eab431cbe58eaa6c1d46cdc";
    private static final String TEST_SECRETKEY = "72c14ce4b2d2417c92dbe9828d45bd5a";
    private static final String TEST_ENVIROMENT_ENDPOINT = "http://nmg01-hpc-w1134.nmg01.baidu.com:8009";
    private static final String TEL1 = "13812345678";
    private static final String TEL2 = "13812345678,13812345679";
    private static final String TEMPLATE = "smsTpl:a37d4613-26d3-4628-a3cd-9855c8e68e76";
    private static final String TEMPLATE2 = "smsTpl:a37d4613-26d3-4628-a3cd-9855c8e68e77";
    private static final String SIGNATURE = "WbtYhzJC-4IIT-HknG";
    private static final String SIGNATURE2 = "WbtYhzJC-4IIT-HknG2";

    private IotSmsReceiverClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_ACCESSKEY, TEST_SECRETKEY))
                .withEndpoint(TEST_ENVIROMENT_ENDPOINT);
        client = new IotSmsReceiverClient(config);


        // for production environment, just create the client like:
        // client = new IotSmsReceiverClient(accessKey, secretKey);
    }

    static CreateIotSmsReceiverRequest makeCreateReq(
            String name, String template, String signature, String receivers) {
        CreateIotSmsReceiverRequest req = new CreateIotSmsReceiverRequest();
        req.setName(name);
        req.setReceivers(receivers);
        req.setSignature(signature);
        req.setTemplate(template);

        return req;
    }

    @Test
    public void testCreate() {
        CreateIotSmsReceiverRequest req = makeCreateReq("testCreate", TEMPLATE, SIGNATURE, TEL1);
        UuidResponse response = client.create(req);

        Assert.assertEquals("ok", response.getResult());
        // get
        IotSmsReceiver smsReceiver = client.get(response.getUuid());
        Assert.assertEquals("testCreate", smsReceiver.getName());
        Assert.assertEquals(TEMPLATE, smsReceiver.getTemplate());
        Assert.assertEquals(SIGNATURE, smsReceiver.getSignature());
        Assert.assertEquals(TEL1, smsReceiver.getReceivers());

        // delete
        client.delete(response.getUuid());
    }

    @Test
    public void testList() {
        ListIotSmsReceiverRequest req = new ListIotSmsReceiverRequest();
        int pageNo = 1;
        int pageSize = 40;
        req.setPageSize(pageSize);
        req.setPageNo(pageNo);
        ListIotSmsReceiverResponse res = client.list(req);
        Assert.assertEquals(pageSize, res.getPageSize());
        Assert.assertEquals(pageNo, res.getPageNo());

        //
        CreateIotSmsReceiverRequest reqCreate = makeCreateReq("testList", TEMPLATE, SIGNATURE, TEL1);
        UuidResponse resCreate = client.create(reqCreate);
        Assert.assertEquals("ok", resCreate.getResult());

        // list again
        ListIotSmsReceiverResponse res2 = client.list(req);
        Assert.assertEquals(res.getTotalCount() + 1, res2.getTotalCount());

        // delete
        client.delete(resCreate.getUuid());

        // list for a third time
        ListIotSmsReceiverResponse res3 = client.list(req);
        Assert.assertEquals(res.getTotalCount(), res3.getTotalCount());
    }

    @Test
    public void testUpdate() {
        CreateIotSmsReceiverRequest req = makeCreateReq("testUpdate", TEMPLATE, SIGNATURE, TEL1);
        UuidResponse response = client.create(req);

        Assert.assertEquals("ok", response.getResult());

        UpdateIotSmsReceiverRequest updateReq = new UpdateIotSmsReceiverRequest();
        updateReq.setName("testUpdateUpdated");
        updateReq.setSignature(SIGNATURE2);
        updateReq.setTemplate(TEMPLATE2);
        updateReq.setReceivers(TEL2);

        CommonResponse updateResponse = client.update(updateReq, response.getUuid());
        Assert.assertEquals("ok", updateResponse.getResult());

        // get
        IotSmsReceiver smsReceiver = client.get(response.getUuid());
        Assert.assertEquals("testUpdateUpdated", smsReceiver.getName());
        Assert.assertEquals(SIGNATURE2, smsReceiver.getSignature());
        Assert.assertEquals(TEMPLATE2, smsReceiver.getTemplate());
        Assert.assertEquals(TEL2, smsReceiver.getReceivers());

        // delete
        client.delete(response.getUuid());
    }
}
