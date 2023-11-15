package com.baidubce.services.rds;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.model.RdsAccount;
import com.baidubce.services.rds.model.RdsAccountInfoRequest;
import com.baidubce.services.rds.model.RdsAccountListRequest;
import com.baidubce.services.rds.model.RdsAccountListResponse;
import com.baidubce.services.rds.model.RdsAutoRenewRequest;
import com.baidubce.services.rds.model.RdsBackupInfoRequest;
import com.baidubce.services.rds.model.RdsBackupInfoResponse;
import com.baidubce.services.rds.model.RdsBilling;
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
import com.baidubce.services.rds.model.RdsModifyParameterRequest;
import com.baidubce.services.rds.model.RdsParameterListRequest;
import com.baidubce.services.rds.model.RdsParameterListResponse;
import com.baidubce.services.rds.model.RdsPaymentTiming;
import com.baidubce.services.rds.model.RdsReleaseInstanceRequest;
import com.baidubce.services.rds.model.RdsReleaseInstanceResponse;
import com.baidubce.services.rds.model.RdsReservation;
import com.baidubce.services.rds.model.RdsSlowLogDownloadDetailRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadDetailResponse;
import com.baidubce.services.rds.model.RdsSlowLogDownloadTasksRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadTasksResponse;
import com.baidubce.services.rds.model.RdsSlowLogRequest;
import com.baidubce.services.rds.model.RdsSlowLogResponse;
import com.baidubce.services.rds.model.RdsSubnetRequest;
import com.baidubce.services.rds.model.RdsSubnetResponse;
import com.baidubce.services.rds.model.RdsZoneRequest;
import com.baidubce.services.rds.model.RdsZoneResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Rds client, operate rds instance
 */
public class RdsClient extends AbstractBceClient {

