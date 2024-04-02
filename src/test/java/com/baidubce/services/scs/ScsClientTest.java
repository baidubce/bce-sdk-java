package com.baidubce.services.scs;

import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.scs.model.InstanceListRequest;
import com.baidubce.services.scs.model.InstanceListResponse;
import com.baidubce.services.scs.model.ScsBilling;
import com.baidubce.services.scs.model.ScsChangeConfigRequest;
import com.baidubce.services.scs.model.ScsClusterType;
import com.baidubce.services.scs.model.ScsCreateRequest;
import com.baidubce.services.scs.model.ScsCreateResponse;
import com.baidubce.services.scs.model.ScsDeleteInstanceRequest;
import com.baidubce.services.scs.model.ScsFlushInstanceRequest;
import com.baidubce.services.scs.model.ScsInstanceDetailRequest;
import com.baidubce.services.scs.model.ScsInstanceDetailResponse;
import com.baidubce.services.scs.model.ScsParamListRequest;
import com.baidubce.services.scs.model.ScsParamListResponse;
import com.baidubce.services.scs.model.ScsParamModifyRequest;
import com.baidubce.services.scs.model.ScsRecoverInstanceRequest;
import com.baidubce.services.scs.model.ScsReleaseInstanceRequest;
import com.baidubce.services.scs.model.ScsRenewInstanceRequest;
import com.baidubce.services.scs.model.ScsRenewInstanceResponse;
import com.baidubce.services.scs.model.ScsReservation;
import com.baidubce.services.scs.model.ScsSlowLogAction;
import com.baidubce.services.scs.model.ScsSlowLogModifyRequest;
import com.baidubce.services.scs.model.ScsSlowLogRequest;
import com.baidubce.services.scs.model.ScsSlowLogResponse;
import com.baidubce.services.scs.model.ScsSlowLogType;
import com.baidubce.services.scs.model.ScsSubnetRequest;
import com.baidubce.services.scs.model.ScsSubnetResponse;
import com.baidubce.services.scs.model.ScsSwapDomainRequest;
import com.baidubce.services.scs.model.ScsZoneResponse;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * scs client test
 */
