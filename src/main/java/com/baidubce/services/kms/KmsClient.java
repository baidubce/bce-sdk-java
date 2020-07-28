/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.kms;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.kms.model.EncryptedRsaKey;
import com.baidubce.services.kms.model.KmsResponse;
import com.baidubce.services.kms.model.ImportAsymmetricKeyRequest;
import com.baidubce.services.kms.model.ImportKeyRequest;
import com.baidubce.services.kms.model.CreateKeyResponse;
import com.baidubce.services.kms.model.CreateKeyRequest;
import com.baidubce.services.kms.model.GetParametersForImportResponse;
import com.baidubce.services.kms.model.GetParametersForImportRequest;
import com.baidubce.services.kms.model.Constants;
import com.baidubce.services.kms.model.ListKeysRequest;
import com.baidubce.services.kms.model.ListKeysResponse;
import com.baidubce.services.kms.model.EncryptRequest;
import com.baidubce.services.kms.model.EncryptResponse;
import com.baidubce.services.kms.model.DecryptRequest;
import com.baidubce.services.kms.model.DecryptResponse;
import com.baidubce.services.kms.model.GenerateDataKeyRequest;
import com.baidubce.services.kms.model.GenerateDataKeyResponse;
import com.baidubce.services.kms.model.EnableKeyRequest;
import com.baidubce.services.kms.model.DisableKeyRequest;
import com.baidubce.services.kms.model.ScheduleKeyDeletionRequest;
import com.baidubce.services.kms.model.ScheduleKeyDeletionResponse;
import com.baidubce.services.kms.model.CancelKeyDeletionRequest;
import com.baidubce.services.kms.model.DescribeKeyRequest;
import com.baidubce.services.kms.model.DescribeKeyResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Key Manager Service.
 */
public class KmsClient extends AbstractBceClient {

