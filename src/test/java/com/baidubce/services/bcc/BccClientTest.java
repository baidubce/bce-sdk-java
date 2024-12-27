/*
 * Copyright (c) 2018-2020 Baidu.com, Inc. All Rights Reserved
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

import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.bcc.model.CreateCdsModel;
import com.baidubce.services.bcc.model.InstanceModel;
import com.baidubce.services.bcc.model.Reservation;
import com.baidubce.services.bcc.model.SecurityGroupModel;
import com.baidubce.services.bcc.model.SecurityGroupRuleModel;
import com.baidubce.services.bcc.model.StorageType;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.bcc.model.TagsOperationRequest;
import com.baidubce.services.bcc.model.asp.AttachAspRequest;
import com.baidubce.services.bcc.model.asp.CreateAspRequest;
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
import com.baidubce.services.bcc.model.deployset.ListDeploySetResponse;
import com.baidubce.services.bcc.model.deployset.UpdateDeploySetRequest;
import com.baidubce.services.bcc.model.flavor.ListBccBidFlavorResponse;
import com.baidubce.services.bcc.model.flavor.ListBccFlavorSpecResponse;
import com.baidubce.services.bcc.model.flavor.ListFlavorSpecRequest;
import com.baidubce.services.bcc.model.image.CreateImageRequest;
import com.baidubce.services.bcc.model.image.CreateImageResponse;
import com.baidubce.services.bcc.model.image.GetAvailableImagesBySpecRequest;
import com.baidubce.services.bcc.model.image.GetAvailableImagesBySpecResponse;
import com.baidubce.services.bcc.model.image.GetImageResponse;
import com.baidubce.services.bcc.model.image.ListImagesRequest;
import com.baidubce.services.bcc.model.image.ListImagesResponse;
import com.baidubce.services.bcc.model.image.ListOsRequest;
import com.baidubce.services.bcc.model.image.ListOsResponse;
import com.baidubce.services.bcc.model.image.ListSharedUserResponse;
import com.baidubce.services.bcc.model.image.RemoteCopyImageRequest;
import com.baidubce.services.bcc.model.image.ShareImageRequest;
import com.baidubce.services.bcc.model.image.UnShareImageRequest;
import com.baidubce.services.bcc.model.instance.BatchAddIpRequest;
import com.baidubce.services.bcc.model.instance.BatchAddIpResponse;
import com.baidubce.services.bcc.model.instance.BatchChangeToPrepaidRequest;
import com.baidubce.services.bcc.model.instance.BatchDeleteIpRequest;
import com.baidubce.services.bcc.model.instance.BatchRefundResourceRequest;
import com.baidubce.services.bcc.model.instance.BatchRefundResourceResponse;
import com.baidubce.services.bcc.model.instance.BatchStopInstanceRequest;
import com.baidubce.services.bcc.model.instance.BccAutoRenewRequest;
import com.baidubce.services.bcc.model.instance.BccPriceRequest;
import com.baidubce.services.bcc.model.instance.BccPriceResponse;
import com.baidubce.services.bcc.model.instance.BindSecurityGroupRequest;
import com.baidubce.services.bcc.model.instance.BindTagsRequest;
import com.baidubce.services.bcc.model.instance.CancelBidOrderResponse;
import com.baidubce.services.bcc.model.instance.ChangeInstanceSubnetRequest;
import com.baidubce.services.bcc.model.instance.ChangeToPrepaidRequest;
import com.baidubce.services.bcc.model.instance.ChangeToPrepaidResponse;
import com.baidubce.services.bcc.model.instance.ChangeVpcRequest;
import com.baidubce.services.bcc.model.instance.CreateEhcClusterRequest;
import com.baidubce.services.bcc.model.instance.CreateEhcClusterResponse;
import com.baidubce.services.bcc.model.instance.CreateInstanceRequest;
import com.baidubce.services.bcc.model.instance.CreateInstanceResponse;
import com.baidubce.services.bcc.model.instance.DeleteEhcClusterRequest;
import com.baidubce.services.bcc.model.instance.DeleteInstanceDeploysetRequest;
import com.baidubce.services.bcc.model.instance.DescribeEhcClusterListRequest;
import com.baidubce.services.bcc.model.instance.DescribeEhcClusterListResponse;
import com.baidubce.services.bcc.model.instance.GetBidInstancePriceRequest;
import com.baidubce.services.bcc.model.instance.GetBidInstancePriceResponse;
import com.baidubce.services.bcc.model.instance.GetInstanceResponse;
import com.baidubce.services.bcc.model.instance.GetInstanceVncResponse;
import com.baidubce.services.bcc.model.instance.InstanceIpv6Request;
import com.baidubce.services.bcc.model.instance.InstanceType;
import com.baidubce.services.bcc.model.instance.ListAvailableResizeSpecRequest;
import com.baidubce.services.bcc.model.instance.ListGetInstanceNoChargeRequest;
import com.baidubce.services.bcc.model.instance.ListInstanceByIdsRequest;
import com.baidubce.services.bcc.model.instance.ListInstanceEnisRequest;
import com.baidubce.services.bcc.model.instance.ListInstanceSpecsResponse;
import com.baidubce.services.bcc.model.instance.ListInstancesRequest;
import com.baidubce.services.bcc.model.instance.ListInstancesResponse;
import com.baidubce.services.bcc.model.instance.ListRecycleInstanceRequest;
import com.baidubce.services.bcc.model.instance.ModifyEhcClusterRequest;
import com.baidubce.services.bcc.model.instance.ModifyInstanceAttributesRequest;
import com.baidubce.services.bcc.model.instance.ModifyInstanceDescRequest;
import com.baidubce.services.bcc.model.instance.PaymentTiming;
import com.baidubce.services.bcc.model.instance.PurchaseReservedInstanceResponse;
import com.baidubce.services.bcc.model.instance.RelatedRenewFlagType;
import com.baidubce.services.bcc.model.instance.ReleasePrepaidInstanceResponse;
import com.baidubce.services.bcc.model.instance.ResizeInstanceRequest;
import com.baidubce.services.bcc.model.instance.UnbindSecurityGroupRequest;
import com.baidubce.services.bcc.model.instance.UnbindTagsRequest;
import com.baidubce.services.bcc.model.keypair.KeypairAttachRequest;
import com.baidubce.services.bcc.model.keypair.KeypairCreateRequest;
import com.baidubce.services.bcc.model.keypair.KeypairDeleteRequest;
import com.baidubce.services.bcc.model.keypair.KeypairDetachRequest;
import com.baidubce.services.bcc.model.keypair.KeypairDetailRequest;
import com.baidubce.services.bcc.model.keypair.KeypairImportRequest;
import com.baidubce.services.bcc.model.keypair.KeypairListRequest;
import com.baidubce.services.bcc.model.keypair.KeypairListResponse;
import com.baidubce.services.bcc.model.keypair.KeypairModel;
import com.baidubce.services.bcc.model.keypair.KeypairRenameRequest;
import com.baidubce.services.bcc.model.keypair.KeypairUpdateDescRequest;
import com.baidubce.services.bcc.model.region.DescribeRegionsRequest;
import com.baidubce.services.bcc.model.region.DescribeRegionsResponse;
import com.baidubce.services.bcc.model.reversed.ReservedTagsRequest;
import com.baidubce.services.bcc.model.securitygroup.CreateSecurityGroupRequest;
import com.baidubce.services.bcc.model.securitygroup.CreateSecurityGroupResponse;
import com.baidubce.services.bcc.model.securitygroup.DeleteSecurityGroupRuleRequest;
import com.baidubce.services.bcc.model.securitygroup.ListSecurityGroupsRequest;
import com.baidubce.services.bcc.model.securitygroup.ListSecurityGroupsResponse;
import com.baidubce.services.bcc.model.securitygroup.SecurityGroupRuleOperateRequest;
import com.baidubce.services.bcc.model.securitygroup.UpdateSecurityGroupRuleRequest;
import com.baidubce.services.bcc.model.snapshot.CreateSnapshotRequest;
import com.baidubce.services.bcc.model.snapshot.CreateSnapshotResponse;
import com.baidubce.services.bcc.model.snapshot.GetSnapshotResponse;
import com.baidubce.services.bcc.model.snapshot.ListSnapchainRequest;
import com.baidubce.services.bcc.model.snapshot.ListSnapchainResponse;
import com.baidubce.services.bcc.model.snapshot.ListSnapshotsRequest;
import com.baidubce.services.bcc.model.snapshot.ListSnapshotsResponse;
import com.baidubce.services.bcc.model.volume.AttachVolumeResponse;
import com.baidubce.services.bcc.model.volume.CancelAutoRenewVolumeClusterRequest;
import com.baidubce.services.bcc.model.volume.CancelAutoRenewVolumeRequest;
import com.baidubce.services.bcc.model.volume.CreateVolumeClusterRequest;
import com.baidubce.services.bcc.model.volume.CreateVolumeClusterResponse;
import com.baidubce.services.bcc.model.volume.CreateVolumeRequest;
import com.baidubce.services.bcc.model.volume.CreateVolumeResponse;
import com.baidubce.services.bcc.model.volume.EphemeralDisk;
import com.baidubce.services.bcc.model.volume.GetVolumeResponse;
import com.baidubce.services.bcc.model.volume.ListVolumeClustersResponse;
import com.baidubce.services.bcc.model.volume.ListVolumesRequest;
import com.baidubce.services.bcc.model.volume.ListVolumesResponse;
import com.baidubce.services.bcc.model.volume.ModifyCdsAttrRequest;
import com.baidubce.services.bcc.model.volume.ModifyVolumeChargeRequest;
import com.baidubce.services.bcc.model.volume.ModifyVolumeChargeTypeRequest;
import com.baidubce.services.bcc.model.volume.RenameVolumeRequest;
import com.baidubce.services.bcc.model.volume.ResizeVolumeRequest;
import com.baidubce.services.bcc.model.volume.VolumePriceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Objects;
import org.hamcrest.CustomMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * test class for testing bcc service
 */
