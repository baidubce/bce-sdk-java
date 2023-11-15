/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bcc;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
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
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.bcc.model.Reservation;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.bcc.model.asp.AspAction;
import com.baidubce.services.bcc.model.asp.AttachAspRequest;
import com.baidubce.services.bcc.model.asp.CreateAspRequest;
import com.baidubce.services.bcc.model.asp.CreateAspResponse;
import com.baidubce.services.bcc.model.asp.DeleteAspRequest;
import com.baidubce.services.bcc.model.asp.DetachAspRequest;
import com.baidubce.services.bcc.model.asp.GetAspRequest;
import com.baidubce.services.bcc.model.asp.GetAspResponse;
import com.baidubce.services.bcc.model.asp.ListAspsRequest;
import com.baidubce.services.bcc.model.asp.ListAspsResponse;
import com.baidubce.services.bcc.model.asp.UpdateAspRequest;
import com.baidubce.services.bcc.model.deployset.CreateDeploySetRequest;
import com.baidubce.services.bcc.model.deployset.CreateDeploySetResponse;
import com.baidubce.services.bcc.model.deployset.DeleteDeploySetRequest;
import com.baidubce.services.bcc.model.deployset.DeploySetAction;
import com.baidubce.services.bcc.model.deployset.ListDeploySetResponse;
import com.baidubce.services.bcc.model.deployset.UpdateDeploySetRequest;
import com.baidubce.services.bcc.model.flavor.ListBccBidFlavorResponse;
import com.baidubce.services.bcc.model.flavor.ListBccFlavorSpecResponse;
import com.baidubce.services.bcc.model.flavor.ListFlavorSpecRequest;
import com.baidubce.services.bcc.model.image.CancelRemoteCopyImageRequest;
import com.baidubce.services.bcc.model.image.CreateImageRequest;
import com.baidubce.services.bcc.model.image.CreateImageResponse;
import com.baidubce.services.bcc.model.image.DeleteImageRequest;
import com.baidubce.services.bcc.model.image.GetAvailableImagesBySpecRequest;
import com.baidubce.services.bcc.model.image.GetAvailableImagesBySpecResponse;
import com.baidubce.services.bcc.model.image.GetImageRequest;
import com.baidubce.services.bcc.model.image.GetImageResponse;
import com.baidubce.services.bcc.model.image.ImageAction;
import com.baidubce.services.bcc.model.image.ListImagesRequest;
import com.baidubce.services.bcc.model.image.ListImagesResponse;
import com.baidubce.services.bcc.model.image.ListOsRequest;
import com.baidubce.services.bcc.model.image.ListOsResponse;
import com.baidubce.services.bcc.model.image.ListSharedUserRequest;
import com.baidubce.services.bcc.model.image.ListSharedUserResponse;
import com.baidubce.services.bcc.model.image.RemoteCopyImageRequest;
import com.baidubce.services.bcc.model.image.ShareImageRequest;
import com.baidubce.services.bcc.model.image.UnShareImageRequest;
import com.baidubce.services.bcc.model.instance.BccPriceRequest;
import com.baidubce.services.bcc.model.instance.BccPriceResponse;
import com.baidubce.services.bcc.model.instance.BindSecurityGroupRequest;
import com.baidubce.services.bcc.model.instance.BindTagsRequest;
import com.baidubce.services.bcc.model.instance.CancelBidOrderRequest;
import com.baidubce.services.bcc.model.instance.CancelBidOrderResponse;
import com.baidubce.services.bcc.model.instance.ChangeInstanceSubnetRequest;
import com.baidubce.services.bcc.model.instance.ChangeToPrepaidRequest;
import com.baidubce.services.bcc.model.instance.ChangeToPrepaidResponse;
import com.baidubce.services.bcc.model.instance.CreateInstanceRequest;
import com.baidubce.services.bcc.model.instance.CreateInstanceResponse;
import com.baidubce.services.bcc.model.instance.FpgaCardType;
import com.baidubce.services.bcc.model.instance.GetBidInstancePriceRequest;
import com.baidubce.services.bcc.model.instance.GetBidInstancePriceResponse;
import com.baidubce.services.bcc.model.instance.GetInstanceRequest;
import com.baidubce.services.bcc.model.instance.GetInstanceResponse;
import com.baidubce.services.bcc.model.instance.GetInstanceVncRequest;
import com.baidubce.services.bcc.model.instance.GetInstanceVncResponse;
import com.baidubce.services.bcc.model.instance.GpuCardType;
import com.baidubce.services.bcc.model.instance.InstanceAction;
import com.baidubce.services.bcc.model.instance.InstanceType;
import com.baidubce.services.bcc.model.instance.ListGetInstanceNoChargeRequest;
import com.baidubce.services.bcc.model.instance.ListInstanceSpecsRequest;
import com.baidubce.services.bcc.model.instance.ListInstanceSpecsResponse;
import com.baidubce.services.bcc.model.instance.ListInstancesRequest;
import com.baidubce.services.bcc.model.instance.ListInstancesResponse;
import com.baidubce.services.bcc.model.instance.ModifyInstanceAttributesRequest;
import com.baidubce.services.bcc.model.instance.ModifyInstanceDescRequest;
import com.baidubce.services.bcc.model.instance.ModifyInstanceHostnameRequest;
import com.baidubce.services.bcc.model.instance.ModifyInstancePasswordRequest;
import com.baidubce.services.bcc.model.instance.PurchaseReservedInstanceRequeset;
import com.baidubce.services.bcc.model.instance.RebootInstanceRequest;
import com.baidubce.services.bcc.model.instance.RebuildBatchInstanceRequest;
import com.baidubce.services.bcc.model.instance.RebuildInstanceRequest;
import com.baidubce.services.bcc.model.instance.ReleaseInstanceByPostRequest;
import com.baidubce.services.bcc.model.instance.ReleaseInstanceRequest;
import com.baidubce.services.bcc.model.instance.ResizeInstanceRequest;
import com.baidubce.services.bcc.model.instance.StartInstanceRequest;
import com.baidubce.services.bcc.model.instance.StopInstanceRequest;
import com.baidubce.services.bcc.model.instance.UnbindSecurityGroupRequest;
import com.baidubce.services.bcc.model.instance.UnbindTagsRequest;
import com.baidubce.services.bcc.model.keypair.KeypairAction;
import com.baidubce.services.bcc.model.keypair.KeypairAttachRequest;
import com.baidubce.services.bcc.model.keypair.KeypairCreateRequest;
import com.baidubce.services.bcc.model.keypair.KeypairCreateResponse;
import com.baidubce.services.bcc.model.keypair.KeypairDeleteRequest;
import com.baidubce.services.bcc.model.keypair.KeypairDetachRequest;
import com.baidubce.services.bcc.model.keypair.KeypairDetailRequest;
import com.baidubce.services.bcc.model.keypair.KeypairImportRequest;
import com.baidubce.services.bcc.model.keypair.KeypairListRequest;
import com.baidubce.services.bcc.model.keypair.KeypairListResponse;
import com.baidubce.services.bcc.model.keypair.KeypairModel;
import com.baidubce.services.bcc.model.keypair.KeypairRenameRequest;
import com.baidubce.services.bcc.model.keypair.KeypairResponse;
import com.baidubce.services.bcc.model.keypair.KeypairUpdateDescRequest;
import com.baidubce.services.bcc.model.securitygroup.CreateSecurityGroupRequest;
import com.baidubce.services.bcc.model.securitygroup.CreateSecurityGroupResponse;
import com.baidubce.services.bcc.model.securitygroup.DeleteSecurityGroupRequest;
import com.baidubce.services.bcc.model.securitygroup.DeleteSecurityGroupRuleRequest;
import com.baidubce.services.bcc.model.securitygroup.ListSecurityGroupsRequest;
import com.baidubce.services.bcc.model.securitygroup.ListSecurityGroupsResponse;
import com.baidubce.services.bcc.model.securitygroup.SecurityGroupAction;
import com.baidubce.services.bcc.model.securitygroup.SecurityGroupRuleOperateRequest;
import com.baidubce.services.bcc.model.securitygroup.UpdateSecurityGroupRuleRequest;
import com.baidubce.services.bcc.model.snapshot.CreateSnapshotRequest;
import com.baidubce.services.bcc.model.snapshot.CreateSnapshotResponse;
import com.baidubce.services.bcc.model.snapshot.DeleteSnapshotRequest;
import com.baidubce.services.bcc.model.snapshot.GetSnapshotRequest;
import com.baidubce.services.bcc.model.snapshot.GetSnapshotResponse;
import com.baidubce.services.bcc.model.snapshot.ListSnapchainRequest;
import com.baidubce.services.bcc.model.snapshot.ListSnapchainResponse;
import com.baidubce.services.bcc.model.snapshot.ListSnapshotsRequest;
import com.baidubce.services.bcc.model.snapshot.ListSnapshotsResponse;
import com.baidubce.services.bcc.model.volume.AttachVolumeRequest;
import com.baidubce.services.bcc.model.volume.AttachVolumeResponse;
import com.baidubce.services.bcc.model.volume.AutoRenewVolumeClusterRequest;
import com.baidubce.services.bcc.model.volume.AutoRenewVolumeRequest;
import com.baidubce.services.bcc.model.volume.CancelAutoRenewVolumeClusterRequest;
import com.baidubce.services.bcc.model.volume.CancelAutoRenewVolumeRequest;
import com.baidubce.services.bcc.model.volume.CreateVolumeClusterRequest;
import com.baidubce.services.bcc.model.volume.CreateVolumeClusterResponse;
import com.baidubce.services.bcc.model.volume.CreateVolumeRequest;
import com.baidubce.services.bcc.model.volume.CreateVolumeResponse;
import com.baidubce.services.bcc.model.volume.DetachVolumeRequest;
import com.baidubce.services.bcc.model.volume.GetVolumeClusterRequest;
import com.baidubce.services.bcc.model.volume.GetVolumeClusterResponse;
import com.baidubce.services.bcc.model.volume.GetVolumeRequest;
import com.baidubce.services.bcc.model.volume.GetVolumeResponse;
import com.baidubce.services.bcc.model.volume.ListVolumeClustersRequest;
import com.baidubce.services.bcc.model.volume.ListVolumeClustersResponse;
import com.baidubce.services.bcc.model.volume.ListVolumesRequest;
import com.baidubce.services.bcc.model.volume.ListVolumesResponse;
import com.baidubce.services.bcc.model.volume.ModifyCdsAttrRequest;
import com.baidubce.services.bcc.model.volume.ModifyVolumeChargeRequest;
import com.baidubce.services.bcc.model.volume.ModifyVolumeChargeTypeRequest;
import com.baidubce.services.bcc.model.volume.PurchaseReservedVolumeClusterRequest;
import com.baidubce.services.bcc.model.volume.PurchaseReservedVolumeRequest;
import com.baidubce.services.bcc.model.volume.ReleaseVolumeRequest;
import com.baidubce.services.bcc.model.volume.RenameVolumeRequest;
import com.baidubce.services.bcc.model.volume.ResizeVolumeClusterRequest;
import com.baidubce.services.bcc.model.volume.ResizeVolumeRequest;
import com.baidubce.services.bcc.model.volume.RollbackVolumeRequest;
import com.baidubce.services.bcc.model.volume.VolumeAction;
import com.baidubce.services.bcc.model.zone.ListZonesResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkState;


/**
 * Provides the client for accessing the Baidu Cloud Compute Service(bcc).
 */