    /**
     * Responsible for handing httpResponses from all Kms service calls.
     */
    private static final HttpResponseHandler[] kmsHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), 
            new BceJsonResponseHandler()
    };


    public KmsClient() {
        this(new KmsClientConfiguration());
    }

    public KmsClient(KmsClientConfiguration clientConfiguration) {
        super(clientConfiguration, kmsHandlers, true);
    }
    
    /**
     * Creates a new master key.
     *
     * @param request The request object containing all options for creating master key.
     *
     * @return The newly created master key.
     */
    public CreateKeyResponse createKey(CreateKeyRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "CreateKey");
        
        byte[] json = null;
        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_DESCRIPTION, request.getDescription());
            jsonGenerator.writeStringField(Constants.FIELD_KEYUSAGE, request.getKeyUsage());
            jsonGenerator.writeStringField(Constants.FIELD_KEYSPEC, request.getKeySpec());
            jsonGenerator.writeStringField(Constants.FIELD_PROTECTEDBY, request.getProtectedBy());
            jsonGenerator.writeStringField(Constants.FIELD_ORIGIN, request.getOrigin());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException(Constants.FAIL_TO_GET_UTF8_BYTES, e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, Constants.APPLICATION_JSON);
        internalRequest.setContent(RestartableInputStream.wrap(json));

        CreateKeyResponse response = this.invokeHttpClient(internalRequest, CreateKeyResponse.class);
        return response;
    }

    /**
     * Returns ListKeysResponse containing master keys.
     *
     * @param request The request object containing limit and marker for listing master keys.
     *
     * @return ListKeysResponse containing a listing of the master keys.
     */
    public ListKeysResponse listKeys(ListKeysRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "ListKeys");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField(Constants.FIELD_LIMIT, request.getLimit());
            jsonGenerator.writeStringField(Constants.FIELD_MARKER, request.getMarker());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);

        ListKeysResponse response = this.invokeHttpClient(internalRequest, ListKeysResponse.class);
        return response;
    }

    /**
     * Returns EncryptResponse containing ciphertext which is encrypted using plaintext by master key.
     *
     * @param request The request object containing masterKeyId and plaintext.
     *
     * @return EncryptResponse containing ciphertext and master key id.
     */
    public EncryptResponse encrypt(EncryptRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter("action", "Encrypt");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeStringField(Constants.FIELD_PLAINTEXT, request.getPlaintext());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);

        EncryptResponse response = this.invokeHttpClient(internalRequest, EncryptResponse.class);
        return response;
    }

    /**
     * Returns DecryptResponse containing plaintext which is decrypted using ciphertext. 
     * MasterKeyId is in ciphertext.
     *
     * @param request The request object containing ciphertext.
     *
     * @return DecryptResponse containing master key id and plaintext .
     */
    public DecryptResponse decrypt(DecryptRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "Decrypt");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeStringField(Constants.FIELD_CIPHERTEXT, request.getCiphertext());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);

        DecryptResponse response = this.invokeHttpClient(internalRequest, DecryptResponse.class);
        return response;
    }

    /**
     * Returns GenerateDataKeyResponse containing ciphertext, keyId and plaintext which is random generated
     * by kms.
     *
     * @param request The request object containing master key id, keySpec and numberOfBytes.
     *
     * @return GenerateDataKeyResponse containing ciphertext, keyId and plaintext.
     */
    public GenerateDataKeyResponse generateDataKey(GenerateDataKeyRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "GenerateDataKey");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeStringField(Constants.FIELD_KEYSPEC, request.getKeySpec());
            jsonGenerator.writeNumberField(Constants.FIELD_NUMBEROFBYTES, request.getNumberOfBytes());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);

        GenerateDataKeyResponse response = this.invokeHttpClient(internalRequest, GenerateDataKeyResponse.class);
        return response;
    }

    /**
     * Enable the specified master key.
     *
     * @param request The request object containing master key id.
     */
    public void enableKey(EnableKeyRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "EnableKey");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);

        this.invokeHttpClient(internalRequest, KmsResponse.class);
        return ;
    }

    /**
     * Disable the specified master key.
     *
     * @param request The request object containing master key id.
     */
    public void disableKey(DisableKeyRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "DisableKey");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);

        this.invokeHttpClient(internalRequest, KmsResponse.class);
    }

    /**
     * Returns ScheduleKeyDeletionResponse containing deletionDate of specified master key and master key id.
     *
     * @param request The request object containing master key id, pendingWindowsInDays.
     *
     * @return ScheduleKeyDeletionResponse containing deletionDate and master key id.
     */
    public ScheduleKeyDeletionResponse scheduleKeyDeletion(ScheduleKeyDeletionRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "ScheduleKeyDeletion");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeNumberField(Constants.FIELD_PENDINGWINDOWINDAYS, request.getPendingWindowsInDays());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);

        ScheduleKeyDeletionResponse response = this.invokeHttpClient(internalRequest, 
                ScheduleKeyDeletionResponse.class); 
        return response;
    }

    /**
     * Cancels deletion of the specified master key.
     *
     * @param request The request object containing master key id.
     */
    public void cancelKeyDeletion(CancelKeyDeletionRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "CancelKeyDeletion");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }
        
        setInternalRequest(internalRequest, writer);
        this.invokeHttpClient(internalRequest, KmsResponse.class);

    }

    /**
     * Returns DescribeKeyResponse containing deletionDate of specified master key and master key id.
     *
     * @param request The request object containing master key id, pendingWindowsInDays.
     *
     * @return DescribeKeyResponse containing deletionDate and master key id.
     */
    public DescribeKeyResponse describeKey(DescribeKeyRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "DescribeKey");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }
        
        setInternalRequest(internalRequest, writer);
        DescribeKeyResponse response = this.invokeHttpClient(internalRequest, DescribeKeyResponse.class);
        return response;
    }

    public GetParametersForImportResponse getParametersForImport(GetParametersForImportRequest request)
            throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "GetParametersForImport");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeStringField(Constants.FIELD_WRAPPINGALGORITHM, request.getWrappingAlgorithm());
            jsonGenerator.writeStringField(Constants.FIELD_WRAPPINGKEYSPEC, request.getWrappingKeySpec());
            jsonGenerator.writeStringField(Constants.FIELD_PUBLICKEYENCODING, request.getPublicKeyEncoding());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);
        GetParametersForImportResponse response = this.invokeHttpClient(internalRequest,
                GetParametersForImportResponse.class);
        return response;
    }

    public KmsResponse importKey(ImportKeyRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "ImportKey");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeStringField(Constants.FIELD_IMPORTTOKEN, request.getImportToken());
            jsonGenerator.writeStringField(Constants.FIELD_ENCRYPTEDKEY, request.getEncryptedKey());
            jsonGenerator.writeStringField(Constants.FIELD_KEYSPEC, request.getKeySpec());
            jsonGenerator.writeStringField(Constants.FIELD_KEYUSAGE, request.getKeyUsage());
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);
        KmsResponse response = this.invokeHttpClient(internalRequest, KmsResponse.class);
        return response;
    }

    public KmsResponse importAsymmetricKey(ImportAsymmetricKeyRequest request) throws Exception {
        checkNotNull(request, Constants.REQUEST_SHOULD_NOT_BE_NULL);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST);
        internalRequest.addParameter(Constants.ACTION, "ImportAsymmetricKey");

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(Constants.FIELD_KEYID, request.getKeyId());
            jsonGenerator.writeStringField(Constants.FIELD_IMPORTTOKEN, request.getImportToken());
            jsonGenerator.writeStringField(Constants.FIELD_ASYMMETRICKEYSPEC, request.getAsymmetricKeySpec());
            jsonGenerator.writeStringField(Constants.FIELD_ASYMMETRICKEYUSAGE, request.getAsymmetricKeyUsage());
            jsonGenerator.writeStringField(Constants.FIELD_ENCRYPTEDKEYENCRYPTIONKEY,
                    request.getEncryptedKeyEncryptionKey());
            if (request.getEncryptedRsaKey() != null) {
                EncryptedRsaKey rsaKey = request.getEncryptedRsaKey();
                jsonGenerator.writeObjectFieldStart(Constants.FIELD_ENCRYPTEDRSAKEY);
                jsonGenerator.writeStringField("publicKeyDer", rsaKey.getPublicKeyDer());
                jsonGenerator.writeStringField("encryptedD", rsaKey.getEncryptedD());
                jsonGenerator.writeStringField("encryptedP", rsaKey.getEncryptedP());
                jsonGenerator.writeStringField("encryptedQ", rsaKey.getEncryptedQ());
                jsonGenerator.writeStringField("encryptedDp", rsaKey.getEncryptedDp());
                jsonGenerator.writeStringField("encryptedDq", rsaKey.getEncryptedDq());
                jsonGenerator.writeStringField("encryptedQinv", rsaKey.getEncryptedQinv());
                jsonGenerator.writeEndObject();
            }
            if (request.getEncryptedSm2Key() != null) {
                throw new BceClientException(Constants.FAIL_TO_SUPPORT, new Exception());
                // EncryptedSm2Key sm2Key = request.getEncryptedSm2Key();
                // jsonGenerator.writeObjectFieldStart(Constants.FIELD_ENCRYPTEDSM2KEY);
                // jsonGenerator.writeStringField("publicKeyDer", sm2Key.getPublicKeyDer());
                // jsonGenerator.writeStringField("encryptedPrivateKey", sm2Key.getEncryptedPrivateKey());
                // jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            throw new BceClientException(Constants.FAIL_TO_GENERATE_JSON, e);
        } finally {
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
        }

        setInternalRequest(internalRequest, writer);
        KmsResponse response = this.invokeHttpClient(internalRequest, KmsResponse.class);
        return response;
    }


    /**
     * set InternalRequest with StringWriter
     *
     * @param internalRequest 
     * @param writer 
     */
    public void setInternalRequest(InternalRequest internalRequest, StringWriter writer) {

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException(Constants.FAIL_TO_GET_UTF8_BYTES, e);
        }
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, Constants.APPLICATION_JSON);
        internalRequest.setContent(RestartableInputStream.wrap(json));
    }

    /**
     * Creates and initializes a new request object for the specified KMS resource. This method is responsible
     * for determining the right way to address resources.
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     *         any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod) {
        InternalRequest request = 
                new InternalRequest(httpMethod, HttpUtils.appendUri(this.getEndpoint()));
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

}


// vim: et tw=100 ts=4 sw=4 cc=120