    /**
     * Responsible for handling httpResponses from all rds service calls.
     */
    private static final HttpResponseHandler[] RDS_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    private static final String BCE_HEADER_ETAG = "x-bce-if-match";

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};
    private static final String REQUEST_KEY = "request";
    private static final String INSTANCE_ID_KEY = "instanceId";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String ACCOUNT_NAME_KEY = "accountName";
    private static final String DESC_KEY = "desc";

    public RdsClient() {
        this(new RdsClientConfiguration());
    }

    public RdsClient(BceClientConfiguration configuration) {
        this(configuration, RDS_HANDLERS);
    }

    public RdsClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        super(config, responseHandlers);
    }

    private InternalRequest createRequest(AbstractBceRequest request, HttpMethodName method, String... pathVariables) {
        return createRequest(null, request, method, pathVariables);
    }

    private InternalRequest createRequest(String version, AbstractBceRequest request, HttpMethodName method,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();
        if (StringUtils.isEmpty(version)) {
            path.add(RdsPaths.VERSION);
        } else {
            path.add(version);
        }
        if (pathVariables != null) {
            Collections.addAll(path, pathVariables);
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[0]));
        InternalRequest internalRequest = new InternalRequest(method, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        internalRequest.setSignOptions(signOptions);
        internalRequest.setCredentials(request.getRequestCredentials());
        return internalRequest;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object
     * @param request         The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest request) {
        if (HttpMethodName.POST == internalRequest.getHttpMethod() ||
                HttpMethodName.PUT == internalRequest.getHttpMethod()) {
            String strJson = JsonUtils.toJsonString(request);
            byte[] requestJson;
            try {
                requestJson = strJson.getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Unsupported encode.", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        }
    }

    /**
     * The encryption implement for AES-128 algorithm for RDS password encryption.
     * Only the first 16 bytes of privateKey will be used to encrypt the content.
     * <p>
     * See more detail on
     * <a href = "https://cloud.baidu.com/doc/RDS/s/Ejwvz0uoq#密码加密传输规范定义">
     * RDS API doc</a>
     *
     * @param content    The content String to encrypt.
     * @param privateKey The security key to encrypt.
     *                   Only the first 16 bytes of privateKey will be used to encrypt the content.
     * @return The encrypted string of the original content with AES-128 algorithm.
     * @throws GeneralSecurityException
     */
    private String aes128WithFirst16Char(String content, String privateKey) throws GeneralSecurityException {
        byte[] crypted = null;
        SecretKeySpec skey = new SecretKeySpec(privateKey.substring(0, 16).getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        crypted = cipher.doFinal(content.getBytes());
        return new String(Hex.encodeHex(crypted));
    }

    /**
     * The default method to generate the random String for clientToken if the optional parameter clientToken
     * is not specified by the user.
     * <p>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    private RdsBilling generateDefaultBilling() {
        RdsBilling billing = new RdsBilling();
        billing.setPaymentTiming(RdsPaymentTiming.Postpaid);
        return billing;
    }

    private RdsBilling generateDefaultBillingWithReservation() {
        RdsBilling billing = new RdsBilling();
        billing.setPaymentTiming(RdsPaymentTiming.Prepaid);
        RdsReservation reservation = new RdsReservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        return billing;
    }


    /**
     * Get the instance list of rds
     *
     * @param request the request
     * @return rds instance list
     */
    public RdsInstanceListResponse getRdsInstanceList(RdsInstanceListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkMaxKeys(request.getMaxKeys());
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsInstanceListResponse.class);
    }


    /**
     * Request the instance detail info
     *
     * @param request thd detail request
     * @return ths response of detail
     */
    public RdsInstanceDetailResponse getRdsInstanceDetail(RdsInstanceDetailRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        String[] paths = {RdsPaths.INSTANCE, request.getInstanceId()};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsInstanceDetailResponse.class);
    }


    /**
     * Create rds account
     *
     * @param request the request of create account
     * @return the response of create account
     * @throws GeneralSecurityException the exception when encrypt
     */
    public AbstractBceResponse createAccount(RdsCreateAccountRequest request) throws GeneralSecurityException {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        // desc is needed
        RdsArgumentUtil.checkString(request.getDesc(), DESC_KEY);
        RdsInstanceDetailRequest detailRequest = new RdsInstanceDetailRequest();
        detailRequest.setInstanceId(request.getInstanceId());
        // request the instance detail
        RdsInstanceDetailResponse detailResponse = getRdsInstanceDetail(detailRequest);
        if (RdsEngine.MySQL != detailResponse.getEngine() && RdsEngine.SQLServer != detailResponse.getEngine()) {
            if (CollectionUtils.isNotEmpty(request.getDatabasePrivileges())) {
                throw new BceClientException("Rds databasePrivileges only support the engine mySql or SQLServer");
            }
        }
        String clientToken = request.getClientToken();
        if (StringUtils.isEmpty(clientToken)) {
            request.setClientToken(generateClientToken());
        }
        String[] paths = {RdsPaths.INSTANCE, request.getInstanceId(), RdsPaths.ACCOUNT};
        String sourcePassword = request.getPassword();
        String signedPassword = aes128WithFirst16Char(sourcePassword, config.getCredentials().getSecretKey());
        request.setPassword(signedPassword);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Request the account list of instance
     *
     * @param request the request of account list
     * @return all accounts of the instance
     */
    public RdsAccountListResponse getAccountList(RdsAccountListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.ACCOUNT};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsAccountListResponse.class);
    }

    /**
     * Request the specific account info
     *
     * @param request the request of specific account
     * @return the response of specific account info
     */
    public RdsAccount getSpecificAccountInfo(RdsAccountInfoRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getAccountName(), ACCOUNT_NAME_KEY);
        String instanceId = request.getInstanceId();
        String accountName = request.getAccountName();
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.ACCOUNT, accountName};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsAccount.class);
    }

    /**
     * Resize the instance
     *
     * @param request the request of resize rds instance
     * @return ths response of resize rds instance
     */
    public AbstractBceResponse resizeInstance(RdsInstanceResizeRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        Integer cpuCount = request.getCpuCount();
        Integer memoryCapacity = request.getMemoryCapacity();
        Integer volumeCapacity = request.getVolumeCapacity();
        if (cpuCount == null && memoryCapacity == null && volumeCapacity == null) {
            String msg = "Please set one of cpuCount,memoryCapacity,volumeCapacity when resize instance";
            throw new BceClientException(msg);
        }
        // check volumeCapacity whether multiple of 5
        RdsArgumentUtil.checkVolumeCapacity(volumeCapacity);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("resize", null);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create rds instance
     *
     * @param request the request of create rds instance
     * @return response
     */
    public RdsCreateInstanceResponse createInstance(RdsCreateInstanceRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        if (StringUtils.isEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        if (request.getBilling() == null) {
            request.setBilling(generateDefaultBilling());
        }
        RdsArgumentUtil.checkPurchaseCount(request.getPurchaseCount());
        RdsArgumentUtil.checkEngine(request.getEngine());
        RdsArgumentUtil.checkEngineVersion(request.getEngineVersion());
        RdsArgumentUtil.checkCpuCount(request.getCpuCount());
        RdsArgumentUtil.checkMemoryCapacity(request.getMemoryCapacity());
        RdsArgumentUtil.checkVolumeCapacity(request.getVolumeCapacity());
        RdsArgumentUtil.checkCharacterSetName(request.getCharacterSetName());
        RdsArgumentUtil.checkAutoRenew(request);
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RdsCreateInstanceResponse.class);
    }

    /**
     * Request the slow log of rds instance
     *
     * @param request the request of slow log
     * @return the response
     */
    public RdsSlowLogResponse getSlowLog(RdsSlowLogRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        RdsArgumentUtil.checkNull(request.getStartTime(), "startTime");
        RdsArgumentUtil.checkNull(request.getEndTime(), "endTime");
        RdsArgumentUtil.checkPage(request.getPageNo(), request.getPageSize());
        String[] paths = {RdsPaths.INSTANCE, instanceId, "slowlogs", "details"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RdsSlowLogResponse.class);
    }

    /**
     * Auto renew the rds instance
     *
     * @param request the request to auto renew rds instance
     * @return the response
     */
    public AbstractBceResponse autoRenew(RdsAutoRenewRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        if (CollectionUtils.isEmpty(request.getInstanceIds())) {
            throw new BceClientException("Please set instanceIds");
        }
        RdsArgumentUtil.checkAutoRenew(request.getAutoRenewTimeUnit(), request.getAutoRenewTime());
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("autoRenew", null);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create a readable rds instance
     *
     * @param request the request of instance creation
     * @return the response
     */
    public RdsCreateInstanceResponse createInstanceReadableReplica(RdsCreateReadableInstance request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getSourceInstanceId(), "sourceInstanceId");
        if (StringUtils.isEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        if (request.getBilling() == null) {
            request.setBilling(generateDefaultBilling());
        } else if (request.getBilling().getPaymentTiming() == RdsPaymentTiming.Prepaid) {
            throw new BceClientException("Prepaid is not support for rds read replica instance");
        }
        Integer purchaseCount = request.getPurchaseCount();
        if (purchaseCount != null && purchaseCount != 1) {
            throw new BceClientException("Currently only supported purchaseCount 1");
        }
        RdsArgumentUtil.checkCpuCount(request.getCpuCount());
        RdsArgumentUtil.checkMemoryCapacity(request.getMemoryCapacity());
        RdsArgumentUtil.checkVolumeCapacity(request.getVolumeCapacity());
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        internalRequest.addParameter("readReplica", null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RdsCreateInstanceResponse.class);
    }

    /**
     * Create a rds proxy instance
     *
     * @param request the request to create rds proxy instance
     * @return the response
     */
    public RdsCreateInstanceResponse createInstanceProxy(RdsCreateProxyInstance request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getSourceInstanceId(), "sourceInstanceId");
        if (StringUtils.isEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        if (request.getBilling() == null) {
            request.setBilling(generateDefaultBilling());
        } else if (request.getBilling().getPaymentTiming() == RdsPaymentTiming.Prepaid) {
            throw new BceClientException("Prepaid is not support for rds proxy instance");
        }
        Integer nodeAmount = request.getNodeAmount();
        RdsArgumentUtil.checkNodeAmount(nodeAmount);
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        internalRequest.addParameter("rdsproxy", null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RdsCreateInstanceResponse.class);
    }

    /**
     * Request the rds zone list
     *
     * @return the response
     */
    public RdsZoneResponse getZoneList() {
        String[] paths = {RdsPaths.ZONE};
        InternalRequest internalRequest = createRequest(new RdsZoneRequest(), HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsZoneResponse.class);
    }

    /**
     * Request the rds subnet list
     *
     * @param request the request of rds subnet
     * @return the response of rds subnet
     */
    public RdsSubnetResponse getSubnetList(RdsSubnetRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String[] paths = {RdsPaths.SUBNET};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        if (StringUtils.isNotEmpty(request.getVpcId())) {
            internalRequest.addParameter("vpcId", request.getVpcId());
        }
        if (StringUtils.isNotEmpty(request.getZoneName())) {
            internalRequest.addParameter("zoneName", request.getZoneName());
        }
        return invokeHttpClient(internalRequest, RdsSubnetResponse.class);
    }

    /**
     * Release the specific rds instance
     *
     * @param request the request to release rds instance
     * @return the response
     */
    public RdsReleaseInstanceResponse releaseInstance(RdsReleaseInstanceRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        if (CollectionUtils.isEmpty(request.getInstanceIds())) {
            throw new BceClientException("instanceIds can not be empty");
        } else if (request.getInstanceIds().size() > 10) {
            throw new BceClientException("instanceIds can not be greater than 10");
        }
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, paths);
        internalRequest.addParameter("instanceIds", convertInstanceIds(request.getInstanceIds()));
        return invokeHttpClient(internalRequest, RdsReleaseInstanceResponse.class);
    }

    /**
     * convert the instanceIds to request parameter
     *
     * @param instanceIds the list of instance id
     * @return the request parameter string
     */
    private String convertInstanceIds(List<String> instanceIds) {
        StringBuilder builder = new StringBuilder();
        for (String instanceId : instanceIds) {
            if (builder.length() == 0) {
                builder.append(instanceId);
            } else {
                builder.append(",").append(instanceId);
            }
        }
        return builder.toString();
    }

    /**
     * Create rds database
     *
     * @param request the request of create database
     * @return the response
     */
    public AbstractBceResponse createDatabase(RdsCreateDatabaseRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getDbName(), "dbName");
        // RdsArgumentUtil.checkString(request.getRemark(), "remark");
        if (request.getRemark() == null) {
            request.setRemark("");
        }
        RdsArgumentUtil.checkNull(request.getCharacterSetName(), "characterSetName");
        RdsInstanceDetailRequest detailRequest = new RdsInstanceDetailRequest();
        detailRequest.setInstanceId(request.getInstanceId());
        RdsInstanceDetailResponse detailResponse = getRdsInstanceDetail(detailRequest);
        if (detailResponse.getEngine() == RdsEngine.PostgreSQL) {
            throw new BceClientException("can not create database for engine " + RdsEngine.PostgreSQL);
        }
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.DATABASES};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete rds database
     *
     * @param request the request of delete rds database
     * @return the response of delete database
     */
    public AbstractBceResponse deleteDatabase(RdsDeleteDatabaseRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String dbName = request.getDbName();
        RdsArgumentUtil.checkString(dbName, "dbName");
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.DATABASES, dbName};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, paths);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modify the rds backup policy
     *
     * @param request the request of modify rds backup policy
     * @return the response
     */
    public AbstractBceResponse modifyBackup(RdsModifyBackupRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String backupDays = request.getBackupDays();
        RdsArgumentUtil.checkString(backupDays, "backupDays");
        String[] days = backupDays.split(",");
        if (days.length == 0) {
            throw new BceClientException("backupDays must be split by ','");
        }
        RdsArgumentUtil.checkString(request.getBackupTime(), "backupTime");
        Integer expireInDays = request.getExpireInDays();
        if (expireInDays != null && (expireInDays < 7 || expireInDays > 730)) {
            throw new BceClientException("expireInDays must range in [7,730]");
        }
        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        request.setInstanceId(null);
        fillPayload(internalRequest, request);
        internalRequest.addParameter("modifyBackupPolicy", null);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Get the rds backup list
     *
     * @param request the request of get rds backup list
     * @return the response
     */
    public RdsGetBackupListResponse getBackupList(RdsGetBackupListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.BACKUP};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        if (StringUtils.isNotEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        Integer maxKeys = request.getMaxKeys();
        if (maxKeys != null) {
            if (maxKeys > 1000) {
                throw new BceClientException("maxKeys can not be greater than 1000");
            }
            internalRequest.addParameter("maxKeys", String.valueOf(maxKeys));
        }
        return invokeHttpClient(internalRequest, RdsGetBackupListResponse.class);
    }

    /**
     * Get the rds backup info
     *
     * @param request the request of get the rds backup info
     * @return the response
     */
    public RdsBackupInfoResponse getBackupInfo(RdsBackupInfoRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String backupId = request.getBackupId();
        RdsArgumentUtil.checkString(backupId, "backupId");
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.BACKUP, backupId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsBackupInfoResponse.class);
    }

    /**
     * Get the rds slow log download task list
     *
     * @param request the request of download rds slow log list
     * @return the response
     */
    public RdsSlowLogDownloadTasksResponse getSlowLogDownloadTaskList(RdsSlowLogDownloadTasksRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String datetime = request.getDatetime();
        RdsArgumentUtil.checkString(datetime, "datetime");
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.SLOW_LOGS, RdsPaths.LOG_LIST, datetime};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsSlowLogDownloadTasksResponse.class);
    }

    /**
     * Get the detail info of downloading rds slow log
     * @param request
     * @return
     */
    public RdsSlowLogDownloadDetailResponse getSlowLogDownloadDetail(RdsSlowLogDownloadDetailRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String logId = request.getLogId();
        RdsArgumentUtil.checkString(logId, "logId");
        Integer time = request.getDownloadValidTimeInSec();
        RdsArgumentUtil.checkNull(time, "downloadValidTimeInSec");
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.SLOW_LOGS, "download_url", logId, time.toString()};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsSlowLogDownloadDetailResponse.class);
    }

    /**
     * Get the rds parameter list
     *
     * @param request the request of get the rds parameter list
     * @return the response
     */
    public RdsParameterListResponse getParameterList(RdsParameterListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.PARAMETER};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsParameterListResponse.class);
    }

    /**
     * Modify rds parameter
     *
     * @param request the request of modify rds parameter
     * @return the response
     */
    public AbstractBceResponse modifyParameter(RdsModifyParameterRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String etag = request.getEtag();
        RdsArgumentUtil.checkString(etag, "etag");
        if (CollectionUtils.isEmpty(request.getParameters())) {
            throw new BceClientException("The parameters can not be null or empty");
        }
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.PARAMETER};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        request.setInstanceId(null);
        internalRequest.addHeader(BCE_HEADER_ETAG, etag);
        request.setEtag(null);
        fillPayload(internalRequest, request);
        AbstractBceResponse response = invokeHttpClient(internalRequest, AbstractBceResponse.class);
        // restore instanceId and etag
        request.setInstanceId(instanceId);
        request.setEtag(etag);
        return response;
    }
}
