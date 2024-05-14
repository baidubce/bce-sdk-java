package com.baidubce.services.iotalarm;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotalarm.model.Alarm;
import com.baidubce.services.iotalarm.model.AlarmKind;
import com.baidubce.services.iotalarm.model.AlarmNoticeMsgType;
import com.baidubce.services.iotalarm.model.AlarmSeverity;
import com.baidubce.services.iotalarm.model.AlarmTrigger;
import com.baidubce.services.iotalarm.model.CommonResponse;
import com.baidubce.services.iotalarm.model.CreateAlarmRequest;
import com.baidubce.services.iotalarm.model.ListAlarmRequest;
import com.baidubce.services.iotalarm.model.ListAlarmResponse;
import com.baidubce.services.iotalarm.model.UpdateAlarmRequest;
import com.baidubce.services.iotalarm.model.UuidResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanyoujun on 2017/6/20.
 */
public class IotAlarmClientTest {
    private static final String TEST_ACCESSKEY = "";
    private static final String TEST_SECRETKEY = "";
    private static final String TEST_ENVIROMENT_ENDPOINT = "http://nmg01-hpc-w1134.nmg01.baidu.com:8009";
    private static final String MQTT_ENDPOINT = "alarmtest";

    private IotAlarmClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_ACCESSKEY, TEST_SECRETKEY))
                .withEndpoint(TEST_ENVIROMENT_ENDPOINT);
        client = new IotAlarmClient(config);

        // for production environment, just create the client like:
        // client = new IotalarmClient(accessKey, secretKey);
    }

    static CreateAlarmRequest makeCreateAlarmReq(String name) {
        CreateAlarmRequest req = new CreateAlarmRequest();
        req.setName(name);
        req.setDesc("when the indoor temperature greater then 60 deg");
        req.setTopic("alarmtest_input");
        req.setEndpointName(MQTT_ENDPOINT);
        req.setSelect("*");
        req.setCondition("temp > 60");
        req.setKind(AlarmKind.DATA_STREAM);
        req.setAlarmTrigger(AlarmTrigger.createCountType(1));
        req.setDestTopic("alarmtest_notify");
        req.setSeverity(AlarmSeverity.MIDDLE);
        req.setSmsMsgType(AlarmNoticeMsgType.ALARM_RECOVER);
        req.setMqttMsgType(AlarmNoticeMsgType.ALL);
        return req;
    }

    @Test
    public void testCreateAndDelete() {
        CreateAlarmRequest req = makeCreateAlarmReq("testCreateAndDelete");

        UuidResponse response = client.createAlarm(req);
        Assert.assertEquals("ok", response.getResult());
        client.deleteAlarm(response.getUuid());
    }

    @Test
    public void testGet() {
        String name = "testGet";
        CreateAlarmRequest req = makeCreateAlarmReq(name);
        UuidResponse response = client.createAlarm(req);
        String uuid = response.getUuid();

        Alarm alarm = client.getAlarm(uuid);
        Assert.assertEquals(name, alarm.getName());
        Assert.assertEquals(Alarm.NORMAL, alarm.getAlarmState());
        Assert.assertEquals(-1, alarm.getStartTime());
        Assert.assertEquals(-1, alarm.getRefreshTime());
        Assert.assertEquals(-1, alarm.getRecoverTime());
        Assert.assertEquals("{}", alarm.getLastMsg());

        client.deleteAlarm(uuid);
    }

    @Test
    public void testList() {
        ListAlarmRequest req = new ListAlarmRequest();
        req.setPageNo(1);
        req.setPageSize(20);
        ListAlarmResponse ret = client.listAlarms(req);
        Assert.assertTrue(ret.getResult().size() >= 0);

        req.setAlarmState(Alarm.ALARMING);
        req.setServerity(Alarm.MIDDLE);
        ret = client.listAlarms(req);
        Assert.assertTrue(ret.getResult().size() >= 0);
    }

    @Test
    public void testUpdate() {
        CreateAlarmRequest req = makeCreateAlarmReq("testUpdate");
        UuidResponse response = client.createAlarm(req);
        String alarmId = response.getUuid();

        UpdateAlarmRequest updateReq = new UpdateAlarmRequest();
        String newName = "testUpdateAfterUpdate";
        updateReq.setName(newName);
        String newCondition = "temp > 65";
        updateReq.setCondition(newCondition);
        updateReq.setSmsMsgType(AlarmNoticeMsgType.ALARM);
        updateReq.setMqttMsgType(AlarmNoticeMsgType.ALARM_RECOVER);

        client.updateAlarm(updateReq, alarmId);

        // verfiy the update
        Alarm alarm = client.getAlarm(alarmId);
        Assert.assertEquals(newName, alarm.getName());
        Assert.assertEquals(newCondition, alarm.getCondition());
        Assert.assertEquals(AlarmNoticeMsgType.ALARM, alarm.getSmsMsgType());
        Assert.assertEquals(AlarmNoticeMsgType.ALARM_RECOVER, alarm.getMqttMsgType());

        client.deleteAlarm(alarmId);
    }

    @Test
    public void testDisableEnable() {
        CreateAlarmRequest req = makeCreateAlarmReq("testDisableEnable");
        UuidResponse response = client.createAlarm(req);
        String alarmId = response.getUuid();
        Alarm alarm = client.getAlarm(alarmId);
        Assert.assertEquals(Alarm.ENABLED, alarm.getDisabled());

        // disable alarm
        client.disableAlarm(alarmId);
        alarm = client.getAlarm(alarmId);
        Assert.assertEquals(Alarm.DISABLED, alarm.getDisabled());

        // enable alarm
        client.enableAlarm(alarmId);
        alarm = client.getAlarm(alarmId);
        Assert.assertEquals(Alarm.ENABLED, alarm.getDisabled());

        // delete alarm
        client.deleteAlarm(alarmId);
    }

    @Test
    public void testRecover() {
        CreateAlarmRequest req = makeCreateAlarmReq("testRecover");
        UuidResponse response = client.createAlarm(req);
        String alarmId = response.getUuid();
        client.recoverAlarm(alarmId);
        Alarm alarm = client.getAlarm(alarmId);
        Assert.assertEquals(Alarm.NORMAL, alarm.getAlarmState());

        client.deleteAlarm(alarmId);
    }

    @Test
    public void testBatchRecover() {
        List<String> ids = new ArrayList<String>();
        CreateAlarmRequest req = makeCreateAlarmReq("testBatchRecover1");
        UuidResponse response = client.createAlarm(req);
        ids.add(response.getUuid());

        req = makeCreateAlarmReq("testBatchRecover2");
        response = client.createAlarm(req);
        ids.add(response.getUuid());

        CommonResponse res = client.recoverAlarmBatch(ids);
        Assert.assertEquals("ok", res.getResult());

        client.deleteAlarmBatch(ids);
    }

    @Test
    public void testBatchDelete() {
        List<String> ids = new ArrayList<String>();
        CreateAlarmRequest req = makeCreateAlarmReq("testBatchDelete1");
        UuidResponse response = client.createAlarm(req);
        ids.add(response.getUuid());

        req = makeCreateAlarmReq("testBatchDelete2");
        response = client.createAlarm(req);
        ids.add(response.getUuid());

        ListAlarmRequest listReq = new ListAlarmRequest();
        listReq.setPageNo(1);
        listReq.setPageSize(50);
        ListAlarmResponse ret = client.listAlarms(listReq);
        int countBeforeBatchDelete = ret.getTotalCount();

        CommonResponse res = client.deleteAlarmBatch(ids);
        Assert.assertEquals("ok", res.getResult());

        ret = client.listAlarms(listReq);
        int countAfterBatchDelete = ret.getTotalCount();

        Assert.assertEquals(countBeforeBatchDelete - 2, countAfterBatchDelete);
    }
}
