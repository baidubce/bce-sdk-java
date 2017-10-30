/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
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
import com.baidubce.services.bcc.model.image.CreateImageRequest;
import com.baidubce.services.bcc.model.image.CreateImageResponse;
import com.baidubce.services.bcc.model.image.DeleteImageRequest;
import com.baidubce.services.bcc.model.image.GetImageRequest;
import com.baidubce.services.bcc.model.image.GetImageResponse;
import com.baidubce.services.bcc.model.image.ListImagesRequest;
import com.baidubce.services.bcc.model.image.ListImagesResponse;
import com.baidubce.services.bcc.model.instance.BindSecurityGroupRequest;
import com.baidubce.services.bcc.model.instance.CreateInstanceRequest;
import com.baidubce.services.bcc.model.instance.CreateInstanceResponse;
import com.baidubce.services.bcc.model.instance.GetInstanceRequest;
import com.baidubce.services.bcc.model.instance.GetInstanceResponse;
import com.baidubce.services.bcc.model.instance.GetInstanceVncRequest;
import com.baidubce.services.bcc.model.instance.GetInstanceVncResponse;
import com.baidubce.services.bcc.model.instance.InstanceAction;
import com.baidubce.services.bcc.model.instance.ListInstanceSpecsRequest;
import com.baidubce.services.bcc.model.instance.ListInstanceSpecsResponse;
import com.baidubce.services.bcc.model.instance.ListInstancesRequest;
import com.baidubce.services.bcc.model.instance.ListInstancesResponse;
import com.baidubce.services.bcc.model.instance.ModifyInstanceAttributesRequest;
import com.baidubce.services.bcc.model.instance.ModifyInstancePasswordRequest;
import com.baidubce.services.bcc.model.instance.PurchaseReservedInstanceRequeset;
import com.baidubce.services.bcc.model.instance.RebootInstanceRequest;
import com.baidubce.services.bcc.model.instance.RebuildInstanceRequest;
import com.baidubce.services.bcc.model.instance.ReleaseInstanceRequest;
import com.baidubce.services.bcc.model.instance.ResizeInstanceRequest;
import com.baidubce.services.bcc.model.instance.StartInstanceRequest;
import com.baidubce.services.bcc.model.instance.StopInstanceRequest;
import com.baidubce.services.bcc.model.instance.UnbindSecurityGroupRequest;
import com.baidubce.services.bcc.model.securitygroup.CreateSecurityGroupRequest;
import com.baidubce.services.bcc.model.securitygroup.CreateSecurityGroupResponse;
import com.baidubce.services.bcc.model.securitygroup.DeleteSecurityGroupRequest;
import com.baidubce.services.bcc.model.securitygroup.ListSecurityGroupsRequest;
import com.baidubce.services.bcc.model.securitygroup.ListSecurityGroupsResponse;
import com.baidubce.services.bcc.model.securitygroup.SecurityGroupRuleOperateRequest;
import com.baidubce.services.bcc.model.snapshot.CreateSnapshotRequest;
import com.baidubce.services.bcc.model.snapshot.CreateSnapshotResponse;
import com.baidubce.services.bcc.model.snapshot.DeleteSnapshotRequest;
import com.baidubce.services.bcc.model.snapshot.GetSnapshotRequest;
import com.baidubce.services.bcc.model.snapshot.GetSnapshotResponse;
import com.baidubce.services.bcc.model.snapshot.ListSnapshotsRequest;
import com.baidubce.services.bcc.model.snapshot.ListSnapshotsResponse;
import com.baidubce.services.bcc.model.volume.AttachVolumeRequest;
import com.baidubce.services.bcc.model.volume.AttachVolumeResponse;
import com.baidubce.services.bcc.model.volume.CreateVolumeRequest;
import com.baidubce.services.bcc.model.volume.CreateVolumeResponse;
import com.baidubce.services.bcc.model.volume.DetachVolumeRequest;
import com.baidubce.services.bcc.model.volume.GetVolumeRequest;
import com.baidubce.services.bcc.model.volume.GetVolumeResponse;
import com.baidubce.services.bcc.model.volume.ListVolumesRequest;
import com.baidubce.services.bcc.model.volume.ListVolumesResponse;
import com.baidubce.services.bcc.model.volume.PurchaseReservedVolumeRequest;
import com.baidubce.services.bcc.model.volume.ReleaseVolumeRequest;
import com.baidubce.services.bcc.model.volume.ResizeVolumeRequest;
import com.baidubce.services.bcc.model.volume.RollbackVolumeRequest;
import com.baidubce.services.bcc.model.volume.VolumeAction;
import com.baidubce.services.bcc.model.zone.ListZonesResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud Compute Service(bcc).
 *
 */