@RunWith(Enclosed.class)
public class BccClientTest {


    @Ignore
    public static class BccBase {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final Logger logger = LoggerFactory.getLogger(BccClientTest.class);
        protected static String endpoint = "bcc.bj.qasandbox.baidu-int.com";
        protected final String ak = "ak";
        protected final String sk = "sk";

        protected BccClientConfiguration config;

        String instanceId = "i-1i0R0VDM";
        String instanceId2 = "i-bQ8DYWiV";
        String systemImageId = "m-NV2YX4Yp";
        String securityGroupId = "g-0cmrbr40a4yd";
        String volumeId = "v-H2oaaW8Q";
        String snapshotId = "s-vfVONjnP";
        String accountId = "accountId";
        String aspId = "asp-Zburrlfu";
        TagModel tagModel = new TagModel().withTagKey("test007").withTagValue("test007");
        List<TagModel> changeTags = new ArrayList<TagModel>();
        String groupId = "asg-3Jp4pQqN";

        public void setUp() {
            this.config = new BccClientConfiguration();
            this.config.setCredentials(new DefaultBceCredentials(ak, sk));
            this.config.setProtocol(Protocol.HTTP);
            this.config.setEndpoint(endpoint);
        }

        public void tearDown() {
            // do something
            logger.info("Base test tearDown");
        }

