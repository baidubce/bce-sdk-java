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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.iothisk.model.BatchCreateClientCertRequest;
import com.baidubce.services.iothisk.model.BatchCreateClientCertResponse;
import com.baidubce.services.iothisk.model.CreateCertGroupRequest;
import com.baidubce.services.iothisk.model.CreateCertGroupResponse;
import com.baidubce.services.iothisk.model.CreateRootCACertRequest;
import com.baidubce.services.iothisk.model.CreateRootCACertResponse;
import com.baidubce.services.iothisk.model.CreateSubCertRequest;
import com.baidubce.services.iothisk.model.CreateSubCertResponse;
import com.baidubce.services.iothisk.model.DefaultIotPkiManageRequest;
import com.baidubce.services.iothisk.model.DefaultIotPkiManageResponse;
import com.baidubce.services.iothisk.model.DownloadCrlResponse;
import com.baidubce.services.iothisk.model.GetBatchCreateStatusResponse;
import com.baidubce.services.iothisk.model.GetCertGroupResponse;
import com.baidubce.services.iothisk.model.GetCertStatusRequest;
import com.baidubce.services.iothisk.model.GetCertStatusResponse;
import com.baidubce.services.iothisk.model.GetOcspResponse;
import com.baidubce.services.iothisk.model.GetRootCACertResponse;
import com.baidubce.services.iothisk.model.GetSubCertResponse;
import com.baidubce.services.iothisk.model.IotPkiManageResponse;
import com.baidubce.services.iothisk.model.QueryClientCertResponse;
import com.baidubce.services.iothisk.model.QueryServerCertResponse;
import com.baidubce.services.iothisk.model.RenewSubCertRequest;
import com.baidubce.services.iothisk.model.RenewSubCertResponse;

/**
 * Provides the client for accessing the iot pki service.
 */
public class IotPkiManageClient extends AbstractIotHiskBceClient {

