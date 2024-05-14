/*
 * Copyright 2017 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotdm.model.v3.device.CreateDeviceRequest;
import com.baidubce.services.iotdm.model.v3.device.DeviceAccessDetailResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceAttributes;
import com.baidubce.services.iotdm.model.v3.device.DeviceListRequest;
import com.baidubce.services.iotdm.model.v3.device.DeviceListResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfileListResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfileResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceViewResponse;
import com.baidubce.services.iotdm.model.v3.device.UpdateDeviceProfileRequest;
import com.baidubce.services.iotdm.model.v3.device.UpdateDeviceRegistryRequest;
import com.baidubce.services.iotdm.model.v3.device.UpdateDeviceViewRequest;
import com.baidubce.services.iotdm.model.AccessDetailResponse;
import com.baidubce.services.iotdm.model.v3.domain.CreateDomainRequest;
import com.baidubce.services.iotdm.model.v3.domain.Domain;
import com.baidubce.services.iotdm.model.v3.domain.DomainDetail;
import com.baidubce.services.iotdm.model.v3.domain.DomainDeviceListResponse;
import com.baidubce.services.iotdm.model.v3.domain.DomainListResponse;
import com.baidubce.services.iotdm.model.v3.domain.UpdateDomainRegistryInfoRequest;
import com.baidubce.services.iotdm.model.v3.domain.UpdateDomainDevicesRequest;
import com.baidubce.services.iotdm.model.v3.domain.UpdateDomainDevicesResponse;
import com.baidubce.services.iotdm.model.v3.rules.DeviceFormatRuleRequest;
import com.baidubce.services.iotdm.model.v3.rules.DeviceFormatRuleResponse;
import com.baidubce.services.iotdm.model.v3.rules.DeviceRuleDestination;
import com.baidubce.services.iotdm.model.v3.rules.DeviceRuleFormat;
import com.baidubce.services.iotdm.model.v3.rules.DeviceRuleRequest;
import com.baidubce.services.iotdm.model.v3.rules.DeviceRuleResponse;
import com.baidubce.services.iotdm.model.v3.rules.DeviceRuleSource;
import com.baidubce.services.iotdm.model.v3.schema.SchemaCreateRequest;
import com.baidubce.services.iotdm.model.v3.schema.SchemaCreateResponse;
import com.baidubce.services.iotdm.model.v3.schema.SchemaListResponse;
import com.baidubce.services.iotdm.model.v3.schema.SchemaProperty;
import com.baidubce.services.iotdm.model.v3.schema.SchemaResponse;
import com.baidubce.services.iotdm.model.v3.schema.SchemaUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Unit test for iot device management V3.
 */
public class IotDmV3ClientTest {

    // qa-sandbox
    private static final String ACCESSKEY = "";
    private static final String SECRETKEY = "";
    private static final String ENDPOINT = "10.73.203.34:8010";
    private static final String TEST_TSDB_DOMAIN = "test222.tsdb-106tunjjq367.tsdb.iot.gz.baidubce.com";


    private static final String TEST_DOMAIN_NAME = "test_domain_0724";
    private static final String TEST_DEVICE_RULE = "test_device_rule";
    private static final String TEST_DEVICE_NAME = "iot_dm_v3_client_test_device";
    private static final String TEST_SCHEMA_NAME = "iot_dm_v3_client_test_schema";

    private static final String TEST_SCHEMA_PROPERTY_TYPE_BOOL_NAME = "bool";
    private static final String TEST_SCHEMA_PROPERTY_TYPE_STRING_NAME = "string";
    private static final String TEST_SCHEMA_PROPERTY_TYPE_NUMBER_NAME = "number";
    private static final boolean BOOL_DEFAULT_VALUE = false;
    private static final String STRING_DEFAULT_VALUE = "s";
    private static final double NUMBER_DEFAULT_VALUE = 1.0;
    private static final String DISPLAY_NAME = "displayName";
    private static final String UNIT = "unit";

    private static final String INITIAL_DESCRIPTION = "initialDescription";
    private static final String UPDATED_DESCRIPTION = "updatedDescription";

    private static final Domain.DomainType TEST_DOMAIN_TYPE = Domain.DomainType.NORMAL;
    private static final String MODIFIED_DEVICE_RULE_NAME = "modified_device_rule_name";

