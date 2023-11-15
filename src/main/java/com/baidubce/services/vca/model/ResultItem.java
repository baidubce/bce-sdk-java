/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Vca ResultItem model.
 *
 * @author houshunwei
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultItem {
    private String attribute;
    private Double confidence;
    private String source;
    private List<TimeInSeconds> time;
    private String faceUrl;
    private String image;
    private List<AnalyzeSubTag> subTags;
    private List<Location> location;
    private String version;

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<TimeInSeconds> getTime() {
        return time;
    }

    public void setTime(List<TimeInSeconds> time) {
        this.time = time;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<AnalyzeSubTag> getSubTags() {
        return subTags;
    }

    public void setSubTags(List<AnalyzeSubTag> subTags) {
        this.subTags = subTags;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultItem{");
        sb.append("confidence=").append(confidence);
        sb.append(", attribute='").append(attribute).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", time='").append(time).append('\'');
        sb.append(", faceUrl='").append(faceUrl).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", subTags=").append(subTags);
        sb.append(", location=").append(location);
        sb.append(", version='").append(version).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TimeInSeconds {
        private Integer start;
        private Integer end;

        public Integer getStart() {
            return start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getEnd() {
            return end;
        }

        public void setEnd(Integer end) {
            this.end = end;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TimeInSeconds{");
            sb.append("start=").append(start);
            sb.append(", end=").append(end);
            sb.append('}');
            return sb.toString();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AnalyzeSubTag {
        private String name;
        private List<String> attribute;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getAttribute() {
            return attribute;
        }

        public void setAttribute(List<String> attribute) {
            this.attribute = attribute;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("AnalyzeSubTag{");
            sb.append("name='").append(name).append('\'');
            sb.append(", attribute=").append(attribute);
            sb.append('}');
            return sb.toString();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        private Integer leftOffsetInPixel;
        private Integer topOffsetInPixel;
        private Integer widthInPixel;
        private Integer heightInPixel;
        private Integer degree;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Location{");
            sb.append("leftOffsetInPixel=").append(leftOffsetInPixel);
            sb.append(", topOffsetInPixel=").append(topOffsetInPixel);
            sb.append(", widthInPixel=").append(widthInPixel);
            sb.append(", heightInPixel=").append(heightInPixel);
            sb.append(", degree=").append(degree);
            sb.append('}');
            return sb.toString();
        }

        public Integer getLeftOffsetInPixel() {
            return leftOffsetInPixel;
        }

        public void setLeftOffsetInPixel(Integer leftOffsetInPixel) {
            this.leftOffsetInPixel = leftOffsetInPixel;
        }

        public Integer getTopOffsetInPixel() {
            return topOffsetInPixel;
        }

        public void setTopOffsetInPixel(Integer topOffsetInPixel) {
            this.topOffsetInPixel = topOffsetInPixel;
        }

        public Integer getWidthInPixel() {
            return widthInPixel;
        }

        public void setWidthInPixel(Integer widthInPixel) {
            this.widthInPixel = widthInPixel;
        }

        public Integer getHeightInPixel() {
            return heightInPixel;
        }

        public void setHeightInPixel(Integer heightInPixel) {
            this.heightInPixel = heightInPixel;
        }

        public Integer getDegree() {
            return degree;
        }

        public void setDegree(Integer degree) {
            this.degree = degree;
        }
    }
}