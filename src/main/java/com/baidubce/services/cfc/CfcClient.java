/*
 * Copyright  2019 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.cfc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

import com.baidubce.http.HttpMethodName;
import com.baidubce.http.Headers;
import com.baidubce.services.cfc.model.CreateFunctionRequest;
import com.baidubce.services.cfc.model.CreateFunctionResponse;
import com.baidubce.services.cfc.model.ListFunctionsRequest;
import com.baidubce.services.cfc.model.ListFunctionsResponse;
import com.baidubce.services.cfc.model.GetFunctionRequest;
import com.baidubce.services.cfc.model.GetFunctionResponse;
import com.baidubce.services.cfc.model.DeleteFunctionRequest;
import com.baidubce.services.cfc.model.DeleteFunctionResponse;
import com.baidubce.services.cfc.model.UpdateFunctionCodeRequest;
import com.baidubce.services.cfc.model.UpdateFunctionCodeResponse;
import com.baidubce.services.cfc.model.GetFunctionConfigurationResponse;
import com.baidubce.services.cfc.model.GetFunctionConfigurationRequest;
import com.baidubce.services.cfc.model.UpdateFunctionConfigurationRequest;
import com.baidubce.services.cfc.model.UpdateFunctionConfigurationResponse;
import com.baidubce.services.cfc.model.ListVersionsByFunctionRequest;
import com.baidubce.services.cfc.model.ListVersionsByFunctionResponse;
import com.baidubce.services.cfc.model.PublishVersionRequest;
import com.baidubce.services.cfc.model.PublishVersionResponse;
import com.baidubce.services.cfc.model.ListAliasesRequest;
import com.baidubce.services.cfc.model.ListAliasesResponse;
import com.baidubce.services.cfc.model.CreateAliasRequest;
import com.baidubce.services.cfc.model.CreateAliasResponse;
import com.baidubce.services.cfc.model.GetAliasResponse;
import com.baidubce.services.cfc.model.GetAliasRequest;
import com.baidubce.services.cfc.model.UpdateAliasResponse;
import com.baidubce.services.cfc.model.UpdateAliasRequest;
import com.baidubce.services.cfc.model.DeleteAliasResponse;
import com.baidubce.services.cfc.model.DeleteAliasRequest;
import com.baidubce.services.cfc.model.CreateTriggerResponse;
import com.baidubce.services.cfc.model.CreateTriggerRequest;
import com.baidubce.services.cfc.model.ListTriggersResponse;
import com.baidubce.services.cfc.model.ListTriggersRequest;
import com.baidubce.services.cfc.model.UpdateTriggerResponse;
import com.baidubce.services.cfc.model.UpdateTriggerRequest;
import com.baidubce.services.cfc.model.DeleteTriggerRequest;
import com.baidubce.services.cfc.model.DeleteTriggerResponse;
import com.baidubce.services.cfc.model.InvokeResponse;
import com.baidubce.services.cfc.model.InvokeRequest;
import com.baidubce.services.cfc.model.GetInvokeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import static com.baidubce.util.Validate.checkNotNull;

/**
 * Provides the client for accessing the Cloud Function Compute Service.
 */
