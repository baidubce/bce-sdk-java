/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bbc;

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
import com.baidubce.services.bbc.model.instance.BindBbcTagsRequest;
import com.baidubce.services.bbc.model.instance.CreateBbcImageRequest;
import com.baidubce.services.bbc.model.instance.CreateBbcImageResponse;
import com.baidubce.services.bbc.model.instance.CreateBbcInstanceRequest;
import com.baidubce.services.bbc.model.instance.CreateBbcInstanceResponse;
import com.baidubce.services.bbc.model.instance.GetBbcFlavorRaidResponse;
import com.baidubce.services.bbc.model.instance.GetBbcFlavorRequest;
import com.baidubce.services.bbc.model.instance.GetBbcFlavorResponse;
import com.baidubce.services.bbc.model.instance.GetBbcInstanceResponse;
import com.baidubce.services.bbc.model.instance.GetInstanceVpcRequest;
import com.baidubce.services.bbc.model.instance.GetInstanceVpcResponse;
import com.baidubce.services.bbc.model.instance.ListBbcFlavorResponse;
import com.baidubce.services.bbc.model.instance.ListBbcFlavorsRequest;
import com.baidubce.services.bbc.model.instance.ListBbcInstancesRequest;
import com.baidubce.services.bbc.model.instance.ListBbcInstancesResponse;
import com.baidubce.services.bbc.model.instance.ListOperationLogRequest;
import com.baidubce.services.bbc.model.instance.ListOperationLogResponse;
import com.baidubce.services.bbc.model.instance.RebuildBbcInstanceRequest;
import com.baidubce.services.bbc.model.instance.RenameBbcInstanceRequest;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.bcc.model.image.DeleteImageRequest;
import com.baidubce.services.bcc.model.image.GetImageRequest;
import com.baidubce.services.bcc.model.image.GetImageResponse;
import com.baidubce.services.bcc.model.image.ListImagesRequest;
import com.baidubce.services.bcc.model.image.ListImagesResponse;
import com.baidubce.services.bcc.model.instance.GetInstanceRequest;
import com.baidubce.services.bcc.model.instance.InstanceAction;
import com.baidubce.services.bcc.model.instance.ModifyInstanceDescRequest;
import com.baidubce.services.bcc.model.instance.ModifyInstancePasswordRequest;
import com.baidubce.services.bcc.model.instance.RebootInstanceRequest;
import com.baidubce.services.bcc.model.instance.ReleaseInstanceRequest;
import com.baidubce.services.bcc.model.instance.StartInstanceRequest;
import com.baidubce.services.bcc.model.instance.StopInstanceRequest;
import com.baidubce.services.bcc.model.reversed.ReservedTagsRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;
import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Hex;

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
import org.apache.commons.collections.CollectionUtils;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkMultyParamsNotBothEmpty;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by fulinhua on 2019-02-18
 */
public class BbcClient extends AbstractBceClient {