public class ScsClientTest extends BaseScsClientTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseScsClientTest.class);

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * Responsible for handling httpResponses from all scs service calls.
     */
    private static final HttpResponseHandler[] SCS_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new ScsResponseTestHandler(),
    };
    private ScsClient scsClient;
    private InstanceInfo instanceInfo;

    @Before
    public void setup() {
        super.setup("scs/config.json");
        scsClient = new ScsClient(configuration, SCS_HANDLERS);
        instanceInfo = readJson("scs/instance.json", InstanceInfo.class);
    }

    @Test
    public void getInstanceList() {
        InstanceListResponse response = scsClient.getInstanceList(new InstanceListRequest());
        print(logger, "getInstanceList", response);
    }

    @Test
    public void getInstanceDetail() {
        ScsInstanceDetailRequest request = new ScsInstanceDetailRequest();
        request.setInstanceId(instanceInfo.getInstanceId());
        ScsInstanceDetailResponse instanceDetail = scsClient.getInstanceDetail(request);
        print(logger, "getInstanceDetail", instanceDetail);
    }

    @Test
    public void getParamList() {
        ScsParamListRequest paramListRequest = new ScsParamListRequest();
        paramListRequest.setInstanceId(instanceInfo.getInstanceId());
        ScsParamListResponse paramListResponse = scsClient.getParamList(paramListRequest);
        print(logger, "getParamList", paramListResponse);
    }

    @Test
    public void modifyScsParamTest() {
        ScsParamModifyRequest modifyRequest = new ScsParamModifyRequest();
        modifyRequest.setInstanceId(instanceInfo.getInstanceId());
        ScsParamModifyRequest.Parameter parameter = new ScsParamModifyRequest.Parameter();
        parameter.setName("timeout");
        parameter.setValue("0");
        modifyRequest.setParameter(parameter);
        scsClient.modifyScsParameter(modifyRequest);
        getParamList();
    }

    @Test
    public void changeScsInstance() {
        ScsChangeConfigRequest request = new ScsChangeConfigRequest();
        request.setInstanceId(instanceInfo.getInstanceId());
        request.setNodeType("cache.n1.small");
        request.setShardNum(1);
        scsClient.changeScsInstance(request);
        // getInstanceDetail();
    }

    @Test
    public void createInstance() {
        ScsCreateRequest createRequest = new ScsCreateRequest();
        createRequest.setInstanceName("scs-sdk-test3");
        ScsBilling billing = new ScsBilling();
        billing.setPaymentTiming("Prepaid");
        ScsReservation reservation = new ScsReservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        billing.setReservation(reservation);
        createRequest.setBilling(billing);
        createRequest.setNodeType("cache.n1.micro");
        createRequest.setPort(6379);
        createRequest.setEngineVersion("4.0");
        createRequest.setStoreType(0);
        createRequest.setPurchaseCount(1);
        createRequest.setShardNum(1);
        createRequest.setProxyNum(0);
        createRequest.setClusterType(ScsClusterType.MASTER_SLAVE);
        createRequest.setReplicationNum(2);
        createRequest.setClientAuth(scsConfig.getPassword());
//        createRequest.setAutoRenewTimeUnit("month");
//        createRequest.setAutoRenewTime(0);
        ScsCreateResponse createResponse = scsClient.createInstance(createRequest);
        print(logger, "createInstance", createResponse);
//        createRequest.set
    }

    @Test
    public void deleteInstance() {
        ScsDeleteInstanceRequest deleteInstance = new ScsDeleteInstanceRequest();
        deleteInstance.setInstanceId(instanceInfo.getInstanceId());
        scsClient.deleteInstance(deleteInstance);
        // query detail after delete,check instance status
        getInstanceDetail();
    }

    @Test
    public void renewInstance() {
        ScsRenewInstanceRequest request = new ScsRenewInstanceRequest();
        List<String> ids = new ArrayList<String>();
        ids.add(instanceInfo.getInstanceId());
        request.setInstanceIds(ids);
        request.setDuration(1);
        ScsRenewInstanceResponse response = scsClient.renewInstance(request);
        print(logger, "renewInstance", response);
    }

    @Test
    public void recoverInstance() {
        ScsRecoverInstanceRequest request = new ScsRecoverInstanceRequest();
        List<String> ids = new ArrayList<String>();
        ids.add(instanceInfo.getInstanceId());
        request.setInstanceIds(ids);
        scsClient.recoverInstance(request);
        getInstanceList();
    }

    @Test
    public void swapDomain() {
        ScsSwapDomainRequest swapDomainRequest = readJson("scs/swap_instance.json", ScsSwapDomainRequest.class);
        AbstractBceResponse response = scsClient.swapDomain(swapDomainRequest);
        print(logger, "swapDomain", response);
    }

    @Test
    public void flushInstance() {
        ScsFlushInstanceRequest flushInstanceRequest = new ScsFlushInstanceRequest();
        flushInstanceRequest.setInstanceId(instanceInfo.getInstanceId());
        // flushInstanceRequest.setPassword(scsConfig.getPassword());
        flushInstanceRequest.setDbIndex(0);
        try {
            AbstractBceResponse response = scsClient.flushInstance(flushInstanceRequest);
            print(logger, "flushInstance", response);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSlowLog() {
        ScsSlowLogRequest scsSlowLogRequest = new ScsSlowLogRequest();
        scsSlowLogRequest.setInstanceId(instanceInfo.getInstanceId());
        scsSlowLogRequest.setPageNow(1);
        scsSlowLogRequest.setPageSize(15);
        try {
            scsSlowLogRequest.setStartTime(DATE_FORMAT.parse("2021-12-24 06:00:00"));
            scsSlowLogRequest.setEndTime(DATE_FORMAT.parse("2021-01-24 06:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        scsSlowLogRequest.setType(ScsSlowLogType.Proxy);
        scsSlowLogRequest.setNodeId("scs-bj-kmazymvcuenz_proxy_0");
        ScsSlowLogResponse slowLogResponse = scsClient.getSlowLog(scsSlowLogRequest);
        print(logger, "getSlowLog", slowLogResponse);
    }

    @Test
    public void modifySlowLog() {
        ScsSlowLogModifyRequest slowLogModifyRequest = new ScsSlowLogModifyRequest();
        slowLogModifyRequest.setInstanceId(instanceInfo.getInstanceId());
        slowLogModifyRequest.setAction(ScsSlowLogAction.OPEN);
        AbstractBceResponse response = scsClient.modifySlowLog(slowLogModifyRequest);
        print(logger, "modifySlowLog", response);
    }

    @Test
    public void getZoneList() {
        ScsZoneResponse zoneResponse = scsClient.getZoneList();
        print(logger, "getZoneList", zoneResponse);
    }

    @Test
    public void getSubnetList() {
        ScsSubnetRequest request = readJson("scs/subnet_request.json", ScsSubnetRequest.class);
        ScsSubnetResponse subnetResponse = scsClient.getSubnetList(request);
        print(logger, "getSubnetList", subnetResponse);
    }

    @Test
    public void releaseInstance() {
        ScsReleaseInstanceRequest request = readJson("scs/release_instance.json", ScsReleaseInstanceRequest.class);
        AbstractBceResponse response = scsClient.releaseInstance(request);
        print(logger, "releaseInstance", response);
    }

    @Test
    public void checkClientAuth() {
        String clientAuth = "abcdefgh";
        ScsArgumentUtil.checkClientAuth(clientAuth);
    }
}