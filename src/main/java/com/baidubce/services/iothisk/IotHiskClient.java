/*
 * Copyright 2018 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iothisk;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.iothisk.model.ActiveRequest;
import com.baidubce.services.iothisk.model.ActiveResponse;
import com.baidubce.services.iothisk.model.AuthRequest;
import com.baidubce.services.iothisk.model.CipherRequest;
import com.baidubce.services.iothisk.model.CipherResponse;

/**
 * Provides the client for accessing the iot hisk service.
 */
public class IotHiskClient extends AbstractIotHiskBceClient {

    private static final String ENDPOINT = "hisk.baidubce.com";
    private static final String VERSION = "v1";

    private static final String TSM = "tsm";
    private static final String DEVICE = "device";
    private static final String DECRYPT = "decrypt";
    private static final String ENCRYPT = "encrypt";
    private static final String ACTIVE = "active";
    private static final String AUTH = "auth";

    private static final String NULL_DEVICE_ID = "device id should not be null.";
    private static final String NULL_REQUEST = "request should not be null.";

    /**
     * Constructs a new hisk client using the client configuration to access hisk.
     *
     * @param config The bcc client configuration options controlling how this client
     *               connects to bcc (e.g. proxy settings, retry counts, etc).
     */
    public IotHiskClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, HANDLERS);
    }

    /**
     * Encrypt message for specified device.
     *
     * @param deviceId specified device id
     * @param request plain text request message(base64 encoding), which required be encrypted
     * @return encrypted cipher message(base64 encoding)
     */
    public CipherResponse encrypt(String deviceId, CipherRequest request) {
        checkNotNull(request, NULL_REQUEST);
        checkNotNull(deviceId, NULL_DEVICE_ID);
        InternalRequest internalRequest = createTsmDeviceRequest(request, HttpMethodName.POST, deviceId, ENCRYPT);
        return this.invokeHttpClient(internalRequest, CipherResponse.class);
    }

    /**
     * Decrypt message for specified device.
     *
     * @param deviceId specified device id
     * @param request cipher request message(base64 encoding), which required be decrypted
     * @return decrypted plain text message(base64 encoding)
     */
    public CipherResponse decrypt(String deviceId, CipherRequest request) {
        checkNotNull(request, NULL_REQUEST);
        checkNotNull(deviceId, NULL_DEVICE_ID);
        InternalRequest internalRequest = createTsmDeviceRequest(request, HttpMethodName.POST, deviceId, DECRYPT);
        return this.invokeHttpClient(internalRequest, CipherResponse.class);
    }

    /**
     * Active specified device
     *
     * @param deviceId specified device id
     * @param request activation message
     * @return successful activation result with device id, otherwise an exception will be thrown
     */
    public ActiveResponse active(String deviceId, ActiveRequest request) {
        checkNotNull(request, NULL_REQUEST);
        checkNotNull(deviceId, NULL_DEVICE_ID);
        InternalRequest internalRequest = createTsmDeviceRequest(request, HttpMethodName.POST, deviceId, ACTIVE);
        return this.invokeHttpClient(internalRequest, ActiveResponse.class);
    }

    /**
     * Authenticate for specified device.
     *
     * @param deviceId specified device id
     * @param request device authentication message
     */
    public void auth(String deviceId, AuthRequest request) {
        checkNotNull(request, NULL_REQUEST);
        checkNotNull(deviceId, NULL_DEVICE_ID);
        InternalRequest internalRequest = createTsmDeviceRequest(request, HttpMethodName.POST, deviceId, AUTH);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    private InternalRequest createTsmDeviceRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                                   String deviceId, String action) {
        List<String> path = new ArrayList<String>();
        path.addAll(Arrays.asList(VERSION, TSM, DEVICE));
        return createRequest(bceRequest, httpMethod, null, path, deviceId, action);
    }

}
