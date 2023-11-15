package com.baidubce.services.evs.model;

import java.io.Serializable;

public class DynamicTrafficFrameRequest extends EvsBaseRequest {

    private static final long serialVersionUID = 5831599197579690891L;

    private boolean enabled;

    private Configuration configuration;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DynamicTrafficFrameRequest that = (DynamicTrafficFrameRequest) o;

        if (enabled != that.enabled) {
            return false;
        }
        return configuration != null ? configuration.equals(that.configuration) : that.configuration == null;
    }

    @Override
    public int hashCode() {
        int result = (enabled ? 1 : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DynamicTrafficFrameRequest{" +
                "enabled=" + enabled +
                ", configuration=" + configuration +
                "} " + super.toString();
    }

    public static class Configuration implements Serializable {

        private static final long serialVersionUID = 1164073562024136036L;

        private FrameLocation frameLocation;

        public FrameLocation getFrameLocation() {
            return frameLocation;
        }

        public void setFrameLocation(FrameLocation frameLocation) {
            this.frameLocation = frameLocation;
        }

        public static class FrameLocation {

            private Double horizontal;

            private Double vertical;

            private Double width;

            private Double height;

            public Double getHorizontal() {
                return horizontal;
            }

            public void setHorizontal(Double horizontal) {
                this.horizontal = horizontal;
            }

            public Double getVertical() {
                return vertical;
            }

            public void setVertical(Double vertical) {
                this.vertical = vertical;
            }

            public Double getWidth() {
                return width;
            }

            public void setWidth(Double width) {
                this.width = width;
            }

            public Double getHeight() {
                return height;
            }

            public void setHeight(Double height) {
                this.height = height;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }

                FrameLocation that = (FrameLocation) o;

                if (Double.compare(that.horizontal, horizontal) != 0) {
                    return false;
                }
                if (Double.compare(that.vertical, vertical) != 0) {
                    return false;
                }
                if (Double.compare(that.width, width) != 0) {
                    return false;
                }
                return Double.compare(that.height, height) == 0;
            }

            @Override
            public int hashCode() {
                int result;
                long temp;
                temp = Double.doubleToLongBits(horizontal);
                result = (int) (temp ^ (temp >>> 32));
                temp = Double.doubleToLongBits(vertical);
                result = 31 * result + (int) (temp ^ (temp >>> 32));
                temp = Double.doubleToLongBits(width);
                result = 31 * result + (int) (temp ^ (temp >>> 32));
                temp = Double.doubleToLongBits(height);
                result = 31 * result + (int) (temp ^ (temp >>> 32));
                return result;
            }

            @Override
            public String toString() {
                return "FrameLocation{" +
                        "horizontal=" + horizontal +
                        ", vertical=" + vertical +
                        ", width=" + width +
                        ", height=" + height +
                        '}';
            }
        }
    }

}