/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response for driving behavior
 */
public class DrivingBehaviorResponse extends AbstractBceResponse {
    private Double distance;
    private Integer duration;
    private Double averageSpeed;
    private Double maxSpeed;
    private Integer speedingNum;
    private Integer harshAccelerationNum;
    private Integer harshBreakingNum;
    private Integer harshSteeringNum;
    private Point startPoint;
    private Point endPoint;
    private List<Speeding> speeding;
    private List<HarshAcceleration> harshAcceleration;
    private List<HarshBreaking> harshBreaking;
    private List<HarshSteering> harshSteering;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getSpeedingNum() {
        return speedingNum;
    }

    public void setSpeedingNum(Integer speedingNum) {
        this.speedingNum = speedingNum;
    }

    public Integer getHarshAccelerationNum() {
        return harshAccelerationNum;
    }

    public void setHarshAccelerationNum(Integer harshAccelerationNum) {
        this.harshAccelerationNum = harshAccelerationNum;
    }

    public Integer getHarshBreakingNum() {
        return harshBreakingNum;
    }

    public void setHarshBreakingNum(Integer harshBreakingNum) {
        this.harshBreakingNum = harshBreakingNum;
    }

    public Integer getHarshSteeringNum() {
        return harshSteeringNum;
    }

    public void setHarshSteeringNum(Integer harshSteeringNum) {
        this.harshSteeringNum = harshSteeringNum;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public List<Speeding> getSpeeding() {
        return speeding;
    }

    public void setSpeeding(
            List<Speeding> speeding) {
        this.speeding = speeding;
    }

    public List<HarshAcceleration> getHarshAcceleration() {
        return harshAcceleration;
    }

    public void setHarshAcceleration(
            List<HarshAcceleration> harshAcceleration) {
        this.harshAcceleration = harshAcceleration;
    }

    public List<HarshBreaking> getHarshBreaking() {
        return harshBreaking;
    }

    public void setHarshBreaking(
            List<HarshBreaking> harshBreaking) {
        this.harshBreaking = harshBreaking;
    }

    public List<HarshSteering> getHarshSteering() {
        return harshSteering;
    }

    public void setHarshSteering(
            List<HarshSteering> harshSteering) {
        this.harshSteering = harshSteering;
    }

    public static class HarshAcceleration {
        private Double latitude;
        private Double longitude;
        private String coordType;
        private Long locTime;
        private Double acceleration;
        private Double initialSpeed;
        private Double endSpeed;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public String getCoordType() {
            return coordType;
        }

        public void setCoordType(String coordType) {
            this.coordType = coordType;
        }

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }

        public Double getAcceleration() {
            return acceleration;
        }

        public void setAcceleration(Double acceleration) {
            this.acceleration = acceleration;
        }

        public Double getInitialSpeed() {
            return initialSpeed;
        }

        public void setInitialSpeed(Double initialSpeed) {
            this.initialSpeed = initialSpeed;
        }

        public Double getEndSpeed() {
            return endSpeed;
        }

        public void setEndSpeed(Double endSpeed) {
            this.endSpeed = endSpeed;
        }
    }

    public static class Point {
        private Double latitude;
        private Double longitude;
        private String coordType;
        private Long locTime;
        private String address;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public String getCoordType() {
            return coordType;
        }

        public void setCoordType(String coordType) {
            this.coordType = coordType;
        }

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }


    public static class Speeding {
        private Double speedingDistance;
        private List<SpeedingPoint> speedingPoints;

        public Double getSpeedingDistance() {
            return speedingDistance;
        }

        public void setSpeedingDistance(Double speedingDistance) {
            this.speedingDistance = speedingDistance;
        }

        public List<SpeedingPoint> getSpeedingPoints() {
            return speedingPoints;
        }

        public void setSpeedingPoints(
                List<SpeedingPoint> speedingPoints) {
            this.speedingPoints = speedingPoints;
        }
    }

    public static class SpeedingPoint {
        private Double latitude;
        private Double longitude;
        private String coordType;
        private Long locTime;
        private Double actualSpeed;
        private Double limitSpeed;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public String getCoordType() {
            return coordType;
        }

        public void setCoordType(String coordType) {
            this.coordType = coordType;
        }

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }

        public Double getActualSpeed() {
            return actualSpeed;
        }

        public void setActualSpeed(Double actualSpeed) {
            this.actualSpeed = actualSpeed;
        }

        public Double getLimitSpeed() {
            return limitSpeed;
        }

        public void setLimitSpeed(Double limitSpeed) {
            this.limitSpeed = limitSpeed;
        }
    }

    public static class HarshBreaking {
        private Double latitude;
        private Double longitude;
        private String coordType;
        private Long locTime;
        private Double acceleration;
        private Double initialSpeed;
        private Double endSpeed;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public String getCoordType() {
            return coordType;
        }

        public void setCoordType(String coordType) {
            this.coordType = coordType;
        }

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }

        public Double getAcceleration() {
            return acceleration;
        }

        public void setAcceleration(Double acceleration) {
            this.acceleration = acceleration;
        }

        public Double getInitialSpeed() {
            return initialSpeed;
        }

        public void setInitialSpeed(Double initialSpeed) {
            this.initialSpeed = initialSpeed;
        }

        public Double getEndSpeed() {
            return endSpeed;
        }

        public void setEndSpeed(Double endSpeed) {
            this.endSpeed = endSpeed;
        }
    }

    public static class HarshSteering {
        private Double latitude;
        private Double longitude;
        private String coordType;
        private Long locTime;
        private Double centripetalAcceleration;
        private String turnType;
        private Double speed;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public String getCoordType() {
            return coordType;
        }

        public void setCoordType(String coordType) {
            this.coordType = coordType;
        }

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }

        public Double getCentripetalAcceleration() {
            return centripetalAcceleration;
        }

        public void setCentripetalAcceleration(Double centripetalAcceleration) {
            this.centripetalAcceleration = centripetalAcceleration;
        }

        public String getTurnType() {
            return turnType;
        }

        public void setTurnType(String turnType) {
            this.turnType = turnType;
        }

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }
    }
}
