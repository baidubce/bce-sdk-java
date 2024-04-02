package com.baidubce.services.iottimer;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotalarm.model.UuidResponse;
import com.baidubce.services.iottimer.model.CreateIotTimerRequest;
import com.baidubce.services.iottimer.model.IotTimer;
import com.baidubce.services.iottimer.model.ListIotTimerRequest;
import com.baidubce.services.iottimer.model.ListIotTimerResponse;
import com.baidubce.services.iottimer.model.UpdateIotTimerRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IotTimerClientTest {
    private static final String AK = "fc375540f44e4728884a4b86dc50340b";
    private static final String SK = "9db8de13f5d141b5a3d38f6dd937fba2";
    private static final String ENDPOINT = "http://nmg01-hpc-w1134.nmg01.baidu.com:8009";

    private static final String ENDPOINT_NAME = "yyj";
    private static final String ENDPOINT_NAME2 = "yyj2";
    private static final String MSG = "{\"a\":1}";
    private static final long BEGIN_AT = 1513243421;
    private static final long PERIOD = 25;
    private static final long TIMES = 10;

    public IotTimerClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        client = new IotTimerClient(config);
    }

    @Test
    public void create() {
        String name = "create";
        UuidResponse uuid = createTimer(name);

        IotTimer timer = client.get(uuid.getUuid());
        Assert.assertEquals(ENDPOINT_NAME, timer.getEndpointName());
        Assert.assertEquals(name, timer.getName());
        Assert.assertEquals(name, timer.getDescription());
        Assert.assertEquals(name, timer.getTopic());
        Assert.assertEquals(TIMES, timer.getTimes());
        Assert.assertEquals(PERIOD, timer.getPeriod());
        Assert.assertEquals(MSG, timer.getMsg());
        Assert.assertEquals(0, timer.getExecutedTimes());
        Assert.assertEquals(0, timer.getLastExecuteTime());

        client.delete(uuid.getUuid());
    }

    @Test
    public void updateTimes() {
        String name = "updateTimes";
        String uuid = createTimer(name).getUuid();

        IotTimer timer = client.get(uuid);
        UpdateIotTimerRequest req = new UpdateIotTimerRequest();
        long newTimes = timer.getTimes() + 1;
        req.setPeriod(timer.getPeriod());
        req.setTimes(newTimes);
        client.update(req, uuid);

        timer = client.get(uuid);
        Assert.assertEquals(newTimes, timer.getTimes());


        try {
            Thread.sleep(4000);
        } catch (InterruptedException ie) {
            // wait for a while that the executedTimes got updated
            System.out.printf("Thread.sleep failed!");
        }

        // set times to 0, then it becomes FINISHED
        newTimes = 1;
        req.setPeriod(timer.getPeriod());
        req.setTimes(newTimes);
        client.update(req, uuid);
        timer = client.get(uuid);
        Assert.assertEquals(newTimes, timer.getTimes());
        Assert.assertEquals(true, timer.isFinished());

        newTimes = 2;
        req.setPeriod(timer.getPeriod());
        req.setTimes(newTimes);
        client.update(req, uuid);
        timer = client.get(uuid);
        Assert.assertEquals(newTimes, timer.getTimes());
        Assert.assertEquals(false, timer.isFinished());
        client.delete(uuid);
    }

    @Test
    public void update() {
        String name = "update";
        String uuid = createTimer(name).getUuid();

        IotTimer timer = client.get(uuid);
        UpdateIotTimerRequest req = new UpdateIotTimerRequest();
        name = timer.getName() + "_new";
        req.setName(name);

        String description = timer.getDescription() + "_new";
        req.setDescription(description);

        String endpoint = ENDPOINT_NAME2;
        req.setEndpointName(endpoint);

        String topic = timer.getTopic() + "_new";
        req.setTopic(topic);

        long period = timer.getPeriod() + 1;
        req.setPeriod(period);

        String msg = timer.getMsg() + "_new";
        req.setMsg(msg);

        req.setTimes(timer.getTimes());

        client.update(req, uuid);

        timer = client.get(uuid);
        Assert.assertEquals(name, timer.getName());
        Assert.assertEquals(description, timer.getDescription());
        Assert.assertEquals(endpoint, timer.getEndpointName());
        Assert.assertEquals(topic, timer.getTopic());
        Assert.assertEquals(period, timer.getPeriod());
        Assert.assertEquals(msg, timer.getMsg());
    }

    UuidResponse createTimer(String name) {
        deleteTimerByName(name);
        CreateIotTimerRequest req = new CreateIotTimerRequest();
        req.setName(name);
        req.setEndpointName(ENDPOINT_NAME);
        req.setTopic(name);
        req.setMsg(MSG);
        req.setPeriod(PERIOD);
        req.setTimes(TIMES);
        req.setDescription(name);
        req.setBeginAt(BEGIN_AT);

        return client.create(req);
    }

    private void deleteTimerByName(String name) {
        String uuid = findTimerIdByName(name);
        if (uuid != null) {
            client.delete(uuid);
        }
    }

    private String findTimerIdByName(String name) {
        int pageNo = 0;
        String id = null;
        ListIotTimerResponse response = null;
        do {
            ListIotTimerRequest request = new ListIotTimerRequest();
            request.setPageNo(++pageNo);
            response = client.list(request);
            for (IotTimer t : response.getResult()) {
                if (t.getName().equals(name)) {
                    id = t.getUuid();
                    break;
                }
            }
        } while (id == null && response.getTotalCount() > response.getPageNo() * response.getPageSize());

        return id;
    }
}
