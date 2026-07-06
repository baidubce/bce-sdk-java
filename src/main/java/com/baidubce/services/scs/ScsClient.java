package com.baidubce.services.scs;

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
import com.baidubce.services.scs.model.InstanceListRequest;
import com.baidubce.services.scs.model.InstanceListResponse;
import com.baidubce.services.scs.model.account.ScsAclUserAuthorityRequest;
import com.baidubce.services.scs.model.account.ScsAclUserDeleteRequest;
import com.baidubce.services.scs.model.account.ScsAclUserListResponse;
import com.baidubce.services.scs.model.account.ScsAclUserModifyPasswordRequest;
import com.baidubce.services.scs.model.account.ScsAclUserRequest;
import com.baidubce.services.scs.model.backup.ScsBackupModifyCommentRequest;
import com.baidubce.services.scs.model.backup.ScsBackupModifyPolicyRequest;
import com.baidubce.services.scs.model.backup.ScsBackupUsageRequest;
import com.baidubce.services.scs.model.backup.ScsDeleteBackupRequest;
import com.baidubce.services.scs.model.group.ScsGroupRenameRequest;
import com.baidubce.services.scs.model.instance.ScsAutoScalingConfigRequest;
import com.baidubce.services.scs.model.instance.ScsAutoScalingConfigResponse;
import com.baidubce.services.scs.model.backup.ScsBackupDetailRequest;
import com.baidubce.services.scs.model.backup.ScsBackupDetailResponse;
import com.baidubce.services.scs.model.backup.ScsBackupListRequest;
import com.baidubce.services.scs.model.backup.ScsBackupListResponse;
import com.baidubce.services.scs.model.backup.ScsBackupPolicyRequest;
import com.baidubce.services.scs.model.backup.ScsBackupPolicyResponse;
import com.baidubce.services.scs.model.backup.ScsBackupUsageResponse;
import com.baidubce.services.scs.model.instance.ScsBatchInstanceRequest;
import com.baidubce.services.scs.model.ScsBilling;
import com.baidubce.services.scs.model.ScsChangeConfigRequest;
import com.baidubce.services.scs.model.backup.ScsCreateBackupRequest;
import com.baidubce.services.scs.model.group.ScsCreateGroupResponse;
import com.baidubce.services.scs.model.instance.ScsInstanceChangeTagRequest;
import com.baidubce.services.scs.model.instance.ScsInstanceModifyDomainRequest;
import com.baidubce.services.scs.model.instance.ScsInstanceModifyPasswordRequest;
import com.baidubce.services.scs.model.logmanage.ScsLogDetailRequest;
import com.baidubce.services.scs.model.logmanage.ScsLogListRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupModifyBnsNameRequest;
import com.baidubce.services.scs.model.template.ScsApplyParamsTemplateRequest;
import com.baidubce.services.scs.model.template.ScsCreateParamsTemplateRequest;
import com.baidubce.services.scs.model.template.ScsCreateParamsTemplateResponse;
import com.baidubce.services.scs.model.ScsCreateRequest;
import com.baidubce.services.scs.model.ScsCreateResponse;
import com.baidubce.services.scs.model.ScsDeleteInstanceRequest;
import com.baidubce.services.scs.model.ScsFlushInstanceRequest;
import com.baidubce.services.scs.model.group.ScsGroupFollowerRequest;
import com.baidubce.services.scs.model.group.ScsGroupCreateRequest;
import com.baidubce.services.scs.model.group.ScsGroupDetailResponse;
import com.baidubce.services.scs.model.group.ScsGroupForbidWriteRequest;
import com.baidubce.services.scs.model.group.ScsGroupListRequest;
import com.baidubce.services.scs.model.group.ScsGroupListResponse;
import com.baidubce.services.scs.model.group.ScsGroupPreCheckRequest;
import com.baidubce.services.scs.model.group.ScsGroupPreCheckResponse;
import com.baidubce.services.scs.model.group.ScsGroupSetQpsRequest;
import com.baidubce.services.scs.model.group.ScsGroupStaleReadableRequest;
import com.baidubce.services.scs.model.group.ScsGroupSyncStatusResponse;
import com.baidubce.services.scs.model.ScsInstanceDetailRequest;
import com.baidubce.services.scs.model.ScsInstanceDetailResponse;
import com.baidubce.services.scs.model.ScsListOrder;
import com.baidubce.services.scs.model.logmanage.ScsLogDetailResponse;
import com.baidubce.services.scs.model.logmanage.ScsLogListResponse;
import com.baidubce.services.scs.model.instance.ScsMaintainTimeRequest;
import com.baidubce.services.scs.model.instance.ScsMaintainTimeResponse;
import com.baidubce.services.scs.model.ScsMarkerRequest;
import com.baidubce.services.scs.model.instance.ScsMigrateAvailabilityZoneRequest;
import com.baidubce.services.scs.model.instance.ScsModifyEntranceRequest;
import com.baidubce.services.scs.model.instance.ScsNodeTypeResponse;
import com.baidubce.services.scs.model.ScsOrderRequest;
import com.baidubce.services.scs.model.ScsOrderResponse;
import com.baidubce.services.scs.model.ScsParamListRequest;
import com.baidubce.services.scs.model.ScsParamListResponse;
import com.baidubce.services.scs.model.ScsParamModifyRequest;
import com.baidubce.services.scs.model.template.ScsParamsTemplateDeleteParamsRequest;
import com.baidubce.services.scs.model.template.ScsParamsTemplateModifyParamsRequest;
import com.baidubce.services.scs.model.template.ScsParamsTemplateDetailResponse;
import com.baidubce.services.scs.model.template.ScsParamsTemplateListResponse;
import com.baidubce.services.scs.model.template.ScsParamsTemplateRenameRequest;
import com.baidubce.services.scs.model.instance.ScsPriceRequest;
import com.baidubce.services.scs.model.instance.ScsPriceResponse;
import com.baidubce.services.scs.model.ScsRecoverInstanceRequest;
import com.baidubce.services.scs.model.recycler.ScsRecyclerInstanceListResponse;
import com.baidubce.services.scs.model.ScsReleaseInstanceRequest;
import com.baidubce.services.scs.model.ScsRenewInstanceRequest;
import com.baidubce.services.scs.model.ScsRenewInstanceResponse;
import com.baidubce.services.scs.model.instance.ScsReplicationRequest;
import com.baidubce.services.scs.model.ScsReservation;
import com.baidubce.services.scs.model.instance.ScsRestartInstanceRequest;
import com.baidubce.services.scs.model.securitygroup.ScsSecurityGroupInstanceResponse;
import com.baidubce.services.scs.model.securitygroup.ScsSecurityGroupRequest;
import com.baidubce.services.scs.model.securitygroup.ScsSecurityGroupVpcResponse;
import com.baidubce.services.scs.model.ScsSlowLogAction;
import com.baidubce.services.scs.model.ScsSlowLogModifyRequest;
import com.baidubce.services.scs.model.ScsSlowLogRequest;
import com.baidubce.services.scs.model.ScsSlowLogResponse;
import com.baidubce.services.scs.model.ScsSubnetRequest;
import com.baidubce.services.scs.model.ScsSubnetResponse;
import com.baidubce.services.scs.model.ScsSwapDomainRequest;
import com.baidubce.services.scs.model.instance.ScsSwitchMasterSlaveRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupCreateRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupCreateResponse;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupDelayInfoResponse;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupDetailResponse;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupListRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupListResponse;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupMemberRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupModifyNameRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupPreCheckRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupPreCheckResponse;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupStatusResponse;
import com.baidubce.services.scs.model.template.ScsSystemTemplateRequest;
import com.baidubce.services.scs.model.template.ScsSystemTemplateResponse;
import com.baidubce.services.scs.model.instance.ScsUpdateInstanceNameRequest;
import com.baidubce.services.scs.model.instance.ScsUpgradeProxyRequest;
import com.baidubce.services.scs.model.instance.ScsUpgradeVersionRequest;
import com.baidubce.services.scs.model.whitelist.ScsWhiteListGroupRequest;
import com.baidubce.services.scs.model.whitelist.ScsWhiteListGroupResponse;
import com.baidubce.services.scs.model.ScsZoneRequest;
import com.baidubce.services.scs.model.ScsZoneResponse;
import com.baidubce.services.scs.model.template.ScsTemplateApplyRecordsResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Scs Client
 * Setup configuration to access scs operation
 */