    private static final String VERSION = "v1";
    private static final String DELETE_VERSION = "v2";
    private static final String INSTANCE_PREFIX = "instance";
    private static final String FLAVOR_PREFIX = "flavor";
    private static final String FLAVOR_RAID_PREFIX = "flavorRaid";
    private static final String IMAGE_PREFIX = "image";
    private static final String OPERATION_LOG_PREFIX = "operationLog";
    private static final String TAG = "tag";
    private static final String VPC = "vpcSubnet";
    private static final String MARKER = "marker";
    private static final String MAX_KEYS = "maxKeys";
    private static final String INTERNAL_IP = "internalIp";
    private static final String IMAGE_TYPE = "imageType";
    private static final String START_TIME = "startTime";
    private static final String END_TIME = "endTime";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String POST_PAID = "Postpaid";
    private static final String BIND = "bind";
    private static final String UNBIND = "unbind";
    private static final String RESERVED_TAG_PREFIX = "bbc/reserved/tag";
    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String INSTANCEID_MESSAGE_KEY = "instanceId";
    private static final String FLAVORID_MESSAGE_KEY = "flavorId";
    private static final String TAGKEY_MESSAGE_KEY = "tagKey";
    private static final String RAID_MESSAGE_KEY = "raid";
    private static final String CHANGETAGS_NULL_ERROR_MESSAGE = "request changeTags should not be null.";
    private static final String ADMINPASS_MESSAGE_KEY = "adminPass";
    private static final String BBC_ID_MESSAGE_KEY = "bbcId";
    private static final String NAME_MESSAGE_KEY = "name";
    private static final String DESC_MESSAGE_KEY = "desc";
    private static final String IMAGEID_MESSAGE_KEY = "imageId";
    private static final String IMAGENAME_MESSAGE_KEY = "imageName";

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all bbc service calls.
     */
    private static final HttpResponseHandler[] bbc_handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new bbc client using the client configuration to access bbc.
     *
     * @param clientConfiguration The bcc client configuration options controlling how this client
     *                            connects to bbc (e.g. proxy settings, retry counts, etc).
     */
    public BbcClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, bbc_handlers);
    }

    public BbcClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers,
                     boolean isHttpAsyncPutEnabled) {
        super(config, responseHandlers, isHttpAsyncPutEnabled);
    }

    public BbcClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        super(config, responseHandlers);
    }

    /**
     * Constructs a new client to invoke service methods on bbc.
     */
    public BbcClient() {
        this(new BccClientConfiguration());
    }

    /**
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
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
     * Creates and initializes a new request object of V2 type operation. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createV2Request(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                            String... pathVariables) {
        List<String> path = new ArrayList<String>();

        path.add(DELETE_VERSION);

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
     * Binding the bbc instance to specified list of tags.
     *
     * @param request The request containing all options for binding the instance to specified list of tags.
     */
    public void bindInstanceTag(BindBbcTagsRequest request) {
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
        internalRequest.addParameter(BIND, null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Unbinding the bbc instance to specified list of tags.
     *
     * @param request The request containing all options for unbinding the instance to specified list of tags.
     */
    public void unBindInstanceTag(BindBbcTagsRequest request) {
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
        internalRequest.addParameter(UNBIND, null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modifying the password of the bbc instance.
     * <p>
     * You can reboot the instance only when the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param request The request containing all options for modifying the  bbc instance password.
     * @throws BceClientException
     */
    public void modifyBbcPassword(ModifyInstancePasswordRequest request) throws BceClientException {
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

    /**
     * Get the Vpc/Subnet information of specified bbc instance.
     *
     * @param request The request containing all options for getting the Vpc/Subnet information
     * @return A List of Vpc/Subnet information
     */
    public GetInstanceVpcResponse getInstanceVpcsubnet(GetInstanceVpcRequest request) throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (null != request.getBbcIds() && !request.getBbcIds().isEmpty()) {
            for (String bbcId : request.getBbcIds()) {
                checkStringNotEmpty(bbcId, checkEmptyExceptionMessageFormat(BBC_ID_MESSAGE_KEY));
            }
        } else {
            throw new IllegalArgumentException(CHANGETAGS_NULL_ERROR_MESSAGE);
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VPC);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, GetInstanceVpcResponse.class);
    }

    /**
     * Return a list of instances owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's bcc Instance.
     * @return The response containing a list of instances owned by the authenticated user.
     */
    public ListBbcInstancesResponse listBbcInstances(ListBbcInstancesRequest request) {
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
        return invokeHttpClient(internalRequest, ListBbcInstancesResponse.class);
    }

    /**
     * Get the detail information of specified bbc instance.
     *
     * @param request The request containing all options for getting the instance info.
     * @return A instance detail model for the instanceId.
     */
    public GetBbcInstanceResponse getInstance(GetInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, INSTANCE_PREFIX, request.getInstanceId());
        return this.invokeHttpClient(internalRequest, GetBbcInstanceResponse.class);
    }


    /**
     * Starting the bbc instance owned by the user.
     * <p>
     * You can start the instance only when the instance is Stopped,
     * otherwise,it's will get <code>409</code> errorCode.
     * <p>
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
     * Stopping the bbc instance owned by the user.
     * <p>
     * You can stop the instance only when the instance is Running,
     * otherwise,it's will get <code>409</code> errorCode.
     * <p>
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
     * Rebooting the bbc instance owned by the user.
     * <p>
     * You can reboot the instance only when the instance is Running,
     * otherwise,it's will get <code>409</code> errorCode.
     * <p>
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
     * Modifying the special attribute to new value of the instance.
     * <p>
     * You can reboot the instance only when the instance is Running or Stopped ,
     * otherwise,it's will get <code>409</code> errorCode.
     *
     * @param request The request containing all options for modifying the instance attribute.
     */
    public void renameBbcInstance(RenameBbcInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, INSTANCE_PREFIX, request.getInstanceId());
        internalRequest.addParameter(InstanceAction.rename.name(), null);
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
        internalRequest.addParameter(InstanceAction.updateDesc.name(), null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Rebuilding the bbc instance owned by the user.
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
    public void rebuildBbcInstance(RebuildBbcInstanceRequest request) throws BceClientException {
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
    public void releaseBbcInstance(ReleaseInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createV2Request(
                request, HttpMethodName.DELETE, INSTANCE_PREFIX, request.getInstanceId());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Listing all the flavors of the bbc instance
     * @param request
     * @return
     */
    public ListBbcFlavorResponse listBbcFlavors(ListBbcFlavorsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, FLAVOR_PREFIX);
        return this.invokeHttpClient(internalRequest, ListBbcFlavorResponse.class);
    }

    /**
     * Geting the flavor information of a flavor
     * @param request The request containing all options for releasing the instance.
     * @return
     */
    public GetBbcFlavorResponse getBbcFlavor(GetBbcFlavorRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getFlavorId(), checkEmptyExceptionMessageFormat(FLAVORID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, FLAVOR_PREFIX, request.getFlavorId());
        return this.invokeHttpClient(internalRequest, GetBbcFlavorResponse.class);
    }
    /**
     * Geting the flavor raid information of a raid
     * @param request The request containing all options for releasing the instance.
     * @return
     */
    public GetBbcFlavorRaidResponse getBbcFlavorRaid(GetBbcFlavorRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getFlavorId(), checkEmptyExceptionMessageFormat(FLAVORID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, FLAVOR_RAID_PREFIX, request.getFlavorId());
        return this.invokeHttpClient(internalRequest, GetBbcFlavorRaidResponse.class);
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
     * @param request The request containing all options for creating a new image.
     * @return The response with id of image newly created.
     */
    public CreateBbcImageResponse createBbcImage(CreateBbcImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getImageName(), checkEmptyExceptionMessageFormat(IMAGENAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, IMAGE_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBbcImageResponse.class);
    }

    /**
     * Listing bbc images owned by the authenticated user.
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
     * <p>
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
     * Listing the operation log of the bbc.
     * The startTime and endTime means the time quantum that the log had taken.
     * If the starTime or the endTime is empty,it means searching the operation log of today.
     * @param request
     * @return
     */
    public ListOperationLogResponse listBbcOperationLog(ListOperationLogRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, OPERATION_LOG_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getStartTime())) {
            internalRequest.addParameter(START_TIME, request.getStartTime());
        }
        if (!Strings.isNullOrEmpty(request.getEndTime())) {
            internalRequest.addParameter(END_TIME, request.getEndTime());
        }
        return invokeHttpClient(internalRequest, ListOperationLogResponse.class);
    }

    /**
     * Create a bcc Instance with the specified options,
     * see all the supported instance in {@link com.baidubce.services.bcc.model.instance.InstanceType}
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p>
     * This is an asynchronous interface,
     * you can get the latest status by invoke {@link #getInstance(GetInstanceRequest)}
     *
     * @param request The request containing all options for creating a bcc Instance.
     * @return List of instanceId newly created
     * @throws BceClientException
     */
    public CreateBbcInstanceResponse createInstance(CreateBbcInstanceRequest request)
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
        checkStringNotEmpty(request.getImageId(), checkEmptyExceptionMessageFormat(IMAGEID_MESSAGE_KEY));
        checkStringNotEmpty(request.getFlavorId(), checkEmptyExceptionMessageFormat(FLAVORID_MESSAGE_KEY));
        checkStringNotEmpty(request.getRaidId(), checkEmptyExceptionMessageFormat(RAID_MESSAGE_KEY));
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
        return invokeHttpClient(internalRequest, CreateBbcInstanceResponse.class);
    }

    /**
     * The method to generate a default Billing which is Postpaid.
     *
     * @return The Billing object with Postpaid PaymentTiming.
     */
    private Billing generateDefaultBilling() {
        Billing billing = new Billing();
        billing.setPaymentTiming(POST_PAID);
        return billing;
    }


    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    private String aes128WithFirst16Char(String content, String privateKey) throws GeneralSecurityException {
        byte[] crypted = null;
        SecretKeySpec skey = new SecretKeySpec(privateKey.substring(0, 16).getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        crypted = cipher.doFinal(content.getBytes());
        return new String(Hex.encodeHex(crypted));
    }


    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     *                        any additional headers or parameters, and execute.
     * @param bceRequest      The original request, as created by the user.
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

    public void bindReservedInstanceToTags(ReservedTagsRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkMultyParamsNotBothEmpty(request.getReservedInstanceIds(), "reservedInstanceIds should NOT be null.");
        if (CollectionUtils.isEmpty(request.getChangeTags())) {
            throw new IllegalArgumentException(CHANGETAGS_NULL_ERROR_MESSAGE);
        }
        for (TagModel tag : request.getChangeTags()) {
            checkStringNotEmpty(tag.getTagKey(), checkEmptyExceptionMessageFormat(TAGKEY_MESSAGE_KEY));
        }

        InternalRequest internalRequest = this.createV2Request(request, HttpMethodName.PUT,
                RESERVED_TAG_PREFIX);
        internalRequest.addParameter(BIND, null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void unbindReservedInstanceFromTags(ReservedTagsRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkMultyParamsNotBothEmpty(request.getReservedInstanceIds(), "reservedInstanceIds should NOT be null.");
        if (CollectionUtils.isEmpty(request.getChangeTags())) {
            throw new IllegalArgumentException(CHANGETAGS_NULL_ERROR_MESSAGE);
        }
        for (TagModel tag : request.getChangeTags()) {
            checkStringNotEmpty(tag.getTagKey(), checkEmptyExceptionMessageFormat(TAGKEY_MESSAGE_KEY));
        }

        InternalRequest internalRequest = this.createV2Request(request, HttpMethodName.PUT,
                RESERVED_TAG_PREFIX);
        internalRequest.addParameter(UNBIND, null);

        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }
}
