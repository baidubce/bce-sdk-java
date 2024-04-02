package com.baidubce.services.dumap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.dumap.model.DirectionDrivingParam;
import com.baidubce.services.dumap.model.DirectionRidingParam;
import com.baidubce.services.dumap.model.DirectionTransitParam;
import com.baidubce.services.dumap.model.GeocoderParam;
import com.baidubce.services.dumap.model.GeoconvParam;
import com.baidubce.services.dumap.model.HardwareLocationRequest;
import com.baidubce.services.dumap.model.IPLocationParam;
import com.baidubce.services.dumap.model.PlaceDetailParam;
import com.baidubce.services.dumap.model.PlaceSearchByBoundsParam;
import com.baidubce.services.dumap.model.PlaceSearchByLocationParam;
import com.baidubce.services.dumap.model.PlaceSearchByRegionParam;
import com.baidubce.services.dumap.model.ReverseGeocoderParam;
import com.baidubce.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * DuMapClient test.
 *
 * @author weizhijun
 * @date 2018/10/18
 */
@Slf4j
public class DuMapClientTest {

    private static final String YOUR_ENDPOINT = "yourEndpoint";
    private static final String YOUR_ACCESSKEY = "yourAk";
    private static final String YOUR_SECRETKEY = "yourSk";
    private static final String YOUR_APP_ID = "yourAppId";

    private DuMapClient client;

