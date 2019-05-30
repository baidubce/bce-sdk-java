/*
 * Copyright 2019 Baidu, Inc.
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

package com.baidubce.services.doc;

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
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import com.baidubce.services.doc.model.GetDocumentRequest;
import com.baidubce.services.doc.model.GetDocumentResponse;
import com.baidubce.services.doc.model.GetDocumentDownloadResponse;
import com.baidubce.services.doc.model.GetDocumentDownloadRequest;
import com.baidubce.services.doc.model.CreateDocumentRequest;
import com.baidubce.services.doc.model.CreateDocumentResponse;
import com.baidubce.services.doc.model.CreateDocumentFromBosRequest;
import com.baidubce.services.doc.model.CreateDocumentFromBosResponse;
import com.baidubce.services.doc.model.CreateNotificationRequest;
import com.baidubce.services.doc.model.ListDocumentsResponse;
import com.baidubce.services.doc.model.ListNotificationsResponse;
import com.baidubce.services.doc.model.ListNotificationsRequest;
import com.baidubce.services.doc.model.CreateNotificationResponse;
import com.baidubce.services.doc.model.DeleteDocumentRequest;
import com.baidubce.services.doc.model.DeleteDocumentResponse;
import com.baidubce.services.doc.model.DeleteNotificationRequest;
import com.baidubce.services.doc.model.DeleteNotificationResponse;
import com.baidubce.services.doc.model.ListDocumentsRequest;
import com.baidubce.services.doc.model.ReadDocumentRequest;
import com.baidubce.services.doc.model.ReadDocumentResponse;
import com.baidubce.services.doc.model.GetNotificationRequest;
import com.baidubce.services.doc.model.GetNotificationResponse;
import com.baidubce.services.doc.model.RegisterDocumentResponse;
import com.baidubce.services.doc.model.RegisterDocumentRequest;
import com.baidubce.services.doc.model.PublishDocumentResponse;
import com.baidubce.services.doc.model.PublishDocumentRequest;
import com.baidubce.services.doc.model.DisableReadTokenResponse;
import com.baidubce.services.doc.model.DisableReadTokenRequest;
import com.baidubce.services.doc.model.GetDocumentImagesRequest;
import com.baidubce.services.doc.model.GetDocumentImagesResponse;


import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import java.io.File;

import java.io.UnsupportedEncodingException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;


/**
 * Client for accessing Document Transcoder Services. All service calls made
 * using this client are blocking, and will not return until the service call
 * completes.
 * Created by xuchuan on 2015/4/20.
 */
public class DocClient extends AbstractBceClient {

    /**
     * The version information for Document service APIs as URI prefix.
     */
    private static final String VERSION = "v2";


    /**
     * The common URI prefix for doc services.
     */
    private static final String DOC = "document";

    /**
     * The common URI prefix for notification services.
     */
    private static final String NOTIFICATION = "notification";