public class BccClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BccClient.class);

    private static final String VERSION = "v2";
    private static final String INSTANCE_PREFIX = "instance";
    private static final String VOLUME_PREFIX = "volume";
    private static final String IMAGE_PREFIX = "image";
    private static final String SECURITYGROUP_PREFIX = "securityGroup";
    private static final String SNAPSHOT_PREFIX = "snapshot";
    private static final String ZONE = "zone";

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
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
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
     *     BCE API doc</a>
     *
     * @param content The content String to encrypt.
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
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        checkStringNotEmpty(request.getImageId(), "imageId should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, INSTANCE_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
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
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, INSTANCE_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getInternalIp())) {
            internalRequest.addParameter("internalIp", request.getInternalIp());
        }
        if (!Strings.isNullOrEmpty(request.getDedicatedHostId())) {
            internalRequest.addParameter("dedicatedHostId", request.getDedicatedHostId());
        }
        if (!Strings.isNullOrEmpty(request.getZoneName())) {
            internalRequest.addParameter("zoneName", request.getZoneName());
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
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getInstanceId(), "request instanceId should not be null.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, INSTANCE_PREFIX, request.getInstanceId());
        return this.invokeHttpClient(internalRequest, GetInstanceResponse.class);
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
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
                .withInstanceId(instanceId).withForceStop(false));
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
                .withInstanceId(instanceId).withForceStop(forceStop));
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
        checkStringNotEmpty(request.getAdminPass(), "request adminPass should not be empty.");
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

    /**
     * Modifying the special attribute to new value of the instance.
     *
     * You can reboot the instance only when the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param request The request containing all options for modifying the instance attribute.
     */
    public void modifyInstanceAttributes(ModifyInstanceAttributesRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
        checkStringNotEmpty(request.getName(), "request name should not be empty.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.modifyAttribute.name(), null);
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
     *     <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     *                      BCE API doc</a>
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
     * all of customized images created from original instance system disk will be saved.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for rebuilding the instance.
     * @throws BceClientException
     */
    public void rebuildInstance(RebuildInstanceRequest request) throws BceClientException {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
        checkStringNotEmpty(request.getImageId(), "request imageId should not be empty.");
        checkStringNotEmpty(request.getAdminPass(), "request adminPass should not be empty.");
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, INSTANCE_PREFIX, request.getInstanceId());
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
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
        if (request.getCpuCount() <= 0) {
            throw new IllegalArgumentException("request cpuCount should be positive.");
        }
        if (request.getMemoryCapacityInGB() <= 0) {
            throw new IllegalArgumentException("request memoryCapacityInGB should be positive.");
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.resize.name(), null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Binding the instance to specified securitygroup.
     *
     * @param instanceId The id of the instance.
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
        checkStringNotEmpty(request.getSecurityGroupId(), "request securityGroupId should not be empty.");
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
        checkStringNotEmpty(request.getSecurityGroupId(), "request securityGroupId should not be empty.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.unbind.name(), null);
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
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
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBillingWithReservation());
        }
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.purchaseReserved.name(), null);
        internalRequest.addParameter("clientToken", request.getClientToken());
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
     *     BCE API doc</a>
     *
     * @return List of specification for instance resource to buy.
     */
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
     *     BCE API doc</a>
     *
     * @param request The request containing all options for Listing all of specification for instance resource.
     * @return List of specification for instance resource to buy.
     */
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
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VOLUME_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
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
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getInstanceId())) {
            internalRequest.addParameter("instanceId", request.getInstanceId());
        }
        if (!Strings.isNullOrEmpty(request.getZoneName())) {
            internalRequest.addParameter("zoneName", request.getZoneName());
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getVolumeId(), "request volumeId should not be empty.");
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
     * @param volumeId The id of the volume which will be attached to specified instance.
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getVolumeId(), "request volumeId should not be empty.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
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
     * @param volumeId The id of the volume which has been attached to specified instance.
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getVolumeId(), "request volumeId should not be empty.");
        checkStringNotEmpty(request.getInstanceId(), "request instanceId should not be empty.");
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getVolumeId(), "request volumeId should not be empty.");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, VOLUME_PREFIX, request.getVolumeId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Resizing the specified volume with newly size.
     *
     * You can resize the specified volume only when the volume is Available,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * The prepaid volume can not be downgrade.
     * <p>
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getVolume(String)}
     *
     * @param volumeId The id of volume which you want to resize.
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
     * <p>
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getVolume(String)}
     *
     * @param request The request containing all options for resize the specified volume.
     */
    public void resizeVolume(ResizeVolumeRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getVolumeId(), "request volumeId should not be empty.");
        checkState(request.getNewCdsSizeInGB() > 0, "request newCdsSizeInGB should greater than 0");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_PREFIX, request.getVolumeId());
        internalRequest.addParameter(VolumeAction.resize.name(), null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Rollback the volume with the specified volume snapshot.
     *
     * <p>
     * You can rollback the specified volume only when the volume is Available,
     * otherwise,it's will get <code>409</code> errorCode.
     * <p>
     * The snapshot used to rollback must be created by the volume,
     * otherwise,it's will get <code>404</code> errorCode.
     * <p>
     * If rolling back the system volume,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.After rolling back the
     * volume,all the system disk data will erase.
     *
     * @param volumeId The id of volume which will be rollback.
     * @param snapshotId The id of snapshot which will be used to rollback the volume.
     */
    public void rollbackVolume(String volumeId, String snapshotId) {
        this.rollbackVolume(new RollbackVolumeRequest()
                .withVolumeId(volumeId).withSnapshotId(snapshotId));
    }

    /**
     * Rollback the volume with the specified volume snapshot.
     *
     * <p>
     * You can rollback the specified volume only when the volume is Available,
     * otherwise,it's will get <code>409</code> errorCode.
     * <p>
     * The snapshot used to rollback must be created by the volume,
     * otherwise,it's will get <code>404</code> errorCode.
     * <p>
     * If rolling back the system volume,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.After rolling back the
     * volume,all the system disk data will erase.
     *
     * @param request The request containing all options for rolling back the specified volume.
     */
    public void rollbackVolume(RollbackVolumeRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getVolumeId(), "request volumeId should not be empty.");
        checkStringNotEmpty(request.getSnapshotId(), "request snapshotId should not be empty.");
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
     * @param volumeId The id of volume which will be renew.
     * @param reservationLength The fixed duration to renew,available is [1,2,3,4,5,6,7,8,9,12,24,36]
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
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBillingWithReservation());
        }
        checkStringNotEmpty(request.getVolumeId(), "request volumeId should not be empty.");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, VOLUME_PREFIX, request.getVolumeId());
        internalRequest.addParameter(VolumeAction.purchaseReserved.name(), null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Creating a customized image from the instance..
     *
     * <p>
     * While creating an image from an instance,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getImage(GetImageRequest)}
     *
     * @param imageName The name of image that will be created.
     * @param instanceId The id of instance which will be used to create image.
     * @return The response with id of image newly created.
     */
    public CreateImageResponse createImageFromInstance(String imageName, String instanceId) {
        return createImage(new CreateImageRequest()
                .withImageName(imageName).withInstanceId(instanceId));
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
     * @param imageName The name of image that will be created.
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
     * <p>
     * You can create an image from an instance or you can create from an snapshot.
     * The parameters of instanceId and snapshotId can no be null simultaneously.
     * when both instanceId and snapshotId are assigned,only instanceId will be used.
     * <p>
     * While creating an image from an instance,the instance must be Running or Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     * <p>
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
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getImageName(), "request imageName should not be empty.");
        if (Strings.isNullOrEmpty(request.getInstanceId()) && Strings.isNullOrEmpty(request.getSnapshotId())) {
            throw new IllegalArgumentException("request instanceId or snapshotId should not be empty .");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, IMAGE_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
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
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, IMAGE_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getImageType())) {
            internalRequest.addParameter("imageType", request.getImageType());
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getImageId(), "request imageId should not be empty.");
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getImageId(), "request imageId should not be empty.");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, IMAGE_PREFIX, request.getImageId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
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
     * @param volumeId The id of volume which will be used to create snapshot.
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
     * @param volumeId The id of volume which will be used to create snapshot.
     * @param snapshotName The name of snapshot will be created.
     * @param desc The optional parameter to describe the newly created snapshot.
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
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getVolumeId(), "request volumeId should not be empty.");
        checkStringNotEmpty(request.getSnapshotName(), "request snapshotName should not be empty.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, SNAPSHOT_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
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
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, SNAPSHOT_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getVolumeId())) {
            internalRequest.addParameter("volumeId", request.getVolumeId());
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getSnapshotId(), "request snapshotId should no be empty.");
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getSnapshotId(), "request snapshotId should no be empty.");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, SNAPSHOT_PREFIX, request.getSnapshotId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }



    /**
     * Listing SecurityGroup owned by the authenticated user.
     *
     * @param request The request containing all options for listing SecurityGroup owned by user.
     * @return The response with list of SecurityGroup which contains SecurityGroup rules owned by user.
     */
    public ListSecurityGroupsResponse listSecurityGroups(ListSecurityGroupsRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, SECURITYGROUP_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getInstanceId())) {
            internalRequest.addParameter("instanceId", request.getInstanceId());
        }
        if (!Strings.isNullOrEmpty(request.getVpcId())) {
            internalRequest.addParameter("vpcId", request.getVpcId());
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
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getName(), "request name should not be empty.");
        if (null == request.getRules() || request.getRules().isEmpty()) {
            throw new IllegalArgumentException("request rules should not be empty");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, SECURITYGROUP_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateSecurityGroupResponse.class);
    }

    /**
     * authorizing a security group rule to a specified security group
     * @param request The request containing all options for authorizing security group rule
     */
    public void authorizeSecurityGroupRule(SecurityGroupRuleOperateRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getSecurityGroupId(), "securityGroupId should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getRule()) {
            throw new IllegalArgumentException("request rule should not be null");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, SECURITYGROUP_PREFIX,
                request.getSecurityGroupId());
        internalRequest.addParameter("authorizeRule", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * revoking a security group rule from the specified security group
     * @param request The request containing all options for revoking security group rule
     */
    public void revokeSecurityGroupRule(SecurityGroupRuleOperateRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getSecurityGroupId(), "securityGroupId should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (null == request.getRule()) {
            throw new IllegalArgumentException("request rule should not be null");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, SECURITYGROUP_PREFIX,
                request.getSecurityGroupId());
        internalRequest.addParameter("revokeRule", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
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
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getSecurityGroupId(), "request securityGroupId should not be empty.");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, SECURITYGROUP_PREFIX, request.getSecurityGroupId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * listing zones within current region
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
     * @param request use withRequestCredentials
     * @return The response with list of zones
     */
    public ListZonesResponse listZones(AbstractBceRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ZONE);
        return invokeHttpClient(internalRequest, ListZonesResponse.class);
    }
}