public class BccClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BccClient.class);

    private static final String VERSION = "v2";
    private static final String INSTANCE_PREFIX = "instance";
    private static final String INSTANCE_BY_SPEC_PREFIX = "instanceBySpec";
    private static final String BID = "bid";
    private static final String FLAVOR_SPEC_PREFIX = "flavorSpec";
    private static final String VOLUME_PREFIX = "volume";
    private static final String IMAGE_PREFIX = "image";
    private static final String OS_PREFIX = "os";
    private static final String SECURITYGROUP_PREFIX = "securityGroup";
    private static final String SNAPSHOT_PREFIX = "snapshot";
    private static final String ZONE = "zone";
    private static final String TAG = "tag";
    private static final String ASP = "asp";
    private static final String KEYPAIR = "keypair";
    private static final String SHARED_USER = "sharedUsers";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String MARKER = "marker";
    private static final String MAX_KEYS = "maxKeys";
    private static final String ZONE_NAME = "zoneName";
    private static final String INTERNAL_IP = "internalIp";
    private static final String DEDICATED_HOST_ID = "dedicatedHostId";
    private static final String IMAGE_TYPE = "imageType";
    private static final String VOLUME_NAME = "volumeName";
    private static final String VPC_ID = "vpcId";
    private static final String FLAVOR_SPEC = "flavorSpec";
    private static final String PRICE = "price";
    private static final String DEPLOYSET = "deployset";
    private static final String CHAIN = "chain";
    private static final String BID_FLAVOR = "bidFlavor";
    private static final String BID_PRICE = "bidPrice";
    private static final String CANCEL_BID_ORDER = "cancelBidOrder";
    private static final String NO_CHARGE = "noCharge";
    private static final String REBUILD = "rebuild";
    private static final String SUBNET_PREFIX = "subnet";
    private static final String CHANGE_SUBNET = "changeSubnet";
    private static final String SECURITY_GROUP_RULE_PREFIX = "rule";
    private static final String SECURITY_GROUP_RULE_UPDATE_PREFIX = "update";
    private static final String SYNC_CREATE = "syncCreate";

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String CHANGETAGS_NULL_ERROR_MESSAGE = "request changeTags should not be null.";
    private static final String INSTANCEID_MESSAGE_KEY = "instanceId";
    private static final String INSTANCE_TYPE_MESSAGE_KEY = "instanceType";
    private static final String ORDER_ID_MESSAGE_KEY = "orderId";
    private static final String TAGKEY_MESSAGE_KEY = "tagKey";
    private static final String ADMINPASS_MESSAGE_KEY = "adminPass";
    private static final String IMAGEID_MESSAGE_KEY = "imageId";
    private static final String NAME_MESSAGE_KEY = "name";
    private static final String DESC_MESSAGE_KEY = "desc";
    private static final String SECURITYGROUPID_MESSAGE_KEY = "securityGroupId";
    private static final String VOLUMEID_MESSAGE_KEY = "volumeId";
    private static final String SNAPSHOTID_MESSAGE_KEY = "snapshotId";
    private static final String IMAGENAME_MESSAGE_KEY = "imageName";
    private static final String SNAPSHOTNAME_MESSAGE_KEY = "snapshotName";
    private static final String ASPNAME_MESSAGE_KEY = "aspName";
    private static final String ASPID_MESSAGE_KEY = "aspId";
    private static final String KEYPAIR_ID_MESSAGE_KEY = "keypair";
    private static final String SUBNETID_MESSAGE_KEY = "subnetId";
    private static final String SECURITY_GROUP_RULE_ID_MESSAGE_KEY = "securityGroupRuleId";
    private static final String CLUSTERID_MESSAGE_KEY = "clusterId";
    private static final String CLUSTER_NAME_MESSAGE_KEY = "clusterName";
    private static final String VOLUME_CLUSTER_PREFIX = "volume/cluster";
    private static final String UUID_FLAG = "uuidFlag";


    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all bcc service calls.
     */
    private static final HttpResponseHandler[] bcc_handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on bcc.
     */
    public BccClient() {
        this(new BccClientConfiguration());
    }

    /**
     * Constructs a new bcc client using the client configuration to access bcc.
     *
     * @param clientConfiguration The bcc client configuration options controlling how this client
     *                            connects to bcc (e.g. proxy settings, retry counts, etc).
     */
    public BccClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, bcc_handlers);
    }


    /**
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();

        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     *                        any additional headers or parameters, and execute.
     * @param bceRequest The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson = null;
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
     *
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * The encryption implement for AES-128 algorithm for BCE password encryption.
     * Only the first 16 bytes of privateKey will be used to encrypt the content.
     *
     * See more detail on
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     * BCE API doc</a>
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
     * The method to generate a default Billing which is Postpaid.
     *
     * @return The Billing object with Postpaid PaymentTiming.
     */
    private Billing generateDefaultBilling() {
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        return billing;
    }

    /**
     * The method to generate a default Billing with default Reservation which default ReservationLength is 1.
     *
     * @return The Billing object with default Reservation which default ReservationLength is 1
     */
    private Billing generateDefaultBillingWithReservation() {
        Billing billing = new Billing();
        billing.withReservation(new Reservation().withReservationLength(1));
        return billing;
    }

    /**
     * Create a bcc Instance with the specified options,
     * see all the supported instance in {@link com.baidubce.services.bcc.model.instance.InstanceType}
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for creating a bcc Instance.
     * @return List of instanceId newly created
     * @throws BceClientException
     */
    public CreateInstanceResponse createInstance(CreateInstanceRequest request)
            throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        if (null != request.getTags() && !request.getTags().isEmpty()) {
            for (TagModel tag : request.getTags()) {
                checkStringNotEmpty(tag.getTagKey(), checkEmptyExceptionMessageFormat(TAGKEY_MESSAGE_KEY));
            }
        }
        if (InstanceType.G1.name().equalsIgnoreCase(request.getInstanceType())) {
            checkIsTrue(GpuCardType.isExists(request.getGpuCard()), "invalid gpgCard parameter");
            checkIsTrue(request.getCardCount() > 0, "invalid cardCount parameter");
        }
        if (InstanceType.F1.name().equalsIgnoreCase(request.getInstanceType())) {
            checkIsTrue(FpgaCardType.isExists(request.getFpgaCard()), "invalid fpgaCard parameter");
            checkIsTrue(request.getCardCount() > 0, "invalid cardCount parameter");
        }
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, INSTANCE_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        if (!Strings.isNullOrEmpty(request.getAdminPass())) {
            BceCredentials credentials = config.getCredentials();
            if (internalRequest.getCredentials() != null) {
                credentials = internalRequest.getCredentials();
            }
            try {
                request.setAdminPass(this.aes128WithFirst16Char(request.getAdminPass(), credentials.getSecretKey()));
            } catch (GeneralSecurityException e) {
                throw new BceClientException("Encryption procedure exception", e);
            }
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateInstanceResponse.class);
    }

    /**
     * Create a bcc Instance by spec
     * see all the supported instance in {@link com.baidubce.services.bcc.model.instance.InstanceType}
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for creating a bcc Instance.
     * @return List of instanceId newly created
     * @throws BceClientException
     */
    public CreateInstanceResponse createInstanceBySpec(CreateInstanceRequest request)
            throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        if (null != request.getTags() && !request.getTags().isEmpty()) {
            for (TagModel tag : request.getTags()) {
                checkStringNotEmpty(tag.getTagKey(), checkEmptyExceptionMessageFormat(TAGKEY_MESSAGE_KEY));
            }
        }
        if (StringUtils.isEmpty(request.getSpec())) {
            if (InstanceType.G1.name().equalsIgnoreCase(request.getInstanceType())) {
                checkIsTrue(GpuCardType.isExists(request.getGpuCard()), "invalid gpgCard parameter");
                checkIsTrue(request.getCardCount() > 0, "invalid cardCount parameter");
            }
            if (InstanceType.F1.name().equalsIgnoreCase(request.getInstanceType())) {
                checkIsTrue(FpgaCardType.isExists(request.getFpgaCard()), "invalid fpgaCard parameter");
                checkIsTrue(request.getCardCount() > 0, "invalid cardCount parameter");
            }
        }
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, INSTANCE_BY_SPEC_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        if (!Strings.isNullOrEmpty(request.getAdminPass())) {
            BceCredentials credentials = config.getCredentials();
            if (internalRequest.getCredentials() != null) {
                credentials = internalRequest.getCredentials();
            }
            try {
                request.setAdminPass(this.aes128WithFirst16Char(request.getAdminPass(), credentials.getSecretKey()));
            } catch (GeneralSecurityException e) {
                throw new BceClientException("Encryption procedure exception", e);
            }
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateInstanceResponse.class);
    }

    /**
     * Create a bcc bidding Instance with the specified options,
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p>
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for creating a bcc bidding Instance.
     * @return List of instanceId newly created
     * @throws BceClientException
     */
    public CreateInstanceResponse createBidInstance(CreateInstanceRequest request)
            throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling().withPaymentTiming("bidding"));
        }
        if (null != request.getTags() && !request.getTags().isEmpty()) {
            for (TagModel tag : request.getTags()) {
                checkStringNotEmpty(tag.getTagKey(), checkEmptyExceptionMessageFormat(TAGKEY_MESSAGE_KEY));
            }
        }
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, INSTANCE_PREFIX, BID);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        if (!Strings.isNullOrEmpty(request.getAdminPass())) {
            BceCredentials credentials = config.getCredentials();
            if (internalRequest.getCredentials() != null) {
                credentials = internalRequest.getCredentials();
            }
            try {
                request.setAdminPass(this.aes128WithFirst16Char(request.getAdminPass(), credentials.getSecretKey()));
            } catch (GeneralSecurityException e) {
                throw new BceClientException("Encryption procedure exception", e);
            }
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateInstanceResponse.class);
    }

    /**
     * Get the price information of specified instance.
     *
     * @param request The request containing all options for get specified bcc price.
     * @return specified bcc price model.
     */
    public BccPriceResponse getPriceBySpec(BccPriceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, INSTANCE_PREFIX, PRICE);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, BccPriceResponse.class);
    }


    /**
     * Return a list of instances owned by the authenticated user.
     *
     * @return The response containing a list of instances owned by the authenticated user.
     */
    public ListInstancesResponse listInstances() {
        return this.listInstances(new ListInstancesRequest());
    }

    /**
     * Return a list of instances owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's bcc Instance.
     * @return The response containing a list of instances owned by the authenticated user.
     */
    public ListInstancesResponse listInstances(ListInstancesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, INSTANCE_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getInternalIp())) {
            internalRequest.addParameter(INTERNAL_IP, request.getInternalIp());
        }
        if (!Strings.isNullOrEmpty(request.getDedicatedHostId())) {
            internalRequest.addParameter(DEDICATED_HOST_ID, request.getDedicatedHostId());
        }
        if (!Strings.isNullOrEmpty(request.getZoneName())) {
            internalRequest.addParameter(ZONE_NAME, request.getZoneName());
        }
        return invokeHttpClient(internalRequest, ListInstancesResponse.class);
    }

    /**
     * Return a list of flavorSpec owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's flavor spec.
     * @return The response containing a list of flavor spec owned by the authenticated user.
     */
    public ListBccFlavorSpecResponse listFlavorSpec(ListFlavorSpecRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, INSTANCE_PREFIX, FLAVOR_SPEC);
        if (!Strings.isNullOrEmpty(request.getZoneName())) {
            internalRequest.addParameter(ZONE_NAME, request.getZoneName());
        }
        return invokeHttpClient(internalRequest, ListBccFlavorSpecResponse.class);
    }

    /**
     * Return a list of bidFlavor owned by the authenticated user.
     *
     * @return The bidding instance flavor.
     */
    public ListBccBidFlavorResponse listBidFlavor() {
        return listBidFlavor(new AbstractBceRequest() {
            @Override
            public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
                return null;
            }
        });
    }

    /**
     * Return a list of bidFlavor owned by the authenticated user.
     *
     * @param request AbstractBceRequest
     * @return The bidding instance flavor.
     */
    private ListBccBidFlavorResponse listBidFlavor(AbstractBceRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, INSTANCE_PREFIX, BID_FLAVOR);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ListBccBidFlavorResponse.class);
    }

    /**
     * Query market price of bidding instance
     *
     * @param request The request containing all options for querying market price of bidding instance.
     * @return GetBidInstancePriceResponse
     * @throws BceClientException
     */
    public GetBidInstancePriceResponse getBidInstancePrice(GetBidInstancePriceRequest request)
            throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null != request.getTags() && !request.getTags().isEmpty()) {
            for (TagModel tag : request.getTags()) {
                checkStringNotEmpty(tag.getTagKey(), checkEmptyExceptionMessageFormat(TAGKEY_MESSAGE_KEY));
            }
        }
        checkStringNotEmpty(request.getInstanceType(), checkEmptyExceptionMessageFormat(INSTANCE_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, INSTANCE_PREFIX, BID_PRICE);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        if (!Strings.isNullOrEmpty(request.getAdminPass())) {
            BceCredentials credentials = config.getCredentials();
            if (internalRequest.getCredentials() != null) {
                credentials = internalRequest.getCredentials();
            }
            try {
                request.setAdminPass(this.aes128WithFirst16Char(request.getAdminPass(), credentials.getSecretKey()));
            } catch (GeneralSecurityException e) {
                throw new BceClientException("Encryption procedure exception", e);
            }
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, GetBidInstancePriceResponse.class);
    }

    /**
     * Cancel the bidding instance order.
     *
     * @param oderId The id of the order.
     * @return The request containing the orderId which is canceled by the user.
     */
    public CancelBidOrderResponse cancelBidOrder(String oderId) {
        return this.cancelBidOrder(new CancelBidOrderRequest().withOrderId(oderId));
    }

    /**
     * Cancel the bidding instance order.
     *
     * @param request The request containing the orderId which is the user wants to cancel.
     * @return The request containing the orderId which is canceled by the user.
     */
    public CancelBidOrderResponse cancelBidOrder(CancelBidOrderRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getOrderId(), checkEmptyExceptionMessageFormat(ORDER_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                INSTANCE_PREFIX, CANCEL_BID_ORDER);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CancelBidOrderResponse.class);
    }

    /**
     * Return a list of noCharge instances owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's bcc noCharge Instance.
     * @return The response containing a list of noCharge instances owned by the authenticated user.
     */
    public ListInstancesResponse getInstanceNoChargeList(ListGetInstanceNoChargeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                INSTANCE_PREFIX, NO_CHARGE);
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getInternalIp())) {
            internalRequest.addParameter(INTERNAL_IP, request.getInternalIp());
        }
        if (!Strings.isNullOrEmpty(request.getZoneName())) {
            internalRequest.addParameter(ZONE_NAME, request.getZoneName());
        }
        return invokeHttpClient(internalRequest, ListInstancesResponse.class);
    }

    /**
     * Get the detail information of specified instance.
     *
     * @param instanceId The id of the instance.
     * @return A instance detail model for the instanceId.
     */
    public GetInstanceResponse getInstance(String instanceId) {
        return getInstance(new GetInstanceRequest().withInstanceId(instanceId));
    }

    /**
     * Get the detail information of specified instance.
     *
     * @param request The request containing all options for getting the instance info.
     * @return A instance detail model for the instanceId.
     */
    public GetInstanceResponse getInstance(GetInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(),
                checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, INSTANCE_PREFIX, request.getInstanceId());
        return this.invokeHttpClient(internalRequest, GetInstanceResponse.class);
    }

    /**
     * Change the instance payment method to prepaid.
     *
     * @param request The request containing all options for changing the instance payment method to prepaid.
     * @return The id of the order.
     */
    public ChangeToPrepaidResponse changeToPrepaid(ChangeToPrepaidRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(),
                checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.toPrepay.name(), null);
        fillPayload(internalRequest, request);
        return this.invokeHttpClient(internalRequest, ChangeToPrepaidResponse.class);
    }

    /**
     * Starting the instance owned by the user.
     *
     * You can start the instance only when the instance is Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     */
    public void startInstance(String instanceId) {
        this.startInstance(new StartInstanceRequest().withInstanceId(instanceId));
    }

    /**
     * Starting the instance owned by the user.
     *
     * You can start the instance only when the instance is Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for starting the instance.
     */
    public void startInstance(StartInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.start.name(), null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Stopping the instance owned by the user.
     *
     * You can stop the instance only when the instance is Running,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     */
    public void stopInstance(String instanceId) {
        this.stopInstance(new StopInstanceRequest()
                .withInstanceId(instanceId).withForceStop(false)
                .withStopWithNoCharge(false));
    }

    /**
     * Stopping the instance owned by the user.
     *
     * You can stop the instance only when the instance is Running,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     * @param forceStop The optional parameter to stop the instance forcibly.If <code>true</code>,
     *                  it will stop the instance just like power off immediately
     *                  and it may result in losing important data which have not been written to disk.
     */
    public void stopInstance(String instanceId, boolean forceStop) {
        this.stopInstance(new StopInstanceRequest()
                .withInstanceId(instanceId).withForceStop(forceStop)
                .withStopWithNoCharge(false));
    }

    /**
     * Stopping the instance owned by the user.
     *
     * You can stop the instance only when the instance is Running,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId       The id of the instance.
     * @param forceStop        The optional parameter to stop the instance forcibly.If <code>true</code>,
     *                         it will stop the instance just like power off immediately
     *                         and it may result in losing important data which have not been written to disk.
     * @param stopWithNoCharge The optional parameter to indicate that whether the instance can be stopped
     *                         with no charge or not, default value is false.
     *                         If <code>true</>, it means the instance can be stopped with no charge.
     */
    public void stopInstance(String instanceId, boolean forceStop, boolean stopWithNoCharge) {
        this.stopInstance(new StopInstanceRequest()
                .withInstanceId(instanceId).withForceStop(forceStop)
                .withStopWithNoCharge(stopWithNoCharge));
    }

    /**
     * Stopping the instance owned by the user.
     *
     * You can stop the instance only when the instance is Running,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for stopping the instance.
     */
    public void stopInstance(StopInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.stop.name(), null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Rebooting the instance owned by the user.
     *
     * You can reboot the instance only when the instance is Running,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     */
    public void rebootInstance(String instanceId) {
        this.rebootInstance(new RebootInstanceRequest().withInstanceId(instanceId).withForceStop(false));
    }

    /**
     * Rebooting the instance owned by the user.
     *
     * You can reboot the instance only when the instance is Running,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     * @param forceStop The option param to stop the instance forcibly.If <code>true</code>,
     *                  it will stop the instance just like power off immediately
     *                  and it may result int losing important data which have not written to disk.
     */
    public void rebootInstance(String instanceId, boolean forceStop) {
        this.rebootInstance(new RebootInstanceRequest().withInstanceId(instanceId).withForceStop(forceStop));
    }

    /**
     * Rebooting the instance owned by the user.
     *
     * You can reboot the instance only when the instance is Running,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for rebooting the instance.
     */
    public void rebootInstance(RebootInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.reboot.name(), null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modifying the password of the instance.
     *
     * You can change the instance password only when the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     * @param adminPass The new password to update.
     *                  The adminPass will be encrypted in AES-128 algorithm
     *                  with the substring of the former 16 characters of user SecretKey.
     * @throws BceClientException
     */
    public void modifyInstancePassword(String instanceId, String adminPass) throws BceClientException {
        this.modifyInstancePassword(new ModifyInstancePasswordRequest()
                .withInstanceId(instanceId).withAdminPass(adminPass));
    }

    /**
     * Modifying the password of the instance.
     *
     * You can reboot the instance only when the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for modifying the instance password.
     * @throws BceClientException
     */
    public void modifyInstancePassword(ModifyInstancePasswordRequest request) throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAdminPass(), checkEmptyExceptionMessageFormat(ADMINPASS_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.changePass.name(), null);
        BceCredentials credentials = config.getCredentials();
        if (internalRequest.getCredentials() != null) {
            credentials = internalRequest.getCredentials();
        }
        try {
            request.setAdminPass(this.aes128WithFirst16Char(request.getAdminPass(), credentials.getSecretKey()));
        } catch (GeneralSecurityException e) {
            throw new BceClientException("Encryption procedure exception", e);
        }
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void modifyInstanceHostname(String instanceId, String hostname, boolean isReboot) throws BceClientException {
        this.modifyInstanceHostname(new ModifyInstanceHostnameRequest()
                .withInstanceId(instanceId).withHostname(hostname).withIsReboot(isReboot));
    }

    /**
     * Modifying the hostname to new value of the instance.
     *
     * @param request The request containing all options for modifying the instance hostname.
     */
    public void modifyInstanceHostname(ModifyInstanceHostnameRequest request) throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getHostname(), checkEmptyExceptionMessageFormat(ADMINPASS_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.changeHostname.name(), null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modifying the special attribute to new value of the instance.
     *
     * You can reboot the instance only when the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param request The request containing all options for modifying the instance attribute.
     */
    public void modifyInstanceAttributes(ModifyInstanceAttributesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.modifyAttribute.name(), null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modifying the special describe to new value of the instance.
     *
     * @param request The request containing all options for modifying the instance desc.
     */
    public void modifyInstanceDesc(ModifyInstanceDescRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDesc(), checkEmptyExceptionMessageFormat(DESC_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.modifyDesc.name(), null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Rebuilding the instance owned by the user.
     *
     * After rebuilding the instance,
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images will be saved for using in the future.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     * @param imageId The id of the image which is used to rebuild the instance.
     * @param adminPass The admin password to login the instance.
     *                  The admin password will be encrypted in AES-128 algorithm
     *                  with the substring of the former 16 characters of user SecretKey.
     *                  See more detail on
     *                  <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     *                  BCE API doc</a>
     * @throws BceClientException
     */
    public void rebuildInstance(String instanceId, String imageId, String adminPass) throws BceClientException {
        this.rebuildInstance(new RebuildInstanceRequest().withInstanceId(instanceId)
                .withImageId(imageId).withAdminPass(adminPass));
    }

    /**
     * Rebuilding the instance owned by the user.
     *
     * After rebuilding the instance,
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images will be saved for using in the future.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     * @param imageId    The id of the image which is used to rebuild the instance.
     * @param adminPass  The admin password to login the instance.
     *                   The admin password will be encrypted in AES-128 algorithm
     *                   with the substring of the former 16 characters of user SecretKey.
     *                   See more detail on
     *                   <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     *                   BCE API doc</a>
     * @param keypairId The id of the keypair.
     * @throws BceClientException
     */
    public void rebuildInstance(String instanceId, String imageId, String adminPass, String keypairId)
            throws BceClientException {
        this.rebuildInstance(new RebuildInstanceRequest().withInstanceId(instanceId)
                .withImageId(imageId).withAdminPass(adminPass).withKeypairId(keypairId));
    }

    /**
     * Rebuilding the instance owned by the user.
     *
     * After rebuilding the instance,
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images created from original instance system disk will be saved.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for rebuilding the instance.
     * @throws BceClientException
     */
    public void rebuildInstance(RebuildInstanceRequest request) throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAdminPass(), checkEmptyExceptionMessageFormat(ADMINPASS_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.rebuild.name(), null);

        BceCredentials credentials = config.getCredentials();
        if (internalRequest.getCredentials() != null) {
            credentials = internalRequest.getCredentials();
        }
        try {
            request.setAdminPass(this.aes128WithFirst16Char(request.getAdminPass(), credentials.getSecretKey()));
        } catch (GeneralSecurityException e) {
            throw new BceClientException("Encryption procedure exception", e);
        }
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Rebuilding the instance owned by the user by batch.
     * <p>
     * After rebuilding the instance,
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images will be saved for using in the future.
     * <p>
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceIds The ids of the instance.
     * @param imageId     The id of the image which is used to rebuild the instance.
     * @param adminPass   The admin password to login the instance.
     *                    The admin password will be encrypted in AES-128 algorithm
     *                    with the substring of the former 16 characters of user SecretKey.
     *                    See more detail on
     *                    <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     *                    BCE API doc</a>
     * @throws BceClientException
     */
    public void rebuildBatchInstance(List<String> instanceIds, String imageId, String adminPass)
            throws BceClientException {
        this.rebuildBatchInstance(new RebuildBatchInstanceRequest().withInstanceIds(instanceIds)
                .withImageId(imageId).withAdminPass(adminPass));
    }

    /**
     * Rebuilding the instance owned by the user by batch.
     * <p>
     * After rebuilding the instance,
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images will be saved for using in the future.
     * <p>
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceIds The ids of the instance.
     * @param imageId     The id of the image which is used to rebuild the instance.
     * @param adminPass   The admin password to login the instance.
     *                    The admin password will be encrypted in AES-128 algorithm
     *                    with the substring of the former 16 characters of user SecretKey.
     *                    See more detail on
     *                    <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     *                    BCE API doc</a>
     * @param keypairId The id of the keypair.
     * @throws BceClientException
     */
    public void rebuildBatchInstance(List<String> instanceIds, String imageId, String adminPass, String keypairId)
            throws BceClientException {
        this.rebuildBatchInstance(new RebuildBatchInstanceRequest().withInstanceIds(instanceIds)
                .withImageId(imageId).withAdminPass(adminPass).withKeypairId(keypairId));
    }

    /**
     * Rebuilding the instance owned by the user.
     * <p>
     * After rebuilding the instance,
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images created from original instance system disk will be saved.
     * <p>
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for rebuilding the instance.
     * @throws BceClientException
     */
    public void rebuildBatchInstance(RebuildBatchInstanceRequest request) throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (request.getInstanceIds().size() == 0) {
            throw new IllegalArgumentException("request instanceIds should not be empty.");
        }
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAdminPass(), checkEmptyExceptionMessageFormat(ADMINPASS_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, REBUILD);

        BceCredentials credentials = config.getCredentials();
        if (internalRequest.getCredentials() != null) {
            credentials = internalRequest.getCredentials();
        }
        try {
            request.setAdminPass(this.aes128WithFirst16Char(request.getAdminPass(), credentials.getSecretKey()));
        } catch (GeneralSecurityException e) {
            throw new BceClientException("Encryption procedure exception", e);
        }
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Releasing the instance owned by the user.
     *
     * Only the Postpaid instance or Prepaid which is expired can be released.
     *
     * After releasing the instance,
     * all of the data will be deleted.
     * all of volumes attached will be auto detached, but the volume snapshots will be saved.
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images created from original instance system disk will be reserved.
     *
     * @param instanceId The id of the instance.
     */
    public void releaseInstance(String instanceId) {
        this.releaseInstance(new ReleaseInstanceRequest().withInstanceId(instanceId));
    }

    /**
     * Releasing the instance owned by the user.
     *
     * Only the Postpaid instance or Prepaid which is expired can be released.
     * After releasing the instance,
     * all of the data will be deleted.
     * all of volumes attached will be detached,but the volume snapshots will be saved.
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images created from original instance system disk will be reserved.
     *
     * @param request The request containing all options for releasing the instance.
     */
    public void releaseInstance(ReleaseInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, INSTANCE_PREFIX, request.getInstanceId());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Releasing the instance owned by the user by POST.
     * <p>
     * Only the Postpaid instance or Prepaid which is expired can be released.
     * <p>
     * After releasing the instance,
     * all of the data will be deleted.
     * all of volumes attached will be auto detached, but the volume snapshots will be saved.
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images created from original instance system disk will be reserved.
     *
     * @param instanceId            The id of the instance.
     * @param relatedReleaseFlag    Whether to release the EIP and the data disk attached to the instance
     *                              at the current time in the same time. If<code>true</code>, it means release,
     *                              and the parameters deleteCdsSnapshotFlag works. If<code>false</code>, it means
     *                              not to release, and the parameters deleteCdsSnapshotFlag does not works.
     * @param deleteCdsSnapshotFlag Whether to delete the CDS Snapshot. If<code>true</code>, it means delete.
     */
    public void releaseInstanceByPost(String instanceId, boolean relatedReleaseFlag, boolean deleteCdsSnapshotFlag) {
        this.releaseInstanceByPost(
                new ReleaseInstanceByPostRequest()
                        .withInstanceId(instanceId)
                        .withRelatedReleaseFlag(relatedReleaseFlag)
                        .withDeleteCdsSnapshotFlag(deleteCdsSnapshotFlag));
    }

    /**
     * Releasing the instance owned by the user.
     * <p>
     * Only the Postpaid instance or Prepaid which is expired can be released.
     * After releasing the instance,
     * all of the data will be deleted.
     * all of volumes attached will be detached,but the volume snapshots will be saved.
     * all of snapshots created from original instance system disk will be deleted,
     * all of customized images created from original instance system disk will be reserved.
     *
     * @param request The request containing all options for releasing the instance.
     */
    public void releaseInstanceByPost(ReleaseInstanceByPostRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, INSTANCE_PREFIX, request.getInstanceId());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Changing the subnetId of the instance owned by the user.
     *
     * @param instanceId The id of instance.
     * @param subnetId   The id of subnet after changed.
     * @param reboot     Whether to reboot automatically or not. If <code>true</code>, it means reboot automatically.
     */
    public void updateInstanceSubnet(String instanceId, String subnetId, boolean reboot) {
        this.updateInstanceSubnet(
                new ChangeInstanceSubnetRequest()
                        .withInstanceId(instanceId)
                        .withSubnetId(subnetId)
                        .withReboot(reboot));
    }

    /**
     * Changing the subnetId of the instance owned by the user.
     *
     * @param request The request containing all options for changing the subnetId of the instance.
     */
    public void updateInstanceSubnet(ChangeInstanceSubnetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getSubnetId(), checkEmptyExceptionMessageFormat(SUBNETID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, SUBNET_PREFIX, CHANGE_SUBNET);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Resizing the instance owned by the user.
     *
     * The Prepaid instance can not be downgrade.
     * Only the Running/Stopped instance can be resized,otherwise,it's will get <code>409</code> errorCode.
     * After resizing the instance,it will be reboot once.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for resizing the instance.
     */
    public void resizeInstance(ResizeInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        if (request.getCpuCount() <= 0) {
            throw new IllegalArgumentException("request cpuCount should be positive.");
        }
        if (request.getMemoryCapacityInGB() <= 0) {
            throw new IllegalArgumentException("request memoryCapacityInGB should be positive.");
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.resize.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Through spec Resizing the instance through the spec owned by the user.
     *
     * The Prepaid instance can not be downgrade.
     * Only the Running/Stopped instance can be resized,otherwise,it's will get <code>409</code> errorCode.
     * After resizing the instance,it will be reboot once.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for resizing the instance.
     */
    public void resizeInstanceBySpec(ResizeInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_BY_SPEC_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.resize.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Binding the instance to specified securitygroup.
     *
     * @param instanceId  The id of the instance.
     * @param securityGroupId The id of the securitygroup.
     */
    public void bindInstanceToSecurityGroup(String instanceId, String securityGroupId) {
        this.bindInstanceToSecurityGroup(new BindSecurityGroupRequest()
                .withInstanceId(instanceId).withSecurityGroupId(securityGroupId));
    }

    /**
     * Binding the instance to specified securitygroup.
     *
     * @param request The request containing all options for binding the instance to specified securitygroup.
     */
    public void bindInstanceToSecurityGroup(BindSecurityGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getSecurityGroupId(),
                checkEmptyExceptionMessageFormat(SECURITYGROUPID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.bind.name(), null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Unbinding the instance from securitygroup.
     *
     * @param instanceId The id of the instance.
     * @param securityGroupId The id of the securitygroup.
     */
    public void unbindInstanceFromSecurityGroup(String instanceId, String securityGroupId) {
        this.unbindInstanceFromSecurityGroup(new UnbindSecurityGroupRequest()
                .withInstanceId(instanceId).withSecurityGroupId(securityGroupId));
    }


    /**
     * Unbinding the instance from securitygroup.
     *
     * @param request The request containing all options for unbinding the instance from securitygroup.
     */
    public void unbindInstanceFromSecurityGroup(UnbindSecurityGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getSecurityGroupId(),
                checkEmptyExceptionMessageFormat(SECURITYGROUPID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.unbind.name(), null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Binding the instance to specified list of tags.
     *
     * @param instanceId The id of the instance.
     * @param changeTags The list of tags.
     */
    public void bindInstanceToTags(String instanceId, List<TagModel> changeTags) {
        this.bindInstanceToTags(new BindTagsRequest()
                .withInstanceId(instanceId).withChangeTags(changeTags));
    }

    /**
     * Binding the instance to specified list of tags.
     *
     * @param request The request containing all options for binding the instance to specified list of tags.
     */
    public void bindInstanceToTags(BindTagsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        if (null != request.getChangeTags() && !request.getChangeTags().isEmpty()) {
            for (TagModel tag : request.getChangeTags()) {
                checkStringNotEmpty(tag.getTagKey(), checkEmptyExceptionMessageFormat(TAGKEY_MESSAGE_KEY));
            }
        } else {
            throw new IllegalArgumentException(CHANGETAGS_NULL_ERROR_MESSAGE);
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                INSTANCE_PREFIX, request.getInstanceId(), TAG);
        internalRequest.addParameter("bind", null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Unbinding the instance to specified list of tags.
     *
     * @param instanceId The id of the instance.
     * @param changeTags The list of tags to be unbound.
     */
    public void unbindInstanceFromTags(String instanceId, List<TagModel> changeTags) {
        this.unbindInstanceFromTags(new UnbindTagsRequest()
                .withInstanceId(instanceId).withChangeTags(changeTags));
    }

    /**
     * Unbinding the instance to specified list of tags.
     *
     * @param request The request containing all options for unbinding the instance to specified list of tags.
     */
    public void unbindInstanceFromTags(UnbindTagsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        if (null != request.getChangeTags() && !request.getChangeTags().isEmpty()) {
            for (TagModel tag : request.getChangeTags()) {
                checkStringNotEmpty(tag.getTagKey(), checkEmptyExceptionMessageFormat(TAGKEY_MESSAGE_KEY));
            }
        } else {
            throw new IllegalArgumentException(CHANGETAGS_NULL_ERROR_MESSAGE);
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                INSTANCE_PREFIX, request.getInstanceId(), TAG);
        internalRequest.addParameter("unbind", null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Getting the vnc url to access the instance.
     *
     * The vnc url can be used once.
     *
     * @param instanceId The id of the instance.
     */
    public GetInstanceVncResponse getInstanceVnc(String instanceId) {
        return this.getInstanceVnc(new GetInstanceVncRequest().withInstanceId(instanceId));
    }

    /**
     * Getting the vnc url to access the instance.
     *
     * The vnc url can be used once.
     *
     * @param request The request containing all options for getting the vnc url.
     */
    public GetInstanceVncResponse getInstanceVnc(GetInstanceVncRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, INSTANCE_PREFIX, request.getInstanceId(), "vnc");
        return this.invokeHttpClient(internalRequest, GetInstanceVncResponse.class);
    }


    /**
     * PurchaseReserved the instance with fixed duration.
     *
     * You can not purchaseReserved the instance which is resizing.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     * @param reservationLength The fixed duration to purchaseReserved,available is [1,2,3,4,5,6,7,8,9,12,24,36]
     * @param reservationTimeUnit The timeUnit to renew the instance,only support "month" now.
     */
    public void purchaseReservedInstance(String instanceId, int reservationLength, String reservationTimeUnit) {
        this.purchaseReservedInstance(new PurchaseReservedInstanceRequeset()
                .withInstanceId(instanceId)
                .withBilling(new Billing().withReservation(new Reservation()
                        .withReservationLength(reservationLength)
                        .withReservationTimeUnit(reservationTimeUnit))));
    }

    /**
     * PurchaseReserved the instance with fixed duration.
     *
     * You can not purchaseReserved the instance which is resizing.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param instanceId The id of the instance.
     * @param reservationLength The fixed duration to purchaseReserved,available is [1,2,3,4,5,6,7,8,9,12,24,36]
     * @param reservationTimeUnit The timeUnit to renew the instance,only support "month" now.
     * @param relatedRenewFlagType The flag of instance related renew.
     *                             see all of supported flag type in
     *                             {@link com.baidubce.services.bcc.model.instance.RelatedRenewFlagType}
     */
    public void purchaseReservedInstance(String instanceId, int reservationLength, String reservationTimeUnit,
                                         String relatedRenewFlagType) {
        this.purchaseReservedInstance(new PurchaseReservedInstanceRequeset()
                .withInstanceId(instanceId)
                .withBilling(new Billing().withReservation(new Reservation()
                        .withReservationLength(reservationLength)
                        .withReservationTimeUnit(reservationTimeUnit)))
                .withRelatedRenewFlag(relatedRenewFlagType));
    }

    /**
     * Renewing the instance with fixed duration.
     *
     * You can not renew the instance which is resizing.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for renewing the instance with fixed duration.
     */
    public void purchaseReservedInstance(PurchaseReservedInstanceRequeset request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBillingWithReservation());
        }
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.purchaseReserved.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * The interface will be deprecated in the future,
     * we suggest to use triad (instanceType，cpuCount，memoryCapacityInGB) to specified the instance configuration.
     *
     * Listing all of specification for instance resource to buy.
     *
     * See more detail on
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.AE.9E.E4.BE.8B.E5.A5.97.E9.A4.90.E8.A7.84.E6.A0.BC">
     * BCE API doc</a>
     *
     * @return List of specification for instance resource to buy.
     */
    @Deprecated
    public ListInstanceSpecsResponse listInstanceSpecs() {
        return this.listInstanceSpecs(new ListInstanceSpecsRequest());
    }

    /**
     * The interface will be deprecated in the future,
     * we suggest to use triad (instanceType，cpuCount，memoryCapacityInGB) to specified the instance configuration.
     *
     * Listing all of specification for instance resource to buy.
     *
     * See more detail on
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.AE.9E.E4.BE.8B.E5.A5.97.E9.A4.90.E8.A7.84.E6.A0.BC">
     * BCE API doc</a>
     *
     * @param request The request containing all options for Listing all of specification for instance resource.
     * @return List of specification for instance resource to buy.
     */
    @Deprecated
    public ListInstanceSpecsResponse listInstanceSpecs(ListInstanceSpecsRequest request) {
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, INSTANCE_PREFIX, "spec");
        return this.invokeHttpClient(internalRequest, ListInstanceSpecsResponse.class);
    }

    /**
     * Create a volume with the specified options.
     *
     * You can use this method to create a new empty volume by specified options
     * or you  can create a new volume from customized volume snapshot but not system disk snapshot.
     * By using the cdsSizeInGB parameter you can create a newly empty volume.
     * By using snapshotId parameter to create a volume form specific snapshot.
     *
     * @param request The request containing all options for creating a volume.
     * @return The response with list of id of volumes newly created.
     */
    public CreateVolumeResponse createVolume(CreateVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VOLUME_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateVolumeResponse.class);
    }

    /**
     * Sync create volume with the specified options.
     * syncCreateVolume does not support snapshotId and Prepay
     *
     * You can use this method to create a new empty volume by specified options
     *
     * @param request The request containing all options for creating a volume.
     * @return The response with list of id of volumes newly created.
     */
    public CreateVolumeResponse syncCreateVolume(CreateVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (StringUtils.isNotEmpty(request.getSnapshotId())) {
            throw new IllegalArgumentException("sync create does not support use snapshot!");
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        if (!"Postpaid".equalsIgnoreCase(request.getBilling().getPaymentTiming())) {
            throw new IllegalArgumentException("sync create does only support Postpaid!");
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VOLUME_PREFIX, SYNC_CREATE);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateVolumeResponse.class);
    }

    /**
     * Listing volumes owned by the authenticated user.
     *
     * @return The response containing a list of volume owned by the authenticated user.
     */
    public ListVolumesResponse listVolumes() {
        return listVolumes(new ListVolumesRequest());
    }

    /**
     * Listing volumes owned by the authenticated user.
     *
     * @param request The request containing all options for listing volumes owned by the authenticated user.
     * @return The response containing a list of volume owned by the authenticated user.
     */
    public ListVolumesResponse listVolumes(ListVolumesRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VOLUME_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getInstanceId())) {
            internalRequest.addParameter(INSTANCEID_MESSAGE_KEY, request.getInstanceId());
        }
        if (!Strings.isNullOrEmpty(request.getZoneName())) {
            internalRequest.addParameter(ZONE_NAME, request.getZoneName());
        }
        return invokeHttpClient(internalRequest, ListVolumesResponse.class);
    }

    /**
     * Get the detail information of specified volume.
     *
     * @param volumeId The id of the volume.
     * @return The response containing the detail information of specified volume.
     */
    public GetVolumeResponse getVolume(String volumeId) {
        return getVolume(new GetVolumeRequest().withVolumeId(volumeId));
    }

    /**
     * Get the detail information of specified volume.
     *
     * @param request The request containing all options for getting the detail information of specified volume.
     * @return The response containing the detail information of specified volume.
     */
    public GetVolumeResponse getVolume(GetVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, VOLUME_PREFIX, request.getVolumeId());
        return invokeHttpClient(internalRequest, GetVolumeResponse.class);
    }

    /**
     * Attaching the specified volume to a specified instance.
     *
     * You can attach the specified volume to a specified instance only
     * when the volume is Available and the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param volumeId   The id of the volume which will be attached to specified instance.
     * @param instanceId The id of the instance which will be attached with a volume.
     * @return The response containing the volume-instance attach relation.
     */
    public AttachVolumeResponse attachVolume(String volumeId, String instanceId) {
        return attachVolume(new AttachVolumeRequest().withVolumeId(volumeId).withInstanceId(instanceId));
    }

    /**
     * Attaching the specified volume to a specified instance.
     *
     * You can attach the specified volume to a specified instance only
     * when the volume is Available and the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param request The request containing all options for attaching the specified volume to a specified instance.
     * @return The response containing the volume-instance attach relation.
     */
    public AttachVolumeResponse attachVolume(AttachVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_PREFIX, request.getVolumeId());
        internalRequest.addParameter(VolumeAction.attach.name(), null);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AttachVolumeResponse.class);
    }

    /**
     * Detaching the specified volume from a specified instance.
     *
     * You can detach the specified volume from a specified instance only
     * when the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param volumeId   The id of the volume which has been attached to specified instance.
     * @param instanceId The id of the instance which will be detached a volume.
     */
    public void detachVolume(String volumeId, String instanceId) {
        this.detachVolume(new DetachVolumeRequest().withVolumeId(volumeId).withInstanceId(instanceId));
    }

    /**
     * Detaching the specified volume from a specified instance.
     *
     * You can detach the specified volume from a specified instance only
     * when the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param request The request containing all options for detaching the specified volume from a specified instance.
     */
    public void detachVolume(DetachVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_PREFIX, request.getVolumeId());
        internalRequest.addParameter(VolumeAction.detach.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Releasing the specified volume owned by the user.
     *
     * You can release the specified volume only
     * when the instance is among state of  Available/Expired/Error,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param volumeId The id of the volume which will be released.
     */
    public void releaseVolume(String volumeId) {
        this.releaseVolume(new ReleaseVolumeRequest().withVolumeId(volumeId));
    }

    /**
     * Releasing the specified volume owned by the user.
     *
     * You can release the specified volume only
     * when the volume is Available/Expired/Error,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param request The request containing all options for releasing the specified volume.
     */
    public void releaseVolume(ReleaseVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));

        if (StringUtils.isEmpty(request.getAutoSnapshot())
                && StringUtils.isEmpty(request.getManualSnapshot())) {
            InternalRequest internalRequest =
                    this.createRequest(request, HttpMethodName.DELETE, VOLUME_PREFIX, request.getVolumeId());
            invokeHttpClient(internalRequest, AbstractBceResponse.class);
        } else {
            InternalRequest internalRequest =
                    this.createRequest(request, HttpMethodName.POST, VOLUME_PREFIX, request.getVolumeId());
            fillPayload(internalRequest, request);
            invokeHttpClient(internalRequest, AbstractBceResponse.class);
        }
    }

    /**
     * Change the volume's billing method.
     * billingMethod can be set to 'prepay' or 'postpay'. when 'prepay' is used, reservationLength must be
     * set to a positive integer which denotes how many month to buy this volume.
     *
     * @param request The request containing all options for modifying the billing method of the specified volume.
     */
    public void modifyVolumeChargeType(ModifyVolumeChargeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));

        ModifyVolumeChargeRequest.ModifyVolumeChargeModel requestModel = request.toModifyVolumeChargeModel();
        InternalRequest internalRequest = this.createRequest(requestModel, HttpMethodName.PUT,
                VOLUME_PREFIX, request.getVolumeId());
        internalRequest.addParameter(VolumeAction.modifyChargeType.name(), null);
        fillPayload(internalRequest, requestModel);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Change the volume's billing method.
     * billingMethod can be set to 'prepay' or 'postpay'. when 'prepay' is used, reservationLength must be
     * set to a positive integer which denotes how many month to buy this volume.
     *
     * @param request
     */
    public void modifyVolumeChargeType(ModifyVolumeChargeTypeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                VOLUME_PREFIX, request.getVolumeId());
        internalRequest.addParameter(VolumeAction.modifyChargeType.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Resizing the specified volume with newly size.
     *
     * You can resize the specified volume only when the volume is Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * The prepaid volume can not be downgrade.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getVolume(String)}
     *
     * @param volumeId       The id of volume which you want to resize.
     * @param newCdsSizeInGB The new volume size you want to resize in GB.
     */
    public void resizeVolume(String volumeId, int newCdsSizeInGB) {
        this.resizeVolume(new ResizeVolumeRequest()
                .withVolumeId(volumeId).withNewCdsSizeInGB(newCdsSizeInGB));
    }

    /**
     * Resizing the specified volume with newly size.
     *
     * You can resize the specified volume only when the volume is Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * The prepaid volume can not be downgrade.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getVolume(String)}
     *
     * @param request The request containing all options for resize the specified volume.
     */
    public void resizeVolume(ResizeVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        checkState(request.getNewCdsSizeInGB() > 0, "request newCdsSizeInGB should greater than 0");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_PREFIX, request.getVolumeId());
        internalRequest.addParameter(VolumeAction.resize.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Rollback the volume with the specified volume snapshot.
     *
     *
     * You can rollback the specified volume only when the volume is Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * The snapshot used to rollback must be created by the volume,
     * otherwise,it's will get <code>404</code> errorCode.
     *
     * If rolling back the system volume,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.After rolling back the
     * volume,all the system disk data will erase.
     *
     * @param volumeId   The id of volume which will be rollback.
     * @param snapshotId The id of snapshot which will be used to rollback the volume.
     */
    public void rollbackVolume(String volumeId, String snapshotId) {
        this.rollbackVolume(new RollbackVolumeRequest()
                .withVolumeId(volumeId).withSnapshotId(snapshotId));
    }

    /**
     * Rollback the volume with the specified volume snapshot.
     *
     * You can rollback the specified volume only when the volume is Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * The snapshot used to rollback must be created by the volume,
     * otherwise,it's will get <code>404</code> errorCode.
     *
     * If rolling back the system volume,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.After rolling back the
     * volume,all the system disk data will erase.
     *
     * @param request The request containing all options for rolling back the specified volume.
     */
    public void rollbackVolume(RollbackVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getSnapshotId(), checkEmptyExceptionMessageFormat(SNAPSHOTID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_PREFIX, request.getVolumeId());
        internalRequest.addParameter(VolumeAction.rollback.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * PurchaseReserved the instance with fixed duration.
     *
     * You can not purchaseReserved the instance which is resizing.
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getVolume(String)}
     *
     * @param volumeId            The id of volume which will be renew.
     * @param reservationLength   The fixed duration to renew,available is [1,2,3,4,5,6,7,8,9,12,24,36]
     * @param reservationTimeUnit The timeUnit to renew the instance,only support "month" now.
     */
    public void purchaseReservedVolume(String volumeId, int reservationLength, String reservationTimeUnit) {
        this.purchaseReservedVolume(new PurchaseReservedVolumeRequest()
                .withVolumeId(volumeId)
                .withBilling(new Billing().withReservation(new Reservation()
                        .withReservationLength(reservationLength)
                        .withReservationTimeUnit(reservationTimeUnit))));
    }

    /**
     * PurchaseReserved the instance with fixed duration.
     *
     * You can not purchaseReserved the instance which is resizing.
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getVolume(String)}
     *
     * @param request The request containing all options for renew the specified volume.
     */
    public void purchaseReservedVolume(PurchaseReservedVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBillingWithReservation());
        }
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_PREFIX, request.getVolumeId());
        internalRequest.addParameter(VolumeAction.purchaseReserved.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void autoRenewVolume(String volumeId, String renewTimeUnit, int renewTime) {
        autoRenewVolume(new AutoRenewVolumeRequest().withVolumeId(volumeId).
                withRenewTimeUnit(renewTimeUnit).withRenewTime(renewTime));
    }

    /**
     * Enable auto renewal the specified volume
     *
     * @param request The request containing all options for renew the specified volume.
     */
    public void autoRenewVolume(AutoRenewVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));

        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        if (request.getRenewTime() <= 0) {
            throw new IllegalArgumentException("request renewTime should be a positive integer");
        }

        if (!request.getRenewTimeUnit().equalsIgnoreCase("month") &&
                !request.getRenewTimeUnit().equalsIgnoreCase("year")) {
            throw new IllegalArgumentException("request renewTimeUnit only support \"month\" and \"year\"");
        }

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VOLUME_PREFIX, VolumeAction.autoRenew.name());

        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Disable auto renewal the volume
     *
     * @param request The request containing all options for disable auto renew the specified volume.
     */
    public void cancelAutoRenewVolume(CancelAutoRenewVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));

        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VOLUME_PREFIX, VolumeAction.cancelAutoRenew.name());

        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Rename the volume
     *
     * @param request The request containing all options for rename the specified volume.
     */
    public void renameVolume(RenameVolumeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_PREFIX, request.getVolumeId());

        internalRequest.addParameter(VolumeAction.rename.name(), null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * change cds volume's attribute。
     * this interface is used to change cds's attribute, currently Name/Desc attribute is supported.
     *
     * @param modifyCdsAttrRequest
     */
    public void modifyCdsAttribute(ModifyCdsAttrRequest modifyCdsAttrRequest) {
        checkNotNull(modifyCdsAttrRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(modifyCdsAttrRequest.getCdsId(),
                checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(modifyCdsAttrRequest, HttpMethodName.PUT, VOLUME_PREFIX,
                        modifyCdsAttrRequest.getCdsId());

        internalRequest.addParameter(VolumeAction.modify.name(), null);
        fillPayload(internalRequest, modifyCdsAttrRequest);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Creating a customized image from the instance..
     *
     * While creating an image from an instance,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getImage(GetImageRequest)}
     *
     * @param imageName  The name of image that will be created.
     * @param instanceId The id of instance which will be used to create image.
     * @return The response with id of image newly created.
     */
    public CreateImageResponse createImageFromInstance(String imageName, String instanceId) {
        return createImage(new CreateImageRequest()
                .withImageName(imageName).withInstanceId(instanceId).withRelateCds(false));
    }

    /**
     * Creating a customized image from the instance..
     *
     * While creating an image from an instance,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getImage(GetImageRequest)}
     *
     * @param imageName  The name of image that will be created.
     * @param instanceId The id of instance which will be used to create image.
     * @param relateCds Whether the image is related with cds.
     *                  If <code>true</code>, it means the image is related with cds.
     * @return The response with id of image newly created.
     */
    public CreateImageResponse createImageFromInstanceWithRelateCds(String imageName,
                                                                    String instanceId, boolean relateCds) {
        return createImage(new CreateImageRequest()
                .withImageName(imageName).withInstanceId(instanceId).withRelateCds(relateCds));
    }

    /**
     * Creating a customized image from specified snapshot.
     *
     * You can create the image only from system snapshot.
     * While creating an image from a system snapshot,the snapshot must be Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getImage(GetImageRequest)}
     *
     * @param imageName  The name of image that will be created.
     * @param snapshotId The id of snapshot which will be used to create image.
     * @return The response with id of image newly created.
     */
    public CreateImageResponse createImageFromSnapshot(String imageName, String snapshotId) {
        return createImage(new CreateImageRequest()
                .withImageName(imageName).withSnapshotId(snapshotId));
    }

    /**
     * Creating a customized image which can be used for creating instance in the future.
     *
     * You can create an image from an instance or you can create from an snapshot.
     * The parameters of instanceId and snapshotId can no be null simultaneously.
     * when both instanceId and snapshotId are assigned,only instanceId will be used.
     *
     * While creating an image from an instance,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * You can create the image only  from system snapshot.
     * While creating an image from a system snapshot,the snapshot must be Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getImage(GetImageRequest)}
     *
     * @param request The request containing all options for creating a new image.
     * @return The response with id of image newly created.
     */
    public CreateImageResponse createImage(CreateImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getImageName(), checkEmptyExceptionMessageFormat(IMAGENAME_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getInstanceId()) && Strings.isNullOrEmpty(request.getSnapshotId())) {
            throw new IllegalArgumentException("request instanceId or snapshotId should not be empty .");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, IMAGE_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateImageResponse.class);
    }

    /**
     * Listing images owned by the authenticated user.
     *
     * @return The response with list of images.
     */
    public ListImagesResponse listImages() {
        return this.listImages(new ListImagesRequest());
    }

    /**
     * Listing images owned by the authenticated user.
     *
     * @param request The request containing all options for listing images owned by user.
     * @return The response with list of images.
     */
    public ListImagesResponse listImages(ListImagesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, IMAGE_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getImageType())) {
            internalRequest.addParameter(IMAGE_TYPE, request.getImageType());
        }
        return invokeHttpClient(internalRequest, ListImagesResponse.class);
    }

    /**
     * Get the detail information of specified image.
     *
     * @param imageId The id of image.
     * @return The response with the image detail information.
     */
    public GetImageResponse getImage(String imageId) {
        return getImage(new GetImageRequest().withImageId(imageId));
    }

    /**
     * Get the detail information of specified image.
     *
     * @param request The request containing all options for getting the detail information of specified image.
     * @return The response with the image detail information.
     */
    public GetImageResponse getImage(GetImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, IMAGE_PREFIX, request.getImageId());
        return invokeHttpClient(internalRequest, GetImageResponse.class);
    }

    /**
     * Deleting the specified image.
     *
     * Only the customized image can be deleted,
     * otherwise,it's will get <code>403</code> errorCode.
     *
     * @param imageId The id of image.
     */
    public void deleteImage(String imageId) {
        deleteImage(new DeleteImageRequest().withImageId(imageId));
    }

    /**
     * Deleting the specified image.
     *
     * Only the customized image can be deleted,
     * otherwise,it's will get <code>403</code> errorCode.
     *
     * @param request The request containing all options for deleting the specified image.
     */
    public void deleteImage(DeleteImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, IMAGE_PREFIX, request.getImageId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Sharing the specified image.
     *
     * Only the customized image can be shared,
     * otherwise,it's will get <code>403</code> errorCode.
     *
     * @param request The request containing all options for sharing the specified image.
     */
    public void shareImage(ShareImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getAccount())
                && Strings.isNullOrEmpty(request.getAccountId())) {
            throw new IllegalArgumentException("request account or accountId should not be empty .");
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, IMAGE_PREFIX, request.getImageId());
        internalRequest.addParameter(ImageAction.share.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * UnSharing the specified image.
     *
     * Only the customized and shared image can be unshared,
     * otherwise,it's will get <code>403</code> errorCode.
     *
     * @param request The request containing all options for unsharing the specified image.
     */
    public void unShareImage(UnShareImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getAccount())
                && Strings.isNullOrEmpty(request.getAccountId())) {
            throw new IllegalArgumentException("request account or accountId should not be empty .");
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, IMAGE_PREFIX, request.getImageId());
        internalRequest.addParameter(ImageAction.unshare.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Listing sharedUsers by the authenticated imageId.
     *
     * @return The response with list of sharedUsers.
     */
    public ListSharedUserResponse listSharedUser(String imageId) {
        return this.listSharedUser(new ListSharedUserRequest().withImageId(imageId));
    }

    /**
     * Listing sharedUsers by the authenticated imageId.
     *
     * @param request The request containing all options for Listing sharedUsers.
     * @return The response with list of sharedUsers.
     */
    public ListSharedUserResponse listSharedUser(ListSharedUserRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, IMAGE_PREFIX, request.getImageId(), SHARED_USER);
        return this.invokeHttpClient(internalRequest, ListSharedUserResponse.class);
    }

    /**
     * Remote copy image.
     *
     * @param request The request containing all options for remote copy image.
     */
    public void remoteCopyImage(RemoteCopyImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(IMAGENAME_MESSAGE_KEY));
        if (request.getDestRegion() == null || request.getDestRegion().size() == 0) {
            throw new IllegalArgumentException("request destRegion should not be empty .");
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, IMAGE_PREFIX, request.getImageId());
        internalRequest.addParameter(ImageAction.remoteCopy.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Cancel remote copy image.
     *
     * @param imageId The id of image.
     */
    public void cancelRemoteCopyImage(String imageId) {
        this.cancelRemoteCopyImage(new CancelRemoteCopyImageRequest().withImageId(imageId));
    }

    /**
     * Cancel remote copy image.
     *
     * @param request The request containing all options for cancel remote copy image.
     */
    public void cancelRemoteCopyImage(CancelRemoteCopyImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, IMAGE_PREFIX, request.getImageId());
        internalRequest.addParameter(ImageAction.cancelRemoteCopy.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * list os by instanceIds.
     *
     * @param request The request containing instanceIds which to query.
     * @return response ListOsResponse
     */
    public ListOsResponse listOs(ListOsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (request.getInstanceIds().size() == 0) {
            throw new IllegalArgumentException("request instanceIds should not be empty.");
        }
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                IMAGE_PREFIX, OS_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ListOsResponse.class);
    }

    /**
     * Creating snapshot from specified volume.
     *
     * You can create snapshot from system volume and CDS volume.
     * While creating snapshot from system volume,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     * While creating snapshot from CDS volume,,the volume must be InUse or Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getSnapshot(GetSnapshotRequest)}
     *
     * @param volumeId     The id of volume which will be used to create snapshot.
     * @param snapshotName The name of snapshot will be created.
     * @return The response with the id of snapshot created.
     */
    public CreateSnapshotResponse createSnapshot(String volumeId, String snapshotName) {
        return this.createSnapshot(new CreateSnapshotRequest().withVolumeId(volumeId).withSnapshotName(snapshotName));
    }


    /**
     * Creating snapshot from specified volume.
     *
     * You can create snapshot from system volume and CDS volume.
     * While creating snapshot from system volume,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     * While creating snapshot from CDS volume,,the volume must be InUs or Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getSnapshot(GetSnapshotRequest)}
     *
     * @param volumeId     The id of volume which will be used to create snapshot.
     * @param snapshotName The name of snapshot will be created.
     * @param desc         The optional parameter to describe the newly created snapshot.
     * @return The response with the id of snapshot created.
     */
    public CreateSnapshotResponse createSnapshot(String volumeId, String snapshotName, String desc) {
        return this.createSnapshot(new CreateSnapshotRequest()
                .withVolumeId(volumeId).withSnapshotName(snapshotName).withDesc(desc));
    }

    /**
     * Creating snapshot from specified volume.
     *
     * You can create snapshot from system volume and CDS volume.
     * While creating snapshot from system volume,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     * While creating snapshot from CDS volume,,the volume must be InUs or Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getSnapshot(GetSnapshotRequest)}
     *
     * @param request The request containing all options for creating a new snapshot.
     * @return The response with the id of snapshot created.
     */
    public CreateSnapshotResponse createSnapshot(CreateSnapshotRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getVolumeId(), checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getSnapshotName(), checkEmptyExceptionMessageFormat(SNAPSHOTNAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, SNAPSHOT_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateSnapshotResponse.class);
    }

    /**
     * Listing snapshots owned by the authenticated user.
     *
     * @param request The request containing all options for listing snapshot.
     * @return The response contains a list of snapshots owned by the user.
     */
    public ListSnapshotsResponse listSnapshots(ListSnapshotsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, SNAPSHOT_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }

        if (!Strings.isNullOrEmpty(request.getVolumeId())) {
            internalRequest.addParameter(VOLUMEID_MESSAGE_KEY, request.getVolumeId());
        }
        return invokeHttpClient(internalRequest, ListSnapshotsResponse.class);
    }

    /**
     * Get the detail information of specified snapshot.
     *
     * @param snapshotId The id of snapshot.
     * @return The response with the snapshot detail information.
     */
    public GetSnapshotResponse getSnapshot(String snapshotId) {
        return this.getSnapshot(new GetSnapshotRequest().withSnapshotId(snapshotId));
    }

    /**
     * Getting the detail information of specified snapshot.
     *
     * @param request The request containing all options for getting the detail information of specified snapshot.
     * @return The response with the snapshot detail information.
     */
    public GetSnapshotResponse getSnapshot(GetSnapshotRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSnapshotId(), checkEmptyExceptionMessageFormat(SNAPSHOTID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, SNAPSHOT_PREFIX, request.getSnapshotId());
        return invokeHttpClient(internalRequest, GetSnapshotResponse.class);
    }

    /**
     * Deleting the specified snapshot.
     *
     * Only when the snapshot is CreatedFailed or Available,the specified snapshot can be deleted.
     * otherwise,it's will get <code>403</code> errorCode.
     *
     * @param snapshotId The id of snapshot.
     */
    public void deleteSnapshot(String snapshotId) {
        this.deleteSnapshot(new DeleteSnapshotRequest().withSnapshotId(snapshotId));
    }

    /**
     * Deleting the specified snapshot.
     *
     * Only when the snapshot is CreatedFailed or Available,the specified snapshot can be deleted.
     * otherwise,it's will get <code>403</code> errorCode.
     *
     * @param request The request containing all options for deleting the specified snapshot.
     */
    public void deleteSnapshot(DeleteSnapshotRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSnapshotId(), checkEmptyExceptionMessageFormat(SNAPSHOTID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, SNAPSHOT_PREFIX, request.getSnapshotId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query the list of user's snapshot chain information.
     *
     * @param request The request containing all options for querying the specified snapshot chain.
     * @return Snapchain information, a collection of SnapchainModel
     */
    public ListSnapchainResponse listSnapchain(ListSnapchainRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, SNAPSHOT_PREFIX, CHAIN);

        return invokeHttpClient(internalRequest, ListSnapchainResponse.class);
    }

    /**
     * Creating autoSnapshotPolicy
     *
     * @param request contains params for creating a new asp
     * @return response contains the id of new asp
     */
    public CreateAspResponse createAsp(CreateAspRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(ASPNAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ASP);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateAspResponse.class);
    }

    /**
     * Deleting autoSnapshotPolicy
     *
     * @param request contains the id of asp for deleting
     */
    public void deleteAsp(DeleteAspRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getAspId(), checkEmptyExceptionMessageFormat(ASPID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, ASP, request.getAspId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Deleting autoSnapshotPolicy
     *
     * @param aspId for deleting
     */
    public void deleteAsp(String aspId) {
        this.deleteAsp(new DeleteAspRequest().withAspId(aspId));
    }

    /**
     * Attaching the specified asp to volumes.
     *
     * @param request The request containing all options for attaching the specified asp to specified volumes.
     */
    public void attachAsp(AttachAspRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getAspId(), checkEmptyExceptionMessageFormat(ASPID_MESSAGE_KEY));
        for (String volumeId : request.getVolumeIds()) {
            checkStringNotEmpty(volumeId, checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, ASP, request.getAspId());
        internalRequest.addParameter(AspAction.attach.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Detaching the specified asp to volumes.
     *
     * @param request The request containing all options for detaching the specified asp to specified volumes.
     */
    public void detachAsp(DetachAspRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getAspId(), checkEmptyExceptionMessageFormat(ASPID_MESSAGE_KEY));
        for (String volumeId : request.getVolumeIds()) {
            checkStringNotEmpty(volumeId, checkEmptyExceptionMessageFormat(VOLUMEID_MESSAGE_KEY));
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, ASP, request.getAspId());
        internalRequest.addParameter(AspAction.detach.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Listing asps owned by the authenticated user.
     *
     * @param request The request containing all options for listing asps owned by user.
     * @return The response with list of asps.
     */
    public ListAspsResponse listAsps(ListAspsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ASP);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getAspName())) {
            internalRequest.addParameter(ASPNAME_MESSAGE_KEY, request.getAspName());
        }
        if (!Strings.isNullOrEmpty(request.getVolumeName())) {
            internalRequest.addParameter(VOLUME_NAME, request.getVolumeName());
        }
        return invokeHttpClient(internalRequest, ListAspsResponse.class);
    }

    /**
     * Getting the detail information of specified asp.
     *
     * @param request The request containing all options for getting the detail information of specified asp.
     * @return The response with the asp detail information.
     */
    public GetAspResponse getAsp(GetAspRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getAspId(), checkEmptyExceptionMessageFormat(ASPID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, ASP, request.getAspId());
        return invokeHttpClient(internalRequest, GetAspResponse.class);
    }

    /**
     * Get the detail information of specified asp.
     *
     * @param aspId The id of asp.
     * @return The response with the asp detail information.
     */
    public GetAspResponse getAsp(String aspId) {
        return this.getAsp(new GetAspRequest().withAspId(aspId));
    }

    /**
     * Updating autoSnapshotPolicy
     *
     * @param request contains params for creating a new asp
     */
    public void updateAsp(UpdateAspRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(ASPNAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getAspId(), checkEmptyExceptionMessageFormat(ASPNAME_MESSAGE_KEY));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ASP, AspAction.update.name());

        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Listing SecurityGroup owned by the authenticated user.
     *
     * @param request The request containing all options for listing SecurityGroup owned by user.
     * @return The response with list of SecurityGroup which contains SecurityGroup rules owned by user.
     */
    public ListSecurityGroupsResponse listSecurityGroups(ListSecurityGroupsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, SECURITYGROUP_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getInstanceId())) {
            internalRequest.addParameter(INSTANCEID_MESSAGE_KEY, request.getInstanceId());
        }
        if (!Strings.isNullOrEmpty(request.getVpcId())) {
            internalRequest.addParameter(VPC_ID, request.getVpcId());
        }
        return invokeHttpClient(internalRequest, ListSecurityGroupsResponse.class);
    }

    /**
     * Creating a newly SecurityGroup with specified rules.
     *
     * @param request The request containing all options for creating a new SecurityGroup.
     * @return The response with the id of SecurityGroup that was created newly.
     */
    public CreateSecurityGroupResponse createSecurityGroup(CreateSecurityGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        if (null == request.getRules() || request.getRules().isEmpty()) {
            throw new IllegalArgumentException("request rules should not be empty");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, SECURITYGROUP_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateSecurityGroupResponse.class);
    }

    /**
     * authorizing a security group rule to a specified security group
     *
     * @param request The request containing all options for authorizing security group rule
     */
    public void authorizeSecurityGroupRule(SecurityGroupRuleOperateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupId(),
                checkEmptyExceptionMessageFormat(SECURITYGROUPID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getRule()) {
            throw new IllegalArgumentException("request rule should not be null");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, SECURITYGROUP_PREFIX,
                request.getSecurityGroupId());
        internalRequest.addParameter(SecurityGroupAction.authorizeRule.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * revoking a security group rule from the specified security group
     *
     * @param request The request containing all options for revoking security group rule
     */
    public void revokeSecurityGroupRule(SecurityGroupRuleOperateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupId(),
                checkEmptyExceptionMessageFormat(SECURITYGROUPID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getRule()) {
            throw new IllegalArgumentException("request rule should not be null");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, SECURITYGROUP_PREFIX,
                request.getSecurityGroupId());
        internalRequest.addParameter(SecurityGroupAction.revokeRule.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Deleting the specified SecurityGroup.
     *
     * @param securityGroupId The id of SecurityGroup that will be deleted.
     */
    public void deleteSecurityGroup(String securityGroupId) {
        deleteSecurityGroup(new DeleteSecurityGroupRequest().withSecurityGroupId(securityGroupId));
    }

    /**
     * Deleting the specified SecurityGroup.
     *
     * @param request The request containing all options for deleting the specified SecurityGroup owned by user.
     */
    public void deleteSecurityGroup(DeleteSecurityGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupId(),
                checkEmptyExceptionMessageFormat(SECURITYGROUPID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, SECURITYGROUP_PREFIX, request.getSecurityGroupId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * listing zones within current region
     *
     * @return The response with list of zones
     */
    public ListZonesResponse listZones() {
        return listZones(new AbstractBceRequest() {
            @Override
            public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
                return null;
            }
        });
    }

    /**
     * listing zones within current region
     *
     * @param request use withRequestCredentials
     * @return The response with list of zones
     */
    public ListZonesResponse listZones(AbstractBceRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ZONE);
        return invokeHttpClient(internalRequest, ListZonesResponse.class);
    }

    /**
     * create an keypair.
     *
     * @param request you can specify name and description in the request
     * @return KeypairModel
     */
    public KeypairModel createKeypair(KeypairCreateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, KEYPAIR);
        internalRequest.addParameter(KeypairAction.create.name(), null);
        fillPayload(internalRequest, request);
        KeypairCreateResponse response = invokeHttpClient(internalRequest, KeypairCreateResponse.class);
        return response.getKeypair();
    }

    /**
     * Import an keypair.
     * User can import an keypair which is created manually.
     * The imported keypair must support one of the following encrypt method: rsa/dsa/ssh-rsa/ssh-dss/ecdsa
     *
     * @param request KeypairImportRequest
     */
    public KeypairModel importKeypair(KeypairImportRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, KEYPAIR);
        fillPayload(internalRequest, request);
        KeypairCreateResponse response = invokeHttpClient(internalRequest, KeypairCreateResponse.class);
        return response.getKeypair();
    }

    /**
     * Attach an keypair to one or more instances.
     *
     * @param request KeypairAttachRequest
     */
    public void attachKeypair(KeypairAttachRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getKeypairId(), checkEmptyExceptionMessageFormat(KEYPAIR_ID_MESSAGE_KEY));
        if (request.getInstanceIds() == null || request.getInstanceIds().size() == 0) {
            throw new IllegalArgumentException("keypair attach: instanceIds can not be empty");
        }

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, KEYPAIR, request.getKeypairId());
        internalRequest.addParameter(KeypairAction.attach.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Detach the keypair from one or more instances.
     * if the keypair has not been attached to one instance, the operation will be ignored.
     *
     * @param request KeypairDetachRequest
     */
    public void detachKeypair(KeypairDetachRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getKeypairId(), checkEmptyExceptionMessageFormat(KEYPAIR_ID_MESSAGE_KEY));
        if (request.getInstanceIds() == null || request.getInstanceIds().size() == 0) {
            throw new IllegalArgumentException("keypair detach: instanceIds can not be empty");
        }

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, KEYPAIR, request.getKeypairId());
        internalRequest.addParameter(KeypairAction.detach.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete keypair.
     * If the keypair is attached to one or more instances, then it can not be deleted.
     *
     * @param request KeypairDeleteRequest
     */
    public void deleteKeypair(KeypairDeleteRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getKeypairId(), checkEmptyExceptionMessageFormat(KEYPAIR_ID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, KEYPAIR, request.getKeypairId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query keypair's detail information.
     *
     * @param request KeypairDetailRequest
     */
    public KeypairModel keypairDetail(KeypairDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getKeypairId(), checkEmptyExceptionMessageFormat(KEYPAIR_ID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, KEYPAIR, request.getKeypairId());
        KeypairResponse response = invokeHttpClient(internalRequest, KeypairResponse.class);
        return response.getKeypair();
    }

    /**
     * Query keypair's information from specifics range.
     *
     * @param request The request containing all options for querying the keypair list.
     */
    public KeypairListResponse listKeypair(KeypairListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, KEYPAIR);

        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }

        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }

        if (request.getName() != null && request.getName().length() > 0) {
            internalRequest.addParameter(NAME_MESSAGE_KEY, request.getName());
        }
        return invokeHttpClient(internalRequest, KeypairListResponse.class);
    }

    /**
     * Rename an keypair to instance.
     *
     * @param request The request containing all options for renaming the specified keypair.
     */
    public void renameKeypair(KeypairRenameRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getKeypairId(), checkEmptyExceptionMessageFormat(KEYPAIR_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, KEYPAIR, request.getKeypairId());
        internalRequest.addParameter(KeypairAction.rename.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * update the description of the keypair to instance.
     *
     * @param request The request containing all options for updating the description of the keypair.
     */
    public void updateKeypairDescription(KeypairUpdateDescRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getKeypairId(), checkEmptyExceptionMessageFormat(KEYPAIR_ID_MESSAGE_KEY));

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, KEYPAIR, request.getKeypairId());
        internalRequest.addParameter(KeypairAction.updateDesc.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query the user's deploy set list
     *
     * @return List of deployment set info
     */
    public ListDeploySetResponse listDeploySet() {
        return listDeploySet(new AbstractBceRequest() {
            @Override
            public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
                return null;
            }
        });
    }

    /**
     * Query the user's deploy set list
     *
     * @param request The request containing all options for querying the deploy set list.
     * @return List of deployment set info
     */
    private ListDeploySetResponse listDeploySet(AbstractBceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET,
                        INSTANCE_PREFIX, DEPLOYSET, DeploySetAction.list.name());

        return invokeHttpClient(internalRequest, ListDeploySetResponse.class);
    }

    /**
     * Create a deploy set
     *
     * @param request The request containing all options for creating a deploy set.
     * @return List of deploy set ids
     */
    public CreateDeploySetResponse createDeploySet(CreateDeploySetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST,
                        INSTANCE_PREFIX, DEPLOYSET, DeploySetAction.create.name());

        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateDeploySetResponse.class);
    }

    /**
     * Delete a deploy set with deployId
     *
     * @param request The request containing all options for deleting the specified deploy set.
     */
    public void deleteDeploySet(DeleteDeploySetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, INSTANCE_PREFIX, DEPLOYSET, request.getDeployId());

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update deploy set
     *
     * @param request The request containing all options for updating the specified deploy set.
     */
    public void updateDeploySet(UpdateDeploySetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, INSTANCE_PREFIX, DEPLOYSET, request.getDeployId());

        internalRequest.addParameter(DeploySetAction.modifyAttribute.name(), null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specific security group rule by the corresponding rule id.
     *
     * @param securityGroupRuleId the security group rule id to be deleted
     */
    public void deleteSecurityGroupRule(String securityGroupRuleId) {
        deleteSecurityGroupRule(new DeleteSecurityGroupRuleRequest().withSecurityGroupRuleId(securityGroupRuleId));
    }

    /**
     * Delete the specific security group rule via params in the deleteSecurityGroupRuleRequest
     *
     * @param request the request for deleteSecurityGroupRule
     */
    public void deleteSecurityGroupRule(DeleteSecurityGroupRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupRuleId(),
                checkEmptyExceptionMessageFormat(SECURITY_GROUP_RULE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request,
                HttpMethodName.DELETE,
                SECURITYGROUP_PREFIX,
                SECURITY_GROUP_RULE_PREFIX,
                request.getSecurityGroupRuleId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update the specific security group rule via params in the updateSecurityGroupRuleRequest
     *
     * @param request the request for updateSecurityGroupRule
     */
    public void updateSecurityGroupRule(UpdateSecurityGroupRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getSecurityGroupRuleId(),
                checkEmptyExceptionMessageFormat(SECURITY_GROUP_RULE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, SECURITYGROUP_PREFIX,
                SECURITY_GROUP_RULE_PREFIX, SECURITY_GROUP_RULE_UPDATE_PREFIX);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create a volume Cluster with the specified options.
     *
     * @param request The request containing all options for creating a volume cluster.
     * @return The response with list of id of volumes newly created.
     */
    public CreateVolumeClusterResponse createVolumeCluster(CreateVolumeClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VOLUME_CLUSTER_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        internalRequest.addParameter(UUID_FLAG, request.getUuidFlag());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateVolumeClusterResponse.class);
    }

    /**
     * Listing volumes owned by the authenticated user.
     *
     * @return The response containing a list of volume owned by the authenticated user.
     */
    public ListVolumeClustersResponse listVolumeClusters() {
        return listVolumeClusters(new ListVolumeClustersRequest());
    }

    /**
     * Listing Cluster owned by the authenticated user.
     *
     * @param request The request containing all options for listing Clusters owned by the authenticated user.
     * @return The response containing a list of Cluster owned by the authenticated user.
     */
    public ListVolumeClustersResponse listVolumeClusters(ListVolumeClustersRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VOLUME_CLUSTER_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getClusterName())) {
            internalRequest.addParameter(CLUSTER_NAME_MESSAGE_KEY, request.getClusterName());
        }
        if (!Strings.isNullOrEmpty(request.getZoneName())) {
            internalRequest.addParameter(ZONE_NAME, request.getZoneName());
        }
        return invokeHttpClient(internalRequest, ListVolumeClustersResponse.class);
    }

    /**
     * Get the detail information of specified cluster.
     *
     * @param clusterId The id of the volume.
     * @return The response containing the detail information of specified volume.
     */
    public GetVolumeClusterResponse getVolumeCluster(String clusterId) {
        GetVolumeClusterRequest request = new GetVolumeClusterRequest();
        request.setClusterId(clusterId);
        return getVolumeCluster(request);
    }

    /**
     * Get the detail information of specified cluster.
     *
     * @param request The request containing all options for getting the detail information of specified cluster.
     * @return The response containing the detail information of specified cluster.
     */
    public GetVolumeClusterResponse getVolumeCluster(GetVolumeClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, VOLUME_CLUSTER_PREFIX, request.getClusterId());
        return invokeHttpClient(internalRequest, GetVolumeClusterResponse.class);
    }

    /**
     * Resizing the specified cluster with newly size.
     *
     * @param clusterId          The id of cluster which you want to resize.
     * @param newClusterSizeInGB The new cluster size you want to resize in GB.
     */
    public void resizeVolumeCluster(String clusterId, int newClusterSizeInGB) {
        ResizeVolumeClusterRequest request = new ResizeVolumeClusterRequest();
        request.setClusterId(clusterId);
        request.setNewClusterSizeInGB(newClusterSizeInGB);
        this.resizeVolumeCluster(request);
    }

    /**
     * Resizing the specified cluster with newly size.
     *
     * @param request The request containing all options for resize the specified cluster.
     */
    public void resizeVolumeCluster(ResizeVolumeClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkState(request.getNewClusterSizeInGB() > 0,
                "request newClusterSizeInGB should greater than 0");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_CLUSTER_PREFIX, request.getClusterId());
        internalRequest.addParameter(VolumeAction.resize.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * PurchaseReserved the cluster with fixed duration.
     *
     * @param clusterId           The id of cluster which will be renew.
     * @param reservationLength   The fixed duration to renew,available is [6,12,24,36,60]
     * @param reservationTimeUnit The timeUnit to renew the instance, support "month" now.
     */
    public void purchaseReservedVolumeCluster(String clusterId, int reservationLength, String reservationTimeUnit) {
        PurchaseReservedVolumeClusterRequest request = new PurchaseReservedVolumeClusterRequest();
        request.setClusterId(clusterId);
        Billing billing = new Billing();
        Reservation reservation = new Reservation();
        reservation.setReservationLength(reservationLength);
        reservation.setReservationTimeUnit(reservationTimeUnit);
        billing.setReservation(reservation);
        request.setBilling(billing);
        this.purchaseReservedVolumeCluster(request);
    }

    /**
     * PurchaseReserved the cluster with fixed duration.
     *
     * You can not purchaseReserved the cluster which is resizing.
     *
     * @param request The request containing all options for renew the specified cluster.
     */
    public void purchaseReservedVolumeCluster(PurchaseReservedVolumeClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_CLUSTER_PREFIX, request.getClusterId());
        internalRequest.addParameter(VolumeAction.purchaseReserved.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void autoRenewVolumeCluster(String clusterId, int renewTime, String renewTimeUnit) {
        AutoRenewVolumeClusterRequest request = new AutoRenewVolumeClusterRequest();
        request.setClusterId(clusterId);
        request.setRenewTime(renewTime);
        request.setRenewTimeUnit(renewTimeUnit);
        autoRenewVolumeCluster(request);
    }

    /**
     * Enable auto renewal the specified cluster
     *
     * @param request The request containing all options for renew the specified cluster.
     */
    public void autoRenewVolumeCluster(AutoRenewVolumeClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));

        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        if (request.getRenewTime() <= 0) {
            throw new IllegalArgumentException("request renewTime should be a positive integer");
        }

        if (!request.getRenewTimeUnit().equalsIgnoreCase("month") &&
                !request.getRenewTimeUnit().equalsIgnoreCase("year")) {
            throw new IllegalArgumentException("request renewTimeUnit only support \"month\" and \"year\"");
        }

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, VOLUME_CLUSTER_PREFIX, VolumeAction.autoRenew.name());

        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Disable auto renewal the cluster
     *
     * @param request The request containing all options for disable auto renew the specified cluster.
     */
    public void cancelAutoRenewVolumeCluster(CancelAutoRenewVolumeClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));

        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, VOLUME_CLUSTER_PREFIX, VolumeAction.cancelAutoRenew.name());

        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * The cluster can be unsubscribed after six months of use.
     *
     * @param clusterId The id of the volume.
     */
    public void refundVolumeCluster(String clusterId) {
        GetVolumeClusterRequest request = new GetVolumeClusterRequest();
        request.setClusterId(clusterId);
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, VOLUME_CLUSTER_PREFIX, request.getClusterId());
        invokeHttpClient(internalRequest, GetVolumeClusterResponse.class);
    }

    public GetAvailableImagesBySpecResponse getAvailableImagesBySpec(
            GetAvailableImagesBySpecRequest request) {

        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                IMAGE_PREFIX, "getAvailableImagesBySpec");
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getSpec())) {
            internalRequest.addParameter("spec", request.getSpec());
        }
        if (!Strings.isNullOrEmpty(request.getOsName())) {
            internalRequest.addParameter("osName", request.getOsName());
        }

        return invokeHttpClient(internalRequest, GetAvailableImagesBySpecResponse.class);
    }

}
