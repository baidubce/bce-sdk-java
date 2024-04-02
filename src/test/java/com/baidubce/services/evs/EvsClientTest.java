package com.baidubce.services.evs;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.evs.model.BindDeviceRequest;
import com.baidubce.services.evs.model.BindDeviceResponse;
import com.baidubce.services.evs.model.DeviceAiAnalysisListMarkRequest;
import com.baidubce.services.evs.model.DeviceAiAnalysisListMarkResponse;
import com.baidubce.services.evs.model.DeviceChannelGetResponse;
import com.baidubce.services.evs.model.DeviceChannelListResponse;
import com.baidubce.services.evs.model.DeviceChannelSignedUrlResponse;
import com.baidubce.services.evs.model.DeviceCountResponse;
import com.baidubce.services.evs.model.DeviceCreateRequest;
import com.baidubce.services.evs.model.DeviceCreateResponse;
import com.baidubce.services.evs.model.DeviceGbConfig;
import com.baidubce.services.evs.model.DeviceGetResponse;
import com.baidubce.services.evs.model.DeviceListMarkRequest;
import com.baidubce.services.evs.model.DeviceListResponse;
import com.baidubce.services.evs.model.DeviceMarkListResponse;
import com.baidubce.services.evs.model.DeviceSignedUrlResponse;
import com.baidubce.services.evs.model.DeviceStopRequest;
import com.baidubce.services.evs.model.DeviceTsStoreListRequest;
import com.baidubce.services.evs.model.DeviceTsStoreListResponse;
import com.baidubce.services.evs.model.DeviceTsStorePageListResponse;
import com.baidubce.services.evs.model.DynamicTrafficFrameRequest;
import com.baidubce.services.evs.model.DeviceUpdateRequest;
import com.baidubce.services.evs.model.GBConfigDownloadResponse;
import com.baidubce.services.evs.model.GBDevicePasswordRequest;
import com.baidubce.services.evs.model.GbPresetAddRequest;
import com.baidubce.services.evs.model.GbPresetAddResponse;
import com.baidubce.services.evs.model.GbPresetGetResponse;
import com.baidubce.services.evs.model.GbPresetListResponse;
import com.baidubce.services.evs.model.SpaceGbProperties;
import com.baidubce.services.evs.model.SpaceMarkListResponse;
import com.baidubce.services.evs.model.SpaceCreateRequest;
import com.baidubce.services.evs.model.SpaceCreateResponse;
import com.baidubce.services.evs.model.SpaceGetResponse;
import com.baidubce.services.evs.model.SpaceListMarkRequest;
import com.baidubce.services.evs.model.SpaceListResponse;
import com.baidubce.services.evs.model.SpaceUpdateRequest;
import com.baidubce.util.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

public class EvsClientTest {

    private static final Logger log = LoggerFactory.getLogger(EvsClientTest.class);
    private static final String AK = "your ak";
    private static final String SK = "your sk";
    private static final String ENDPOINT = "endpoint";
    private EvsClient evsClient;

    @Before
    public void setUp() {
        EvsClientConfiguration config = new EvsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint(ENDPOINT);
        evsClient = new EvsClient(config);
    }