    @Before
    public void setUp() {
        BceClientConfiguration configuration = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(YOUR_ACCESSKEY, YOUR_SECRETKEY))
                .withEndpoint(YOUR_ENDPOINT);
        client = new DuMapClient(configuration);
    }

    @Test
    public void testPlaceQueryByBoundsJson() {
        PlaceSearchByBoundsParam placeSearchByBoundsParam = PlaceSearchByBoundsParam.builder()
                .query("美食")
                .bounds("38.76623,116.43213,39.54321,116.46773")
                .retCoordtype("gcj02ll")
                .build();
        String response = client.placeQuery(YOUR_APP_ID, placeSearchByBoundsParam);
        log.info("place query response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("results"));
    }

    @Test
    public void testPlaceQueryByBoundsXml() {
        PlaceSearchByBoundsParam placeSearchByBoundsParam = PlaceSearchByBoundsParam.builder()
                .query("美食")
                .bounds("38.76623,116.43213,39.54321,116.46773")
                .retCoordtype("gcj02ll")
                .output("xml")
                .build();
        String response = client.placeQuery(YOUR_APP_ID, placeSearchByBoundsParam);
        log.info("place query response: {}", response);
    }

    @Test
    public void testPlaceQueryByRegionJson() {
        PlaceSearchByRegionParam param = PlaceSearchByRegionParam.builder()
                .query("ATM机")
                .tag("银行")
                .region("北京")
                .build();
        String response = client.placeQuery(YOUR_APP_ID, param);
        log.info("place query response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("results"));
    }

    @Test
    public void testPlaceQueryByRegionXml() {
        PlaceSearchByRegionParam param = PlaceSearchByRegionParam.builder()
                .query("ATM机")
                .tag("银行")
                .region("北京")
                .output("xml")
                .build();
        String response = client.placeQuery(YOUR_APP_ID, param);
        log.info("place query response: {}", response);
    }

    @Test
    public void testPlaceQueryByLocationJson() {
        PlaceSearchByLocationParam param = PlaceSearchByLocationParam.builder()
                .query("银行")
                .location("39.915,116.404")
                .radius("2000")
                .build();
        String response = client.placeQuery(YOUR_APP_ID, param);
        log.info("place query response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("results"));
    }

    @Test
    public void testPlaceQueryByLocationXml() {
        PlaceSearchByLocationParam param = PlaceSearchByLocationParam.builder()
                .query("银行")
                .location("39.915,116.404&")
                .radius("2000")
                .output("xml")
                .build();
        String response = client.placeQuery(YOUR_APP_ID, param);
        log.info("place query response: {}", response);
    }

    @Test
    public void testPlaceDetailJson() {
        PlaceDetailParam param = PlaceDetailParam.builder()
                .uid("08db2caeab1dc6dcfb2213da")
                .scope(2)
                .output("json")
                .build();
        String response = client.placeDetail(YOUR_APP_ID, param);
        log.info("place detail response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("result"));
    }

    @Test
    public void testPlaceDetailXml() {
        PlaceDetailParam param = PlaceDetailParam.builder()
                .uid("08db2caeab1dc6dcfb2213da")
                .scope(2)
                .output("xml")
                .build();
        String response = client.placeDetail(YOUR_APP_ID, param);
        log.info("place detail response: {}", response);
    }

    @Test
    public void testGeocoderJson() {
        GeocoderParam param = GeocoderParam.builder()
                .address("北京市海淀区上地十街10号")
                .output("json")
                .build();
        String response = client.geocoder(YOUR_APP_ID, param);
        log.info("geo coder response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("result"));
    }

    @Test
    public void testGeocoderXml() {
        GeocoderParam param = GeocoderParam.builder()
                .address("北京市海淀区上地十街10号")
                .output("xml")
                .build();
        String response = client.geocoder(YOUR_APP_ID, param);
        log.info("geo coder response: {}", response);
    }

    @Test
    public void testReverseGeocoderJson() {
        ReverseGeocoderParam param = ReverseGeocoderParam.builder()
                .location("35.658651,139.745415")
                .output("json")
                .build();
        String response = client.reverseGeocoder(YOUR_APP_ID, param);
        log.info("reverse geocoder response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("result"));
    }

    @Test
    public void testReverseGeocoderXml() {
        ReverseGeocoderParam param = ReverseGeocoderParam.builder()
                .location("35.658651,139.745415")
                .output("xml")
                .build();
        String response = client.reverseGeocoder(YOUR_APP_ID, param);
        log.info("reverse geocoder response: {}", response);
    }

    @Test
    public void testDirectionTransitJson() {
        DirectionTransitParam param = DirectionTransitParam.builder()
                .origin("40.056878,116.30815")
                .destination("31.222965,121.505821")
                .output("json")
                .build();
        String response = client.direction(YOUR_APP_ID, param);
        log.info("direction response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("result"));
    }

    @Test
    public void testDirectionTransitXml() {
        DirectionTransitParam param = DirectionTransitParam.builder()
                .origin("40.056878,116.30815")
                .destination("31.222965,121.505821")
                .output("xml")
                .pageIndex(1)
                .pageSize(1)
                .build();
        String response = client.direction(YOUR_APP_ID, param);
        log.info("direction response: {}", response);
    }

    @Test
    public void testDirectionRidingJson() {
        DirectionRidingParam param = DirectionRidingParam.builder()
                .origin("40.056878,116.30815")
                .destination("31.222965,121.505821")
                .output("json")
                .build();
        String response = client.direction(YOUR_APP_ID, param);
        log.info("direction response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("result"));
    }

    @Test
    public void testDirectionRidingXml() {
        DirectionRidingParam param = DirectionRidingParam.builder()
                .origin("40.056878,116.30815")
                .destination("31.222965,121.505821")
                .output("xml")
                .build();
        String response = client.direction(YOUR_APP_ID, param);
        log.info("direction response: {}", response);
    }

    @Test
    public void testDirectionDrivingJson() {
        DirectionDrivingParam param = DirectionDrivingParam.builder()
                .origin("40.056878,116.30815")
                .destination("31.222965,121.505821")
                .output("json")
                .build();
        String response = client.direction(YOUR_APP_ID, param);
        log.info("direction response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("result"));
    }

    @Test
    public void testDirectionDrivingXml() {
        DirectionDrivingParam param = DirectionDrivingParam.builder()
                .origin("40.056878,116.30815")
                .destination("31.222965,121.505821")
                .output("xml")
                .build();
        String response = client.direction(YOUR_APP_ID, param);
        log.info("direction response: {}", response);
    }

    @Test
    public void testGeoconvJson() {
        GeoconvParam param = GeoconvParam.builder().coords("114.21892734521,29.575429778924").build();
        String response = client.geoconv(YOUR_APP_ID, param);
        log.info("geoconv response: {}", response);
        HashMap map = JsonUtils.fromJsonString(response, HashMap.class);
        Assert.assertEquals(0, map.get("status"));
        Assert.assertNotNull(map.get("result"));
    }

    @Test
    public void testGeoconvXml() {
        GeoconvParam param = GeoconvParam.builder().coords("114.21892734521,29.575429778924").output("xml").build();
        String response = client.geoconv(YOUR_APP_ID, param);
        log.info("geoconv response: {}", response);
    }

    @Test
    public void testIPLocation() {
        IPLocationParam param = IPLocationParam.builder().ip("61.135.162.115").build();
        String response = client.locate(YOUR_APP_ID, param);
        log.info("locate ip response: {}", response);
    }

    @Test
    public void testHardwareLocation() {
        List<Map<String, Object>> body = new ArrayList<Map<String, Object>>();
        HardwareLocationRequest request = HardwareLocationRequest.builder()
                .src("")
                .prod("")
                .ver("1.0")
                .trace(false)
                .body(body)
                .build();
        String response = client.locate(YOUR_APP_ID, request);
        log.info("locate ip response: {}", response);
    }
}