    private IotDmV3Client client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESSKEY, SECRETKEY))
                .withEndpoint(ENDPOINT);
        client = new IotDmV3Client(config);
    }

    @Test
    public void testRemoveDefaultDevice() {
        removeDefaultDevice();
    }

    @Test
    public void testCreateDeviceRule() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();
        createDefaultDeviceWithSchemaId(schemaCreateResponse.getSchemaId());

        createDefaultDeviceRule();
        client.removeDeviceRule(TEST_DEVICE_NAME);
        removeDefaultDevice();
        client.deleteSchema(schemaCreateResponse.getSchemaId());

    }

    @Test
    public void testGetDeviceRules() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();
        createDefaultDeviceWithSchemaId(schemaCreateResponse.getSchemaId());
        createDefaultDeviceRule();
        DeviceRuleResponse response = client.getDeviceRules(TEST_DEVICE_NAME);

        assertThat(response.getDeviceName(), equalTo(TEST_DEVICE_NAME));
        assertThat(response.getName(), equalTo(TEST_DEVICE_RULE));

        client.removeDeviceRule(TEST_DEVICE_NAME);
        removeDefaultDevice();
        client.deleteSchema(schemaCreateResponse.getSchemaId());
    }

    @Test
    public void testModifyDeviceRule() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();
        createDefaultDeviceWithSchemaId(schemaCreateResponse.getSchemaId());

        createDefaultDeviceRule();

        DeviceRuleResponse response1 = client.getDeviceRules(TEST_DEVICE_NAME);
        assertThat(response1.getName(), equalTo(TEST_DEVICE_RULE));

        DeviceRuleRequest request = new DeviceRuleRequest()
                .withName(MODIFIED_DEVICE_RULE_NAME);

        DeviceRuleResponse response2 = client.modifyDeviceRule(TEST_DEVICE_NAME, request);
        assertThat(response2.getName(), equalTo(MODIFIED_DEVICE_RULE_NAME));

        client.removeDeviceRule(TEST_DEVICE_NAME);
        removeDefaultDevice();
        client.deleteSchema(schemaCreateResponse.getSchemaId());
    }

    @Test
    public void testEnableAndDisableDeviceRule() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();
        createDefaultDeviceWithSchemaId(schemaCreateResponse.getSchemaId());
        createDefaultDeviceRule();

        client.enableDeviceRule(TEST_DEVICE_NAME);

        DeviceRuleResponse response1 = client.getDeviceRules(TEST_DEVICE_NAME);
        assertThat(response1.getEnable(), equalTo(true));

        client.disableDeviceRule(TEST_DEVICE_NAME);

        DeviceRuleResponse response2 = client.getDeviceRules(TEST_DEVICE_NAME);
        assertThat(response2.getEnable(), equalTo(false));

        client.removeDeviceRule(TEST_DEVICE_NAME);
        removeDefaultDevice();
        client.deleteSchema(schemaCreateResponse.getSchemaId());
    }

    @Test
    public void testTsdbFormatRule() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();
        createDefaultDeviceWithSchemaId(schemaCreateResponse.getSchemaId());

        DeviceFormatRuleRequest deviceFormatRuleRequest = new DeviceFormatRuleRequest();
        List<DeviceRuleDestination> desList = new ArrayList<DeviceRuleDestination>(1);
        DeviceRuleDestination destination = new DeviceRuleDestination();
        destination.setKind(DeviceRuleDestination.KindType.TSDB);
        destination.setValue(TEST_TSDB_DOMAIN);
        desList.add(destination);
        deviceFormatRuleRequest.setDestinations(desList);
        List<DeviceRuleSource> sources = new ArrayList<DeviceRuleSource>(3);
        DeviceRuleSource source = new DeviceRuleSource();
        source.setDescription("This is condition 1");
        source.setCondition("<>");
        source.setType(SchemaProperty.PropertyType.NUMBER);
        source.setName(TEST_SCHEMA_PROPERTY_TYPE_NUMBER_NAME);
        source.setValue("0");
        sources.add(source);

        DeviceRuleSource source2 = new DeviceRuleSource();
        source2.setDescription("This is condition 1");
        source2.setCondition("=");
        source2.setType(SchemaProperty.PropertyType.BOOL);
        source2.setName(TEST_SCHEMA_PROPERTY_TYPE_BOOL_NAME);
        source2.setValue("false");

        DeviceRuleSource source3 = new DeviceRuleSource();
        source3.setDescription("This is condition 1");
        source3.setCondition("=");
        source3.setType(SchemaProperty.PropertyType.STRING);
        source3.setName(TEST_SCHEMA_PROPERTY_TYPE_STRING_NAME);
        source3.setValue("");

        sources.add(source2);
        sources.add(source3);

        deviceFormatRuleRequest.setSources(sources);
        deviceFormatRuleRequest.setName(TEST_DEVICE_RULE);

        DeviceRuleFormat format1 = new DeviceRuleFormat();
        format1.setMode(DeviceRuleFormat.ModeType.FIELD);
        format1.setMetric("newMetricName");
        Map<String, String> tags = Maps.newHashMap();
        tags.put("tag1", "aaa");
        format1.setTags(tags);
        deviceFormatRuleRequest.setFormat(format1);

        DeviceFormatRuleResponse response1 = client.createTsdbFormatRule(TEST_DEVICE_NAME, deviceFormatRuleRequest);
        assertThat(response1.getName(), equalTo(TEST_DEVICE_RULE));
        assertThat(response1.getDeviceName(), equalTo(TEST_DEVICE_NAME));
        assertThat(response1.getFormat().getMode(), equalTo(DeviceRuleFormat.ModeType.FIELD));
        assertThat(response1.getFormat().getMetric(), equalTo("newMetricName"));
        assertThat((String) response1.getFormat().getTags().get("tag1"), equalTo("aaa"));

        deviceFormatRuleRequest.setName(MODIFIED_DEVICE_RULE_NAME);

        DeviceFormatRuleResponse response2 = client.modifyTsdbFormatRule(TEST_DEVICE_NAME, deviceFormatRuleRequest);
        assertThat(response2.getName(), equalTo(MODIFIED_DEVICE_RULE_NAME));
        assertThat(response2.getDeviceName(), equalTo(TEST_DEVICE_NAME));
        assertThat(response1.getFormat().getMode(), equalTo(DeviceRuleFormat.ModeType.FIELD));
        assertThat(response1.getFormat().getMetric(), equalTo("newMetricName"));
        assertThat((String) response1.getFormat().getTags().get("tag1"), equalTo("aaa"));

        DeviceFormatRuleResponse response3 = client.getTsdbFormatRule(TEST_DEVICE_NAME);

        assertThat(response3.getName(), equalTo(MODIFIED_DEVICE_RULE_NAME));
        assertThat(response3.getDeviceName(), equalTo(TEST_DEVICE_NAME));

        client.removeDeviceRule(TEST_DEVICE_NAME);
        removeDefaultDevice();
        client.deleteSchema(schemaCreateResponse.getSchemaId());

    }

    @Test
    public void testCreateAndRemoveDomain() {
        CreateDomainRequest request = new CreateDomainRequest()
                .withName(TEST_DOMAIN_NAME)
                .withType(TEST_DOMAIN_TYPE)
                .withDescription(INITIAL_DESCRIPTION);
        client.createDomain(request);
        client.removeDomain(TEST_DOMAIN_NAME);
    }

    @Test
    public void testGetDomains() {
        createDefaultDomain();
        DomainListResponse domainListResponse = client.getDomains(1, 20, "name", "asc", TEST_DOMAIN_NAME, null, null);

        assertThat(domainListResponse.getAmount(), equalTo(1));
        assertThat(domainListResponse.getDomains().get(0).getName(), equalTo(TEST_DOMAIN_NAME));
        client.removeDomain(TEST_DOMAIN_NAME);
    }

    @Test
    public void testGetDomainDetail() {
        createDefaultDomain();
        DomainDetail response = client.getDomainDetail(TEST_DOMAIN_NAME);
        assertThat(response.getName(), equalTo(TEST_DOMAIN_NAME));
        assertThat(response.getType(), equalTo(TEST_DOMAIN_TYPE));
        client.removeDomain(TEST_DOMAIN_NAME);
    }

    @Test
    public void testUpdateDomain() {
        createDefaultDomain();
        createDefaultDevice();

        List<String> deviceNames = Lists.newArrayList();
        deviceNames.add(TEST_DEVICE_NAME);

        UpdateDomainDevicesRequest request1 = new UpdateDomainDevicesRequest()
                .withAddedDevices(deviceNames);
        UpdateDomainDevicesResponse response1 = client.modifyDomainDevices(TEST_DOMAIN_NAME, request1);
        assertThat(response1.getAddedDevices().get(0), equalTo(TEST_DEVICE_NAME));

        UpdateDomainDevicesRequest request2 = new UpdateDomainDevicesRequest()
                .withRemovedDevices(deviceNames);
        UpdateDomainDevicesResponse response2 = client.modifyDomainDevices(TEST_DOMAIN_NAME, request2);
        assertThat(response2.getRemovedDevices().get(0), equalTo(TEST_DEVICE_NAME));
        removeDefaultDevice();
        client.removeDomain(TEST_DOMAIN_NAME);
    }

    @Test
    public void testUpdateDomainRegistryInfo() {
        createDefaultDomain();
        UpdateDomainRegistryInfoRequest request = new UpdateDomainRegistryInfoRequest()
                .withDescription(UPDATED_DESCRIPTION);
        client.modifyDomainRegistryInfo(TEST_DOMAIN_NAME, request);

        DomainListResponse domainListResponse = client.getDomains(1, 20, "name", "asc", TEST_DOMAIN_NAME, null, null);
        assertThat(domainListResponse.getDomains().get(0).getName(), equalTo(TEST_DOMAIN_NAME));
        assertThat(domainListResponse.getDomains().get(0).getDescription(), equalTo(UPDATED_DESCRIPTION));

        client.removeDomain(TEST_DOMAIN_NAME);
    }

    @Test
    public void testGetDomainAccessDetail() {
        createDefaultDomain();
        AccessDetailResponse response = client.getDomainAccessDetail(TEST_DOMAIN_NAME);
        assertThat(response.getUsername().contains(TEST_DOMAIN_NAME), equalTo(true));
        client.removeDomain(TEST_DOMAIN_NAME);
    }

    @Test
    public void testUpdateDomainSecretKey() {
        createDefaultDomain();
        AccessDetailResponse response1 = client.getDomainAccessDetail(TEST_DOMAIN_NAME);
        AccessDetailResponse response2 = client.updateDomainSecretKey(TEST_DOMAIN_NAME);
        assertThat(response1.getKey(), not(response2.getKey()));
        client.removeDomain(TEST_DOMAIN_NAME);
    }

    @Test
    public void testGetDomainDeviceList() {
        createDefaultDomain();
        createDefaultDevice();
        List<String> deviceNames = Lists.newArrayList();
        deviceNames.add(TEST_DEVICE_NAME);

        UpdateDomainDevicesRequest request1 = new UpdateDomainDevicesRequest()
                .withAddedDevices(deviceNames);
        UpdateDomainDevicesResponse response1 = client.modifyDomainDevices(TEST_DOMAIN_NAME, request1);

        DomainDeviceListResponse response =
                client.getDomainDeviceList(TEST_DOMAIN_NAME, 1, 20, "name", "asc", "", "", null);
        assertThat(response.getAmount(), greaterThan(0));

        removeDefaultDevice();
        client.removeDomain(TEST_DOMAIN_NAME);

    }

    @Test
    public void testCreateSchema() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();

        SchemaResponse schemaResponse = client.getSchema(schemaCreateResponse.getSchemaId());
        assertThat(schemaResponse.getId(), equalTo(schemaCreateResponse.getSchemaId()));
        assertThat(schemaResponse.getName(), equalTo(TEST_SCHEMA_NAME));
        assertThat(schemaResponse.getDescription(), equalTo(INITIAL_DESCRIPTION));
        assertThat(schemaResponse.getCreateTime(), notNullValue());
        assertThat(schemaResponse.getLastUpdatedTime(), notNullValue());
        assertThat(schemaResponse.getCreateTime(), equalTo(schemaResponse.getLastUpdatedTime()));
        assertThat(schemaResponse.getProperties().size(), equalTo(3));

        deleteSchemaById(schemaResponse.getId());
    }

    @Test
    public void testUpdateSchema() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();

        List<SchemaProperty> properties = Lists.newArrayList(
                createSchemaProperty(SchemaProperty.PropertyType.STRING));
        SchemaUpdateRequest schemaUpdateRequest = new SchemaUpdateRequest()
                .withDescription(UPDATED_DESCRIPTION)
                .withProperties(properties);

        client.updateSchema(schemaCreateResponse.getSchemaId(),
                schemaUpdateRequest);

        SchemaResponse schemaResponse = client.getSchema(schemaCreateResponse.getSchemaId());
        assertThat(schemaResponse.getDescription(), equalTo(UPDATED_DESCRIPTION));
        assertThat(schemaResponse.getProperties().size(), equalTo(properties.size()));

        deleteSchemaById(schemaResponse.getId());
    }

    @Test
    public void testListSchema() {
        createDefaultSchema();

        SchemaListResponse schemaListResponse = client.getSchemas(1, 20, "name", "asc", TEST_SCHEMA_NAME);
        assertThat(schemaListResponse.getTotalCount(), equalTo(1));
        assertThat(schemaListResponse.getResult().get(0).getName(), equalTo(TEST_SCHEMA_NAME));
        assertThat(schemaListResponse.getResult().get(0).getProperties().size(), equalTo(3));

        client.deleteSchema(schemaListResponse.getResult().get(0).getId());
        schemaListResponse = client.getSchemas(1, 20, "name", "asc", TEST_SCHEMA_NAME);
        assertThat(schemaListResponse.getTotalCount(), equalTo(0));
    }

    @Test
    public void testCreateDevice() {
        DeviceAccessDetailResponse response = createDefaultDevice();
        assertThat(response.getTcpEndpoint(), notNullValue());
        assertThat(response.getSslEndpoint(), notNullValue());
        assertThat(response.getUsername(), notNullValue());
        assertThat(response.getKey(), notNullValue());

        DeviceProfileResponse profileResponse = client.getDeviceProfile(TEST_DEVICE_NAME);
        assertThat(profileResponse.getName(), equalTo(TEST_DEVICE_NAME));
        assertThat(profileResponse.getFavourite(), equalTo(false));
        assertThat(profileResponse.getDescription(), equalTo(INITIAL_DESCRIPTION));
        assertThat(profileResponse.getAttributes().size(), equalTo(0));
        assertThat(profileResponse.getCreateTime(), notNullValue());
        assertThat(profileResponse.getState(), notNullValue());
        assertThat(profileResponse.getLastActiveTime(), notNullValue());

        DeviceListResponse removeDevicesResponse = removeDefaultDevice();
        assertThat(removeDevicesResponse.getDevices().size(), equalTo(1));
        assertThat(removeDevicesResponse.getDevices().get(0), equalTo(TEST_DEVICE_NAME));
    }

    @Test
    public void testCreateDeviceWithSchema() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();

        createDefaultDeviceWithSchemaId(schemaCreateResponse.getSchemaId());

        DeviceProfileResponse profileResponse = client.getDeviceProfile(TEST_DEVICE_NAME);
        assertThat(profileResponse.getSchemaId(), equalTo(schemaCreateResponse.getSchemaId()));
        assertThat(profileResponse.getSchemaName(), equalTo(TEST_SCHEMA_NAME));
    }

    @Test
    public void testListDeviceProfile() {
        createDefaultDevice();

        DeviceProfileListResponse response = client.getDeviceProfiles(1, 10,
                "name", "asc", "name", TEST_DEVICE_NAME, null);
        assertThat(response.getAmount(), equalTo(1));
        assertThat(response.getDevices().get(0).getName(), equalTo(TEST_DEVICE_NAME));
        assertThat(response.getDevices().get(0).getDevice().getReported().size(), equalTo(0));
        assertThat(response.getDevices().get(0).getDevice().getLastUpdatedTime().getReported().size(), equalTo(0));

        removeDefaultDevice();
        response = client.getDeviceProfiles(1, 10,
                "name", "asc", "name", TEST_DEVICE_NAME, null);
        assertThat(response.getAmount(), equalTo(0));
    }

    @Test
    public void testUpdateDeviceProfileAndResetDevices() {
        createDefaultDevice();

        DeviceAttributes deviceAttributes = new DeviceAttributes()
                .withReported(createJsonNode("string", "s"))
                .withDesired(createJsonNode("string", "s"));

        UpdateDeviceProfileRequest updateDeviceProfileRequest = new UpdateDeviceProfileRequest()
                .withAttributes(createJsonNode("region", "Shanghai"))
                .withDevice(deviceAttributes);
        DeviceProfileResponse deviceProfileResponse = client.updateDeviceProfile(TEST_DEVICE_NAME,
                updateDeviceProfileRequest);

        assertThat(deviceProfileResponse.getAttributes().get("region").asText(), equalTo("Shanghai"));
        assertThat(deviceProfileResponse.getDevice().getReported().get("string").asText(), equalTo("s"));
        assertThat(deviceProfileResponse.getDevice().getDesired().get("string").asText(), equalTo("s"));
        assertThat(deviceProfileResponse.getDevice().getLastUpdatedTime().getReported().get("string"), notNullValue());
        assertThat(deviceProfileResponse.getDevice().getLastUpdatedTime().getDesired().get("string"), notNullValue());
        assertThat(deviceProfileResponse.getDevice().getProfileVersion(), equalTo(1));

        List<String> devices = Arrays.asList(TEST_DEVICE_NAME);
        DeviceListRequest request = new DeviceListRequest();
        request.setDevices(devices);
        DeviceListResponse response = client.resetDevices(request);
        assertThat(response.getDevices(), equalTo(devices));

        deviceProfileResponse = client.getDeviceProfile(TEST_DEVICE_NAME);
        assertThat(deviceProfileResponse.getDevice().getProfileVersion(), equalTo(0));
        assertThat(deviceProfileResponse.getDevice().getDesired().size(), equalTo(0));
        assertThat(deviceProfileResponse.getDevice().getReported().size(), equalTo(0));
    }

    @Test
    public void testUpdateDeviceRegistry() {
        createDefaultDevice();
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();

        UpdateDeviceRegistryRequest request = new UpdateDeviceRegistryRequest()
                .withDescription(UPDATED_DESCRIPTION)
                .withSchemaId(schemaCreateResponse.getSchemaId())
                .withFavourite(true);

        DeviceProfileResponse response = client.updateDeviceRegistry(TEST_DEVICE_NAME, request);
        assertThat(response.getFavourite(), equalTo(true));
        assertThat(response.getSchemaId(), equalTo(schemaCreateResponse.getSchemaId()));
        assertThat(response.getDescription(), equalTo(UPDATED_DESCRIPTION));
    }

    @Test
    public void testGetDeviceAccessDetail() {
        DeviceAccessDetailResponse createResponse = createDefaultDevice();
        DeviceAccessDetailResponse getResponse = client.getDeviceAccessDetail(TEST_DEVICE_NAME);
        assertThat(getResponse.getUsername(), equalTo(createResponse.getUsername()));
        assertThat(getResponse.getSslEndpoint(), equalTo(createResponse.getSslEndpoint()));
        assertThat(getResponse.getTcpEndpoint(), equalTo(createResponse.getTcpEndpoint()));
        assertThat(getResponse.getKey(), equalTo("xxxxxxxxx"));
        assertThat(getResponse.getKey(), not(createResponse.getKey()));
    }

    @Test
    public void testUpdateSecretKey() {
        DeviceAccessDetailResponse createResponse = createDefaultDevice();
        DeviceAccessDetailResponse updatedResponse = client.updateDeviceSecretKey(TEST_DEVICE_NAME);
        assertThat(updatedResponse.getUsername(), equalTo(createResponse.getUsername()));
        assertThat(updatedResponse.getSslEndpoint(), equalTo(createResponse.getSslEndpoint()));
        assertThat(updatedResponse.getTcpEndpoint(), equalTo(createResponse.getTcpEndpoint()));
        assertThat(updatedResponse.getKey(), not(createResponse.getKey()));
    }

    @Test
    public void testGetDeviceView() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();
        createDefaultDeviceWithSchemaId(schemaCreateResponse.getSchemaId());

        DeviceAttributes deviceAttributes = new DeviceAttributes();
        deviceAttributes.setReported(createAllTypeNode());
        deviceAttributes.setDesired(createAllTypeNode());
        UpdateDeviceProfileRequest updateDeviceProfileRequest = new UpdateDeviceProfileRequest();
        updateDeviceProfileRequest.setDevice(deviceAttributes);
        client.updateDeviceProfile(TEST_DEVICE_NAME, updateDeviceProfileRequest);

        DeviceViewResponse deviceViewResponse = client.getDeviceView(TEST_DEVICE_NAME);
        assertThat(deviceViewResponse.getProperties().size(), equalTo(3));
        assertThat(deviceViewResponse.getProfileVersion(), equalTo(1));
        checkDeviceViewProperties(deviceViewResponse.getProperties());
    }

    @Test
    public void testUpdateDeviceView() {
        SchemaCreateResponse schemaCreateResponse = createDefaultSchema();
        createDefaultDeviceWithSchemaId(schemaCreateResponse.getSchemaId());

        UpdateDeviceViewRequest updateDeviceViewRequest = new UpdateDeviceViewRequest()
                .withDesired(createAllTypeNode())
                .withReported(createAllTypeNode());

        DeviceViewResponse deviceViewResponse = client.updateDeviceView(TEST_DEVICE_NAME, updateDeviceViewRequest);
        assertThat(deviceViewResponse.getProperties().size(), equalTo(3));
        assertThat(deviceViewResponse.getProfileVersion(), equalTo(1));
        checkDeviceViewProperties(deviceViewResponse.getProperties());

        DeviceProfileResponse deviceProfileResponse = client.getDeviceProfile(TEST_DEVICE_NAME);
        checkDeviceProfileDeviceAttributes(deviceProfileResponse.getDevice());
    }

    private void checkDeviceViewProperties(List<DeviceViewResponse.DeviceViewAttribute> properties) {
        for (int i = 0; i < properties.size(); ++i) {
            String value;
            switch (properties.get(i).getType()) {
                case BOOL:
                    value = String.valueOf(BOOL_DEFAULT_VALUE);
                    break;
                case NUMBER:
                    value = String.valueOf(NUMBER_DEFAULT_VALUE);
                    break;
                case STRING:
                    value = STRING_DEFAULT_VALUE;
                    break;
                default:
                    value = "error";
            }
            assertThat(properties.get(i).getReportedValue(), equalTo(value));
            assertThat(properties.get(i).getDesiredValue(), equalTo(value));
            assertThat(properties.get(i).getDesiredTime(), notNullValue());
            assertThat(properties.get(i).getReportedTime(), notNullValue());
            assertThat(properties.get(i).getReportedTime(), equalTo(properties.get(i).getDesiredTime()));
            assertThat(properties.get(i).getUnit(), equalTo(UNIT));
            assertThat(properties.get(i).getShowName(), equalTo(DISPLAY_NAME));
            assertThat(properties.get(i).getDefaultValue(), equalTo(value));
        }
    }

    private void checkDeviceProfileDeviceAttributes(DeviceAttributes device) {
        assertThat(device.getDesired().get(TEST_SCHEMA_PROPERTY_TYPE_BOOL_NAME).isBoolean(),
                equalTo(true));
        assertThat(device.getDesired().get(TEST_SCHEMA_PROPERTY_TYPE_BOOL_NAME).asBoolean(),
                equalTo(BOOL_DEFAULT_VALUE));
        assertThat(device.getReported().get(TEST_SCHEMA_PROPERTY_TYPE_BOOL_NAME).isBoolean(),
                equalTo(true));
        assertThat(device.getReported().get(TEST_SCHEMA_PROPERTY_TYPE_BOOL_NAME).asBoolean(),
                equalTo(BOOL_DEFAULT_VALUE));

        assertThat(device.getDesired().get(TEST_SCHEMA_PROPERTY_TYPE_NUMBER_NAME).isNumber(),
                equalTo(true));
        assertThat(device.getDesired().get(TEST_SCHEMA_PROPERTY_TYPE_NUMBER_NAME).asDouble(),
                equalTo(NUMBER_DEFAULT_VALUE));
        assertThat(device.getReported().get(TEST_SCHEMA_PROPERTY_TYPE_NUMBER_NAME).isNumber(),
                equalTo(true));
        assertThat(device.getReported().get(TEST_SCHEMA_PROPERTY_TYPE_NUMBER_NAME).asDouble(),
                equalTo(NUMBER_DEFAULT_VALUE));

        assertThat(device.getDesired().get(TEST_SCHEMA_PROPERTY_TYPE_STRING_NAME).isTextual(),
                equalTo(true));
        assertThat(device.getDesired().get(TEST_SCHEMA_PROPERTY_TYPE_STRING_NAME).asText(),
                equalTo(STRING_DEFAULT_VALUE));
        assertThat(device.getReported().get(TEST_SCHEMA_PROPERTY_TYPE_STRING_NAME).isTextual(),
                equalTo(true));
        assertThat(device.getReported().get(TEST_SCHEMA_PROPERTY_TYPE_STRING_NAME).asText(),
                equalTo(STRING_DEFAULT_VALUE));
    }

    private SchemaProperty createSchemaProperty(SchemaProperty.PropertyType type) {
        SchemaProperty property = new SchemaProperty()
                .withDisplayName(DISPLAY_NAME)
                .withUnit(UNIT)
                .withType(type);
        switch (type) {
            case STRING:
                property.setName(TEST_SCHEMA_PROPERTY_TYPE_STRING_NAME);
                property.setDefaultValue(STRING_DEFAULT_VALUE);
                break;
            case NUMBER:
                property.setName(TEST_SCHEMA_PROPERTY_TYPE_NUMBER_NAME);
                property.setDefaultValue(String.valueOf(NUMBER_DEFAULT_VALUE));
                break;
            case BOOL:
                property.setName(TEST_SCHEMA_PROPERTY_TYPE_BOOL_NAME);
                property.setDefaultValue(String.valueOf(BOOL_DEFAULT_VALUE));
                break;
            default:
                break;
        }
        return property;
    }

    private DeviceRuleResponse createDefaultDeviceRule() {
        DeviceRuleRequest deviceRuleRequest = new DeviceRuleRequest();
        List<DeviceRuleDestination> desList = new ArrayList<DeviceRuleDestination>(1);
        DeviceRuleDestination destination = new DeviceRuleDestination();
        destination.setKind(DeviceRuleDestination.KindType.TSDB);
        destination.setValue(TEST_TSDB_DOMAIN);
        desList.add(destination);
        deviceRuleRequest.setDestinations(desList);
        List<DeviceRuleSource> sources = new ArrayList<DeviceRuleSource>(3);
        DeviceRuleSource source = new DeviceRuleSource();
        source.setDescription("This is condition 1");
        source.setCondition("<>");
        source.setType(SchemaProperty.PropertyType.NUMBER);
        source.setName(TEST_SCHEMA_PROPERTY_TYPE_NUMBER_NAME);
        source.setValue("0");
        sources.add(source);

        DeviceRuleSource source2 = new DeviceRuleSource();
        source2.setDescription("This is condition 1");
        source2.setCondition("=");
        source2.setType(SchemaProperty.PropertyType.BOOL);
        source2.setName(TEST_SCHEMA_PROPERTY_TYPE_BOOL_NAME);
        source2.setValue("false");

        DeviceRuleSource source3 = new DeviceRuleSource();
        source3.setDescription("This is condition 1");
        source3.setCondition("=");
        source3.setType(SchemaProperty.PropertyType.STRING);
        source3.setName(TEST_SCHEMA_PROPERTY_TYPE_STRING_NAME);
        source3.setValue("");

        sources.add(source2);
        sources.add(source3);

        deviceRuleRequest.setSources(sources);
        deviceRuleRequest.setName(TEST_DEVICE_RULE);
        return client.createDeviceRule(TEST_DEVICE_NAME, deviceRuleRequest);
    }

    private AccessDetailResponse createDefaultDomain() {
        CreateDomainRequest request = new CreateDomainRequest()
                .withName(TEST_DOMAIN_NAME)
                .withDescription(INITIAL_DESCRIPTION)
                .withType(TEST_DOMAIN_TYPE);
        return client.createDomain(request);

    }

    private DeviceAccessDetailResponse createDefaultDevice() {
        CreateDeviceRequest request = new CreateDeviceRequest()
                .withDeviceName(TEST_DEVICE_NAME)
                .withDescription(INITIAL_DESCRIPTION);
        return client.createDevice(request);
    }

    private SchemaCreateResponse createDefaultSchema() {
        List<SchemaProperty> properties = Arrays.asList(
                createSchemaProperty(SchemaProperty.PropertyType.BOOL),
                createSchemaProperty(SchemaProperty.PropertyType.STRING),
                createSchemaProperty(SchemaProperty.PropertyType.NUMBER)
        );
        SchemaCreateRequest request = new SchemaCreateRequest()
                .withName(TEST_SCHEMA_NAME)
                .withDescription(INITIAL_DESCRIPTION)
                .withProperties(properties);
        return client.createSchema(request);
    }

    private DeviceAccessDetailResponse createDefaultDeviceWithSchemaId(String schemaId) {
        CreateDeviceRequest request = new CreateDeviceRequest()
                .withDeviceName(TEST_DEVICE_NAME)
                .withSchemaId(schemaId);
        return client.createDevice(request);
    }

    private ObjectNode createJsonNode(String key, String value) {
        ObjectNode node = new ObjectMapper().createObjectNode();
        node.put(key, value);
        return node;
    }

    private ObjectNode createAllTypeNode() {
        ObjectNode node = new ObjectMapper().createObjectNode();
        node.put(TEST_SCHEMA_PROPERTY_TYPE_BOOL_NAME, BOOL_DEFAULT_VALUE);
        node.put(TEST_SCHEMA_PROPERTY_TYPE_NUMBER_NAME, NUMBER_DEFAULT_VALUE);
        node.put(TEST_SCHEMA_PROPERTY_TYPE_STRING_NAME, STRING_DEFAULT_VALUE);
        return node;
    }

    private void clean() {
        removeDefaultDevice();
        deleteDefaultSchema();
    }

    private DeviceListResponse removeDefaultDevice() {
        DeviceListRequest request = new DeviceListRequest()
                .withDevices(Arrays.asList(TEST_DEVICE_NAME));
        return client.removeDevices(request);
    }

    private void deleteDefaultSchema() {
        SchemaListResponse listResponse = client.getSchemas(1, 1, "createTime", "desc", TEST_SCHEMA_NAME);
        if (listResponse.getTotalCount() == 1) {
            deleteSchemaById(listResponse.getResult().get(0).getId());
        }
    }

    private void deleteSchemaById(String schemaId) {
        client.deleteSchema(schemaId);
    }

}