    @Test
    public void createRtmpSpaceTest() {
        SpaceCreateRequest request = new SpaceCreateRequest();
        request.setSpaceName("test_sdk带宽");
        request.setEdgeId(1L);
        request.setEvsChargeType("BANDWIDTH");

        SpaceCreateResponse response = evsClient.createSpace(request);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void createGbSpaceTest() {
        SpaceCreateRequest request = new SpaceCreateRequest();
        request.setType("GB28181");
        request.setSpaceName("gb_sdk");
        request.setEdgeId(1L);

        SpaceCreateResponse response = evsClient.createSpace(request);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void createBVCPSpaceTest() {
        SpaceCreateRequest request = new SpaceCreateRequest();
        request.setType("BVCP");
        request.setSpaceName("bvcp_sdk");
        request.setDeviceMode("EVS-R8345");
        request.setEdgeId(1L);
        request.setDescription("");
        SpaceGbProperties gbProperties = new SpaceGbProperties();
        gbProperties.setInviteImmediate(true);
        gbProperties.setStreamType("TCP");
        gbProperties.setCodeStream("MAIN_STREAM");
        request.setGbProperties(gbProperties);
        SpaceCreateResponse response = evsClient.createSpace(request);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void deleteSpaceTest() {
        evsClient.deleteSpace(1000944);
    }

    @Test
    public void updateSpaceTest() {
        SpaceUpdateRequest request = new SpaceUpdateRequest();
        request.setSpaceName("test_sdk_two");

        evsClient.updateSpace(1000954, request);
    }

    @Test
    public void getSpaceTest() {
        SpaceGetResponse response = evsClient.getSpace(1000954);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void getListMarkSpaceTest() {
        SpaceListMarkRequest request = new SpaceListMarkRequest();
        request.setMaxSize(20);
        request.setType("BVCP");

        SpaceMarkListResponse response = evsClient.listSpaceByMarker(request);
        log.info("marker: {}", response.getMarker());
        log.info("nextMarker: {}", response.getNextMarker());
        Collection<SpaceListResponse> data = response.getData();
        for (SpaceListResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void disableSpaceTest() {
        evsClient.disableSpace(1000954);
    }

    @Test
    public void enableSpaceTest() {
        evsClient.enableSpace(1000954);
    }

    @Test
    public void createRtmpDeviceTest() {
        DeviceCreateRequest request = new DeviceCreateRequest();
        request.setSpaceId(1000954L);
        request.setType("RTMP");
        request.setDeviceName("test_device_sdk");
        request.setDeviceStreamId("huangqiang/rtmp");

        DeviceCreateResponse response = evsClient.createDevice(request);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void createGbDeviceTest() {
        DeviceCreateRequest request = new DeviceCreateRequest();
        request.setSpaceId(1000955L);
        request.setType("GB28181");
        request.setDeviceName("test_device_sdk");
        request.setDeviceStreamId("huangqiang/gb");
        DeviceGbConfig gbConfig = new DeviceGbConfig();
        gbConfig.setGbId("34020000001311000005");
        gbConfig.setUsername("34020000001311000005");
        gbConfig.setPassword("IPC");
        request.setGbConfig(gbConfig);
        DeviceCreateResponse response = evsClient.createDevice(request);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void deleteDeviceTest() {
        evsClient.deleteDevice(1018895);
    }

    @Test
    public void updateDeviceTest() {
        DeviceUpdateRequest request = new DeviceUpdateRequest();
        request.setDeviceName("test_sdk_two");

        evsClient.updateDevice(1020725, request);
    }

    @Test
    public void getDeviceTest() {
        DeviceGetResponse response = evsClient.getDevice(1020725);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void getListMarkDeviceTest() {
        DeviceListMarkRequest request = new DeviceListMarkRequest();
        request.setMaxSize(10);
        request.setSpaceId(1000955L);

        DeviceMarkListResponse response = evsClient.listMarkDevice(request);
        log.info("marker: {}", response.getMarker());
        log.info("nextMarker: {}", response.getNextMarker());
        Collection<DeviceListResponse> data = response.getData();
        for (DeviceListResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void disableDeviceTest() {
        evsClient.disableDevice(1020725, null);
    }

    @Test
    public void enableDeviceTest() {
        evsClient.enableDevice(1020725);
    }

    @Test
    public void getDeviceRecordListTest() {
        DeviceTsStoreListRequest request = new DeviceTsStoreListRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        request.setBegin(1650347171L);
        request.setEnd(1650951971L);

        DeviceTsStorePageListResponse response = evsClient.listDeviceRecord(1034976, request);
        log.info("totalCount: {}", response.getTotalCount());
        Collection<DeviceTsStoreListResponse> data = response.getData();
        for (DeviceTsStoreListResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void getDeviceCountRecodingTest() {
        DeviceCountResponse response = evsClient.countDeviceRecoding(1020636, 1622563200, 1623040798);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void removeDeviceRecordTest() {
        evsClient.removeDeviceRecord(1020636, 1622563200, 1623040798);
    }

    @Test
    public void getDeviceThumbnailListTest() {
        DeviceTsStoreListRequest request = new DeviceTsStoreListRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        request.setBegin(1622563200L);
        request.setEnd(1623040798L);

        DeviceTsStorePageListResponse response = evsClient.listDeviceThumbnail(1020636, request);
        log.info("totalCount: {}", response.getTotalCount());
        Collection<DeviceTsStoreListResponse> data = response.getData();
        for (DeviceTsStoreListResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void getDeviceCountThumbnailTest() {
        DeviceCountResponse response = evsClient.countDevicehumbnail(1020636, 1622563200L,
                1623040798L);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void removeDeviceThumbnailTest() {
        evsClient.removeDeviceThumbnail(1020636, 1623037198L, 1623040798L);
    }

    @Test
    public void getDeviceAiAnalysisTest() {
        DeviceAiAnalysisListMarkRequest request = new DeviceAiAnalysisListMarkRequest();
        request.setMaxSize(15);
        request.setBegin(1703728800000L);
        request.setEnd(1703732400000L);

        DeviceAiAnalysisListMarkResponse response = evsClient.listDeviceAiAnalysisResultByMarker(1060920, "FACE", request);
        Collection<DeviceTsStoreListResponse> data = response.getData();
        for (DeviceTsStoreListResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void generateDeviceSignedUrlTest() {
        String domain = "bfgmcez8saygatc1832.up.evs.bcelive.com";
        String app = "huangqiang";
        String stream = "test";
        String protocol = "rtmp";
        DeviceSignedUrlResponse deviceSignedUrl = evsClient.generateDeviceSignedUrl(domain, app, stream, protocol);
        log.info(deviceSignedUrl.getUrl());
    }

    @Test
    public void getDeviceGbConfigDownloadTest() {
        GBConfigDownloadResponse response = evsClient.getDeviceGbConfigDownload(1020651);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void teleBootDeviceTest() {
        evsClient.teleBootDevice(1020651);
    }

    @Test
    public void trafficFrameDeviceTest() {
        DynamicTrafficFrameRequest dynamicTrafficFrame = new DynamicTrafficFrameRequest();
        dynamicTrafficFrame.setEnabled(true);
        DynamicTrafficFrameRequest.Configuration configuration = new DynamicTrafficFrameRequest.Configuration();
        DynamicTrafficFrameRequest.Configuration.FrameLocation frameLocation = new DynamicTrafficFrameRequest.Configuration.FrameLocation();
        frameLocation.setHorizontal(10.0);
        frameLocation.setVertical(20.0);
        frameLocation.setWidth(40.0);
        frameLocation.setHeight(40.0);
        configuration.setFrameLocation(frameLocation);
        dynamicTrafficFrame.setConfiguration(configuration);

        evsClient.trafficFrameDevice(1020652, dynamicTrafficFrame);
    }

    @Test
    public void modifyDeviceGbPasswordTest() {
        GBDevicePasswordRequest request = new GBDevicePasswordRequest();
        request.setPassword("test123");

        evsClient.modifyDeviceGbPassword(1020651, request);
    }

    @Test
    public void getChannelListTest() {
        DeviceChannelListResponse response = evsClient.listChannel(1020651);
        List<DeviceChannelGetResponse> data = response.getData();
        for (DeviceChannelGetResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void refreshChannelsTest() {
        evsClient.refreshChannels(1020661);
    }

    @Test
    public void getChannelTest() {
        DeviceChannelGetResponse response = evsClient.getChannel(1020652);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void stopChannelTest() {
        DeviceStopRequest request = new DeviceStopRequest();
        evsClient.stopChannel(1020652, request);
    }

    @Test
    public void startChannelTest() {
        evsClient.startChannel(1020652);
    }

    @Test
    public void generateChannelSignedUrlTest() {
        DeviceChannelSignedUrlResponse response = evsClient.generateChannelSignedUrl(1020652, "hls");
        log.info(response.getUrl());
    }

    @Test
    public void getChannelRecordListTest() {
        DeviceTsStoreListRequest request = new DeviceTsStoreListRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        request.setBegin(1622995200L);
        request.setEnd(1623050738L);

        DeviceTsStorePageListResponse response = evsClient.listChannelRecord(1020652, request);
        log.info("totalCount: {}", response.getTotalCount());
        Collection<DeviceTsStoreListResponse> data = response.getData();
        for (DeviceTsStoreListResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void getChannelCountRecodingTest() {
        DeviceCountResponse response = evsClient.countChannelRecoding(1020652, 1622995200L, 1623050738L);
        log.info(JsonUtils.toJsonString(response));
    }


    @Test
    public void getChannelThumbnailListTest() {
        DeviceTsStoreListRequest request = new DeviceTsStoreListRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        request.setBegin(1622995200L);
        request.setEnd(1623050738L);

        DeviceTsStorePageListResponse response = evsClient.listChannelThumbnail(1020652, request);
        log.info("totalCount: {}", response.getTotalCount());
        Collection<DeviceTsStoreListResponse> data = response.getData();
        for (DeviceTsStoreListResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void getChannelCountThumbnailTest() {
        DeviceCountResponse response = evsClient.countChannelThumbnail(1020652, 1622390400,
                1623051009);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void removeChannelThumbnailTest() {
        evsClient.removeChannelThumbnail(1020652, 1622995200, 1623051078);
    }

    @Test
    public void getChannelAiAnalysisTest() {
        DeviceAiAnalysisListMarkRequest request = new DeviceAiAnalysisListMarkRequest();
        request.setMaxSize(15);
        request.setBegin(1703728800000L);
        request.setEnd(1703754000000L);

        DeviceAiAnalysisListMarkResponse response = evsClient.listChannelAiAnalysisResultByMarker(1040844, "FACE"
                , request);
        Collection<DeviceTsStoreListResponse> data = response.getData();
        for (DeviceTsStoreListResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void modifyChannelPtzTest() {
        evsClient.modifyChannelPtz(1020652, "right", 2);
    }

    @Test
    public void modifyChannelFiTest() {
        evsClient.modifyChannelFi(1020652, "focusfar", 10);
    }

    @Test
    public void addChannelPresetTest() {
        GbPresetAddRequest request = new GbPresetAddRequest();
        request.setName("test_preset_3");
        GbPresetAddResponse response = evsClient.addChannelPreset(1020652, request);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void deleteChannelPresetTest() {
        evsClient.deleteChannelPreset(1020652, 1);
    }

    @Test
    public void getChannelPresetListTest() {
        GbPresetListResponse response = evsClient.listChannelPreset(1020652);

        Collection<GbPresetGetResponse> data = response.getData();
        for (GbPresetGetResponse datum : data) {
            log.info(JsonUtils.toJsonString(datum));
        }
    }

    @Test
    public void gotoChannelPresetTest() {
        evsClient.gotoChannelPreset(1020652, 1);
    }

    @Test
    public void bindDeviceBySnCodeTest(){
        BindDeviceRequest request = new BindDeviceRequest();
        request.setDeviceName("test");
        request.setDeviceMode("TD-9555A3");
        request.setSpaceId(1003287L);
        request.setSnCode("HT0018aed80621");
        BindDeviceResponse response = evsClient.bindDeviceBySnCode(request);
        log.info(JsonUtils.toJsonString(response));
    }

}