public class ScsClient extends AbstractBceClient {

    /**
     * Responsible for handling httpResponses from all scs service calls.
     */
    private static final HttpResponseHandler[] SCS_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    private static final String SCS_AK_KEY = "X-Bce-Accesskey";
    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};
    private static final String INSTANCE_ID = "instanceId";
    private static final String CLIENT_TOKEN = "clientToken";

    public ScsClient() {
        this(new ScsClientConfiguration());
    }

    public ScsClient(BceClientConfiguration config) {
        this(config, SCS_HANDLERS);
    }

    public ScsClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        super(config, responseHandlers);
    }

    private InternalRequest createRequest(AbstractBceRequest request, HttpMethodName method, String... pathVariables) {
        return createRequest(null, request, method, pathVariables);
    }

    private InternalRequest createRequest(String version, AbstractBceRequest request, HttpMethodName method,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();
        if (StringUtils.isEmpty(version)) {
            path.add(ScsPaths.VERSION);
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
     * Create scs instance
     *
     * @param request the request of instance create
     * @return response of scs instance create which contains instance id
     */
    public ScsCreateResponse createInstance(ScsCreateRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        if (StringUtils.isEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        ScsBilling billing = request.getBilling();
        if (billing == null) {
            billing = defaultScsBilling();
        }
        request.setBilling(billing);
        // check billing paymentTiming and reservation
        ScsArgumentUtil.checkBilling(billing);
        ScsArgumentUtil.checkClusterName(request.getInstanceName());
        ScsArgumentUtil.checkString(request.getNodeType(), "nodeType");
        int port = request.getPort();
        ScsArgumentUtil.checkClusterPort(port);
        ScsArgumentUtil.checkString(request.getEngineVersion(), "EngineVersion");
        ScsArgumentUtil.checkPurchaseCount(request.getPurchaseCount());
        ScsArgumentUtil.checkNull(request.getClusterType(), "ClusterType");
        String autoRenewTimeUnit = request.getAutoRenewTimeUnit();
        ScsArgumentUtil.checkAutoRenewTimeUnit(autoRenewTimeUnit);
        ScsArgumentUtil.checkAutoRenewTime(request.getAutoRenewTime(), autoRenewTimeUnit);
        String clientAuth = request.getClientAuth();
        if (StringUtils.isNotEmpty(clientAuth)) {
            clientAuth = clientAuth.trim();
            ScsArgumentUtil.checkClientAuth(clientAuth);
            try {
                String password = aes128WithFirst16Char(clientAuth, config.getCredentials().getSecretKey());
                request.setClientAuth(password);
            } catch (GeneralSecurityException e) {
                throw new BceClientException("encryption error");
            }
        }

        String[] paths = {ScsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        internalRequest.addHeader(SCS_AK_KEY, config.getCredentials().getAccessKeyId());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsCreateResponse.class);
    }

    /**
     * Delete instance by instance id
     *
     * @param request the request of delete instance
     */
    public AbstractBceResponse deleteInstance(ScsDeleteInstanceRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String id = request.getInstanceId();
        ScsArgumentUtil.checkString(id, INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, id};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.DELETE, paths);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Renew the scs instance
     *
     * @param request the request of renew instance
     * @return the response of renew instance
     */
    public ScsRenewInstanceResponse renewInstance(ScsRenewInstanceRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        if (CollectionUtils.isEmpty(request.getInstanceIds())) {
            throw new IllegalArgumentException("Please set the instance id when renew instance");
        }
        if (request.getDuration() <= 0) {
            throw new IllegalArgumentException("The duration must more than one month");
        }
        String[] paths = {ScsPaths.INSTANCE, ScsPaths.RENEW};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsRenewInstanceResponse.class);
    }

    /**
     * Recover the instance from the recycle bin
     *
     * @param request the request of recover instance
     */
    public AbstractBceResponse recoverInstance(ScsRecoverInstanceRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        if (CollectionUtils.isEmpty(request.getInstanceIds())) {
            throw new IllegalArgumentException("Please set the instance ids when recover instance");
        }
        String[] paths = {ScsPaths.RECYCLER, ScsPaths.RECOVER};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Request scs instance list
     *
     * @param request instance list request
     * @return scs instance list
     */
    public InstanceListResponse getInstanceList(InstanceListRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, ScsPaths.INSTANCE);
        if (StringUtils.isNotEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        Integer maxKeys = request.getMaxKeys();
        if (maxKeys != null) {
            if (maxKeys > 1000) {
                throw new IllegalArgumentException("maxKeys can not be greater than 1000");
            }
            internalRequest.addParameter("maxKeys", String.valueOf(maxKeys));
        }
        if (StringUtils.isNotEmpty(request.getInstanceIds())) {
            internalRequest.addParameter("instanceIds", request.getInstanceIds());
        }
        if (StringUtils.isNotEmpty(request.getVnetIp())) {
            internalRequest.addParameter("vnetIp", request.getVnetIp());
        }
        return invokeHttpClient(internalRequest, InstanceListResponse.class);
    }

    /**
     * Request scs instance detail
     *
     * @param request Instance detail request which contains instance id
     * @return scs instance detail info
     */
    public ScsInstanceDetailResponse getInstanceDetail(ScsInstanceDetailRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String instanceId = request.getInstanceId();
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsInstanceDetailResponse.class);
    }

    /**
     * Request param list
     *
     * @param request the request of param list
     * @return param list response
     */
    public ScsParamListResponse getParamList(ScsParamListRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String id = request.getInstanceId();
        ScsArgumentUtil.checkString(id, INSTANCE_ID);
        HttpMethodName get = HttpMethodName.GET;
        String[] paths = {ScsPaths.INSTANCE, id, ScsPaths.PARAMETER};
        InternalRequest internalRequest = createRequest(request, get, paths);
        return invokeHttpClient(internalRequest, ScsParamListResponse.class);
    }

    /**
     * Modify scs parameter
     *
     * @param request the request modify param
     */
    public AbstractBceResponse modifyScsParameter(ScsParamModifyRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String id = request.getInstanceId();
        ScsArgumentUtil.checkString(id, INSTANCE_ID);
        if (StringUtils.isEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        String[] paths = {ScsPaths.INSTANCE, id, ScsPaths.PARAMETER};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter(INSTANCE_ID, id);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Change scs instance
     * Get the latest instance info by {@link #getInstanceDetail(ScsInstanceDetailRequest)}
     *
     * @param request The request of change instance
     */
    public ScsOrderResponse changeScsInstance(ScsChangeConfigRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String id = request.getInstanceId();
        ScsArgumentUtil.checkString(id, INSTANCE_ID);
        if (StringUtils.isEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        String nodeType = request.getNodeType();
        if (StringUtils.isEmpty(nodeType)) {
            throw new BceClientException("Please set nodeType");
        }
        Integer shardNum = request.getShardNum();
        if (shardNum != null && shardNum <= 0) {
            throw new BceClientException("ShardNum must be at least 1");
        }
        ScsInstanceDetailRequest detailRequest = new ScsInstanceDetailRequest();
        detailRequest.setInstanceId(id);
        ScsInstanceDetailResponse detailResponse = getInstanceDetail(detailRequest);
        if (detailResponse == null) {
            throw new BceClientException("Can not get instance detail");
        }

        String detailNodeType = detailResponse.getNodeType();
        Integer detailShardNum = detailResponse.getShardNum();
        if (nodeType.equals(detailNodeType) && (shardNum != null && shardNum.equals(detailShardNum))) {
            throw new BceClientException("NodeType and shardNum has not changed");
        }
        if (!nodeType.equals(detailNodeType) &&
                (shardNum != null && detailShardNum != null && !shardNum.equals(detailShardNum))) {
            throw new BceClientException("NodeType and shareNum can not change together");
        }
        ScsBilling billing = request.getBilling();
        if (billing == null) {
            billing = defaultScsBilling();
        }
        // check billing paymentTiming and reservation
        ScsArgumentUtil.checkBilling(billing);
        String[] paths = {ScsPaths.INSTANCE, id, ScsPaths.CHANGE};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsOrderResponse.class);
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
                HttpMethodName.PUT == internalRequest.getHttpMethod() ||
                HttpMethodName.DELETE == internalRequest.getHttpMethod()) {
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

    private void addClientToken(InternalRequest internalRequest, String clientToken) {
        if (StringUtils.isEmpty(clientToken)) {
            clientToken = generateClientToken();
        }
        internalRequest.addParameter(CLIENT_TOKEN, clientToken);
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

    /**
     * Create default billing which paymentTiming is Postpaid
     *
     * @return the default billing
     */
    private ScsBilling defaultScsBilling() {
        ScsBilling billing = new ScsBilling();
        billing.setPaymentTiming("Postpaid");
        return billing;
    }

    /**
     * Create default billing which is prepaid
     *
     * @return the billing
     */
    private ScsBilling defaultScsBillingWithReservation() {
        ScsBilling billing = new ScsBilling();
        billing.setPaymentTiming("Prepaid");
        ScsReservation reservation = new ScsReservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        billing.setReservation(reservation);
        return billing;
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
     * Get create price of a scs instance.
     *
     * @param request the request of create price
     * @return the price response
     */
    public ScsPriceResponse getCreatePrice(ScsPriceRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, ScsPaths.PRICE);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsPriceResponse.class);
    }

    /**
     * Get resize price of a scs instance.
     *
     * @param instanceId the scs instance id
     * @param request the request of resize price
     * @return the price response
     */
    public ScsPriceResponse getResizePrice(String instanceId, ScsPriceRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, instanceId, ScsPaths.PRICE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsPriceResponse.class);
    }

    /**
     * Get available node type list.
     *
     * @return the node type response
     */
    public ScsNodeTypeResponse getNodeTypeList() {
        AbstractBceRequest request = new ScsZoneRequest();
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, ScsPaths.NODE_TYPES);
        return invokeHttpClient(internalRequest, ScsNodeTypeResponse.class);
    }

    /**
     * Add replication for a scs instance.
     *
     * @param request the request of replication add
     * @return the order response
     */
    public ScsOrderResponse addReplication(ScsReplicationRequest request) {
        return resizeReplication(request, HttpMethodName.POST);
    }

    /**
     * Delete replication from a scs instance.
     *
     * @param request the request of replication delete
     * @return the order response
     */
    public ScsOrderResponse deleteReplication(ScsReplicationRequest request) {
        return resizeReplication(request, HttpMethodName.PUT);
    }

    private ScsOrderResponse resizeReplication(ScsReplicationRequest request, HttpMethodName method) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.RESIZE_REPLICATION};
        InternalRequest internalRequest = createRequest(request, method, paths);
        addClientToken(internalRequest, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsOrderResponse.class);
    }

    /**
     * Restart a scs instance.
     *
     * @param request the request of instance restart
     * @return the response
     */
    public AbstractBceResponse restartInstance(ScsRestartInstanceRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.RESTART};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update scs instance name.
     *
     * @param request the request of instance name update
     * @return the response
     */
    public AbstractBceResponse updateInstanceName(ScsUpdateInstanceNameRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getInstanceName(), "instanceName");
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.RENAME};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update scs instance domain name.
     *
     * @param request the request of instance domain name update
     * @return the response
     */
    public AbstractBceResponse updateInstanceDomainName(ScsInstanceModifyDomainRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getDomain(), "domain");
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.RENAME_DOMAIN};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Bind tags to a scs instance.
     *
     * @param request the request of tag bind
     * @return the response
     */
    public AbstractBceResponse bindingTag(ScsInstanceChangeTagRequest request) {
        return changeTag(request, ScsPaths.BIND_TAG);
    }

    /**
     * Unbind tags from a scs instance.
     *
     * @param request the request of tag unbind
     * @return the response
     */
    public AbstractBceResponse unBindingTag(ScsInstanceChangeTagRequest request) {
        return changeTag(request, ScsPaths.UNBIND_TAG);
    }

    private AbstractBceResponse changeTag(ScsInstanceChangeTagRequest request, String action) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), action};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Get whitelist groups of a scs instance.
     *
     * @param request the request of whitelist group list
     * @return the whitelist group response
     */
    public ScsWhiteListGroupResponse getWhiteListGroup(ScsWhiteListGroupRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.WHITE_LIST};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        if (StringUtils.isNotEmpty(request.getGroupName())) {
            internalRequest.addParameter("groupName", request.getGroupName());
        }
        return invokeHttpClient(internalRequest, ScsWhiteListGroupResponse.class);
    }

    /**
     * Create a whitelist group for a scs instance.
     *
     * @param request the request of whitelist group create
     * @return the response
     */
    public AbstractBceResponse createWhiteListGroup(ScsWhiteListGroupRequest request) {
        return changeWhiteListGroup(request, HttpMethodName.POST);
    }

    /**
     * Modify a whitelist group of a scs instance.
     *
     * @param request the request of whitelist group modify
     * @return the response
     */
    public AbstractBceResponse modifyWhiteListGroup(ScsWhiteListGroupRequest request) {
        return changeWhiteListGroup(request, HttpMethodName.PUT);
    }

    private AbstractBceResponse changeWhiteListGroup(ScsWhiteListGroupRequest request, HttpMethodName method) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.WHITE_LIST};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, method, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete a whitelist group of a scs instance.
     *
     * @param request the request of whitelist group delete
     * @return the response
     */
    public AbstractBceResponse deleteWhiteListGroup(ScsWhiteListGroupRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getGroupName(), "groupName");
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.WHITE_LIST};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.DELETE, paths);
        internalRequest.addParameter("groupName", request.getGroupName());
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modify password of a scs instance.
     *
     * @param request the request of password modify
     * @return the response
     * @throws GeneralSecurityException when password encryption fails
     */
    public AbstractBceResponse modifyPassword(ScsInstanceModifyPasswordRequest request) throws GeneralSecurityException {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        if (StringUtils.isNotEmpty(request.getPassword())) {
            request.setPassword(aes128WithFirst16Char(request.getPassword(), config.getCredentials().getSecretKey()));
        }
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.MODIFY_CLIENT_AUTH};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Swap instance domain
     *
     * @param request the request of swap domain
     * @return the response
     */
    public AbstractBceResponse swapDomain(ScsSwapDomainRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getSourceInstanceId(), "sourceInstanceId");
        ScsArgumentUtil.checkString(request.getTargetInstanceId(), "targetInstanceId");
        String[] paths = {ScsPaths.INSTANCE, "swapDomain"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Request to flush scs instance , the password will be encrypted
     *
     * @param request the request of flush instance
     * @return the response of flush instance
     */
    public AbstractBceResponse flushInstance(ScsFlushInstanceRequest request) throws GeneralSecurityException {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String instanceId = request.getInstanceId();
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        if (StringUtils.isNotEmpty(request.getPassword())) {
            String signedPwd = aes128WithFirst16Char(request.getPassword(), config.getCredentials().getSecretKey());
            request.setPassword(signedPwd);
        }
        ScsArgumentUtil.checkDbIndex(request.getDbIndex());
        String[] paths = {ScsPaths.INSTANCE, instanceId, ScsPaths.FLUSH};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Request the scs slow log
     *
     * @param request the request of scs slow log
     * @return the response
     */
    public ScsSlowLogResponse getSlowLog(ScsSlowLogRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkNull(request.getType(), "type");
        ScsArgumentUtil.checkNull(request.getStartTime(), "startTime");
        ScsArgumentUtil.checkPage(request.getPageNow(), request.getPageSize());
        String[] paths = {ScsPaths.INSTANCE, ScsPaths.CLOUD_LOG, request.getInstanceId()};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        internalRequest.addParameter("type", request.getType().getValue());
        internalRequest.addParameter("startTime", dateFormat.format(request.getStartTime()));
        internalRequest.addParameter("pageNow", String.valueOf(request.getPageNow()));
        if (request.getPageSize() == null) {
            // set the default pageSize
            request.setPageSize(1000);
        }
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        setSlowLogParameter(internalRequest, request, dateFormat);
        return invokeHttpClient(internalRequest, ScsSlowLogResponse.class);
    }

    private void setSlowLogParameter(InternalRequest internalRequest, ScsSlowLogRequest request,
                                     SimpleDateFormat dateFormat) {
        Date endTime = request.getEndTime();
        if (endTime != null) {
            internalRequest.addParameter("endTime", dateFormat.format(endTime));
        }
        String shardId = request.getShardId();
        if (StringUtils.isNotEmpty(shardId)) {
            internalRequest.addParameter("shardId", shardId);
        }
        String nodeId = request.getNodeId();
        if (StringUtils.isNotEmpty(nodeId)) {
            internalRequest.addParameter("nodeId", nodeId);
        }
        ScsListOrder listOrder = request.getListOrder();
        if (listOrder != null) {
            internalRequest.addParameter("listOrder", listOrder.getValue());
        }
    }

    /**
     * Modify the slow log enable
     *
     * @param request request of modify the slow log enable
     * @return the response
     */
    public AbstractBceResponse modifySlowLog(ScsSlowLogModifyRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String instanceId = request.getInstanceId();
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        ScsSlowLogAction action = request.getAction();
        ScsArgumentUtil.checkNull(action, "action");
        String[] paths = {ScsPaths.CLOUD_LOG, instanceId, ScsPaths.MODIFY};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("action", action.getValue());
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Request the backup list of a scs instance.
     *
     * @param request the request of backup list
     * @return the backup list response
     */
    public ScsBackupListResponse getBackupList(ScsBackupListRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.BACKUP};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        addBackupListParameters(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsBackupListResponse.class);
    }

    private void addBackupListParameters(InternalRequest internalRequest, ScsBackupListRequest request) {
        if (StringUtils.isNotBlank(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotBlank(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (StringUtils.isNotBlank(request.getStartTime())) {
            internalRequest.addParameter("startTime", request.getStartTime());
        }
        if (StringUtils.isNotBlank(request.getEndTime())) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        addPageParameters(internalRequest, "pageNo", request.getPageNo(), request.getPageSize());
    }

    /**
     * Get the backup policy of a scs instance.
     *
     * @param request the request of backup policy
     * @return the backup policy response
     */
    public ScsBackupPolicyResponse getBackupPolicy(ScsBackupPolicyRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.BACKUP, ScsPaths.POLICY};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsBackupPolicyResponse.class);
    }

    /**
     * Create a backup for a scs instance.
     *
     * @param request the request of backup create
     * @return the response
     */
    public AbstractBceResponse createBackup(ScsCreateBackupRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.BACKUP};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete a backup of a scs instance.
     *
     * @param request the request of backup delete
     * @return the response
     */
    public AbstractBceResponse deleteBackup(ScsDeleteBackupRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getBatchId(), "batchId");
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.BACKUP, request.getBatchId()};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.DELETE, paths);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modify the backup comment of a scs instance.
     *
     * @param request the request of backup comment modify
     * @return the response
     */
    public AbstractBceResponse modifyBackupComment(ScsBackupModifyCommentRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getBatchId(), "batchId");
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.BACKUP, request.getBatchId(),
                ScsPaths.COMMENT};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Get backup usage of a scs instance.
     *
     * @param request the request of backup usage
     * @return the backup usage response
     */
    public ScsBackupUsageResponse getBackupUsage(ScsBackupUsageRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.BACKUP, ScsPaths.USAGE};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsBackupUsageResponse.class);
    }

    /**
     * Get backup detail of a scs instance.
     *
     * @param request the request of backup detail
     * @return the backup detail response
     */
    public ScsBackupDetailResponse getBackupDetail(ScsBackupDetailRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getBackupRecordId(), "backupRecordId");
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.BACKUP, request.getBackupRecordId(),
                ScsPaths.URL};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsBackupDetailResponse.class);
    }

    /**
     * Modify the backup policy of a scs instance.
     *
     * @param request the request of backup policy modify
     * @return the response
     */
    public AbstractBceResponse modifyBackupPolicy(ScsBackupModifyPolicyRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.MODIFY_BACKUP_POLICY};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * List acl users of a scs instance.
     *
     * @param instanceId the scs instance id
     * @return the acl user list response
     */
    public ScsAclUserListResponse listAclUsers(String instanceId) {
        AbstractBceRequest request = new ScsZoneRequest();
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, instanceId, ScsPaths.ACL_USER_ACTIONS};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsAclUserListResponse.class);
    }

    /**
     * Create an acl user for a scs instance.
     *
     * @param request the request of acl user create
     * @return the response
     */
    public AbstractBceResponse createAclUser(ScsAclUserRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        if (StringUtils.isNotEmpty(request.getClientAuth())) {
            try {
                request.setClientAuth(aes128WithFirst16Char(request.getClientAuth(), config.getCredentials().getSecretKey()));
            } catch (GeneralSecurityException e) {
                throw new BceClientException("encryption error");
            }
        }
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.ACL_USER_ACTIONS};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete an acl user of a scs instance.
     *
     * @param request the request of acl user delete
     * @return the response
     */
    public AbstractBceResponse deleteAclUser(ScsAclUserDeleteRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getUserName(), "userName");
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.ACL_USER_ACTIONS, ScsPaths.DELETE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Set acl user authority for a scs instance.
     *
     * @param request the request of acl user authority set
     * @return the response
     */
    public AbstractBceResponse setAclUserAuthority(ScsAclUserAuthorityRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getUserName(), "userName");
        ScsArgumentUtil.checkNull(request.getUserType(), "userType");
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.ACL_USER_ACTIONS, ScsPaths.AUTHORITY};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modify acl user password for a scs instance.
     *
     * @param request the request of acl user password modify
     * @return the response
     */
    public AbstractBceResponse modifyAclUserPassword(ScsAclUserModifyPasswordRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getUserName(), "userName");
        if (StringUtils.isNotEmpty(request.getClientAuth())) {
            try {
                request.setClientAuth(aes128WithFirst16Char(request.getClientAuth(), config.getCredentials().getSecretKey()));
            } catch (GeneralSecurityException e) {
                throw new BceClientException("encryption error");
            }
        }
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.ACL_USER_ACTIONS, "modifyPasswd"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Change scs instances to prepay billing.
     *
     * @param request the request of billing change
     * @return the order response
     */
    public ScsOrderResponse toPrepay(ScsOrderRequest request) {
        return changePayment(request, ScsPaths.TO_PREPAY, ScsOrderResponse.class);
    }

    /**
     * Change scs instances to postpay billing.
     *
     * @param request the request of billing change
     * @return the order response
     */
    public ScsOrderResponse toPostpay(ScsOrderRequest request) {
        return changePayment(request, ScsPaths.TO_POSTPAY, ScsOrderResponse.class);
    }

    /**
     * Cancel changing scs instances to postpay billing.
     *
     * @param request the request of billing change cancel
     * @return the response
     */
    public AbstractBceResponse cancelToPostpay(ScsOrderRequest request) {
        return changePayment(request, ScsPaths.CANCEL_TO_POSTPAY, AbstractBceResponse.class);
    }

    private <T extends AbstractBceResponse> T changePayment(ScsOrderRequest request, String action, Class<T> response) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String[] paths = {ScsPaths.INSTANCE, action};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, response);
    }

    /**
     * Switch master and slave for a scs instance.
     *
     * @param request the request of master-slave switch
     * @return the response
     */
    public AbstractBceResponse switchMasterSlave(ScsSwitchMasterSlaveRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.SWITCH_MASTER_SLAVE};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Migrate a scs instance to another availability zone.
     *
     * @param request the request of availability zone migration
     * @return the response
     */
    public AbstractBceResponse migrateAvailabilityZone(ScsMigrateAvailabilityZoneRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.AZONE_MIGRATION};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modify access entrances of a scs instance.
     *
     * @param request the request of entrance modify
     * @return the response
     */
    public AbstractBceResponse modifyEntrance(ScsModifyEntranceRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.AZONE_MIGRATION, ScsPaths.MODIFY_ENTRANCE};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Upgrade the engine version of a scs instance.
     *
     * @param request the request of version upgrade
     * @return the response
     */
    public AbstractBceResponse upgradeVersion(ScsUpgradeVersionRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.UPGRADE_VERSION};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Upgrade proxy nodes of a scs instance.
     *
     * @param request the request of proxy upgrade
     * @return the response
     */
    public AbstractBceResponse upgradeProxy(ScsUpgradeProxyRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.UPGRADE_PROXY};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Get auto scaling config of a scs instance.
     *
     * @param instanceId the scs instance id
     * @return the auto scaling config response
     */
    public ScsAutoScalingConfigResponse getAutoScalingConfig(String instanceId) {
        AbstractBceRequest request = new ScsZoneRequest();
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, instanceId, ScsPaths.AUTO_SCALING_CONFIG};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsAutoScalingConfigResponse.class);
    }

    /**
     * Set auto scaling config for a scs instance.
     *
     * @param request the request of auto scaling config set
     * @return the response
     */
    public AbstractBceResponse setAutoScalingConfig(ScsAutoScalingConfigRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.AUTO_SCALING_CONFIG};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete auto scaling config of a scs instance.
     *
     * @param instanceId the scs instance id
     * @return the response
     */
    public AbstractBceResponse deleteAutoScalingConfig(String instanceId) {
        AbstractBceRequest request = new ScsZoneRequest();
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, instanceId, ScsPaths.DELETE_AUTO_SCALING_CONFIG};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.POST, paths);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * List security groups by vpc id.
     *
     * @param vpcId the vpc id
     * @return the security group vpc response
     */
    public ScsSecurityGroupVpcResponse listSecurityGroupByVpcId(String vpcId) {
        AbstractBceRequest request = new ScsZoneRequest();
        ScsArgumentUtil.checkString(vpcId, "vpcId");
        String[] paths = {ScsPaths.SECURITY, ScsPaths.VPC, vpcId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsSecurityGroupVpcResponse.class);
    }

    /**
     * List security groups by scs instance id.
     *
     * @param instanceId the scs instance id
     * @return the security group instance response
     */
    public ScsSecurityGroupInstanceResponse listSecurityGroupByInstanceId(String instanceId) {
        AbstractBceRequest request = new ScsZoneRequest();
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, instanceId, ScsPaths.SECURITY_GROUP};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsSecurityGroupInstanceResponse.class);
    }

    /**
     * Bind security groups to a scs instance.
     *
     * @param request the request of security group bind
     * @return the response
     */
    public AbstractBceResponse bindSecurityGroups(ScsSecurityGroupRequest request) {
        return changeSecurityGroups(request, ScsPaths.BIND);
    }

    /**
     * Unbind security groups from a scs instance.
     *
     * @param request the request of security group unbind
     * @return the response
     */
    public AbstractBceResponse unBindSecurityGroups(ScsSecurityGroupRequest request) {
        return changeSecurityGroups(request, ScsPaths.UNBIND);
    }

    /**
     * Replace security groups of a scs instance.
     *
     * @param request the request of security group replace
     * @return the response
     */
    public AbstractBceResponse replaceSecurityGroups(ScsSecurityGroupRequest request) {
        return changeSecurityGroups(request, ScsPaths.UPDATE);
    }

    private AbstractBceResponse changeSecurityGroups(ScsSecurityGroupRequest request, String action) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        if (CollectionUtils.isEmpty(request.getSecurityGroupIds())) {
            throw new IllegalArgumentException("Please set securityGroupIds");
        }
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.SECURITY_GROUP, action};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * List scs instances in the recycle bin.
     *
     * @param request the request of recycle instance list
     * @return the recycle instance list response
     */
    public ScsRecyclerInstanceListResponse listRecycleInstances(ScsMarkerRequest request) {
        if (request == null) {
            request = new ScsMarkerRequest();
        }
        String[] paths = {ScsPaths.RECYCLER, ScsPaths.LIST};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        addMarkerParameters(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsRecyclerInstanceListResponse.class);
    }

    /**
     * Delete scs instances from the recycle bin.
     *
     * @param request the request of recycle instance delete
     * @return the response
     */
    public AbstractBceResponse deleteRecyclerInstances(ScsBatchInstanceRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String[] paths = {ScsPaths.RECYCLER, ScsPaths.DELETE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * List logs by scs instance id.
     *
     * @param request the request of log list
     * @return the log list response
     */
    public ScsLogListResponse listLogByInstanceId(ScsLogListRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.LOG};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        addParameterIfNotEmpty(internalRequest, "fileType", request.getFileType());
        addParameterIfNotEmpty(internalRequest, "startTime", request.getStartTime());
        addParameterIfNotEmpty(internalRequest, "endTime", request.getEndTime());
        return invokeHttpClient(internalRequest, ScsLogListResponse.class);
    }

    /**
     * Get log detail by log id.
     *
     * @param request the request of log detail
     * @return the log detail response
     */
    public ScsLogDetailResponse getLogById(ScsLogDetailRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        ScsArgumentUtil.checkString(request.getLogId(), "logId");
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.LOG, request.getLogId()};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        if (request.getValidSeconds() != null) {
            internalRequest.addParameter("validSeconds", String.valueOf(request.getValidSeconds()));
        }
        return invokeHttpClient(internalRequest, ScsLogDetailResponse.class);
    }

    /**
     * Get maintain time of a scs instance.
     *
     * @param instanceId the scs instance id
     * @return the maintain time response
     */
    public ScsMaintainTimeResponse getMaintainTime(String instanceId) {
        AbstractBceRequest request = new ScsZoneRequest();
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, instanceId, ScsPaths.TIME_WINDOW};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsMaintainTimeResponse.class);
    }

    /**
     * Modify maintain time of a scs instance.
     *
     * @param request the request of maintain time modify
     * @return the response
     */
    public AbstractBceResponse modifyMaintainTime(ScsMaintainTimeRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId(), ScsPaths.TIME_WINDOW};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Pre-check scs group creation.
     *
     * @param request the request of group pre-check
     * @return the group pre-check response
     */
    public ScsGroupPreCheckResponse groupPreCheck(ScsGroupPreCheckRequest request) {
        return invokeGroup(request, ScsGroupPreCheckResponse.class, HttpMethodName.POST, ScsPaths.CHECK);
    }

    /**
     * Create a scs group.
     *
     * @param request the request of group create
     * @return the group create response
     */
    public ScsCreateGroupResponse createGroup(ScsGroupCreateRequest request) {
        return invokeGroup(request, ScsCreateGroupResponse.class, HttpMethodName.POST, ScsPaths.CREATE);
    }

    /**
     * Request scs group list.
     *
     * @param request the request of group list
     * @return the group list response
     */
    public ScsGroupListResponse getGroupList(ScsGroupListRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String[] paths = {ScsPaths.GROUP, ScsPaths.LIST};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        checkPageParameters("pageNo", request.getPageNo(), request.getPageSize());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsGroupListResponse.class);
    }

    /**
     * Get scs group detail.
     *
     * @param groupId the scs group id
     * @return the group detail response
     */
    public ScsGroupDetailResponse getGroupDetail(String groupId) {
        return invokeGroupById(groupId, ScsGroupDetailResponse.class, HttpMethodName.GET);
    }

    /**
     * Delete a scs group.
     *
     * @param groupId the scs group id
     * @return the response
     */
    public AbstractBceResponse deleteGroup(String groupId) {
        return invokeGroupAction(groupId, HttpMethodName.DELETE, null, ScsPaths.RELEASE);
    }

    /**
     * Add a follower to a scs group.
     *
     * @param groupId the scs group id
     * @param request the request of follower add
     * @return the response
     */
    public AbstractBceResponse groupAddFollower(String groupId, ScsGroupFollowerRequest request) {
        return invokeGroupAction(groupId, HttpMethodName.POST, request, ScsPaths.JOIN);
    }

    /**
     * Remove a follower from a scs group.
     *
     * @param groupId the scs group id
     * @param instanceId the follower instance id
     * @return the response
     */
    public AbstractBceResponse groupRemoveFollower(String groupId, String instanceId) {
        ScsArgumentUtil.checkString(instanceId, INSTANCE_ID);
        return invokeGroupAction(groupId, HttpMethodName.POST, null, ScsPaths.QUIT, instanceId);
    }

    /**
     * Update scs group name.
     *
     * @param groupId the scs group id
     * @param request the request of group rename
     * @return the response
     */
    public AbstractBceResponse updateGroupName(String groupId, ScsGroupRenameRequest request) {
        return invokeGroupAction(groupId, HttpMethodName.PUT, request);
    }

    /**
     * Set forbid-write config for a scs group.
     *
     * @param groupId the scs group id
     * @param request the request of forbid-write config
     * @return the response
     */
    public AbstractBceResponse groupForbidWrite(String groupId, ScsGroupForbidWriteRequest request) {
        return invokeGroupAction(groupId, HttpMethodName.PUT, request, ScsPaths.FORBID_WRITE);
    }

    /**
     * Set qps config for a scs group.
     *
     * @param groupId the scs group id
     * @param request the request of qps config
     * @return the response
     */
    public AbstractBceResponse groupSetQps(String groupId, ScsGroupSetQpsRequest request) {
        return invokeGroupAction(groupId, HttpMethodName.PUT, request, ScsPaths.QPS);
    }

    /**
     * Get sync status of a scs group.
     *
     * @param groupId the scs group id
     * @return the group sync status response
     */
    public ScsGroupSyncStatusResponse groupSyncStatus(String groupId) {
        return invokeGroupById(groupId, ScsGroupSyncStatusResponse.class, HttpMethodName.GET, ScsPaths.SYNC_STATUS);
    }

    /**
     * Set stale readable config for a scs group.
     *
     * @param groupId the scs group id
     * @param request the request of stale readable config
     * @return the response
     */
    public AbstractBceResponse groupStaleReadable(String groupId, ScsGroupStaleReadableRequest request) {
        return invokeGroupAction(groupId, HttpMethodName.PUT, request, ScsPaths.STALE_READABLE);
    }

    private <T extends AbstractBceResponse> T invokeGroup(AbstractBceRequest request, Class<T> responseClass,
                                                         HttpMethodName method, String... paths) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String[] fullPaths = appendPath(new String[]{ScsPaths.GROUP}, paths);
        InternalRequest internalRequest = createRequest(request, method, fullPaths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, responseClass);
    }

    private <T extends AbstractBceResponse> T invokeGroupById(String groupId, Class<T> responseClass,
                                                             HttpMethodName method, String... paths) {
        ScsArgumentUtil.checkString(groupId, "groupId");
        AbstractBceRequest request = new ScsZoneRequest();
        String[] fullPaths = appendPath(new String[]{ScsPaths.GROUP, groupId}, paths);
        InternalRequest internalRequest = createRequest(request, method, fullPaths);
        return invokeHttpClient(internalRequest, responseClass);
    }

    private AbstractBceResponse invokeGroupAction(String groupId, HttpMethodName method,
                                                  AbstractBceRequest request, String... paths) {
        ScsArgumentUtil.checkString(groupId, "groupId");
        if (request == null) {
            request = new ScsZoneRequest();
        }
        String[] fullPaths = appendPath(new String[]{ScsPaths.GROUP, groupId}, paths);
        InternalRequest internalRequest = createRequest(request, method, fullPaths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create a scs parameter template.
     *
     * @param request the request of parameter template create
     * @return the parameter template create response
     */
    public ScsCreateParamsTemplateResponse createParamsTemplate(ScsCreateParamsTemplateRequest request) {
        return invokeParamsTemplate(request, ScsCreateParamsTemplateResponse.class, HttpMethodName.POST, ScsPaths.CREATE);
    }

    /**
     * Request scs parameter template list.
     *
     * @param request the request of parameter template list
     * @return the parameter template list response
     */
    public ScsParamsTemplateListResponse getParamsTemplateList(ScsMarkerRequest request) {
        if (request == null) {
            request = new ScsMarkerRequest();
        }
        String[] paths = {ScsPaths.TEMPLATE, ScsPaths.LIST};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        addMarkerParameters(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsParamsTemplateListResponse.class);
    }

    /**
     * Get scs parameter template detail.
     *
     * @param templateId the template id
     * @return the parameter template detail response
     */
    public ScsParamsTemplateDetailResponse getParamsTemplateDetail(String templateId) {
        return invokeParamsTemplateById(templateId, ScsParamsTemplateDetailResponse.class, HttpMethodName.GET);
    }

    /**
     * Delete a scs parameter template.
     *
     * @param templateId the template id
     * @return the response
     */
    public AbstractBceResponse deleteParamsTemplate(String templateId) {
        return invokeParamsTemplateAction(templateId, HttpMethodName.DELETE, null, ScsPaths.DELETE);
    }

    /**
     * Rename a scs parameter template.
     *
     * @param templateId the template id
     * @param request the request of parameter template rename
     * @return the response
     */
    public AbstractBceResponse renameParamsTemplate(String templateId, ScsParamsTemplateRenameRequest request) {
        return invokeParamsTemplateAction(templateId, HttpMethodName.PUT, request, ScsPaths.RENAME);
    }

    /**
     * Apply a scs parameter template.
     *
     * @param templateId the template id
     * @param request the request of parameter template apply
     * @return the response
     */
    public AbstractBceResponse applyParamsTemplate(String templateId, ScsApplyParamsTemplateRequest request) {
        return invokeParamsTemplateAction(templateId, HttpMethodName.POST, request, ScsPaths.APPLY);
    }

    /**
     * Add parameters to a scs parameter template.
     *
     * @param templateId the template id
     * @param request the request of parameter add
     * @return the response
     */
    public AbstractBceResponse templateAddParams(String templateId, ScsParamsTemplateModifyParamsRequest request) {
        return invokeParamsTemplateAction(templateId, HttpMethodName.POST, request, ScsPaths.ADD_PARAMS);
    }

    /**
     * Modify parameters in a scs parameter template.
     *
     * @param templateId the template id
     * @param request the request of parameter modify
     * @return the response
     */
    public AbstractBceResponse templateModifyParams(String templateId, ScsParamsTemplateModifyParamsRequest request) {
        return invokeParamsTemplateAction(templateId, HttpMethodName.PUT, request, ScsPaths.MODIFY_PARAMS);
    }

    /**
     * Delete parameters from a scs parameter template.
     *
     * @param templateId the template id
     * @param request the request of parameter delete
     * @return the response
     */
    public AbstractBceResponse templateDeleteParams(String templateId, ScsParamsTemplateDeleteParamsRequest request) {
        return invokeParamsTemplateAction(templateId, HttpMethodName.POST, request, ScsPaths.DELETE_PARAMS);
    }

    /**
     * Get scs system templates.
     *
     * @param request the request of system template list
     * @return the system template response
     */
    public ScsSystemTemplateResponse getSystemTemplate(ScsSystemTemplateRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String[] paths = {ScsPaths.TEMPLATE, ScsPaths.SYSTEM};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        addParameterIfNotEmpty(internalRequest, "engine", request.getEngine());
        addParameterIfNotEmpty(internalRequest, "engineVersion", request.getEngineVersion());
        addParameterIfNotEmpty(internalRequest, "clusterType", request.getClusterType());
        return invokeHttpClient(internalRequest, ScsSystemTemplateResponse.class);
    }

    /**
     * Get apply records of a scs parameter template.
     *
     * @param templateId the template id
     * @param request the request of template apply records
     * @return the template apply records response
     */
    public ScsTemplateApplyRecordsResponse getTemplateApplyRecords(String templateId, ScsMarkerRequest request) {
        ScsArgumentUtil.checkString(templateId, "templateId");
        if (request == null) {
            request = new ScsMarkerRequest();
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                ScsPaths.TEMPLATE, ScsPaths.RECORD, templateId);
        addMarkerParameters(internalRequest, request);
        return invokeHttpClient(internalRequest, ScsTemplateApplyRecordsResponse.class);
    }

    private <T extends AbstractBceResponse> T invokeParamsTemplate(AbstractBceRequest request, Class<T> responseClass,
                                                                   HttpMethodName method, String... paths) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String[] fullPaths = appendPath(new String[]{ScsPaths.TEMPLATE}, paths);
        InternalRequest internalRequest = createRequest(request, method, fullPaths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, responseClass);
    }

    private <T extends AbstractBceResponse> T invokeParamsTemplateById(String templateId, Class<T> responseClass,
                                                                       HttpMethodName method, String... paths) {
        ScsArgumentUtil.checkString(templateId, "templateId");
        AbstractBceRequest request = new ScsZoneRequest();
        String[] fullPaths = appendPath(new String[]{ScsPaths.TEMPLATE}, paths);
        fullPaths = appendPath(fullPaths, templateId);
        InternalRequest internalRequest = createRequest(request, method, fullPaths);
        return invokeHttpClient(internalRequest, responseClass);
    }

    private AbstractBceResponse invokeParamsTemplateAction(String templateId, HttpMethodName method,
                                                           AbstractBceRequest request, String action) {
        ScsArgumentUtil.checkString(templateId, "templateId");
        if (request == null) {
            request = new ScsZoneRequest();
        }
        String[] paths = {ScsPaths.TEMPLATE, action, templateId};
        InternalRequest internalRequest = createRequest(request, method, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Pre-check scs sync group creation.
     *
     * @param request the request of sync group pre-check
     * @return the sync group pre-check response
     */
    public ScsSyncGroupPreCheckResponse syncGroupPreCheck(ScsSyncGroupPreCheckRequest request) {
        return invokeSyncGroup(request, ScsSyncGroupPreCheckResponse.class, HttpMethodName.POST, ScsPaths.CHECK);
    }

    /**
     * Create a scs sync group.
     *
     * @param request the request of sync group create
     * @return the sync group create response
     */
    public ScsSyncGroupCreateResponse createSyncGroup(ScsSyncGroupCreateRequest request) {
        return invokeSyncGroup(request, ScsSyncGroupCreateResponse.class, HttpMethodName.POST, ScsPaths.CREATE);
    }

    /**
     * Request scs sync group list.
     *
     * @param request the request of sync group list
     * @return the sync group list response
     */
    public ScsSyncGroupListResponse listSyncGroup(ScsSyncGroupListRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String[] paths = {ScsPaths.SYNC_GROUP, ScsPaths.LIST};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        addPageParameters(internalRequest, "page", request.getPageNo(), request.getPageSize());
        return invokeHttpClient(internalRequest, ScsSyncGroupListResponse.class);
    }

    /**
     * Get scs sync group detail.
     *
     * @param groupId the sync group id
     * @return the sync group detail response
     */
    public ScsSyncGroupDetailResponse getSyncGroupDetail(String groupId) {
        return invokeSyncGroupById(groupId, ScsSyncGroupDetailResponse.class, HttpMethodName.GET);
    }

    /**
     * Delete a scs sync group.
     *
     * @param groupId the sync group id
     * @return the response
     */
    public AbstractBceResponse deleteSyncGroup(String groupId) {
        return invokeSyncGroupAction(groupId, HttpMethodName.DELETE, null);
    }

    /**
     * Remove a cluster from a scs sync group.
     *
     * @param groupId the sync group id
     * @param request the request of cluster remove
     * @return the response
     */
    public AbstractBceResponse removeSyncGroupCluster(String groupId, ScsSyncGroupMemberRequest request) {
        return invokeSyncGroupAction(groupId, HttpMethodName.POST, request, ScsPaths.REMOVE_CLUSTER);
    }

    /**
     * Add a cluster to a scs sync group.
     *
     * @param groupId the sync group id
     * @param request the request of cluster add
     * @return the response
     */
    public AbstractBceResponse addSyncGroupCluster(String groupId, ScsSyncGroupMemberRequest request) {
        return invokeSyncGroupAction(groupId, HttpMethodName.POST, request, ScsPaths.ADD_CLUSTER);
    }

    /**
     * Modify scs sync group name.
     *
     * @param groupId the sync group id
     * @param request the request of sync group name modify
     * @return the response
     */
    public AbstractBceResponse modifySyncGroupName(String groupId, ScsSyncGroupModifyNameRequest request) {
        return invokeSyncGroupAction(groupId, HttpMethodName.PUT, request);
    }

    /**
     * Get scs sync group status.
     *
     * @param groupId the sync group id
     * @return the sync group status response
     */
    public ScsSyncGroupStatusResponse getSyncGroupStatus(String groupId) {
        return invokeSyncGroupById(groupId, ScsSyncGroupStatusResponse.class, HttpMethodName.GET, ScsPaths.SYNC_STATUS);
    }

    /**
     * Get scs sync group delay information.
     *
     * @param groupId the sync group id
     * @return the sync group delay info response
     */
    public ScsSyncGroupDelayInfoResponse getSyncGroupDelayInfo(String groupId) {
        return invokeSyncGroupById(groupId, ScsSyncGroupDelayInfoResponse.class, HttpMethodName.GET, ScsPaths.DELAY_INFO);
    }

    /**
     * Modify BNS group of a scs sync group.
     *
     * @param groupId the sync group id
     * @param request the request of BNS group modify
     * @return the response
     */
    public AbstractBceResponse modifySyncGroupBnsGroup(String groupId, ScsSyncGroupModifyBnsNameRequest request) {
        return invokeSyncGroupAction(groupId, HttpMethodName.PUT, request, ScsPaths.MODIFY_BNS_GROUP);
    }

    private <T extends AbstractBceResponse> T invokeSyncGroup(AbstractBceRequest request, Class<T> responseClass,
                                                             HttpMethodName method, String... paths) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String[] fullPaths = appendPath(new String[]{ScsPaths.SYNC_GROUP}, paths);
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, method, fullPaths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, responseClass);
    }

    private <T extends AbstractBceResponse> T invokeSyncGroupById(String groupId, Class<T> responseClass,
                                                                 HttpMethodName method, String... paths) {
        ScsArgumentUtil.checkString(groupId, "groupId");
        AbstractBceRequest request = new ScsZoneRequest();
        String[] fullPaths = appendPath(new String[]{ScsPaths.SYNC_GROUP, groupId}, paths);
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, method, fullPaths);
        return invokeHttpClient(internalRequest, responseClass);
    }

    private AbstractBceResponse invokeSyncGroupAction(String groupId, HttpMethodName method,
                                                      AbstractBceRequest request, String... paths) {
        ScsArgumentUtil.checkString(groupId, "groupId");
        if (request == null) {
            request = new ScsZoneRequest();
        }
        String[] fullPaths = appendPath(new String[]{ScsPaths.SYNC_GROUP, groupId}, paths);
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, method, fullPaths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    private String[] appendPath(String[] paths, String... morePaths) {
        List<String> allPaths = new ArrayList<String>();
        Collections.addAll(allPaths, paths);
        if (morePaths != null) {
            Collections.addAll(allPaths, morePaths);
        }
        return allPaths.toArray(new String[0]);
    }

    private void addMarkerParameters(InternalRequest internalRequest, ScsMarkerRequest request) {
        addParameterIfNotEmpty(internalRequest, "marker", request.getMarker());
        Integer maxKeys = request.getMaxKeys();
        if (maxKeys != null) {
            checkPageSize(maxKeys, "maxKeys");
            internalRequest.addParameter("maxKeys", String.valueOf(maxKeys));
        }
    }

    private void addPageParameters(InternalRequest internalRequest, String pageKey, Integer pageNo, Integer pageSize) {
        checkPageParameters(pageKey, pageNo, pageSize);
        if (pageNo != null) {
            internalRequest.addParameter(pageKey, String.valueOf(pageNo));
        }
        if (pageSize != null) {
            internalRequest.addParameter("pageSize", String.valueOf(pageSize));
        }
    }

    private void checkPageParameters(String pageKey, Integer pageNo, Integer pageSize) {
        if (pageNo != null && pageNo <= 0) {
            throw new IllegalArgumentException(pageKey + " must be greater than 0");
        }
        if (pageSize != null) {
            checkPageSize(pageSize, "pageSize");
        }
    }

    private void checkPageSize(Integer pageSize, String parameterName) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException(parameterName + " must be greater than 0");
        }
        if (pageSize > 1000) {
            throw new IllegalArgumentException(parameterName + " can not be greater than 1000");
        }
    }

    private void addParameterIfNotEmpty(InternalRequest internalRequest, String key, String value) {
        if (StringUtils.isNotEmpty(value)) {
            internalRequest.addParameter(key, value);
        }
    }

    /**
     * Get the scs zone list
     *
     * @return the scs zone list
     */
    public ScsZoneResponse getZoneList() {
        ScsZoneRequest request = new ScsZoneRequest();
        String[] paths = {ScsPaths.ZONE};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, ScsZoneResponse.class);
    }

    /**
     * Request the scs subnet list
     *
     * @param request the request of scs subnet list
     * @return the response
     */
    public ScsSubnetResponse getSubnetList(ScsSubnetRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        String[] paths = {ScsPaths.SUBNET};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.GET, paths);
        if (StringUtils.isNotEmpty(request.getVpcId())) {
            internalRequest.addParameter("vpcId", request.getVpcId());
        }
        if (StringUtils.isNotEmpty(request.getZoneName())) {
            internalRequest.addParameter("zoneName", request.getZoneName());
        }
        return invokeHttpClient(internalRequest, ScsSubnetResponse.class);
    }

    /**
     * Release the specific scs instance
     *
     * @param request the request of release scs instance
     * @return the response
     */
    public AbstractBceResponse releaseInstance(ScsReleaseInstanceRequest request) {
        Validate.checkNotNull(request, ScsArgumentUtil.REQUEST_NULL_ERROR_MESSAGE);
        ScsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID);
        String[] paths = {ScsPaths.INSTANCE, request.getInstanceId()};
        InternalRequest internalRequest = createRequest(ScsPaths.VERSION_1, request, HttpMethodName.DELETE, paths);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

}