        public void toJsonPrettyString(String method, Object object) {
            try {
                logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        protected void expectBceServiceException(final int statusCode, final String errorCode) {
            this.thrown.expect(new CustomMatcher<Throwable>(
                    "BceServiceException [ statusCode=" + statusCode + ", errorCode=" + errorCode + "]") {
                @Override
                public boolean matches(Object item) {
                    return (item instanceof BceServiceException)
                            && ((BceServiceException) item).getStatusCode() == statusCode
                            && Objects.equal(((BceServiceException) item).getErrorCode(), errorCode);
                }
            });
        }
    }

    public static class RegionTest extends BccBase {
        protected BccClient client;
        @Before
        public void setUp() {
            super.setUp();
            String bccGlobalRegionEndpoint = "bcc.baidubce.com";
            config.setEndpoint(bccGlobalRegionEndpoint);
            client = new BccClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void describeRegions() {
            DescribeRegionsRequest describeRegionsRequest = new DescribeRegionsRequest();
            DescribeRegionsResponse describeRegionsResponse = client.describeRegions(describeRegionsRequest);
            System.out.println(describeRegionsResponse.getRegions());
            toJsonPrettyString("describeRegionsResponse", describeRegionsResponse);
        }
    }

    public static class ReservedTest extends BccBase {
        protected BccClient client;
        List<String> reservedInstanceIds = Arrays.asList("r-Qyycx1SX");
        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void bindBccReservedInstanceToTags() {
            changeTags.add(tagModel);
            client.bindReservedInstanceToTags(new ReservedTagsRequest().withInstanceIds(reservedInstanceIds)
                    .withChangeTags(changeTags));
        }

        @Test(expected = IllegalArgumentException.class)
        public void bindBccReservedInstanceToTagsWithEmptyIds() {
            changeTags.add(tagModel);
            client.bindReservedInstanceToTags(new ReservedTagsRequest().withInstanceIds(Collections.<String>emptyList())
                    .withChangeTags(changeTags));
        }

        @Test(expected = IllegalArgumentException.class)
        public void bindBccReservedInstanceToTagsWithEmptyTags() {
            changeTags.add(tagModel);
            client.bindReservedInstanceToTags(new ReservedTagsRequest().withInstanceIds(reservedInstanceIds)
                    .withChangeTags(Collections.<TagModel>emptyList()));
        }

        @Test(expected = IllegalArgumentException.class)
        public void bindBccReservedInstanceToTagsWithEmptyTagKey() {
            changeTags.add(tagModel);
            changeTags.add(new TagModel().withTagKey("").withTagValue("test007"));
            client.bindReservedInstanceToTags(new ReservedTagsRequest().withInstanceIds(reservedInstanceIds)
                    .withChangeTags(Collections.<TagModel>emptyList()));
        }

        @Test
        public void unBindBccReservedInstanceFromTags() {
            changeTags.add(tagModel);
            client.unbindReservedInstanceFromTags(new ReservedTagsRequest().withInstanceIds(reservedInstanceIds)
                    .withChangeTags(changeTags));
        }

        @Test
        public void bindTagsBatchByResourceType() {
            changeTags.add(tagModel);
            TagsOperationRequest tagsOperationRequest = new TagsOperationRequest();
            tagsOperationRequest.setTags(changeTags);
            tagsOperationRequest.setResourceType("bccri");
            tagsOperationRequest.setResourceIds(reservedInstanceIds);
            client.bindTagsBatchByResourceType(tagsOperationRequest);
        }

        @Test(expected = IllegalArgumentException.class)
        public void bindTagsBatchByResourceTypeEmptyType() {
            changeTags.add(tagModel);
            TagsOperationRequest tagsOperationRequest = new TagsOperationRequest();
            tagsOperationRequest.setTags(changeTags);
            tagsOperationRequest.setResourceIds(reservedInstanceIds);
            client.bindTagsBatchByResourceType(tagsOperationRequest);
        }

        @Test(expected = IllegalArgumentException.class)
        public void bindTagsBatchByResourceTypeEmptyTags() {
            TagsOperationRequest tagsOperationRequest = new TagsOperationRequest();
            tagsOperationRequest.setResourceType("bccri");
            tagsOperationRequest.setResourceIds(reservedInstanceIds);
            client.bindTagsBatchByResourceType(tagsOperationRequest);
        }

        @Test(expected = IllegalArgumentException.class)
        public void bindTagsBatchByResourceTypeErrorType() {
            TagsOperationRequest tagsOperationRequest = new TagsOperationRequest();
            tagsOperationRequest.setResourceType("error");
            tagsOperationRequest.setResourceIds(reservedInstanceIds);
            client.bindTagsBatchByResourceType(tagsOperationRequest);
        }

        @Test
        public void unbindTagsBatchByResourceType() {
            changeTags.add(tagModel);
            TagsOperationRequest tagsOperationRequest = new TagsOperationRequest();
            tagsOperationRequest.setTags(changeTags);
            tagsOperationRequest.setResourceType("bccri");
            tagsOperationRequest.setResourceIds(reservedInstanceIds);
            client.unbindTagsBatchByResourceType(tagsOperationRequest);
        }

        @Test(expected = IllegalArgumentException.class)
        public void unbindTagsBatchByResourceTypeEmptyType() {
            changeTags.add(tagModel);
            TagsOperationRequest tagsOperationRequest = new TagsOperationRequest();
            tagsOperationRequest.setTags(changeTags);
            tagsOperationRequest.setResourceIds(reservedInstanceIds);
            client.unbindTagsBatchByResourceType(tagsOperationRequest);
        }

        @Test(expected = IllegalArgumentException.class)
        public void unbindTagsBatchByResourceTypeEmptyTags() {
            TagsOperationRequest tagsOperationRequest = new TagsOperationRequest();
            tagsOperationRequest.setResourceIds(reservedInstanceIds);
            tagsOperationRequest.setResourceType("bccri");
            client.unbindTagsBatchByResourceType(tagsOperationRequest);
        }
    }
    /**
     * Test case about instance begin
     */
    public static class InstanceTest extends BccBase {
        protected BccClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void test_RequestWithInvalidCredential() {
            this.expectBceServiceException(403, "AccessForbidden");
            this.client.listInstances(new ListInstancesRequest()
                    .withRequestCredentials(new DefaultBceCredentials("test", "test")));
        }

        private CreateInstanceRequest createInstanceWithSpecRequest() {
            EphemeralDisk ephemeralDisk = new EphemeralDisk();
            ephemeralDisk.setSizeInGB(5);
            List<EphemeralDisk> ephemeralDiskList = new ArrayList<EphemeralDisk>();
            ephemeralDiskList.add(ephemeralDisk);
            return new CreateInstanceRequest()
//                    .withSpec("bcc.ic3.c1m1")
                    .withSpec("bcc.g3.c4m12")
                    .withName("bcc-sdk" + System.currentTimeMillis())
                    .withZoneName("cn-gz-b")
//                    .withEphemeralDisks(ephemeralDiskList)
                    .withImageId(systemImageId)
                    .withBidModel("market")
                    .withAspId(aspId)
//                    .withCreateCdsList(Arrays.asList(new CreateCdsModel().withCdsSizeInGB(5)))
                    .withBilling(new Billing().withPaymentTiming("Postpaid"));
        }

        @Test
        public void createInstanceWithSpec() throws GeneralSecurityException {
            CreateInstanceRequest instanceRequest = createInstanceWithSpecRequest();
            CreateInstanceResponse response = client.createInstanceBySpec(instanceRequest);
            assertThat(response.getInstanceIds(), notNullValue());
            toJsonPrettyString("createInstance", response.getInstanceIds());
        }

        private BccPriceRequest bccSpecPriceRequest() {
            return new BccPriceRequest()
                    .withSpec("bcc.d1.c12m48")
                    .withPaymentTiming("Prepaid")
                    .withZoneName("cn-gz-b")
                    .withPurchaseCount(1)
                    .withPurchaseLength(1);
        }

        private BccPriceRequest bccSpecIdPriceRequest() {
            return new BccPriceRequest()
                    .withSpecId("g1")
                    .withPaymentTiming("Postpaid")
                    .withZoneName("cn-gz-b")
                    .withPurchaseCount(1)
                    .withPurchaseLength(1);
        }

        @Test
        public void getPriceBySpec() {
            BccPriceRequest request = bccSpecIdPriceRequest();
            BccPriceResponse response = client.getPriceBySpec(request);
            toJsonPrettyString("getPriceBySpec", response);
        }

        @Test
        public void listBidFlavor() {
            ListBccBidFlavorResponse response = client.listBidFlavor();
            toJsonPrettyString("listBidFlavor", response);
        }

        private GetBidInstancePriceRequest getBidInstancePriceRequest() {
            List<CreateCdsModel> createCdsList = new ArrayList<CreateCdsModel>();
            CreateCdsModel createCdsModel = new CreateCdsModel().withStorageType(StorageType.hp1.name()).withCdsSizeInGB(5);
            createCdsList.add(createCdsModel);
            return new GetBidInstancePriceRequest()
                    .withInstanceType("N1")
                    .withCpuCount(2)
                    .withMemoryCapacityInGB(2)
                    .withRootDiskSizeInGb(45)
                    .withRootDiskStorageType("cloud_hp1")
                    .withCreateCdsList(createCdsList)
                    .withPurchaseCount(1);
        }

        @Test
        public void getBidInstancePrice() {
            GetBidInstancePriceRequest getBidInstancePriceRequest = getBidInstancePriceRequest();
            GetBidInstancePriceResponse response = client.getBidInstancePrice(getBidInstancePriceRequest);
            toJsonPrettyString("getBidInstancePrice", response);
        }

        @Test
        public void listFlavorSpecByZoneName() {
            ListBccFlavorSpecResponse response = client.listFlavorSpec(
                    new ListFlavorSpecRequest().withZoneName("cn-bj-b"));
            toJsonPrettyString("listFlavorSpecByZoneName", response);
        }

        private CreateInstanceRequest createInstanceWithWithCpuMemoryRequest() {
            return new CreateInstanceRequest()
                    .withInstanceType(InstanceType.N3.name())
                    .withCpuCount(1)
                    .withMemoryCapacityInGB(1)
                    .withName("bcc-sdk" + System.currentTimeMillis())
                    .withHostname("sdkhostnametest")
                    .withAutoSeqSuffix(true)
                    .withPurchaseCount(2)
                    .withImageId("m-dPS2DoDi")
//                    .withCreateCdsList(Arrays.asList(new CreateCdsModel().withCdsSizeInGB(5)))
                    .withBilling(new Billing().withPaymentTiming("Postpaid"));
        }

        // create N1/N2/N3
        @Test
        public void createInstanceWithCpuMemory() throws GeneralSecurityException {
            CreateInstanceRequest instanceRequest = createInstanceWithWithCpuMemoryRequest();
            CreateInstanceResponse response = client.createInstance(instanceRequest);
            assertThat(response.getInstanceIds(), notNullValue());
            toJsonPrettyString("createInstance", response.getInstanceIds());
        }

        @Test
        public void createInstanceWithCpuMemoryHiddenSn() throws GeneralSecurityException {
            CreateInstanceRequest instanceRequest = createInstanceWithWithCpuMemoryRequest();
            instanceRequest.setDisableRootDiskSerial(true);
            CreateInstanceResponse response = client.createInstance(instanceRequest);
            assertThat(response.getInstanceIds(), notNullValue());
            toJsonPrettyString("createInstance", response.getInstanceIds());
        }

        @Test
        public void createInstanceWithTags() {
            CreateInstanceRequest instanceRequest = createInstanceWithWithCpuMemoryRequest();
            List<TagModel> tagModels = new ArrayList<TagModel>();
            tagModels.add(new TagModel().withTagKey("test").withTagValue("test"));
            instanceRequest.withRelationTag(false).withTags(tagModels);
            CreateInstanceResponse response = client.createInstance(instanceRequest);
            toJsonPrettyString("createInstanceWithTags", response.getInstanceIds());
        }

        @Test
        public void createInstanceWithAspId() {
            CreateInstanceRequest instanceRequest = new CreateInstanceRequest()
                    .withInstanceType(InstanceType.N3.name())
                    .withCpuCount(1)
                    .withMemoryCapacityInGB(1)
                    .withName("bcc-sdk" + System.currentTimeMillis())
                    .withImageId(systemImageId)
                    .withCreateCdsList(Arrays.asList(new CreateCdsModel().withCdsSizeInGB(5)))
                    .withZoneName("cn-gz-b")
                    .withBilling(new Billing().withPaymentTiming("Postpaid"))
                    .withAspId(aspId);
            CreateInstanceResponse response = client.createInstance(instanceRequest);
            toJsonPrettyString("createInstanceWithAspId", response.getInstanceIds());
        }

        //        @Test
        public void createInstanceWithZoneName() {
            CreateInstanceRequest instanceRequest = createInstanceWithWithCpuMemoryRequest();
            List<EphemeralDisk> ephemeralDisks = new ArrayList<EphemeralDisk>();
            ephemeralDisks.add(new EphemeralDisk().withSizeInGB(5));
            instanceRequest.withZoneName("cn-bj-b").withEphemeralDisks(ephemeralDisks);
            CreateInstanceResponse response = client.createInstance(instanceRequest);
            toJsonPrettyString("createDedicatedInstance", response.getInstanceIds());
        }

        //        @Test
        public void createDedicatedInstance() {
            CreateInstanceRequest instanceRequest = new CreateInstanceRequest();
            List<EphemeralDisk> ephemeralDisks = new ArrayList<EphemeralDisk>();
            ephemeralDisks.add(new EphemeralDisk().withSizeInGB(25).withStorageType("ssd"));
            instanceRequest.withCpuCount(1).withMemoryCapacityInGB(1).withImageId(systemImageId)
                    .withDedicatedHostId("d-jOruT2Hw").withPurchaseCount(2)
                    .withEphemeralDisks(ephemeralDisks);
            CreateInstanceResponse response = client.createInstance(instanceRequest);
            toJsonPrettyString("createDedicatedInstance", response.getInstanceIds());
        }

        private CreateInstanceRequest createBidInstanceWithCpuMemoryRequest() {
            return new CreateInstanceRequest()
                    .withInstanceType(InstanceType.N3.name())
                    .withCpuCount(1)
                    .withMemoryCapacityInGB(4)
                    .withName("bid-bcc-sdk" + System.currentTimeMillis())
                    .withHostname("sdkhostnametest")
                    .withAutoSeqSuffix(true)
                    .withPurchaseCount(2)
                    .withImageId("m-dPS2DoDi")
                    .withCreateCdsList(Arrays.asList(new CreateCdsModel().withCdsSizeInGB(5)))
                    .withBilling(new Billing().withPaymentTiming("Postpaid").withPaymentTiming("bidding"))
                    .withZoneName("cn-gz-b")
                    .withBidModel("custom")
                    .withBidPrice("0.0254");
        }

        @Test
        public void createBidInstance() {
            CreateInstanceRequest instanceRequest = createBidInstanceWithCpuMemoryRequest();
            CreateInstanceResponse response = client.createBidInstance(instanceRequest);
            toJsonPrettyString("createBidInstance", response.getInstanceIds());
        }

        @Test
        public void createBidInstanceWithAspId() {
            CreateInstanceRequest instanceRequest = createBidInstanceWithCpuMemoryRequest();
            instanceRequest.withAspId(aspId);
            CreateInstanceResponse response = client.createBidInstance(instanceRequest);
            toJsonPrettyString("createBidInstance", response.getInstanceIds());
        }

        @Test
        public void listInstance() {
            ListInstancesResponse response = client.listInstances(new ListInstancesRequest());
            assertThat(response.getInstances(), notNullValue());
            toJsonPrettyString("listInstance", response);
        }

        @Test
        public void listInstanceByIds() {
            ListInstanceByIdsRequest request = new ListInstanceByIdsRequest();
            request.setInstanceIds(Arrays.asList("i-9Ow9E***","i-keLMS***"));
            ListInstancesResponse response = client.listInstanceByIds(request);
            assertThat(response.getInstances(), notNullValue());
            toJsonPrettyString("listInstanceByIds", response);
        }

        @Test
        public void listInstanceByZoneName() {
            ListInstancesResponse response = client.listInstances(new ListInstancesRequest().withZoneName("cn-bj-b"));
            toJsonPrettyString("listInstanceByZoneName", response);
        }

        @Test
        public void listInstanceByEhcCluster() {
            ListInstancesResponse response =
                    client.listInstances(new ListInstancesRequest().withEhcClusterId("ehc-bk4hM1N3"));
            toJsonPrettyString("listInstanceByZoneName", response);
            System.out.println(response);
        }

        @Test
        public void getInstance() {
            GetInstanceResponse response = client.getInstance("i-CUgRx5Os");
            assertThat(response.getInstance(), notNullValue(InstanceModel.class));
            toJsonPrettyString("getInstance", response.getInstance());
        }

        @Test
        public void getInstanceNotExist() {
            this.expectBceServiceException(404, "NoSuchObject");
            client.getInstance("asdfghjkl");
        }

        @Test
        public void startInstance() {
            client.startInstance(instanceId);
        }

        @Test
        public void stopInstance() {
            client.stopInstance(instanceId, true);
        }

        @Test
        public void batchStopInstance() {
            BatchStopInstanceRequest request = new BatchStopInstanceRequest();
            request.setInstanceIds(Arrays.asList("i-KEmEvelA","i-0nPl9WFJ"));
            request.setForceStop(true);
            request.setStopWithNoCharge(false);
            client.batchStopInstance(request);
        }

        //        @Test
        public void rebootInstance() {
            client.rebootInstance(instanceId, true);
        }

        //        @Test
        public void changePassInstance() {
            try {
                client.modifyInstancePassword(instanceId, "1qaz@WSX");
            } catch (BceClientException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void changeHostnameInstance() {
            try {
                client.modifyInstanceHostname("i-eqq2Sqkl", "hostnamechange", true, true);
            } catch (BceClientException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void modifyAttributeInstance() {
            client.modifyInstanceAttributes(
                    new ModifyInstanceAttributesRequest()
                            .withInstanceId(instanceId)
                            .withName("name" + System.currentTimeMillis())
                            .withNetEthQueueCount(3)
            );
        }

        @Test
        public void modifyDescInstance() {
            client.modifyInstanceDesc(
                    new ModifyInstanceDescRequest()
                            .withInstanceId("i-5FUtWFAs")
                            .withDesc("desc" + System.currentTimeMillis())
            );
        }


        @Test
        public void rebuildInstance() {
            client.rebuildInstance(instanceId, systemImageId, "1qaz@WSX", "k-jGp7LjFi");
        }

        @Test
        public void rebuildBatchInstance() {
            List<String> instanceIds = new ArrayList<String>();
            instanceIds.add(instanceId);
            instanceIds.add(instanceId2);
            client.rebuildBatchInstance(instanceIds, systemImageId, "1qaz@WSX", "k-jGp7LjFi");
        }

        //        @Test
        public void releaseInstance() {
            client.releaseInstance(instanceId);
        }

        @Test
        public void resizeInstance() {
            client.resizeInstance(new ResizeInstanceRequest()
                    .withInstanceId("i-M1qlCk90").withCpuCount(2).withMemoryCapacityInGB(2)
            );
        }

        @Test
        public void resizeInstanceBySpec() {
            client.resizeInstanceBySpec(new ResizeInstanceRequest()
                    .withInstanceId("i-jL2s2hBI").withSpec("bcc.lgn1.c12m40.1p4"));
        }

        //        @Test
        public void bindSecurityGroupInstance() {
            client.bindInstanceToSecurityGroup(new BindSecurityGroupRequest()
                    .withInstanceId(instanceId).withSecurityGroupId(securityGroupId)
            );
        }

        //        @Test
        public void unbindSecurityGroupInstance() {
            client.unbindInstanceFromSecurityGroup(new UnbindSecurityGroupRequest()
                    .withInstanceId(instanceId).withSecurityGroupId(securityGroupId)
            );
        }

        @Test
        public void bindInstanceToTags() {
            changeTags.add(tagModel);
            client.bindInstanceToTags(new BindTagsRequest().withInstanceId(instanceId)
                    .withChangeTags(changeTags));
        }

        @Test
        public void unbindInstanceFromTags() {
            changeTags.add(tagModel);
            client.unbindInstanceFromTags(new UnbindTagsRequest().withInstanceId(instanceId)
                    .withChangeTags(changeTags));
        }

        //        @Test
        public void getInstanceVnc() {
            GetInstanceVncResponse response = client.getInstanceVnc(instanceId);
            assertThat(response.getVncUrl(), notNullValue());
            toJsonPrettyString("getInstanceSpec", response.getVncUrl());
        }

        @Test
        public void getInstanceSpec() {
            ListInstanceSpecsResponse response = client.listInstanceSpecs();
            assertThat(response.getInstanceTypes(), notNullValue());
            toJsonPrettyString("getInstanceSpec", response.getInstanceTypes());
        }

        private ListGetInstanceNoChargeRequest listGetInstanceNoChargeRequest() {
            return new ListGetInstanceNoChargeRequest()
//                          .withInternalIp("bcc.d1.c12m48")
//                          .withKeypairId("Prepaid")
                    .withZoneName("cn-bj-b");
        }

        @Test
        public void getInstanceNoChargeList() {
            ListGetInstanceNoChargeRequest listGetInstanceNoChargeRequest = listGetInstanceNoChargeRequest();
            ListInstancesResponse response = client.getInstanceNoChargeList(listGetInstanceNoChargeRequest);
            toJsonPrettyString("getInstanceNoChargeList", response);
        }

        private ChangeToPrepaidRequest changeToPrepaidRequest() {
            return new ChangeToPrepaidRequest().withInstanceId(instanceId).withDuration(3).withRelationCds(true);
        }

        @Test
        public void changeToPrepaid() {
            ChangeToPrepaidRequest changeToPrepaidRequest = changeToPrepaidRequest();
            ChangeToPrepaidResponse response = client.changeToPrepaid(changeToPrepaidRequest);
            toJsonPrettyString("changeToPrepaid", response);
        }

        @Test
        public void releaseInstanceByPost() {
            client.releaseInstanceByPost(instanceId, true, true);
        }

        @Test
        public void releasePrepaidInstanceByPost() {
            ReleasePrepaidInstanceResponse releasePrepaidInstanceResponse =
                    client.releasePrepaidInstanceByPost(instanceId, true, true, true);
            System.out.println(releasePrepaidInstanceResponse);
        }



        @Test
        public void releaseRecycledInstance() {
            client.releaseRecycledInstance("i-ad2UViaC");
        }

        @Test
        public void releaseMultipleInstancesByPost() {
            client.releaseMultipleInstancesByPost(Arrays.asList(instanceId),
                    true,
                    true,
                    true,
                    true);
        }

        private ChangeInstanceSubnetRequest changeInstanceSubnetRequest() {
            return new ChangeInstanceSubnetRequest()
                    .withInstanceId(instanceId)
                    .withSubnetId("sbn-6xpdu51g1n5j")
//                    .withSubnetId("sbn-hwj93wes5xdt")
                    .withReboot(false);
        }

        @Test
        public void updateInstanceSubnet() {
            ChangeInstanceSubnetRequest changeInstanceSubnetRequest = changeInstanceSubnetRequest();
            changeInstanceSubnetRequest
                    .withInstanceId("i-pKq2Bnhf")
                    .withSubnetId("sbn-m1dr1mn5d6t9")
                    .withSecurityGroupIds(Collections.singletonList("g-dt6xvzpcp93m"));
            client.updateInstanceSubnet(changeInstanceSubnetRequest);
        }

        @Test
        public void deleteInstanceDeploySet() {
            DeleteInstanceDeploysetRequest deleteInstanceDeploysetRequest = new DeleteInstanceDeploysetRequest();
            deleteInstanceDeploysetRequest
                    .withDeployId("dset-NXyUzFou")
                    .withInstanceIdList(Collections.singletonList("i-pKq2Bnhf"));
            client.deleteInstanceDeploySet(deleteInstanceDeploysetRequest);
        }

        @Test
        public void deleteRecycledInstance() {
            client.deleteRecycledInstance("i-P5Prfq01");
        }

        @Test
        public void cancelBidOrder() {
            CancelBidOrderResponse response = client.cancelBidOrder("227c31b165754c37b452d8dd47b86175");
            toJsonPrettyString("cancelBidOrder", response);
        }

        @Test
        public void purchaseReservedInstance() {
            PurchaseReservedInstanceResponse response = client.purchaseReservedInstance(instanceId, 2,
                    "month", RelatedRenewFlagType.CDS.name());
            System.out.println(response);
        }

        @Test
        public void createAutoRenewRule() {
            client.createAutoRenewRule(new BccAutoRenewRequest()
                    .withInstanceId("i-dl2s537H")
                    .withRenewTimeUnit("month")
                    .withRenewTime(1)
                    .withRenewEip(true)
                    .withRenewCds(true));
        }

        @Test
        public void deleteAutoRenewRule() {
            client.deleteAutoRenewRule(new BccAutoRenewRequest()
                    .withInstanceId("i-dl2s537H")
                    .withRenewEip(true)
                    .withRenewCds(true));
        }

        @Test
        public void testBatchChangeToPrepaid() {
            ChangeToPrepaidRequest changeToPrepaidRequest = new ChangeToPrepaidRequest()
                    .withInstanceId("i-kfnD7UQ7")
                    .withDuration(1)
                    .withAutoRenew(true)
                    .withAutoRenewPeriod(1);

            BatchChangeToPrepaidRequest request = new BatchChangeToPrepaidRequest()
                    .withConfig(Arrays.asList(changeToPrepaidRequest));
            client.batchChangeToPrepaid(request);
        }

        @Test
        public void testListRecycleInstance() {
            ListRecycleInstanceRequest request = new ListRecycleInstanceRequest()
                    .withPaymentTiming(PaymentTiming.Postpaid);
            client.listRecycleInstance(request);
        }

        @Test
        public void testListAvailableResizeSpec() {
            ListAvailableResizeSpecRequest request = new ListAvailableResizeSpecRequest()
                    .withSpec("bcc.g4.c1m1");
            client.listAvailableResizeSpec(request);
        }

        @Test
        public void testChangeVpc() {
            ChangeVpcRequest request = new ChangeVpcRequest().withInstanceId("");
            client.changeVpc(request);
        }

        @Test
        public void testListInstanceEnis() {
            ListInstanceEnisRequest request = new ListInstanceEnisRequest().withInstanceId("i-CH47zOoW");
            client.listInstanceEnis(request);
        }

        @Test
        public void testCreateEhcCluster() {
            CreateEhcClusterRequest request = new CreateEhcClusterRequest();
            request.withName("test-ehcCluster").withDescription("test-description").withZoneName("cn-bj-b");
            CreateEhcClusterResponse response = client.createEhcCluster(request);
            System.out.println(response);
        }

        @Test
        public void testEhcClusterList() {
            DescribeEhcClusterListRequest request = new DescribeEhcClusterListRequest();
            request.withEhcClusterIdList(Arrays.asList("ehc-PmutHoRZ"));
            DescribeEhcClusterListResponse describeEhcClusterListResponse = client.ehcClusterList(request);
            System.out.println(describeEhcClusterListResponse);
        }

        @Test
        public void testModifyEhcCluster() {
            ModifyEhcClusterRequest request = new ModifyEhcClusterRequest();
            request.withEhcClusterId("ehc-PmutHoRZ").withName("test-modify").withDescription("");
            client.modifyEhcCluster(request);
        }

        @Test
        public void testDeleteEhcCluster() {
            DeleteEhcClusterRequest request = new DeleteEhcClusterRequest();
            request.withEhcClusterIdList(Arrays.asList("ehc-PmutHoRZ"));
            client.deleteEhcCluster(request);
        }

    }
    /**
     * Test case about instance end
     */


    /**
     * Test case about volume begin
     */
    public static class VolumeTest extends BccBase {
        protected BccClient client;
        private List<String> volumes = new ArrayList<String>();

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
//            volumes.addAll(client.createVolume(createVolumeRequest()).getVolumeIds());
        }

        @After
        public void tearDown() {
            // do something
            for (String vid : volumes) {
//                client.releaseVolume(vid);
            }
            super.tearDown();
        }

        private CreateVolumeRequest createVolumeRequest() {
            return new CreateVolumeRequest()
                    .withCdsSizeInGB(5)
                    .withPurchaseCount(1).withBilling(new Billing().withPaymentTiming("Postpaid"))
                    .withChargeType("Postpaid");
        }

        @Test
        public void createVolume() {
            CreateVolumeRequest request = new CreateVolumeRequest().withBilling(new Billing().withPaymentTiming("Prepaid")
                            .withReservation(new Reservation().withReservationLength(1).withReservationTimeUnit("month")))
                    .withCdsSizeInGB(5).withPurchaseCount(1)
                    .withChargeType("Postpaid");
            CreateVolumeResponse response = client.createVolume(request);
            assertThat(response.getVolumeIds(), notNullValue());
            toJsonPrettyString("createVolume", response.getVolumeIds());
            volumes.addAll(response.getVolumeIds());
        }

        @Test
        public void syncCreateVolume() {
            CreateVolumeRequest request = new CreateVolumeRequest().withCdsSizeInGB(5).withPurchaseCount(1)
                    .withChargeType("Postpaid");
            CreateVolumeResponse response = client.syncCreateVolume(request);
            System.out.println(response);
            System.out.println(response.getVolumes().get(0).getVolumeUuid());
            assertThat(response.getVolumeIds(), notNullValue());
            toJsonPrettyString("createVolume", response.getVolumeIds());
            volumes.addAll(response.getVolumeIds());
        }

        //        @Test
        public void createVolumeWhitZoneName() {
            CreateVolumeResponse response = client.createVolume(createVolumeRequest().withZoneName("cn-bj-b"));
            assertThat(response.getVolumeIds(), notNullValue());
            toJsonPrettyString("createVolume", response.getVolumeIds());
            volumes.addAll(response.getVolumeIds());
        }

        @Test
        public void listVolume() {
            ListVolumesResponse response = client.listVolumes();
            assertThat(response.getVolumes(), notNullValue());
            toJsonPrettyString("listVolume", response);
        }

        @Test
        public void listVolumeWithEbcDiskSize() {
            ListVolumesRequest request = new ListVolumesRequest();
            request.setInstanceId("i-Jq59glX4");
            ListVolumesResponse response = client.listVolumes(request);
            assertThat(response.getVolumes(), notNullValue());
            toJsonPrettyString("listVolume", response);
        }

        @Test
        public void listVolumeByZoneName() {
            ListVolumesRequest request = new ListVolumesRequest().withZoneName("cn-bj-b");
            toJsonPrettyString("listVolumeByZoneName", client.listVolumes(request));
        }

        @Test
        public void getVolume() {
            volumeId = "v-haqDwjGF";
            GetVolumeResponse response = client.getVolume(volumeId);
            assertThat(response.getVolume(), notNullValue());
            toJsonPrettyString("getVolume", response);
        }

        //        @Test
        public void attachVolume() {
            AttachVolumeResponse response = client.attachVolume(volumeId, instanceId);
            assertThat(response.getVolumeAttachment(), notNullValue());
            toJsonPrettyString("attachVolume", response);
        }

        //        @Test
        public void detachVolume() {
            client.detachVolume(volumeId, instanceId);
        }

        @Test
        public void deleteVolume() {
            volumeId = "v-98ipMOV7";
            client.releaseVolume(volumeId);
        }

        //        @Test
        public void resizeVolume() {
            client.resizeVolume(
                    new ResizeVolumeRequest()
                            .withVolumeId(volumeId)
                            .withNewCdsSizeInGB(15)
            );
        }

        //        @Test
        public void rollbackVolume() {
            client.rollbackVolume(volumeId, snapshotId);

        }

        @Test
        public void autoRenewVolume() {
            client.autoRenewVolume("v-iutc25rt", "month", 3);
        }

        @Test
        public void cancelAutoRenewVolume() {
            CancelAutoRenewVolumeRequest request = new CancelAutoRenewVolumeRequest().withVolumeId("v-b1amqdP0");
            client.cancelAutoRenewVolume(request);
        }

        @Test
        public void renameVolume() {
            volumeId = "v-iutc25rt";
            RenameVolumeRequest request = new RenameVolumeRequest();
            String newName = UUID.randomUUID().toString();//volume-b0ut4x20
            request.setName(newName);
            request.setVolumeId(volumeId);
            client.renameVolume(request);
        }

        @Test
        public void modifyCdsAttribute() {
            volumeId = "v-uHSyEnJe";
            ModifyCdsAttrRequest request = new ModifyCdsAttrRequest();
            request.setCdsName("new_CDS_Name");
            request.setDesc("new desc...");
            request.setCdsId("v-b1amqdP0");
            client.modifyCdsAttribute(request);
        }

        @Test
        public void modifyVolumeChargeType() {
            volumeId = "v-ZxRIF434";
            ModifyVolumeChargeRequest request = new ModifyVolumeChargeRequest();
            request.setVolumeId(volumeId);
            request.setBillingMethod("postpay");
//            request.setReservationLength(1);
            client.modifyVolumeChargeType(request);
        }

        @Test
        public void modifyVolumeChargeType2() {
            volumeId = "v-6WeHyS3w";
            ModifyVolumeChargeTypeRequest request = new ModifyVolumeChargeTypeRequest();
            request.setVolumeId(volumeId);
//            Billing billing = new Billing().withPaymentTiming("Postpaid").withReservation(new Reservation().withReservationLength(2));
//            request.setBilling(billing);
            client.modifyVolumeChargeType(request);
        }

        @Test
        public void getVolumePrice() {
            VolumePriceRequest request = new VolumePriceRequest();
            request.setPurchaseLength(12);
            request.setStorageType("cloud_hp1");
            request.setCdsSizeInGB(10);
            request.setPaymentTiming("Postpaid");
            request.setPurchaseCount(1);
            request.setZoneName("cn-bj-b");
            client.getCdsPrice(request);
        }
    }

    /**
     * Test case about volume end
     */


    /**
     * Test case about image begin
     */
    public static class ImageTest extends BccBase {
        protected BccClient client;
        private List<String> vids = new ArrayList<String>();

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
        }

        @After
        public void tearDown() {
            // do something
            for (String id : vids) {
                client.deleteImage(id);
            }
            super.tearDown();
        }

        private CreateImageRequest createImageFromInstanceRequest() {
            return new CreateImageRequest()
                    .withInstanceId(instanceId)
                    .withImageName("imagecdh" + System.currentTimeMillis());
        }

        private CreateImageRequest createImageFromSnapshotRequest() {
            return new CreateImageRequest().withSnapshotId(snapshotId)
                    .withImageName("from-snapshot" + System.currentTimeMillis());
        }

        @Test
        public void createImageFromInstanceId() {
            CreateImageResponse response = client.createImage(createImageFromInstanceRequest());
            assertThat(response.getImageId(), notNullValue());
            toJsonPrettyString("createImage", response);
        }

        @Test
        public void createImageFromSnapshot() {
            CreateImageResponse response = client.createImage(createImageFromSnapshotRequest());
            assertThat(response.getImageId(), notNullValue());
            toJsonPrettyString("createImage", response);
        }

        private CreateImageRequest createImageFromInstanceIdRequestWithRelatedCds() {
            return new CreateImageRequest().withInstanceId(instanceId2)
                    .withImageName("from-instanceId" + System.currentTimeMillis())
                    .withRelateCds(true);
        }

        @Test
        public void createImageFromInstanceIdWithRelatedCds() {
            CreateImageResponse response = client.createImage(createImageFromInstanceIdRequestWithRelatedCds());
            assertThat(response.getImageId(), notNullValue());
            toJsonPrettyString("createImage", response);
        }

        @Test
        public void listImage() {
            ListImagesResponse response = client.listImages(new ListImagesRequest());
            assertThat(response.getImages(), notNullValue());
            toJsonPrettyString("listImage", response.getImages());
        }


        @Test
        public void getImage() {
            GetImageResponse response = client.getImage("m-JJqdRqhx");
            assertThat(response.getImage(), notNullValue());
            toJsonPrettyString("getImage", response);
        }

        //        @Test
        private ShareImageRequest shareImageRequest() {
            return new ShareImageRequest().withAccountId(accountId)
                    .withImageId(systemImageId);
        }

        @Test
        public void shareImage() {
            client.shareImage(shareImageRequest());
        }

        private UnShareImageRequest unShareImageRequest() {
            return new UnShareImageRequest().withAccountId(accountId)
                    .withImageId(systemImageId);
        }

        @Test
        public void unShareImage() {
            client.unShareImage(unShareImageRequest());
        }

        @Test
        public void listSharedUser() {
            ListSharedUserResponse response = client.listSharedUser("m-0UTwGUnR");
            assertThat(response.getUsers(), notNullValue());
            toJsonPrettyString("listImage", response.getUsers());
        }

        private RemoteCopyImageRequest remoteCopyImageRequest() {
            ArrayList<String> regions = new ArrayList<String>();
            regions.add("bj");
            regions.add("gz");
            return new RemoteCopyImageRequest().withImageId(systemImageId)
                    .withname("i-K3QkY5CQimage")
                    .withDestRegion(regions);
        }

        @Test
        public void remoteCopyImage() {
            client.remoteCopyImage(remoteCopyImageRequest());
        }

        @Test
        public void cancelRemoteCopyImage() {
            client.cancelRemoteCopyImage("m-sTsSJmNs");
        }

        //        @Test
        public void deleteImage() {
            client.deleteImage("m-lgcPsq0B");
        }

        @Test
        public void listOs() {
            ListOsRequest request = new ListOsRequest();
            List<String> ids = new ArrayList<String>();
            ids.add(instanceId);
            ids.add(instanceId2);
            request.setInstanceIds(ids);
            ListOsResponse response = client.listOs(request);
            assertThat(response.getOsInfo(), notNullValue());
            toJsonPrettyString("listOs", response.getOsInfo());
        }
    }

    /**
     * Test case about image end
     */


    /**
     * Test case about snapshot begin
     */
    public static class SnapshotTest extends BccBase {
        protected BccClient client;
        private List<String> snapshots = new ArrayList<String>();

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
//            snapshots.add(client.createSnapshot(createSnapshotRequest()).getSnapshotId());
        }

        @After
        public void tearDown() {
            // do something
            for (String id : snapshots) {
                client.deleteSnapshot(id);
            }
            super.tearDown();
        }

        private CreateSnapshotRequest createSnapshotRequest() {
            return new CreateSnapshotRequest()
                    .withVolumeId(volumeId).withDesc("desc_snapshot" + System.currentTimeMillis())
                    .withSnapshotName("name-snapshot" + System.currentTimeMillis());
        }

        //        @Test
        public void createSnapshot() {
            CreateSnapshotResponse response = client.createSnapshot(createSnapshotRequest());
            assertThat(response.getSnapshotId(), notNullValue());
            toJsonPrettyString("createSnapshot", response);
        }

        @Test
        public void listSnapshot() {
            ListSnapshotsResponse response = client.listSnapshots(new ListSnapshotsRequest());
            assertThat(response.getSnapshots(), notNullValue());
            toJsonPrettyString("listSnapshot", response);
        }


        @Test
        public void getSnapshot() {
            String target = snapshots.isEmpty() ? snapshotId : snapshots.get(0);
            GetSnapshotResponse response = client.getSnapshot(target);
            assertThat(response.getSnapshot(), notNullValue());
            assertThat(response.getSnapshot().getExpireTime(), notNullValue());
            toJsonPrettyString("getSnapshot", response);
        }

        //        @Test
        public void deleteSnapshot() {
            client.deleteSnapshot("s-Bkl25CxV");
        }

        @Test
        public void listSnapchain() {
            ListSnapchainRequest request = new ListSnapchainRequest();
            ListSnapchainResponse response = client.listSnapchain(request);
            logger.info("SnapChain={}", response);
        }
    }

    /**
     * Test case about snapshot end
     */


    /**
     * Test case about SecurityGroup begin
     */
    public static class SecurityGroupTest extends BccBase {
        protected BccClient client;
        private List<String> securityGroupIds = new ArrayList<String>();

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
            securityGroupIds.add(client.createSecurityGroup(createSecurityGroupRequest()).getSecurityGroupId());
        }

        @After
        public void tearDown() {
            // do something
            for (String sid : securityGroupIds) {
                client.deleteSecurityGroup(sid);
            }
            super.tearDown();
        }

        @Test
        public void createSecurityGroup() {
            CreateSecurityGroupRequest request = createSecurityGroupRequest();
            CreateSecurityGroupResponse response = client.createSecurityGroup(request);
            assertThat(response.getSecurityGroupId(), notNullValue());
            toJsonPrettyString("createSecurityGroup", response);
            securityGroupIds.add(response.getSecurityGroupId());
        }

        private CreateSecurityGroupRequest createSecurityGroupRequest() {
            List<SecurityGroupRuleModel> rules = new ArrayList<SecurityGroupRuleModel>();
            rules.add(new SecurityGroupRuleModel()
                    .withRemark("ingress_remark")
                    .withProtocol("tcp")
                    .withPortRange("1-65535")
                    .withDirection("ingress")
                    .withSourceIp("")
                    .withSourceGroupId(""));

            rules.add(new SecurityGroupRuleModel()
                    .withRemark("egress_remark")
                    .withProtocol("")
                    .withPortRange("")
                    .withDirection("egress")
                    .withDestIp("")
                    .withDestGroupId(""));
            List<TagModel> tags = new ArrayList<TagModel>();
            tags.add(new TagModel()
                    .withTagKey("testTagKey")
                    .withTagValue("testTagValue"));
            return new CreateSecurityGroupRequest()
                    .withVpcId("vpc-uiudcexceb7y")
                    .withDesc("sdk_desc").withName("sdk_name" + System.currentTimeMillis())
                    .withRules(rules).withTags(tags);
        }

        @Test
        public void listSecurityGroup() {
            ListSecurityGroupsResponse response = client.listSecurityGroups(new ListSecurityGroupsRequest());
            assertThat(response.getSecurityGroups(), notNullValue());
            toJsonPrettyString("listSecurityGroup", response);
        }

        @Test
        public void getSecurityGroupTest() {
            SecurityGroupModel response = client.getSecurityGroup("g-8vrnussccb6s");
            assertThat(response, notNullValue());
            toJsonPrettyString("getSecurityGroup", response);
        }


        @Test
        public void deleteSecurityGroup() {
            if (!securityGroupIds.isEmpty()) {
                String target = securityGroupIds.get(0);
                client.deleteSecurityGroup(target);
                securityGroupIds.remove(target);
            }
        }

        //        @Test
        public void authorizeSecurityGroupRule() {
            SecurityGroupRuleOperateRequest request = new SecurityGroupRuleOperateRequest()
                    .withSecurityGroupId("g-RrAecfjQ");
            SecurityGroupRuleModel ruleModel = new SecurityGroupRuleModel().withProtocol("tcp")
                    .withPortRange("80-90").withDirection("ingress");
            request.withRule(ruleModel);

            client.authorizeSecurityGroupRule(request);
        }

        //        @Test
        public void revokeSecurityGroupRule() {
            SecurityGroupRuleOperateRequest request = new SecurityGroupRuleOperateRequest()
                    .withSecurityGroupId("g-RrAecfjQ");
            SecurityGroupRuleModel ruleModel = new SecurityGroupRuleModel().withProtocol("tcp")
                    .withPortRange("80-90").withDirection("ingress");
            request.withRule(ruleModel);

            client.revokeSecurityGroupRule(request);
        }

        @Test
        public void deleteSecurityGroupRuleTest1() {
            client.deleteSecurityGroupRule("r-073v099jnxe3");
        }

        @Test
        public void deleteSecurityGroupRuleTest2() {
            DeleteSecurityGroupRuleRequest request = new DeleteSecurityGroupRuleRequest();
            request.setSecurityGroupRuleId("r-tizfix8ytq2a");
            client.deleteSecurityGroupRule(request);
        }

        @Test
        public void updateSecurityGroupRuleTest() {
            UpdateSecurityGroupRuleRequest request = new UpdateSecurityGroupRuleRequest();
            request.setSecurityGroupRuleId("r-46zu9vkwxa9v");
            request.setRemark("remark");
            request.setPortRange("1-9999");
            request.setProtocol("tcp");
            request.setSourceIp("180.76.1.0");
            request.setDestGroupId("g-e2pqjjudz2b8");
            client.updateSecurityGroupRule(request);
        }
    }

    /**
     * Test case about SecurityGroup end
     */

    /**
     * Test case about Zone begin
     */
    public static class ZoneTest extends BccBase {
        protected BccClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void listZonesTest() {
            toJsonPrettyString("listZonesTest", client.listZones());
        }
    }
    /**
     * Test case about Zone end
     */

    /**
     * Test case about Asp begin
     */
    public static class AspTest extends BccBase {
        protected BccClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void createAspTest() {
            CreateAspRequest request = new CreateAspRequest();
            request.setName("lv_test");

            request.setRepeatWeekdays(Collections.singletonList(1));
            request.setTimePoints(Collections.singletonList(21));
            request.setRetentionDays("7");
            toJsonPrettyString("createAspTest", client.createAsp(request));
        }

        @Test
        public void deleteAspTest() {
            DeleteAspRequest request = new DeleteAspRequest();
            request.setAspId("asp-1eHdzBIT");
            client.deleteAsp(request);
        }

        @Test
        public void attachAspTest() {
            AttachAspRequest request = new AttachAspRequest();
            request.setAspId("asp-QpxyYezb");
            List<String> volumes = new ArrayList<String>();
            volumes.add("v-tYwujz0V");
            volumes.add("v-3m2JBh0X");
            request.setVolumeIds(volumes);
            client.attachAsp(request);
        }

        @Test
        public void detachAspTest() {
            DetachAspRequest request = new DetachAspRequest();
            request.setAspId("asp-QpxyYezb");
            List<String> volumes = new ArrayList<String>();
            volumes.add("v-tYwujz0V");
            volumes.add("v-3m2JBh0X");
            request.setVolumeIds(volumes);
            client.detachAsp(request);
        }

        @Test
        public void listAspTest() {
            ListAspsRequest request = new ListAspsRequest();
            ListAspsResponse response = client.listAsps(request);
            toJsonPrettyString("listAspTest", response.getAutoSnapshotPolicys());
        }

        @Test
        public void getAspTest() {
            GetAspRequest request = new GetAspRequest();
            request.setAspId("asp-l0OPaeZt");

            GetAspResponse response = client.getAsp(request);
            toJsonPrettyString("getAspTest", response.getAutoSnapshotPolicy());
        }


        @Test
        public void updateAspTest() {
            UpdateAspRequest request = new UpdateAspRequest();
            request.setAspId("asp-l0OPaeZt");
            request.setName("NewName");
            request.setRepeatWeekdays(Collections.singletonList("1"));
            request.setTimePoints(Collections.singletonList("10"));
            request.setRetentionDays("2");
            client.updateAsp(request);
        }
    }
    /**
     * Test case about Asp end
     */

    /**
     * Test case about Deployset begin
     */
    public static class DeploySetTest extends BccBase {
        protected BccClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }


        @Test
        public void listDeploySet() {
            ListDeploySetResponse deploySets = client.listDeploySet();
            toJsonPrettyString("listDeploySet", deploySets);
        }

        @Test
        public void createDeploySet() {
            CreateDeploySetRequest request = new CreateDeploySetRequest();
            request.setName("deploySet2");
            request.setDesc("deploySet2 desc");
            request.setStrategy("HOST_HA");
            request.setConcurrency('2');
            CreateDeploySetResponse deploySet = client.createDeploySet(request);
            toJsonPrettyString("deploySet", deploySet);
        }

        @Test
        public void deleteDeploySet() {
            DeleteDeploySetRequest request = new DeleteDeploySetRequest();
            request.setDeployId("dset-tIVfsdkC");
            client.deleteDeploySet(request);
        }

        @Test
        public void updateDeploySet() {
            UpdateDeploySetRequest request = new UpdateDeploySetRequest();
            request.setDeployId("dset-pHQg46fT");
            request.setDesc("new desc from update");
            request.setDesc("new name from update");
            client.updateDeploySet(request);
        }
    }
    /**
     * Test case about Deployset end
     */

    /**
     * Test case about KeypairTest begin
     */
    public static class KeypairTest extends BccBase {
        protected BccClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void createKeypair() {
            KeypairCreateRequest request = new KeypairCreateRequest();
            request.setName("keypair01");
            request.setDescription("desc...");
            KeypairModel keypair = client.createKeypair(request);
            toJsonPrettyString("createKeypair", keypair);
        }

        @Test
        public void importKeypair() {
            KeypairImportRequest requst = new KeypairImportRequest();
            requst.setName("keypairFromImport");
            requst.setDescription("self desc....");
            requst.setPublicKey("ssh-rsa MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArA/a89pZm8oXxHtfPWuidlESXElSvMCSXlb43TWmgTcSVtjfio5xuChtA7MmUqT60iz649yFoXIiuwEzcaS1GvTpj+F8H6zHFfwdB/EePkYUxF/pWdx/VdiAbiLbMxYYbxMMi7qbd8ZUUF+yf9aRpp7sGVMRyEuhgllgsSyQSg6G5qcjf8Bi+lbhy0+lLtmT2PtbBPbryik6z0svKDlVgBnPxZ/3ACu0hNdO0tTB2lx2fwUGNoUXw2OVhQ5jQxjHyy29IXmQFQBY/YC+2WelpCRHz+U82L+V0NKXygtuRTMpncHxuCXHD4UoQJZXb+XkKfAE12IH/B7K5ZJgRtm1UwIDAQAB");
            KeypairModel keypair = client.importKeypair(requst);
            toJsonPrettyString("importKeypair", keypair);
            /**
             *
             * RSA 2048 PKCS#8
             *
             * -----BEGIN PUBLIC KEY-----
             * MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArA/a89pZm8oXxHtfPWui
             * dlESXElSvMCSXlb43TWmgTcSVtjfio5xuChtA7MmUqT60iz649yFoXIiuwEzcaS1
             * GvTpj+F8H6zHFfwdB/EePkYUxF/pWdx/VdiAbiLbMxYYbxMMi7qbd8ZUUF+yf9aR
             * pp7sGVMRyEuhgllgsSyQSg6G5qcjf8Bi+lbhy0+lLtmT2PtbBPbryik6z0svKDlV
             * gBnPxZ/3ACu0hNdO0tTB2lx2fwUGNoUXw2OVhQ5jQxjHyy29IXmQFQBY/YC+2Wel
             * pCRHz+U82L+V0NKXygtuRTMpncHxuCXHD4UoQJZXb+XkKfAE12IH/B7K5ZJgRtm1
             * UwIDAQAB
             * -----END PUBLIC KEY-----
             *
             * -----BEGIN PRIVATE KEY-----
             * MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCsD9rz2lmbyhfE
             * e189a6J2URJcSVK8wJJeVvjdNaaBNxJW2N+KjnG4KG0DsyZSpPrSLPrj3IWhciK7
             * ATNxpLUa9OmP4XwfrMcV/B0H8R4+RhTEX+lZ3H9V2IBuItszFhhvEwyLupt3xlRQ
             * X7J/1pGmnuwZUxHIS6GCWWCxLJBKDobmpyN/wGL6VuHLT6Uu2ZPY+1sE9uvKKTrP
             * Sy8oOVWAGc/Fn/cAK7SE107S1MHaXHZ/BQY2hRfDY5WFDmNDGMfLLb0heZAVAFj9
             * gL7ZZ6WkJEfP5TzYv5XQ0pfKC25FMymdwfG4JccPhShAlldv5eQp8ATXYgf8Hsrl
             * kmBG2bVTAgMBAAECggEBAKreuAaw0Ypn4MCL8GHzkQxEqKIjYtn1t6WeY1hy3h62
             * TS0YBK75lK9Y3iHteGJcWoX8i09cAQz6ZsklgSwbcIsLqyLqvzRJhhAsJbxsVCdt
             * u1PlF7zYv0/GSIyXau3JxIwR88STla8Vs0d6Ma7IrC425qqjlyY9DXCASAjLA6O2
             * Nb3xvn7tZVsr+Exg79MRrfUxWL1xF/VEol55LnmGh2Zy5y9886pQYhU2buJQWuda
             * L0zOgeDGGfEi/LjNIJhPYBHRRHeKe0id4uARzOe0DU5+Yi/bLTeVOAcs4odJnRP+
             * nhuZEE489twJYTl/NEqzagRraWksswhMlIzSN7KVW2kCgYEA4A4pPN9JgHT6Wa5X
             * TuM5N2p5rZS/b4efvQeVDNbKSXBHr4p7umEWVTIkqySCm24/Z9TVJNbt97KiGTWC
             * u4Bdh1cGLWgiQbCwUFXRb7nfT2z11gq3MUNJlvGBVJRziNgZ1IpofnKr3o3uVp9/
             * tVauxLvLdgoFWOB+eEVMVIm9QbUCgYEAxJf6QTwgcUZmy85oFNPFxZq9pZ+Zf0uL
             * 100DdzXyZ5PIVUSZ1pE6zywFr4mN2djnejN8Bm3hbtbpMrmLX94Tq4hxH5wksQC1
             * nOkSVmHfxe1QCopFcOkAYyZeEy1tTbD9xjVTZqndRz88KfOACNIUioTOOzbdQ20U
             * 2PiZkwa0n+cCgYEA07CNhdXxvEzJvLHcC8zj4lfXXwmLVfTuF8tyKA9IUNqk4FYX
             * UKEqWJRn0MtOG0yu24tbVtM7Qyyjuu2z9zPxKk4up/gbZbVq4Hw0xTr4ojJwkyrM
             * qt7ISLUlXKRmBIUo4odi8xWlDUFMTsuj/dFIjthgA8a0BqUU2M2gsL7y8YECgYEA
             * jeobqC3MSZkFqcflUGuTT9MlZcLxXnfdid05TDvwMZ73tb3gw4hxCI6ByDam5IR0
             * jL1PoaB+ZLHVA133Y4v/bQsAhk4EGpYlhBCnwR3EGK/34LPx7tD0C57tuDBJwBSt
             * DQD/pJOofb8DIlF5smRcmaXG7e3sCblbnEjRK9ZiWy8CgYEApt0cgeQficJUReMO
             * uPMian9sGARJtCuUkZBxWVcv36ScoluEfsGTTaBm0YyFvbvlqulvXWZsZJU3n8Jg
             * QiJ/9RzhLm+qYa9lTOXd7Alqu79bplJ4NdsHV41YZQLcXMyq6i2KYXJUGcTvfqrH
             * 7oZ3PhNCbtJMp45IagRHPUtpiFI=
             * -----END PRIVATE KEY-----
             */
        }

        @Test
        public void listKeypair() {
            KeypairListRequest request = new KeypairListRequest();
            request.setName("abc");
            KeypairListResponse response = client.listKeypair(request);
            toJsonPrettyString("listKeypair", response);
        }

        @Test
        public void getKeypair() {
            KeypairDetailRequest request = new KeypairDetailRequest();
            request.setKeypairId("k-jGp7LjFi");
            KeypairModel keypair = client.keypairDetail(request);
            toJsonPrettyString("getKeypair", keypair);
        }

        @Test
        public void attachKeypair() {
            KeypairAttachRequest request = new KeypairAttachRequest();
            request.setKeypairId("k-jGp7LjFi");
            request.setInstanceIds(Collections.singletonList("i-M1qlCk90"));
            client.attachKeypair(request);
        }

        @Test
        public void detachKeypair() {
            KeypairDetachRequest request = new KeypairDetachRequest();
            request.setKeypairId("k-jGp7LjFi");
            request.setInstanceIds(Collections.singletonList("i-M1qlCk90"));
            client.detachKeypair(request);
        }

        @Test
        public void deleteKeypair() {
            KeypairDeleteRequest request = new KeypairDeleteRequest();
            request.setKeypairId("k-mjQFcndc");
            client.deleteKeypair(request);
        }

        @Test
        public void renameKeypair() {
            KeypairRenameRequest request = new KeypairRenameRequest();
            request.setKeypairId("k-jGp7LjFi");
            request.setName("lv_test_name");
            client.renameKeypair(request);
        }

        @Test
        public void updateKeypairDescription() {
            KeypairUpdateDescRequest request = new KeypairUpdateDescRequest();
            request.setKeypairId("k-jGp7LjFi");
            request.setDescription("update Keypair Description");
            client.updateKeypairDescription(request);
        }
    }

    /**
     * Test case about KeypairTest end
     */

    public static class VolumeClusterTest extends BccBase {
        protected BccClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BccClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        @Test
        public void createVolumeCluster() {
            CreateVolumeClusterRequest request = new CreateVolumeClusterRequest();
            request.setClusterSizeInGB(97280);
            request.setStorageType("hp1");
            request.setPurchaseCount(1);
            Billing billing = new Billing();
            billing.setPaymentTiming("Prepaid");
            Reservation reservation = new Reservation();
            reservation.setReservationLength(6);
            reservation.setReservationTimeUnit("month");
            billing.setReservation(reservation);
            request.setBilling(billing);
            CreateVolumeClusterResponse cluster = client.createVolumeCluster(request);
            toJsonPrettyString("createVolumeCluster", cluster);
        }

        @Test
        public void listVolumeClusters() {
            ListVolumeClustersResponse response = client.listVolumeClusters();
            toJsonPrettyString("listVolumeCLusters", response);
        }

        @Test
        public void getVolumeCluster() {
            toJsonPrettyString("getVolumeCluster", client.getVolumeCluster("DC-g4MhBMut"));
        }

        @Test
        public void resizeVolumeCluster() {
            client.resizeVolumeCluster("DC-eSeCTzV7", 107520);
        }

        @Test
        public void purchaseReservedVolumeCluster() {
            client.purchaseReservedVolumeCluster("DC-eSeCTzV7", 6, "month");
        }

        @Test
        public void autoRenewVolumeCluster() {
            client.autoRenewVolumeCluster("DC-eSeCTzV7", 6, "month");
        }

        @Test
        public void cancelAutoRenewVolumeCluster() {
            CancelAutoRenewVolumeClusterRequest request = new CancelAutoRenewVolumeClusterRequest();
            request.setClusterId("DC-eSeCTzV7");
            client.cancelAutoRenewVolumeCluster(request);
        }

        @Test
        public void refundVolumeClusterTest() {
            client.refundVolumeCluster("DC-eSeCTzV7");
        }


        @Test
        public void createInstanceWithInternalIpsTest() {


            CreateInstanceRequest request = new CreateInstanceRequest();
            request.setSpec("bcc.g4.c1m1");
            request.setRootDiskSizeInGb(40);
            request.setRootDiskStorageType("ssd");
            request.setImageId("m-zlaNc3qH");
            request.setPurchaseCount(1);
            request.setName("InternalIpsTest");
            request.setBilling(new Billing().withPaymentTiming("Postpaid"));
            request.setInternalIps(Collections.singletonList("192.168.0.30"));
            request.setEhcClusterId("ehc-bk4hM1N3");

            client.createInstanceBySpec(request);
        }

        @Test
        public void BatchRefundResourceTest() {

            BatchRefundResourceRequest request = new BatchRefundResourceRequest();
            request.setInstanceId(Collections.singletonList("i-aDfqfgWf"));
            BatchRefundResourceResponse response = client.batchRefundResource(request);
            toJsonPrettyString("batchRefundResource", response);
        }

        @Test
        public void delIpv6Test() {

            InstanceIpv6Request request = new InstanceIpv6Request();
            request.setInstanceId("i-0nPl9WFJ");
            request.setIpv6Address("2400:da00:e003:0:41c:4100:0:5");
            request.setReboot(false);

            client.delIpv6(request);
        }

        @Test
        public void batchAddIpTest() {

            BatchAddIpRequest request = new BatchAddIpRequest();
            request.setInstanceId("i-udU7HS0a");
            request.setAllocateMultiIpv6Addr(true);
            request.setSecondaryPrivateIpAddressCount(2);

            BatchAddIpResponse response = client.batchAddIp(request);
            toJsonPrettyString("batchAddIp", response);
        }


        @Test
        public void batchDelIpTest() {

            BatchDeleteIpRequest request = new BatchDeleteIpRequest();
            request.setInstanceId("i-udU7HS0a");
            request.setPrivateIps(Collections.singletonList("2400:da00:e003:0:41c:4100:0:5"));

            client.batchDeleteIp(request);
        }

        @Test
        public void adlIpv6Test() {

            InstanceIpv6Request request = new InstanceIpv6Request();
            request.setInstanceId("i-0nPl9WFJ");
            request.setIpv6Address("2400:da00:e003:0:41c:4100:0:5");
            request.setReboot(false);

            client.addIpv6(request);
        }


        @Test
        public void getAvailableImagesBySpecTest() {

            GetAvailableImagesBySpecRequest request = new GetAvailableImagesBySpecRequest();
            request.setSpec("bcc.g4.c1m1");
            GetAvailableImagesBySpecResponse response = client.getAvailableImagesBySpec(request);
            toJsonPrettyString("getAvailableImagesBySpec", response);

        }
    }

}