    /**
     * Constructs a new pki client using the client configuration to access hisk pki.
     *
     * @param config The bcc client configuration options controlling how this client
     *               connects to bcc (e.g. proxy settings, retry counts, etc).
     */
    public IotPkiManageClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(IotPkiManageConstants.ENDPOINT) : config, HANDLERS);
    }

    /**
     * Create a root cert.
     *
     * @param request The request object containing all options for creating a root cert.
     * @param clientToken A random string to make request idempotent.
     * @return The created root cert ID.
     */
    public CreateRootCACertResponse createRootCACert(CreateRootCACertRequest request, String clientToken) {
        checkNotNull(request, IotPkiManageConstants.NULL_REQUEST);
        checkClientToken(clientToken);
        InternalRequest internalRequest =
                createIotPkiManageRequest(request, HttpMethodName.POST, IotPkiManageConstants.ROOT_CERT);
        internalRequest.addParameter(IotPkiManageConstants.CLIENT_TOKEN, clientToken);
        return this.invokeHttpClient(internalRequest, CreateRootCACertResponse.class);
    }

    /**
     * Delete a root cert.
     *
     * @param certId The cert ID of the root cert which will be deleted.
     */
    public void deleteRootCACert(String certId) {
        checkCertId(certId);
        InternalRequest internalRequest = createIotPkiManageRequest(
                new DefaultIotPkiManageRequest(), HttpMethodName.DELETE, IotPkiManageConstants.ROOT_CERT, certId);
        this.invokeHttpClient(internalRequest, DefaultIotPkiManageResponse.class);
    }

    /**
     * Get a root cert.
     *
     * @param certId The cert ID of the root cert which will be got.
     * @return Crl download url and root cert download url.
     */
    public GetRootCACertResponse getRootCACert(String certId) {
        checkCertId(certId);
        InternalRequest internalRequest = createIotPkiManageRequest(
                new DefaultIotPkiManageRequest(), HttpMethodName.GET, IotPkiManageConstants.ROOT_CERT, certId);
        return this.invokeHttpClient(internalRequest, GetRootCACertResponse.class);
    }

    /**
     * Create a cert group.
     *
     * @param request The request object containing all options for creating a cert group.
     * @param clientToken A random string to make request idempotent.
     * @return The created cert group ID.
     */
    public CreateCertGroupResponse createCertGroup(CreateCertGroupRequest request, String clientToken) {
        checkNotNull(request, IotPkiManageConstants.NULL_REQUEST);
        checkClientToken(clientToken);
        InternalRequest internalRequest = createIotPkiManageRequest(
                request, HttpMethodName.POST, IotPkiManageConstants.CERT_GROUP);
        internalRequest.addParameter(IotPkiManageConstants.CLIENT_TOKEN, clientToken);
        return this.invokeHttpClient(internalRequest, CreateCertGroupResponse.class);
    }

    /**
     * Delete a cert group.
     *
     * @param groupId The group ID of the cert group which will be deleted.
     */
    public void deleteCertGroup(String groupId) {
        checkCertId(groupId);
        InternalRequest internalRequest = createIotPkiManageRequest(
                new DefaultIotPkiManageRequest(), HttpMethodName.DELETE, IotPkiManageConstants.CERT_GROUP, groupId);
        this.invokeHttpClient(internalRequest, DefaultIotPkiManageResponse.class);
    }

    /**
     * Get a cert group.
     *
     * @param groupId The group ID of the cert group which will be got.
     * @return Root cert ID and sub cert' ID of this group.
     */
    public GetCertGroupResponse getCertGroup(String groupId) {
        checkCertId(groupId);
        InternalRequest internalRequest = createIotPkiManageRequest(
                new DefaultIotPkiManageRequest(), HttpMethodName.GET, IotPkiManageConstants.CERT_GROUP, groupId);
        return this.invokeHttpClient(internalRequest, GetCertGroupResponse.class);
    }

    /**
     * Create a server cert.
     *
     * @param request The request object containing all options for creating a server cert.
     * @param clientToken A random string to make request idempotent.
     * @return The created server cert ID.
     */
    public CreateSubCertResponse createServerCert(CreateSubCertRequest request, String clientToken) {
        return createSubCert(request, clientToken, CertType.SERVER);
    }

    /**
     * Delete a server cert.
     *
     * @param serverCertId The server cert ID of the cert which will be deleted.
     */
    public void deleteServerCert(String serverCertId) {
        deleteSubCert(serverCertId, CertType.SERVER);
    }

    /**
     * Get a server cert.
     *
     * @param serverCertId The server cert ID of the cert which will be got.
     * @return Root cert ID, cert group ID and cert download url of this cert.
     */
    public GetSubCertResponse getServerCert(String serverCertId) {
        return getSubCert(serverCertId, CertType.SERVER);
    }

    /**
     * Query server certs.
     *
     * @param rootCACertId The root cert ID of server certs which will be queried.
     * @param groupId The cert group ID of server certs which will be queried.
     * @return Server cert list of the query.
     */
    public QueryServerCertResponse queryServerCerts(String rootCACertId, String groupId) {
        return querySubCerts(rootCACertId, groupId, CertType.SERVER, QueryServerCertResponse.class);
    }

    /**
     * Renew a server cert.
     *
     * @param request The request object containing all options for renewing a server cert.
     * @param serverCertId The server cert ID of the cert which will be renewed.
     * @param clientToken A random string to make request idempotent.
     * @return Download url of the new server cert.
     */
    public RenewSubCertResponse renewServerCert(RenewSubCertRequest request, String serverCertId, String clientToken) {
        return renewSubCert(request, serverCertId, clientToken, CertType.SERVER);
    }

    /**
     * Create a client cert.
     *
     * @param request The request object containing all options for creating a client cert.
     * @param clientToken A random string to make request idempotent.
     * @return The created client cert ID.
     */
    public CreateSubCertResponse createClientCert(CreateSubCertRequest request, String clientToken) {
        return createSubCert(request, clientToken, CertType.CLIENT);
    }

    /**
     * Delete a client cert.
     *
     * @param clientCertId The client cert ID of the cert which will be deleted.
     */
    public void deleteClientCert(String clientCertId) {
        deleteSubCert(clientCertId, CertType.CLIENT);
    }

    /**
     * Get a client cert.
     *
     * @param clientCertId The client cert ID of the cert which will be got.
     * @return Root cert ID, cert group ID and cert download url of this cert.
     */
    public GetSubCertResponse getClientCert(String clientCertId) {
        return getSubCert(clientCertId, CertType.CLIENT);
    }

    /**
     * Query client certs.
     *
     * @param rootCACertId The root cert ID of client certs which will be queried.
     * @param groupId The cert group ID of client certs which will be queried.
     * @return Client cert list of the query.
     */
    public QueryClientCertResponse queryClientCerts(String rootCACertId, String groupId) {
        return querySubCerts(rootCACertId, groupId, CertType.CLIENT, QueryClientCertResponse.class);
    }

    /**
     * Renew a client cert.
     *
     * @param request The request object containing all options for renewing a client cert.
     * @param clientCertId The client cert ID of the cert which will be renewed.
     * @param clientToken A random string to make request idempotent.
     * @return Download url of the new client cert.
     */
    public RenewSubCertResponse renewClientCert(RenewSubCertRequest request, String clientCertId, String clientToken) {
        return renewSubCert(request, clientCertId, clientToken, CertType.CLIENT);
    }

    /**
     * Batch create client certs.
     *
     * @param request The request object containing all options for creating client certs.
     * @param clientToken A random string to make request idempotent.
     * @return The create batch ID.
     */
    public BatchCreateClientCertResponse batchCreateClientCert(
            BatchCreateClientCertRequest request, String clientToken) {
        checkNotNull(request, IotPkiManageConstants.NULL_REQUEST);
        checkClientToken(clientToken);

        InternalRequest internalRequest = createIotPkiManageRequest(
                request, HttpMethodName.POST, IotPkiManageConstants.CLIENT_CERT, IotPkiManageConstants.JOB);
        internalRequest.addParameter(IotPkiManageConstants.CLIENT_TOKEN, clientToken);
        return this.invokeHttpClient(internalRequest, BatchCreateClientCertResponse.class);
    }

    /**
     * Get batch create status
     *
     * @param jobId The create batch ID.
     * @return Status of the create batch.
     */
    public GetBatchCreateStatusResponse getBatchCreateStatus(String jobId) {
        checkJobId(jobId);

        InternalRequest internalRequest = createIotPkiManageRequest(new DefaultIotPkiManageRequest(),
                HttpMethodName.GET, IotPkiManageConstants.CLIENT_CERT, IotPkiManageConstants.JOB, jobId);
        return this.invokeHttpClient(internalRequest, GetBatchCreateStatusResponse.class);
    }

    /**
     * Download client cert.
     *
     * @param clientCertId The client cert ID which will be downloaded.
     * @return Cert content string, encoded by base64, using PEM format.
     */
    public String downloadClientCert(String clientCertId) {
        return downloadCert(clientCertId, CertType.CLIENT);
    }

    /**
     * Download server cert.
     *
     * @param serverCertId The server cert ID which will be downloaded.
     * @return Cert content string, encoded by base64, using PEM format.
     */
    public String downloadServerCert(String serverCertId) {
        return downloadCert(serverCertId, CertType.SERVER);
    }

    /**
     * Download root cert.
     *
     * @param rootCertId The root cert ID which will be downloaded.
     * @return Cert content string, encoded by base64, using PEM format.
     */
    public String downloadRootCert(String rootCertId) {
        return downloadCert(rootCertId, CertType.ROOT);
    }

    /**
     * Download batch create certs.
     *
     * @param jobId The create batch ID which will be downloaded.
     * @return null if create failed or processing,
     *         or a map which maps device ID and cert content.
     */
    public Map<String, String> downloadBatchCreateCerts(String jobId) {
        checkJobId(jobId);

        GetBatchCreateStatusResponse response = getBatchCreateStatus(jobId);
        if (response.isSucceed() || response.isPartialSucceed()) {
            try {
                return unzipCert(IOUtils.toByteArray(new URL(response.getDownloadUrl())));
            } catch (IOException e) {
                throw new RuntimeException(IotPkiManageConstants.DOWNLOAD_CERT_FAILED);
            }
        } else {
            return null;
        }
    }

    /**
     * Get cert status.
     *
     * @param request The request object containing all options for creating client certs.
     * @return Cert status of request.
     */
    public GetCertStatusResponse getCertStatus(GetCertStatusRequest request) {
        checkNotNull(request, IotPkiManageConstants.NULL_REQUEST);

        InternalRequest internalRequest = createIotPkiManageRequest(
                request, HttpMethodName.PUT, IotPkiManageConstants.CERT, IotPkiManageConstants.GET_STATUS);
        return this.invokeHttpClient(internalRequest, GetCertStatusResponse.class);
    }

    /**
     * Download root cert.
     *
     * @param issuerDN The root cert DN, can be found in root cert.
     * @return Crl content string, encoded by base64, using PEM format.
     */
    public DownloadCrlResponse downloadCrl(String issuerDN) {
        checkIssuerDN(issuerDN);

        InternalRequest internalRequest = createIotPkiManageRequest(
                new DefaultIotPkiManageRequest(), HttpMethodName.GET, IotPkiManageConstants.CRL);
        internalRequest.setParameters(IotPkiManageConstants.CRL_PARAMS);
        internalRequest.addParameter(IotPkiManageConstants.ISSUER, issuerDN);
        return this.invokeHttpClient(internalRequest, DownloadCrlResponse.class);
    }

    /**
     * Standard ocsp query use HTTP GET method.
     *
     * @param ocspRequest Standard ocsp request.
     * @return Standard ocsp response.
     */
    public GetOcspResponse getOcspResponse(byte[] ocspRequest) {
        checkNotNull(ocspRequest, IotPkiManageConstants.NULL_REQUEST);

        String encodedOcspRequest = encodeOcspReqeust(ocspRequest);
        InternalRequest internalRequest = createIotPkiManageRequest(
                new DefaultIotPkiManageRequest(), HttpMethodName.GET, IotPkiManageConstants.OCSP, encodedOcspRequest);
        return this.invokeHttpClient(internalRequest, GetOcspResponse.class);
    }

    private InternalRequest createIotPkiManageRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                                  String subPath, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.addAll(Arrays.asList(IotPkiManageConstants.VERSION, IotPkiManageConstants.PKI, subPath));
        return createRequest(bceRequest, httpMethod, null, path, pathVariables);
    }

    private void checkClientToken(String clientToken) {
        checkArgument(StringUtils.isNotEmpty(clientToken), IotPkiManageConstants.EMPTY_CLIENT_TOKEN);
        checkArgument(StringUtils.length(clientToken) <= IotPkiManageConstants.MAX_CLIENT_TOKEN_LENGTH,
                IotPkiManageConstants.TOO_LONG_CLIENT_TOKEN);
    }

    private void checkCertId(String certId) {
        checkArgument(StringUtils.length(certId) == IotPkiManageConstants.CERT_ID_LENGTH,
                IotPkiManageConstants.INVALID_CERT_ID);
    }

    private void checkIssuerDN(String issuerDN) {
        checkArgument(StringUtils.isNotEmpty(issuerDN), IotPkiManageConstants.INVALID_ISSUER_DN);
    }

    private void checkJobId(String jobId) {
        checkArgument(StringUtils.length(jobId) == IotPkiManageConstants.JOB_ID_LENGTH,
                IotPkiManageConstants.INVALID_JOB_ID);
    }

    private void checkAddressNotEmpty(List<String> address) {
        checkArgument(address != null && !address.isEmpty(), IotPkiManageConstants.EMPTY_ADDRESS);
    }

    private CreateSubCertResponse createSubCert(CreateSubCertRequest request, String clientToken, CertType certType) {
        checkNotNull(request, IotPkiManageConstants.NULL_REQUEST);
        checkClientToken(clientToken);
        if (certType == CertType.SERVER) {
            checkAddressNotEmpty(request.getAddress());
        }

        InternalRequest internalRequest =
                createIotPkiManageRequest(request, HttpMethodName.POST, getSubCertPathByCertType(certType));
        internalRequest.addParameter(IotPkiManageConstants.CLIENT_TOKEN, clientToken);
        return this.invokeHttpClient(internalRequest, CreateSubCertResponse.class);
    }

    private void deleteSubCert(String subCertId, CertType certType) {
        checkCertId(subCertId);
        InternalRequest internalRequest = createIotPkiManageRequest(new DefaultIotPkiManageRequest(),
                HttpMethodName.DELETE, getSubCertPathByCertType(certType), subCertId);
        this.invokeHttpClient(internalRequest, DefaultIotPkiManageResponse.class);
    }

    private GetSubCertResponse getSubCert(String subCertId, CertType certType) {
        checkCertId(subCertId);
        InternalRequest internalRequest = createIotPkiManageRequest(new DefaultIotPkiManageRequest(),
                HttpMethodName.GET, getSubCertPathByCertType(certType), subCertId);
        return this.invokeHttpClient(internalRequest, GetSubCertResponse.class);
    }

    private <T extends IotPkiManageResponse> T querySubCerts(
            String rootCACertId, String groupId, CertType certType, Class<T> responseClass) {
        checkArgument(rootCACertId != null || groupId != null);

        InternalRequest internalRequest = createIotPkiManageRequest(new DefaultIotPkiManageRequest(),
                HttpMethodName.GET, getSubCertPathByCertType(certType), IotPkiManageConstants.QUERY);
        if (rootCACertId != null) {
            internalRequest.addParameter(IotPkiManageConstants.ROOT_CERT_ID, rootCACertId);
        }
        if (groupId != null) {
            internalRequest.addParameter(IotPkiManageConstants.GROUP_ID, groupId);
        }
        return this.invokeHttpClient(internalRequest, responseClass);
    }

    private RenewSubCertResponse renewSubCert(
            RenewSubCertRequest request, String subCertId, String clientToken, CertType certType) {
        checkNotNull(request, IotPkiManageConstants.NULL_REQUEST);
        checkCertId(subCertId);
        checkClientToken(clientToken);
        if (certType == CertType.SERVER) {
            checkAddressNotEmpty(request.getNewAddress());
        }

        InternalRequest internalRequest = createIotPkiManageRequest(
                request, HttpMethodName.PUT, getSubCertPathByCertType(certType),
                subCertId, IotPkiManageConstants.RENEW);
        internalRequest.addParameter(IotPkiManageConstants.CLIENT_TOKEN, clientToken);
        return this.invokeHttpClient(internalRequest, RenewSubCertResponse.class);
    }

    private String downloadCert(String certId, CertType certType) {
        checkCertId(certId);

        switch (certType) {
            case SERVER:
            case CLIENT:
                return downloadCert(getSubCert(certId, certType).getDownloadUrl());
            case ROOT:
                return downloadCert(getRootCACert(certId).getDownloadUrl());
            default:
                throw new RuntimeException(IotPkiManageConstants.INVALID_CERT_TYPE);
        }
    }

    private String downloadCert(String downloadUrl) {
        try {
            return IOUtils.toString(new URL(downloadUrl));
        } catch (IOException e) {
            throw new RuntimeException(IotPkiManageConstants.DOWNLOAD_CERT_FAILED);
        }
    }

    private String getSubCertPathByCertType(CertType certType) {
        return certType == CertType.SERVER ? IotPkiManageConstants.SEVER_CERT : IotPkiManageConstants.CLIENT_CERT;
    }

    private Map<String, String> unzipCert(byte[] certZip) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(certZip);
        ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);
        Map<String, String> deviceIdCertMap = new HashMap<String, String>();
        ZipEntry entry = zipInputStream.getNextEntry();
        byte[] buffer = new byte[1024];
        while (entry != null) {
            String name = entry.getName();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len;
            while ((len = zipInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            zipInputStream.closeEntry();
            deviceIdCertMap.put(name, new String(byteArrayOutputStream.toByteArray()));
            byteArrayOutputStream.close();

            entry = zipInputStream.getNextEntry();
        }
        zipInputStream.close();
        return deviceIdCertMap;
    }

    private String encodeOcspReqeust(byte[] ocspRequest) {
        try {
            return URLEncoder.encode(Base64.encodeBase64String(ocspRequest), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(IotPkiManageConstants.INVALID_OCSP_REQUEST);
        }
    }

    private enum CertType {
        SERVER,
        CLIENT,
        ROOT
    }

}
