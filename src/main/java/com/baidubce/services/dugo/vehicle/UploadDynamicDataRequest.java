/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vehicle dynamic attribute upload request
 * Created by liuzhenxing01 on 2018/10/19.
 */
public class UploadDynamicDataRequest extends AbstractDuGoRequest {
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String SPEED = "speed";
    public static final String DIRECTION = "direction";
    public static final String HEIGHT = "height";
    public static final String RADIUS = "radius";
    public static final String COORD_TYPE_INPUT = "coordTypeInput";

    private List<PointData> points;

    public List<PointData> getPoints() {
        return points;
    }

    public void setPoints(List<PointData> points) {
        this.points = points;
    }

    public static class PointData {
        private Long locTime;
        private String vehicleId;
        private Map<String, Object> data = new HashMap<String, Object>();

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public Map<String, Object> getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }

        public void setLatitude(Double latitude) {
            data.put(LATITUDE, latitude);
        }

        public void setLongitude(Double longitude) {
            data.put(LONGITUDE, longitude);
        }

        public void setCoordTypeInput(String coordTypeInput) {
            data.put(COORD_TYPE_INPUT, coordTypeInput);
        }

        public void setSpeed(Double speed) {
            data.put(SPEED, speed);
        }

        public void setDirection(Integer direction) {
            data.put(DIRECTION, direction);
        }

        public void setHeight(Double height) {
            data.put(HEIGHT, height);
        }

        public void setRadius(Double radius) {
            data.put(RADIUS, radius);
        }
    }
}
