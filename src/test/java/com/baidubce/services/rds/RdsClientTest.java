package com.baidubce.services.rds;

import com.baidubce.BceClientException;
import com.baidubce.BceConstants;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.model.DatabasePrivilege;
import com.baidubce.services.rds.model.RdsAccount;
import com.baidubce.services.rds.model.RdsAccountInfoRequest;
import com.baidubce.services.rds.model.RdsAccountListRequest;
import com.baidubce.services.rds.model.RdsAccountListResponse;
import com.baidubce.services.rds.model.RdsAccountPrivileges;
import com.baidubce.services.rds.model.RdsAccountType;
import com.baidubce.services.rds.model.RdsAutoRenewRequest;
import com.baidubce.services.rds.model.RdsBackupInfoRequest;
import com.baidubce.services.rds.model.RdsBackupInfoResponse;
import com.baidubce.services.rds.model.RdsBilling;
import com.baidubce.services.rds.model.RdsBindingTagsRequest;
import com.baidubce.services.rds.model.RdsChangeDatabasePortRequest;
import com.baidubce.services.rds.model.RdsCharacterSet;
import com.baidubce.services.rds.model.RdsClusterStatusCheckRequest;
import com.baidubce.services.rds.model.RdsClusterStatusCheckResponse;
import com.baidubce.services.rds.model.RdsConnInformationRequest;
import com.baidubce.services.rds.model.RdsCreateAccountRequest;
import com.baidubce.services.rds.model.RdsCreateDatabaseRequest;
import com.baidubce.services.rds.model.RdsCreateInstanceRequest;
import com.baidubce.services.rds.model.RdsCreateInstanceResponse;
import com.baidubce.services.rds.model.RdsCreateProxyInstance;
import com.baidubce.services.rds.model.RdsCreateReadableInstance;
import com.baidubce.services.rds.model.RdsDatabasePrivileges;
import com.baidubce.services.rds.model.RdsDeleteAccountRequest;
import com.baidubce.services.rds.model.RdsDeleteDatabaseRequest;
import com.baidubce.services.rds.model.RdsDeleteSpecifiedBackUpRequest;
import com.baidubce.services.rds.model.RdsDialingTestRequest;
import com.baidubce.services.rds.model.RdsDialingTestResponse;
import com.baidubce.services.rds.model.RdsEngine;
import com.baidubce.services.rds.model.RdsFullPhysicalBackupRequest;
import com.baidubce.services.rds.model.RdsGetAutoConfigForSpecifiedRequest;
import com.baidubce.services.rds.model.RdsGetAutoConfigForSpecifiedResponse;
import com.baidubce.services.rds.model.RdsGetBackupListRequest;
import com.baidubce.services.rds.model.RdsGetBackupListResponse;
import com.baidubce.services.rds.model.RdsGetBinLogInfoRequest;
import com.baidubce.services.rds.model.RdsGetBinLogListRequest;
import com.baidubce.services.rds.model.RdsGetDatabaseListRequest;
import com.baidubce.services.rds.model.RdsGetNewPurchasePriceRequest;
import com.baidubce.services.rds.model.RdsGetNewPurchasePriceResponse;
import com.baidubce.services.rds.model.RdsGetOrderStatusRequest;
import com.baidubce.services.rds.model.RdsGetPGListRequest;
import com.baidubce.services.rds.model.RdsGetPGListResponse;
import com.baidubce.services.rds.model.RdsGetPGLogDetailsRequest;
import com.baidubce.services.rds.model.RdsGetPGLogDetailsResponse;
import com.baidubce.services.rds.model.RdsGetPriceDifferenceRequest;
import com.baidubce.services.rds.model.RdsGetPriceDifferenceResponse;
import com.baidubce.services.rds.model.RdsInstanceDetailRequest;
import com.baidubce.services.rds.model.RdsInstanceDetailResponse;
import com.baidubce.services.rds.model.RdsInstanceListRequest;
import com.baidubce.services.rds.model.RdsInstanceListResponse;
import com.baidubce.services.rds.model.RdsInstanceResizeRequest;
import com.baidubce.services.rds.model.RdsModifyAccountPasswordRequest;
import com.baidubce.services.rds.model.RdsModifyAccountPermissionRequest;
import com.baidubce.services.rds.model.RdsModifyAccountRemarksRequest;
import com.baidubce.services.rds.model.RdsModifyBackupRequest;
import com.baidubce.services.rds.model.RdsModifyDatabaseDescriptionRequest;
import com.baidubce.services.rds.model.RdsModifyParameter;
import com.baidubce.services.rds.model.RdsModifyParameterRequest;
import com.baidubce.services.rds.model.RdsNetworkStatusRequest;
import com.baidubce.services.rds.model.RdsParameter;
import com.baidubce.services.rds.model.RdsParameterListRequest;
import com.baidubce.services.rds.model.RdsParameterListResponse;
import com.baidubce.services.rds.model.RdsPaymentTiming;
import com.baidubce.services.rds.model.RdsRecoverForBackUpSetRequest;
import com.baidubce.services.rds.model.RdsRecoverForTimeNodeRequest;
import com.baidubce.services.rds.model.RdsReleaseInstanceRequest;
import com.baidubce.services.rds.model.RdsReleaseInstanceResponse;
import com.baidubce.services.rds.model.RdsRenewTimeUnit;
import com.baidubce.services.rds.model.RdsRenewalRequest;
import com.baidubce.services.rds.model.RdsRenewalResponse;
import com.baidubce.services.rds.model.RdsReservation;
import com.baidubce.services.rds.model.RdsRestartRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadDetailRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadDetailResponse;
import com.baidubce.services.rds.model.RdsSlowLogDownloadTasksRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadTasksResponse;
import com.baidubce.services.rds.model.RdsSlowLogErrorLogDownloadDetailsRequest;
import com.baidubce.services.rds.model.RdsSlowLogErrorLogDownloadDetailsResponse;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogDetailsRequest;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogDetailsResponse;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogListRequest;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogListResponse;
import com.baidubce.services.rds.model.RdsSlowLogRequest;
import com.baidubce.services.rds.model.RdsSlowLogResponse;
import com.baidubce.services.rds.model.RdsSubnet;
import com.baidubce.services.rds.model.RdsSubnetMap;
import com.baidubce.services.rds.model.RdsSubnetRequest;
import com.baidubce.services.rds.model.RdsSubnetResponse;
import com.baidubce.services.rds.model.RdsSupportEnableAutoExpansionRequest;
import com.baidubce.services.rds.model.RdsSupportEnableAutoExpansionResponse;
import com.baidubce.services.rds.model.RdsSupportHotSwappingRequest;
import com.baidubce.services.rds.model.RdsSupportHotSwappingResponse;
import com.baidubce.services.rds.model.RdsSyncModeRequest;
import com.baidubce.services.rds.model.RdsTag;
import com.baidubce.services.rds.model.RdsUpdateNameRequest;
import com.baidubce.services.rds.model.RdsUpdateStorageAutoExpansionConfigRequest;
import com.baidubce.services.rds.model.RdsUpdateStorageAutoExpansionConfigResPonse;
import com.baidubce.services.rds.model.RdsUpdateTimeWindowRequest;
import com.baidubce.services.rds.model.RdsUpdateWriteListResquest;
import com.baidubce.services.rds.model.RdsViewWriteListRequest;
import com.baidubce.services.rds.model.RdsViewWriteListResponse;
import com.baidubce.services.rds.model.RdsZoneResponse;
import com.baidubce.services.rds.model.RecoveryToSourceInstanceModel;
import com.baidubce.services.rds.model.RecoveryToSourceInstanceTableModel;
import com.baidubce.services.rds.model.Resource;
import com.baidubce.services.rds.model.SecurityIps;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

