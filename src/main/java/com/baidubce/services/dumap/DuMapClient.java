/*
 * Copyright (c) 2018-2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dumap;

import org.apache.commons.lang3.StringUtils;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
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
import com.baidubce.util.Validate;

/**
 * DuMap client which provides the common service and api.
 *
 * @author weizhijun
 * @date 2018/10/18
 */
public class DuMapClient extends BaseDuMapClient {

    private static final String DEFAULT_OUTPUT = "json";
    private static final int DEFAULT_RADIUS = 1000;
    private static final int DEFAULT_PAGE_INDEX = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_CONV_FROM = 1;
    private static final int DEFAULT_CONV_TO = 5;

    /**
     * Construct a DuMap client with default settings.
     */
    public DuMapClient() {
        super();
    }

    /**
     * Construct a DuMap client with BceClientConfiguration.
     *
     * @param configuration BceClientConfiguration
     */
    public DuMapClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Search some place by rectangular area.
     *
     * @param appId app id
     * @param param PlaceSearchByBoundsParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/Place-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3>https
     * ://cloud.baidu.com/doc/DUMAP/Place-API.html</a>
     */
    public String placeQuery(String appId, PlaceSearchByBoundsParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getQuery(), DuMapValidateMsg.VALIDATE_MESSAGE_SEARCH_KEYWORD);
        Validate.checkStringNotEmpty(param.getBounds(), DuMapValidateMsg.VALIDATE_MESSAGE_SEARCH_AREA);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        return callDuMap("/place/v2/search", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Search some place by administrative area.
     *
     * @param appId app id
     * @param param PlaceSearchByRegionParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/Place-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3>https
     * ://cloud.baidu.com/doc/DUMAP/Place-API.html</a>
     */
    public String placeQuery(String appId, PlaceSearchByRegionParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getQuery(), DuMapValidateMsg.VALIDATE_MESSAGE_SEARCH_KEYWORD);
        Validate.checkStringNotEmpty(param.getRegion(), DuMapValidateMsg.VALIDATE_MESSAGE_SEARCH_AREA);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        return callDuMap("/place/v2/search", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Search some place by circular area.
     *
     * @param appId app id
     * @param param PlaceSearchByLocationParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/Place-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3>https
     * ://cloud.baidu.com/doc/DUMAP/Place-API.html</a>
     */
    public String placeQuery(String appId, PlaceSearchByLocationParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getQuery(), DuMapValidateMsg.VALIDATE_MESSAGE_SEARCH_KEYWORD);
        Validate.checkStringNotEmpty(param.getLocation(), DuMapValidateMsg.VALIDATE_MESSAGE_SEARCH_AREA);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        return callDuMap("/place/v2/search", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Get some place detail.
     *
     * @param appId app id
     * @param param PlaceDetailParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/Place-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3>https
     * ://cloud.baidu.com/doc/DUMAP/Place-API.html</a>
     */
    public String placeDetail(String appId, PlaceDetailParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getUid(), DuMapValidateMsg.VALIDATE_MESSAGE_POI_UID);
        Validate.checkNotNull(param.getScope(), DuMapValidateMsg.VALIDATE_MESSAGE_POI_SCOPE);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        return callDuMap("/place/v2/detail", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Geocode some place.
     *
     * @param appId app id
     * @param param GeocoderParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/Geocoder-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3>https
     * ://cloud.baidu.com/doc/DUMAP/Geocoder-API.html</a>
     */
    public String geocoder(String appId, GeocoderParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getAddress(), DuMapValidateMsg.VALIDATE_MESSAGE_ADDRESS);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        return callDuMap("/geocoder/v2/", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Reverser geocode some place.
     *
     * @param appId app id
     * @param param ReverseGeocoderParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/Geocoder-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3>https
     * ://cloud.baidu.com/doc/DUMAP/Geocoder-API.html</a>
     */
    public String reverseGeocoder(String appId, ReverseGeocoderParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getLocation(), DuMapValidateMsg.VALIDATE_MESSAGE_ADDRESS);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        if (param.getRadius() == 0) {
            param.setRadius(DEFAULT_RADIUS);
        }
        return callDuMap("/geocoder/v2/", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Direct the bus route.
     *
     * @param appId app id
     * @param param DirectionTransitParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/Direction-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3>https
     * ://cloud.baidu.com/doc/DUMAP/Direction-API.html</a>
     */
    public String direction(String appId, DirectionTransitParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getOrigin(), DuMapValidateMsg.VALIDATE_MESSAGE_ORIGIN);
        Validate.checkStringNotEmpty(param.getDestination(), DuMapValidateMsg.VALIDATE_MESSAGE_DESTINATION);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        if (param.getPageSize() == 0) {
            param.setPageSize(DEFAULT_PAGE_SIZE);
        }
        if (param.getPageIndex() == 0) {
            param.setPageIndex(DEFAULT_PAGE_INDEX);
        }
        return callDuMap("/direction/v2/transit", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Direct the riding route.
     *
     * @param appId app id
     * @param param DirectionRidingParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/Direction-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3>https
     * ://cloud.baidu.com/doc/DUMAP/Direction-API.html</a>
     */
    public String direction(String appId, DirectionRidingParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getOrigin(), DuMapValidateMsg.VALIDATE_MESSAGE_ORIGIN);
        Validate.checkStringNotEmpty(param.getDestination(), DuMapValidateMsg.VALIDATE_MESSAGE_DESTINATION);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        return callDuMap("/direction/v2/riding", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Direct the driving route.
     *
     * @param appId app id
     * @param param DirectionDrivingParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/Direction-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3>https
     * ://cloud.baidu.com/doc/DUMAP/Direction-API.html</a>
     */
    public String direction(String appId, DirectionDrivingParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getOrigin(), DuMapValidateMsg.VALIDATE_MESSAGE_ORIGIN);
        Validate.checkStringNotEmpty(param.getDestination(), DuMapValidateMsg.VALIDATE_MESSAGE_DESTINATION);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        return callDuMap("/direction/v2/driving", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Transform the coordinate.
     *
     * @param appId app id
     * @param param GeoconvParam
     *
     * @return Json string / Xml string
     *
     * @See <a href=https://cloud.baidu.com/doc/DUMAP/CoordinatesTransform-API.html#.E6.8E.A5.E5.8F.A3.E6.96.87.E6.A1.A3
     * >https://cloud.baidu.com/doc/DUMAP/CoordinatesTransform-API.html</a>
     */
    public String geoconv(String appId, GeoconvParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(param.getCoords(), DuMapValidateMsg.VALIDATE_MESSAGE_COORDS);
        if (StringUtils.isBlank(param.getOutput())) {
            param.setOutput(DEFAULT_OUTPUT);
        }
        if (param.getFrom() == 0) {
            param.setFrom(DEFAULT_CONV_FROM);
        }
        if (param.getTo() == 0) {
            param.setTo(DEFAULT_CONV_TO);
        }
        return callDuMap("/geoconv/v1/", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Locate IP.
     *
     * @param appId app id
     * @param param IpLocationParam
     *
     * @return Json string
     */
    public String locate(String appId, IPLocationParam param) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        return callDuMap("/location/ip", appId, null, param, HttpMethodName.GET).getPayload();
    }

    /**
     * Locate intelligent hardware.
     *
     * @param appId   app id
     * @param request HardwareLocationRequest
     *
     * @return Json string
     */
    public String locate(String appId, HardwareLocationRequest request) {
        Validate.checkStringNotEmpty(appId, DuMapValidateMsg.VALIDATE_MESSAGE_APP_ID);
        Validate.checkNotNull(request.getSrc(), DuMapValidateMsg.VALIDATE_MESSAGE_SRC);
        Validate.checkNotNull(request.getProd(), DuMapValidateMsg.VALIDATE_MESSAGE_PROD);
        Validate.checkNotNull(request.getVer(), DuMapValidateMsg.VALIDATE_MESSAGE_VER);
        return callDuMap("/locapi/v2", appId, request, null, HttpMethodName.POST, BodyType.JSON).getPayload();
    }
}
