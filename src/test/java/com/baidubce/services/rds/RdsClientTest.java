package com.baidubce.services.rds;

import com.baidubce.BceClientException;
import com.baidubce.BceConstants;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.model.RdsAccount;
import com.baidubce.services.rds.model.RdsAccountInfoRequest;
import com.baidubce.services.rds.model.RdsAccountListRequest;
import com.baidubce.services.rds.model.RdsAccountListResponse;
import com.baidubce.services.rds.model.RdsAutoRenewRequest;
import com.baidubce.services.rds.model.RdsBackupInfoRequest;
import com.baidubce.services.rds.model.RdsBackupInfoResponse;
import com.baidubce.services.rds.model.RdsCreateAccountRequest;
import com.baidubce.services.rds.model.RdsCreateDatabaseRequest;
import com.baidubce.services.rds.model.RdsCreateInstanceRequest;
import com.baidubce.services.rds.model.RdsCreateInstanceResponse;
import com.baidubce.services.rds.model.RdsCreateProxyInstance;
import com.baidubce.services.rds.model.RdsCreateReadableInstance;
import com.baidubce.services.rds.model.RdsDeleteDatabaseRequest;
import com.baidubce.services.rds.model.RdsEngine;
import com.baidubce.services.rds.model.RdsGetBackupListRequest;
import com.baidubce.services.rds.model.RdsGetBackupListResponse;
import com.baidubce.services.rds.model.RdsInstanceDetailRequest;
import com.baidubce.services.rds.model.RdsInstanceDetailResponse;
import com.baidubce.services.rds.model.RdsInstanceListRequest;
import com.baidubce.services.rds.model.RdsInstanceListResponse;
import com.baidubce.services.rds.model.RdsInstanceResizeRequest;
import com.baidubce.services.rds.model.RdsModifyBackupRequest;
import com.baidubce.services.rds.model.RdsModifyParameter;
import com.baidubce.services.rds.model.RdsModifyParameterRequest;
import com.baidubce.services.rds.model.RdsParameter;
import com.baidubce.services.rds.model.RdsParameterListRequest;
import com.baidubce.services.rds.model.RdsParameterListResponse;
import com.baidubce.services.rds.model.RdsReleaseInstanceRequest;
import com.baidubce.services.rds.model.RdsReleaseInstanceResponse;
import com.baidubce.services.rds.model.RdsRenewTimeUnit;
import com.baidubce.services.rds.model.RdsSlowLogDownloadDetailRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadDetailResponse;
import com.baidubce.services.rds.model.RdsSlowLogDownloadTasksRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadTasksResponse;
import com.baidubce.services.rds.model.RdsSlowLogRequest;
import com.baidubce.services.rds.model.RdsSlowLogResponse;
import com.baidubce.services.rds.model.RdsSubnetRequest;
import com.baidubce.services.rds.model.RdsSubnetResponse;
import com.baidubce.services.rds.model.RdsZoneResponse;
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
            LOGGER.info("[{}]==>\n{}", method, json);
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
        detailRequest.setInstanceId(rdsConfig.getInstanceId());
        RdsInstanceDetailResponse detailResponse = rdsClient.getRdsInstanceDetail(detailRequest);
        print("getInstanceDetail", detailResponse);
    }

    @Test
    public void createAccount() {
        RdsCreateAccountRequest createAccountRequest = new RdsCreateAccountRequest();
        createAccountRequest.setInstanceId(rdsConfig.getInstanceId());
        createAccountRequest.setAccountName("rds_test_name");
        createAccountRequest.setPassword("rds_password");
        createAccountRequest.setDesc("rds_sdk_created");
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
        listRequest.setInstanceId(rdsConfig.getInstanceId());
        RdsAccountListResponse listResponse = rdsClient.getAccountList(listRequest);
        print("getAccountList", listResponse);
    }

    @Test
    public void getSpecificAccountInfo() {
        RdsAccountInfoRequest infoRequest = new RdsAccountInfoRequest();
        infoRequest.setInstanceId(rdsConfig.getInstanceId());
        infoRequest.setAccountName(rdsConfig.getAccountName());
        RdsAccount accountInfo = rdsClient.getSpecificAccountInfo(infoRequest);
        print("getSpecificAccountInfo", accountInfo);
    }

    @Test
    public void resizeInstance() {
        RdsInstanceResizeRequest resizeRequest = new RdsInstanceResizeRequest();
        resizeRequest.setInstanceId(rdsConfig.getInstanceId());
        resizeRequest.setVolumeCapacity(10);
        resizeRequest.setIsDirectPay(true);
        AbstractBceResponse resizeResponse = rdsClient.resizeInstance(resizeRequest);
        print("resizeInstance", resizeResponse);
    }

    @Test
    public void createInstance() {
        RdsCreateInstanceRequest request = new RdsCreateInstanceRequest();
        request.setEngine(RdsEngine.MySQL);
        request.setEngineVersion("5.7");
        request.setCpuCount(1);
        request.setMemoryCapacity(1);
        request.setVolumeCapacity(5);
        request.setIsDirectPay(true);
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
    public void createReadableInstance() {
        RdsCreateReadableInstance createReadableInstance = new RdsCreateReadableInstance();
        createReadableInstance.setInstanceName("sdk_created_readable");
        createReadableInstance.setSourceInstanceId(rdsConfig.getInstanceId());
        createReadableInstance.setCpuCount(1);
        createReadableInstance.setMemoryCapacity(1);
        createReadableInstance.setVolumeCapacity(5);
        createReadableInstance.setIsDirectPay(true);
        RdsCreateInstanceResponse response =
                rdsClient.createInstanceReadableReplica(createReadableInstance);
        print("createReadableInstance", response);
    }

    @Test
    public void createProxyInstance() {
        RdsCreateProxyInstance proxyInstance = new RdsCreateProxyInstance();
        proxyInstance.setInstanceName("sdk_created_proxy");
        proxyInstance.setSourceInstanceId(rdsConfig.getInstanceId());
        proxyInstance.setNodeAmount(2);
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
        RdsReleaseInstanceRequest request = readJson("rds/release_instance.json", RdsReleaseInstanceRequest.class);
        RdsReleaseInstanceResponse response = rdsClient.releaseInstance(request);
        print("releaseInstance", response);
    }

    @Test
    public void createDatabase() {
        RdsCreateDatabaseRequest request = readJson("rds/create_database.json", RdsCreateDatabaseRequest.class);
        AbstractBceResponse response = rdsClient.createDatabase(request);
        print("createDatabase", response);
    }

    @Test
    public void deleteDatabase() {
        RdsDeleteDatabaseRequest request = readJson("rds/delete_database.json", RdsDeleteDatabaseRequest.class);
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
        String jsonFile = "rds/slow_log_download_task.json";
        RdsSlowLogDownloadTasksRequest request = readJson(jsonFile, RdsSlowLogDownloadTasksRequest.class);
        RdsSlowLogDownloadTasksResponse response = rdsClient.getSlowLogDownloadTaskList(request);
        print("getSlowLogDownloadTaskList", response);
    }

    @Test
    public void getSlowLogDownloadDetail() {
        String jsonFile = "rds/slow_log_download_detail.json";
        RdsSlowLogDownloadDetailRequest request = readJson(jsonFile, RdsSlowLogDownloadDetailRequest.class);
        RdsSlowLogDownloadDetailResponse detailResponse = rdsClient.getSlowLogDownloadDetail(request);
        print("getSlowLogDownloadDetail", detailResponse);
    }

    @Test
    public void getParameterList() {
        RdsParameterListRequest request = readJson("rds/get_parameter.json", RdsParameterListRequest.class);
        RdsParameterListResponse parameterList = rdsClient.getParameterList(request);
        print("getParameterList", parameterList);
    }

    @Test
    public void modifyParameter() {
        RdsParameterListRequest parameterRequest = readJson("rds/get_parameter.json", RdsParameterListRequest.class);
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
        if (modifyParameter == null || StringUtils.isEmpty(etag)) {
            throw new BceClientException("modifyParameter is null");
        }
        modifyParameter.setValue("10");
        RdsModifyParameterRequest request = new RdsModifyParameterRequest();
        request.setEtag(etag);
        request.setInstanceId(parameterRequest.getInstanceId());
        request.addParameters(modifyParameter);
        AbstractBceResponse response = rdsClient.modifyParameter(request);
        print("modifyParameter", response);
    }

}
