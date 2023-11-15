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
import com.baidubce.services.scs.model.ScsBilling;
import com.baidubce.services.scs.model.ScsChangeConfigRequest;
import com.baidubce.services.scs.model.ScsCreateRequest;
import com.baidubce.services.scs.model.ScsCreateResponse;
import com.baidubce.services.scs.model.ScsDeleteInstanceRequest;
import com.baidubce.services.scs.model.ScsFlushInstanceRequest;
import com.baidubce.services.scs.model.ScsInstanceDetailRequest;
import com.baidubce.services.scs.model.ScsInstanceDetailResponse;
import com.baidubce.services.scs.model.ScsListOrder;
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
import com.baidubce.services.scs.model.ScsSubnetRequest;
import com.baidubce.services.scs.model.ScsSubnetResponse;
import com.baidubce.services.scs.model.ScsSwapDomainRequest;
import com.baidubce.services.scs.model.ScsZoneRequest;
import com.baidubce.services.scs.model.ScsZoneResponse;
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
        String clientAuth = request.getClientAuth().trim();
        if (StringUtils.isNotEmpty(clientAuth)) {
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
            Integer maxKeys = request.getMaxKeys();
            if (maxKeys != null) {
                if (maxKeys > 1000) {
                    throw new IllegalArgumentException("maxKeys can not be greater than 1000");
                }
                internalRequest.addParameter("maxKeys", String.valueOf(maxKeys));
            }
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
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, ScsPaths.INSTANCE, instanceId);
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
        InternalRequest internalRequest = createRequest(request, get, ScsPaths.INSTANCE, id, ScsPaths.PARAMETER);
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
    public AbstractBceResponse changeScsInstance(ScsChangeConfigRequest request) {
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
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
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