/**
 * Rds client test
 */
public class RdsClientTest extends BaseRdsTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RdsClientTest.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(BceConstants.DEFAULT_DATETIME_FORMAT);

    private static final HttpResponseHandler[] RDS_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new RdsResponseTestHandler(),
    };

    private static void print(String method, Object obj) {
        try {
            String json = JsonUtils.toJsonPrettyString(obj);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private RdsClient rdsClient;

    @Before
    public void config() {
        RdsClientConfiguration configuration = setup("rds/config.json");
        rdsClient = new RdsClient(configuration, RDS_HANDLERS);
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @Test
    public void signedPassword() {
        try {
            String password = aes128WithFirst16Char(rdsConfig.getPassword(), rdsConfig.getSk());
            print("signedPassword", password);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    private String aes128WithFirst16Char(String content, String privateKey) throws GeneralSecurityException {
        byte[] crypted = null;
        SecretKeySpec skey = new SecretKeySpec(privateKey.substring(0, 16).getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        crypted = cipher.doFinal(content.getBytes());
        return new String(Hex.encodeHex(crypted));
    }

    @Test
    public void getInstanceList() {
        RdsInstanceListRequest request = new RdsInstanceListRequest();
        RdsInstanceListResponse rdsInstanceList = rdsClient.getRdsInstanceList(request);
        print("getInstanceList", rdsInstanceList);
    }

    @Test
    public void getInstanceDetail() {
        RdsInstanceDetailRequest detailRequest = new RdsInstanceDetailRequest();
        detailRequest.setInstanceId("rds-17JzX6Wm");
        RdsInstanceDetailResponse detailResponse = rdsClient.getRdsInstanceDetail(detailRequest);
        print("getInstanceDetail", detailResponse);
    }

    @Test
    public void createAccount() {
        RdsCreateAccountRequest createAccountRequest = new RdsCreateAccountRequest();
        createAccountRequest.setInstanceId("rds-nr2GCznE");
        createAccountRequest.setAccountName("test_acc");
        createAccountRequest.setPassword("rds_passwordKJH");
        createAccountRequest.setAccountType(RdsAccountType.Common);
        DatabasePrivilege privilege = new DatabasePrivilege();
        privilege.setDbName("test");
        privilege.setAuthType("ReadOnly");
        List<DatabasePrivilege> privileges = new ArrayList<>();
        privileges.add(privilege);
        createAccountRequest.setDatabasePrivileges(privileges);
        createAccountRequest.setDesc("rds_sdk_created_createAccount_test");
        createAccountRequest.setType("OnlyMaster");
        try {
            AbstractBceResponse accountResponse = rdsClient.createAccount(createAccountRequest);
            print("createAccount", accountResponse);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAccountList() {
        RdsAccountListRequest listRequest = new RdsAccountListRequest();
        listRequest.setInstanceId("rds-jHqrZCEk");
        RdsAccountListResponse listResponse = rdsClient.getAccountList(listRequest);
        print("getAccountList", listResponse);
    }

    @Test
    public void getSpecificAccountInfo() {
        RdsAccountInfoRequest infoRequest = new RdsAccountInfoRequest();
        infoRequest.setInstanceId("rds-jHqrZCEk");
        infoRequest.setAccountName("test_account123");
        RdsAccount accountInfo = rdsClient.getSpecificAccountInfo(infoRequest);
        print("getSpecificAccountInfo", accountInfo);
    }

    @Test
    public void resizeInstance() {
        RdsInstanceResizeRequest resizeRequest = new RdsInstanceResizeRequest();
        resizeRequest.setInstanceId("rds-6cSY7hOj");
        resizeRequest.setVolumeCapacity(10);
        resizeRequest.setIsDirectPay(true);
        AbstractBceResponse resizeResponse = rdsClient.resizeInstance(resizeRequest);
        print("resizeInstance", resizeResponse);
    }

    @Test
    public void createInstance() {
        RdsCreateInstanceRequest request = new RdsCreateInstanceRequest();
        RdsBilling billing = new RdsBilling();
        RdsPaymentTiming rdsPaymentTiming = RdsPaymentTiming.create("Prepaid");
        RdsReservation reservation = new RdsReservation();
        reservation.setReservationLength(1);
        billing.setPaymentTiming(rdsPaymentTiming);
        billing.setReservation(reservation);
        request.setBilling(billing);

        RdsRenewTimeUnit renewTimeUnit = RdsRenewTimeUnit.create("year");
        request.setAutoRenewTimeUnit(renewTimeUnit);
        request.setAutoRenewTime(1);

        request.setPurchaseCount(1);
        request.setInstanceName("rds-010");

        request.setEngine(RdsEngine.MySQL);
        request.setEngineVersion("5.6");
        request.setCharacterSetName("utf8mb4");
        request.setCpuCount(1);
        request.setMemoryCapacity(2);
        request.setVolumeCapacity(5);
        request.setDiskIoType("normal_io");

        List<String> zoneNames = new ArrayList<>();
        zoneNames.add("cn-bj-d");
        request.setZoneNames(zoneNames);

        request.setVpcId("vpc-70pxg3pmv8rv");
        request.setIsDirectPay(true);
        RdsSubnet subnet = new RdsSubnet();
        subnet.setSubnetId("sbn-dqafncqsy3y4");
        subnet.setZoneName("cn-bj-d");

        List<RdsSubnet> subnets = new ArrayList<>();
        subnets.add(subnet);

        request.setSubnets(subnets);


        RdsTag tag = new RdsTag();
        tag.setTagKey("goods_type");
        tag.setTagValue("music");

        List<RdsTag> tags = new ArrayList<>();
        tags.add(tag);
        request.setTags(tags);


        print("request is ", request);
        RdsCreateInstanceResponse instanceResponse = rdsClient.createInstance(request);
        print("createInstance", instanceResponse);
    }

    @Test
    public void getSlowLog() throws ParseException {
        RdsSlowLogRequest request = new RdsSlowLogRequest();
        request.setInstanceId(rdsConfig.getInstanceId());
        request.setStartTime(DATE_FORMAT.parse("2022-02-08T00:00:00Z"));
        request.setEndTime(DATE_FORMAT.parse("2022-02-09T00:00:00Z"));
        RdsSlowLogResponse slowLogResponse = rdsClient.getSlowLog(request);
        print("getSlowLog", slowLogResponse);
    }

    @Test
    public void autoRenew() {
        RdsAutoRenewRequest autoRenewRequest = new RdsAutoRenewRequest();
        autoRenewRequest.setAutoRenewTimeUnit(RdsRenewTimeUnit.MONTH);
        autoRenewRequest.addInstanceId(rdsConfig.getInstanceId());
        AbstractBceResponse response = rdsClient.autoRenew(autoRenewRequest);
        print("autoRenew", response);
    }

    @Test
    public void createReadReplicaInstance() {

        RdsCreateReadableInstance createReadableInstance = new RdsCreateReadableInstance();

        RdsBilling billing = new RdsBilling();
        RdsPaymentTiming rdsPaymentTiming = RdsPaymentTiming.create("Postpaid");
        RdsReservation reservation = new RdsReservation();
        reservation.setReservationLength(1);
        billing.setPaymentTiming(rdsPaymentTiming);
        billing.setReservation(reservation);
        createReadableInstance.setBilling(billing);

        createReadableInstance.setInstanceName("rds-readableInstance");
        createReadableInstance.setCpuCount(1);
        createReadableInstance.setMemoryCapacity(2);
        createReadableInstance.setVolumeCapacity(60);

        createReadableInstance.setDiskIoType("normal_io");

        createReadableInstance.setIsDirectPay(false);

        createReadableInstance.setPurchaseCount(1);
        createReadableInstance.setInstanceName("rds-TestMyqlPostpaid");
        String sourceInstanceId = "rds-N2UsU5lo";
        createReadableInstance.setSourceInstanceId(sourceInstanceId);

        String zoneName = "cn-bj-d";
        List<String> zoneNames = new ArrayList<>();
        zoneNames.add(zoneName);
        createReadableInstance.setZoneNames(zoneNames);
        createReadableInstance.setVpcId("vpc-70pxg3pmv8rv");

        RdsSubnet subnet = new RdsSubnet();
        subnet.setSubnetId("sbn-dqafncqsy3y4");
        subnet.setZoneName("cn-bj-d");
        List<RdsSubnet> subnets = new ArrayList<>();
        subnets.add(subnet);
        createReadableInstance.setSubnets(subnets);
        createReadableInstance.setEntryPort(3306);

        RdsCreateInstanceResponse response =
                rdsClient.createInstanceReadableReplica(createReadableInstance);
        print("createReadableInstance", response);
    }

    @Test
    public void createProxyInstance() {
        RdsCreateProxyInstance proxyInstance = new RdsCreateProxyInstance();

        RdsBilling billing = new RdsBilling();
        RdsPaymentTiming rdsPaymentTiming = RdsPaymentTiming.create("Postpaid");
        billing.setPaymentTiming(rdsPaymentTiming);

        proxyInstance.setSourceInstanceId("rds-N2UsU5lo");
        proxyInstance.setInstanceName("rds-TestMyqlPostpaid");

        proxyInstance.setNodeAmount(2);

        List<String> zoneNames = new ArrayList<>();
        zoneNames.add("cn-bj-d");
        proxyInstance.setZoneNames(zoneNames);

        proxyInstance.setVpcId("vpc-70pxg3pmv8rv");

        RdsSubnet subnet = new RdsSubnet();
        subnet.setSubnetId("sbn-dqafncqsy3y4");
        subnet.setZoneName("cn-bj-d");

        proxyInstance.setIsDirectPay(false);

        subnet.setZoneName("cn-bj-d");
        List<RdsSubnet> subnets = new ArrayList<>();
        subnets.add(subnet);
        proxyInstance.setSubnets(subnets);

        RdsCreateInstanceResponse response = rdsClient.createInstanceProxy(proxyInstance);
        print("createProxyInstance", response);
    }

    @Test
    public void getZoneList() {
        RdsZoneResponse zoneResponse = rdsClient.getZoneList();
        print("getZoneList", zoneResponse);
    }

    @Test
    public void getSubnetList() {
        RdsSubnetRequest request = new RdsSubnetRequest();
        RdsSubnetResponse subnetResponse = rdsClient.getSubnetList(request);
        print("getSubnetList", subnetResponse);
    }

    @Test
    public void releaseInstance() {
        RdsReleaseInstanceRequest request = new RdsReleaseInstanceRequest();
        String instanceId = "rds-v1JOe6Di";
        List<String> instanceIds = new ArrayList<>();
        instanceIds.add(instanceId);
        request.setInstanceIds(instanceIds);
        RdsReleaseInstanceResponse response = rdsClient.releaseInstance(request);
        print("releaseInstance", response);
    }

    @Test
    public void createDatabase() {
        RdsCreateDatabaseRequest request = new RdsCreateDatabaseRequest();
        request.setInstanceId("rds-Qk2LpXBj");
        request.setCharacterSetName(RdsCharacterSet.UTF8);
        request.setDbName("testMysqlDatabase");
        request.setRemark("testRemark");
        RdsAccountPrivileges privileges = new RdsAccountPrivileges();
        privileges.setAccountName("rdsroot1");
        privileges.setAuthType("ReadWrite");
        List<RdsAccountPrivileges> privilegesList = new ArrayList<>();
        privilegesList.add(privileges);
        request.setAccountPrivileges(privilegesList);
        request.setCtype("zh_CN.utf-8");
        request.setCollate("zh_CN.utf-8");
        request.setOwner("rdsroot1");
        AbstractBceResponse response = rdsClient.createDatabase(request);
        print("createDatabase", response);
    }

    @Test
    public void deleteDatabase() {
        RdsDeleteDatabaseRequest request = new RdsDeleteDatabaseRequest();
        request.setInstanceId("rds-Qk2LpXBj");
        request.setDbName("testMysqlDatabase");
        AbstractBceResponse response = rdsClient.deleteDatabase(request);
        print("deleteDatabase", response);
    }

    @Test
    public void modifyBackup() {
        RdsModifyBackupRequest request = readJson("rds/modify_backup_policy.json", RdsModifyBackupRequest.class);
        AbstractBceResponse response = rdsClient.modifyBackup(request);
        print("modifyBackup", response);
    }

    @Test
    public void getBackupList() {
        RdsGetBackupListRequest request = readJson("rds/backup_list.json", RdsGetBackupListRequest.class);
        RdsGetBackupListResponse backupList = rdsClient.getBackupList(request);
        print("getBackupList", backupList);
    }

    @Test
    public void getBackupInfo() {
        RdsBackupInfoRequest request = readJson("rds/backup_info.json", RdsBackupInfoRequest.class);
        RdsBackupInfoResponse backupInfo = rdsClient.getBackupInfo(request);
        print("getBackupInfo", backupInfo);
    }

    @Test
    public void getSlowLogDownloadTaskList() {
        RdsSlowLogDownloadTasksRequest request = new RdsSlowLogDownloadTasksRequest();
        request.setInstanceId("rds-N5CfquAn");
        request.setDatetime("2024-07-11T19:48:05Z");
        RdsSlowLogDownloadTasksResponse response = rdsClient.getSlowLogDownloadTaskList(request);
        print("getSlowLogDownloadTaskList", response);
    }

    @Test
    public void getSlowLogDownloadDetail() {
        RdsSlowLogDownloadDetailRequest request = new RdsSlowLogDownloadDetailRequest();
        request.setInstanceId("rds-jHqrZCEk");
        request.setLogId("postgresql-2024-07-12_000000.log");
        request.setDownloadValidTimeInSec(1800);
        RdsSlowLogDownloadDetailResponse detailResponse = rdsClient.getSlowLogDownloadDetail(request);
        print("getSlowLogDownloadDetail", detailResponse);
    }

    @Test
    public void getParameterList() {
        RdsParameterListRequest request = new RdsParameterListRequest();
        request.setInstanceId("rds-jHqrZCEk");
        RdsParameterListResponse parameterList = rdsClient.getParameterList(request);
        print("getParameterList", parameterList);
    }

    @Test
    public void modifyParameter() {
        RdsParameterListRequest parameterRequest = new RdsParameterListRequest();
        parameterRequest.setInstanceId("rds-jHqrZCEk");
        RdsParameterListResponse parameterList = rdsClient.getParameterList(parameterRequest);
        List<RdsParameter> parameters = parameterList.getParameters();
        RdsModifyParameter modifyParameter = null;
        String etag = null;
        if (CollectionUtils.isNotEmpty(parameters)) {
            for (RdsParameter rdsParameter : parameters) {
                if ("connect_timeout".equalsIgnoreCase(rdsParameter.getName())) {
                    modifyParameter = new RdsModifyParameter(rdsParameter.getName(), rdsParameter.getValue());
                    etag = rdsParameter.getEtag();
                    break;
                }
            }
        }
        modifyParameter.setValue("10");
        modifyParameter.setApplyMethod("timewindow");
        modifyParameter.setEtag(etag);
        if (modifyParameter == null || StringUtils.isEmpty(etag)) {
            throw new BceClientException("modifyParameter is null");
        }

        RdsModifyParameterRequest request = new RdsModifyParameterRequest();
        request.setInstanceId(parameterRequest.getInstanceId());
        request.addParameters(modifyParameter);
        request.setEffectiveTime("timewindow");
        AbstractBceResponse response = rdsClient.modifyParameter(request);
        print("modifyParameter", response);
    }

    @Test
    public void testUpdateRdsPublicNetworkAccessStatus() {
        RdsNetworkStatusRequest request = new RdsNetworkStatusRequest();
        request.setInstanceId("rds-17JzX6Wm");
        request.setPublicAccess(false);
        AbstractBceResponse response = rdsClient.updateRdsPublicNetworkAccessStatus(request);
        print("updateRdsPublicNetworkAccessStatus", response);
    }

    @Test
    public void testUpdateRdsSyncMode() {
        RdsSyncModeRequest request = new RdsSyncModeRequest();
        request.setSyncMode("async");
        request.setInstanceId("rds-17JzX6Wm");
        AbstractBceResponse response = rdsClient.updateRdsSyncMode(request);
        print("updateRdsSyncMode", response);
    }

    @Test
    public void testUpdateRdsName() {
        RdsUpdateNameRequest request = new RdsUpdateNameRequest();
        request.setInstanceId("rds-17JzX6Wm");
        request.setInstanceName("gaojianPGSQL");
        AbstractBceResponse response = rdsClient.updateRdsName(request);
        print("updateRdsName", response);
    }

    @Test
    public void testUpdateRdsConnectionInformation() {
        RdsConnInformationRequest request = new RdsConnInformationRequest();
        request.setInstanceId("rds-6f17R5R3");
        request.setAddress("fkajsdlk");
        AbstractBceResponse response = rdsClient.updateRdsConnectionInformation(request);
        print("updateRdsConnectionInformation", response);
    }

    @Test
    public void testRdsRestart() {
        RdsRestartRequest request = new RdsRestartRequest();
        request.setInstanceId("rds-6QCusckC");
        AbstractBceResponse response = rdsClient.rdsRestart(request);
    }

    @Test
    public void testRdsRenewal() {
        RdsRenewalRequest request = new RdsRenewalRequest();
        request.setDuration("1");
        List<String> id = new ArrayList<>();
        id.add("rds-6f17R5R3");
        request.setInstanceIds(id);
        RdsRenewalResponse response = rdsClient.rdsRenewal(request);
        print("rdsRenewal", response);
    }

    @Test
    public void testRdsUpdateTimeWindow() {
        RdsUpdateTimeWindowRequest request = new RdsUpdateTimeWindowRequest();
        request.setInstanceId("rds-7xabvFUH");
        request.setMaintainDuration(1);
        request.setMaintainStartTime("01:00:00");
        AbstractBceResponse response = rdsClient.rdsUpdateTimeWindow(request);
        print("rdsUpdateTimeWindow", response);
    }

    @Test
    public void testUpdateRdsStorageAutoExpansionConfig() {
        RdsUpdateStorageAutoExpansionConfigRequest request = new RdsUpdateStorageAutoExpansionConfigRequest();
        request.setInstanceId("rds-lZIdjcC3");
        request.setAction("close");
        RdsUpdateStorageAutoExpansionConfigResPonse response = rdsClient.updateRdsStorageAutoExpansionConfig(request);
        print("updateRdsStorageAutoExpansionConfig", response);
    }

    @Test
    public void testGetAutoConfigForSpecified() {
        RdsGetAutoConfigForSpecifiedRequest request = new RdsGetAutoConfigForSpecifiedRequest();
        request.setInstanceId("rds-7xabvFUH");
        RdsGetAutoConfigForSpecifiedResponse response = rdsClient.getAutoConfigForSpecified(request);
        print("testGetAutoConfigForSpecified", response);
    }

    @Test
    public void testSupportEnableAutoExpansion() {
        RdsSupportEnableAutoExpansionRequest request = new RdsSupportEnableAutoExpansionRequest();
        request.setInstanceId("rds-k1dffnQn");
        RdsSupportEnableAutoExpansionResponse response = rdsClient.supportEnableAutoExpansion(request);
        print("supportEnableAutoExpansion", response);
    }

//    @Test
//    public void testavailableZoneMigration() {
//        RdsZoneMigrationRequest request = new RdsZoneMigrationRequest();
//        RdsSubnetMap subnetMap = new RdsSubnetMap();
//        subnetMap.setSubnetId("sbn-dqafncqsy3y4");
//        subnetMap.setZoneName(null);
//        List<RdsSubnetMap> subnets = new ArrayList<>();
//        subnets.add(subnetMap);
//        request.setSubnets(subnets);
//        request.setInstanceId("rds-7xabvFUH");
//        request.setMasterAzone("cn-bj-d");
//        request.setBackupAzone("cn-bj-d");
//        List<String> zoneNames = new ArrayList<>();
//        zoneNames.add("");
//        request.setEffectiveTime("timewindow");
//        request.setZoneNames(zoneNames);
//        RdsZoneMigrationResponse response = rdsClient.availableZoneMigration(request);
//        print("rdsRenewal", response);
//    }

    @Test
    public void testBindingTags() {
        RdsBindingTagsRequest request = new RdsBindingTagsRequest();
        RdsTag tag = new RdsTag();
        tag.setTagValue("testValue");
        tag.setTagKey("testKey");
        List<RdsTag> tags = new ArrayList<>();
        tags.add(tag);
        String instanceId = "rds-lZIdjcC3";
        Resource resource = new Resource(instanceId, tags);
        List<Resource> resources = new ArrayList<>();
        resources.add(resource);
        request.setResources(resources);
        AbstractBceResponse response = rdsClient.bindingTags(request);
        print("bindingTags", response);
    }

    @Test
    public void testClusterStatusCheck() {
        RdsClusterStatusCheckRequest request = new RdsClusterStatusCheckRequest();
        request.setInstanceId("rds-k1dffnQn");
        RdsClusterStatusCheckResponse response = rdsClient.clusterStatusCheck(request);
        print("clusterStatusCheck", response);
    }

    @Test
    public void testDialingTest() {
        RdsDialingTestRequest request = new RdsDialingTestRequest();
        request.setInstanceId("rds-6QCusckC");
        RdsDialingTestResponse response = rdsClient.dialingTest(request);
        print("dialingTest", response);
    }

    @Test
    public void testGetPriceDifference() {
        RdsGetPriceDifferenceRequest request = new RdsGetPriceDifferenceRequest();
        request.setInstanceId("rds-6QCusckC");
        request.setCpuCount(1);
        request.setAllocatedMemoryInGB(1);
        request.setAllocatedStorageInGB(50);
        request.setDiskIoType("cloud_high");
        RdsGetPriceDifferenceResponse response = rdsClient.getPriceDifference(request);
        print("getPriceDifference", response);
    }

    @Test
    public void testGetNewPurchasePrice() {
        RdsGetNewPurchasePriceRequest request = new RdsGetNewPurchasePriceRequest();
        request.setDuration(1);
        RdsGetNewPurchasePriceRequest.Instance instances = new RdsGetNewPurchasePriceRequest.Instance();
        instances.setEngine("MySQL");
        instances.setCpuCount(1);
        instances.setAllocatedMemoryInGB(1);
        instances.setAllocatedStorageInGB(50);
        instances.setDiskIoType("cloud_high");
        request.setInstance(instances);
        request.setNumber(1);
        request.setProductType("postpay");
        RdsGetNewPurchasePriceResponse response = rdsClient.getNewPurchasePrice(request);
        print("getNewPurchasePrice", response);
    }

    @Test
    public void testSupportHotSwapping() {
        RdsSupportHotSwappingRequest request = new RdsSupportHotSwappingRequest();
        request.setInstanceId("rds-6QCusckC");
        request.setBackupAzone("cn-bj-d");
        request.setAllocatedMemoryInMB(1024);
        request.setAllocatedStorageInGB(50);
        request.setCpuCount(1);
        request.setMasterAzone("cn-bj-d");
        request.setSubnetId("sbn-dqafncqsy3y4");
        RdsSupportHotSwappingResponse response = rdsClient.supportHotSwapping(request);
        print("supportHotSwapping", response);
    }

    @Test
    public void testModifyAccountRemark() {
        RdsModifyAccountRemarksRequest request = new RdsModifyAccountRemarksRequest();
        request.setInstanceId("rds-jHqrZCEk");
        request.setRemark("remark1111111944499994");
        request.setAccountName("test_account123");
        AbstractBceResponse response = rdsClient.modifyAccountRemark(request);
    }

    @Test
    public void testModifyAccountPermissions() {
        RdsModifyAccountPermissionRequest request = new RdsModifyAccountPermissionRequest();

        request.setInstanceId("rds-tXjFULZA");
        request.setAccountName("nosuper");

        DatabasePrivilege privilege = new DatabasePrivilege();
        privilege.setAuthType("ReadOnly");
        privilege.setDbName("nosuperdb");

        List<DatabasePrivilege> privileges = new ArrayList<>();
        privileges.add(privilege);
        request.setDatabasePrivileges(privileges);
        AbstractBceResponse response =rdsClient.modifyAccountPermissions(request);
    }

    @Test
    public void testModifyAccountPassword() throws GeneralSecurityException {
        RdsModifyAccountPasswordRequest request = new RdsModifyAccountPasswordRequest();
        request.setInstanceId("rds-tXjFULZA");
        request.setAccountName("nosuper");
        request.setPassword("123jklMN");
        AbstractBceResponse response = rdsClient.modifyAccountPassword(request);
    }

    @Test
    public void testDeleteAccount() {
        RdsDeleteAccountRequest request = new RdsDeleteAccountRequest();
        request.setInstanceId("rds-RhmXpFKn");
        request.setAccountName("delaccount");
        AbstractBceResponse response = rdsClient.deleteAccount(request);
    }

    @Test
    public void testChangeDatabasePort() {
        RdsChangeDatabasePortRequest request = new RdsChangeDatabasePortRequest();
        request.setInstanceId("rds-Ml7QDBqz");
        request.setEntryPort(13206);
        AbstractBceResponse response = rdsClient.changeDatabasePort(request);
        print("changeDatabasePort", response);
    }

    @Test
    public void testGetDatabaseList() {
        RdsGetDatabaseListRequest request = new RdsGetDatabaseListRequest();
        request.setInstanceId("rds-Ml7QDBqz");
        AbstractBceResponse response = rdsClient.getDatabaseList(request);
    }

    @Test
    public void testModifyDatabaseDescription() {
        RdsModifyDatabaseDescriptionRequest request = new RdsModifyDatabaseDescriptionRequest();
        request.setInstanceId("rds-1a6K6qX8");
        request.setDbName("dbfakfl");
        request.setRemark("testRemark");
        AbstractBceResponse response = rdsClient.modifyDatabaseDescription(request);
    }

    @Test
    public void testViewWriteList() {
        RdsViewWriteListRequest request = new RdsViewWriteListRequest();
        request.setInstanceId("rds-5WIldjI3");
        RdsViewWriteListResponse response = rdsClient.viewWriteList(request);
        print("viewWriteList", response);
    }

    @Test
    public void testUpdateWriteList() {
        RdsUpdateWriteListResquest resquest = new RdsUpdateWriteListResquest();
        resquest.setInstanceId("rds-5WIldjI3");
        List<String> ips = Arrays.asList("%");
        resquest.setSecurityIps(ips);
        AbstractBceResponse response =rdsClient.updateWriteList(resquest);
        print("updateWriteList", response);
    }

//    @Test
//    public void testHotActiveInstanceGroupForcedCut() {
//        RdsHotActiveInstanceGroupForcedCutRequest request = new RdsHotActiveInstanceGroupForcedCutRequest();
////        request.setForce(1);
//        request.setGroupId("rdcf03azby8");
//        request.setLeaderId("rds-6f17R5R3");
////        request.setMaxBehind(10);
//        RdsHotActiveInstanceGroupForcedCutResponse response = rdsClient.hotActiveInstanceGroupForcedCut(request);
//        print("hotActiveInstanceGroupForcedCut", response);
//    }

    @Test
    public void testGetOrderStatus() {
        RdsGetOrderStatusRequest request = new RdsGetOrderStatusRequest();
        request.setOrderId("0fa2f32386184e7aa47233446b9614dd");
        AbstractBceResponse response = rdsClient.getOrderStatus(request);
        print("getOrderStatus", response);
    }

    @Test
    public void testManuallyInitiateFullPhysicalBackup() {
        RdsFullPhysicalBackupRequest request = new RdsFullPhysicalBackupRequest();
        request.setInstanceId("rds-5WIldjI3");
        AbstractBceResponse response = rdsClient.manuallyInitiateFullPhysicalBackup(request);
        print("manuallyInitiateFullPhysicalBackup", response);
    }

    @Test
    public void testDeleteTheSpecifiedBackupSet() {
        RdsDeleteSpecifiedBackUpRequest request = new RdsDeleteSpecifiedBackUpRequest();
        request.setInstanceId("rdsmsn17thg0i4p");
        request.setSnapshotId("1720671279584629601");
        AbstractBceResponse response = rdsClient.deleteTheSpecifiedBackupSet(request);
        print("deleteTheSpecifiedBackupSet", response);
    }

    @Test
    public void testGetBinLogList() {
        RdsGetBinLogListRequest request = new RdsGetBinLogListRequest();
        request.setInstanceId("rds-1Cm4tVAO");
        request.setDatetime("2024-07-11T06:48:29Z");
        AbstractBceResponse response = rdsClient.getBinLogList(request);
        print("getBinLogList", response);
    }

    @Test
    public void testGetBinLogInfo() {
        RdsGetBinLogInfoRequest request = new RdsGetBinLogInfoRequest();
        request.setInstanceId("rds-1Cm4tVAO");
        request.setLogId("1720685193973036601");
        request.setDownloadValidTimeInSec("1800");
        AbstractBceResponse response = rdsClient.getBinLogInfo(request);
        print("getBinLogInfo", response);
    }

    @Test
    public void testRecoverForTimeNode() {
        RdsRecoverForTimeNodeRequest request = new RdsRecoverForTimeNodeRequest();
        request.setInstanceId("rds-1Cm4tVAO");
        request.setSourceInstanceId("rds-1Cm4tVAO");
        request.setTargetInstanceId("rds-1Cm4tVAO");
        request.setDatetime("2024-07-10T18:36:46Z");

        List<RecoveryToSourceInstanceModel> datas = new ArrayList<>();
        RecoveryToSourceInstanceModel data = new RecoveryToSourceInstanceModel();

        List<RecoveryToSourceInstanceTableModel> tables = new ArrayList<>();
        RecoveryToSourceInstanceTableModel table = new RecoveryToSourceInstanceTableModel();

        table.setTableName("deadlock");
        table.setNewTablename("newdeadlock");
        tables.add(table);

        data.setDbName("cxtest");
        data.setNewDbname("newcxtest");
        data.setRestoreMode("table");
        data.setTables(tables);


        datas.add(data);
        request.setData(datas);
        AbstractBceResponse response = rdsClient.recoverForTimeNode(request);
        print("recoverForTimeNode", response);
    }

    @Test
    public void testRecoverForBackUpSet() {
        RdsRecoverForBackUpSetRequest request = new RdsRecoverForBackUpSetRequest();
        request.setInstanceId("rds-JC7L0yFI");
        request.setSourceInstanceId("rds-1Cm4tVAO");
        request.setTargetInstanceId("rds-1Cm4tVAO");
        request.setSnapshotId("1720637060912738501");
        List<RecoveryToSourceInstanceModel> datas = new ArrayList<>();
        RecoveryToSourceInstanceModel data = new RecoveryToSourceInstanceModel();

        List<RecoveryToSourceInstanceTableModel> tables = new ArrayList<>();
        RecoveryToSourceInstanceTableModel table = new RecoveryToSourceInstanceTableModel();

        table.setTableName("newcxtest");
        table.setNewTablename("cxtest");

        data.setDbName("newdeadlock");
        data.setNewDbname("deadlock");
        data.setRestoreMode("table");
        data.setTables(tables);

        datas.add(data);
        request.setData(datas);

        AbstractBceResponse response = rdsClient.recoverForBackUpSet(request);
        print("recoverForBackUpSet", response);
    }

    @Test
    public void testGetSlowLogGetErrorLogDetails() {
        RdsSlowLogGetErrorLogDetailsRequest request = new RdsSlowLogGetErrorLogDetailsRequest();
        request.setInstanceId("rds-b3VFCjMA");
        request.setStartTime("2024-07-10T16:00:00Z");
        request.setEndTime("2024-07-10T16:00:05Z");
        request.setPageNo("1");
        request.setPageSize("10");
        request.setKeyWord("Logging");
        RdsSlowLogGetErrorLogDetailsResponse response = rdsClient.getSlowLogGetErrorLogDetails(request);
        print("getSlowLogGetErrorLogDetails", response);
    }

    @Test
    public void testGetSlowLogErrorLogDownloadDetails() {
        RdsSlowLogErrorLogDownloadDetailsRequest request = new RdsSlowLogErrorLogDownloadDetailsRequest();
        request.setInstanceId("rds-1AfwpHXs");
        request.setLogId("postgresql-2024-07-11_000000.log");
        request.setDownloadValidTimeInSec(1090);
        RdsSlowLogErrorLogDownloadDetailsResponse response = rdsClient.getSlowLogErrorLogDownloadDetails(request);
        print("getSlowLogErrorLogDownloadDetails", response);
    }

    @Test
    public void testSlowLogGetErrorLogList() {
        RdsSlowLogGetErrorLogListRequest request = new RdsSlowLogGetErrorLogListRequest();
        request.setInstanceId("rds-jHqrZCEk");
        request.setDatetime("2024-07-11 00:00:00");
        RdsSlowLogGetErrorLogListResponse response = rdsClient.slowLogGetErrorLogList(request);
        print("slowLogGetErrorLogList", response);
    }

    @Test
    public void testGetPGLogDetails() {
        RdsGetPGLogDetailsRequest request = new RdsGetPGLogDetailsRequest();
        request.setInstanceId("rds-jHqrZCEk");
        request.setPglogId("postgresql-2024-07-11_000000.log");
        request.setDownloadValidTimeInSec(3600);
        RdsGetPGLogDetailsResponse response = rdsClient.getPGLogDetails(request);
        print("getPGLogDetails", response);
    }

    @Test
    public void testGetPGList() {
        RdsGetPGListRequest request = new RdsGetPGListRequest();
        request.setInstanceId("rds-jHqrZCEk");
        request.setDate("2023-06-14");
        RdsGetPGListResponse response = rdsClient.getPGList(request);
        print("getPGList", response);
    }
}