public class CfcClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CfcClient.class);

    /**
     * The default version for CFC service calls.
     */
    private static final String VERSION = "v1";

    /**
     * The path param for CFC service calls.
     */
    private static final String FUNCTIONS = "functions";

    /**
     * The path param for CFC service calls.
     */
    private static final String CODE = "code";

    /**
     * The path param for CFC service calls.
     */
    private static final String CONFIGURATION = "configuration";

    /**
     * The path param for CFC service calls.
     */
    private static final String VERSIONS = "versions";

    /**
     * The path param for CFC service calls.
     */
    private static final String ALIASES = "aliases";

    /**
     * The path param for CFC service calls.
     */
    private static final String RELATION = "relation";

    /**
     * The path param for CFC service calls.
     */
    private static final String INVOCATIONS = "invocations";

    /**
     * Error message
     */
    private static final String ERRORMSG = "The request should not be null.";

    /**
     * encoding type
     */
    private static final String ENCODINGTYPE = "utf-8";

    /**
     * encoding type error
     */
    private static final String ENCODINGERR = "utf-8 encoding not supported";

    /**
     * content type
     */
    private static final String CONTENTTYPE = "application/json; charset=utf-8";

    /**
     * The default host to sign for CFC service calls.
     */
    private static final String[] HEADERS_TO_SIGN = {"host"};

    /**
     * The default invoke max timeout for creating new connections.
     */
    private static final int INVOKE_CONNECTION_TIMEOUT_IN_MILLIS = 310 * 1000;

    /**
     * The default invoke max timeout for reading from a connected socket.
     */
    private static final int INVOKE_SOCKET_TIMEOUT_IN_MILLIS = 310 * 1000;

    /**
     * Responsible for handling httpResponses from all CFC service calls.
     */
    private static HttpResponseHandler[] cfcHandlers = new HttpResponseHandler[]{
            new BceErrorResponseHandler(),
            new BceMetadataResponseHandler(),
            new CfcResponseHandler(),
            new BceJsonResponseHandler(),
    };

    /**
     * Constructs a new client to invoke service methods on CFC
     */
    public CfcClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client using the client configuration to access CFC services.
     *
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to Document services (e.g. proxy settings, retry counts, etc).
     */
    public CfcClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, cfcHandlers);
    }

    /**
     * Create a new function
     *
     * @param request The request containing user-defined function information
     * @return Result of the createFunction operation returned by the service
     */
    public CreateFunctionResponse createFunction(CreateFunctionRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, FUNCTIONS);
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CreateFunctionResponse.class);
    }

    /**
     * Get user function list
     *
     * @param functionVersion (Optional) Specify the version of the function. If no function returns the $LATEST
     *                        version of the function, the optional valid value ALL will return all versions, including
     *                        $LATEST
     * @param marker          (Optional) marker
     * @param maxItems        (Optional) 1-10000
     * @return
     */
    public ListFunctionsResponse listFunctions(String functionVersion, Integer marker, Integer maxItems) {
        ListFunctionsRequest request = new ListFunctionsRequest()
                .withFunctionVersion(functionVersion)
                .withMarker(marker)
                .withMaxItems(maxItems);
        return this.listFunctions(request);
    }

    /**
     * Get user function list
     *
     * @param request
     * @return User function list
     */
    public ListFunctionsResponse listFunctions(ListFunctionsRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, FUNCTIONS);
        if (request.getFunctionVersion() != null) {
            internalRequest.addParameter("FunctionVersion", request.getFunctionVersion());
        }
        if (request.getMarker() != null) {
            internalRequest.addParameter("Marker", String.valueOf(request.getMarker()));
        }
        if (request.getMaxItems() != null) {
            internalRequest.addParameter("MaxItems", String.valueOf(request.getMaxItems()));
        }
        return invokeHttpClient(internalRequest, ListFunctionsResponse.class);
    }

    /**
     * Get function information
     *
     * @param functionName (Required) Function Name You can specify a function name (for example, Thumbnail), or you
     *                     can specify the function's BRN resource name (for example, brn:bce:cfc:bj:account-id:
     *                     function:thumbnail:$LATEST). CFC also allows you to specify a partial BRN (for example,
     *                     account-id:Thumbnail). Note that the BRN length is limited to 1-170. If only the function
     *                     name is specified, the length is limited to 64 characters.
     * @param qualifier    (optional) Function Alias Use this optional parameter to specify a function version or alias.
     *                    If you specify a function version, the API will use the qualified function BRN to request and
     *                     return information about the specific CFC function version. If you specify an alias, the API
     *                     returns information about the version of the function pointed to by the alias. If you don't
     *                     provide this parameter, the API returns information about the CFC function $LATEST
     * @return
     */
    public GetFunctionResponse getFunction(String functionName, String qualifier) {
        GetFunctionRequest request = new GetFunctionRequest()
                .withFunctionName(functionName)
                .withQualifier(qualifier);
        return this.getFunction(request);
    }

    /**
     * Get function information
     *
     * @param request
     * @return GetFunctionResponse User function information
     */
    public GetFunctionResponse getFunction(GetFunctionRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, FUNCTIONS,
                request.getFunctionName());
        if (request.getQualifier() != null) {
            internalRequest.addParameter("Qualifier", request.getQualifier());
        }
        return invokeHttpClient(internalRequest, GetFunctionResponse.class);
    }

    /**
     * Delete function
     *
     * @param functionName (Required) Function name
     * @param qualifier    (optional) Function alias
     * @return
     */
    public DeleteFunctionResponse deleteFunction(String functionName, String qualifier) {
        DeleteFunctionRequest request = new DeleteFunctionRequest()
                .withFunctionName(functionName)
                .withQualifier(qualifier);
        return this.deleteFunction(request);
    }

    /**
     * Delete function
     *
     * @param request
     * @return
     */
    public DeleteFunctionResponse deleteFunction(DeleteFunctionRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, FUNCTIONS,
                request.getFunctionName());
        if (request.getQualifier() != null) {
            internalRequest.addParameter("Qualifier", request.getQualifier());
        }
        return invokeHttpClient(internalRequest, DeleteFunctionResponse.class);
    }

    /**
     * Update function code
     *
     * @param request
     * @return Function information
     */
    public UpdateFunctionCodeResponse updateFunctionCode(UpdateFunctionCodeRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, FUNCTIONS,
                request.getFunctionName(), CODE);
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, UpdateFunctionCodeResponse.class);
    }

    /**
     * Get function configuration
     *
     * @param functionName (Required) Function name
     * @param qualifier    (Optional) Function alias
     * @return
     */
    public GetFunctionConfigurationResponse getFunctionConfiguration(String functionName, String qualifier) {
        GetFunctionConfigurationRequest request = new GetFunctionConfigurationRequest()
                .withFunctionName(functionName)
                .withQualifier(qualifier);
        return this.getFunctionConfiguration(request);
    }

    /**
     * Get function configuration
     *
     * @param request
     * @return function information
     */
    public GetFunctionConfigurationResponse getFunctionConfiguration(GetFunctionConfigurationRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, FUNCTIONS,
                request.getFunctionName(), CONFIGURATION);
        if (request.getQualifier() != null) {
            internalRequest.addParameter("Qualifier", request.getQualifier());
        }
        return invokeHttpClient(internalRequest, GetFunctionConfigurationResponse.class);
    }

    /**
     * Update function configuration
     *
     * @param request
     * @return Function information
     */
    public UpdateFunctionConfigurationResponse updateFunctionConfiguration(UpdateFunctionConfigurationRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, FUNCTIONS,
                request.getFunctionName(), CONFIGURATION);
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, UpdateFunctionConfigurationResponse.class);
    }

    /**
     * Get function version list
     *
     * @param functionName     (Required) Function name
     * @param marker           (Optional) Function marker
     * @param maxItems         (Optional) MaxItems 1-10000
     * @return
     */
    public ListVersionsByFunctionResponse listVersionsByFunction(String functionName, Integer marker,
                                                                 Integer maxItems) {
        ListVersionsByFunctionRequest request = new ListVersionsByFunctionRequest()
                .withFunctionName(functionName)
                .withMarker(marker)
                .withMaxItems(maxItems);
        return this.listVersionsByFunction(request);
    }

    /**
     * Get function version list
     *
     * @param request
     * @return
     */
    public ListVersionsByFunctionResponse listVersionsByFunction(ListVersionsByFunctionRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, FUNCTIONS,
                request.getFunctionName(), VERSIONS);
        if (request.getMarker() != null) {
            internalRequest.addParameter("Marker", String.valueOf(request.getMarker()));
        }
        if (request.getMaxItems() != null) {
            internalRequest.addParameter("MaxItems", String.valueOf(request.getMaxItems()));
        }
        return invokeHttpClient(internalRequest, ListVersionsByFunctionResponse.class);
    }

    public PublishVersionResponse publishVersion(String functionName, String description, String codeSha256) {
        PublishVersionRequest request = new PublishVersionRequest()
                .withFunctionName(functionName)
                .withDescription(description)
                .withCodeSha256(codeSha256);
        return this.publishVersion(request);
    }

    /**
     * Publish function
     *
     * @param request
     * @return
     */
    public PublishVersionResponse publishVersion(PublishVersionRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, FUNCTIONS,
                request.getFunctionName(), VERSIONS);
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, PublishVersionResponse.class);
    }

    public ListAliasesResponse listAliases(String functionName, String functionVersion, Integer marker,
                                           Integer maxItems) {
        ListAliasesRequest request = new ListAliasesRequest()
                .withFunctionName(functionName)
                .withFunctionVersion(functionVersion)
                .withMarker(marker)
                .withMaxItems(maxItems);
        return this.listAliases(request);
    }

    /**
     * Get function alias list
     *
     * @param request
     * @return
     */
    public ListAliasesResponse listAliases(ListAliasesRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, FUNCTIONS,
                request.getFunctionName(), ALIASES);
        if (request.getFunctionVersion() != null) {
            internalRequest.addParameter("FunctionVersion", request.getFunctionVersion());
        }
        if (request.getMarker() != null) {
            internalRequest.addParameter("Marker", String.valueOf(request.getMarker()));
        }
        if (request.getMaxItems() != null) {
            internalRequest.addParameter("MaxItems", String.valueOf(request.getMaxItems()));
        }
        return invokeHttpClient(internalRequest, ListAliasesResponse.class);
    }

    /**
     * Create function alias
     *
     * @param functionName    Function name
     * @param functionVersion Function version
     * @param name            Alias, uppercase and lowercase letters, numbers, and -_/. special characters, must start
     *                        with a letter and be limited to 64 characters in length
     * @param description     Alias description
     * @return Alias information
     */
    public CreateAliasResponse createAlias(String functionName, String functionVersion, String name,
                                           String description) {
        CreateAliasRequest request = new CreateAliasRequest(name)
                .withName(name)
                .withFunctionName(functionName)
                .withFunctionVersion(functionVersion)
                .withDescription(description);
        return createAlias(request);

    }

    /**
     * Create function alias
     *
     * @param request
     * @return Alias information
     */
    public CreateAliasResponse createAlias(CreateAliasRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, FUNCTIONS,
                request.getFunctionName(), ALIASES);
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CreateAliasResponse.class);
    }

    /**
     * Get function alias
     *
     * @param aliasName    Alias name
     * @param functionName Function name
     * @return
     */
    public GetAliasResponse getAlias(String aliasName, String functionName) {
        GetAliasRequest request = new GetAliasRequest()
                .withAliasName(aliasName)
                .withFunctionName(functionName);
        return this.getAlias(request);
    }

    /**
     * Get function alias
     *
     * @param request
     * @return
     */
    public GetAliasResponse getAlias(GetAliasRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, FUNCTIONS,
                request.getFunctionName(), ALIASES, request.getAliasName());
        return invokeHttpClient(internalRequest, GetAliasResponse.class);
    }

    /**
     * Update alias
     *
     * @param functionName    Function name
     * @param functionVersion Function version
     * @param aliasName       Alias name
     * @param description     Alias description
     * @return
     */
    public UpdateAliasResponse updateAlias(String functionName, String functionVersion, String aliasName,
                                           String description) {
        UpdateAliasRequest request = new UpdateAliasRequest()
                .withFunctionName(functionName)
                .withAliasName(aliasName)
                .withFunctionVersion(functionVersion)
                .withDescription(description);
        return this.updateAlias(request);
    }

    /**
     * Update function alias
     *
     * @param request
     * @return
     */
    public UpdateAliasResponse updateAlias(UpdateAliasRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, FUNCTIONS,
                request.getFunctionName(), ALIASES, request.getAliasName());
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, UpdateAliasResponse.class);
    }

    /**
     * Delete function alias
     *
     * @param functionName Function name
     * @param aliasName    Alias name
     * @return
     */
    public DeleteAliasResponse deleteAlias(String functionName, String aliasName) {
        DeleteAliasRequest request = new DeleteAliasRequest()
                .withFunctionName(functionName)
                .withAliasName(aliasName);
        return this.deleteAlias(request);
    }

    /**
     * Delete function alias
     *
     * @param request
     * @return
     */
    public DeleteAliasResponse deleteAlias(DeleteAliasRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, FUNCTIONS,
                request.getFunctionName(), ALIASES, request.getAliasName());
        return invokeHttpClient(internalRequest, DeleteAliasResponse.class);
    }

    /**
     * Get trigger list
     *
     * @param functionBrn Function BRN
     * @return
     */
    public ListTriggersResponse listTriggers(String functionBrn) {
        ListTriggersRequest request = new ListTriggersRequest()
                .withFunctionBrn(functionBrn);
        return listTriggers(request);
    }

    /**
     * Get trigger list
     *
     * @param request
     * @return
     */
    public ListTriggersResponse listTriggers(ListTriggersRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, RELATION);
        if (request.getFunctionBrn() != null) {
            internalRequest.addParameter("FunctionBrn", request.getFunctionBrn());
        }
        return invokeHttpClient(internalRequest, ListTriggersResponse.class);
    }

    /**
     * Create trigger
     *
     * @param target Function BRN
     * @param source Trigger source
     * @param data   Trigger parameter configuration
     * @return
     */
    public CreateTriggerResponse createTrigger(String target, String source, Map<String, String> data) {
        CreateTriggerRequest request = new CreateTriggerRequest()
                .withTarget(target)
                .withSource(source)
                .withData(data);
        return this.createTrigger(request);
    }

    /**
     * Create trigger
     *
     * @param request
     * @return
     */
    public CreateTriggerResponse createTrigger(CreateTriggerRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, RELATION);
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CreateTriggerResponse.class);
    }

    /**
     * Update trigger
     *
     * @param relationId Trigger ID
     * @param target     Function BRN
     * @param source     Trigger source
     * @param data       Trigger parameter configuration
     * @return
     */
    public UpdateTriggerResponse updateTrigger(String relationId, String target, String source,
                                               Map<String, String> data) {
        UpdateTriggerRequest request = new UpdateTriggerRequest()
                .withRelationId(relationId)
                .withTarget(target)
                .withSource(source)
                .withData(data);
        UpdateTriggerResponse response = this.updateTrigger(request);
        response.getRelation().setSid(null);
        return response;
    }

    /**
     * Update trigger
     *
     * @param request
     * @return
     */
    public UpdateTriggerResponse updateTrigger(UpdateTriggerRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, RELATION);
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, UpdateTriggerResponse.class);

    }

    /**
     * Delete trigger
     *
     * @param target     Function BRN
     * @param source     Trigger source
     * @param relationId Trigger ID
     * @return
     */
    public DeleteTriggerResponse deleteTrigger(String target, String source, String relationId) {
        DeleteTriggerRequest request = new DeleteTriggerRequest()
                .withTarget(target)
                .withSource(source)
                .withRelationId(relationId);
        return this.deleteTrigger(request);
    }

    /**
     * Delete trigger
     *
     * @param request
     * @return
     */
    public DeleteTriggerResponse deleteTrigger(DeleteTriggerRequest request) {
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, RELATION);
        if (request.getTarget() != null) {
            internalRequest.addParameter("Target", request.getTarget());
        }
        if (request.getSource() != null) {
            internalRequest.addParameter("Source", request.getSource());
        }
        if (request.getRelationId() != null) {
            internalRequest.addParameter("RelationId", request.getRelationId());
        }
        return invokeHttpClient(internalRequest, DeleteTriggerResponse.class);
    }

    /**
     * Invoke function
     *
     * @param functionName   Function name or function BRN
     * @param invocationType Call mode Event (asynchronous call) returns 202/RequestResponse (sync return) / DryRun
     *                       (test function). Default ResauestResponse
     * @param logType        Log type Tail / None You can set this optional parameter to Tail, provided the
     *                       InvocationType parameter must be RequestResponse
     * @param qualifier      Function version or function alias
     * @param payload
     * @return
     */
    public InvokeResponse invoke(String functionName, String invocationType, String logType, String qualifier,
                                 byte[] payload) {
        InvokeRequest request = new InvokeRequest()
                .withFunctionName(functionName)
                .withInvocationType(invocationType)
                .withLogType(logType)
                .withQualifier(qualifier)
                .withPayload(payload);
        return this.invoke(request);
    }

    /**
     * Invoke function
     *
     * @param request
     * @return
     */
    public InvokeResponse invoke(InvokeRequest request) {
        this.config.setConnectionTimeoutInMillis(INVOKE_CONNECTION_TIMEOUT_IN_MILLIS);
        this.config.setSocketTimeoutInMillis(INVOKE_SOCKET_TIMEOUT_IN_MILLIS);
        checkNotNull(request, ERRORMSG);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, FUNCTIONS,
                request.getFunctionName(), INVOCATIONS);
        if (request.getInvocationType() != null) {
            internalRequest.addParameter("InvocationType", request.getInvocationType());
        }
        if (request.getLogType() != null) {
            internalRequest.addParameter("LogType", request.getLogType());
        }
        if (request.getQualifier() != null) {
            internalRequest.addParameter("Qualifier", request.getQualifier());
        }
        this.attachInvokeRequestToBody(internalRequest, request.getPayload());
        GetInvokeResponse response = invokeHttpClient(internalRequest, GetInvokeResponse.class);
        StringBuilder payload = new StringBuilder();
        try {
            if (response.getInvoke().getContent() != null) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getInvoke().getContent(), ENCODINGTYPE));
                String line;
                while ((line = reader.readLine()) != null) {
                    payload.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        InvokeResponse invokeResponse = new InvokeResponse();
        invokeResponse.setMetadata(response.getMetadata());
        byte[] payloadBytes;
        try {
            payloadBytes = JsonUtils.toJsonString(payload).getBytes(ENCODINGTYPE);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException(ENCODINGERR, e);
        }
        invokeResponse.setPayload(payloadBytes);
        String logResult = response.getInvoke().getObjectMetadata().getBceLogResult();
        if (logResult != "") {
            invokeResponse.setBceLogResult(logResult);
        }

        return invokeResponse;

    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest    The original BCE request created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific
     * parameters to send.
     */
    private InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
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
     * put json object into http content for put or post request.
     *
     * @param request     json object of rest request
     * @param httpRequest http request object
     */
    private void attachRequestToBody(AbstractBceRequest request, InternalRequest httpRequest) {
        byte[] content;
        try {
            content = JsonUtils.toJsonString(request).getBytes(ENCODINGTYPE);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException(ENCODINGERR, e);
        }

        httpRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(content.length));
        httpRequest.addHeader(Headers.CONTENT_TYPE, CONTENTTYPE);
        httpRequest.setContent(RestartableInputStream.wrap(content));

    }

    /**
     * put invoke payload into http body for put or post request.
     * @param httpRequest json object of rest request
     * @param payload  http request object
     */
    private void attachInvokeRequestToBody(InternalRequest httpRequest, byte[] payload) {
        httpRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(payload.length));
        httpRequest.addHeader(Headers.CONTENT_TYPE, CONTENTTYPE);
        httpRequest.setContent(RestartableInputStream.wrap(payload));
    }

}