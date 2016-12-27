/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.ocr;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;

import com.baidubce.services.ocr.model.BankcardRecognitionRequest;
import com.baidubce.services.ocr.model.BankcardRecognitionResponse;
import com.baidubce.services.ocr.model.FormAbstractBceRequest;
import com.baidubce.services.ocr.model.GeneralRecognitionRequest;
import com.baidubce.services.ocr.model.GeneralRecognitionResponse;
import com.baidubce.services.ocr.model.IdcardRecognitionRequest;
import com.baidubce.services.ocr.model.IdcardRecognitionResponse;

import com.baidubce.util.HttpUtils;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lvsiyuan on 16/11/18.
 */
public class OcrClient extends AbstractBceClient {

    private static final String VERSION = "api/v1";
    private static final String PATH_OCR = "ocr";

    private static final String PARA_GENERAL = "general";
    private static final String PARA_BANK = "bankcard";
    private static final String PARA_ID = "idcard";

    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";

    /**
     * Responsible for handling httpResponses from all Ocr service calls.
     */
    private static final HttpResponseHandler[] responseHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client using the client configuration.
     *
     * @param ocrConfig The client configuration options controlling how this client connects to Ocr services
     *            (e.g. proxy settings, retry counts, etc).
     */
    public OcrClient(BceClientConfiguration ocrConfig) {
        // enable HTTP Async manner for PUT method
        super(ocrConfig, responseHandlers, true);
    }

    /**
     * Gets the bankcard recognition properties of specific image resource.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param image The image data which needs to be base64
     * @return The bankcard recognition properties of the image resource
     */
    public BankcardRecognitionResponse bankcardRecognition(String image) {
        BankcardRecognitionRequest request = new BankcardRecognitionRequest().withImage(image);
        return bankcardRecognition(request);
    }

    /**
     * Gets the bankcard recognition properties of specific image resource.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request wrapper object containing all options.
     * @return The bankcard recognition properties of the image resource
     */
    public BankcardRecognitionResponse bankcardRecognition(BankcardRecognitionRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getImage(), "Image should not be null or empty!");

        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, PARA_BANK);

        return invokeHttpClient(internalRequest, BankcardRecognitionResponse.class);
    }

    /**
     * Gets the idcard recognition properties of specific image resource.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param image The image data which needs to be base64
     * @param side The side of idcard image. (front/back)
     * @return The idcard recognition properties of the image resource
     */
    public IdcardRecognitionResponse idcardRecognition(String image, String side) {
        IdcardRecognitionRequest request = new IdcardRecognitionRequest().withImage(image).withSide(side);
        return idcardRecognition(request);
    }

    /**
     * Gets the idcard recognition properties of specific image resource.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param image The image data which needs to be base64
     * @param side The side of idcard image. (front/back)
     * @param direction Decide if the image has been rotated (true/false)
     * @return The idcard recognition properties of the image resource
     */
    public IdcardRecognitionResponse idcardRecognition(String image, String side, Boolean direction) {
        IdcardRecognitionRequest request = new IdcardRecognitionRequest().withImage(image)
                                            .withSide(side).withDirection(direction);
        return idcardRecognition(request);
    }

    /**
     * Gets the idcard recognition properties of specific image resource.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request wrapper object containing all options.
     * @return The idcard recognition properties of the image resource
     */
    public IdcardRecognitionResponse idcardRecognition(IdcardRecognitionRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getImage(), "Image should not be null or empty!");

        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, PARA_ID);

        return invokeHttpClient(internalRequest, IdcardRecognitionResponse.class);
    }

    /**
     * Gets the general recognition properties of specific image resource.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param image The image data which needs to be base64
     * @return The general recognition properties of the image resource
     */
    public GeneralRecognitionResponse generalRecognition(String image) {
        GeneralRecognitionRequest request = new GeneralRecognitionRequest().withImage(image);
        return generalRecognition(request);
    }

    /**
     * Gets the general recognition properties of specific image resource.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param image The image data which needs to be base64
     * @param granularity Decide if recognize single character (big/small)
     * @param mask Represent the gray/white/black shade image area (base64 data)
     * @param langType Language type, detail to be CHN_ENG (ENG/POR/FRE/GER/ITA/SPA/RUS/JAP)
     * @param direction Decide if the image has been rotated (true/false)
     * @return The general recognition properties of the image resource
     */
    public GeneralRecognitionResponse generalRecognition(String image, String granularity,
                                                         String mask, String langType, Boolean direction) {
        GeneralRecognitionRequest request = new GeneralRecognitionRequest().withImage(image)
                .withGranularity(granularity).withMask(mask).withLangType(langType).withDirection(direction);
        return generalRecognition(request);
    }

    /**
     * Gets the general recognition properties of specific image resource.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request wrapper object containing all options.
     * @return The general recognition properties of the image resource
     */
    public GeneralRecognitionResponse generalRecognition(GeneralRecognitionRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getImage(), "Image should not be null or empty!");

        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, PARA_GENERAL);

        return invokeHttpClient(internalRequest, GeneralRecognitionResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource. This method is responsible for
     * determining HTTP method, URI path, credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.
     * </p>
     *
     * @param httpMethod The HTTP method to use when sending the request.
     * @param request The original request, as created by the user.
     * @param pathVariables The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate any
     *         additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, FormAbstractBceRequest request, String...pathVariables) {

        // Build URL paths
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(VERSION);
        pathComponents.add(PATH_OCR);

        // Append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(getEndpoint(), pathComponents.toArray(new String[pathComponents.size()]));

        // Get a InternalRequest instance and set headers
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        internalRequest.setCredentials(request.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST
                || httpMethod == HttpMethodName.PUT) {
            fillRequestPayload(internalRequest, request);
        }
        return internalRequest;
    }

    private InternalRequest fillRequestPayload(InternalRequest internalRequest, FormAbstractBceRequest request) {
        String strFormParameters = request.toFormString();
        byte[] requestJson = null;
        try {
            requestJson = strFormParameters.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, FORM_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));

        return internalRequest;
    }
}