    @Override
    public boolean isRegionSupported() {
        return false;
    }



    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static final HttpResponseHandler[] docHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new Document client to invoke service methods on Document Transcoder.
     */
    public DocClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client using the client configuration to access Document Transcoder services.
     *
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to Document services (e.g. proxy settings, retry counts, etc).
     */
    public DocClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, docHandlers);
    }

    /**
     * Create a Document.
     *
     * @param file  The document .
     * @param title  The document title.
     * @param format  The document format.
     *
     * @return A CreateDocumentResponse object containing the information returned by Document.
     */
    public CreateDocumentResponse createDocument(File file, String title, String format) {
        CreateDocumentRequest request = new CreateDocumentRequest();
        request.setFile(file);
        request.setTitle(title);
        request.setFormat(format);
        return this.createDocument(request);
    }

    /**
     * Create a Document.
     *
     * @param file  The document .
     * @param title  The document title.
     *
     * @return A CreateDocumentResponse object containing the information returned by Document.
     */
    public CreateDocumentResponse createDocument(File file, String title) {
        CreateDocumentRequest request = new CreateDocumentRequest();
        String format;
        String filename = file.getName();
        if (filename.lastIndexOf(".") == -1) {
            throw new BceClientException("Cannot get file format from file name:" + filename);
        }
        format = filename.substring(filename.lastIndexOf(".") + 1);
        request.setFile(file);
        request.setTitle(title);
        request.setFormat(format);
        return this.createDocument(request);
    }

    /**
     * Create a Document.
     *
     * @param file  The document .
     * @param title  The document title.
     * @param format  The document format.
     * @param notification  The document notification name.
     *
     * @return A CreateDocumentResponse object containing the information returned by Document.
     */
    public CreateDocumentResponse createDocument(File file, String title, String format, String notification) {
        CreateDocumentRequest request = new CreateDocumentRequest();
        request.setFile(file);
        request.setTitle(title);
        request.setFormat(format);
        request.setNotification(notification);
        return this.createDocument(request);
    }

    /**
     * Create a Document.
     *
     * @param file  The document .
     * @param title  The document title.
     * @param format  The document format.
     * @param notification  The document notification name.
     * @param access  The document access privilege(PUBLIC/PRIVATE).
     *
     * @return A CreateDocumentResponse object containing the information returned by Document.
     */
    public CreateDocumentResponse createDocument(File file, String title, String format,
                                                 String notification, String access) {
        CreateDocumentRequest request = new CreateDocumentRequest();
        request.setFile(file);
        request.setTitle(title);
        request.setFormat(format);
        request.setNotification(notification);
        request.setAccess(access);
        return this.createDocument(request);
    }

    /**
     * Create a Document.
     *
     * @param file  The document .
     * @param title  The document title.
     * @param format  The document format.
     * @param notification  The document notification name.
     * @param access  The document access privilege(PUBLIC/PRIVATE).
     * @param targetType  The document converts type(h5/image).
     *
     * @return A CreateDocumentResponse object containing the information returned by Document.
     */
    public CreateDocumentResponse createDocument(File file, String title, String format,
                                                 String notification, String access, String targetType) {
        CreateDocumentRequest request = new CreateDocumentRequest();
        request.setFile(file);
        request.setTitle(title);
        request.setFormat(format);
        request.setNotification(notification);
        request.setAccess(access);
        request.setTargetType(targetType);
        return this.createDocument(request);
    }

    /**
     * Create a Document.
     *
     * @param request  The request object containing all the parameters to upload a new doc.
     *
     * @return A CreateDocumentResponse object containing the information returned by Document.
     */
    public CreateDocumentResponse createDocument(CreateDocumentRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getFile(), "file should not be null.");
        checkNotNull(request.getTitle(), "title should not be null.");
        checkNotNull(request.getFormat(), "format should not be null.");
        // register document
        RegisterDocumentRequest regRequest = new RegisterDocumentRequest();
        regRequest.setFormat(request.getFormat());
        regRequest.setTitle(request.getTitle());
        regRequest.setNotification(request.getNotification());
        regRequest.setAccess(request.getAccess());
        regRequest.setTargetType(request.getTargetType());

        RegisterDocumentResponse regResponse = registerDocument(regRequest);

        // call bos upload doc
        bosUploadDocument(regResponse.getBucket(), regResponse.getObject(),
                request.getFile(), regResponse.getBosEndpoint());

        // publish document
        PublishDocumentRequest pubRequest = new PublishDocumentRequest();
        pubRequest.setDocumentId(regResponse.getDocumentId());
        publishDocument(pubRequest);

        CreateDocumentResponse response = new CreateDocumentResponse();
        response.setDocumentId(regResponse.getDocumentId());

        return response;
    }

    /**
     * Create a Document.
     *
     * @param bucket  The document bucket.
     * @param object  The document object.
     * @param title  The document title.
     * @param format  The document format.
     * @param notification  The document notification name.
     *
     * @return A CreateDocumentFromBosResponse object containing the information returned by Document.
     */
    public CreateDocumentFromBosResponse createDocumentFromBos(String bucket, String object,
                                                               String title, String format, String notification) {
        CreateDocumentFromBosRequest request = new CreateDocumentFromBosRequest();
        request.setBucket(bucket);
        request.setObject(object);
        request.setTitle(title);
        request.setFormat(format);
        request.setNotification(notification);
        return this.createDocumentFromBos(request);
    }

    /**
     * Create a Document.
     *
     * @param bucket  The document bucket.
     * @param object  The document object.
     * @param title  The document title.
     * @param format  The document format.
     * @param notification  The document notification name.
     * @param access  The document access privilege(PUBLIC/PRIVATE).
     *
     * @return A CreateDocumentFromBosResponse object containing the information returned by Document.
     */
    public CreateDocumentFromBosResponse createDocumentFromBos(String bucket, String object,
                                                               String title, String format,
                                                               String notification, String access) {
        CreateDocumentFromBosRequest request = new CreateDocumentFromBosRequest();
        request.setBucket(bucket);
        request.setObject(object);
        request.setTitle(title);
        request.setFormat(format);
        request.setNotification(notification);
        request.setAccess(access);
        return this.createDocumentFromBos(request);
    }

    /**
     * Create a Document.
     *
     * @param bucket  The document bucket.
     * @param object  The document object.
     * @param title  The document title.
     * @param format  The document format.
     * @param notification  The document notification name.
     * @param access  The document access privilege(PUBLIC/PRIVATE).
     * @param targetType  The document converts type(h5/image).
     *
     * @return A CreateDocumentFromBosResponse object containing the information returned by Document.
     */
    public CreateDocumentFromBosResponse createDocumentFromBos(String bucket, String object,
                                                               String title, String format,
                                                               String notification, String access, String targetType) {
        CreateDocumentFromBosRequest request = new CreateDocumentFromBosRequest();
        request.setBucket(bucket);
        request.setObject(object);
        request.setTitle(title);
        request.setFormat(format);
        request.setNotification(notification);
        request.setAccess(access);
        request.setTargetType(targetType);
        return this.createDocumentFromBos(request);
    }

    /**
     * Create a Document.
     *
     * @param bucket  The document bucket.
     * @param object  The document object.
     * @param title  The document title.
     * @param format  The document format.
     *
     * @return A CreateDocumentFromBosResponse object containing the information returned by Document.
     */
    public CreateDocumentFromBosResponse createDocumentFromBos(String bucket, String object,
                                                               String title, String format) {
        CreateDocumentFromBosRequest request = new CreateDocumentFromBosRequest();
        request.setBucket(bucket);
        request.setObject(object);
        request.setTitle(title);
        request.setFormat(format);
        return this.createDocumentFromBos(request);
    }

    /**
     * Create a Document.
     *
     * @param request  The request object containing all the parameters to upload a new doc.
     *
     * @return A CreateDocumentResponse object containing the information returned by Document.
     */
    public CreateDocumentFromBosResponse createDocumentFromBos(CreateDocumentFromBosRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getBucket(), "bucket should not be null.");
        checkNotNull(request.getObject(), "object should not be null.");
        checkNotNull(request.getTitle(), "title should not be null.");
        checkNotNull(request.getFormat(), "format should not be null.");
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, DOC);
        internalRequest.addParameter("source", "bos");
        String strJson = JsonUtils.toJsonString(request);
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        return invokeHttpClient(internalRequest, CreateDocumentFromBosResponse.class);
    }

    /**
     * publish a Document.
     *
     * @param request  The request object containing register infomation.
     *
     * @return A RegisterDocumentResponse object containing the information returned by Document.
     */
    private RegisterDocumentResponse registerDocument(RegisterDocumentRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, DOC);
        internalRequest.addParameter("register", null);
        String strJson = JsonUtils.toJsonString(request);
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        return invokeHttpClient(internalRequest, RegisterDocumentResponse.class);
    }

    /**
     * publish a Document.
     *
     * @param bucketName  The bucket name response from register.
     * @param objectName  The object name response from register.
     * @param file  The Document file need to be uploaded.
     * @param endpoint  The bos endpoint response from register.
     *
     * @return A PublishDocumentResponse object containing the information returned by Document.
     */
    private void bosUploadDocument(String bucketName, String objectName, File file, String endpoint) {
        BosClientConfiguration config = new BosClientConfiguration(this.config);
        config.setEndpoint(endpoint);
        BosClient bosClient = new BosClient(config);

        PutObjectResponse response = bosClient.putObject(bucketName, objectName, file);
    }

    /**
     * publish a Document.
     *
     * @param request  The request object containing a documentId.
     *
     * @return A PublishDocumentResponse object containing the information returned by Document.
     */
    private PublishDocumentResponse publishDocument(PublishDocumentRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getDocumentId(), "documentId should not be null.");
        InternalRequest internalRequest = this.createRequest(HttpMethodName.PUT, request, DOC, request.getDocumentId());
        internalRequest.addParameter("publish", null);
        // need to set content-length, otherwise auth will failed
        // cause HTTP will set Content-Length auto when send http request
        internalRequest.addHeader(Headers.CONTENT_LENGTH, "0");
        PublishDocumentResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, PublishDocumentResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }

    /**
     * get a Document.
     *
     * @param documentId  The document id.
     *
     * @return A GetDocumentResponse object containing the information returned by Document.
     */
    public GetDocumentResponse getDocument(String documentId) {
        GetDocumentRequest request = new GetDocumentRequest();
        request.setDocumentId(documentId);
        return this.getDocument(request);
    }

    /**
     * get a Document.
     *
     * @param request  The request object containing a docId.
     *
     * @return A GetDocumentResponse object containing the information returned by Document.
     */
    public GetDocumentResponse getDocument(GetDocumentRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getDocumentId(), "documentId should not be null.");
        InternalRequest internalRequest = this.createRequest(HttpMethodName.GET, request, DOC, request.getDocumentId());
        GetDocumentResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, GetDocumentResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }

    /**
     * get a Document Image list if Converted to image.
     * Make Sure the Document convert type is image, otherwise will throw BceServiceException
     *
     * @param documentId the documentId need to get.
     *
     * @return A GetDocumentImageResponse object containing the information returned by Document.
     */
    public GetDocumentImagesResponse getDocumentImages(String documentId) {
        checkNotNull(documentId, "documentId should not be null.");
        GetDocumentImagesRequest request = new GetDocumentImagesRequest();
        request.setDocumentId(documentId);
        InternalRequest internalRequest = this.createRequest(HttpMethodName.GET, request, DOC, request.getDocumentId());
        internalRequest.addParameter("getImages", null);
        GetDocumentImagesResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, GetDocumentImagesResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }


    /**
     * list all Document.
     *
     *
     * @return A ListDocumentsResponse object containing the information returned by Document.
     */
    public ListDocumentsResponse listDocuments() {
        ListDocumentsRequest request = new ListDocumentsRequest();
        InternalRequest internalRequest = this.createRequest(HttpMethodName.GET, request, DOC);
        ListDocumentsResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, ListDocumentsResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }

    /**
     * list all Document by status.
     *
     * @param status the status
     * @return A ListDocumentsResponse object containing the information returned by Document.
     */
    public ListDocumentsResponse listDocuments(String status) {
        ListDocumentsRequest request = new ListDocumentsRequest();
        InternalRequest internalRequest = this.createRequest(HttpMethodName.GET, request, DOC);
        internalRequest.addParameter("status", status);
        ListDocumentsResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, ListDocumentsResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }

    /**
     * list all Document by status, marker.
     *@param status document status
     *@param marker the marker, can be ""
     *@param maxSize the maxSize, should be (0, 200]
     *
     * @return A ListDocumentsResponse object containing the information returned by Document.
     */
    public ListDocumentsResponse listDocuments(String status, String marker, int maxSize) {
        ListDocumentsRequest request = new ListDocumentsRequest();
        InternalRequest internalRequest = this.createRequest(HttpMethodName.GET, request, DOC);
        internalRequest.addParameter("status", status);
        if (marker != null) {
            internalRequest.addParameter("marker", marker);
        }
        internalRequest.addParameter("maxSize", String.valueOf(maxSize));

        ListDocumentsResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, ListDocumentsResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }


    /**
     * delete a Document.
     *
     * @param documentId  The document id.
     *
     * @return A DeleteDocumentResponse object containing the information returned by Document.
     */
    public DeleteDocumentResponse deleteDocument(String documentId) {
        DeleteDocumentRequest request = new DeleteDocumentRequest();
        request.setDocumentId(documentId);
        return this.deleteDocument(request);
    }

    /**
     * delete a Document.
     *
     * @param request  The request object containing a docId.
     *
     * @return A DeleteDocumentResponse object containing the information returned by Document.
     */
    public DeleteDocumentResponse deleteDocument(DeleteDocumentRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getDocumentId(), "documentId should not be null.");
        InternalRequest internalRequest = this.createRequest(
                HttpMethodName.DELETE, request, DOC, request.getDocumentId());
        DeleteDocumentResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, DeleteDocumentResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }

    /**
     * get a Document Download link.
     *
     * @param documentId the documentId need to download.
     *
     * @return A GetDocumentDownloadResponse object containing the information returned by Document.
     */
    public GetDocumentDownloadResponse getDocumentDownload(String documentId) {
        checkNotNull(documentId, "documentId should not be null.");
        GetDocumentDownloadRequest request = new GetDocumentDownloadRequest();
        request.setDocumentId(documentId);
        InternalRequest internalRequest = this.createRequest(HttpMethodName.GET, request, DOC, request.getDocumentId());
        internalRequest.addParameter("download", null);
        GetDocumentDownloadResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, GetDocumentDownloadResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }

    /**
     * get a Document Download link.
     *
     * @param documentId the documentId need to download.
     * @param expireInSeconds the Download link expire time in second. -1 set never expire.
     *
     * @return A GetDocumentDownloadResponse object containing the information returned by Document.
     */
    public GetDocumentDownloadResponse getDocumentDownload(String documentId, long expireInSeconds) {
        checkNotNull(documentId, "documentId should not be null.");
        GetDocumentDownloadRequest request = new GetDocumentDownloadRequest();
        request.setDocumentId(documentId);
        InternalRequest internalRequest = this.createRequest(HttpMethodName.GET, request, DOC, request.getDocumentId());
        internalRequest.addParameter("download", null);
        internalRequest.addParameter("expireInSeconds", String.valueOf(expireInSeconds));
        GetDocumentDownloadResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, GetDocumentDownloadResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }


    /**
     * read a Document, get document reader infomation.
     *
     * @param documentId  The document id.
     *
     * @return A ReadDocumentResponse object containing the information returned by Document.
     */
    public ReadDocumentResponse readDocument(String documentId) {
        ReadDocumentRequest request = new ReadDocumentRequest();
        request.setDocumentId(documentId);
        return this.readDocument(request);
    }

    /**
     * read a Document, get document reader infomation.
     *
     * @param documentId  The document id.
     * @param expireInSeconds The expire time
     *
     * @return A ReadDocumentResponse object containing the information returned by Document.
     */
    public ReadDocumentResponse readDocument(String documentId, long expireInSeconds) {
        ReadDocumentRequest request = new ReadDocumentRequest();
        request.setDocumentId(documentId);
        request.setExpireInSeconds(expireInSeconds);
        return this.readDocument(request);
    }

    /**
     * read a Document, get document reader infomation.
     *
     * @param request  The request object containing a documentId.
     *
     * @return A ReadDocumentResponse object containing the information returned by Document.
     */
    public ReadDocumentResponse readDocument(ReadDocumentRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getDocumentId(), "documentId should not be null.");
        InternalRequest internalRequest = this.createRequest(
                HttpMethodName.GET, request, DOC, request.getDocumentId());
        internalRequest.addParameter("read", null);
        internalRequest.addParameter("expireInSeconds", String.valueOf(request.getExpireInSeconds()));
        ReadDocumentResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, ReadDocumentResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }

    /**
     * Disable read token.
     *
     * @param documentId  The document id.
     * @param token The token need to disable
     *
     * @return A DisableReadTokenResponse object.
     */
    public DisableReadTokenResponse disableReadToken(String documentId, String token) {
        DisableReadTokenRequest request = new DisableReadTokenRequest();
        request.setDocumentId(documentId);
        request.setToken(token);
        return this.disableReadToken(request);
    }

    /**
     * Disable read token.
     *
     * @param request  The request object containing a documentId.
     *
     * @return A DisableReadTokenResponse object.
     */
    public DisableReadTokenResponse disableReadToken(DisableReadTokenRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getDocumentId(), "documentId should not be null.");
        checkNotNull(request.getToken(), "token should not be null.");
        InternalRequest internalRequest = this.createRequest(
                HttpMethodName.PUT, request, DOC, request.getDocumentId());
        internalRequest.addParameter("disableReadToken", null);
        internalRequest.addParameter("token", request.getToken());
        DisableReadTokenResponse response;
        try {
            response = this.invokeHttpClient(internalRequest, DisableReadTokenResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                // ignore exception
            }
        }

        return response;
    }

    /**
     * List all your doc notifications.
     *
     * @return The list of all your doc notifications
     */
    public ListNotificationsResponse listNotifications() {
        ListNotificationsRequest request = new ListNotificationsRequest();
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, NOTIFICATION);
        return invokeHttpClient(internalRequest, ListNotificationsResponse.class);
    }

    /**
     * Delete your doc notification by doc notification name.
     *
     * @param name  doc notification name.
     * @return the response
     */
    public DeleteNotificationResponse deleteNotification(String name) {
        DeleteNotificationRequest request = new DeleteNotificationRequest();
        request.setName(name);
        return deleteNotification(request);
    }

    /**
     * Delete your doc notification by doc notification name.
     *
     * @param request The request object containing all parameters for deleting dco notification.
     * @return the response
     */
    public DeleteNotificationResponse deleteNotification(DeleteNotificationRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getName(), "The parameter name should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE, request, NOTIFICATION, request
                .getName());
        return invokeHttpClient(internalRequest, DeleteNotificationResponse.class);
    }

    /**
     * Get your doc notification by doc notification name.
     *
     * @param name  doc notification name.
     *
     * @return Your doc notification.
     */
    public GetNotificationResponse getNotification(String name) {
        GetNotificationRequest request = new GetNotificationRequest();
        request.setName(name);
        return getNotification(request);
    }

    /**
     * Get your doc notification by doc notification name.
     *
     * @param request The request object containing all parameters for getting doc notification.
     *
     * @return Your doc notification.
     */
    public GetNotificationResponse getNotification(GetNotificationRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getName(), "The parameter name should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, NOTIFICATION,
                request.getName());
        return invokeHttpClient(internalRequest, GetNotificationResponse.class);
    }

    /**
     * Create a doc notification in the doc stream service.
     *
     * @param name  The name of notification.
     * @param endpoint The address to receive notification message.
     * @return the response
     */
    public CreateNotificationResponse createNotification(String name, String endpoint) {
        CreateNotificationRequest request = new CreateNotificationRequest();
        request.withName(name).withEndpoint(endpoint);
        return createNotification(request);
    }

    /**
     * Create a doc notification in the doc stream service.
     *
     * @param request The request object containing all options for creating doc notification.
     * @return the response
     */
    public CreateNotificationResponse createNotification(CreateNotificationRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getName(),
                "The parameter name should NOT be null or empty string.");
        checkStringNotEmpty(request.getEndpoint(),
                "The parameter endpoint should NOT be null or empty string.");

        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, NOTIFICATION);
        String strJson = JsonUtils.toJsonString(request);
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        return invokeHttpClient(internalRequest, CreateNotificationResponse.class);
    }


    /**
     * Creates and initializes a new request object for the specified resource.
     * This method is responsible for determining HTTP method, URI path, 
     * credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.  
     * </p>
     * @param httpMethod
     *            The HTTP method to use when sending the request.
     * @param request
     *            The original request, as created by the user.
     * @param pathVariables
     *            The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path,
     *         ready for callers to populate any additional headers or
     *         parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, AbstractBceRequest request, String...pathVariables) {

        // build URL paths
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(VERSION);

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(getEndpoint(), pathComponents.toArray(new String[pathComponents.size()]));

        // get a InternalRequest instance and set headers
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        internalRequest.setCredentials(request.getRequestCredentials());

        return internalRequest;
    }

}
